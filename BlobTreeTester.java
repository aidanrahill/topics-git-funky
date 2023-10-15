import java.io.File;
import java.io.IOException;

public class BlobTreeTester {
    public static void main(String[] args) {
        try {
            // Tree.Initalize();
            // Tree.AddFile("1.txt");
            // Tree.AddFile("2.txt");
            // Tree.AddFile("3.txt");
            // Tree.RemoveFile("3.txt");
            new Blob(new File("1.txt"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
