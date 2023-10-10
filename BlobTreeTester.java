import java.io.IOException;

public class BlobIndexTester {
    public static void main(String[] args) {
        try {
            Index.Initalize();
            Index.AddFile("1.txt");
            Index.AddFile("2.txt");
            Index.AddFile("3.txt");
            Index.RemoveFile("3.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
