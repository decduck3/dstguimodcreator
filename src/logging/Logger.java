package logging;

import savesystem.SaveObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Logger {

    public static String logLocation = "./log.txt";

    public static String currentLog = "";

    public static void Log(String message){
        currentLog = currentLog + message + "\n";
        System.out.println(message);
        WriteChanges();
    }

    public static void Error(String error){
        currentLog = currentLog + "[ERROR] " + error + "\n";
        System.out.println("[ERROR] " + error);
        WriteChanges();
    }

    public static void WriteChanges(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(logLocation);
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(fileOut);
            out.writeObject(currentLog);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
