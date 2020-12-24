import frames.NewModConfig;
import frames.ProjectSelect;
import modloader.ModLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class Master {
    public static ProjectSelect projectSelect;
    public static JFrame projectSelectFrame;

    public static Object currentlySelectedTable;

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        projectSelect = new ProjectSelect();

        projectSelectFrame = new JFrame("ProjectSelect");
        projectSelectFrame.setContentPane(projectSelect.getProjectSelectPanel());
        projectSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DefaultTableModel model = (DefaultTableModel) projectSelect.getProjectsListTable().getModel();
        model.addColumn("Project Name:");
        model.addColumn("Project Date:");
        readMods();

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
                int col = projectSelect.getProjectsListTable().columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    currentlySelectedTable = projectSelect.getProjectsListTable().getModel().getValueAt(row, col);
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
                ModLoader.CreateMod(System.getProperty("user.dir") + "/mods" + name, author, name);
            }
        });

        newModConfigFrame.pack();
        newModConfigFrame.setVisible(true);
    }

    public static void LoadCurrentMod(){
        if(currentlySelectedTable == null){
            JOptionPane.showMessageDialog(projectSelectFrame,
                    "No project is selected",
                    "Cannot Load Project",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            projectSelectFrame.setVisible(false);
            ModLoader.LoadMod(System.getProperty("user.dir") + "/mods" + currentlySelectedTable);
        }
    }

    public static void readMods(){
        DefaultTableModel model = (DefaultTableModel) projectSelect.getProjectsListTable().getModel();
        String[] mods = getAllDirectories(System.getProperty("user.dir") + "/mods");
        System.out.println(System.getProperty("user.dir") + "/mods");
        System.out.println(Arrays.toString(mods));
        for(int i = 0; i < mods.length; i++){
            model.addRow(new Object[]{mods[i], "0/0/0000"});
        }
    }

    public static String[] getAllDirectories(String path){
        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }
}
