package ORM;

import Modelo.Articulos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrmArticulos {

    Scanner input = new Scanner(System.in);

    public OrmArticulos() {

    }

    public Articulos crearArticulo(String codigo, String descripcion, Double precioVenta, Double envio, int tiempo) {   // metodo que crea un cliente premium



        Articulos articulo = new Articulos(codigo, descripcion, precioVenta, envio, tiempo);

        return articulo;
    }
    public void insArticulo(Articulos articulo) {    /**Metodo que agrega un articulo */
        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        try {
            actualSession.beginTransaction();
            actualSession.save(articulo);
            actualSession.getTransaction().commit();
            System.out.println("Articulo guardado");
        } catch (Exception e) {


            System.out.println("error al guardar el articulo");
        } finally {
            actualSession.close();

        }
    }

    public List<Articulos> Articulos(){  // para mostrar los articulos
        
        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        
        try {
            actualSession.beginTransaction();
            List<Articulos> articulos = actualSession.createQuery("from Articulos").getResultList();
            return articulos;
        } finally {
            actualSession.close();
        }
    }


    public Articulos ormObtenerArticulo(String codigo){

        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        try {
            actualSession.beginTransaction();
            List<Articulos> arti = actualSession.createQuery("from Articulos").getResultList();
            for(Articulos articulo: arti){
                System.out.println(articulo);
                if(articulo.getCodigo().equals(codigo)){

                    return articulo;
                }
            }
        } finally {
            actualSession.close();
        }
        return null;
    }

}