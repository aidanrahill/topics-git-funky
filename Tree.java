import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tree {
    static File rootFile = new File("./objects/");
    static File treeFile = new File("./objects/tree.txt/");

    public static void Initalize() throws IOException {
        if (!rootFile.exists()) {
            rootFile.mkdir();
        }
        if (!treeFile.exists()) {
            treeFile.createNewFile();
        }
    }

    public static String AddFile(File treeFile, String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(treeFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(treeFile, true));
        if (!reader.ready()) {
            writer.write(getEntryName(file));
        } else {
            writer.write("\n" + getEntryName(file));
        }
        writer.close();
        reader.close();
        new Blob(treeFile);
        return Blob.Analyze(treeFile);
    }
    public static String RemoveFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(treeFile));
        String newTree = "";
        String fileEntry = getEntryName(file);
        boolean first = true;
        while (reader.ready()) {
            String entry = reader.readLine();
            if (!entry.equals(fileEntry)) {
                if (!first) {
                    newTree += "\n";
                }
                newTree += entry;
                first = false;
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(treeFile));
        writer.write(newTree);
        writer.close();
        new Blob(treeFile);
        return Blob.Analyze(treeFile);
    }
    static String getEntryName(File file) throws IOException {
        return file.toString() + " : " + Blob.Analyze(file);
    }
}
