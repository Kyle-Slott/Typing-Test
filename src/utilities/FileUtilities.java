package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileUtilities {
	private static String saveDirectory = "data";
	private static String openDirectory = "data";
	private static File path = null;
	
	public static int getFileSize(String fileName) throws IOException {
		int count=0;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		while (br.readLine()!=null) {
			count++;
		}
		br.close();
		return count;
	}
	
	public static void saveFile(String contents, Stage stage) throws IOException {
		if (path==null) {
			saveAsFile(contents, stage);
		} else {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
			bw.append(contents);
			bw.close();
		}
	}
	
	public static void saveAsFile(String contents, Stage stage) throws IOException {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(saveDirectory));
		fc.setInitialFileName("log.txt");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
		File path = fc.showSaveDialog(stage);
    	if (path!=null) {
    		BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
    		bw.append(contents);
    		bw.close();
    	}
	}
	
	public static File openFile(Stage stage) {
		File tempFile;
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(openDirectory));
		fc.setTitle("Open File");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
		if ((tempFile = fc.showOpenDialog(stage))!=null) {
			path = tempFile;
		}
		return tempFile;
	}
	
	public static File openLearningFile(Stage stage) {
		File tempFile;
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(openDirectory));
		fc.setTitle("Select File to Learn");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
		tempFile = fc.showOpenDialog(stage);
		return tempFile;
	}
	
	public static String readFile(File file) throws IOException {
		String result = null;

        DataInputStream reader = new DataInputStream(new FileInputStream(file));
        int nBytesToRead = reader.available();
        if(nBytesToRead > 0) {
            byte[] bytes = new byte[nBytesToRead];
            reader.read(bytes);
            result = new String(bytes);
            
        }
        reader.close();
        return result;
	}
	
	public static void setPath(File newPath) {
		path = newPath;
	}
	
	public static File getPath() {
		return path;
	}
}
