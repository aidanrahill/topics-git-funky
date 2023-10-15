import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWrite {
    public static String read(File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file)); 
        String newString = "";
        while(reader.ready()){
            newString += (char)reader.read();
        }
        reader.close();
        return newString;
    }
    public static void write(File file, String string) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(file)); 
        writer.write(string);
        writer.close();
    }
}
