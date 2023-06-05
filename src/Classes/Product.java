/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Sham
 */
public class Product {

    FileSystem fs = new FileSystem("PRODUCTS.txt");

    private String Id;
    private String Name;
    private String Price;
    private String Qty;
    private String AgeGroup;
    private String Category;
    private String Description;
    private String imageName;
    private int ProductCount;
       

    public Product() {

    }

    public Product(String Id, String Name, String Price, String Qty, String AgeGroup, String Category, String Description, String imageName) {
        this.Id = Id;
        this.Name = Name;
        this.Price = Price;
        this.Qty = Qty;
        this.AgeGroup = AgeGroup;
        this.Category = Category;
        this.Description = Description;
        this.imageName = imageName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        if (Name == null) {
            return "";
        }
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String Qty) {
        this.Qty = Qty;
    }

    public String getAgeGroup() {
        return AgeGroup;
    }

    public void setAgeGroup(String AgeGroup) {
        this.AgeGroup = AgeGroup;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public int getProductCount() {
        try (BufferedReader reader = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
            String line;
            int count = 0;

            while ((line = reader.readLine()) != null) {
                count++;
            }

            return count;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
    

    public boolean addPRODUCTS() {
        if (!fs.createANewFile()) {
            String record = Id + " " + Name + " " + Price + " " + Qty + " " + AgeGroup + " " + Category + " " + Description + " " + imageName;
            //  System.err.println(TID+ " "+MName+" "+Price+" "+Qty+" "+AgeGroup+" "+Category+" "+Product_Description);
            return fs.writeDataToFile(record);
        }
        return false;
    }

    public boolean isPIDExist(String pid) {
        List<String> records = fs.readFileData();

        for (String record : records) {
            String[] fields = record.split(" ");
            String existingPID = fields[0];

            if (existingPID.equals(pid)) {
                return true; // TID already exists
            }
        }

        return false; // TID does not exist
    }

    public void displayProductData(JTable productTable) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Quantity");
        model.addColumn("Age Group");
        model.addColumn("Category");

        productTable.setModel(model);

        File file = new File("PRODUCTS.txt");

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split(" ");
                model.addRow(dataRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Hide the "Description" and "Image Name" columns
        TableColumnModel columnModel = productTable.getColumnModel();
        int descriptionColumnIndex = -1;
        int imageNameColumnIndex = -1;

        columnModel.getColumn(0).setPreferredWidth(50); // ID column width
        columnModel.getColumn(1).setPreferredWidth(80); // Name column width
        columnModel.getColumn(2).setPreferredWidth(30); // Price column width
        columnModel.getColumn(3).setPreferredWidth(30); // Quantity column width
        columnModel.getColumn(4).setPreferredWidth(30); // Age Group column width
        columnModel.getColumn(5).setPreferredWidth(100); // Category column width

        // Disable column width resizing
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }

        // Find the column indexes of "Description" and "Image Name"
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            String columnName = column.getHeaderValue().toString();

            if (columnName.equals("Description")) {
                descriptionColumnIndex = i;
            } else if (columnName.equals("Image Name")) {
                imageNameColumnIndex = i;
            }
        }

        // Remove the "Description" and "Image Name" columns if found
        if (descriptionColumnIndex != -1) {
            TableColumn descriptionColumn = columnModel.getColumn(descriptionColumnIndex);
            columnModel.removeColumn(descriptionColumn);
        }

        if (imageNameColumnIndex != -1) {
            TableColumn imageNameColumn = columnModel.getColumn(imageNameColumnIndex);
            columnModel.removeColumn(imageNameColumn);
        }

        // Disable row editing
        productTable.setDefaultEditor(Object.class, null);
    }

    public boolean searchTOYS(String MName) {
        boolean isFound = false;
        BufferedReader gt = null;
        try {
            String[] words = null;
            gt = fs.readFile();

            String TOYS;
            outerloop:
            while ((TOYS = gt.readLine()) != null) {
                words = TOYS.split(" ");
                for (String word : words) {
                    if (word.contains(MName)) {
                        isFound = true;

                        this.setId(words[0]);
                        this.setName(words[1]);
                        this.setPrice(words[2]);
                        this.setQty(words[3]);
                        this.setAgeGroup(words[4]);
                        this.setCategory(words[5]);
                        this.setDescription(words[6]);
                        this.setImageName(words[7]);
                        break outerloop;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the BufferedReader
            if (gt != null) {
                try {
                    gt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isFound;
    }

    public List<Product> searchAllTOYS(String MName) {
        List<Product> matches = new ArrayList<>();
        try {
            String[] words = null;
            BufferedReader gt = fs.readFile();

            String TOYS;
            while ((TOYS = gt.readLine()) != null) {
                words = TOYS.split(" ");
                if (words[0].contains(MName) || words[1].contains(MName)
                        || words[2].contains(MName) || words[3].contains(MName)
                        || words[4].contains(MName) || words[5].contains(MName)
                        || words[6].contains(MName)) {
                    Product toy = new Product();
                    toy.setId(words[0]);
                    toy.setName(words[1]);
                    toy.setPrice(words[2]);
                    toy.setQty(words[3]);
                    toy.setAgeGroup(words[4]);
                    toy.setCategory(words[5]);
                    toy.setDescription(words[6]);

                    // check if toy with same ID already exists in matches
                    boolean duplicate = false;
                    for (Product match : matches) {
                        if (match.getId().equals(toy.getId())) {
                            duplicate = true;
                            break;
                        }
                    }
                    if (!duplicate) {
                        matches.add(toy);
                    }
                }
            }
        } catch (Exception e) {
        }
        return matches;
    }

    public boolean updatePRODUCTS(String productCode, String updatedRecord, File newImageFile) {
        List<String> records = fs.readFileData();
        List<String> updatedRecords = new ArrayList<>();

        for (String record : records) {
            String[] fields = record.split(" ");
            String code = fields[0];
            if (code.equals(productCode)) {
                updatedRecords.add(updatedRecord);
            } else {
                updatedRecords.add(record);
            }
        }

        try ( FileWriter writer = new FileWriter("tempfile.txt")) {
            for (String updatedRec : updatedRecords) {
                writer.write(updatedRec + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            Path source = Path.of("tempfile.txt");
            Path destination = Path.of("PRODUCTS.txt");
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);

            // Check if a new image file is provided
            if (newImageFile != null) {
                String newFileName = productCode + "." + getFileExtension(newImageFile.getName());
                Path imageSource = newImageFile.toPath();
                Path imageDestination = Path.of("src/Products", newFileName);
                Files.move(imageSource, imageDestination, StandardCopyOption.REPLACE_EXISTING);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String getFileExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex > 0 && extensionIndex < fileName.length() - 1) {
            return fileName.substring(extensionIndex + 1);
        }
        return "";
    }

    public boolean deleteProduct(String productCode) {
        List<String> records = fs.readFileData();
        List<String> updatedRecords = new ArrayList<>();

        boolean productFound = false;

        for (String record : records) {
            String[] fields = record.split(" ");
            String code = fields[0];
            if (code.equals(productCode)) {
                productFound = true;
            } else {
                updatedRecords.add(record);
            }
        }

        if (!productFound) {
            System.out.println("Product Code not found.");
            return false;
        }

        if (!productFound) {
            System.out.println("Product Code not found.");
            return false;
        }

        if (!fs.writeDataToFile("tempfile.txt", String.join("\n", updatedRecords))) {
            System.out.println("Failed to write data to tempfile.txt.");
            return false;
        }

        try {
            Path source = Path.of("tempfile.txt");
            Path destination = Path.of("PRODUCTS.txt");
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
