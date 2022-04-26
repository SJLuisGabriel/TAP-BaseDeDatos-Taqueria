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
import sample.components.CellCustome;
import sample.components.CellCustomeEmpleados;
import sample.models.ClientesDAO;
import sample.models.EmpleadosDAO;

public class EmpleadosBD extends Stage {

    private Scene escena;
    private TableView<EmpleadosDAO> tbvEmpleado;
    private Button btnAgregar;
    private VBox vBox;
    private EmpleadosDAO EmpDAO;

    public EmpleadosBD(){
        EmpDAO = new EmpleadosDAO();
        CrearUI();
        this.setTitle("Empleados Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbvEmpleado = new TableView<>();
        btnAgregar = new Button("Nuevo Empleado");
        EmpDAO = new EmpleadosDAO();
        btnAgregar.setOnAction(event -> {
            new EmpleadosForms(tbvEmpleado, null);
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbvEmpleado,btnAgregar);
        escena = new Scene(vBox,980,300);
        CrearTabla();
    }

    private void CrearTabla() {
        TableColumn<EmpleadosDAO,Integer> tbcIdEmpleado = new TableColumn<>("ID");
        tbcIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("claveemp"));

        TableColumn<EmpleadosDAO,String> tbcNomEmpleado = new TableColumn<>("NOMBRE");
        tbcNomEmpleado.setCellValueFactory(new PropertyValueFactory<>("nomemp"));

        TableColumn<EmpleadosDAO,String> tbcApeMatEmpleado = new TableColumn<>("APELLIDO MATERNO");
        tbcApeMatEmpleado.setCellValueFactory(new PropertyValueFactory<>("apematemp"));
        tbcApeMatEmpleado.setMinWidth(130);

        TableColumn<EmpleadosDAO,String> tbcApePatEmpleado = new TableColumn<>("APELLIDO PATERNO");
        tbcApePatEmpleado.setCellValueFactory(new PropertyValueFactory<>("apepatemp"));
        tbcApePatEmpleado.setMinWidth(130);

        TableColumn<EmpleadosDAO,String> tbcRFCEmpleado = new TableColumn<>("RFC");
        tbcRFCEmpleado.setCellValueFactory(new PropertyValueFactory<>("rfcemp"));

        TableColumn<EmpleadosDAO,Double> tbcSueldoEmpleado = new TableColumn<>("SUELDO");
        tbcSueldoEmpleado.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        TableColumn<EmpleadosDAO,String> tbcTelfono = new TableColumn<>("TELEFONO");
        tbcTelfono.setCellValueFactory(new PropertyValueFactory<>("telfono"));

        TableColumn<EmpleadosDAO,String> tbcNSSEmpleado = new TableColumn<>("NSS");
        tbcNSSEmpleado.setCellValueFactory(new PropertyValueFactory<>("nssemp"));

        TableColumn<EmpleadosDAO,String> tbcEmailEmpleado = new TableColumn<>("EMAIL");
        tbcEmailEmpleado.setCellValueFactory(new PropertyValueFactory<>("emailemp"));

        TableColumn<EmpleadosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
            @Override
            public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> param) {
                return new CellCustomeEmpleados(1);
            }
        });

        TableColumn<EmpleadosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
            @Override
            public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> param) {
                return new CellCustomeEmpleados(2);
            }
        });

        tbvEmpleado.getColumns().addAll(tbcIdEmpleado,tbcNomEmpleado,tbcApePatEmpleado,tbcApeMatEmpleado,
                tbcRFCEmpleado,tbcSueldoEmpleado,tbcTelfono,tbcNSSEmpleado,tbcEmailEmpleado,tbcEditar,tbcBorrar);
        tbvEmpleado.setItems(EmpDAO.SELECCIONAREmpleados());

    }
}
