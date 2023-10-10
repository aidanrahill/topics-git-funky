import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Commit {
    public static void CreateCommit(String previousSHA, String author, String summary) throws IOException {
        File commitFile = new File("./objects/" +
                Sha1.toSHA1("" + treeSHA + previousSHA + nextSHA + author + date + summary));
        FileWriter writer = new FileWriter(commitFile);
        writer.write("tree : " + treeSHA + "\n");
        writer.write("previous : " + previousSHA + "\n");
        writer.write("next : " + nextSHA + "\n");
        writer.write("author : " + author + "\n");
        writer.write("date : " + date + "\n");
        writer.write("summary : " + summary);
        writer.close();
    }
}
