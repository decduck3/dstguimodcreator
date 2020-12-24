package modloader;

public class Mod {
    public String modName;
    public String modAuthor;

    public Mod(){

    }

    public Mod(String path){
        LoadFromFile(path);
    }

    public void LoadFromFile(String path){

    }
}
