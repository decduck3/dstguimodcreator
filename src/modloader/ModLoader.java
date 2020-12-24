package modloader;

import frames.ModEditor;

import javax.swing.*;

public class ModLoader {
    public static JFrame modEditorFrame;
    public static ModEditor modEditor;

    public static Mod currentMod;

    public static void LoadMod(String path){
        CreateModEditorFrame();

        currentMod = new Mod(path);
    }
    public static void CreateMod(String path, String author, String name){
        CreateModEditorFrame();

        currentMod = new Mod();
        currentMod.modName = name;
        currentMod.modAuthor = author;
    }

    public static void CreateModEditorFrame(){
        modEditorFrame = new JFrame("Mod Editor");
        modEditor = new ModEditor();

        modEditorFrame.setContentPane(modEditor.getModEditorPanel());
        modEditorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        modEditorFrame.pack();
        modEditorFrame.setVisible(true);
    }
}
