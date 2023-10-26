import java.io.File;
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
        String fileContents = ReadWrite.read(file);
        ReadWrite.write(blobFile, fileContents);
    }
        public Blob(String string) throws IOException {
            new Blob(new File(string));
        }

    public static String Analyze(File file) throws IOException {
        String fileContents = ReadWrite.read(file);
        return Sha1.toSHA1(fileContents);
    }
}