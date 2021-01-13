package logging;
import java.io.*;
import java.util.Calendar;

import static constants.Constants.FILE_LOCATION;

public class Logger {

    public static String logLocation = FILE_LOCATION + "/log.txt";

    public static String currentLog = "";

    public static void Log(String message){
        currentLog = currentLog + "[" + Calendar.getInstance().getTime() + "] " + message + "\n";
        System.out.println(message);
        WriteChanges();
    }

    public static void Error(String error){
        Log("[ERROR] " + error);
        WriteChanges();
    }

    public static void WriteChanges(){
        try {
            File file = new File(logLocation);
            FileWriter fr = new FileWriter(file, false);
            fr.write(currentLog);
            fr.close();
        } catch (IOException e) {
            try {
                new File(logLocation).createNewFile();
            } catch (IOException ioException) {
                return;
            }
            WriteChanges();
        }

    }

}
