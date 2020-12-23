import frames.ProjectSelect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Master {
    public static ProjectSelect projectSelect;

    public static void main(String[] args){
        projectSelect = new ProjectSelect();

        JFrame frame = new JFrame("ProjectSelect");
        frame.setContentPane(projectSelect.getProjectSelectPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = (DefaultTableModel) projectSelect.getProjectsListTable().getModel();
        model.addColumn("Projects:");
        model.addColumn("Date:");
        model.addRow(new Object[]{"Projects:", "Date:"});

        frame.pack();
        frame.setVisible(true);
    }
}
