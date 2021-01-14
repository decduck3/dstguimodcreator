package recipes;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    public String result = "";
    public List<String> input = new ArrayList<String>();
    public String tag = "";

    public Recipe(String result, List<String> input, String tag) {
        this.result = result;
        this.input = input;
        this.tag = tag;
    }
}
