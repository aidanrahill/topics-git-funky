import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            BlobTest.test();
            // new Blob(new File(1.txt"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
