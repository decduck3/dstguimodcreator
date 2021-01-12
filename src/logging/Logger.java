package logging;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
