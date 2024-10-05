import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.Item;
import src.commands.AddCommand;
import src.commands.DeleteCommand;
import src.commands.ListCommand;
import src.manager.ShoppingCartDB;
import src.manager.FileName;

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
        ShoppingCartDB manager = new ShoppingCartDB(); 
        FileName fileName = new FileName();

        while (!keyboardInput.equals("exit")) {
            keyboardInput = console.readLine("> ");
            if (keyboardInput.startsWith("login")) {
                //Read Username
                Scanner scanner = new Scanner(keyboardInput.substring(5));
                String username = scanner.nextLine();

                // Load/Create file
                dirPathFileName = fileName.generateFileName(username);
                cart = manager.readCartFile(dirPathFileName, cart);
                isUserLoggedIn = true;
                scanner.close();
            }
            else if (keyboardInput.startsWith("delete")) {
                Scanner scanner = new Scanner(keyboardInput.substring(7));
                String input = scanner.next();
                int number = Integer.parseInt(input) - 1;
                DeleteCommand deleteCommand = new DeleteCommand(number);
                deleteCommand.execute(cart);
                scanner.close();
            } 

            else if (keyboardInput.startsWith("add")) {
                Scanner scanner = new Scanner(keyboardInput);
                String input = scanner.nextLine();
                String trimmedCommand = input.replaceAll(",\\s+", ",");
                String[] arrOfItems = trimmedCommand.substring(4).split(",");
                AddCommand addCommand = new AddCommand(arrOfItems);
                addCommand.execute(cart);
                scanner.close();
            } 
            else if(keyboardInput.equals("list")) {
                ListCommand listCommand = new ListCommand();
                listCommand.execute(cart);
            }
            else if (keyboardInput.equals("save")) {
                if (isUserLoggedIn) {
                    manager.writeToCartFile(dirPathFileName, cart);
                    System.out.println("File successfully saved");
                } else {
                    System.out.println("Please login before saving");
                }
            } 
            else if (keyboardInput.equals("users")) {
                manager.readAllFiles(fileName.getDirPath());
            }
        }
    }
}