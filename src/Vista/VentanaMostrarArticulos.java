package Vista;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import Controlador.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class VentanaMostrarArticulos implements Initializable {
    @FXML
    private TextArea txtArticulos;
    @FXML
    private Button btnSalir;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Controller c = new Controller();
        List articulos = c.MostarArticulo();

        String delim = "\n";

        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < articulos.size() - 1)
        {
            sb.append(articulos.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(articulos.get(i));

        String res = sb.toString();
        txtArticulos.setText(res);
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
        List articulos = c.MostarArticulo();

        String delim = "\n";

        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < articulos.size() - 1)
        {
            sb.append(articulos.get(i));
            sb.append(delim);
            i++;
        }
        sb.append(articulos.get(i));

        String res = sb.toString();
        txtArticulos.setText(res);
    }

}