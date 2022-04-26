package sample.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.ClientesDAO;

public class ClientesForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtNombre, txttelefono, txtdireccion;
    private Button btnGuardar;
    private ClientesDAO objCDAO;
    private TableView<ClientesDAO> tbvProductos;

    public ClientesForms(TableView<ClientesDAO> tbvProductos, ClientesDAO objCDAO){
        this.tbvProductos = tbvProductos;
        if( objCDAO != null )
            this.objCDAO = objCDAO;             // La acción es una actualización
        else
            this.objCDAO = new ClientesDAO();  // La acción es una inserción
        CrearUI();
        this.setTitle("Formulario De Clientes");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Del Cliente");
        txtNombre.setText(objCDAO.getNomcte());

        txttelefono = new TextField();
        txttelefono.setPromptText("Telefono Del Cliente");
        txttelefono.setText(objCDAO.getTelcte());

        txtdireccion = new TextField();
        txtdireccion.setPromptText("Direccion Del Cliente");
        txtdireccion.setText(objCDAO.getDircte());

        btnGuardar = new Button("Guardar");

        btnGuardar.setOnAction(event -> {
            objCDAO.setNomcte(txtNombre.getText());
            objCDAO.setTelcte(txttelefono.getText());
            objCDAO.setDircte(txtdireccion.getText());

            if( objCDAO.getCvecte() > 0 )
                objCDAO.ACTUALIZAR();
            else
                objCDAO.INSERTAR();

            tbvProductos.setItems(objCDAO.SELECCIONAR());
            tbvProductos.refresh();

            this.close();
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(txtNombre,txttelefono,txtdireccion,btnGuardar);
        escena = new Scene(vBox,300 ,250);
    }
}