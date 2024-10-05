package src.commands;

import java.util.List;
import src.Item;

public class AddCommand implements Command{
    private final String[] listOfItems;

    public AddCommand(String[] listOfItems) {
        this.listOfItems = listOfItems;
    }

    @Override
    public void execute(List<Item> cart) {
        for (String itemName : listOfItems) {
            Item item = new Item(itemName);
            if (cart.contains(item)){
                System.out.printf("You have %s in your cart \n", item.getItemName());
            } else {
                cart.add(item);
                System.out.printf("%s added to your cart \n",item.getItemName());
            }
        }
    }
}
