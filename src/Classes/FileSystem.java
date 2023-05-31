
package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class FileSystem 
{

    private static String FILE_PATH = "C:\\Users\\Sham\\Documents\\NetBeansProjects\\E_Commerce\\";
    File file;
    private String fileName;

    public FileSystem(String fileName) {
        this.fileName = fileName;
        createANewFile();
    }

    public boolean createANewFile() {
        try {
            file = new File(FILE_PATH + fileName);
            if (file.createNewFile()) {
                System.out.println("File Created: " + file.getName());
                return true;
            }

            System.out.println(" ");
            return false;
        } catch (IOException e) {
            System.out.println("Something went wrowng with creating file" + e);
            return false;
        }
    }
    
    public boolean writeDataToFile(String record) {
        try {
            file.createNewFile();
            FileWriter newWrite = new FileWriter(file, true);
            BufferedWriter newBuffer = new BufferedWriter(newWrite);

            newBuffer.write(record);
            newBuffer.newLine();
            newBuffer.close();
            newWrite.close();
            return true;

        } catch (IOException e) {
            System.out.println("Something went wrowng with creating file" + e);
            return false;
        }
    }
    
     public boolean writeDataToFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("can't write Data To File" + e);
        }
        return false;
    }
    
    public BufferedReader readFile() {
        if (!createANewFile()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);
                return buffer;
            } catch (IOException e) {
                System.out.println("Something went wrowng with creating file" + e);
            }
        }
        return null;
    }
    

    
    public List<String> readuserFileData(String filePath) {
        List<String> records = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            System.out.println("can't Read FileData " + e);
        }

        return records;
    }

   

}
        
        

