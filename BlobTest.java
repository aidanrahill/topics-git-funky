import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

// import java.GitUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlobTest {
    @Test
    @DisplayName("Testing if Blob class creates an objects folder")
    public void test() throws IOException{

	// programmatically create a test file
	File file = new File("test.txt");
	file.createNewFile();

	/* 
			programmatically write to the test file,
			you may have a different method or class
			that writes to file
	*/
	ReadWrite.write(file, "hello world");

	// programmatically create a blob
	Blob blob = new Blob("test.txt");

	// Blob should create an objects folder.
	File folder = new File("objects");

	// does the folder exist? 
	assertTrue(folder.exists());
    File shadFile = new File("/objects/" + Blob.Analyze(file));
    assertTrue(shadFile.exists());
    assertTrue(ReadWrite.read(shadFile).equals(ReadWrite.read(file)));
}
}
