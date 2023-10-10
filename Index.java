import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Index {
    static File rootFile = new File("./objects/");
    static File indexFile = new File("./objects/index.txt/");

    public static void Initalize() throws IOException {
        if (!rootFile.exists()) {
            rootFile.mkdir();
        }
        if (!indexFile.exists()) {
            indexFile.createNewFile();
        }
    }

    public static void AddFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(indexFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(indexFile, true));
        Blob.Create(indexFile, rootFile.getAbsolutePath());
        if (!reader.ready()) {
            writer.write(getEntryName(file));
        } else {
            writer.write("\n" + getEntryName(file));
        }
        writer.close();
        reader.close();
    }

    public static void RemoveFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(indexFile));
        String newIndex = "";
        String fileEntry = getEntryName(file);
        boolean first = true;
        while (reader.ready()) {
            String entry = reader.readLine();
            if (!entry.equals(fileEntry)) {
                if (!first) {
                    newIndex += "\n";
                }
                newIndex += entry;
                first = false;
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(indexFile));
        writer.write(newIndex);
        writer.close();
    }

    static String getEntryName(File file) throws IOException {
        return file.toString() + " : " + Blob.Analyze(file);
    }
}
