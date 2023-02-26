package Vista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Controlador.*;

import javax.persistence.Column;
import javax.swing.*;

public class VentanaArticulos implements Initializable{
    String user;
    String pass;
    String codigo;
    String Descripcion;
    double precioDeVenta;
    double gastosDeEnvio;
    int tiempoDePreparacion;
    @FXML
    private TextField txtcodigo, txtDescripcion, txtprecioDeVenta, txtgastosDeEnvio,txttiempoDePreparacion;
    @FXML
    private Button btn1,btn2;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void click01 (ActionEvent event){
        codigo = txtcodigo.getText();
        Descripcion = txtDescripcion.getText();
        precioDeVenta = Double.parseDouble(txtprecioDeVenta.getText());
        gastosDeEnvio = Double.parseDouble(txtgastosDeEnvio.getText());
        tiempoDePreparacion = Integer.parseInt(txttiempoDePreparacion.getText());
        Controller c = new Controller();
        c.OrmAddArticulo(codigo, Descripcion, precioDeVenta, gastosDeEnvio,tiempoDePreparacion);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Se ha almacenado el articulo.");
        alert.showAndWait();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Ventana01.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("Ventana01");
            stage.setScene(scene);
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
        }
    }

    @FXML
    private void click02 (ActionEvent event){
        Stage stage = (Stage) this.btn2.getScene().getWindow();
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
