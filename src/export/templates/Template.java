package export.templates;

import modloader.Mod;
import modloader.classes.Item;

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
            ReplaceAll("MOD_ICON", fileComponent(Mod.modIcon.texture.texPath));
            ReplaceAll("MOD_XML_ICON", fileComponent(Mod.modIcon.texture.xmlPath));

        }else if(templateType == Type.Modmain){
            String prefabTemplate = "\"PREFABNAME\",\n";
            String prefabs = "";
            for(Item i: Mod.items){
                prefabs = prefabs + prefabTemplate.replace("PREFABNAME", i.itemId);
                System.out.println(prefabTemplate.replace("PREFABNAME", i.itemId));
            }
            prefabs = prefabs.substring(0, prefabs.length() - 2); //Remove the last ",", and "\n"
            ReplaceAll("PREFAB_FILES", prefabs);


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
