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
    
        
        Object[][] mátrixot ad vissza amiben a sorok és oszlopok benne vannak
        Ebből aztán tudom frissíteni a táblát könnyen
        Object[row][col]
        Egy sor:    for(int i 0 - col_num) Object[row][i]
    */
    Object[][] getAllItems(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
    Object[][] getAllItems(); //inner join 
    
    /*
        true-t ad vissza ha sikerült, false-t ha nem
        AMELYIK PARAMÉTERT NEM HASZNÁLJUK AZ implicit NULL :D
    */
    boolean updateDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
    
    boolean removeFromDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
}
