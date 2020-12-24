package modloader;

import frames.ModEditor;
import modloader.classes.Item;
import modloader.classes.Texture;
import modloader.resources.Resource;
import modloader.resources.ResourceLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

public class ModLoader {
    public static JFrame modEditorFrame;
    public static ModEditor modEditor;
    public static DefaultTableModel textureModel;

    public static Mod currentMod;

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

        for(int i =0; i < ResourceLoader.resources.size(); i++){
            textureModel.addRow(new Object[] { new File(ResourceLoader.resources.get(i).texture.texPath).getName(), ResourceLoader.resources.get(i).texture.texPath });
        }

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
    }

    public static void CreateModEditorFrame(){
        modEditorFrame = new JFrame("Mod Editor");
        modEditor = new ModEditor();

        modEditorFrame.setContentPane(modEditor.getModEditorPanel());
        modEditorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textureModel = (DefaultTableModel) modEditor.getModItemTextureSelect().getModel();

        textureModel.addColumn("Texture");
        textureModel.addColumn("Path");

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
                    Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).itemTexture = modEditor.getModItemTextureSelect().getModel().getValueAt(row, col);
                }
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

        //Axe
        modEditor.getAxe().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getAxe().isSelected()){
                    item.axe.efficiency = (float)getFloat("Axe Efficiency"); // Default value for axe
                }
            }
        });

        //Weapon
        modEditor.getWeapon().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getWeapon().isSelected()){
                    item.weapon.damage = (float) getFloat("Weapon Damage:"); // Default value for axe
                    item.weapon.electric = getBool("Weapon electric:");
                    item.weapon.ranged = getBool("Weapon ranged:");
                    if(item.weapon.ranged){
                        item.weapon.ammo = Mod.items.get(getOption("Weapon Ammo:", Mod.items.toArray(new Object[0])));
                    }
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
        return n == 1;
    }
}
