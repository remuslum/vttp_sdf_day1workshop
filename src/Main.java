package src;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = System.console();
        List<String> cart = new ArrayList<>();
        String keyboardInput = "";

        System.out.println("Welcome to your shopping cart");
        System.out.println("====================");
        System.out.println("Commands List:");
        System.out.println("LIst items in your cart: list");
        System.out.println("Add items in your cart: add");
        System.out.println("Delete an item in your list: delete 1");
        System.out.println("Exit the cart: exit");

        while (!keyboardInput.equals("exit")) {
            keyboardInput = console.readLine("> ");
            if (keyboardInput.startsWith("delete")) {
                Scanner scanner = new Scanner(keyboardInput.substring(7));
                String input = scanner.next();
                int number = Integer.parseInt(input) - 1;
                if (number >= cart.size()) {
                    System.out.println("Incorrect item index");
                } else {
                    String item = cart.get(number);
                    cart.remove(number);
                    System.out.printf("%s removed from cart \n", item);
                }
                scanner.close();
            } 

            else if (keyboardInput.startsWith("add")) {
                Scanner scanner = new Scanner(keyboardInput);
                String input = scanner.nextLine();
                String trimmedCommand = input.replaceAll(",\\s+", ",");
                String[] arrOfItems = trimmedCommand.substring(4).split(",");
                for (String item : arrOfItems) {
                    if (cart.contains(item)) {
                        System.out.printf("You have %s in your cart \n", item);
                    } else {
                        cart.add(item);
                        System.out.printf("%s added to your cart \n",item);
                    }
                }
                scanner.close();
            } 

            else if(keyboardInput.equals("list")) {
                if (!cart.isEmpty()) {
                    for (int i = 0; i < cart.size(); i++) {
                        System.out.printf("%d. %s \n",i+1,cart.get(i));
                    }
                } else {
                    System.out.println("Your cart is empty");
                }
            }
        }        
    }
}