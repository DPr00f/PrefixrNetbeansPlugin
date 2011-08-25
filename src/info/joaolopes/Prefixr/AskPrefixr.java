package info.joaolopes.Prefixr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author Pink Donut <joaolopes at pinkdonut.net>
 */
public class AskPrefixr {
    public static String now(String css){
        String toRet = "";
        try {
            // Construct data
            String data = URLEncoder.encode("css", "UTF-8") + "=" + URLEncoder.encode(css, "UTF-8");

            // Send data
            URL url = new URL("http://prefixr.com/api/index.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                toRet += line + "\n";
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
        }
        return toRet;
    }
}
