import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Blob {
    public static void Create(File file, String destination) throws IOException {
        String fileContents = Analyze(file);
        String sha1FileContents = Sha1.toSHA1(fileContents);
        new File(destination + "/" + sha1FileContents).createNewFile();
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