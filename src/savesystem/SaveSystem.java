package savesystem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import modloader.classes.Item;

import java.io.*;

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

        XStream xstream = new XStream(new DomDriver());
        SaveObject toSave = new SaveObject();

        String xml = xstream.toXML(toSave);

        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(xml);
        printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load(String filePath){
        try {
            SaveObject e = null;
            XStream xstream = new XStream(new StaxDriver());

            File f = new File(filePath);
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();

            String xml = new String(data);

            e = (SaveObject) xstream.fromXML(xml);

            e.LoadBack();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static SaveObject TempLoad(String filePath){
        try {
            SaveObject e = null;
            XStream xstream = new XStream(new StaxDriver());

            File f = new File(filePath);
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();

            String xml = new String(data);

            e = (SaveObject) xstream.fromXML(xml);

            return e;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }
}
