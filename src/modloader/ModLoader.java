package modloader;

import frames.ModEditor;
import modloader.classes.Item;
import modloader.classes.Texture;
import modloader.resources.Resource;
import modloader.resources.ResourceLoader;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.html.Option;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

public class ModLoader {
    public static JFrame modEditorFrame;
    public static ModEditor modEditor;
    public static DefaultTableModel textureModel;
    public static DefaultTableModel resourceModel;

    public static void LoadMod(String path){
        CreateModEditorFrame();

        Mod.LoadFromFile(path);
    }
    public static void CreateMod(String path, String author, String name){
        CreateModEditorFrame();

        Mod.modName = name;
        Mod.modAuthor = author;
    }

    public static void Update(){
        modEditor.getModItemSelect().removeAllItems();
        for(int i = 0; i < Mod.items.size(); i++){
            modEditor.getModItemSelect().addItem(Mod.items.get(i).itemName);
        }

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
            e.printStackTrace();
        }
    }

    public static void CreateModEditorFrame(){
        modEditorFrame = new JFrame("Mod Editor");
        modEditor = new ModEditor();

        modEditorFrame.setContentPane(modEditor.getModEditorPanel());
        modEditorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textureModel = (DefaultTableModel) modEditor.getModItemTextureSelect().getModel();
        resourceModel = (DefaultTableModel) modEditor.getResourcesTable().getModel();

        textureModel.addColumn("Texture");
        textureModel.addColumn("Path");

        resourceModel.addColumn("Texture");
        resourceModel.addColumn("Path");

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

        modEditor.getModItemTextureSelect().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = modEditor.getModItemTextureSelect().rowAtPoint(evt.getPoint());
                int col = modEditor.getModItemTextureSelect().columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                    //item.itemTexture = ResourceLoader.GetResource(modEditor.getModItemTextureSelect().getModel().getValueAt(row, col).toString()).texture;

                    Update();
                }
            }
        });

        modEditor.getResourcesAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                ResourceLoader.LoadResource(texFile.getAbsolutePath(), xmlFile.getAbsolutePath());

                Update();
            }
        });

        //Save Item
        modEditor.getModItemSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                Update();
            }
        });

        modEditor.getModItemSelect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        });

        //Axe - For development (not final)
        modEditor.getAxe().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getAxe().isSelected()){
                    //item.axe.efficiency = (float)getFloat("Axe Efficiency"); // Default value for axe
                    System.out.println(getBool("Test bool:"));
                    System.out.println(getInt("Test int:"));
                    System.out.println(getFloat("Test float:"));
                    System.out.println(getOption("Test option", new Object[] { "Option 1", "Option 2"}));

                    Update();
                }
            }
        });

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
