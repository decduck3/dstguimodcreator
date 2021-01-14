package recipes;

import modloader.ModLoader;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RecipeLoader extends ModLoader {

    public static List<Recipe> recipes = new ArrayList<Recipe>();

    public static void SetupRecipeTab(){
        recipes.add(new Recipe("honey", new ArrayList<String>(){{
            add("honeycomb");
            add("rock");
            add("rock");
        }}, ""));
    }

    public static DefaultMutableTreeNode GetRecipeAsNode(Recipe recipe){
        DefaultMutableTreeNode ret = new DefaultMutableTreeNode(recipe.result);
        DefaultMutableTreeNode ingredients = new DefaultMutableTreeNode("Ingredients");
        for(String ing:recipe.input){
            ingredients.add(new DefaultMutableTreeNode("Ingredient: " + ing));
        }
        ret.add(ingredients);
        ret.add(new DefaultMutableTreeNode("Tag: " + recipe.tag));
        return ret;
    }

    public static void UpdateRecipeTab(){
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)((DefaultTreeModel) modEditor.getModRecipesTree().getModel()).getRoot();
        root.removeAllChildren();
        for(Recipe r:recipes){
            root.add(GetRecipeAsNode(r));
        }
        ((DefaultTreeModel) modEditor.getModRecipesTree().getModel()).reload();
    }

}
