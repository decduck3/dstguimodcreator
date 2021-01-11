package export.templates;

import logging.Logger;
import modloader.Mod;
import modloader.ModLoader;
import modloader.classes.Item;
import modloader.resources.Resource;
import modloader.resources.ResourceManager;

import java.io.File;

public class Template {
    public static String fileComponent(String fname) {
        int pos = fname.lastIndexOf(File.separator);
        if(pos > -1)
            return fname.substring(pos + 1);
        else
            return fname;
    }

    public enum Type{
        Modmain,
        Modinfo,
        Item
    }

    private String template;
    private Type templateType;
    private Item item;

    public Template(String rawTemplate, Type t){
        this.template = rawTemplate;
        this.templateType = t;
    }
    public Template(String rawTemplate, Type t, Item item){
        this.template = rawTemplate;
        this.templateType = t;
        this.item = item;
    }

    public void Create(){
        Logger.Log("Starting template create of " + templateType.name());
        if(templateType == Type.Modinfo){

            ReplaceAll("MOD_NAME", Mod.modName);
            ReplaceAll("MOD_DESCRIPTION", Mod.modDescription);
            ReplaceAll("MOD_AUTHOR", Mod.modAuthor);
            ReplaceAll("MOD_VERSION", Mod.modVersion);
            ReplaceAll("MOD_ICON", fileComponent(ResourceManager.modicons.get(Mod.modIcon).texture.texPath));
            ReplaceAll("MOD_XML_ICON", fileComponent(ResourceManager.modicons.get(Mod.modIcon).texture.xmlPath));

            Logger.Log("Replaced all values, returning");

        }else if(templateType == Type.Modmain){
            String prefabTemplate = "   \"PREFABNAME\",\n";
            String prefabs = "";
            Logger.Log("Generating prefabs...");
            if(Mod.items.size() > 0){
                for(Item i: Mod.items){
                    prefabs = prefabs + prefabTemplate.replace("PREFABNAME", i.itemId);
                }
                prefabs = prefabs.substring(0, prefabs.length()-1); //Remove last newline
                Logger.Log("Replacing prefabs...");
                ReplaceAll("PREFAB_FILES", prefabs);
            }else{
                Logger.Log("Skipping prefabs, no items");
                ReplaceAll("PREFAB_FILES", "");
            }
            Logger.Log("Done prefabs");

            String assets = "";

            String atlasTemplate = "    Asset(\"ATLAS\", \"REPLACE\"),\n";
            String imageTemplate = "    Asset(\"IMAGE\", \"REPLACE\" ),\n";

            Logger.Log("Generating assets...");
            if(ResourceManager.resources.size() > 0){
                for(Resource r:ResourceManager.resources){
                    if(r.isTexture && r.filePath != ""){ //If a texture, and not the mod icon
                        String atlas = atlasTemplate.replace("REPLACE", r.filePath + ModLoader.fileComponent(r.texture.texPath));
                        String image = imageTemplate.replace("REPLACE", r.filePath + ModLoader.fileComponent(r.texture.xmlPath));
                        assets = assets + atlas + image;
                    }
                }
                assets = assets.substring(0, assets.length()-1); //Remove last newline
                Logger.Log("Replacing assets...");
                ReplaceAll("ASSETS", assets);
            }else{
                Logger.Log("No assets, skipping");
                ReplaceAll("ASSETS", "");
            }
            Logger.Log("All done, returning");

        }else if(templateType == Type.Item){



        }
    }

    private void ReplaceAll(String a, String b){
        template = template.replace(a, b);
    }

    public String getTemplate() {
        return template;
    }
}
