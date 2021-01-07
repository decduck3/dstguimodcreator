package modloader.resources;

import modloader.classes.Texture;

public class Resource implements java.io.Serializable {
    public boolean isTexture;
    public boolean isOther;

    public Texture texture;
    public String filePath;

    public String displayUse;
}
