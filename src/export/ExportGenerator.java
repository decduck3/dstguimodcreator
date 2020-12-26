package export;

import modloader.Mod;
import modloader.classes.Item;

import java.io.File;

public class ExportGenerator {
    public static String modinfoGenerator(){
        String base = ExportTemplates.modinfoTemplate;

        base = base.replace("{0}", Mod.modName);
        base = base.replace("{1}", Mod.modVersion);
        base = base.replace("{2}", Mod.modDescription);
        base = base.replace("{3}", Mod.modAuthor);

        return base;
    }

    public static String modmainGenerator(){
        String base = ExportTemplates.modmainTemplate;

        String prefabs = "";

        for(int i = 0; i < Mod.items.size(); i++){
            prefabs = prefabs + "\"" + Mod.items.get(i) + "\",";
        }

        base = base.replace("{0}", prefabs);
        base = base.replace("{1}", "");

        return base;
    }

    public static String itemGenerator(Item i){
        String base = ExportTemplates.itemTemplate;

        try{
            String assets = ExportTemplates.image.replace("REPLACE", i.itemTexture.texture.texPath) + ExportTemplates.atlas.replace("REPLACE", i.itemTexture.texture.xmlPath);
            base = base.replace("{0}", assets);
        }catch (NullPointerException n){
            base = base.replace("{0}", "");
        }



        base = base.replace("{1}", "");
        base = base.replace("{2}", "");
        try{
            for(int x = 0; x < 2; x++)
                base = base.replace("{3}", new File(i.itemTexture.texture.texPath).getName());
        }catch(NullPointerException n){
            for(int x = 0; x < 2; x++)
                base = base.replace("{3}", "null");
        }


        if(i.equipableBool){
            base = base.replace("{7}", ExportTemplates.equipable.replace("REPLACE", "equipreplace"));
            if(i.handBool){
                base = base.replace("equipreplace", ExportTemplates.hand);
            }else if(i.chestBool){
                base = base.replace("equipreplace", ExportTemplates.chest);
            }else if(i.hatBool){
                base = base.replace("equipreplace", ExportTemplates.hat);
            }
        }
        if(i.armorBool){
            base = base.replace("{7}", ExportTemplates.armor.replace("ARMOR", String.valueOf(i.armor.maxCondition)).replace("ABSORPTION", String.valueOf(i.armor.resistance)));
        }
        if(i.axeBool){
            base = base.replace("{7}", ExportTemplates.axe.replace("SPEED", String.valueOf(i.axe.efficiency)));
        }
        if(i.dappernessBool){
            base = base.replace("{7}", ExportTemplates.dapperness.replace("REPLACE", String.valueOf(i.dapperness.rate)));
        }
        if(i.durabilityBool){
            base = base.replace("{7}", ExportTemplates.duriability.replaceAll("MAX", String.valueOf(i.durability.durability)));
        }
        if(i.edibleBool){
            base = base.replace("{7}",
                    ExportTemplates.edible.replace("HEALTH", String.valueOf(i.edible.health).replace("HUNGER", String.valueOf(i.edible.hunger).replace("SANITY", String.valueOf(i.edible.sanity)))));
        }
        base = base.replace("{7}", "");
        base = base.replace("{6}", i.itemId);

        return base;
    }
}
