import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.CartManager;
import src.Item;

public class Main {
    public static void main(String[] args) throws IOException{

        System.out.println("Welcome to your shopping cart");
        System.out.println("====================");
        System.out.println("Commands List:");
        System.out.println("LIst items in your cart: list");
        System.out.println("Add items in your cart: add");
        System.out.println("Delete an item in your list: delete 1");
        System.out.println("Exit the cart: exit");
        System.out.println("Please login to your existing account");
        
        Console console = System.console();
        String keyboardInput = "";
        String dirPath = "";
        String dirPathFileName = "";
        boolean isUserLoggedIn = false;
        List<Item> cart = new ArrayList<>();
        String fileType = ".db";
        CartManager manager = new CartManager();

        while (!keyboardInput.equals("exit")) {
            keyboardInput = console.readLine("> ");
            if (keyboardInput.startsWith("login")) {
                // Check if the database cartdb exists
                if (!(new File("cartdb")).exists()){
                    // If it does not, create a new database file named db
                    dirPath = "db";
                } else {
                    dirPath = "cartdb";
                }
                Scanner scanner = new Scanner(keyboardInput.substring(5));
                dirPathFileName = dirPath + File.separator + scanner.nextLine() + fileType;
                File file = new File(dirPathFileName);
                if (!file.exists()) {
                    file.createNewFile();
                    cart = new ArrayList<>();
                } else {
                    manager.readCartFile(dirPathFileName, cart); 
                }
                scanner.close();
            }
            else if (keyboardInput.startsWith("delete")) {
                Scanner scanner = new Scanner(keyboardInput.substring(7));
                String input = scanner.next();
                int number = Integer.parseInt(input) - 1;
                if (number >= cart.size()) {
                    System.out.println("Incorrect item index");
                } else {
                    Item item = cart.get(number);
                    cart.remove(number);
                    System.out.printf("%s removed from cart \n", item.getItemName());
                }
                scanner.close();
            } 

            else if (keyboardInput.startsWith("add")) {
                Scanner scanner = new Scanner(keyboardInput);
                String input = scanner.nextLine();
                String trimmedCommand = input.replaceAll(",\\s+", ",");
                String[] arrOfItems = trimmedCommand.substring(4).split(",");
                for (String itemName : arrOfItems) {
                    Item item = new Item(itemName);
                    if (cart.contains(item)) {
                        System.out.printf("You have %s in your cart \n", item.getItemName());
                    } else {
                        cart.add(item);
                        System.out.printf("%s added to your cart \n",item.getItemName());
                    }
                }
                scanner.close();
            } 

            else if(keyboardInput.equals("list")) {
                if (!cart.isEmpty()) {
                    for (int i = 0; i < cart.size(); i++) {
                        System.out.printf("%d. %s \n",i+1,cart.get(i).getItemName());
                    }
                } else {
                    System.out.println("Your cart is empty");
                }
            }
        }
        manager.writeToCartFile(dirPathFileName, cart);
    }
}