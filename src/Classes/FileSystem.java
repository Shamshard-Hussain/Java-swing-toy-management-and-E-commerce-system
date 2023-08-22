package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    private static String FILE_PATH = "C:\\Users\\Sham\\Documents\\NetBeansProjects\\E_Commerce\\";
    File file;
    private String fileName;

    public FileSystem(String fileName) {
        this.fileName = fileName;
        createANewFile();
        createProductFolder();
    }

    private static void createProductFolder() {
        String folderPath = "src/Products"; // Specify the folder path

        File folder = new File(folderPath);

        // Create the folder if it doesn't exist
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Product folder created successfully.");
            } else {
                System.out.println("Failed to create the product folder.");
            }
        } else {
            System.out.println("Product folder already exists.");
        }
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
            System.out.println("Something went wrowng with writing file" + e);
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
                System.out.println("Something went wrowng with reading file" + e);
            }
        }
        return null;
    }

    public List<String> readFileData() {
        List<String> records = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PRODUCTS.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
            reader.close();
        } catch (IOException e) {
              System.out.println("Something went wrowng with reading file" + e);
        }
        return records;
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

   public boolean isContentEmpty(String filePath) {
        try {
            return Files.size(Paths.get(filePath)) == 0;
        } catch (IOException e) {
            System.out.println("Something went wrowng.can't Read FileData " + e);
            return true; // Return true if an error occurs (for safety)
        }
    }
    
    
}
