package ItemTest;


import java.util.Random;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mtrua
 */
public class ItemGenerator {
    
    public static Object[][] generateData()
    {
        return generateData(10);
    }
    
    public static Object[][] generateData(int row)
    {
        Object[][] data = new Object[row][10];
        
        for(int i = 0; i < row; i++)
        {
            data[i] = generateLine();
        }
        
        return data;
    }
    
    //public Item(String ItemID, String Category, String Name, int Quantity, 
    //String OperatorName, LocalDateTime TimePlaced, LocalDateTime TimeModified, Boolean NeedsReorder)
    
    /*
        {"Cikkszám", "Polc", "Doboz", 
        "Megnevezés", "Kategória", "Darabszám", "Elhelyezés dátuma", 
        "Módosítás dátuma", "Kezelő személyzet", "Utánrendelés szükséges"};
    */
    
    private static Object[] generateLine()
    {
        Random r = new Random();
        
        return new Object[] 
        {generateString(), generateString(), generateString(), generateString(), 
         generateString(), (Integer)r.nextInt(10000), dateNow(), 
         dateNow(), generateString(), (Boolean)r.nextBoolean()
        };
    }
    
    private static Date dateNow()
    {
        return java.sql.Timestamp.valueOf(LocalDateTime.now());
    }
    
    private static String generateString()
    {
        Random r = new Random();
        
        return String.valueOf(new char[] {(char)(r.nextInt('Z'-'A')+'A'),
                  (char)(r.nextInt('Z'-'A')+'A'),
                  (char)(r.nextInt('Z'-'A')+'A'),
                  (char)(r.nextInt('Z'-'A')+'A')});
    }
}
