module com.github.stephenwanjala.postfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.apache.pdfbox;
    requires java.net.http;
    requires com.google.gson;
    requires retrofit2;
    requires retrofit2.converter.gson;


    opens com.github.stephenwanjala.postfx.domain.model to com.google.gson;
    opens com.github.stephenwanjala.postfx to javafx.fxml;
    exports com.github.stephenwanjala.postfx;
}