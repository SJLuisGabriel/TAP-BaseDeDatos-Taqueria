package sample.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.components.CellCustomeOrdenes;
import sample.models.OrdenesDAO;

public class OrdenesBD extends Stage {

    private Scene escena;
    private TableView<OrdenesDAO> tbvOrdenes;
    private Button btnAgregar;
    private VBox vBox;
    private OrdenesDAO ordDAO;

    public OrdenesBD(){
        ordDAO = new OrdenesDAO();
        CrearUI();
        this.setTitle("Ordenes Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tbvOrdenes = new TableView<>();
        btnAgregar = new Button("Nueva Orden");
        ordDAO = new OrdenesDAO();
        btnAgregar.setOnAction(event -> {
            new OrdenesForms(tbvOrdenes, null);
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbvOrdenes,btnAgregar);
        escena = new Scene(vBox,585,300);
        CrearTabla();
    }

    private void CrearTabla() {
        TableColumn<OrdenesDAO,Integer> tbcIdOrdenes = new TableColumn<>("ID");
        tbcIdOrdenes.setCellValueFactory(new PropertyValueFactory<>("cveorden"));

        TableColumn<OrdenesDAO,Double> tbcMontoOrden = new TableColumn<>("Monto Orden");
        tbcMontoOrden.setCellValueFactory(new PropertyValueFactory<>("montoorden"));
        tbcMontoOrden.setMinWidth(100);

        TableColumn<OrdenesDAO,Double> tbcFechaOrden = new TableColumn<>("Fecha De Orden");
        tbcFechaOrden.setCellValueFactory(new PropertyValueFactory<>("fechaorden"));
        tbcFechaOrden.setMinWidth(100);

        TableColumn<OrdenesDAO,Integer> tbcClaveEmp = new TableColumn<>("Clave Empleado");
        tbcClaveEmp.setCellValueFactory(new PropertyValueFactory<>("claveemp"));
        tbcClaveEmp.setMinWidth(100);

        TableColumn<OrdenesDAO,Integer> tbcClaveCliente = new TableColumn<>("Clave Cliente");
        tbcClaveCliente.setCellValueFactory(new PropertyValueFactory<>("cvecte"));
        tbcClaveCliente.setMinWidth(100);

        TableColumn<OrdenesDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<OrdenesDAO, String>, TableCell<OrdenesDAO, String>>() {
            @Override
            public TableCell<OrdenesDAO, String> call(TableColumn<OrdenesDAO, String> param) {
                return new CellCustomeOrdenes(1);
            }
        });

        TableColumn<OrdenesDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(new Callback<TableColumn<OrdenesDAO, String>, TableCell<OrdenesDAO, String>>() {
            @Override
            public TableCell<OrdenesDAO, String> call(TableColumn<OrdenesDAO, String> param) {
                return new CellCustomeOrdenes(2);
            }
        });

        tbvOrdenes.getColumns().addAll(tbcIdOrdenes,tbcMontoOrden,tbcFechaOrden,tbcClaveEmp,tbcClaveCliente,
                tbcEditar,tbcBorrar);
        tbvOrdenes.setItems(ordDAO.SELECCIONAROrdenes());
    }
}
