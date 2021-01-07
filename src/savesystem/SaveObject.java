package savesystem;

import modloader.Mod;
import modloader.classes.*;
import modloader.resources.Resource;
import modloader.resources.ResourceLoader;

import java.util.*;

public class SaveObject implements java.io.Serializable {
    public String modName;
    public String modAuthor;
    public String modDescription;
    public String modVersion;
    public Resource modIcon;

    public Item[] items;
    public Resource[] resources;

    public SaveObject(){
        modName = Mod.modName;
        modAuthor = Mod.modAuthor;
        modDescription = Mod.modDescription;
        modVersion = Mod.modAuthor;
        modIcon = Mod.modIcon;

        items = Mod.items.toArray(new Item[0]);
        resources = ResourceLoader.resources.toArray(new Resource[0]);
    }

    public void LoadBack(){
        Mod.modName = modName;
        Mod.modAuthor = modAuthor;
        Mod.modDescription = modDescription;
        Mod.modVersion = modVersion;
        Mod.modIcon = modIcon;

        Mod.items.clear();
        for(int i = 0; i < items.length; i++){
            Mod.items.add(items[i]);
        }

        ResourceLoader.resources.clear();
        for(int i = 0; i < resources.length; i++){
            ResourceLoader.resources.add(resources[i]);
        }
    }
}
