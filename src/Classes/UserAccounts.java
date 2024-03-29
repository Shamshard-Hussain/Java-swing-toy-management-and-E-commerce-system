
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    
    public int getCustomerCount() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Customer.txt"))) {
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
    
    public boolean addUser() {
    if (!fs.createANewFile()) {
        String record = User_F_Name+ " "+User_L_Name+" "+User_Email+" "+User_Password;
        //   System.err.println(User_F_Name+ " "+User_L_Name+" "+User_Email+" "+User_Password);
        
        String filePath = "Customer.txt"; // Change this to your file path
        
        if (!fs.isContentEmpty(filePath)) { // Check if the file is not empty
            record = "\n" + record;   // Append a new line before the record
        }
        
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
        String firstName = fields[0]; 
        String recordEmail = fields[2]; // Assuming email is at index 2 in the record
        String recordPassword = fields[3]; // Assuming password is at index 3 in the record
        

        if (recordEmail.equalsIgnoreCase(email) && recordPassword.equals(password)) {
            setUser_F_Name(firstName); // Set the user's first name in the UserAccounts object
            return true; // Login successful
            
        }
    }

    return false; // Login failed
}
    
    
public boolean resetPassword(String email, String newPassword) {
    String filePath = "Customer.txt"; // Replace with the actual file path
    List<String> records = fs.readuserFileData(filePath);

    for (int i = 0; i < records.size(); i++) {
        String record = records.get(i);
        String[] fields = record.split(" ");
        String recordEmail = fields[2]; // Assuming email is at index 2 in the record

        if (recordEmail.equalsIgnoreCase(email)) {
            // Update the password in the record
            fields[3] = newPassword; // Assuming password is at index 3 in the record
            records.set(i, String.join(" ", fields)); // Update the modified record
            
            // Convert the list of records to a single string with newline characters
            String updatedContent = String.join("\n", records);

            fs.writeDataToFile(filePath, updatedContent); // Write updated content back to the file
            return true; // Password reset successful
        }
    }

    return false; // Email not found, password reset failed
}

public String getUserFirstName(String userId) {
        String filePath = "Customer.txt"; // Replace with the actual file path
        List<String> records = fs.readuserFileData(filePath);

        for (String record : records) {
            String[] fields = record.split(" ");
            String recordUserId = fields[2]; // Assuming user ID is at index 0 in the record
            String firstName = fields[0];    // Assuming first name is at index 1 in the record
            
            if (recordUserId.equalsIgnoreCase(userId)) {
                return firstName; // Return the first name if the user ID matches
            }
        }

        return null; // Return null if user ID not found
    }

}



