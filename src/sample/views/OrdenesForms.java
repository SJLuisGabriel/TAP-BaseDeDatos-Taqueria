package sample.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.OrdenesDAO;

public class OrdenesForms extends Stage {

    private Scene escena;
    private VBox vBox,vBox1, vBox2;
    private HBox hBox;
    private TextField txtMontoOrden,txtFechaOrden,txtClaveEmp,txtClaveCliente;
    private Label lblMontoOrden,lblFechaOrden,lblClaveEmp,lblClaveCliente;
    private Button btnGuardar;
    private OrdenesDAO objOrdDAO;
    private TableView<OrdenesDAO> tbvOrdenes;

    public OrdenesForms(TableView<OrdenesDAO> tbvOrdenes, OrdenesDAO objOrdDAO){
        this.tbvOrdenes = tbvOrdenes;
        if( objOrdDAO != null )
            this.objOrdDAO = objOrdDAO;             // La acción es una actualización
        else
            this.objOrdDAO = new OrdenesDAO();  // La acción es una inserción
        CrearUI();
        this.setTitle("Formulario De Ordenes");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtMontoOrden = new TextField();
        txtMontoOrden.setPromptText("");
        txtMontoOrden.setText(String.valueOf(objOrdDAO.getMontoorden()));
        lblMontoOrden = new Label("Monto De La Orden");

        txtFechaOrden = new TextField();
        txtFechaOrden.setPromptText("YYYY-MM-DD");
        txtFechaOrden.setText(objOrdDAO.getFechaorden());
        lblFechaOrden = new Label("Fecha De La Orden");

        txtClaveCliente = new TextField();
        txtClaveCliente.setPromptText("");
        txtClaveCliente.setText(String.valueOf(objOrdDAO.getCvecte()));
        lblClaveCliente = new Label("Clave Del Cliente");

        txtClaveEmp = new TextField();
        txtClaveEmp.setPromptText("");
        txtClaveEmp.setText(String.valueOf(objOrdDAO.getClaveemp()));
        lblClaveEmp = new Label("Clave Del Empleado");

        btnGuardar = new Button("Guardar");

        btnGuardar.setOnAction(event -> {
            objOrdDAO.setMontoorden(Double.parseDouble(txtMontoOrden.getText()));
            objOrdDAO.setFechaorden(txtFechaOrden.getText());
            objOrdDAO.setCvecte(Integer.parseInt(txtClaveCliente.getText()));
            objOrdDAO.setClaveemp(Integer.parseInt(txtClaveEmp.getText()));

            if( objOrdDAO.getCveorden() > 0 )
                objOrdDAO.ACTUALIZAROrdenes();
            else
                objOrdDAO.INSERTAROrdenes();

            tbvOrdenes.setItems(objOrdDAO.SELECCIONAROrdenes());
            tbvOrdenes.refresh();

            this.close();
        });

        vBox = new VBox();
        vBox1 = new VBox();
        vBox2 = new VBox();
        hBox = new HBox();
        vBox1.setSpacing(7);
        vBox1.setPadding(new Insets(10.0));
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10.0));
        vBox1.getChildren().addAll(lblMontoOrden,lblFechaOrden,lblClaveCliente,lblClaveEmp);
        vBox2.getChildren().addAll(txtMontoOrden,txtFechaOrden,txtClaveCliente,txtClaveEmp);
        hBox.getChildren().addAll(vBox1,vBox2);
        vBox.getChildren().addAll(hBox,btnGuardar);
        escena = new Scene(vBox,300 ,250);
    }
}