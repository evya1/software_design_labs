package org.example.hello_jfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello World!");
        TextField helloTF = new TextField("");
        Button btn = new Button();
        btn.setText("Say 'Hello'");
        btn.setOnAction((event) -> {
            helloTF.setText("Hello World!");
        });

        StackPane root = new StackPane();
        StackPane.setAlignment(helloTF, Pos.TOP_CENTER);
        root.getChildren().addAll(helloTF, btn);

        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }
}
