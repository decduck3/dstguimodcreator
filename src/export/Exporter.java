package export;

import export.templates.Template;
import frames.ExportWindow;
import logging.Logger;
import modloader.Mod;
import modloader.ModLoader;
import modloader.resources.Resource;
import modloader.resources.ResourceManager;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public class Exporter {
    private static ExportWindow exportWindow;
    private static JFrame exportWindowFrame;
    private static int points;

    public static void Export(){
        try{
            InitLoading();
            String modOutput = Mod.path + "_exported/";
            new File(modOutput).mkdir();

            CopyResources(modOutput);
            MoveLoading();

            TemplateLoader.LoadTemplates();
            MoveLoading();

            Templates.CreateTemplates();
            MoveLoading();

            Templates.modinfo.Create();
            Write(Templates.modinfo, modOutput + "modinfo.lua");
            MoveLoading();

            Templates.modmain.Create();
            Write(Templates.modmain, modOutput + "modmain.lua");
            MoveLoading();

            Done();
        }catch(Exception e){
            ModLoader.ShowWarning("There was an error while exporting the mod!");
            Logger.Error(e.getLocalizedMessage());
            Done();
        }

    }
    private static void CopyResources(String outputLocation){
        new File(outputLocation + "images").mkdir();
        new File(outputLocation + "images/inventoryimages").mkdir();
        new File(outputLocation + "images/bigportraits").mkdir();
        for(Resource r: ResourceManager.resources){
            if(r.isTexture) {
                try {
                    Files.copy(Paths.get(r.texture.texPath), Paths.get(outputLocation + r.filePath + ModLoader.fileComponent(r.texture.texPath)), StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(Paths.get(r.texture.xmlPath), Paths.get(outputLocation + r.filePath + ModLoader.fileComponent(r.texture.xmlPath)), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void InitLoading(){
        exportWindow = new ExportWindow();
        exportWindowFrame = new JFrame("Exporting...");
        ImageIcon img = new ImageIcon("src/resources/dstguimodcreatorlogo.png");
        exportWindowFrame.setIconImage(img.getImage());
        exportWindowFrame.setContentPane(exportWindow.getExportWindowFrame());
        exportWindowFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        exportWindowFrame.pack();
        exportWindowFrame.setVisible(true);
        points = 5; //Load Templates, Create Templates, Modmain, Modinfo, Copy Resources
        for(int i = 0; i < Mod.items.size(); i++){
            points += 1;
        }
    }
    private static void Write(Template toWrite, String fileLocation){
        try {
            new File(fileLocation).createNewFile();
            FileWriter f = new FileWriter(fileLocation, false);
            f.write(toWrite.getTemplate());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void MoveLoading(){
        int currentValue = exportWindow.getExportProgressBar().getValue();
        float eachPointValue = 100/points;
        exportWindow.getExportProgressBar().setValue(currentValue + (int) eachPointValue);
        exportWindowFrame.pack();
    }
    private static void Done(){
        exportWindow.getExportProgressBar().setValue(100);
        exportWindowFrame.dispose();
        JOptionPane.showMessageDialog(ModLoader.modEditorFrame, "Done!");
    }
}
