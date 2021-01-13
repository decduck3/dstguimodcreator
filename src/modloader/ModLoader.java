package modloader;

import config.GlobalConfig;
import frames.ModEditor;
import logging.Logger;
import modloader.classes.Item;
import modloader.classes.components.*;
import modloader.resources.Resource;
import modloader.resources.ResourceManager;
import resources.ResourceLoader;
import savesystem.SaveSystem;
import speech.SpeechFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Field;

public class ModLoader {
    public static JFrame modEditorFrame;
    public static ModEditor modEditor;
    public static DefaultTableModel resourceModel;
    public static DefaultTableModel speechModel;

    public static String fileComponent(String fname) {
        int pos = fname.lastIndexOf(File.separator);
        if(pos > -1)
            return fname.substring(pos + 1);
        else
            return fname;
    }

    public static void LoadMod(String path){
        Logger.Log("Loading mod...");
        Logger.Log("Path: " + path);
        Mod.path = path;

        SaveSystem.Load(path);

        Debug();

        Logger.Log("Creating editor window...");
        CreateModEditorFrame();
        Logger.Log("Done!");

        Update();
    }
    public static void CreateMod(String path, String author, String name){
        Logger.Log("Creating mod...");
        Logger.Log("Path: " + path);
        Mod.path = path;

        Logger.Log("Creating editor window...");
        CreateModEditorFrame();
        Logger.Log("Done!");

        Mod.modName = name;
        Mod.modAuthor = author;
        Mod.modDescription = "Example Description";
        Mod.modVersion = "1.0";

        SaveSystem.Save(path);
        Update();
    }

    public static void Debug(){
        System.out.println("Mod Name: " + Mod.modName);
        System.out.println("Mod Author: " + Mod.modAuthor);
        System.out.println("Mod Description: " + Mod.modDescription);
        System.out.println("Mod Version: " + Mod.modVersion);
    }

