package Vista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controlador.Controller;
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


public class VentanaCliente implements Initializable {

    @FXML
    private Button btnCrear,btnCrear2, btnSalir;
    @FXML
    private TextField txtnif, txtnombre, txtdomicilio, txtemail, txtcuotaMensual, txtdescuento,txtnif2, txtnombre2, txtdomicilio2, txtemail2;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void clickCrearClientesPremium (ActionEvent event){
        String nif = txtnif.getText();
        String nombre = txtnombre.getText();
        String domicilio = txtdomicilio.getText();
        String email = txtemail.getText();
        float cuotaMensual = Float.parseFloat(txtcuotaMensual.getText());
        float descuento = Float.parseFloat(txtdescuento.getText());

        Controller c = new Controller();
        c.ormAddClientesPremium(nif, nombre,  domicilio,  email,  cuotaMensual,  descuento);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Se ha almacenado el Cliente PREMIUM.");
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
    private void clickCrearClientesStandar (ActionEvent event){
        String nif = txtnif2.getText();
        String nombre = txtnombre2.getText();
        String domicilio = txtdomicilio2.getText();
        String email = txtemail2.getText();

        Controller c = new Controller();
        c.ormAddClientesStandar(nif, nombre,  domicilio,  email);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Se ha almacenado el Cliente STANDAR.");
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
}