package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.StageStyle;

public class LevelChoosing extends Pane {
    public LevelChoosing(){
        setPrefSize(1200, 700);
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Label label = new Label("Choose the difficulty level(1, 2, 3):");
        label.setAlignment(Pos.CENTER);
        label.setTranslateX(0);
        label.setTranslateY(250);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        label.setTextFill(Color.LIGHTSALMON);
        label.setEffect(new DropShadow(40, Color.LIGHTSKYBLUE));
        label.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setPrefSize(1200, 80);

        TextField field = new TextField("1");
        field.setTranslateX(0);
        field.setTranslateY(350);
        field.setAlignment(Pos.CENTER);
        field.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        field.setEffect(new DropShadow(40, Color.LIGHTSKYBLUE));
        field.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        field.setPrefSize(1200, 50 );

        Button button = new Button("ENTER");
        button.setAlignment(Pos.CENTER);
        button.setTranslateX(0);
        button.setTranslateY(420);
        button.setPrefSize(1200, 40);
        button.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        button.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        button.setEffect(new DropShadow(40, Color.CORAL));
        this.getChildren().addAll(label, field, button);
        button.setOnAction(actionEvent -> {
            switch (field.getText()) {
                case "1":
                case "2":
                case "3":
                    GameRoot.level = Integer.parseInt(field.getText());
                    Main.scene.gameRoot = new GameRoot();
                    Main.scene.setRoot(Main.scene.gameRoot);
                    Main.scene.buttonPressed();
                    break;
                default:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("ILLEGAL VALUE EXCEPTION");
                    alert.setHeaderText(null);
                    alert.setContentText("ONLY VALUES '1', '2', '3' ARE POSSIBLE!!!");
                    alert.showAndWait();
                    Main.scene.setRoot(new LevelChoosing());
                    break;
            }
        });
    }
}
