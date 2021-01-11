package modloader.classes;

import modloader.classes.components.*;
import modloader.resources.Resource;

public class Item implements java.io.Serializable {
    public String itemName = "New Item";
    public String itemId = "new_item";
    public int itemTexture;

    public boolean edibleBool;
    public Edible edible;

    public boolean axeBool;
    public Axe axe;

    public boolean hatBool;
    public Hat hat;

    public boolean dappernessBool;
    public Dapperness dapperness;

    public boolean chestBool;
    public Chest chest;

    public boolean handBool;
    public Hand hand;

    public boolean durabilityBool;
    public Durability durability;

    public boolean equipableBool;
    public Equipable equipable;

    public boolean armorBool;
    public Armor armor;
}
