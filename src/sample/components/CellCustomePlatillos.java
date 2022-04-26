package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.PlatillosDAO;
import sample.views.PlatillosForms;
import java.util.Optional;

public class CellCustomePlatillos extends TableCell<PlatillosDAO, String> {
    private Button btnCelda;
    private  int opc;
    private PlatillosDAO objPltDAO;

    public CellCustomePlatillos(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                objPltDAO = CellCustomePlatillos.this.getTableView().getItems().get(CellCustomePlatillos.this.getIndex());
                new PlatillosForms(CellCustomePlatillos.this.getTableView(), objPltDAO);
            });
        }else{
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje Del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Este Platillo?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    objPltDAO = CellCustomePlatillos.this.getTableView().getItems().get(CellCustomePlatillos.this.getIndex());
                    objPltDAO.ELIMINARPlatillos();
                    CellCustomePlatillos.this.getTableView().setItems(objPltDAO.SELECCIONARPlatillos());
                    CellCustomePlatillos.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item,boolean empty){
        super.updateItem(item,empty);
        if(!empty){
            setGraphic(btnCelda);
        }
    }
}