package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Clock;
import models.MarkovGenerator;
import models.SpellChecker;
import models.StatusBar;
import models.TypingTestChecker;
import utilities.FileUtilities;
//import javafx.stage.FileChooser;
//import javafx.stage.Window;

public class PrimaryController implements Initializable{
	private StatusBar sb;
	private SpellChecker sc;
	private Stack<String> undoStack;
	private MarkovGenerator mg;
	private String undoState;
	private Clock clock = new Clock();
	private TypingTestChecker ttc = new TypingTestChecker();
	
	@FXML
    private TextField wordCountField;

    @FXML
    private TextField percentCountField;

    @FXML
    private TextField timeField;
    
	@FXML
	private Menu fileMenu;

    @FXML
    private MenuItem fileMenuNew;

    @FXML
    private MenuItem fileMenuOpen;

    @FXML
    private MenuItem fileMenuClose;

    @FXML
    private MenuItem fileMenuSave;

    @FXML
    private MenuItem fileMenuSaveAs;

    @FXML
    private MenuItem fileMenuExit;

    @FXML
    private MenuItem editMenuUndo;

    @FXML
    private MenuItem editMenuWordCount;

    @FXML
    private MenuItem editMenuSentenceCount;

    @FXML
    private MenuItem editMenuFleschScore;

    @FXML
    private MenuItem menuSpellCheck;

    @FXML
    private MenuItem menuLearn;

    @FXML
    private MenuItem menuCreate;
    
    @FXML
    private Button create;

    @FXML
    private TextArea displayArea;
    
    @FXML
    private TextArea textArea;

    @FXML
    private TextArea misspelledArea;
    
    @FXML
    private TextField markovWord;

    @FXML
    private TextField markovLength;

    @FXML
    private void handleExit() throws IOException {
    	int option = 0;
    	if((textArea.getText() != "") || (FileUtilities.getPath()!=null)) {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setHeaderText("Save the current file before closing?");
    		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CLOSE, ButtonType.CANCEL);
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.orElse(ButtonType.YES)== ButtonType.YES) option = 1;
    		if (result.orElse(ButtonType.CLOSE)== ButtonType.CLOSE) option = 2;
    		if (result.orElse(ButtonType.CANCEL)== ButtonType.CANCEL) option = 3;
    	}
    	if (option == 1) {
    		saveFile(new ActionEvent());
    	}
    	if (option != 3) {
    		exitProgram(new ActionEvent());
    	}
    }
    
    @FXML
    void closeFileMenu(ActionEvent event) {
    	
    }

    @FXML
    void createNew(ActionEvent event) {
    	textArea.setText("");
    	FileUtilities.setPath(null);
    	undoStack.clear();
    }

    @FXML
    void createText(ActionEvent event) {
    	mg.generateRandomWord();
    	String rnd = mg.getRandomWord();
    		String tempStr = markovLength.getText().replaceAll("[^0-9]", "");
    		if (!(tempStr.equals(""))){
    			int length = Integer.parseInt(tempStr);
    			displayArea.setText(mg.generateMarkov(length, rnd, rnd));
    		} else {
    			displayArea.setText(mg.generateMarkov(20, rnd, rnd));
    		}
    	markovLength.setText("");
    	clock.resetTime();
    	textArea.setText("");
    	wordCountField.setText("Word Count = " + sb.updateWordCount(textArea.getText()));
    	misspelledArea.setText("");
    	timeField.setText("Time: " + clock.getTime());
    }

    @FXML
    void exitProgram(ActionEvent event){
    		System.exit(0);
    }

    @FXML
    void learnFile(ActionEvent event) throws IOException {
    	Stage stage = (Stage) displayArea.getScene().getWindow();
    	File file = FileUtilities.openLearningFile(stage);
    	mg.setPath(file);
    }

    @FXML
    void openFile(ActionEvent event) throws IOException {
    	Stage stage = (Stage) displayArea.getScene().getWindow();
    	File file = FileUtilities.openFile(stage);
    	if (file!=null) {
    		displayArea.setText(FileUtilities.readFile(file));
    	}
    	undoStack.clear();
    	undoState = "";
    }

    @FXML
    void saveAsFile(ActionEvent event) throws IOException {
    	Stage stage = (Stage) textArea.getScene().getWindow();
    	FileUtilities.saveAsFile(textArea.getText(), stage);
    }

    @FXML
    void saveFile(ActionEvent event) throws IOException {
    	Stage stage = (Stage) textArea.getScene().getWindow();
    	String text = textArea.getText();
    	FileUtilities.saveFile(text, stage);
    	
    }

    @FXML
    void undoAction(ActionEvent event) {
    	undoState="";
    	if (undoStack.isEmpty()==false) {
    		textArea.setText(undoStack.pop());
    	} else {
    		textArea.setText("");
    	}
    	textArea.end();
    }

    @FXML
    void update(KeyEvent event) {
    	if ((undoState!=null) && (undoState!="")) {
    		undoStack.push(undoState);
    	}
    	undoState= textArea.getText();
    	
    	updatePercentCount(new ActionEvent());
		updateWordCount(new ActionEvent());
		updateTime(new ActionEvent());
		updateSpellCheck(new ActionEvent());
    }

	@FXML
    void updateTime(ActionEvent event) {
		timeField.setText("Time: " + clock.getTime());
    }

    @FXML
    void updatePercentCount(ActionEvent event) {
    	percentCountField.setText("% Correct: " + ttc.testCheck(displayArea.getText(), textArea.getText()));
    }

    @FXML
    void updateSpellCheck(ActionEvent event) {
    	String text = textArea.getText();
    	String misspelled = sc.spellCheck(text);
    	misspelledArea.setText(misspelled);
    }

    @FXML
    void updateWordCount(ActionEvent event) {
    	wordCountField.setText("Word Count = " + sb.updateWordCount(textArea.getText()));
    }
    
    @Override
	public void initialize(URL url, ResourceBundle rb) {
    	try {
    		textArea.setWrapText(true);
    		undoState = null;
    		undoStack = new Stack<String>();
    		sb = new StatusBar();
    		sc = new SpellChecker();
    		mg = new MarkovGenerator();
    		mg.setPath(new File("data/warAndPeace.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
