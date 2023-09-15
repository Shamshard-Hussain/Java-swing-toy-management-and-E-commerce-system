/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
    private ImageIcon imageIcon;

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(265, 173, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the resized image
        this.imageIcon = new ImageIcon(resizedImage);
    }

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
        try ( BufferedReader reader = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
            String line;
            int count = 0;

            while ((line = reader.readLine()) != null) {
                count++;
            }

            return count;
        } catch (IOException e) {
             System.out.println("canot read the product file" + e);
        }

        return 0;
    }

    public boolean addPRODUCTS() {
        if (!fs.createANewFile()) {
            String record = Id + " " + Name + " " + Price + " " + Qty + " " + AgeGroup + " " + Category + " " + Description + " " + imageName;

            String filePath = "PRODUCTS.txt"; // Change this to your file path

            if (!fs.isContentEmpty(filePath)) { // Check if the file is not empty
                record = "\n" + record;   // Append a new line before the record
            }

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
            System.out.println("Something went wrowng with displaying product data" + e);
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

    public boolean searchProductData(JTable productTable, String searchQuery) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Quantity");
        model.addColumn("Age Group");
        model.addColumn("Category");

        productTable.setModel(model);

        File file = new File("PRODUCTS.txt");
        boolean found = false; // Flag to track if any matching products were found

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split(" ");

                // Check if any column in the data row contains the search query
                boolean matchFound = false; // Flag to track if a match is found in any column
                for (int i = 0; i < dataRow.length; i++) {
                    String columnValue = dataRow[i].replace("_", " ");
                    if (columnValue.toLowerCase().contains(searchQuery.toLowerCase())) {
                        matchFound = true;
                        break;
                    }
                }

                if (matchFound) {
                    model.addRow(dataRow);
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrowng with search product data" + e);
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

        return found; // Return the flag indicating if any matching products were found
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
            System.out.println("Something went wrowng with searching toy" + e);
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
            System.out.println("Something went wrowng with writing temp file" + e);
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
            System.out.println("Failed to Update Product .");
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
            System.out.println("Something went wrowng with replacing files" + e);
            return false;
        }

        return true;
    }

    public static int generateProductId(BufferedReader reader) {
        int maxId = 0;

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 1) {
                    int currentId = Integer.parseInt(parts[0].substring(1));
                    maxId = Math.max(maxId, currentId);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to generate Product Id.");
        }

        return maxId + 1; // Increment the maximum ID for auto-increment
    }

    public static void displayNextProductId(BufferedReader reader, JTextField textField) {
        int nextId = generateProductId(reader);
        String generatedId = "C" + String.format("%03d", nextId); // Format as "C001"
        textField.setText(generatedId);
    }

    public void resizeImage(Product product) {
        try {
            ImageIcon imageIcon = new ImageIcon(product.getImageName());
            Image image = imageIcon.getImage().getScaledInstance(265, 173, Image.SCALE_SMOOTH);
            product.setImageIcon(new ImageIcon(image));
        } catch (Exception e) {
             System.out.println("Something went wrowng with resize images" + e);
        }
    }

    public void populateHomeProducts(JLabel[] nameLabelLabels, JLabel[] categoryLabelLabels, JLabel[] imageLabels, JLabel[] priceLabelLabels) {

        try ( BufferedReader br = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
            LinkedList<String> lastThreeLines = new LinkedList<>();
            String line;

            // Read and store the last 3 lines of the file
            while ((line = br.readLine()) != null) {
                lastThreeLines.add(line);
                if (lastThreeLines.size() > 3) {
                    lastThreeLines.removeFirst(); // Remove the oldest line if more than 3 lines are stored
                }
            }

            int index = 0; // Initialize an index for the labels arrays

            // Iterate over the last 3 lines in reverse order
            for (int i = lastThreeLines.size() - 1; i >= 0 && index < nameLabelLabels.length; i--) {
                line = lastThreeLines.get(i);
                String[] productData = line.split(" ");
                if (productData.length == 8) {
                    Product product = new Product();
                    product.setId(productData[0]);
                    product.setName(productData[1]);
                    product.setPrice(productData[2]);
                    product.setAgeGroup(productData[3]);
                    product.setCategory(productData[5]);
                    product.setDescription(productData[6]);
                    product.setImageName("src/Products/" + productData[7]);
                    resizeImage(product);

                    // Set the product name and category on the corresponding labels
                    nameLabelLabels[index].setText(product.getName());
                    categoryLabelLabels[index].setText(product.getCategory());
                    priceLabelLabels[index].setText("Rs. " + product.getPrice() + ".00/=");

                    // Set the image on the corresponding JLabel
                    ImageIcon imageIcon = new ImageIcon(product.getImageName());
                    Image scaledImage = imageIcon.getImage().getScaledInstance(
                            imageLabels[index].getWidth(),
                            imageLabels[index].getHeight(),
                            Image.SCALE_SMOOTH
                    );
                    imageLabels[index].setIcon(new ImageIcon(scaledImage));

                    index++; // Increment the index for the next labels
                }
            }
        } catch (IOException e) {
             System.out.println("Something went wrowng with populate home products" + e);
        }
    }
    
    // Method to add a value to the encrypted total sum
    public static void addToTotalSum(double valueToAdd) {
        try {
            double existingSum = 0.0;

            // Check if the encryption file exists
            File encryptionFile = new File("income.enc");

            if (encryptionFile.exists()) {
                // If the encryption file exists, read the existing value
                FileInputStream fis = new FileInputStream(encryptionFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                existingSum = ois.readDouble();
                ois.close();
                fis.close();
            }

            // Add the new value to the existing sum
            double newSum = existingSum + valueToAdd;

            // Write the new sum to the encryption file
            FileOutputStream fos = new FileOutputStream(encryptionFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeDouble(newSum);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Something went wrowng with create total income" + e);
        }
    }

    // Method to retrieve the total sum
    public static double getTotalSum() {
        try {
            // Check if the encryption file exists
            File encryptionFile = new File("income.enc");

            if (encryptionFile.exists()) {
                // If the encryption file exists, read the existing value
                FileInputStream fis = new FileInputStream(encryptionFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                double totalSum = ois.readDouble();
                ois.close();
                fis.close();
                return totalSum;
            }
        } catch (IOException e) {
           System.out.println("Something went wrowng with displaying total income" + e);
        }
        return 0.0; // Return 0 if the file doesn't exist or if there was an error
    }
    
    
}

