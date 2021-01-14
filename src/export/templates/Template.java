package export.templates;

import logging.Logger;
import modloader.Mod;
import modloader.ModLoader;
import items.Item;
import modloader.resources.Resource;
import modloader.resources.ResourceManager;
import speech.SpeechFile;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

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
            Logger.Log("Generating speech...");

            String Speech = "";

            String ItemSpeechTemplate = "STRINGS.CHARACTERS.REPLACE_CHARACTER.REPLACE_ITEM = \"REPLACE_TEXT\"\n";

            for(Resource r:ResourceManager.resources){
                if(r.isSpeech){
                    if(r.speechFile.speechType == SpeechFile.SpeechType.Item){
                        Set<String> keySet = r.speechFile.itemSpeech.speech.keySet();
                        for(Iterator<String> it = keySet.iterator(); it.hasNext();){
                            String next = it.next();
                            String itemSpeech = ItemSpeechTemplate.replace("REPLACE_CHARACTER", next.toUpperCase())
                                    .replace("REPLACE_ITEM", r.speechFile.itemSpeech.itemName.toUpperCase()).replace("REPLACE_TEXT", r.speechFile.itemSpeech.speech.get(next));
                            Speech = Speech + itemSpeech;
                        }
                    }
                }
            }

            ReplaceAll("SPEECH", Speech);

            Logger.Log("Done, returning");

        }else if(templateType == Type.Item){

            Logger.Log("Starting template creation for item of id: " + item.itemId);

            //ASSETS
            String assets = "";

            String atlasTemplate = "    Asset(\"ATLAS\", \"REPLACE\"),\n";
            String imageTemplate = "    Asset(\"IMAGE\", \"REPLACE\" ),\n";
            String animTemplate = "	Asset(\"ANIM\", \"anim/REPLACE.zip\"),\n";

            Logger.Log("Generating assets...");
            if(ResourceManager.resources.size() > 0){
                for(Resource r:ResourceManager.resources){
                    if(r.isTexture && r.filePath != ""){ //If a texture, and not the mod icon
                        String atlas = atlasTemplate.replace("REPLACE", r.filePath + ModLoader.fileComponent(r.texture.texPath));
                        String image = imageTemplate.replace("REPLACE", r.filePath + ModLoader.fileComponent(r.texture.xmlPath));
                        assets = assets + atlas + image;
                    }
                    if(r.isAnim){
                        String anim = animTemplate.replace("REPLACE", ModLoader.fileComponent(r.animFilePath));
                        assets = assets + anim;
                    }
                }
                assets = assets.substring(0, assets.length()-1); //Remove last newline
                Logger.Log("Replacing assets...");
                ReplaceAll("ASSETS", assets);
            }else{
                Logger.Log("No assets, skipping");
                ReplaceAll("ASSETS", "");
            }

            Logger.Log("Starting simple replace");
            ReplaceAll("ID", item.itemId.toLowerCase());
            ReplaceAll("I_UPPER_D", item.itemId.toUpperCase());
            ReplaceAll("NA_ME", item.itemName);
            ReplaceAll("NA_UPPER_ME", item.itemName.toUpperCase());
            Logger.Log("Done");

            //UPPER
            ReplaceAll("UPPER", "");


            //INNER
            ReplaceAll("FILLER", "");
        }
    }

    private void ReplaceAll(String a, String b){
        template = template.replace(a, b);
    }

    public String getTemplate() {
        return template;
    }
}
