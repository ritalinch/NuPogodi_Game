package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

class MenuButton extends Button{
    Runnable action;
    public MenuButton(String name, Runnable action) {
        this.action = action;
        setPrefSize(350,70);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 20));
        this.setTextFill(Color.BLACK);
        this.setOnMouseEntered(e -> this.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY))));
        this.setOnMouseExited(e -> this.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY))));
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setText(name);
        setOnAction(actionEvent -> action.run());
    }
}
