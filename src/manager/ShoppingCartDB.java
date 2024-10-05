package src.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import src.Item;

public class ShoppingCartDB {

    public List<Item> readCartFile(String dirPathFileName, List<Item> cart) throws IOException{
        File file = new File(dirPathFileName);
        // Check if data file exists
        if (file.exists()) {
            // Add items to cart if data file exists
            FileReader fr = new FileReader(dirPathFileName);
            BufferedReader br = new BufferedReader(fr);
            String line = "";

            while ((line = br.readLine()) != null) {
                Item item = new Item(line);
                cart.add(item);
            }
            br.close();
            fr.close();
            System.out.println("File has been retrieved and loaded"); 
        } else {
            file.createNewFile();
            System.out.println("A new file has been created");
        }
        return cart;   
    }

    public void writeToCartFile(String fileName, List<Item> cart) throws IOException{
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Item item : cart) {
            bw.append(item.getItemName());
            bw.newLine();
        }
        bw.flush();
        fw.close();
        bw.close();
    }

    public void readAllFiles(String dirPath) throws IOException{
        File[] listOfFiles = new File(dirPath).listFiles();
        if (listOfFiles == null) {
            System.out.println("There are no files");
        } else {
            for (File file : listOfFiles) {
                String fileName = file.getName();
                System.out.println(fileName.substring(0, fileName.length() - 3));
            }
        }
    }

}
