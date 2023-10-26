import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tree {
    File treeDirectory;
    public File treeFile;
    public File trees = new File("trees");
    public Tree(String directoryPath) throws IOException{
        this.treeDirectory = new File(directoryPath);
        if(!trees.exists()){
            trees.mkdir();
        }
        if (!treeDirectory.exists()) {
            treeDirectory.mkdir();
        }
        if(!treeDirectory.isDirectory()){
            throw new IOException("wrong file type");
        }
        // treeFile = new File("./trees/" + treeDirectory.toString());
        // treeFile.createNewFile();
        // BufferedWriter writer = new BufferedWriter(new FileWriter(treeFile));
        String str = "";
        boolean first = true;
        for (File file : treeDirectory.listFiles()) {
            if(!first){
                // writer.write("\n");
                str += "\n";
            }
            if(file.isDirectory()){
                Tree tree = new Tree(file.getPath());
                file = tree.treeFile;
            }
            else{
                new Blob(file);
            }
            // writer.write(getEntryName(file));
            str += getEntryName(file);
            first = false;
        } 
        File tf = new File ("trees/" + Sha1.toSHA1(str));
        tf.createNewFile();
        FileWriter writer = new FileWriter(tf);
        writer.write(str);
        writer.close();
        this.treeFile = tf;
        new Blob(treeFile);
    }
    public void  addDirectory(String directoryPath) throws IOException{
        Tree tree = new Tree(directoryPath);
        add(trees + directoryPath.toString());
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
