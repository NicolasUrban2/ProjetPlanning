package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    private MainController mainController = MainController.getInstance();
    @FXML
    private Button loginButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onLoginButtonClick() {
        this.mainController.changeView("homePage");
    }
}
