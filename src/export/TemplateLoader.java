package export;

import logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TemplateLoader {

    public static String ITEM_TEMPLATE;
    public static String MODMAIN_TEMPLATE;
    public static String MODINFO_TEMPLATE;

    public static final String ITEM_TEMPLATE_LOCATION = "src/export/templates/item.template";
    public static final String MODMAIN_TEMPLATE_LOCATION = "src/export/templates/modmain.template";
    public static final String MODINFO_TEMPLATE_LOCATION = "src/export/templates/modinfo.template";

    public static void LoadTemplates(){
        ITEM_TEMPLATE = LoadTemplate(ITEM_TEMPLATE_LOCATION);
        MODMAIN_TEMPLATE = LoadTemplate(MODMAIN_TEMPLATE_LOCATION);
        MODINFO_TEMPLATE = LoadTemplate(MODINFO_TEMPLATE_LOCATION);

        System.out.println(MODMAIN_TEMPLATE);
    }

    private static String LoadTemplate(String location){
        File f = new File(location);
        String returnValue = "";
        try {
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()){
                String data = reader.nextLine() + "\n";
                returnValue = returnValue + data;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Logger.Log("Loaded template: " + location);
        return returnValue;
    }

}
