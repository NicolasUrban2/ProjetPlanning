package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EventComponentController implements Initializable {
    @FXML
    private Label type;

    @FXML
    private Label subject;

    @FXML
    private Label room;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setSubject(String subject) {
        this.subject.setText(subject);
    }

    public void setRoom(String room) {
        this.room.setText(room);
    }

    public String getType() {
        return this.type.getText();
    }

    public String getSubject() {
        return this.subject.getText();
    }

    public String getRoom() {
        return this.room.getText();
    }
}
