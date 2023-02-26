package Vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import Controlador.*;
import javafx.stage.Stage;

public class VentanaEliminarPedido implements Initializable {
    @FXML
    private ComboBox comboPedidos;
    @FXML
    private Button btnEliminar, btnCancelar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Controller c = new Controller();
        c.AgregarPedidosComboBox(comboPedidos);
    }
    @FXML
    private void clickEliminar(ActionEvent event){
        Controller c = new Controller();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("ELIMINANDO Pedido sin enviar...");
        alert.setContentText("¿Esta seguro/a que desea eliminar este pedido?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){
            c.eliminarPedidos(comboPedidos);
        }
    }
    @FXML
    private void clickSalir (ActionEvent event){
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
        try {
            Ventana01 v = new Ventana01();
            Parent root = FXMLLoader.load(v.getClass().getResource("Ventana01.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("GRUPO LOST");
            stage.setScene(scene);
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
        }
    }
}
