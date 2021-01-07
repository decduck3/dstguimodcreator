package modloader.resources;

import modloader.classes.Texture;

import java.util.*;
import java.io.*;

public class ResourceLoader {
    public static List<Resource> resources = new ArrayList<Resource>();

    public static Object currentlySelected;

    public enum TextureLocation{
        InventoryImage,
        ModIcon,
        Portrait,
        MapIcon
    }

    public static void LoadResource(String tex, String xml, TextureLocation texLocation){
        Resource resource = new Resource();
        resource.isTexture = true;
        resource.isOther = false;

        resource.texture = new Texture();
        resource.texture.texPath = tex;
        resource.texture.xmlPath = xml;

        if(texLocation == TextureLocation.InventoryImage){
            resource.filePath = "images/inventoryimages/";
            resource.displayUse = "Inventory Image";
        }else if(texLocation == TextureLocation.ModIcon){
            resource.filePath = "";
            resource.displayUse = "Mod Icon";
        }else if(texLocation == TextureLocation.Portrait){
            resource.filePath = "bigportrait/";
            resource.displayUse = "Character Portrait";
        }else if(texLocation == TextureLocation.MapIcon){
            resource.filePath = "images/map_icons/";
            resource.displayUse = "Map Icon";
        }

        resources.add(resource);
    }

    public static Resource GetResource(String name){
        for(int i = 0; i < resources.size(); i++){
            if(resources.get(i).texture.texPath == name || new File(resources.get(i).texture.texPath).getName() == name){
                return resources.get(i);
            }
        }
        return null;
    }

    public static void RemoveResource(String texPath, String xmlPath){
        for(Resource r:resources){
            if(r.texture.texPath == texPath && r.texture.xmlPath == xmlPath){
                resources.remove(r);
                return;
            }
        }
    }
}
