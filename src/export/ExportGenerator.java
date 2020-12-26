package export;

import modloader.classes.Item;

public class ExportGenerator {
    public static String modinfoGenerator(){
        return "modinfo";
    }

    public static String modmainGenerator(){
        return "modmain";
    }

    public static String itemGenerator(Item i){
        return i.itemName;
    }
}
