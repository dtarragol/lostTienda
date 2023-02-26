package ORM;

import Modelo.Articulos;
import Modelo.Clientes;
import Modelo.ClientesEstandar;
import Modelo.ClientesPremium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class OrmCliente {

    Scanner input = new Scanner(System.in);

    /*public OrmCliente(String user, String pass) {

    }*/

    public ClientesPremium menuInsClientePremium(String nif, String nombre, String direccion, String email, float cuotaMensual, float descuento) {
        ClientesPremium cp = new ClientesPremium(nif, nombre, direccion, email, cuotaMensual, descuento);
        return cp;
    }
    public ClientesEstandar menuInsClienteStandar(String nif, String nombre, String direccion, String email) {
        ClientesEstandar ce = new ClientesEstandar(nif, nombre, direccion, email);
        return ce;
    }
    public List<Clientes> ClientesP(){
        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientesPremium.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        try {
            actualSession.beginTransaction();
            List<Clientes> clientes = actualSession.createQuery("from ClientesPremium where descuento!=0").getResultList();
            return clientes;
        } finally {
            actualSession.close();
        }
    }
    public Clientes clienteP(String nif){
        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientesPremium.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        try {
            actualSession.beginTransaction();
            List<Clientes> clientes = actualSession.createQuery("from ClientesPremium where descuento!=0").getResultList();
            for(Clientes cliente: clientes){
                if(cliente.getNif().equals(nif)){
                    return cliente;
                }
            }
        } finally {
            actualSession.close();
        }
        return null;
    }
    public List<Clientes> ClientesE(){
        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientesEstandar.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        try {
            actualSession.beginTransaction();
            List<Clientes> clientes = actualSession.createQuery("from ClientesEstandar where descuento=0").getResultList();
            return clientes;
        } finally {
            actualSession.close();
        }
    }

    public void insClientePr(ClientesPremium premium) {    /**Metodo que agrega un cliente premium */
        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientesPremium.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();
        try {
            actualSession.beginTransaction();
            actualSession.save(premium);
            actualSession.getTransaction().commit();
            System.out.println("Cliente premium guardado");
        } catch (Exception e) {


            System.out.println("error al guardar el cliente");
        } finally {
            actualSession.close();
        }
    }

    public void insClienteEs(ClientesEstandar estandar) {  /** Metodo que inserta un cliente estandar */
        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientesEstandar.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();

        try {
            actualSession.beginTransaction();
            actualSession.save(estandar);
            actualSession.getTransaction().commit();
            System.out.println("Cliente Estandar guardado");
        } catch (Exception e) {
            System.out.println("error al guardar el cliente");

        } finally {
            actualSession.close();
        }
    }
    public void ormImprimirClientes() {   /** Imprime todos los clientes de la base de datos */

        List<ClientesPremium> clienteP;
        List<ClientesEstandar> clienteEstandar;

        SessionFactory ormSession = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientesEstandar.class).buildSessionFactory();
        Session actualSession = ormSession.openSession();

        try {
            actualSession.beginTransaction();

            clienteEstandar = actualSession.createQuery("from ClientesEstandar where descuento=0").getResultList();
            actualSession.getTransaction().commit();
        } finally {
            actualSession.close();
        }


        SessionFactory ormSessions = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientesPremium.class).buildSessionFactory();
        Session actualSessions = ormSessions.openSession();

        try {
            actualSessions.beginTransaction();

            clienteP = actualSessions.createQuery("from ClientesPremium where descuento!=0").getResultList();
            actualSessions.getTransaction().commit();
        } finally {
            actualSessions.close();
        }

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("------------------------------------------------Lista de clientes Premium------------------------------------------------");
        for (ClientesPremium cPs : clienteP) {
            System.out.println(cPs);

        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        System.out.println("------------------------------------------------Lista de clientes Estandar-----------------------------------------------");
        for (ClientesEstandar cEs : clienteEstandar) {
            System.out.println(cEs);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }
}






















