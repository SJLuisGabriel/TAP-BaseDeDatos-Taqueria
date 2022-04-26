package sample.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuTablas extends Stage {

    private Scene escena;
    private VBox vBox;
    private HBox hBox1,hBox2,hBox3;
    private Button btnClientes,btnEmpleados,btnOrdenes,btnPlatillos,btnDetPlatillo,btnDetOrden,btnInsumos;

    public MenuTablas(){
        CrearUI();
        this.setTitle("Menu De La Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        btnClientes = new Button("Tabla Clientes");
        btnEmpleados = new Button("Tabla Empleados");
        btnOrdenes = new Button("Tabla Ordenes");
        btnPlatillos = new Button("Tabla Platillos");
        btnDetOrden = new Button("Tabla Detalle De Orden");
        btnDetPlatillo = new Button("Tabla Detalle De Platillos");
        btnInsumos = new Button("Tabla Insumos");

        btnClientes.setOnAction(event -> new ClientesBD());
        btnEmpleados.setOnAction(action -> new EmpleadosBD());
        btnOrdenes.setOnAction(action -> new OrdenesBD());
        btnPlatillos.setOnAction(action -> new PlatillosBD());
       // btnDetOrden.setOnAction(action -> );
       // btnDetPlatillo.setOnAction(action -> );
       // btnInsumos.setOnAction(action -> );

        vBox = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(20);
        hBox1.setSpacing(20);
        hBox2.setSpacing(20);
        hBox3.setSpacing(20);
        hBox1.getChildren().addAll(btnClientes,btnEmpleados,btnOrdenes);
        hBox2.getChildren().addAll(btnPlatillos,btnDetOrden,btnInsumos);
        hBox3.getChildren().addAll(btnDetPlatillo);
        vBox.getChildren().addAll(hBox1,hBox2,hBox3);
        escena = new Scene(vBox,420,150);
    }
}
