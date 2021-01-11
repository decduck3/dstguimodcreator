package updater;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Updater {
    public boolean CheckForUpdate(){
        try {
            URL url = new URL("https://api.github.com/repos/decduck3/dstguimodcreator/releases/latest");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            Map<String, String> params = new HashMap<String, String>();
            params.put("accept", "application/vnd.github.v3+json");
            params.put("owner", "decduck3");
            params.put("repo", "dstguimodcreator");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
