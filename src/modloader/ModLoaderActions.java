package modloader;

import export.Exporter;
import frames.SpeechConfig;
import logging.Logger;
import modloader.classes.Item;
import modloader.classes.components.Equipable;
import modloader.resources.Resource;
import modloader.resources.ResourceManager;
import speech.SpeechFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ModLoaderActions extends ModLoader{
    public static void SetupListeners(){
        modEditor.getModItemCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mod.items.add(new Item());
                Update();
                Logger.Log("Created new item");
            }
        });

        modEditor.getModItemDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mod.items.remove(Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()));
                Update();
                Logger.Log("Removed item");
            }
        });

        modEditor.getSaveAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveAll();
            }
        });

        modEditor.getResourcesAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(ModLoader.getOption("Type of resource", new Object[]{ "Texture", "Speech" })){
                    case 0:
                        Logger.Log("Importing texture....");
                        JFileChooser chooser = new JFileChooser();
                        chooser.setAcceptAllFileFilterUsed(false);
                        FileNameExtensionFilter tex = new FileNameExtensionFilter("TEX File", "tex");
                        FileNameExtensionFilter xml = new FileNameExtensionFilter("XML File", "xml");

                        chooser.addChoosableFileFilter(tex);
                        JOptionPane.showMessageDialog(modEditorFrame, chooser, "Open TEX file", JOptionPane.QUESTION_MESSAGE);
                        File texFile = chooser.getSelectedFile();

                        chooser.removeChoosableFileFilter(tex);
                        chooser.addChoosableFileFilter(xml);
                        JOptionPane.showMessageDialog(modEditorFrame, chooser, "Open XML file", JOptionPane.QUESTION_MESSAGE);
                        File xmlFile = chooser.getSelectedFile();

                        ResourceManager.LoadResource(texFile.getAbsolutePath(), xmlFile.getAbsolutePath(), ResourceManager.TextureLocation.values()[(ModLoader.getOption("Texture location", new Object[] {"Inventory Image", "Mod Icon", "Portrait", "Map Icon"}))]);
                        Logger.Log("Created resource with settings:\n" +
                                "   Tex Path: " + texFile.getAbsolutePath() + "\n" +
                                "   Xml Path: " + xmlFile.getAbsolutePath() + "\n");
                        break;
                    case 1:
                        Logger.Log("Creating speech file...");
                        JFrame speechConfigFrame = new JFrame("Create New Speech File");
                        ImageIcon img = new ImageIcon("src/resources/dstguimodcreatorlogo.png");
                        speechConfigFrame.setIconImage(img.getImage());
                        SpeechConfig speech = new SpeechConfig();
                        speechConfigFrame.setContentPane(speech.getSpeechConfigPanel());
                        speechConfigFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                        speech.getSpeechCreate().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ResourceManager.LoadResource(SpeechFile.SpeechType.values()[speech.getSpeechFileType().getSelectedIndex()], System.getProperty("user.dir") + "/speech/" + speech.getSpeechNameTextField().getText() + ".speech");
                                speechConfigFrame.dispose();
                                Update();
                                Logger.Log("Created speech resource");
                            }
                        });

                        speechConfigFrame.pack();
                        speechConfigFrame.setVisible(true);
                        Logger.Log("Finished speechConfigFrame setup");
                        break;
                }

                Update();
            }
        });

        modEditor.getResourcesRemove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResourceManager.RemoveResource(
                        (String)modEditor.getResourcesTable().getModel().getValueAt(modEditor.getResourcesTable().getSelectedRow(), 1),
                        (String)modEditor.getResourcesTable().getModel().getValueAt(modEditor.getResourcesTable().getSelectedRow(), 2));
                Update();
                Logger.Log("Removed resource");
            }
        });

        modEditor.getModItemSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveItem();
                Update();
                Logger.Log("Saved Item");
            }
        });

        modEditor.getModConfigSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveModConfig();
                Update();
                Logger.Log("Saved mod config");
            }
        });

        modEditor.getModItemSelect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        });

        modEditor.getAxe().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getAxe().isSelected()){
                    item.axe.efficiency = getFloat("Axe efficiency");

                    Update();
                    Logger.Log("Added axe component");
                }
            }
        });

        modEditor.getArmor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getArmor().isSelected()){
                    item.armor.resistance = getFloat("Armor Resistance");
                    item.armor.maxCondition = getFloat("Armor Max Condition");

                    Update();
                    Logger.Log("Added armor component");
                }
            }
        });

        modEditor.getChest().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getChest().isSelected()){
                    if(modEditor.getEquipable().isSelected()){
                        item.equipable.place = Equipable.Place.Chest;
                        item.chest.watertightness = getFloat("Water tightness");
                    }else{
                        JOptionPane.showMessageDialog(modEditorFrame, "Enable equip-able first", "Enable equip-able first", JOptionPane.WARNING_MESSAGE);
                        modEditor.getChest().setSelected(false);
                    }
                    Update();
                    Logger.Log("Added chest component");
                }
            }
        });

        modEditor.getDapperness().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getDapperness().isSelected()){
                    item.dapperness.rate = getFloat("Dapperness rate");

                    Update();
                    Logger.Log("Added dapperness component");
                }
            }
        });

        modEditor.getDurability().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getDurability().isSelected()){
                    if(modEditor.getAxe().isSelected()){
                        item.durability.durability = getFloat("Durability");
                    }else{
                        JOptionPane.showMessageDialog(modEditorFrame, "Enable axe first", "Enable axe first", JOptionPane.WARNING_MESSAGE);
                    }
                    Update();
                    Logger.Log("Added durability component");
                }
            }
        });

        modEditor.getEdible().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getEdible().isSelected()){
                    item.edible.health = getFloat("Health");
                    item.edible.hunger = getFloat("Hunger");
                    item.edible.sanity = getFloat("Sanity");

                    Update();
                    Logger.Log("Added edible component");
                }
            }
        });

        //Don't need one for equipable

        modEditor.getHand().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getChest().isSelected()){
                    if(modEditor.getEquipable().isSelected()){
                        item.equipable.place = Equipable.Place.Hand;
                        item.hand.watertightness = getFloat("Water tightness");
                    }else{
                        JOptionPane.showMessageDialog(modEditorFrame, "Enable equip-able first", "Enable equip-able first", JOptionPane.WARNING_MESSAGE);
                        modEditor.getChest().setSelected(false);
                    }
                    Update();
                    Logger.Log("Added hand component");
                }
            }
        });

        modEditor.getHat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getChest().isSelected()){
                    if(modEditor.getEquipable().isSelected()){
                        item.equipable.place = Equipable.Place.Hat;
                        item.hat.watertightness = getFloat("Water tightness");
                    }else{
                        JOptionPane.showMessageDialog(modEditorFrame, "Enable equip-able first", "Enable equip-able first", JOptionPane.WARNING_MESSAGE);
                        modEditor.getChest().setSelected(false);
                    }
                    Update();
                    Logger.Log("Added hat component");
                }
            }
        });

        modEditor.getModExport().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exporter.Export();
                Logger.Log("Exported");
            }
        });

        modEditor.getModSpeechReloadSpeech().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReloadSpeech();
                Update();
                Logger.Log("Reloaded speech resources");
            }
        });
    }
}
