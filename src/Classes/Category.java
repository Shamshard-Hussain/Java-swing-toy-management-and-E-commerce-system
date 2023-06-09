/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sham
 */
public class Category {
     FileSystem fs =new FileSystem("Category.txt");
    
 private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }



    public boolean addCategory(){
       if (!fs.createANewFile())
       {
         String record =Name;  
         return fs.writeDataToFile(record);
       }
       return false;
    }

    public boolean isCategoryExist(String tid) {
    List<String> records = fs.readFileData();
    
    for (String record : records) {
        String[] fields = record.split(" ");
        String existingTID = fields[0];
        
        if (existingTID.equals(tid)) {
            return true; // TID already exists
        }
    }
    
    return false; // TID does not exist
}
    

    //C:\\Users\\Sham\\Documents\\NetBeansProjects\\E_Commerce\\delete.png
    public void populateCategoryTable(JTable tbl_Category) {
        DefaultTableModel model = (DefaultTableModel) tbl_Category.getModel();
        File file = new File("Category.txt");
        model.setRowCount(0);

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String categoryName = line.trim();
                model.addRow(new Object[]{categoryName, "Delete"});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(String categoryName, DefaultTableModel tableModel, int row) {
        // Implement the logic to delete the category from the system
        // based on the categoryName parameter
        
   //     System.out.println("Deleting category: " + categoryName);
        
        // Remove the selected row from the table model
        tableModel.removeRow(row);
        
        // Update the text file to reflect the deleted category
        try {
            File file = new File("Category.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (!trimmedLine.equals(categoryName)) {
                    writer.write(line + System.lineSeparator());
                }
            }

            writer.close();
            reader.close();
            
            // Delete the original file and rename the temporary file
            if (file.delete()) {
                if (!tempFile.renameTo(file)) {
                    System.out.println("Error renaming the file");
                }
            } else {
                System.out.println("Error deleting the file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  public void populateCategoryComboBox(JComboBox<String> cmbCategory) {
      cmbCategory.removeAllItems();
        try (BufferedReader reader = new BufferedReader(new FileReader("Category.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cmbCategory.addItem(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
  public void populateAllCategoryComboBox(JComboBox<String> cmbCategory) {
    cmbCategory.removeAllItems();
    cmbCategory.addItem("All"); // Add default category "All"

    try (BufferedReader reader = new BufferedReader(new FileReader("Category.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            cmbCategory.addItem(line.trim());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
  
}
