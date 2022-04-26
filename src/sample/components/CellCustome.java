package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.ClientesDAO;
import sample.views.ClientesForms;
import java.util.Optional;

public class CellCustome extends TableCell<ClientesDAO, String> {
    private Button btnCelda;
    private  int opc;
    private ClientesDAO objPDAO;

    public CellCustome(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                objPDAO = CellCustome.this.getTableView().getItems().get(CellCustome.this.getIndex());
                new ClientesForms(CellCustome.this.getTableView(), objPDAO);
            });
        }else{
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar de la acción");
                alerta.setContentText("¿Realmente deseas borrar este producto?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    objPDAO = CellCustome.this.getTableView().getItems().get(CellCustome.this.getIndex());
                    objPDAO.ELIMINAR();
                    CellCustome.this.getTableView().setItems(objPDAO.SELECCIONAR());
                    CellCustome.this.getTableView().refresh();
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