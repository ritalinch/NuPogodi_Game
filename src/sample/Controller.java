package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Controller {
    AnimationTimer animationTimer;
    static long timeCounter;
    public Controller(){
        animationTimer = new AnimationTimer() {
            @Override
            public void start() {
                super.start();
                timeCounter = System.currentTimeMillis();
            }
            @Override
            public void handle(long l) {
                if (Egg.broken >= 4){
                    long now = System.currentTimeMillis();
                    timeCounter = (now - timeCounter) / 1000;
                    stopgame();
                    stop();
                }
            }
        };
        animationTimer.start();
    }
    public void stopgame(){
        Main.scene.setRoot(new gameOverRoot());
    }

    static class gameOverRoot extends Pane{
        gameOverRoot(){
            setPrefSize(1200, 700);
            setBackground(new Background(new BackgroundFill(new ImagePattern
                    (new Image("sample/images/заяц.png")), CornerRadii.EMPTY, Insets.EMPTY)));
            ImageView img = new ImageView("sample/images/img_1.png");
            img.setFitHeight(355);
            img.setFitWidth(600);
            img.setTranslateX(600);
            img.setTranslateY(0);

            Label label = new Label("Enter you name:");
            label.setTranslateX(750);
            label.setTranslateY(400);
            label.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
            label.setTextFill(Color.LIGHTSALMON);
            label.setEffect(new DropShadow(40, Color.LIGHTSKYBLUE));
            label.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

            TextField field = new TextField("player");
            field.setTranslateX(790);
            field.setTranslateY(462);
            field.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
            field.setEffect(new DropShadow(40, Color.LIGHTSKYBLUE));
            field.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            field.setPrefSize(200, 45 );

            Button button = new Button("SAVE AND EXIT");
            button.setTranslateX(790);
            button.setTranslateY(515);
            button.setPrefSize(200, 45);
            button.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            button.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
            button.setEffect(new DropShadow(40, Color.CORAL));
            button.setOnAction(actionEvent -> {
                new Player("" + field.getText());
                System.exit(1);
            });
            this.getChildren().addAll(img, label, field, button);
        }
    }
}
