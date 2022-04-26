package sample.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.EmpleadosDAO;

public class EmpleadosForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtNombre,txtApeMaterno,txtApePaterno,txtRFC,txtSueldo,txtTelefono,txtNSS,txtEmail;
    private Button btnGuardar;
    private EmpleadosDAO objEmpDAO;
    private TableView<EmpleadosDAO> tbvEmpleados;

    public EmpleadosForms(TableView<EmpleadosDAO> tbvEmpleados, EmpleadosDAO objEmpDAO){
        this.tbvEmpleados = tbvEmpleados;
        if( objEmpDAO != null )
            this.objEmpDAO = objEmpDAO;          // La acción es una actualización
        else
            this.objEmpDAO = new EmpleadosDAO();  // La acción es una inserción
        CrearUI();
        this.setTitle("Formulario De Empleados");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Del Empleado");
        txtNombre.setText(objEmpDAO.getNomemp());

        txtApePaterno = new TextField();
        txtApePaterno.setPromptText("Apellido Paterno");
        txtApePaterno.setText(objEmpDAO.getApepatemp());

        txtApeMaterno = new TextField();
        txtApeMaterno.setPromptText("Apellido Materno");
        txtApeMaterno.setText(objEmpDAO.getApematemp());

        txtRFC = new TextField();
        txtRFC.setPromptText("RFC Del Empleado");
        txtRFC.setText(objEmpDAO.getRfcemp());

        txtSueldo = new TextField();
        txtSueldo.setPromptText("Sueldo Del Empleado");
        txtSueldo.setText(String.valueOf(objEmpDAO.getSueldo()));

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono Del Empleado");
        txtTelefono.setText(objEmpDAO.getTelfono());

        txtNSS = new TextField();
        txtNSS.setPromptText("NSS Del Empleado");
        txtNSS.setText(objEmpDAO.getNssemp());

        txtEmail = new TextField();
        txtEmail.setPromptText("Email Del Empleado");
        txtEmail.setText(objEmpDAO.getEmailemp());

        btnGuardar = new Button("Guardar");

        btnGuardar.setOnAction(event -> {
            objEmpDAO.setNomemp(txtNombre.getText());
            objEmpDAO.setApepatemp(txtApePaterno.getText());
            objEmpDAO.setApematemp(txtApeMaterno.getText());
            objEmpDAO.setRfcemp(txtRFC.getText());
            objEmpDAO.setSueldo(Double.parseDouble(txtSueldo.getText()));
            objEmpDAO.setTelfono(txtTelefono.getText());
            objEmpDAO.setNssemp(txtNSS.getText());
            objEmpDAO.setEmailemp(txtEmail.getText());

            if( objEmpDAO.getClaveemp() > 0 )
                objEmpDAO.ACTUALIZAREmpleados();
            else
                objEmpDAO.INSERTAREmpleados();

            tbvEmpleados.setItems(objEmpDAO.SELECCIONAREmpleados());
            tbvEmpleados.refresh();

            this.close();
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(txtNombre,txtApePaterno,txtApeMaterno,txtRFC,txtSueldo,txtTelefono,
                txtNSS,txtEmail,btnGuardar);
        escena = new Scene(vBox,300 ,330);
    }
}