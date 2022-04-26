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
import sample.components.CellCustomePlatillos;
import sample.models.PlatillosDAO;

import static sample.Main.BaseDeDatos;

public class PlatillosBD extends Stage {

    private Scene escena;
    private TableView<PlatillosDAO> tbvPlatillos;
    private Button btnAgregar;
    private VBox vBox;
    private PlatillosDAO pltDAO;

    public PlatillosBD(){
        pltDAO = new PlatillosDAO();
        CrearUI();
        this.setTitle("Platillos Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbvPlatillos = new TableView<>();
        btnAgregar = new Button("Nuevo Platillo");
        pltDAO = new PlatillosDAO();
        btnAgregar.setOnAction(event -> {
           new PlatillosForms(tbvPlatillos, null);
            System.out.println(BaseDeDatos);
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbvPlatillos,btnAgregar);
        escena = new Scene(vBox,710,300);
        CrearTabla();
    }

    private void CrearTabla() {
        TableColumn<PlatillosDAO,Integer> tbcIdPlatillos = new TableColumn<>("ID");
        tbcIdPlatillos.setCellValueFactory(new PropertyValueFactory<>("cveplatillo"));

        TableColumn<PlatillosDAO,String> tbcNomPlatillo = new TableColumn<>("Nombre Platillo");
        tbcNomPlatillo.setCellValueFactory(new PropertyValueFactory<>("nomplatillo"));
        tbcNomPlatillo.setMinWidth(110);

        TableColumn<PlatillosDAO,Double> tbcCostPlatillo = new TableColumn<>("Costo Platillo");
        tbcCostPlatillo.setCellValueFactory(new PropertyValueFactory<>("costo"));
        tbcCostPlatillo.setMinWidth(110);

        TableColumn<PlatillosDAO,String> tbcTamioPlatillo = new TableColumn<>("Tamaño Platillo");
        tbcTamioPlatillo.setCellValueFactory(new PropertyValueFactory<>("tamaio"));
        tbcTamioPlatillo.setMinWidth(110);

        TableColumn<PlatillosDAO,Double> tbcPrecioPlatillo = new TableColumn<>("Precio Platillo");
        tbcPrecioPlatillo.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tbcPrecioPlatillo.setMinWidth(110);

        TableColumn<PlatillosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
            @Override
            public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                return new CellCustomePlatillos(1);
            }
        });

        TableColumn<PlatillosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
            @Override
            public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                return new CellCustomePlatillos(2);
            }
        });


        tbvPlatillos.getColumns().addAll(tbcIdPlatillos,tbcNomPlatillo,tbcCostPlatillo,tbcTamioPlatillo,
                tbcPrecioPlatillo,tbcEditar,tbcBorrar);
        tbvPlatillos.setItems(pltDAO.SELECCIONARPlatillos());

    }
}