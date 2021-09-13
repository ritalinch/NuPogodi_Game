package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Records extends Stage {
    public Records(){
        ArrayList<String> list = new ArrayList<>();
        for(Player p : Player.players){ list.add(p.toString()); }
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(list);
        ScrollPane root = new ScrollPane();
        root.prefWidthProperty().bind(listView.widthProperty());
        root.prefHeightProperty().bind(listView.heightProperty());
        root.setContent(listView);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Group group = new Group();
        group.getChildren().add(root);
        Scene scene = new Scene(group, 400, 500);
        listView.setTranslateX(0);
        listView.setTranslateY(0);
        listView.setPrefWidth(400);
        listView.setPrefHeight(500);
        this.setResizable(false);
        this.setTitle("Ranking");
        this.setScene(scene);
        this.show();
    }
}
