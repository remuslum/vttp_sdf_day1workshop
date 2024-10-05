package src.commands;

import java.util.List;
import src.Item;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List<Item> cart) {
        if (index >= cart.size()) {
            System.out.println("Incorrect item index");
        } else {
            Item item = cart.get(index);
            cart.remove(index);
            System.out.printf("%s removed from cart \n", item.getItemName());
        }
    }
    
}
