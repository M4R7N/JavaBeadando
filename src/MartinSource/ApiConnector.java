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


/**
 *
 * @author mAtri
 */
public class ApiConnector implements ApiConnectorInterface {
    
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
    
    Object[][] data;
    
    @Override
    public Object[][] getAllItems()
    {
        try
        {
           JSONObject json = readJsonFromUrl("localhost:8080/rest/test/item/");
           
           //Minden egyéb csak még nincs meg a json formátum
           //FÉLIX SEGÍTS
           JSONArray arr = new JSONArray();
        }
        catch(IOException |JSONException e) {
            Logger.Log(e.toString());
        }
        
        return data;
    }
    
    @Override
    public Object[][] getAllItems(String searchWord)
    {
        //Keresés a rendezett data táblázatban és egy új Object[][] visszaadása
        // (Persze csak miután nyomtam egy feltöltést bele)
        
        return data;
    }
    
    @Override
    public Object[][] getAllItems(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10)
    {
        //Ezt nem akarom használni mert nem sok értelme van, lehet törlöm
        return data;
    }
    
    @Override
    public boolean addToDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10)
    {
        
        return false;
    }
    
    /*
        true-t ad vissza ha sikerült, false-t ha nem
        AMELYIK PARAMÉTERT NEM HASZNÁLJUK AZ implicit NULL :D
    */
    @Override
    public boolean updateDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10)
    {
        return false;
    }
    
    @Override
    public boolean removeFromDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10)
    {
        return false;
    }
}
