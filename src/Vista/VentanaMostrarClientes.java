package Vista;

import Controlador.Controller;
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

public class VentanaMostrarClientes implements Initializable {
    @FXML
    private TextArea txtClientes, txtClientes1;
    @FXML
    private Button btnSalir,btnActualizar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Controller c = new Controller();
        List clientes = c.ormMostarClientesP();
        List clientes2 = c.ormMostarClientesE();
        String delim = "\n";

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        while (i < clientes.size() - 1)
        {
            sb.append(clientes.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(clientes.get(i));

        String res = sb.toString();
        txtClientes.setText(res);
        i = 0;
        while (i < clientes2.size() - 1)
        {
            sb2.append(clientes2.get(i));
            sb2.append(delim);
            i++;
        }
        sb2.append(clientes2.get(i));

        String res2 = sb2.toString();
        txtClientes1.setText(res2);
    }
    @FXML
    private void clickSalir (ActionEvent event){
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
    @FXML
    private void clickMostrar (ActionEvent event){
        Controller c = new Controller();
        List clientes = c.MostarArticulo();

        String delim = "\n";

        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < clientes.size() - 1)
        {
            sb.append(clientes.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(clientes.get(i));

        String res = sb.toString();
        txtClientes.setText(res);
    }
}
