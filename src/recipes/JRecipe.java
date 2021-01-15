package recipes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import static modloader.ModLoader.modEditor;

public class JRecipe {

    Recipe recipe;

    public JRecipe(Recipe recipe){
        this.recipe = recipe;
    }

    public JPanel getRecipePanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        //panel.setLayout(new BoxLayout(panel, 1));
        panel.setMaximumSize(new Dimension(-1, 20));

        JLabel result = new JLabel("Result: " + recipe.result);
        JComboBox ingredients = new JComboBox();
        JButton changeResult = new JButton("Change result");
        JButton addIngredient = new JButton("Add Ingredient");
        JButton removeIngredient = new JButton("Remove Ingredient");

        panel.add(result);
        panel.add(ingredients);
        panel.add(changeResult);
        panel.add(addIngredient);
        panel.add(removeIngredient);

        panel.add(new JScrollPane(modEditor.getModEditorPanel(),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        return panel;
    }

}
