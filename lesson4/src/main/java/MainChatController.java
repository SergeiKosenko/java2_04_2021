import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Text;

import java.awt.*;
import java.awt.Dialog;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class MainChatController {


    public TextArea chatArea;
    public TextArea chatArea1;
    public ListView onlineUsers;
    public TextField inputField;
    public Button btnSendMessage;

    public void mockAction(ActionEvent actionEvent) {
        System.out.println("MOCK!");
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void showAbout(ActionEvent actionEvent) throws Exception {

//        Alert alert = new Alert(INFORMATION);
//        alert.setTitle("О программе");
//        alert.setHeaderText(null);
//        alert.setContentText("Сетевой чат версия 1.0\nВсе права защищены ");
//        alert.showAndWait();

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(40);
        dialogVbox.getChildren().add(new TextArea("\n      Сетевой чат версия 1.0\n       Все права защищены"));
        Scene dialogScene = new Scene(dialogVbox, 200, 100);
        dialog.centerOnScreen();
        dialogScene.getClass().getResource("/about.fxml");
        dialog.setScene(dialogScene);
        dialog.showAndWait();



        FXMLLoader aboutLoader = new FXMLLoader(getClass().getResource("/about.fxml"));
        Parent root1 = aboutLoader.load();
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.toFront();
        stage1.show();
   }

    public void showHelp(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI("https://docs.google.com/document/d/1wr0YEtIc5yZtKFu-KITqYnBtp8KC28v2FEYUANL0YAM/edit?usp=sharing"));
    }

    public void sendMessage(ActionEvent actionEvent) {
        appendTextFromTF();
    }

    private void appendTextFromTF() {
        String msg = inputField.getText();
        if (msg.isEmpty()) return;
        chatArea.getClass().getResource("/style.css").toExternalForm();
        chatArea.getStyleClass().add("border-region");
        chatArea.appendText("Я: " + msg + System.lineSeparator());
        chatArea.getClass().getResource("/style.css").toExternalForm();
        chatArea.getStyleClass().add("border-region1");
        chatArea.appendText("                      Не я: " + msg + System.lineSeparator());
        inputField.clear();


//        chatArea1.getStyleClass().add("border-region1");
//        chatArea1.getClass().getResource("/style.css").toExternalForm();
//        chatArea1.getStyleClass().add("border-region1");
//        chatArea1.appendText("\nНе я: " + msg + System.lineSeparator());
//        inputField.clear();
    }

    public void exitAbout() {
    }
}
