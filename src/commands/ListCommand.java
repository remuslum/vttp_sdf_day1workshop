package src.commands;

import java.util.List;
import src.Item;

public class ListCommand implements Command{
    
    @Override
    public void execute(List<Item> cart) {
        if (!cart.isEmpty()) {
            for (int i = 0; i < cart.size(); i++) {
                System.out.printf("%d. %s \n",i+1,cart.get(i).getItemName());
            }
        } else {
            System.out.println("Your cart is empty");
        }
    }
}
