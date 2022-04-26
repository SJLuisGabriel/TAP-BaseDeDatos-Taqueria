package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.OrdenesDAO;
import sample.views.OrdenesForms;

import java.util.Optional;

public class CellCustomeOrdenes extends TableCell<OrdenesDAO, String> {
    private Button btnCelda;
    private  int opc;
    private OrdenesDAO objOrdDAO;

    public CellCustomeOrdenes(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                objOrdDAO = CellCustomeOrdenes.this.getTableView().getItems().get(CellCustomeOrdenes.this.getIndex());
                new OrdenesForms(CellCustomeOrdenes.this.getTableView(), objOrdDAO);
            });
        }else{
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje Del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Esta Orden?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    objOrdDAO = CellCustomeOrdenes.this.getTableView().getItems().get(CellCustomeOrdenes.this.getIndex());
                    objOrdDAO.ELIMINAROrdenes();
                    CellCustomeOrdenes.this.getTableView().setItems(objOrdDAO.SELECCIONAROrdenes());
                    CellCustomeOrdenes.this.getTableView().refresh();
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