/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartinSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Debug:
import ItemTest.ItemGenerator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mAtri
 */
public class ApiConnector implements ApiConnectorInterface {
    
    //felül kell írni majd ha már nem tesztelés folyik ATYA
    private static int rowCount = 20;
    private static int colCount = 10;
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
    }
    
    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          JSONObject json = new JSONObject(jsonText);
          return json;
        } finally {
          is.close();
        }
    }
    
    private Object[][] data;
    
    @Override
    public Object[][] getAllItems()
    {
        data = ItemGenerator.generateData(rowCount);
        
        //kell a változo a teszt logoláshoz
        final Logger logger = Logger.getGlobal();

        
        
        try {
            //Elködjük a kérést az api féle
            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/rest/item/all").openConnection();
            //megnézzük a vaálasz status codeját
            int status = connection.getResponseCode();
            //ha 200 akkor ok
            if(status == 200)
            {
                String jsonReply;
                //lekérjük a válasz input streamjét
                InputStream response = connection.getInputStream();
                //átalakítjuk a választ stringé a függvénnyel
                jsonReply = convertStreamToString(response);
                //teszt kiriatás hogy jó-e
                logger.log(Level.INFO, jsonReply);
            }
            else
            {
                //Legyen üres a visszaadott adat
                data = new Object[0][10];
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ApiConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    @Override
    public Object[][] getAllItems(String searchWord)
    {
        //Keresés a rendezett data táblázatban és egy új Object[][] visszaadása
        // (Persze csak miután nyomtam egy feltöltést bele)
        
        Object[][] outData;
        
        Vector<Object[]> lines = new Vector<>();
        for(int i = 0; i < rowCount; ++i)
        {
            for(int j = 0; j < colCount; ++j)
            {
                if(data[i][j].toString().toLowerCase().contains(searchWord.toLowerCase()))   
                {
                    lines.add(data[i]);
                    break;
                }
            }
        }
        
        outData = new Object[lines.size()][colCount];
        for(int i = 0; i < lines.size(); i++)
        {
            outData[i] = lines.elementAt(i);
        }
        
        return outData;
    }
    
    /* {"Cikkszám", "Polc", "Doboz", 
        "Megnevezés", "Kategória", "Darabszám", "Elhelyezés dátuma", 
        "Módosítás dátuma", "Kezelő személyzet", "Utánrendelés szükséges"};
    */
    
    @Override
    public Object[][] getAllItems(String itemID, String shelf, String box, 
            String name, String category, int quantity, Date placed, 
            Date modified, String operator, boolean reorder)
    {
        //Ezt nem akarom használni mert nem sok értelme van, lehet törlöm
        return data;
    }
    
    @Override
    public boolean addToDB(String itemID, String shelf, String box, 
            String name, String category, int quantity, LocalDateTime placed, 
            LocalDateTime modified, String operator, boolean reorder)
    {
        //placed és modified is now-ot kap
        
        return false;
    }
    
    /*
        true-t ad vissza ha sikerült, false-t ha nem
        AMELYIK PARAMÉTERT NEM HASZNÁLJUK AZ implicit NULL :D
    */
    @Override
    public boolean updateDB(String itemID, String shelf, String box, 
            String name, String category, int quantity, LocalDateTime placed, 
            LocalDateTime modified, String operator, boolean reorder)
    {
        return false;
    }
    
    @Override
    public boolean removeFromDB(String itemID)
    {
        return false;
    }
    
    
    //Segédfüggvény ami a kérés input streamjét stringé alakítja
    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {

        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }
}
