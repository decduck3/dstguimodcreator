package modloader.resources;

import modloader.classes.Texture;

import java.util.*;
import java.io.*;

public class ResourceLoader {
    public static List<Resource> resources = new ArrayList<Resource>();

    public static void LoadResource(String tex, String xml){
        Resource resource = new Resource();
        resource.isTexture = true;
        resource.isOther = false;

        resource.texture = new Texture();
        resource.texture.texPath = tex;
        resource.texture.xmlPath = xml;

        resource.filePath = "";

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
}
