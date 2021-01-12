package config;

import java.io.*;

import static constants.Constants.FILE_LOCATION;
import static logging.Logger.currentLog;

public class Config {

    public boolean darkMode;
    public boolean askSaveOnLeave;

    public void Save(){
        darkMode = GlobalConfig.darkMode;
        askSaveOnLeave = GlobalConfig.askSaveOnLeave;

        try {
            File file = new File(FILE_LOCATION + "/config.xml");
            FileWriter fr = new FileWriter(file, false);
            fr.write(GlobalConfig.stream.toXML(this));
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load(){
        try {
            File file = new File(FILE_LOCATION + "/config.xml");
            FileInputStream  fr = new FileInputStream (file);
            byte[] data = new byte[(int) file.length()];
            fr.read(data);
            fr.close();

            String xml = new String(data, "UTF-8");

            Config config = (Config) GlobalConfig.stream.fromXML(xml);

            GlobalConfig.darkMode = config.darkMode;
            GlobalConfig.askSaveOnLeave = config.askSaveOnLeave;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
