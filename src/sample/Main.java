package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.models.Conexion;
import sample.models.ConexionMySQL;
import sample.views.MenuTablas;

public class Main extends Application implements EventHandler{

    private Scene escena;
    private VBox vBox;
    private Label lblTexto;
    private Button btnMaria, btnMysql;
    private HBox hBox;
    public static int BaseDeDatos = 0;

    @Override
    public void start(Stage primaryStage){

        //primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWING,this);
        CrearUI();
        primaryStage.setScene(escena);
        primaryStage.setTitle("Bases De Datos");
        primaryStage.show();
    }

    private void CrearUI() {

        vBox = new VBox();
        hBox = new HBox();
        btnMaria = new Button("MariDB");
        btnMysql = new Button("MySQL");
        lblTexto = new Label("CON CUAL BASE SE DATOS DESEAS INGRESAR?");

        vBox.setAlignment(Pos.TOP_CENTER);
        hBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        hBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 5, 5, 5));
        lblTexto.setFont(Font.font("Candara", 20));
        btnMaria.setFont(Font.font("Candara", 15));
        btnMysql.setFont(Font.font("Candara", 15));

        btnMaria.setOnAction(event -> {
            Conexion.crearConexion();
            new MenuTablas();
            BaseDeDatos = 1;
        });

        btnMysql.setOnAction(event -> {
            ConexionMySQL.crearConexionMySQL();
            BaseDeDatos = 2;
        });

        btnMysql.setOnAction(event -> Conexion.crearConexion());
        hBox.getChildren().addAll(btnMaria, btnMysql);
        vBox.getChildren().addAll(lblTexto, hBox);
        escena = new Scene(vBox,450,100);

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {
        Alert alerta  = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Bienvenido");
        alerta.setHeaderText("Mensaje Del Sistema");
        alerta.setContentText("Manejo De Eventos De La Ventana Usando Dialogos");
        alerta.showAndWait();
    }
}
