package savesystem;

import items.Item;
import logging.Logger;
import modloader.Mod;
import modloader.resources.Resource;
import modloader.resources.ResourceManager;
import recipes.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SaveObject implements java.io.Serializable {
    public String modName;
    public String modAuthor;
    public String modDescription;
    public String modVersion;
    public int modIcon;

    public List<Item> items = new ArrayList<Item>();
    public List<Resource> resources = new ArrayList<Resource>();
    public List<Recipe> recipes = new ArrayList<Recipe>();

    public SaveObject(){
        modName = Mod.modName;
        modAuthor = Mod.modAuthor;
        modDescription = Mod.modDescription;
        modVersion = Mod.modAuthor;
        modIcon = Mod.modIcon;

        items = Mod.items;
        ResourceManager.GenerateResourceLists();
        resources = ResourceManager.resources;
        recipes = Mod.recipes;

        Logger.Log("Created SaveObject");
    }

    public void LoadBack(){
        Mod.modName = modName;
        Mod.modAuthor = modAuthor;
        Mod.modDescription = modDescription;
        Mod.modVersion = modVersion;
        Mod.modIcon = modIcon;

        Mod.items.clear();
        for(int i = 0; i < items.size(); i++){
            Mod.items.add(items.get(i));
        }
        Mod.recipes.clear();
        for(int i = 0; i < recipes.size(); i++){
            Mod.recipes.add(recipes.get(i));
        }


        LoadResourcesList(resources);
        ResourceManager.GenerateResourceLists();

        Logger.Log("Loaded from SaveObject");
    }

    public void LoadResourcesList(List<Resource> a){
        for(Resource r: a){
            ResourceManager.LoadResource(r);
            if(r.isSpeech){
                System.out.println(r.speechFile.filePath);
            }
        }
    }
}
