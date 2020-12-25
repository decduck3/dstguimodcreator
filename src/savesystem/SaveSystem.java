package savesystem;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

public class SaveSystem {
    public static void clearTheFile(String fileName) {
        try {
            FileWriter fwOb = new FileWriter(fileName, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void Save(String filePath){
        try {
        File f = new File(filePath);
        if(!f.isFile()){
            f.createNewFile();
        }

        clearTheFile(filePath);

        FileOutputStream fileOut =
                new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(new SaveObject());
        out.close();
        fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load(String filePath){
        SaveObject e = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (SaveObject) in.readObject();
            in.close();
            fileIn.close();

            e.LoadBack();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }
    }
}
