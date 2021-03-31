package frames;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

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
    private JButton modItemDelete;
    private JButton modItemCreate;
    private JLabel modItemNameLabel;
    private JTextField modItemNameTextField;
    private JComboBox modItemTextureSelect;
    private JLabel modItemTextureSelectLabel;
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
    private JButton openFolder;
    private JPanel modExportPanel;
    private JTree modItemComponentNotAdded;
    private JTree modItemComponetsAdded;
    private JButton githubButton;
    private JButton wikiButton;
    private JPanel modRecipes;
    private JPanel modRecipesButtonPanel;
    private JButton modRecipesButtonCreate;
    private JButton modRecipesButtonDelete;
    private JPanel modRecipesListPanel;
    private JScrollPane modRecipesListScrollPlane;

    public JScrollPane getModRecipesListScrollPlane() {
        return modRecipesListScrollPlane;
    }

    public JPanel getModRecipesListPanel() {
        return modRecipesListPanel;
    }

    public JPanel getModRecipes() {
        return modRecipes;
    }

    public JPanel getModRecipesButtonPanel() {
        return modRecipesButtonPanel;
    }

    public JButton getModRecipesButtonCreate() {
        return modRecipesButtonCreate;
    }

    public JButton getModRecipesButtonDelete() {
        return modRecipesButtonDelete;
    }

    public JButton getGithubButton() {
        return githubButton;
    }

    public JButton getWikiButton() {
        return wikiButton;
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

    public JTable getModSpeechTable() {
        return modSpeechTable;
    }

    public JScrollPane getModSpeechTableScrollPlane() {
        return modSpeechTableScrollPlane;
    }

    public JButton getModSpeechReloadSpeech() {
        return modSpeechReloadSpeech;
    }

    public JButton getOpenFolder() {
        return openFolder;
    }

    public JPanel getModExportPanel() {
        return modExportPanel;
    }

    public JTree getModItemComponentNotAdded() {
        return modItemComponentNotAdded;
    }

    public JTree getModItemComponetsAdded() {
        return modItemComponetsAdded;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
