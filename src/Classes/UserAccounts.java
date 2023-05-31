
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAccounts {
    FileSystem fs =new FileSystem("Customer.txt");
        private String User_F_Name;
        private String User_L_Name;
        private String User_Email;
        private String User_Password;
        private int UsersCount;

      public UserAccounts(){}

    public UserAccounts(String User_F_Name, String User_L_Name, String User_Email, String User_Password) 
    {
        this.User_F_Name = User_F_Name;
        this.User_L_Name = User_L_Name;
        this.User_Email = User_Email;
        this.User_Password = User_Password;
        
    }

    public String getUser_F_Name() {
        return User_F_Name;
    }

    public void setUser_F_Name(String User_F_Name) {
        this.User_F_Name = User_F_Name;
    }

    public String getUser_L_Name() {
        return User_L_Name;
    }

    public void setUser_L_Name(String User_L_Name) {
        this.User_L_Name = User_L_Name;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String User_Email) {
        this.User_Email = User_Email;
    }

    public String getUser_Password() {
        return User_Password;
    }

    public void setUser_Password(String User_Password) {
        this.User_Password = User_Password;
    }

    public int getUsersCount() {
        return UsersCount;
    }

    public void setUsersCount(int UsersCount) {
        this.UsersCount = UsersCount;
    }
    
    public boolean addUser(){
       if (!fs.createANewFile())
       {
         String record = User_F_Name+ " "+User_L_Name+" "+User_Email+" "+User_Password;
        //   System.err.println(User_F_Name+ " "+User_L_Name+" "+User_Email+" "+User_Password);
           return fs.writeDataToFile(record);
       }
       return false;
    }
    
    
    public boolean isUserExist(String email) {
    String filePath = "Customer.txt"; // Replace with the actual file path
     List<String> records = fs.readuserFileData(filePath);

    for (String record : records) {
        String[] fields = record.split(" ");
        String recordEmail = fields[2]; // Assuming email is at index 2 in the record

        if (recordEmail.equalsIgnoreCase(email)) {
            return true; // Email exists
        }
    }

    return false; // Email does not exist
    }

 
        
    public String viewAllUser() {
        File file = new File("Customer.txt");
        String[] dataRow = null;
        String allUser = null;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            Object[] tablelines = br.lines().toArray();
            for (int i = 0; i < tablelines.length; i++) 
            {
                String line = tablelines[i].toString().trim();
                dataRow = line.split(" ");

                if (allUser == null) {allUser = dataRow[0] + " " + dataRow[1] + " " + dataRow[2] + " ";} 
                else { allUser = allUser + "/" + dataRow[0] + " " + dataRow[1] + " " + dataRow[2] + " "; } 
            }
        } 
        
        catch (FileNotFoundException ex) 
        {Logger.getLogger(UserAccounts.class.getName()).log(Level.SEVERE, null, ex);}
        
        return allUser;
    }
    
    public boolean login(String email, String password) {
    String filePath = "Customer.txt"; // Replace with the actual file path
     List<String> records = fs.readuserFileData(filePath);

    for (String record : records) {
        String[] fields = record.split(" ");
        String recordEmail = fields[2]; // Assuming email is at index 2 in the record
        String recordPassword = fields[3]; // Assuming password is at index 3 in the record

        if (recordEmail.equalsIgnoreCase(email) && recordPassword.equals(password)) {
            return true; // Login successful
        }
    }

    return false; // Login failed
}
    
    
}
