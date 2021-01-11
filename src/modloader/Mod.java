package modloader;

import modloader.classes.*;
import modloader.resources.Resource;

import java.util.*;

public class Mod {
    public static String modName;
    public static String modAuthor;
    public static String modDescription;
    public static String modVersion;
    public static int modIcon;

    public static String path;

    public static List<Item> items = new ArrayList<Item>();
}
