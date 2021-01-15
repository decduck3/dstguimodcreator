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
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class RecipeLoader extends ModLoader {

    public static List<Recipe> recipes = new ArrayList<Recipe>();

    static{
        for(int i = 0; i < 20; i++){
            recipes.add(new Recipe("" + i, new ArrayList<String>(), ""));
        }
    }

    public static void SetupRecipeTab(){

    }

    public static void UpdateRecipeTab(){

    }

}
