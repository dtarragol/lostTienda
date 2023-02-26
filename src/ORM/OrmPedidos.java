package ORM;


import Controlador.Controller;
import Modelo.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrmPedidos {

    public OrmPedidos() {
    }

    public void insPedido(Pedido ped){ /**Metodo usado para insertar el objeto de tipo pedido en la BBDD con ORM */

        SessionFactory ormSessions = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).buildSessionFactory();
        Session actualSession = ormSessions.openSession();

        try {

            actualSession.beginTransaction();
            actualSession.save(ped);
            actualSession.getTransaction().commit();
            System.out.println("Pedido guardado exitosamente");
        }catch(Exception e){

            System.out.println("Error al guardar el Pedido");

        } finally {
            actualSession.close();
        }
    }

    public void ormBorrarPedidos(int idPedido) {   /** Metodo que borra un pedido */

        SessionFactory ormSessions = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).buildSessionFactory();
        Session actualSessions = ormSessions.openSession();
        Pedido ped;
        try {

            actualSessions.beginTransaction();
            ped = actualSessions.get(Pedido.class, idPedido);
            actualSessions.delete(ped);
            actualSessions.getTransaction().commit();
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle("Info");
            alert2.setContentText("Se ha ELIMINADO el Pedido "+idPedido);
            alert2.showAndWait();

        } finally {
            actualSessions.close();
        }
    }
    public void ormImprimirPedidosEnviados(TextArea txt) {  /** Imprime una lista de todos los pedidos */

        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        SessionFactory ormSession2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos.class).buildSessionFactory();
        Session actualSession2 = ormSession.openSession();
        Session actualSession3 = ormSession2.openSession();
        try {
            actualSession.beginTransaction();
            System.out.println("Empezando");
            List<Pedido> pedidos = actualSession.createQuery("from Pedido").getResultList();
            List<Articulos> articulos = actualSession3.createQuery("from Articulos").getResultList();
            actualSession3.beginTransaction();
            System.out.println(pedidos);
            for (Pedido pe : pedidos){

                String codArticulo = pe.getCodArticulo();

                for (Articulos articulo : articulos){
                    if(articulo.getCodigo().equals(codArticulo)){
                        pe.setArticulo(articulo);
                        pe.setEnviado(pe.pedidoEnviado(pe.getFechaHora(),pe.getArticulo().getTiempoDePreparacion()));
                    }
                }
            }

            List<Pedido> pe2= new ArrayList<>();
            for (Pedido pe : pedidos) {
                if (pe.pedidoEnviado(pe.getFechaHora(),pe.getArticulo().getTiempoDePreparacion())){
                    pe2.add(pe);
                }
            }
            if(!pe2.isEmpty()) {
                String delim = "\n";
                StringBuilder sb = new StringBuilder();

                int i = 0;
                while (i < pe2.size() - 1) {
                    sb.append(pe2.get(i));
                    sb.append(delim);
                    i++;
                }
                sb.append(pe2.get(i));

                String res = sb.toString();
                System.out.println("Enviados: \n" + res);
                txt.setText(res);
                actualSession.getTransaction().commit();
            }
        } finally {
            actualSession.close();
            actualSession2.close();
            actualSession3.close();
        }

    }
    public List<Pedido> PedidosNoEnviados(){
        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        SessionFactory ormSession2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos.class).buildSessionFactory();
        Session actualSession2 = ormSession.openSession();
        Session actualSession3 = ormSession2.openSession();
        try {
            actualSession.beginTransaction();
            System.out.println("Empezando");
            List<Pedido> pedidos = actualSession.createQuery("from Pedido").getResultList();
            List<Articulos> articulos = actualSession3.createQuery("from Articulos").getResultList();
            actualSession3.beginTransaction();
            System.out.println(pedidos);
            for (Pedido pe : pedidos){

                String codArticulo = pe.getCodArticulo();

                for (Articulos articulo : articulos){
                    if(articulo.getCodigo().equals(codArticulo)){
                        pe.setArticulo(articulo);
                        pe.setEnviado(pe.pedidoEnviado(pe.getFechaHora(),pe.getArticulo().getTiempoDePreparacion()));
                    }
                }
            }

            List<Pedido> pe2= new ArrayList<>();
            for (Pedido pe : pedidos) {
                if (!pe.pedidoEnviado(pe.getFechaHora(),pe.getArticulo().getTiempoDePreparacion())){
                    System.out.println("NO ENVIADO:\n"+ pe);
                    pe2.add(pe);
                }
            }
                return pe2;
        } finally {
            actualSession.close();
            actualSession2.close();
            actualSession3.close();
        }
    }
    public void ormImprimirPedidosNoEnviados(TextArea txt) {  /** Imprime una lista de todos los pedidos */

        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        SessionFactory ormSession2 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos.class).buildSessionFactory();
        Session actualSession2 = ormSession.openSession();
        Session actualSession3 = ormSession2.openSession();
        try {
            actualSession.beginTransaction();
            System.out.println("Empezando");
            List<Pedido> pedidos = actualSession.createQuery("from Pedido").getResultList();
            List<Articulos> articulos = actualSession3.createQuery("from Articulos").getResultList();
            actualSession3.beginTransaction();
            System.out.println(pedidos);
            for (Pedido pe : pedidos){

                String codArticulo = pe.getCodArticulo();

                for (Articulos articulo : articulos){
                    if(articulo.getCodigo().equals(codArticulo)){
                        pe.setArticulo(articulo);
                        pe.setEnviado(pe.pedidoEnviado(pe.getFechaHora(),pe.getArticulo().getTiempoDePreparacion()));
                    }
                }
            }

            List<Pedido> pe2= new ArrayList<>();
            for (Pedido pe : pedidos) {
                if (!pe.pedidoEnviado(pe.getFechaHora(),pe.getArticulo().getTiempoDePreparacion())){
                    pe2.add(pe);
                }
            }
            if(!pe2.isEmpty()) {
                String delim = "\n";
                StringBuilder sb = new StringBuilder();

                int i = 0;
                while (i < pe2.size() - 1) {
                    sb.append(pe2.get(i));
                    sb.append(delim);
                    i++;
                }
                sb.append(pe2.get(i));

                String res = sb.toString();
                System.out.println("NO enviados: \n" + res);
                txt.setText(res);
                actualSession.getTransaction().commit();
            }
        } finally {
            actualSession.close();
            actualSession2.close();
            actualSession3.close();
        }

    }
}









