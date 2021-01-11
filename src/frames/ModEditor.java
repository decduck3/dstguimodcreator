package frames;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class ModEditor {
    private JPanel modEditorPanel;
    private JTabbedPane modConfig;
    private JPanel mainConfig;
    private JPanel modItems;
    private JPanel modCharacters;
    private JPanel resources;
    private JTextField modNameTextField;
    private JTextField modAuthorTextField;
    private JTextArea modDescriptTextArea;
    private JTextField modVersionTextField;
    private JLabel modNameLabel;
    private JLabel modAuthorLabel;
    private JLabel modDescriptionLabel;
    private JLabel modVersionLabel;
    private JPanel modItemConfigPanel;
    private JTable modItemList;
    private JScrollPane modItemListScrollPlane;
    private JButton modItemDelete;
    private JButton modItemCreate;
    private JLabel modItemNameLabel;
    private JTextField modItemNameTextField;
    private JComboBox modItemTextureSelect;
    private JLabel modItemTextureSelectLabel;
    private JPanel modItemCheckboxPanel;
    private JCheckBox Edible;
    private JCheckBox Fuel;
    private JCheckBox Light;
    private JCheckBox Pickaxe;
    private JCheckBox Axe;
    private JCheckBox Weapon;
    private JCheckBox Durability;
    private JCheckBox Hat;
    private JCheckBox Equipable;
    private JCheckBox Dapperness;
    private JCheckBox Storage;
    private JCheckBox Chest;
    private JCheckBox Armor;
    private JCheckBox Hand;
    private JCheckBox NotImplemented;
    private JPanel modSpeechConfig;
    private JLabel modItemIdLabel;
    private JTextField modItemIdTextField;
    private JComboBox modItemSelect;
    private JButton modItemSave;
    private JButton modConfigSave;
    private JTable resourcesTable;
    private JButton resourcesAdd;
    private JButton resourcesRemove;
    private JScrollPane resourcesScroll;
    private JButton saveAll;
    private JButton modExport;
    private JComboBox modIconTextureSelect;
    private JLabel modIconLabel;
    private JTable modSpeechTable;
    private JScrollPane modSpeechTableScrollPlane;
    private JButton modSpeechReloadSpeech;

    public JTable getModSpeechTable() {
        return modSpeechTable;
    }

    public JScrollPane getModSpeechTableScrollPlane() {
        return modSpeechTableScrollPlane;
    }

    public JButton getModSpeechReloadSpeech() {
        return modSpeechReloadSpeech;
    }

    public JPanel getModEditorPanel() {
        return modEditorPanel;
    }

    public JTabbedPane getModConfig() {
        return modConfig;
    }

    public JPanel getMainConfig() {
        return mainConfig;
    }

    public JPanel getModItems() {
        return modItems;
    }

    public JPanel getModCharacters() {
        return modCharacters;
    }

    public JPanel getResources() {
        return resources;
    }

    public JTextField getModNameTextField() {
        return modNameTextField;
    }

    public JTextField getModAuthorTextField() {
        return modAuthorTextField;
    }

    public JTextArea getModDescriptTextArea() {
        return modDescriptTextArea;
    }

    public JTextField getModVersionTextField() {
        return modVersionTextField;
    }

    public JLabel getModNameLabel() {
        return modNameLabel;
    }

    public JLabel getModAuthorLabel() {
        return modAuthorLabel;
    }

    public JLabel getModDescriptionLabel() {
        return modDescriptionLabel;
    }

    public JLabel getModVersionLabel() {
        return modVersionLabel;
    }

    public JPanel getModItemConfigPanel() {
        return modItemConfigPanel;
    }

    public JTable getModItemList() {
        return modItemList;
    }

    public JScrollPane getModItemListScrollPlane() {
        return modItemListScrollPlane;
    }

    public JButton getModItemDelete() {
        return modItemDelete;
    }

    public JButton getModItemCreate() {
        return modItemCreate;
    }

    public JLabel getModItemNameLabel() {
        return modItemNameLabel;
    }

    public JTextField getModItemNameTextField() {
        return modItemNameTextField;
    }

    public JComboBox getModItemTextureSelect() {
        return modItemTextureSelect;
    }

    public JLabel getModItemTextureSelectLabel() {
        return modItemTextureSelectLabel;
    }

    public JPanel getModItemCheckboxPanel() {
        return modItemCheckboxPanel;
    }

    public JCheckBox getEdible() {
        return Edible;
    }

    public JCheckBox getFuel() {
        return Fuel;
    }

    public JCheckBox getLight() {
        return Light;
    }

    public JCheckBox getPickaxe() {
        return Pickaxe;
    }

    public JCheckBox getAxe() {
        return Axe;
    }

    public JCheckBox getWeapon() {
        return Weapon;
    }

    public JCheckBox getDurability() {
        return Durability;
    }

    public JCheckBox getHat() {
        return Hat;
    }

    public JCheckBox getEquipable() {
        return Equipable;
    }

    public JCheckBox getDapperness() {
        return Dapperness;
    }

    public JCheckBox getStorage() {
        return Storage;
    }

    public JCheckBox getChest() {
        return Chest;
    }

    public JCheckBox getArmor() {
        return Armor;
    }

    public JCheckBox getHand() {
        return Hand;
    }

    public JCheckBox getNotImplemented() {
        return NotImplemented;
    }

    public JPanel getModSpeechConfig() {
        return modSpeechConfig;
    }

    public JLabel getModItemIdLabel() {
        return modItemIdLabel;
    }

    public JTextField getModItemIdTextField() {
        return modItemIdTextField;
    }

    public JComboBox getModItemSelect() {
        return modItemSelect;
    }

    public JButton getModItemSave() {
        return modItemSave;
    }

    public JButton getModConfigSave() {
        return modConfigSave;
    }

    public JTable getResourcesTable() {
        return resourcesTable;
    }

    public JButton getResourcesAdd() {
        return resourcesAdd;
    }

    public JButton getResourcesRemove() {
        return resourcesRemove;
    }

    public JScrollPane getResourcesScroll() {
        return resourcesScroll;
    }

    public JButton getSaveAll() {
        return saveAll;
    }

    public JButton getModExport() {
        return modExport;
    }

    public JComboBox getModIconTextureSelect() {
        return modIconTextureSelect;
    }

    public JLabel getModIconLabel() {
        return modIconLabel;
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
        modEditorPanel = new JPanel();
        modEditorPanel.setLayout(new GridBagLayout());
        modConfig = new JTabbedPane();
        modConfig.setEnabled(true);
        Font modConfigFont = this.$$$getFont$$$("Belisa plumilla manual", -1, 36, modConfig.getFont());
        if (modConfigFont != null) modConfig.setFont(modConfigFont);
        modConfig.setTabLayoutPolicy(1);
        modConfig.setTabPlacement(2);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        modEditorPanel.add(modConfig, gbc);
        mainConfig = new JPanel();
        mainConfig.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 3, new Insets(20, 20, 20, 20), -1, -1));
        modConfig.addTab("Main Config", null, mainConfig, "Change things such as mod name, author, config options");
        modNameLabel = new JLabel();
        modNameLabel.setText("Mod Name:");
        mainConfig.add(modNameLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modNameTextField = new JTextField();
        mainConfig.add(modNameTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        modAuthorLabel = new JLabel();
        modAuthorLabel.setText("Mod Author");
        mainConfig.add(modAuthorLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modAuthorTextField = new JTextField();
        mainConfig.add(modAuthorTextField, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        modDescriptionLabel = new JLabel();
        modDescriptionLabel.setText("Mod Description");
        mainConfig.add(modDescriptionLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modDescriptTextArea = new JTextArea();
        mainConfig.add(modDescriptTextArea, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        modVersionLabel = new JLabel();
        modVersionLabel.setText("Mod Version");
        mainConfig.add(modVersionLabel, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modVersionTextField = new JTextField();
        mainConfig.add(modVersionTextField, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        modConfigSave = new JButton();
        modConfigSave.setText("Save");
        mainConfig.add(modConfigSave, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modIconTextureSelect = new JComboBox();
        mainConfig.add(modIconTextureSelect, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modIconLabel = new JLabel();
        modIconLabel.setText("Mod Icon");
        mainConfig.add(modIconLabel, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modItems = new JPanel();
        modItems.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(20, 20, 20, 20), -1, -1));
        modConfig.addTab("Items", null, modItems, "Create, delete and modify your modded items");
        modItemConfigPanel = new JPanel();
        modItemConfigPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        modItems.add(modItemConfigPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1000, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        modItemConfigPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        modItemNameLabel = new JLabel();
        modItemNameLabel.setText("Item Name:");
        modItemConfigPanel.add(modItemNameLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modItemNameTextField = new JTextField();
        modItemConfigPanel.add(modItemNameTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        modItemTextureSelectLabel = new JLabel();
        modItemTextureSelectLabel.setText("Texture:");
        modItemConfigPanel.add(modItemTextureSelectLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modItemCheckboxPanel = new JPanel();
        modItemCheckboxPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        modItemConfigPanel.add(modItemCheckboxPanel, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(150, 150), new Dimension(150, 150), null, 0, false));
        Edible = new JCheckBox();
        Edible.setText("Edible");
        modItemCheckboxPanel.add(Edible, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Axe = new JCheckBox();
        Axe.setText("Axe");
        modItemCheckboxPanel.add(Axe, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Durability = new JCheckBox();
        Durability.setText("Durability");
        modItemCheckboxPanel.add(Durability, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Chest = new JCheckBox();
        Chest.setText("Chest");
        modItemCheckboxPanel.add(Chest, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Hat = new JCheckBox();
        Hat.setText("Hat");
        modItemCheckboxPanel.add(Hat, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Equipable = new JCheckBox();
        Equipable.setSelected(false);
        Equipable.setText("Equipable");
        modItemCheckboxPanel.add(Equipable, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Dapperness = new JCheckBox();
        Dapperness.setText("Dapperness");
        modItemCheckboxPanel.add(Dapperness, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Armor = new JCheckBox();
        Armor.setText("Armor");
        modItemCheckboxPanel.add(Armor, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Hand = new JCheckBox();
        Hand.setText("Hand");
        modItemCheckboxPanel.add(Hand, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        modItemConfigPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        modItemIdLabel = new JLabel();
        modItemIdLabel.setText("Item ID:");
        modItemConfigPanel.add(modItemIdLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modItemIdTextField = new JTextField();
        modItemConfigPanel.add(modItemIdTextField, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        modItemTextureSelect = new JComboBox();
        modItemConfigPanel.add(modItemTextureSelect, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modItemCreate = new JButton();
        modItemCreate.setText("New Item");
        modItems.add(modItemCreate, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modItemDelete = new JButton();
        modItemDelete.setText("Delete Item");
        modItems.add(modItemDelete, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modItemSelect = new JComboBox();
        modItems.add(modItemSelect, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modItemSave = new JButton();
        modItemSave.setText("Save");
        modItems.add(modItemSave, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modCharacters = new JPanel();
        modCharacters.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(20, 20, 20, 20), -1, -1));
        modConfig.addTab("Characters", null, modCharacters, "Create, delete and modify your modded characters");
        resources = new JPanel();
        resources.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(20, 20, 20, 20), -1, -1));
        modConfig.addTab("Resources", null, resources, "Import assets to use in your mod");
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        resources.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        resourcesAdd = new JButton();
        resourcesAdd.setText("Add Resource");
        resources.add(resourcesAdd, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resourcesRemove = new JButton();
        resourcesRemove.setText("Remove Resource");
        resources.add(resourcesRemove, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resourcesScroll = new JScrollPane();
        resources.add(resourcesScroll, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resourcesTable = new JTable();
        resourcesScroll.setViewportView(resourcesTable);
        modSpeechConfig = new JPanel();
        modSpeechConfig.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(20, 20, 20, 20), -1, -1));
        modConfig.addTab("Speech", modSpeechConfig);
        modSpeechTableScrollPlane = new JScrollPane();
        modSpeechConfig.add(modSpeechTableScrollPlane, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        modSpeechTable = new JTable();
        modSpeechTableScrollPlane.setViewportView(modSpeechTable);
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        modSpeechConfig.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        modSpeechReloadSpeech = new JButton();
        modSpeechReloadSpeech.setText("Reload");
        modSpeechConfig.add(modSpeechReloadSpeech, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveAll = new JButton();
        saveAll.setText("Save All");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        modEditorPanel.add(saveAll, gbc);
        modExport = new JButton();
        modExport.setText("Export");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        modEditorPanel.add(modExport, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return modEditorPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
