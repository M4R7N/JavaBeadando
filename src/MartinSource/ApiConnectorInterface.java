package MartinSource;

import java.util.Date;

/* 
 * @author MARITN
 */
public interface ApiConnectorInterface {
    
    //Olyan lekérdezések kellenek, hogy:
    /*  
        Mindegyik mező alapján lehet keresni (még db is)
        Egy keresés string érkezik, azt kell keresni mindenben
        pl where articleNum is searchWord or box is searchWord
        (Lehet finomítani, hogy szóköznél split és mindegyik szóra rákeres
        pl where articleNum is search[0] or [1] )
        
    
        Object[][] mátrixot ad vissza amiben a sorok és oszlopok benne vannak
        Ebből aztán tudom frissíteni a táblát könnyen
        Object[row][col]
        Egy sor:    for(int i 0 - col_num) Object[row][i]
    */
    
    public Object[][] getAllItems();
    
    public Object[][] getAllItems(String searchWord);
    
    public Object[][] getAllItems(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
    
    public boolean addToDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
    
    /*
        true-t ad vissza ha sikerült, false-t ha nem
        AMELYIK PARAMÉTERT NEM HASZNÁLJUK AZ implicit NULL :D
    */
    public boolean updateDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
    
    public boolean removeFromDB(String param1, String param2, String param3, String param4,
            String param5, int param6, Date param7, Date param8, String param9, 
            boolean param10);
}
