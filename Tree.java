import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tree {
    File treeDirectory;
    public File treeFile;

    public Tree(File treeDirectory) throws IOException{
        this.treeDirectory = treeDirectory;
        if (!treeFile.exists()) {
            treeFile.mkdir();
        }
        if(!treeFile.isDirectory()){
            throw new IOException("wrong tree file type");
        }
        treeFile = new File("./objects/" + treeDirectory.toString());
        BufferedWriter writer = new BufferedWriter(new FileWriter(treeFile));
        boolean first = true;
        for (File file : treeFile.listFiles()) {
            if(!first){
                writer.write("\n");
            }
            if(treeFile.isDirectory()){
                Tree tree = new Tree(file);
                file = tree.treeFile;
            }
            else{
                new Blob(file);
            }
            writer.write(getEntryName(file));
            first = false;
        } 
        writer.close();
        new Blob(treeFile);
    }

    public void add(String filePath) throws IOException {
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
    }

    public void remove(String filePath) throws IOException {
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
    }
    
    private String getEntryName(File file) throws IOException {
        String fileType;
        if(file.isDirectory()){
            fileType = "tree";
        }
        else{
            fileType = "blob";
        }
        return fileType +  " : " + Blob.Analyze(file) + " : " + file.toString();
    }
}
