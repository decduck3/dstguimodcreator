package config;

import com.sun.jna.platform.unix.X11;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;

import static constants.Constants.FILE_LOCATION;

public class GlobalConfig {

    public static boolean darkMode = false;
    public static boolean askSaveOnLeave = true;
    public static String modsLocation = "/mods/";

    public static XStream stream;

    public static void CreateStream(){
        stream = new XStream(new DomDriver());
        stream.alias("config", Config.class);

        if(!new File(FILE_LOCATION + "/config.xml").exists()){
            new Config().Save();
            System.out.println("Created new config");
        }
    }

}
