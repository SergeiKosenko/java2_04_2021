package ru.kosenko.chat.client;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.kosenko.common.ChatMessage;
import ru.kosenko.common.MessageType;
import ru.kosenko.network.ChatMessageService;
import ru.kosenko.network.ChatMessageServiceImpl;
import ru.kosenko.network.MessageProcessor;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainChatController implements Initializable, MessageProcessor {


    public TextArea chatArea;
    public ListView onlineUsers;
    public TextField inputField;
    public Button btnSendMessage;
    public TextField loginField;
    public PasswordField passwordField;
    public Button btnSendAuth;
    private ChatMessageService messageService;
    private String currentName;

    public void mockAction(ActionEvent actionEvent) {
        System.out.println("MOCK!");
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void showHelp(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI("https://docs.google.com/document/d/1wr0YEtIc5yZtKFu-KITqYnBtp8KC28v2FEYUANL0YAM/edit?usp=sharing"));
    }

    public void sendMessage(ActionEvent actionEvent) {
        String text = inputField.getText();
        if (text.isEmpty()) return;
        ChatMessage msg = new ChatMessage();
        msg.setMessageType(MessageType.PUBLIC);
        msg.setFrom(currentName);
        msg.setBody(text);
        messageService.send(msg.marshall());
        inputField.clear();
    }

    private void appendTextFromTF(ChatMessage msg) {
//        String msg = inputField.getText();
//        if (msg.isEmpty()) return;
        String text = String.format("[%s] %s\n", msg.getFrom(), msg.getBody());
        chatArea.appendText(text);
//        inputField.clear();
    }

    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong!");
        alert.setHeaderText(e.getMessage());
        VBox dialog = new VBox();
        Label label = new Label("Trace:");
        TextArea textArea = new TextArea();
        //TODO
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement el : e.getStackTrace()) {
            builder.append(el).append(System.lineSeparator());
        }
        textArea.setText(builder.toString());
        dialog.getChildren().addAll(label, textArea);
        alert.getDialogPane().setContent(dialog);
        alert.showAndWait();
    }

    public void showAbout(ActionEvent actionEvent)  {

        Label secondLabel = new Label("Сетевой чат\nJava 2\ngb.ru\n2021");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene aboutScene = new Scene(secondaryLayout, 230, 100);

        Stage aboutWindow = new Stage();
        aboutWindow.setTitle("About");
        aboutWindow.setScene(aboutScene);

        Stage aboutStage = new Stage();
        aboutWindow.setX(aboutStage.getX() + 200);
        aboutWindow.setY(aboutStage.getY() + 100);

        aboutWindow.show();
    }
//        Alert alert = new Alert(INFORMATION);
//        alert.setTitle("О программе");
//        alert.setHeaderText(null);
//        alert.setContentText("Сетевой чат версия 1.0\nВсе права защищены ");
//        alert.showAndWait();


//        final Stage dialog = new Stage();
//        dialog.initModality(Modality.APPLICATION_MODAL);
//        VBox dialogVbox = new VBox(40);
//        dialogVbox.getChildren().add(new TextArea("\n      Сетевой чат версия 1.0\n       Все права защищены"));
//        Scene dialogScene = new Scene(dialogVbox, 200, 100);
//        dialog.centerOnScreen();
//        dialogScene.getClass().getResource("/about.fxml");
//        dialog.setScene(dialogScene);
//        dialog.showAndWait();


//        FXMLLoader aboutLoader = new FXMLLoader(getClass().getResource("/about.fxml"));
//        Parent root1 = aboutLoader.load();
//        Scene scene1 = new Scene(root1);
//        Stage stage1 = new Stage();
//        stage1.setScene(scene1);
//        stage1.toFront();
//        stage1.show();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.messageService = new ChatMessageServiceImpl("localhost", 8282, this);
        messageService.connect();
    }

    @Override
    public void processMessage(String msg) {
        Platform.runLater(() -> {
                    ChatMessage message = ChatMessage.unmarshall(msg);
                    System.out.println("Received message");

                    switch (message.getMessageType()) {
                        case PUBLIC :
                            appendTextFromTF(message);
                            break;
                        case CLIENT_LIST :
                            refreshOnlineUsers(message);
                            break;
                        case AUTH_CONFIRM :
                        {
                            this.currentName = message.getBody();
                            App.stage1.setTitle(currentName);
                        }
                        break;
                    }
                }
        );
    }


    private void refreshOnlineUsers(ChatMessage message) {
        this.onlineUsers.setItems(FXCollections.observableArrayList(message.getOnlineUsers()));
    }

    public void sendAuth(ActionEvent actionEvent) {
        String log = loginField.getText();
        String pass = passwordField.getText();
        if (log.isEmpty() || pass.isEmpty()) return;
        ChatMessage msg = new ChatMessage();
        msg.setMessageType(MessageType.SEND_AUTH);
        msg.setLogin(log);
        msg.setPassword(pass);
        messageService.send(msg.marshall());
    }
}
