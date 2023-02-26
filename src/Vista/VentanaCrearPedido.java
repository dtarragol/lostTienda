package Vista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Controlador.*;
import javafx.stage.Stage;

public class VentanaCrearPedido implements Initializable {
    @FXML
    private TextField txtCantidadP, txtCantidadE;
    @FXML
    private Button btnCrearPP,btnCrearPE, btnSalir;
    @FXML
    private ComboBox comboBoxClientePremium,comboBoxArticuloPremium, comboBoxClienteEstandar, ComboBoxArticuloEstandar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Controller c = new Controller();
        c.AgregarClientesPremiumComboBox(comboBoxClientePremium);
        c.AgregarClientesEstandarComboBox(comboBoxClienteEstandar);
        c.AgregarArticulosComboBox(comboBoxArticuloPremium);
        c.AgregarArticulosComboBox(ComboBoxArticuloEstandar);
    }
    @FXML
    private void crearPE (javafx.event.ActionEvent event){
        Controller c = new Controller();
        c.CrearPedidosE(comboBoxClienteEstandar,ComboBoxArticuloEstandar,txtCantidadE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Se ha almacenado el Pedido.");
        alert.showAndWait();
    }
    @FXML
    private void crearPP (javafx.event.ActionEvent event){
        Controller c = new Controller();
        c.CrearPedidosP(comboBoxClientePremium,comboBoxArticuloPremium,txtCantidadP);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Se ha almacenado el Pedido.");
        alert.showAndWait();
    }
    @FXML
    private void clickSalir (javafx.event.ActionEvent event){
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
