package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilities.AccountUtilities;

public class SignInController implements Initializable{

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    
    @FXML
    private Text errMessageSignIn;

    @FXML
    void changeSceneSignUp(ActionEvent event) throws IOException {
    	Parent signUpRoot = FXMLLoader.load(getClass().getResource("/views/RegisterView.fxml"));
		Scene signUpScene = new Scene(signUpRoot);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(signUpScene);
		window.show();
    }

    @FXML
    void verifyAccount(ActionEvent event) throws IOException {
    	if (AccountUtilities.loginQuery(username.getText(), password.getText())) {
    		Parent primaryRoot = FXMLLoader.load(getClass().getResource("/views/PrimaryView.fxml"));
			Scene primaryScene = new Scene(primaryRoot);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(primaryScene);
			window.show();
    	} else {
    		errMessageSignIn.setVisible(true);
    	}
    }
    
    @Override
	public void initialize(URL url, ResourceBundle rb) {
    	try {
			AccountUtilities.loadAccountStore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}

