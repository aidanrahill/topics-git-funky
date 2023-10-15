import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Commit {
    static File headFile = new File("./head");
    public Commit(String previousCommitSha, String author, String summary) throws IOException {
        String commitSha = Sha1.toSHA1("" + previousCommitSha + author + summary);
        File commitFile = new File("./objects/" + commitSha);
        writeToFile(commitFile, previousCommitSha, null, author, summary);
    }
    public Commit(String author, String summary) throws IOException{
        String commitSha = Sha1.toSHA1(""  + author + summary);
        File commitFile = new File("./objects/" + commitSha);
        writeToFile(commitFile, null, null, author, summary);

    }
    static void updateHead(String newCommitSha) throws IOException{
        if(headFile.exists()){
            BufferedReader reader = new BufferedReader(new FileReader(headFile));
            String oldCommitSha = reader.readLine();;
            addNext(oldCommitSha, newCommitSha);
            reader.close();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(headFile));
        writer.write(newCommitSha);
        writer.close();
    }
    static void addNext(String commitSha, String nextCommitSha) throws IOException{
        File commitFile = new File("./objects/" + commitSha);
        BufferedReader reader = new BufferedReader(new FileReader(commitFile));
        String newCommit = "";
        //if you squint your eyes enough, this code is flipping you off.

        newCommit += reader.readLine() + "\n";
        newCommit += reader.readLine() + "\n";
        newCommit += reader.readLine() +" " + nextCommitSha + "\n";
        newCommit += reader.readLine() + "\n";
        newCommit += reader.readLine() + "\n";
        newCommit += reader.readLine();

        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(commitFile));
        writer.write(newCommit);
        writer.close();
    }
    static void writeToFile(File commitFile, String previousCommitSha, String nextCommitSha, String author, String summary) throws IOException{
        if(previousCommitSha == null){
            previousCommitSha = "";
        }
        if(nextCommitSha == null){
            nextCommitSha = "";
        }
        FileWriter writer = new FileWriter(commitFile);
        //not actually the sha yet because thats not a thing (yet)
        String treeSHA = Blob.Analyze(headFile);
        writer.write("tree : " +treeSHA + "\n");
        writer.write("previous : " + previousCommitSha + "\n");
        writer.write("next : \n");
        writer.write("author : " + author + "\n");
        writer.write("date : " + new Date().toString() +"\n");
        writer.write("summary : " + summary);
        writer.close();
    }
}
