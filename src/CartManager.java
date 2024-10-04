package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CartManager {
    public List<Item> readCartFile(String dirPathFileName, List<Item> cart) throws IOException{
        // Check for database
        FileReader fr = new FileReader(dirPathFileName);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while ((line = br.readLine()) != null) {
            Item item = new Item(line);
            cart.add(item);
        }
        br.close();
        fr.close(); 
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
}
