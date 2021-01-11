
import frames.NewModConfig;
import frames.ProjectSelect;
import logging.Logger;
import modloader.ModLoader;
import savesystem.SaveObject;
import savesystem.SaveSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Master {
    public static ProjectSelect projectSelect;
    public static JFrame projectSelectFrame;

    public static int currentlySelectedRow = -1;

    public static void main(String[] args){
        Logger.Log("Starting up...");
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            Logger.Log("Changing look and feel");
        } catch (ClassNotFoundException e) {
            Logger.Error(e.getMessage());
        } catch (InstantiationException e) {
            Logger.Error(e.getMessage());
        } catch (IllegalAccessException e) {
            Logger.Error(e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            Logger.Error(e.getMessage());
        }
        Logger.Log("Creating frame...");
        projectSelect = new ProjectSelect();

        projectSelectFrame = new JFrame("Project Select");
        ImageIcon img = new ImageIcon("src/resources/dstguimodcreatorlogo.png");
        projectSelectFrame.setIconImage(img.getImage());
        projectSelectFrame.setContentPane(projectSelect.getProjectSelectPanel());
        projectSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Logger.Log("Done!");

        Logger.Log("Setting up mods table and reading mods...");
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        projectSelect.getProjectsListTable().setModel(model);
        model.addColumn("Project Name:");
        model.addColumn("Project Author:");
        model.addColumn("Project Path:");
        readMods();
        Logger.Log("Done!");

        projectSelect.getNewMod().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateNewMod();
            }
        });

        projectSelect.getLoadMod().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadCurrentMod();
            }
        });

        projectSelect.getProjectsListTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = projectSelect.getProjectsListTable().rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    currentlySelectedRow = row;
                }
            }
        });

        projectSelectFrame.pack();
        projectSelectFrame.setVisible(true);
    }

    public static void CreateNewMod(){
        JFrame newModConfigFrame = new JFrame("Mod Creation");
        NewModConfig newModConfig = new NewModConfig();
        newModConfigFrame.setContentPane(newModConfig.getNewModConfigPanel());
        newModConfigFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        newModConfig.getCreateMod().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = newModConfig.getModNameTextField().getText();
                String author = newModConfig.getModAuthorTextField().getText();
                projectSelectFrame.setVisible(false);
                newModConfigFrame.setVisible(false);
                ModLoader.CreateMod(System.getProperty("user.dir") + "/mods/" + name.replace(" ", "") + ".demv", author, name);
            }
        });

        newModConfigFrame.pack();
        newModConfigFrame.setVisible(true);
    }

    public static void LoadCurrentMod(){
        if(currentlySelectedRow == -1){
            JOptionPane.showMessageDialog(projectSelectFrame,
                    "No project is selected",
                    "Cannot Load Project",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            projectSelectFrame.setVisible(false);
            ModLoader.LoadMod(System.getProperty("user.dir") + "/mods/" + projectSelect.getProjectsListTable().getModel().getValueAt(currentlySelectedRow, 2));
        }
    }

    public static void readMods(){
        try{
            DefaultTableModel model = (DefaultTableModel) projectSelect.getProjectsListTable().getModel();
            String[] mods = getAllDirectories(System.getProperty("user.dir") + "/mods");
            for(int i = 0; i < mods.length; i++){
                SaveObject saveObject = SaveSystem.TempLoad(System.getProperty("user.dir") + "/mods/" + mods[i]);
                model.addRow(new Object[]{saveObject.modName, saveObject.modAuthor, mods[i]});
            }
        }catch (Exception e){
            Logger.Error(e.getMessage());
        }
    }

    public static String[] getAllDirectories(String path){
        File dir = new File(path);
        File [] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".demv");
            }
        });
        List<String> _directories = new ArrayList<String>();

        for(int i = 0; i < files.length; i++){
            _directories.add(files[i].getName());
        }

        return _directories.toArray(new String[0]);
    }
}
