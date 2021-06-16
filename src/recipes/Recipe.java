package recipes;

public class Recipe {

    public int id;
    public String result;
    public String[] ingredients;
    public String tag;
    public String workstation;

    public Recipe(){

    }

    public Recipe(int id, String result, String[] ingredients, String tag, String workstation) {
        this.id = id;
        this.result = result;
        this.ingredients = ingredients;
        this.tag = tag;
        this.workstation = workstation;
    }
}
