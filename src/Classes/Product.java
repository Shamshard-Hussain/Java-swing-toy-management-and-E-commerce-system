/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.List;

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
  
    
    
    
    
    
    
   
}
    
    
    
    
    
    

