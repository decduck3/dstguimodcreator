package modloader;

import export.Exporter;
import frames.SpeechConfig;
import logging.Logger;
import items.Item;
import modloader.resources.ResourceManager;
import recipes.RecipeLoader;
import resources.ResourceLoader;
import speech.SpeechFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static constants.Constants.FILE_LOCATION;

public class ModLoaderActions extends ModLoader{
    public static void SetupListeners(){
        modEditor.getModItemCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mod.items.add(new Item());
                Update();
                ModLoader.modEditor.getModItemSelect().setSelectedIndex(Mod.items.size()-1);
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
                switch(ModLoader.getOption("Type of resource", new Object[]{ "Texture", "Speech", "Animation" })){
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
                        ImageIcon img = new ImageIcon(ResourceLoader.class.getResource("dstguimodcreatorlogo.png"));
                        speechConfigFrame.setIconImage(img.getImage());
                        SpeechConfig speech = new SpeechConfig();
                        speechConfigFrame.setContentPane(speech.getSpeechConfigPanel());
                        speechConfigFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                        speech.getSpeechCreate().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ResourceManager.LoadResource(SpeechFile.SpeechType.values()[speech.getSpeechFileType().getSelectedIndex()], FILE_LOCATION + "/speech/" + speech.getSpeechNameTextField().getText() + ".xml");
                                speechConfigFrame.dispose();
                                Update();
                                Logger.Log("Created speech resource");
                            }
                        });

                        speechConfigFrame.pack();
                        speechConfigFrame.setLocationRelativeTo(null);
                        speechConfigFrame.setVisible(true);
                        Logger.Log("Finished speechConfigFrame setup");
                        break;
                    case 2:
                        Logger.Log("Importing animation...");
                        JFileChooser animChooser = new JFileChooser();
                        animChooser.setAcceptAllFileFilterUsed(false);
                        FileNameExtensionFilter anim = new FileNameExtensionFilter("Animation File", "zip");

                        animChooser.addChoosableFileFilter(anim);
                        JOptionPane.showMessageDialog(modEditorFrame, animChooser, "Open Animation file", JOptionPane.QUESTION_MESSAGE);
                        File animationFile = animChooser.getSelectedFile();
                        ResourceManager.LoadResource(animationFile.getAbsolutePath());
                        Logger.Log("Finished importing animation");
                        break;
                }

                Update();
            }
        });

        modEditor.getResourcesRemove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResourceManager.resources.remove(modEditor.getResourcesTable().getSelectedRow());
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

        modEditor.getOpenFolder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File(FILE_LOCATION + "/speech/"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        modEditor.getGithubButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/decduck3/dstguimodcreator"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });

        modEditor.getWikiButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/decduck3/dstguimodcreator/wiki"));
                } catch (IOException | URISyntaxException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        modEditor.getModRecipesButtonCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecipeLoader.CreateNewRecipe();
            }
        });

        modEditor.getModRecipesButtonDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecipeLoader.DeleteRecipe();
            }
        });
    }
}
