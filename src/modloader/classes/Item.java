package modloader.classes;

import modloader.classes.components.*;

public class Item implements java.io.Serializable {
    public String itemName = "New Item";
    public String itemId;
    public Texture itemTexture;

    public boolean edibleBool;
    public Edible edible;

    public boolean fuelBool;
    public Fuel fuel;

    public boolean axeBool;
    public Axe axe;

    public boolean pickaxeBool;
    public Pickaxe pickaxe;

    public boolean lightBool;
    public Light light;

    public boolean weaponBool;
    public Weapon weapon;

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

    public boolean storageBool;
    public Storage storage;

    public boolean armorBool;
    public Armor armor;
}
