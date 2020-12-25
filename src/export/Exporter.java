package export;

import modloader.Mod;
import modloader.ModLoader;
import modloader.classes.Item;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Exporter {
    public static String pathToDirectory;

    public static void Export(){
        try{
            GetDirectory();
            CreateStructure();
            ExportModInfo();
            ExportItems();
        }catch (IOException e){
            JOptionPane.showMessageDialog(ModLoader.modEditorFrame, e.getMessage(), "IO Error", JOptionPane.WARNING_MESSAGE);
        }



        JOptionPane.showMessageDialog(ModLoader.modEditorFrame, "Export successful");
    }

    public static void GetDirectory(){
        pathToDirectory = new File(Mod.path).getParent() + "/exported_" + Mod.modName;
        System.out.println(pathToDirectory);
    }

    public static void CreateStructure(){
        new File(pathToDirectory).mkdir();
        new File(pathToDirectory + "/scripts").mkdir();
        new File(pathToDirectory + "/anim").mkdir();
        new File(pathToDirectory + "/scripts/prefabs").mkdir();
        new File(pathToDirectory + "/images").mkdir();
        new File(pathToDirectory + "/images/inventoryimages").mkdir();
    }

    public static void ExportModInfo() throws IOException {
        File modinfo = new File(pathToDirectory + "/modinfo.lua");
        File modmain = new File(pathToDirectory + "/modmain.lua");

        modinfo.createNewFile();
        modmain.createNewFile();

        FileWriter modinfoWriter = new FileWriter(modinfo.getAbsolutePath());
        FileWriter modmainWriter = new FileWriter(modmain.getAbsolutePath());

        modinfoWriter.write(ExportGenerator.modinfoGenerator());
        modmainWriter.write(ExportGenerator.modmainGenerator());

        modinfoWriter.close();
        modmainWriter.close();
    }

    public static void ExportItems(){

    }
    private  static void ExportItem(Item i){

    }
}
