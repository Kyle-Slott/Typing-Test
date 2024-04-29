package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilities.AccountUtilities;

public class RegisterController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordConfirm;

    @FXML
    private Text errMessagePassword;

    @FXML
    private Text errMessageUsername;
    
    @FXML
    private Text errMessagePasswordConfirm;

    @FXML
    private Text errMessageUsernameShort;

    @FXML
    void changeSceneSignIn(ActionEvent event) throws IOException {
    	Parent signInRoot = FXMLLoader.load(getClass().getResource("/views/SignInView.fxml"));
		Scene signInScene = new Scene(signInRoot);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(signInScene);
		window.show();
    }

    @FXML
    void registerAccount(ActionEvent event) throws IOException {
    	if (checkAccountValidity()) {
    		AccountUtilities.addAccount(username.getText(), password.getText());
			Parent primaryRoot = FXMLLoader.load(getClass().getResource("/views/PrimaryView.fxml"));
			Scene primaryScene = new Scene(primaryRoot);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(primaryScene);
			window.show();
    	}
    }
    
    private boolean checkAccountValidity(){
    	boolean validAccount = true;
    	errMessageUsernameShort.setVisible(false);
    	errMessagePasswordConfirm.setVisible(false);
    	errMessageUsername.setVisible(false);
    	errMessagePassword.setVisible(false);
    	
    	if (username.getText().length()<3) {
    		errMessageUsernameShort.setVisible(true); //display message saying username too short
    		validAccount = false;
    	} else {
			if (AccountUtilities.getAccounts().containsKey(username.getText())) {
				errMessageUsername.setVisible(true); //display message saying account already exists
				validAccount = false;
			} else {
				if (password.getText().equals(passwordConfirm.getText())==false) {
					errMessagePasswordConfirm.setVisible(true); // display message passwords don't match
					validAccount = false;
				} else {
					if (AccountUtilities.passwordRequirementTest(password.getText())==false) {
						errMessagePassword.setVisible(true); // display message password invalid
						validAccount = false;
					}
				}
			}
    	}
    	if (validAccount==false) {
    		password.setText("");
			passwordConfirm.setText("");
    	}
    	return validAccount;
    }
}
