package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        List<String> cart = new ArrayList<>();
        System.out.println("Welcome to your shopping cart");
        while (true) {
            command = scanner.nextLine();
            
            if (command.contains("exit")) {
                System.out.println("Bye!");
                break;
            }

            else if (command.contains("delete")) {
                int number = Integer.parseInt(command.substring(7)) - 1;
                if (number >= cart.size()) {
                    System.out.println("Incorrect item index");
                } else {
                    String item = cart.get(number);
                    cart.remove(number);
                    System.out.printf("%s removed from cart \n", item);
                }
                continue;
            } 

            else if (command.contains("add")) {
                String trimmedCommand = command.replaceAll(",\\s+", ",");
                String[] arrOfItems = trimmedCommand.substring(4).split(",");
                for (String item : arrOfItems) {
                    if (cart.contains(item)) {
                        System.out.printf("You have %s in your cart \n", item);
                    } else {
                        cart.add(item);
                        System.out.printf("%s added to your cart \n",item);
                    }
                }
                continue;
            } 

            else if(command.contains("list")) {
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else {
                    for (int i = 0; i < cart.size(); i++) {
                        System.out.printf("%d. %s \n", i+1, cart.get(i));
                    }
                }
                continue;
            }
            scanner.close();   
        }        
    }
}