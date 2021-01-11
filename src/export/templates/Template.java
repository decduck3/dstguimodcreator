package export.templates;

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
        if(templateType == Type.Modinfo){

            ReplaceAll("MOD_NAME", Mod.modName);
            ReplaceAll("MOD_DESCRIPTION", Mod.modDescription);
            ReplaceAll("MOD_AUTHOR", Mod.modAuthor);
            ReplaceAll("MOD_VERSION", Mod.modVersion);
            ReplaceAll("MOD_ICON", fileComponent(ResourceManager.modicons.get(Mod.modIcon).texture.texPath));
            ReplaceAll("MOD_XML_ICON", fileComponent(ResourceManager.modicons.get(Mod.modIcon).texture.xmlPath));

        }else if(templateType == Type.Modmain){
            String prefabTemplate = "   \"PREFABNAME\",\n";
            String prefabs = "";
            for(Item i: Mod.items){
                prefabs = prefabs + prefabTemplate.replace("PREFABNAME", i.itemId);
                System.out.println(prefabTemplate.replace("PREFABNAME", i.itemId));
            }
            prefabs = prefabs.substring(0, prefabs.length()-1); //Remove last newline
            ReplaceAll("PREFAB_FILES", prefabs);

            String assets = "";

            String atlasTemplate = "    Asset(\"ATLAS\", \"REPLACE\"),\n";
            String imageTemplate = "    Asset(\"IMAGE\", \"REPLACE\" ),\n";

            for(Resource r:ResourceManager.resources){
                if(r.isTexture && r.filePath != ""){ //If a texture, and not the mod icon
                    String atlas = atlasTemplate.replace("REPLACE", r.filePath + ModLoader.fileComponent(r.texture.texPath));
                    String image = imageTemplate.replace("REPLACE", r.filePath + ModLoader.fileComponent(r.texture.xmlPath));
                    assets = assets + atlas + image;
                }
            }
            assets = assets.substring(0, assets.length()-1); //Remove last newline
            ReplaceAll("ASSETS", assets);

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
