package modloader;

import export.Exporter;
import frames.SpeechConfig;
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
            }
        });

        modEditor.getModItemDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mod.items.remove(Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()));
                Update();
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
                        break;
                    case 1:
                        JFrame speechConfigFrame = new JFrame("Create New Speech File");
                        SpeechConfig speech = new SpeechConfig();
                        speechConfigFrame.setContentPane(speech.getSpeechConfigPanel());
                        speechConfigFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                        speech.getSpeechCreate().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ResourceManager.LoadResource(SpeechFile.SpeechType.values()[speech.getSpeechFileType().getSelectedIndex()], System.getProperty("user.dir") + "/speech/" + speech.getSpeechNameTextField().getText() + ".speech");
                                speechConfigFrame.dispose();
                                Update();
                            }
                        });

                        speechConfigFrame.pack();
                        speechConfigFrame.setVisible(true);
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
            }
        });

        modEditor.getModItemSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveItem();
                Update();
            }
        });

        modEditor.getModConfigSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveModConfig();
                Update();
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
                }
            }
        });

        modEditor.getHand().addActionListener(new ActionListener() {
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
                }
            }
        });

        modEditor.getModExport().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exporter.Export();
            }
        });

        modEditor.getModSpeechReloadSpeech().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReloadSpeech();
                Update();
            }
        });
    }
}
