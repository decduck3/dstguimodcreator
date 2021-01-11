package savesystem;

import logging.Logger;
import modloader.Mod;
import modloader.classes.*;
import modloader.resources.Resource;
import modloader.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;

public class SaveObject implements java.io.Serializable {
    public String modName;
    public String modAuthor;
    public String modDescription;
    public String modVersion;
    public int modIcon;

    public Item[] items;
    public List<Resource> resources = new ArrayList<Resource>();

    public SaveObject(){
        modName = Mod.modName;
        modAuthor = Mod.modAuthor;
        modDescription = Mod.modDescription;
        modVersion = Mod.modAuthor;
        modIcon = Mod.modIcon;

        items = Mod.items.toArray(new Item[0]);
        ResourceManager.GenerateResourceLists();
        resources = ResourceManager.resources;

        Logger.Log("Created SaveObject");
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
        LoadResourcesList(resources);
        ResourceManager.GenerateResourceLists();

        Logger.Log("Loaded from SaveObject");
    }

    public void LoadResourcesList(List<Resource> a){
        for(Resource r: a){
            ResourceManager.LoadResource(r);
        }
    }
}