    public static <T> void AddClassToTree(DefaultMutableTreeNode root, Class toAdd, T values){
        Field[] fields = toAdd.getFields();
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(toAdd.getSimpleName());

        for(Field f:fields){
            if(values != null){
                try {
                    DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(f.getName() + ": " + f.get(values));
                    node.add(tempNode);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else{
                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(f.getName());
                node.add(tempNode);
            }
        }

        root.add(node);
    }

    public static String getExpansionState(JTree tree){

        StringBuilder  sb = new StringBuilder();

        for(int i =0 ; i < tree.getRowCount(); i++){
            TreePath tp = tree.getPathForRow(i);
            if(tree.isExpanded(i)){
                sb.append(tp.toString());
                sb.append(",");
            }
        }

        return sb.toString();

    }

    public static void setExpansionState(String s, JTree tree){

        for(int i = 0 ; i<tree.getRowCount(); i++){
            TreePath tp = tree.getPathForRow(i);
            if(s.contains(tp.toString() )){
                tree.expandRow(i);
            }
        }
    }

    public static void Update(){
        ResourceManager.GenerateResourceLists();
        int selectedModItem = modEditor.getModItemSelect().getSelectedIndex();
        int selectedModIcon = Mod.modIcon;
        int selectedModTexture = 0;
        try{
            selectedModTexture = ResourceManager.resources.indexOf(Mod.items.get(selectedModItem).itemTexture);
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

        resourceModel.setRowCount(0);

        modEditor.getModIconTextureSelect().removeAllItems();
        modEditor.getModItemTextureSelect().removeAllItems();
        for(Resource r: ResourceManager.resources){
            if(r.isTexture){
                resourceModel.addRow(new Object[] { ModLoader.fileComponent(r.texture.texPath), "Texture", r.texture.texPath + ";" + r.texture.xmlPath, r.filePath});
            }else if(r.isSpeech){
                resourceModel.addRow(new Object[] { r.speechFile.resourceName, "Speech", r.speechFile.filePath, r.speechFile.speechType.name()});
            }else if(r.isAnim){
                resourceModel.addRow(new Object[] { fileComponent(r.animFilePath), "Animation", r.animFilePath, ""});
            }
        }

        for(Resource r: ResourceManager.inventoryimages){
            modEditor.getModItemTextureSelect().addItem(fileComponent(r.texture.texPath));
        }
        for(Resource r: ResourceManager.modicons){
            modEditor.getModIconTextureSelect().addItem(fileComponent(r.texture.texPath));
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
            Item item = null;
            try{
                item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
            }catch (IndexOutOfBoundsException e){
                return;
            }

            if(modEditor.getModItemSelect().getSelectedIndex() < Mod.items.size() + 1){

            }else{
                throw new Exception("Item not selected");
            }

            DefaultTreeModel modelNotAdded = (DefaultTreeModel) modEditor.getModItemComponentNotAdded().getModel();
            DefaultTreeModel modelAdded = (DefaultTreeModel) modEditor.getModItemComponetsAdded().getModel();
            DefaultMutableTreeNode rootNotAdded = (DefaultMutableTreeNode) modelNotAdded.getRoot();
            DefaultMutableTreeNode rootAdded = (DefaultMutableTreeNode) modelAdded.getRoot();

            String expandedAdded = getExpansionState(modEditor.getModItemComponetsAdded());
            String expandedNotAdded = getExpansionState(modEditor.getModItemComponentNotAdded());

            rootNotAdded.removeAllChildren();
            rootAdded.removeAllChildren();

            if(item.armorBool){
                AddClassToTree(rootAdded, Armor.class, item.armor);
            }else{
                AddClassToTree(rootNotAdded, Armor.class, null);
            }

            if(item.edibleBool){
                AddClassToTree(rootAdded, Edible.class, item.edible);
            }else{
                AddClassToTree(rootNotAdded, Edible.class, null);
            }

            if(item.axeBool){
                AddClassToTree(rootAdded, Axe.class, item.axe);
            }else{
                AddClassToTree(rootNotAdded, Axe.class, null);
            }

            if(item.dappernessBool){
                AddClassToTree(rootAdded, Dapperness.class, item.dapperness);
            }else{
                AddClassToTree(rootNotAdded, Dapperness.class, null);
            }

            if(item.durabilityBool){
                AddClassToTree(rootAdded, Durability.class, item.durability);
            }else{
                AddClassToTree(rootNotAdded, Durability.class, null);
            }

            if(item.equipableBool){
                AddClassToTree(rootAdded, Equippable.class, item.equippable);
            }else{
                AddClassToTree(rootNotAdded, Equippable.class, null);
            }

            modelNotAdded.reload();
            modelAdded.reload();

            setExpansionState(expandedAdded, modEditor.getModItemComponetsAdded());
            setExpansionState(expandedNotAdded, modEditor.getModItemComponentNotAdded());

            modEditor.getModItemNameTextField().setText(item.itemName);
            modEditor.getModItemIdTextField().setText(item.itemId);
            modEditor.getModItemTextureSelect().setSelectedIndex(item.itemTexture);
        } catch (Exception e) {
            Logger.Error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        modEditorFrame.validate();
    }

    public static void ReloadSpeech(){
        for(int i = 0; i < speechModel.getRowCount(); i++){
            speechModel.removeRow(i);
        }

        for(Resource r: ResourceManager.resources){
            if(r.isSpeech){
                ResourceManager.ReloadResource(r);
                if(r.speechFile.speechType == SpeechFile.SpeechType.Item)
                {
                    speechModel.addRow(new Object[]{r.speechFile.resourceName, r.speechFile.filePath, r.speechFile.speechType, r.speechFile.itemSpeech.speech.size()});
                }else if(r.speechFile.speechType == SpeechFile.SpeechType.Character){
                    speechModel.addRow(new Object[]{r.speechFile.resourceName, r.speechFile.filePath, r.speechFile.speechType, r.speechFile.characterSpeech.speech.size()});
                }

            }
        }
    }

    public static void SaveItem(){
        try {
            Item item = null;
            try{
                item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
            }catch (Exception e){
                return;
            }

            item.itemName = modEditor.getModItemNameTextField().getText();
            item.itemId = modEditor.getModItemIdTextField().getText();
            item.itemTexture = modEditor.getModItemTextureSelect().getSelectedIndex();
        }catch(java.lang.IndexOutOfBoundsException e){
            ShowWarning("There was a problem with saving an item. Please make sure all fields are filled then try again");
            Logger.Error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void SaveModConfig(){
        try{
            Mod.modName = modEditor.getModNameTextField().getText();
            Mod.modAuthor = modEditor.getModAuthorTextField().getText();
            Mod.modDescription = modEditor.getModDescriptTextArea().getText();
            Mod.modVersion = modEditor.getModVersionTextField().getText();
            Mod.modIcon = modEditor.getModIconTextureSelect().getSelectedIndex();
        }catch (java.lang.IndexOutOfBoundsException e){
            ShowWarning("There was a problem saving the mod config. Please make sure all fields are filled then try again");
            Logger.Error(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    public static void SaveAll(){
        try{
            SaveItem();
            SaveModConfig();
            SaveSystem.Save(Mod.path);
        }catch(Exception e){
            JOptionPane.showMessageDialog(modEditorFrame, e.getLocalizedMessage(), "Error while saving", JOptionPane.ERROR_MESSAGE);
            Logger.Error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        Logger.Log("Saved All");
    }

    public static void CreateModEditorFrame(){
        modEditorFrame = new JFrame("Mod Editor");
        ImageIcon img = new ImageIcon(ResourceLoader.class.getResource("dstguimodcreatorlogo.png"));
        modEditorFrame.setIconImage(img.getImage());
        modEditor = new ModEditor();

        Logger.Log("Created JFrame and ModEditor objects");

        modEditorFrame.setContentPane(modEditor.getModEditorPanel());
        modEditorFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        modEditorFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if(GlobalConfig.askSaveOnLeave && getBool("Save?")){
                    SaveAll();
                }
                modEditorFrame.dispose();
                System.exit(0);
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

        Logger.Log("Added window listener");

        speechModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        resourceModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        modEditor.getResourcesTable().setModel(resourceModel);
        modEditor.getModSpeechTable().setModel(speechModel);

        ((DefaultTreeModel)modEditor.getModItemComponetsAdded().getModel()).setRoot(new DefaultMutableTreeNode("Added"));
        ((DefaultTreeModel)modEditor.getModItemComponentNotAdded().getModel()).setRoot(new DefaultMutableTreeNode("Not Added"));

        JTree addedTree = modEditor.getModItemComponetsAdded();
        JTree notAddedTree = modEditor.getModItemComponentNotAdded();
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ModLoader.SaveItem();
                int selRow = addedTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = addedTree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 1) {

                    }
                    else if(e.getClickCount() == 2) {
                        try {
                            if (Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Axe.class)) {
                                Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).axeBool = false;
                                Update();
                                return;
                            }
                            if (Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Edible.class)) {
                                Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).edibleBool = false;
                                Update();
                                return;
                            }
                            if (Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Dapperness.class)) {
                                Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).dappernessBool = false;
                                Update();
                                return;
                            }
                            if (Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Durability.class)) {
                                Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).durabilityBool = false;
                                Update();
                                return;
                            }
                            if (Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Equippable.class)) {
                                Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).equipableBool = false;
                                Update();
                                return;
                            }
                            if (Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Armor.class)) {
                                Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).armorBool = false;
                                Update();
                                return;
                            }
                        }catch(NullPointerException n){

                        }

                        if (Item.classMap.get(selPath.getPath()[1].toString()).equals(Axe.class)) {
                            Item item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                            String value = selPath.getLastPathComponent().toString();
                            if(value.startsWith("efficiency")){
                                item.axe.efficiency = getFloat("Axe efficiency");
                            }
                            Update();
                            return;
                        }
                        if (Item.classMap.get(selPath.getPath()[1].toString()).equals(Edible.class)) {
                            Item item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                            String value = selPath.getLastPathComponent().toString();
                            if(value.startsWith("health")){
                                item.edible.health = getFloat("Food health");
                            }
                            if(value.startsWith("sanity")){
                                item.edible.sanity = getFloat("Food sanity");
                            }
                            if(value.startsWith("hunger")){
                                item.edible.hunger = getFloat("Food hunger");
                            }
                            Update();
                            return;
                        }
                        if (Item.classMap.get(selPath.getPath()[1].toString()).equals(Dapperness.class)) {
                            Item item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                            String value = selPath.getLastPathComponent().toString();
                            if(value.startsWith("rate")){
                                item.dapperness.rate = getFloat("Dapperness rate");
                            }
                            Update();
                            return;
                        }
                        if (Item.classMap.get(selPath.getPath()[1].toString()).equals(Durability.class)) {
                            Item item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                            String value = selPath.getLastPathComponent().toString();
                            if(value.startsWith("durability")){
                                item.durability.durability = getFloat("Durability");
                            }
                            Update();
                            return;
                        }
                        if (Item.classMap.get(selPath.getPath()[1].toString()).equals(Equippable.class)) {
                            Item item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                            String value = selPath.getLastPathComponent().toString();
                            if(value.startsWith("place")){
                                item.equippable.place = Equippable.Place.values()[getOption("Place", new Object[]{ "Hat", "Chest", "Hand" })];
                            }
                            Update();
                            return;
                        }
                        if (Item.classMap.get(selPath.getPath()[1].toString()).equals(Armor.class)) {
                            Item item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                            String value = selPath.getLastPathComponent().toString();
                            if(value.startsWith("resistance")){
                                item.armor.resistance = getFloat("Resistance");
                            }
                            if(value.startsWith("maxCondition")){
                                item.armor.maxCondition = getFloat("Max Condition");
                            }
                            Update();
                            return;
                        }
                    }
                }
            }
        };
        addedTree.addMouseListener(ml);
        MouseListener ml2 = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ModLoader.SaveItem();
                int selRow = notAddedTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = notAddedTree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 1) {

                    }
                    else if(e.getClickCount() == 2) {
                        if(Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Axe.class)){
                            Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).axeBool = true;
                            Update();
                        }
                        if(Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Edible.class)){
                            Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).edibleBool = true;
                            Update();
                        }
                        if(Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Dapperness.class)){
                            Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).dappernessBool = true;
                            Update();
                        }
                        if(Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Durability.class)){
                            Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).durabilityBool = true;
                            Update();
                        }
                        if(Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Equippable.class)){
                            Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).equipableBool = true;
                            Update();
                        }
                        if(Item.classMap.get(selPath.getLastPathComponent().toString()).equals(Armor.class)){
                            Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).armorBool = true;
                            Update();
                        }
                    }
                }
            }
        };
        notAddedTree.addMouseListener(ml2);

        Logger.Log("Created table models with appropriate settings");

        resourceModel.addColumn("Name");
        resourceModel.addColumn("Type");
        resourceModel.addColumn("Path");
        resourceModel.addColumn("Other");

        speechModel.addColumn("Name");
        speechModel.addColumn("Location");
        speechModel.addColumn("Type");
        speechModel.addColumn("Entries");

        Logger.Log("Added columns to resourceModel and speechModel");

        //Go see file for definitions
        ModLoaderActions.SetupListeners();

        Logger.Log("Completed ModLoaderActions.SetupListeners()");

        modEditorFrame.pack();
        modEditorFrame.setLocationRelativeTo(null);
        modEditorFrame.setVisible(true);
        Logger.Log("Finished Init of modEditorFrame");
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

    public static void ShowWarning(String message){
        JOptionPane.showMessageDialog(modEditorFrame, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
