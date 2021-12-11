/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartinSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * @author MArtIn
 */
//Nagyon noob logger mert nincs idő a rendesre
public class Logger {
    
    public static String LogFilePath = "log.txt";
    
    private static String now()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        return dtf.format(LocalDateTime.now());
    }
    
    public static void Log(String output)
    {
        LogToSout(output);
        LogToFile(output);
    }
    
    private static void LogToSout(String output)
    {
        System.out.println(now() + " - " + output);
    }
    
    private static void LogToFile(String output)
    {
        File file = null;
        try
        {
            file = new File(LogFilePath);
            file.createNewFile();
        } 
        catch (NullPointerException | IOException | SecurityException e){}
        
        FileWriter writer = null;
        try
        {
            writer = new FileWriter(file, true);
            
            writer.write(now() + " - " + output);
        }
        catch (NullPointerException | IOException e) {}
        finally {
            try
            {
                writer.close();
            } 
            catch(NullPointerException | IOException e) {}
        }
    }
}
