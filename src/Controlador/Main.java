package Controlador;
import Modelo.*;
import Vista.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;



public class Main extends Application {
    static String user;
    static String pass;
    @Override
    public void start(Stage primaryStage) throws Exception {

        Ventana01 v = new Ventana01();
        Parent root = FXMLLoader.load(v.getClass().getResource("Ventana01.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("GRUPO LOST");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}




