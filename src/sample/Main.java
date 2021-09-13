package sample;
import javafx.application.Application;import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

    static CustomScene scene;
    public void start (Stage primaryStage){
        scene = new CustomScene(new Pane());
        primaryStage.setTitle("Ну погоди!!!");
        primaryStage.setScene(scene);
        primaryStage.show();
        new Controller();
    }

    public static void main (String[] args){
        Player.deserialize();
        launch(args);
    }

}