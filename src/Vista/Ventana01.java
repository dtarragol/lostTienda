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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Ventana01 implements Initializable {
    String user;
    String pass;
    @FXML
    private Button btnCrearArticulo,btnMostrarArticulos,btnCrearCliente, btnMostrarClientes, btnCrearPedido, BtnEliminarPedido, btnMostrarPedidos, btnSalir;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    @FXML
    private void clickCrearArticulos (ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            VentanaArticulos v = new VentanaArticulos();
            Parent root = FXMLLoader.load(v.getClass().getResource("VentanaArticulos.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("GRUPO LOST");
            stage.setScene(scene);
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
        }
    }
    @FXML
    private void clickMostrarArticulos (ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            VentanaMostrarArticulos v = new VentanaMostrarArticulos();
            Parent root = FXMLLoader.load(v.getClass().getResource("VentanaMostrarArticulos.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("GRUPO LOST - Mostrar articulos");
            stage.setScene(scene);
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
        }
    }
    @FXML
    private void clickCrearCliente (ActionEvent event){


        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            VentanaCliente v = new VentanaCliente();
            Parent root = FXMLLoader.load(v.getClass().getResource("VentanaCliente.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("GRUPO LOST - Crear cliente");
            stage.setScene(scene);
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
        }
    }
    @FXML
    private void clickMostrarClientes (ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            VentanaMostrarClientes v = new VentanaMostrarClientes();
            Parent root = FXMLLoader.load(v.getClass().getResource("VentanaMostrarClientes.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("GRUPO LOST - Mostrar clientes");
            stage.setScene(scene);
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
        }
    }
    @FXML
    private void clickCrearPedido (ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            VentanaCrearPedido v= new VentanaCrearPedido();
            Parent root = FXMLLoader.load(v.getClass().getResource("VentanaCrearPedido.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("GRUPO LOST - Crear pedido");
            stage.setScene(scene);
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
        }
    }
    @FXML
    private void clickEliminarPedido (ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            VentanaEliminarPedido v = new VentanaEliminarPedido();
            Parent root = FXMLLoader.load(v.getClass().getResource("VentanaEliminarPedido.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("GRUPO LOST - Borrar pedido");
            stage.setScene(scene);
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
        }
    }

    @FXML
    private void clickMostrarPedido (ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            VentanaMostrarPedidos v = new VentanaMostrarPedidos();
            Parent root = FXMLLoader.load(v.getClass().getResource("VentanaMostrarPedidos.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("GRUPO LOST - Mostrar pedido");
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
    }
}