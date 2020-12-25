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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModLoader {
    public static JFrame modEditorFrame;
    public static ModEditor modEditor;
    public static DefaultTableModel textureModel;
    public static DefaultTableModel resourceModel;

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
        int selected = modEditor.getModItemSelect().getSelectedIndex();

        modEditor.getModItemSelect().removeAllItems();
        for(int i = 0; i < Mod.items.size(); i++){
            modEditor.getModItemSelect().addItem(Mod.items.get(i).itemName);
        }

        if(Mod.items.size() < selected){
            selected = 0;
        }
        modEditor.getModItemSelect().setSelectedIndex(selected);

        for(int i = 0; i < textureModel.getRowCount(); i++){
            textureModel.removeRow(i);
        }

        for(int i = 0; i < resourceModel.getRowCount(); i++){
            resourceModel.removeRow(i);
        }

        for(int i =0; i < ResourceLoader.resources.size(); i++){
            textureModel.addRow(new Object[] { new File(ResourceLoader.resources.get(i).texture.texPath).getName(), ResourceLoader.resources.get(i).texture.texPath });
            resourceModel.addRow(new Object[] { new File(ResourceLoader.resources.get(i).texture.texPath).getName(), ResourceLoader.resources.get(i).texture.texPath });
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
            modEditor.getWeapon().setSelected(item.weaponBool);
            modEditor.getDurability().setSelected(item.durabilityBool);
            modEditor.getPickaxe().setSelected(item.pickaxeBool);
            modEditor.getHat().setSelected(item.hatBool);
            modEditor.getEquipable().setSelected(item.equipableBool);
            modEditor.getLight().setSelected(item.lightBool);
            modEditor.getDapperness().setSelected(item.dappernessBool);
            modEditor.getStorage().setSelected(item.storageBool);
            modEditor.getEdible().setSelected(item.edibleBool);
            modEditor.getChest().setSelected(item.chestBool);
            modEditor.getArmor().setSelected(item.armorBool);
            modEditor.getFuel().setSelected(item.fuelBool);
            modEditor.getHand().setSelected(item.handBool);

            modEditorFrame.pack();
        } catch (Exception e) {
            System.out.println("Item not selected");
        }
    }

    public static void SaveItem(){
        try {
            var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());

            item.itemName = modEditor.getModItemNameTextField().getText();
            item.itemId = modEditor.getModItemIdTextField().getText();
            //item.itemTexture = modEditor.getModItemTextureSelect().getModel() Need to implement resources
            item.axeBool = modEditor.getAxe().isSelected();
            item.weaponBool = modEditor.getWeapon().isSelected();
            item.durabilityBool = modEditor.getDurability().isSelected();
            item.pickaxeBool = modEditor.getPickaxe().isSelected();
            item.hatBool = modEditor.getHat().isSelected();
            item.equipableBool = modEditor.getEquipable().isSelected();
            item.lightBool = modEditor.getLight().isSelected();
            item.dappernessBool = modEditor.getDapperness().isSelected();
            item.storageBool = modEditor.getStorage().isSelected();
            item.edibleBool = modEditor.getEdible().isSelected();
            item.chestBool = modEditor.getChest().isSelected();
            item.armorBool = modEditor.getArmor().isSelected();
            item.fuelBool = modEditor.getFuel().isSelected();
            item.handBool = modEditor.getHand().isSelected();
        }catch(java.lang.IndexOutOfBoundsException e){
            System.out.println("Item not selected");
        }
    }

    public static void SaveModConfig(){
        Mod.modName = modEditor.getModNameTextField().getText();
        Mod.modAuthor = modEditor.getModAuthorTextField().getText();
        Mod.modDescription = modEditor.getModDescriptTextArea().getText();
        Mod.modVersion = modEditor.getModVersionTextField().getText();
    }

    public static void CreateModEditorFrame(){
        modEditorFrame = new JFrame("Mod Editor");
        modEditor = new ModEditor();

        modEditorFrame.setContentPane(modEditor.getModEditorPanel());
        modEditorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textureModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        modEditor.getModItemTextureSelect().setModel(textureModel);
        resourceModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        modEditor.getResourcesTable().setModel(resourceModel);

        textureModel.addColumn("Texture");
        textureModel.addColumn("Path");

        resourceModel.addColumn("Texture");
        resourceModel.addColumn("Path");

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
