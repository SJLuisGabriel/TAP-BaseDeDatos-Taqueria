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
import sample.models.PlatillosDAO;

public class PlatillosForms extends Stage {

    private Scene escena;
    private VBox vBox,vBox1, vBox2;
    private HBox hBox;
    private Label lblNombrePlatillo,lblCosto,lblTamio,lblPrecio;
    private TextField txtNombrePlatillo,txtCosto,txtTamaio,txtPrecio;
    private Button btnGuardar;
    private PlatillosDAO objPltDAO;
    private TableView<PlatillosDAO> tbvPlatillos;

    public PlatillosForms(TableView<PlatillosDAO> tbvPlatillos, PlatillosDAO objPltDAO){
        this.tbvPlatillos = tbvPlatillos;
        if( objPltDAO != null )
            this.objPltDAO = objPltDAO;             // La acción es una actualización
        else
            this.objPltDAO = new PlatillosDAO();  // La acción es una inserción
        CrearUI();
        this.setTitle("Formulario De Clientes");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNombrePlatillo = new TextField();
        txtNombrePlatillo.setPromptText("Platillo");
        txtNombrePlatillo.setText(objPltDAO.getNomplatillo());
        lblNombrePlatillo = new Label("Nombre Del Platillo");
        txtNombrePlatillo.setMinWidth(230);

        txtCosto = new TextField();
        txtCosto.setPromptText("Costo");
        txtCosto.setText(String.valueOf(objPltDAO.getCosto()));
        lblCosto = new Label("Costo Del Platillo");

        txtTamaio = new TextField();
        txtTamaio.setPromptText("C(Chico) - M(Mediano) - G(Grande)");
        txtTamaio.setText(objPltDAO.getTamaio());
        lblTamio = new Label("Tamaño Del Platillo");

        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio");
        txtPrecio.setText(String.valueOf(objPltDAO.getPrecio()));
        lblPrecio = new Label("Precio Del Platillo");

        btnGuardar = new Button("Guardar");

        btnGuardar.setOnAction(event -> {
            objPltDAO.setNomplatillo(txtNombrePlatillo.getText());
            objPltDAO.setPrecio(Double.parseDouble(txtCosto.getText()));
            objPltDAO.setTamaio(txtTamaio.getText());
            objPltDAO.setPrecio(Double.parseDouble(txtCosto.getText()));

            if( objPltDAO.getCveplatillo() > 0 )
                objPltDAO.ACTUALIZARPlatillos();
            else
                objPltDAO.INSERTARPlatilos();

            tbvPlatillos.setItems(objPltDAO.SELECCIONARPlatillos());
            tbvPlatillos.refresh();

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
        vBox1.getChildren().addAll(lblNombrePlatillo,lblCosto,lblTamio,lblPrecio);
        vBox2.getChildren().addAll(txtNombrePlatillo,txtCosto,txtTamaio,txtPrecio);
        hBox.getChildren().addAll(vBox1,vBox2);
        vBox.getChildren().addAll(hBox,btnGuardar);
        escena = new Scene(vBox,400 ,250);
    }
}