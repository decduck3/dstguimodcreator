package frames;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StartupForm {
    private JPanel startupPanel;
    private JLabel image;

    public JPanel getStartupPanel() {
        return startupPanel;
    }

    public JLabel getImage() {
        return image;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        startupPanel = new JPanel();
        startupPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(20, 20, 20, 20), -1, -1));
        startupPanel.setBackground(new Color(-1));
        startupPanel.setForeground(new Color(-1));
        startupPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        image = new JLabel();
        image.setIcon(new ImageIcon(getClass().getResource("/resources/Github.png")));
        image.setText("");
        startupPanel.add(image, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return startupPanel;
    }

}