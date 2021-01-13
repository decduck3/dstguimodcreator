package frames;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class SpeechConfig {
    private JPanel speechConfigPanel;
    private JComboBox speechFileType;
    private JButton speechCreate;
    private JTextField speechNameTextField;
    private JLabel speechNameLabel;

    public JPanel getSpeechConfigPanel() {
        return speechConfigPanel;
    }

    public JComboBox getSpeechFileType() {
        return speechFileType;
    }

    public JButton getSpeechCreate() {
        return speechCreate;
    }

    public JTextField getSpeechNameTextField() {
        return speechNameTextField;
    }

    public JLabel getSpeechNameLabel() {
        return speechNameLabel;
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
        speechConfigPanel = new JPanel();
        speechConfigPanel.setLayout(new GridLayoutManager(3, 2, new Insets(20, 20, 20, 20), -1, -1));
        speechFileType = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Character");
        defaultComboBoxModel1.addElement("Item");
        speechFileType.setModel(defaultComboBoxModel1);
        speechConfigPanel.add(speechFileType, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        speechCreate = new JButton();
        speechCreate.setText("Create Speech");
        speechConfigPanel.add(speechCreate, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        speechNameLabel = new JLabel();
        speechNameLabel.setText("File Name:");
        speechConfigPanel.add(speechNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        speechNameTextField = new JTextField();
        speechConfigPanel.add(speechNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return speechConfigPanel;
    }

}
