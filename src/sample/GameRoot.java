package sample;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class GameRoot extends Pane {
    static Wolf wolf;
    static Line floor;
    static int level;

    ImageView brokEgg;
    ImageView collectedEgg;
    AnimationTimer EggsCounter;
    Label brokenEggsInfo = new Label();
    Label caughtEggsInfo = new Label();



    public static class Wolf extends Circle {
        Wolf(double x, double y){
            ImagePattern pattern = new ImagePattern(new Image("sample/images/wolf.png"));
            this.setFill(pattern);
            this.setRadius(150);
            this.setEffect(new DropShadow(8, Color.rgb(203,250, 76, 0.9)));
            setTranslateX(x);
            setTranslateY(y);
            setRotationAxis(Rotate.Y_AXIS);
        }
        void moveWolfLeft(){
            if (getTranslateX() > 180.0){
                setTranslateX(getTranslateX() - 30.0);
            }
            setRotate(180);
        }
        void moveWolfRight(){
            if (getTranslateX() < 970.0){
                setTranslateX(getTranslateX() + 30.0);
            }
            setRotate(0);
        }
    }

    public void setLabels(){
        this.brokenEggsInfo.setTranslateX(560);
        this.brokenEggsInfo.setTranslateY(20);
        this.brokenEggsInfo.setPrefSize(100, 50);
        this.brokenEggsInfo.setText("" + Egg.broken);
        this.brokenEggsInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 33));
        this.brokenEggsInfo.setTextFill(Color.LIGHTSALMON);

        this.caughtEggsInfo.setTranslateX(685);
        this.caughtEggsInfo.setTranslateY(20);
        this.caughtEggsInfo.setPrefSize(100, 50);
        this.caughtEggsInfo.setText("" + Egg.caught);
        this.caughtEggsInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 33));
        this.caughtEggsInfo.setTextFill(Color.MEDIUMSLATEBLUE);
        this.getChildren().addAll(this.brokenEggsInfo, this.caughtEggsInfo);
    }

    public void setBackground(){
        ImageView im = new ImageView("sample/images/img.png");
        im.setFitWidth(1200);
        im.setFitHeight(700);
        getChildren().add(im);
    }
    public void updateLabels(){
        this.brokenEggsInfo.setText("" + Egg.broken);
        this.caughtEggsInfo.setText("" + Egg.caught);
    }

    public void createEggPictures(){
        this.brokEgg = new ImageView("sample/images/разбитоеяйцо.png");
        this.brokEgg.setFitWidth(45);
        this.brokEgg.setFitHeight(50);
        this.brokEgg.setTranslateX(500);
        this.brokEgg.setTranslateY(20);

        this.collectedEgg = new ImageView("sample/images/собряй.png");
        this.collectedEgg.setFitWidth(50);
        this.collectedEgg.setFitHeight(50);
        this.collectedEgg.setTranslateX(620);
        this.collectedEgg.setTranslateY(20);
        this.getChildren().addAll(this.brokEgg,  this.collectedEgg);
    }

    public void launchTimer(){
        this.EggsCounter = new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateLabels();
            }
        };
        this.EggsCounter.start();
    }

    public void kuricy(){
        ImageView k1 = new ImageView("sample/images/img_2.png");
        ImageView k4 = new ImageView("sample/images/img_2.png");

        k1.setFitWidth(200);
        k4.setFitWidth(200);
        k1.setFitHeight(200);
        k4.setFitHeight(200);

        k1.setTranslateX(0);
        k1.setTranslateY(0);
        k4.setTranslateX(1000);
        k4.setTranslateY(0);
        k4.setRotationAxis(Rotate.Y_AXIS);
        k4.setRotate(180);

        getChildren().addAll(k1, k4);
    }

    public void createControlButtons(){
        MenuButton left = new MenuButton("<<<", () -> wolf.moveWolfLeft());
        MenuButton right = new MenuButton(">>>", () -> wolf.moveWolfRight());
        left.setPrefSize(150, 50);
        left.setTranslateX(450);
        left.setTranslateY(100);
        left.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        right.setPrefSize(150, 50);
        right.setTranslateX(620);
        right.setTranslateY(100);
        this.getChildren().addAll(left, right);
    }

    public GameRoot() {
        setBackground();
        kuricy();
        createEggPictures();
        setLabels();
        launchTimer();
        setPrefSize(1200, 700);
        floor = new Line(0, 585.0, 1200, 585.0);
        floor.setStroke(Color.CHARTREUSE);
        floor.setStrokeWidth(15);
        floor.setEffect(new Bloom(1.0));
        getChildren().add(floor);
        wolf = new Wolf(600, 550.0);
        getChildren().add(wolf);
        egging();
        createControlButtons();
    }

    public void egging() {
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(0), e -> this.getChildren().add(new Egg(1)));
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(2), e -> this.getChildren().add(new Egg(2)));
        final KeyFrame kf3 = new KeyFrame(Duration.seconds(0), e -> this.getChildren().add(new Egg(3)));
        final KeyFrame kf4 = new KeyFrame(Duration.seconds(2), e -> this.getChildren().add(new Egg(4)));
        final Timeline timeline = new Timeline(kf1,kf2, kf3 , kf4);
        Platform.runLater(timeline::play);
    }

}
