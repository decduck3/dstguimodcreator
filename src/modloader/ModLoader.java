package modloader;

import frames.ModEditor;
import modloader.classes.Item;
import modloader.classes.Texture;
import modloader.resources.Resource;
import modloader.resources.ResourceLoader;
import savesystem.SaveSystem;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.html.Option;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModLoader {
    public static JFrame modEditorFrame;
    public static ModEditor modEditor;
    public static DefaultTableModel resourceModel;

    public static String fileComponent(String fname) {
        int pos = fname.lastIndexOf(File.separator);
        if(pos > -1)
            return fname.substring(pos + 1);
        else
            return fname;
    }

    public static void LoadMod(String path){
        Mod.path = path;

        SaveSystem.Load(path);

        Debug();

        CreateModEditorFrame();
        Update();
    }
    public static void CreateMod(String path, String author, String name){
        Mod.path = path;

        CreateModEditorFrame();

        Mod.modName = name;
        Mod.modAuthor = author;

        SaveSystem.Save(path);
        Update();
    }

    public static void Debug(){
        System.out.println("Mod Name: " + Mod.modName);
        System.out.println("Mod Author: " + Mod.modAuthor);
        System.out.println("Mod Description: " + Mod.modDescription);
        System.out.println("Mod Version: " + Mod.modVersion);
    }

    public static void Update(){
        int selectedModItem = modEditor.getModItemSelect().getSelectedIndex();
        int selectedModIcon = ResourceLoader.resources.indexOf(Mod.modIcon);
        int selectedModTexture = 0;
        try{
            selectedModTexture = ResourceLoader.resources.indexOf(Mod.items.get(selectedModItem).itemTexture);
        }catch(IndexOutOfBoundsException e){

        }


        modEditor.getModItemSelect().removeAllItems();
        for(int i = 0; i < Mod.items.size(); i++){
            modEditor.getModItemSelect().addItem(Mod.items.get(i).itemName);
        }

        if(Mod.items.size() < selectedModItem){
            selectedModItem = 0;
        }
        modEditor.getModItemSelect().setSelectedIndex(selectedModItem);

        for(int i = 0; i < resourceModel.getRowCount(); i++){
            resourceModel.removeRow(i);
        }

        modEditor.getModIconTextureSelect().removeAllItems();
        modEditor.getModItemTextureSelect().removeAllItems();
        for(Resource r:ResourceLoader.resources){
            resourceModel.addRow(new Object[] { ModLoader.fileComponent(r.texture.texPath), r.texture.texPath, r.texture.xmlPath,  r.displayUse });
            modEditor.getModIconTextureSelect().addItem(fileComponent(r.texture.texPath));
            modEditor.getModItemTextureSelect().addItem(fileComponent(r.texture.texPath));
        }
        if(Mod.items.size() < selectedModIcon){
            selectedModIcon = 0;
        }
        if(Mod.items.size() < selectedModTexture){
            selectedModTexture = 0;
        }
        try{
            modEditor.getModItemTextureSelect().setSelectedIndex(selectedModTexture);
        }catch (IllegalArgumentException e){

        }
        try{
            modEditor.getModIconTextureSelect().setSelectedIndex(selectedModIcon);
        }catch (IllegalArgumentException e){

        }

        modEditor.getModNameTextField().setText(Mod.modName);
        modEditor.getModAuthorTextField().setText(Mod.modAuthor);
        modEditor.getModDescriptTextArea().setText(Mod.modDescription);
        modEditor.getModVersionTextField().setText(Mod.modVersion);

        try {
            var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());

            modEditor.getModItemNameTextField().setText(item.itemName);
            modEditor.getModItemIdTextField().setText(item.itemId);
            //Set the selected for the texture
            modEditor.getAxe().setSelected(item.axeBool);
            modEditor.getDurability().setSelected(item.durabilityBool);
            modEditor.getHat().setSelected(item.hatBool);
            modEditor.getEquipable().setSelected(item.equipableBool);
            modEditor.getDapperness().setSelected(item.dappernessBool);
            modEditor.getEdible().setSelected(item.edibleBool);
            modEditor.getChest().setSelected(item.chestBool);
            modEditor.getArmor().setSelected(item.armorBool);
            modEditor.getHand().setSelected(item.handBool);

            modEditorFrame.pack();
        } catch (Exception e) {

        }
    }

    public static void SaveItem(){
        try {
            var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());

            item.itemName = modEditor.getModItemNameTextField().getText();
            item.itemId = modEditor.getModItemIdTextField().getText();
            item.itemTexture = ResourceLoader.resources.get(modEditor.getModItemTextureSelect().getSelectedIndex());
            item.axeBool = modEditor.getAxe().isSelected();
            item.durabilityBool = modEditor.getDurability().isSelected();
            item.hatBool = modEditor.getHat().isSelected();
            item.equipableBool = modEditor.getEquipable().isSelected();
            item.dappernessBool = modEditor.getDapperness().isSelected();
            item.edibleBool = modEditor.getEdible().isSelected();
            item.chestBool = modEditor.getChest().isSelected();
            item.armorBool = modEditor.getArmor().isSelected();
            item.handBool = modEditor.getHand().isSelected();
        }catch(java.lang.IndexOutOfBoundsException e){

        }
    }

    public static void SaveModConfig(){
        Mod.modName = modEditor.getModNameTextField().getText();
        Mod.modAuthor = modEditor.getModAuthorTextField().getText();
        Mod.modDescription = modEditor.getModDescriptTextArea().getText();
        Mod.modVersion = modEditor.getModVersionTextField().getText();
        Mod.modIcon = ResourceLoader.resources.get(modEditor.getModIconTextureSelect().getSelectedIndex());
    }

    public static void SaveAll(){
        try{
            SaveItem();
            SaveModConfig();
            SaveSystem.Save(Mod.path);
        }catch(Exception e){
            JOptionPane.showMessageDialog(modEditorFrame, e.getLocalizedMessage(), "Error while saving", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void CreateModEditorFrame(){
        modEditorFrame = new JFrame("Mod Editor");
        modEditor = new ModEditor();

        modEditorFrame.setContentPane(modEditor.getModEditorPanel());
        modEditorFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        modEditorFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if(getBool("Save?")){
                    SaveAll();
                }
                modEditorFrame.dispose();
                return;
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        resourceModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        modEditor.getResourcesTable().setModel(resourceModel);

        resourceModel.addColumn("Texture");
        resourceModel.addColumn("TEX Path");
        resourceModel.addColumn("XML Path");
        resourceModel.addColumn("Type");

        //Go see file for definitions
        ModLoaderActions.SetupListeners();

        modEditorFrame.pack();
        modEditorFrame.setVisible(true);
    }

    public static int getInt(String message){
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner spinner = new JSpinner(model);
        JOptionPane.showMessageDialog(modEditorFrame, spinner, message, JOptionPane.QUESTION_MESSAGE);
        return (int)spinner.getValue();
    }

    public static double getFloat(String message){
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 1000, 0.1);
        JSpinner spinner = new JSpinner(model);
        JOptionPane.showMessageDialog(modEditorFrame, spinner, message, JOptionPane.QUESTION_MESSAGE);
        return (double) spinner.getValue();
    }

    public static int getOption(String message, Object[] options){
        JComboBox comboBox = new JComboBox(options);
        JOptionPane.showMessageDialog(modEditorFrame, comboBox, message, JOptionPane.QUESTION_MESSAGE);
        return comboBox.getSelectedIndex();
    }

    public static boolean getBool(String message){
        Object[] options = {
            "Yes",
            "No"
        };
        int n = JOptionPane.showOptionDialog(modEditorFrame, message, message, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return n == 0;
    }
}
