/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Sham
 */
public class EncryptedTotalSumExample {
    
    public static void main(String[] args) {
        
//        String encryptionFilePath = "income.enc"; // Path to the encrypted file
//
//        // Calculate the totalSum
//        double totalSum = 123.45; // Replace with your calculated total sum
//
//        // Check if the encryption file exists
//        File encryptionFile = new File(encryptionFilePath);
//
//        try {
//            double existingSum = 0.0;
//
//            if (encryptionFile.exists()) {
//                // If the encryption file exists, read the existing value
//                FileInputStream fis = new FileInputStream(encryptionFile);
//                ObjectInputStream ois = new ObjectInputStream(fis);
//                existingSum = ois.readDouble();
//                ois.close();
//                fis.close();
//            }
//
//            // Add the new value to the existing sum
//            double newSum = existingSum + totalSum;
//
//            // Write the new sum to the encryption file
//            FileOutputStream fos = new FileOutputStream(encryptionFile);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeDouble(newSum);
//            oos.close();
//            fos.close();
//
//            System.out.println("Total Sum: " + newSum);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



Product toy = new Product();
// Add a value to the total sum
        toy.addToTotalSum(50.0);

        // Retrieve and display the total sum
        double totalSum = toy.getTotalSum();
        System.out.println("Total Sum: " + totalSum);









    }
}
