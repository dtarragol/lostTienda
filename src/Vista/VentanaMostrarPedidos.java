package Vista;

import Controlador.Controller;
import ORM.OrmArticulos;
import ORM.OrmCliente;
import ORM.OrmPedidos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaMostrarPedidos implements Initializable {
    @FXML
    private TextArea txtNoEnviados, txtEnviados;
    @FXML
    private Button btnSalir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        OrmPedidos OP= new OrmPedidos();
        OP.ormImprimirPedidosEnviados(txtEnviados);
        OP.ormImprimirPedidosNoEnviados(txtNoEnviados);
    }
    @FXML
    private void Salir (javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
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
