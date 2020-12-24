package modloader;

import frames.ModEditor;
import modloader.classes.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModLoader {
    public static JFrame modEditorFrame;
    public static ModEditor modEditor;

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



        modEditorFrame.pack();
    }

    public static void CreateModEditorFrame(){
        modEditorFrame = new JFrame("Mod Editor");
        modEditor = new ModEditor();

        modEditorFrame.setContentPane(modEditor.getModEditorPanel());
        modEditorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                    //Need to implement resources
                    //Mod.items.get(modEditor.getModItemSelect().getSelectedIndex()).itemTexture = modEditor.getModItemTextureSelect().getModel().getValueAt(row, col);
                }
            }
        });

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
            }
        });

        modEditor.getAxe().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getAxe().isSelected()){
                    item.axe.efficiency = getFloat("Axe Efficiency", 1); // Default value for axe
                }
            }
        });
        modEditor.getWeapon().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getWeapon().isSelected()){
                    item.weapon.damage = getFloat("Weapon Damage:", 17); // Default value for axe

                }
            }
        });

        modEditorFrame.pack();
        modEditorFrame.setVisible(true);
    }

    public static int getInt(String message, int defaultValue){
        String response = JOptionPane.showInputDialog(message);
        if(response!=null){
            return Integer.parseInt(response);
        }else{
            return defaultValue;
        }
    }

    public static float getFloat(String message, float defaultValue){
        String response = JOptionPane.showInputDialog(message);
        if(response!=null){
            return Float.parseFloat(response);
        }else{
            return defaultValue;
        }

    }

    public static boolean getBool(String message, boolean defaultValue){
        boolean response = JOptionPane.showOptionDialog(modEditorFrame, message, JOptionPane.YES_NO_OPTION)
    }
}
