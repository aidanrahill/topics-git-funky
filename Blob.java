import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Blob {
    static File rootFile = new File("./objects/"); 
    public Blob(File file) throws IOException {
        if(!rootFile.exists()){
            rootFile.mkdir();
        }
        String sha1FileContents = Analyze(file);
        File blobFile = new File("./objects/" + sha1FileContents);
        blobFile.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(blobFile));
        BufferedReader reader = new BufferedReader(new FileReader(blobFile));
        String fileContents = "";
        while (reader.ready()) {
            fileContents += (char) reader.read();
        }
        writer.write(fileContents);
        writer.close();
        reader.close();
    }

    public static String Analyze(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String fileContents = "";
        while (reader.ready()) {
            fileContents += (char) reader.read();
        }
        reader.close();
        return Sha1.toSHA1(fileContents);
    }
}