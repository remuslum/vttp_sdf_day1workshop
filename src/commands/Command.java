package src.commands;

import java.util.List;
import src.Item;

public interface Command {
    public void execute(List<Item> cart);
}
