package MartinSource;

import java.util.Date;

/* 
 * @author MARITN
 */
public interface ApiConnectorInterface {
    
    //Olyan lekérdezések kellenek, hogy:
    /*  (5 string 1 integer 2 date 1 string 1 bool)
        A bemenő paraméterek alapján minél több meg van adva annál jobban lehet
        szűkíteni a találatokat.
        Mindegyik mező alapján lehet keresni (még db is)
        Amelyiket nem keressük az nullként érkezik be
    
        Nem void hanem List<TableRow> féleség ha van ilyen ha nincs akkor 
        List<List<Object>>
        Ahol a legbelső list maga egy sor, ami azt tárolja meg az összes sor
        ami eredményként érkezett
        
    */
    void getAllItems(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
    void getAllItems(); //inner join 
    
    /*
        true-t ad vissza ha sikerült, false-t ha nem
    */
    boolean updateDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
}
