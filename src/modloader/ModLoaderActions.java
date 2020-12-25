package modloader;

import export.Exporter;
import frames.ModEditor;
import modloader.classes.Item;
import modloader.resources.ResourceLoader;
import savesystem.SaveSystem;

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

        modEditor.getSaveAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveItem();
                SaveModConfig();
                SaveSystem.Save(Mod.path);
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

        //Axe - For development (not final)
        modEditor.getAxe().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var item = Mod.items.get(modEditor.getModItemSelect().getSelectedIndex());
                if(modEditor.getAxe().isSelected()){
                    System.out.println(getBool("Test bool:"));
                    System.out.println(getInt("Test int:"));
                    System.out.println(getFloat("Test float:"));
                    System.out.println(getOption("Test option", new Object[] { "Option 1", "Option 2"}));

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
    }
}
