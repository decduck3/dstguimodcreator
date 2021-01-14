package recipes;

import modloader.Mod;
import modloader.ModLoader;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class RecipeLoader extends ModLoader {

    public static List<Recipe> recipes = new ArrayList<Recipe>();
    static{
        for(Recipe r:recipes){
            r.input.add("input");
        }
    }

    public static void SetupRecipeTab(){
        for(Recipe r:Mod.recipes){
            recipes.add(r);
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        modEditor.getModRecipeList().setModel(model);

        for(Recipe r:recipes){
            model.addElement(r.result);
        }
    }

    public static void UpdateRecipeTab(){
        Mod.recipes.clear();
        for(Recipe r:recipes){
            Mod.recipes.add(r);
        }
    }

}
