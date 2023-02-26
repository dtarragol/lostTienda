package Controlador;

import ORM.*;
import Modelo.*;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Controller {


    /** Metodo Constructor de la Clase
     *
     */


    public Controller() {
    }
/*
//PARA PEDIR USUARIO Y CONTRASEÑA DE LA BBDD
    public String USUARIO(){
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(null);
        tid.setTitle("USUARIO");
        tid.setContentText("Introduce el usuario: ");
        Optional<String> us = tid.showAndWait();
        user=us.get();
        return user;
    }
    public String PASSWORD(){
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(null);
        tid.setTitle("USUARIO");
        tid.setContentText("Introduce el password: ");
        Optional<String> ps = tid.showAndWait();
        pass=ps.get();
        return pass;
    }
*/
    public void AgregarClientesPremiumComboBox(ComboBox comboBox){
        List<Clientes> cli = ormMostarClientesP();
        for (Clientes clientes: cli){
            comboBox.getItems().add(clientes.getNif());
        }
    }
    public void AgregarClientesEstandarComboBox(ComboBox comboBox){
        List<Clientes> cli = ormMostarClientesE();
        for (Clientes clientes: cli){
            comboBox.getItems().add(clientes.getNif());
        }
    }
    public void AgregarArticulosComboBox(ComboBox comboBox){
        List<Articulos> arti = MostarArticulo();
        for (Articulos articulos: arti){
            comboBox.getItems().add(articulos.getCodigo());
        }
    }

    public void AgregarPedidosComboBox(ComboBox comboBox){

        OrmPedidos pedidos= new OrmPedidos();
        List<Pedido> P= pedidos.PedidosNoEnviados();
        if (P!=null){
            for (Pedido pedido: P){
                comboBox.getItems().add(pedido.getNumeroPedido());
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("No se encuentran pedidos sin enviar.");
            alert.showAndWait();
        }
    }
    public void OrmAddArticulo(String codigo, String descripcion, Double precioVenta, Double envio, int tiempo){
        OrmArticulos articulo= new OrmArticulos();
        Articulos articulos= articulo.crearArticulo( codigo,  descripcion,  precioVenta,  envio,  tiempo);
        articulo.insArticulo(articulos);
    }

    public List<Articulos> MostarArticulo(){ /** Metodo que muestra loas articulos usando Hibernate */

        OrmArticulos articulos= new OrmArticulos();

        List<Articulos> arti= articulos.Articulos();
        return arti;
    }
    public List<Clientes> ormMostarClientesP(){ /** Metodo que muestra todos los clientes de ls BBDD con Hibernate */
        OrmCliente clientes= new OrmCliente();
        List<Clientes> clients= clientes.ClientesP();
        return clients;
    }
    public List<Clientes> ormMostarClientesE(){ /** Metodo que muestra todos los clientes de ls BBDD con Hibernate */
        OrmCliente clientes= new OrmCliente();
        List<Clientes> clients= clientes.ClientesE();
        //clientes.ormImprimirClientes();
        return clients;
    }


    /** METODO QUE AGREGA UN CLIENTE USANDO HIBERNATE */
    public void ormAddClientesPremium(String nif, String nombre, String direccion, String email, float cuotaMensual, float descuento){
        System.out.println("Que tipo de cliente deseas insertar?");
        OrmCliente cliente= new OrmCliente();
        ClientesPremium clientesPremium = cliente.menuInsClientePremium(nif, nombre,  direccion,  email,  cuotaMensual,  descuento);
        cliente.insClientePr(clientesPremium);
    }
    public void ormAddClientesStandar(String nif, String nombre, String direccion, String email){
        System.out.println("Que tipo de cliente deseas insertar?");
        OrmCliente cliente= new OrmCliente();
        ClientesEstandar clientesEstandar = cliente.menuInsClienteStandar(nif, nombre,  direccion,  email);
        cliente.insClienteEs(clientesEstandar);
    }
    public void CrearPedidosP(ComboBox comboboxCliente, ComboBox comboboxArticulo, TextField textField){
        String nifCl;
        int cantArticulo;
        double prTotal, gastEnv;
        float des;
        LocalDateTime fecha;
        String codArticulo;
        Pedido pe;
        OrmPedidos pedido= new OrmPedidos();
        OrmArticulos articulo= new OrmArticulos();
        OrmCliente cliente= new OrmCliente();

        /** Obtencion del nif del cliente */
        nifCl= comboboxCliente.getSelectionModel().getSelectedItem().toString();

        /** Obtencion del codigo del Articulos */
        codArticulo= comboboxArticulo.getSelectionModel().getSelectedItem().toString();

        /** Obtencion de los gastos de envio */

        gastEnv=articulo.ormObtenerArticulo(codArticulo).getGastosDeEnvio();

        /** Obtencion de Descuento del cliente */
        des = cliente.clienteP(nifCl).getDescuento();

        /** Obtencion de la cantidad de Articulos */
        cantArticulo=Integer.parseInt(textField.getText());

        /** Creacion del pedido con metodo constructor */
        pe= new Pedido(nifCl,codArticulo,cantArticulo);

        /** Calculo del precio total del envio */
        prTotal= (articulo.ormObtenerArticulo(codArticulo).getPrecioDeVenta()* cantArticulo) + ((1-(des / 100)) * gastEnv);
        pe.setPrecioTotal2(prTotal);
        fecha=LocalDateTime.now();
        pe.setFechaHora(fecha);
        pedido.insPedido(pe);
    }
    public void eliminarPedidos(ComboBox comboBox){
        int numeroPedido;
        numeroPedido=Integer.parseInt(comboBox.getSelectionModel().getSelectedItem().toString());
        OrmPedidos OP = new OrmPedidos();
        OP.ormBorrarPedidos(numeroPedido);
    }


    public void CrearPedidosE(ComboBox comboboxCliente, ComboBox comboboxArticulo, TextField textField){
        String nifCl;
        int cantArticulo;
        double prTotal, gastEnv;

        LocalDateTime fecha;
        String codArticulo;
        Pedido pe;
        OrmPedidos pedido= new OrmPedidos();
        OrmArticulos articulo= new OrmArticulos();

        /** Obtencion del nif del cliente */
        nifCl= comboboxCliente.getSelectionModel().getSelectedItem().toString();

        /** Obtencion del codigo del Articulos */
        codArticulo= comboboxArticulo.getSelectionModel().getSelectedItem().toString();

        /** Obtencion de los gastos de envio */
        gastEnv=articulo.ormObtenerArticulo(codArticulo).getGastosDeEnvio();

        /** Obtencion de la cantidad de Articulos */
        cantArticulo=Integer.parseInt(textField.getText());

        /** Creacion del pedido con metodo constructor */
        pe= new Pedido(nifCl,codArticulo,cantArticulo);

        /** Calculo del precio total del envio */
        prTotal= (articulo.ormObtenerArticulo(codArticulo).getPrecioDeVenta()* cantArticulo) + gastEnv;
        pe.setPrecioTotal2(prTotal);
        fecha=LocalDateTime.now();
        pe.setFechaHora(fecha);
        pedido.insPedido(pe);
    }

    public void ormImprimirPedidosEnviados(TextArea txt) {  /** Imprime una lista de todos los pedidos */

        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        SessionFactory ormSession2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos.class).buildSessionFactory();
        Session actualSession2 = ormSession.openSession();
        Session actualSession3 = ormSession2.openSession();
        try {
            actualSession.beginTransaction();

            List<Pedido> pedidos = actualSession.createQuery("from Pedido").getResultList();

            for (Pedido pe : pedidos){
                actualSession2.beginTransaction();
                String codigoArticulo= actualSession2.createQuery("from Pedido").toString();
                System.out.println(codigoArticulo);
                actualSession3.beginTransaction();
                List<Articulos> articulos = actualSession3.createQuery("from Articulos").getResultList();
                for (Articulos articulo : articulos){
                    if(articulo.getCodigo().equals(codigoArticulo)){
                        pe.setArticulo(articulo);
                        pe.setEnviado(pe.pedidoEnviado(pe.getFechaHora(),pe.getArticulo().getTiempoDePreparacion()));
                    }
                }
            }
            List<Pedido> pe2=null;
            for (Pedido pe : pedidos) {
                if (pe.getEnviado()){
                    pe2.add(pe);
                }
            }
            String delim = "\n";

            StringBuilder sb = new StringBuilder();

            int i = 0;
            while (i < pe2.size() - 1)
            {
                sb.append(pe2.get(i));
                sb.append(delim);
                i++;
            }
            sb.append(pe2.get(i));

            String res = sb.toString();
            txt.setText(res);
            actualSession.getTransaction().commit();
        } finally {
            actualSession.close();
            actualSession2.close();
            actualSession3.close();
        }
    }





}