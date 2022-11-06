module acvl {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.security.jgss;
    requires json.simple;

    opens acvl.vue to javafx.fxml;
    exports acvl.vue;
    exports acvl.controller;
    opens acvl.controller to javafx.fxml;
    exports acvl.tools;
    opens acvl.tools to javafx.fxml;
}