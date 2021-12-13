/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartinSource;

import FelixSource.Container;
import FelixSource.Item;
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
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mAtri
 */
public class ApiConnector implements ApiConnectorInterface {
    
    private Object[][] data = new Object[0][10];
    
    @Override
    public Object[][] getAllItems()
    {
        //data = ItemGenerator.generateData(rowCount);
        
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
                
                //Convert json to object[][]
                
                Vector<Object[]> items = new Vector<>();
                Vector<Item> collection = new Vector<>();
                
                JSONArray jsonarr = new JSONArray(jsonReply);
                
                for(int i = 0; i < jsonarr.length(); i++)
                {
                    JSONObject o = (JSONObject)jsonarr.get(i);
                    
                    Item item = new Item(
                            o.getString("itemID"), 
                            o.getString("category"),
                            o.getString("name"),
                            o.getInt("quantity"),
                            o.getString("operatorName"),
                            LocalDateTime.parse(o.getString("timePlaced")),
                            LocalDateTime.parse(o.getString("timeModified")),
                            o.getBoolean("needsReorder")
                    );
                    
                    JSONArray cArr = o.getJSONArray("container");
                    
                    ArrayList<Container> arrList = new ArrayList<>();
                    
                    for(int j = 0; j < cArr.length(); j++)
                    {
                        JSONObject c = (JSONObject)cArr.getJSONObject(j);
                        Container singleCon = new Container(
                            c.getString("shelf"),
                            c.getString("box")
                        );
                        
                        arrList.add(singleCon);
                    }
                    
                    item.setContainer(arrList);
                    
                    collection.add(item);
                }
                
                for(int i = 0; i < collection.size(); i++)
                {
                    Item item = collection.get(i);
                    ArrayList<Container> cArr = item.getContainer();
                    if(cArr.size()>0)
                    {
                        for(Container c : cArr)
                        {
                            Object[] out = new Object[10];
                            
                            out[0] = item.getItemID();
                            out[1] = c.getShelf();
                            out[2] = c.getBox();
                            out[3] = item.getName();
                            out[4] = item.getCategory();
                            out[5] = item.getQuantity();
                            out[6] = java.sql.Timestamp.valueOf(item.getTimePlaced());
                            out[7] = java.sql.Timestamp.valueOf(item.getTimeModified());
                            out[8] = item.getOperatorName();
                            out[9] = item.getNeedsReorder();
                            
                            items.add(out);
                        }
                    }
                    else
                    {
                        Object[] out = new Object[10];
                        
                        out[0] = item.getItemID();
                        out[3] = item.getName();
                        out[4] = item.getCategory();
                        out[5] = item.getQuantity();
                        out[6] = java.sql.Timestamp.valueOf(item.getTimePlaced());
                        out[7] = java.sql.Timestamp.valueOf(item.getTimeModified());
                        out[8] = item.getOperatorName();
                        out[9] = item.getNeedsReorder();
                        
                        items.add(out);
                    }
                }
                
                data = new Object[items.size()][10];
                
                for(int i = 0; i < items.size(); i++)
                {
                    data[i] = items.get(i);
                }
                
                
                //teszt kiriatás hogy jó-e
                //logger.log(Level.INFO, jsonReply);
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
        for(int i = 0; i < data.length; ++i)
        {
            for(int j = 0; j < data[i].length; ++j)
            {
                boolean res = false;
                try{
                    res = data[i][j].toString().toLowerCase().contains(searchWord.toLowerCase());
                }
                catch(NullPointerException ex)
                {
                    Logger.getLogger(ApiConnector.class.getName()).log(Level.FINE, null, ex);
                }
                if(res)   
                {
                    lines.add(data[i]);
                    break;
                }
            }
        }
        
        outData = new Object[lines.size()][10];
        for(int i = 0; i < lines.size(); i++)
        {
            outData[i] = lines.elementAt(i);
        }
        
        return outData;
    }
    
    @Override
    public boolean addOrUpdateDB(String itemID, String shelf, String box, 
            String name, String category, int quantity, LocalDateTime placed, 
            LocalDateTime modified, String operator, boolean reorder)
    {
        //ha módosítás akkor a placed marad, a modified változik csak
        ArrayList<Container> arList = new ArrayList<>();
        arList.add(new Container(shelf, box));
        
        Item outItem = new Item(itemID, category, name, quantity, 
                operator, placed, modified, reorder, 
                arList);
        
        // át jsonbe
        JSONObject jsonObject = new JSONObject(outItem);
        
        final Logger logger = Logger.getGlobal();
        
        logger.log(Level.INFO, jsonObject.toString());
        int status = 0;
        try
        {
            URL url = new URL("http://localhost:8080/rest/item/withcontainer");
            
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            
            con.setDoOutput(true);
            
            DataOutputStream wr = new DataOutputStream (
            con.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.close();
            
            status = con.getResponseCode();
            
            logger.log(Level.INFO, Integer.toString(status));
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ApiConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status == 201;
    }
    
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
        final Logger logger = Logger.getGlobal();
        
        int status = 0;
        try
        {
            URL url = new URL("http://localhost:8080/rest/item/delete/".concat(itemID));
            
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("DELETE");
            //con.setDoOutput(true);
            status = con.getResponseCode();
            
            logger.log(Level.INFO, Integer.toString(status));
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ApiConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status == 200;
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
