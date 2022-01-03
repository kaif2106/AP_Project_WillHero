module plswrk.willherofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens plswrk.willherofx to javafx.fxml;
    exports plswrk.willherofx;
}