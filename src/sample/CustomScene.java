package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class CustomScene extends Scene {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;
    GameRoot gameRoot;
    LevelChoosing levelChoosing;
    VBox menuBox;
    List<Pair<String, Runnable>> menuButtons;

    public void initialization(){
        levelChoosing = new LevelChoosing();
        menuBox = new VBox(5);
        menuButtons = Arrays.asList(
                new Pair<>("НОВАЯ ИГРА - NEW GAME", () -> {
                    levelChoosing = new LevelChoosing();
                    setRoot(levelChoosing);

                }),
                new Pair<>("РЕКОРДЫ - RECORDS", Records::new),
                new Pair<>("ВЫХОД - EXIT", () -> System.exit(1))
        );
    }

    public CustomScene(Parent parent) {
        super(parent);
        initialization();
        this.setRoot(createContent());
    }
    private VBox setMenu(){
        menuBox.setTranslateX(400.0);
        menuBox.setTranslateY(285.0);
        menuButtons.forEach(data -> menuBox.getChildren().add(new MenuButton(data.getKey(), data.getValue())));
        return menuBox;
    }

    public void buttonPressed(){
        final KeyCombination shortcut = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_ANY, KeyCombination.SHIFT_ANY);
        this.setOnKeyPressed( e -> {
            if (shortcut.match(e)) {
                initialization();
                this.setRoot(createContent());
            } else {
                switch (e.getCode()) {
                    case A:
                    case KP_LEFT:
                        GameRoot.wolf.moveWolfLeft();
                        break;
                    case D:
                    case KP_RIGHT:
                        GameRoot.wolf.moveWolfRight();
                        break;
                }
            }

        });
    }

    private Parent createContent(){
        ImageView bg = new ImageView("sample/images/bg.png");
        bg.setFitHeight(HEIGHT);
        bg.setFitWidth(WIDTH);
        Pane root = new Pane();
        root.getChildren().add(bg);
        root.getChildren().add(setMenu());
        return root;
    }
}
