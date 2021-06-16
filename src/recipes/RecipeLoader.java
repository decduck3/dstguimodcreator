package recipes;

import frames.ModEditor;
import items.Item;
import items.components.*;
import logging.Logger;
import modloader.Mod;
import modloader.ModLoader;
import util.TreeHelper;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RecipeLoader extends ModLoader {

    public static Recipe currentlySelected;

    public static void SetupRecipeEditor(JTree tree){
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ModLoader.SaveItem();
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 1) {

                    }
                    else if(e.getClickCount() == 2) {
                        Click(selPath.getLastPathComponent().toString(), selPath);
                    }
                }
            }
        };
        tree.addMouseListener(ml);
    }

    public static void Click(String name, TreePath path){
        if(currentlySelected == null){
            return;
        }
        if(((DefaultMutableTreeNode)path.getPathComponent(1)).toString() == "Ingredients"){
            Logger.Log("Clicked Ingredients");
        }
    }

    public static void Update(){
        int recipeIndex = modEditor.getModRecipesSelector().getSelectedIndex();
        if(Mod.recipes.size()-1 < recipeIndex){
            Logger.Error("Selection too long");
        }
        LoadRecipe(Mod.recipes.get(recipeIndex));
    }

    public static void LoadRecipe(Recipe r){
        JTree tree = modEditor.getModRecipesEditor();
        DefaultTreeModel model = (DefaultTreeModel)modEditor.getModRecipesEditor().getModel();
        String expansionState = TreeHelper.getExpansionState(tree);
        model.setRoot(new DefaultMutableTreeNode(r.result));
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();

        DefaultMutableTreeNode ingredients = TreeHelper.CreateNode("Ingredients");
        for(int i = 0; i < r.ingredients.length; i++){
            ingredients.add(TreeHelper.CreateNode(r.ingredients[i]));
        }
        ingredients.add(TreeHelper.CreateNode("New..."));

        root.add(ingredients);
        root.add(TreeHelper.CreateNode("Workstation: " + r.workstation));
        root.add(TreeHelper.CreateNode("Tag: " + r.tag));

        model.reload();

        TreeHelper.setExpansionState(expansionState, tree);
    }

    public static void CreateNewRecipe(){

    }

    public static void DeleteRecipe(){

    }

}
