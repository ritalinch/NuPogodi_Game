package sample;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Egg extends ImageView {
    int gutter;
    static int broken = 0;
    static int caught = 0;
    AnimationTimer check;

    Egg(int gutter){
        super("sample/images/яЙЦО8бит.png");
        this.gutter = gutter;
        setFitWidth(78);
        setFitHeight(120);
        switch(gutter){
            case 1:
                setTranslateX(20);
                setTranslateY(20);
                break;
            case 2:
                setTranslateX(1200);
                setTranslateY(20);
                break;
            case 3:
                setTranslateX(20);
                setTranslateY(235);
                break;
            case 4:
                setTranslateX(1299);
                setTranslateY(235);
                break;
        }
        launchCheckTimer();
        check.start();
        startParallelTransition();
    }

     public void launchCheckTimer(){
        this.check = new AnimationTimer() {
            @Override
             public void handle(long timestamp) {
                if(Egg.this.getBoundsInParent().intersects(GameRoot.floor.getBoundsInParent())){
                    Main.scene.gameRoot.getChildren().remove(Egg.this);
                    int tmp = Egg.this.gutter;
                    Main.scene.gameRoot.getChildren().add(new Egg(tmp));
                    final KeyFrame kf = new KeyFrame(Duration.seconds(10000),
                            e -> Main.scene.gameRoot.getChildren().add(new Egg(tmp)));
                    final Timeline timeline = new Timeline(kf);
                    Platform.runLater(timeline::play);
                    broken++;
                    stop();
                }
                else if(Egg.this.getBoundsInParent().intersects(GameRoot.wolf.getBoundsInParent())){
                    Main.scene.gameRoot.getChildren().remove(Egg.this);
                    int tmp = Egg.this.gutter;
                    Main.scene.gameRoot.getChildren().add(new Egg(tmp));
                    final KeyFrame kf = new KeyFrame(Duration.seconds(10000),
                            e -> Main.scene.gameRoot.getChildren().add(new Egg(tmp)));
                    final Timeline timeline = new Timeline(kf);
                    Platform.runLater(timeline::play);
                    caught++;
                    stop();
                }
            }
        };
    }

    public PathTransition createPathTransition(){
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000 - 2000*GameRoot.level));
        pathTransition.setPath(this.gutterPath());
        pathTransition.setNode(this);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        return pathTransition;
    }

    public RotateTransition createRotateTransition(){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(2500), this);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(8);
        return rotateTransition;
    }

    public void startParallelTransition(){
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(createPathTransition(), createRotateTransition());
        parallelTransition.play();
    }


    public Path gutterPath(){
        Path path = new Path();
        switch(gutter){
            case 1:
                path.getElements().add(new MoveTo(20, 20));
                path.getElements().add(new CubicCurveTo(200, 200, 240, 300, 350, 350));
                path.getElements().add(new CubicCurveTo(400, 420, 400, 560, 450, 650));
            case 2:
                path.getElements().add(new MoveTo(1200, 20));
                path.getElements().add(new CubicCurveTo(878, 200, 838, 300, 728, 350));
                path.getElements().add(new CubicCurveTo(728, 420, 678, 560, 678, 650));
            case 3:
                path.getElements().add(new MoveTo(20, 235));
                path.getElements().add(new CubicCurveTo(200, 435, 400, 535, 450, 650));
                break;
            case 4:
                path.getElements().add(new MoveTo(1200, 235));
                path.getElements().add(new CubicCurveTo(878, 435, 778, 535, 728, 650));
                break;
        }
        return path;
    }
}