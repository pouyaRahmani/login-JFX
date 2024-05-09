module com.ikiu.loginjfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ikiu.loginjfx to javafx.fxml;
    exports com.ikiu.loginjfx;
}