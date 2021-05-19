import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scene.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);

        stage.setScene(scene);
//        stage.setAlwaysOnTop(true);
        stage.setTitle("April Chat");
        stage.show();


    }


//    public static void about() throws Exception {
//        final Stage dialog = new Stage();
//        dialog.initModality(Modality.APPLICATION_MODAL);
//        VBox dialogVbox = new VBox(20);
//        dialogVbox.getChildren();
//        Scene dialogScene = new Scene(dialogVbox, 300, 200);
//        dialog.setScene(dialogScene);
//        dialog.showAndWait();
//
//
//        FXMLLoader aboutLoader = new FXMLLoader(getClass().getResource("/about.fxml"));
//        Parent root1 = aboutLoader.load();
//        Scene scene1 = new Scene(root1);
//        Stage stage1 = new Stage();
//        stage1.setScene(scene1);
//        stage1.toFront();
//        stage1.show();
//    }

}
