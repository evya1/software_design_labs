module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.hello_jfx;
    opens org.example.hello_jfx to javafx.fxml;
}
