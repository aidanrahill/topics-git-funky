import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Commit {
    static File headFile = new File("./head");
    public Commit(String previousCommitSha, String author, String summary) throws IOException {
        String commitSha = Sha1.toSHA1("" + previousCommitSha + author + summary);
        File commitFile = new File(commitSha);
        writeToFile(commitFile, previousCommitSha, null, author, summary);
        new Blob(commitFile);
    }
    public Commit(String author, String summary) throws IOException{
        String commitSha = Sha1.toSHA1(""  + author + summary);
        File commitFile = new File(commitSha);
        writeToFile(commitFile, null, null, author, summary);
        new Blob(commitFile);
    }
    static void updateHead(String newCommitSha) throws IOException{
        if(headFile.exists()){
            String oldCommitSha = ReadWrite.read(headFile);
            addNext(oldCommitSha, newCommitSha);
        }
        ReadWrite.write(headFile, newCommitSha);
    }
    static void addNext(String commitSha, String nextCommitSha) throws IOException{
        File commitFile = new File(commitSha);
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
        ReadWrite.write(commitFile, newCommit);
    }
    static void writeToFile(File commitFile, String previousCommitSha, String nextCommitSha, String author, String summary) throws IOException{
        if(previousCommitSha == null){
            previousCommitSha = "";
        }
        if(nextCommitSha == null){
            nextCommitSha = "";
        }
        String treeSHA = ReadWrite.read(headFile);
        ReadWrite.write(commitFile, 
        treeSHA + "\n" + 
        previousCommitSha + "\n" + 
        nextCommitSha +  "\n" + 
        author + "\n" +
        new Date().toString() + "\n" + 
        summary );
    }
}
