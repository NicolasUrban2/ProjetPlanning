module com.planning.projetplanning {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.planning.projetplanning to javafx.fxml;
    exports com.planning.projetplanning;
}