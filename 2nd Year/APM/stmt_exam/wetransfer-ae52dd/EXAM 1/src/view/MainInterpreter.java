package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainInterpreter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainLayout.fxml"));
        Scene scene = new Scene(root);
        mainStage.setTitle("Toy Language - Please select a program");
        mainStage.setScene(scene);
        mainStage.show();
    }

}
