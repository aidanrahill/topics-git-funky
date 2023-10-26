import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TreeTest {

    @Test
    void testCreateFile() throws IOException {
        File file = new File("test1.txt");
        file.createNewFile();

        ReadWrite.write(file, "1234");
        Tree tree = new Tree("test1,txt");

        File treeFile = new File("Tree");

        assertTrue(treeFile.exists());
    }

    @Test
    void testMakeBlob() throws IOException {
        File file = new File("test.txt");
        file.createNewFile();

        ReadWrite.write(file, "lopez");
        Tree tree = new Tree("test.txt");

        String expectedFileString = Sha1.toSHA1("lopez");
        File expectedShaFile = new File("objects/" + expectedFileString);

        assertTrue(expectedShaFile.exists());
    }

    @Test
    void testAddEntry() throws IOException {

        File file = new File("test.txt");
        file.createNewFile();

        ReadWrite.write(file, "abc");
        Tree tree = new Tree("");
        tree.add(file.toString());

        String expected = "blob : " + Sha1.toSHA1("abc") + " : test.txt" + "\n";
        String actual = ReadWrite.read(new File("tree.txt"));
        assertTrue(expected.equals(actual));

    }

    @Test
    void testRemove() throws IOException {

        File file = new File("test.txt");
        file.createNewFile();
        ReadWrite.write(file, "rahill");

        File file1 = new File("test1.txt");
        file1.createNewFile();
        ReadWrite.write(file1, "aidan");
        Tree tree = new Tree("");
        tree.add("test.txt");
        tree.add("test1.txt");

        tree.remove("test1.txt");

        String expectedEntry = "blob : " + Sha1.toSHA1("rahill") + " : test.txt" + "\n";
        String realEntry = ReadWrite.read(file);

        assertTrue(expectedEntry.equals(realEntry));

    }

    @AfterEach
    void tearDown() {
        File testFile = new File("test.txt");
        testFile.delete();

        File test2File = new File("test1.txt");
        test2File.delete();

        File treeFile = new File("tree.txt");
        treeFile.delete();
    }
}