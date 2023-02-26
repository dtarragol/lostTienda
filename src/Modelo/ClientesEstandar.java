package Modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/** Clase "ClientesEstandar" */


@Entity
@Table(name="clientes")
@DiscriminatorValue(value = "Estandar")
public class ClientesEstandar extends Clientes {

    protected ClientesEstandar() {
    }

    /**
     * Metodo constructor de la clase
     *
     * @param name
     * @param addres
     * @param Nif
     * @param mail
     */

    //cONSTRUCTOR 1
    public ClientesEstandar(String Nif,String name, String addres, String mail) {
        super(Nif,name, addres,  mail);
    }


    //TO STRING
    @Override
    public String toString() {
        return super.toString();
    }

    //IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS
    public Clientes copiaCliente(Clientes clientes){
        ClientesEstandar e = (ClientesEstandar) clientes;
        Clientes copiaCliente = new ClientesEstandar(e.getNombre(), e.getDomicilio(),e.getNif(), e.getEmail());
        return copiaCliente(e);
    }
    public String tipoCliente(){
        return "El cliente " + this.getNombre() + "es de tipo Estandar";
    }
    public float calcAnual(){
        return 0;
    }
    public float descuentoEnv(){
        return 0;
    }
    /*public String toString() {
        return "\nNIF:" + this.getNif() + "', Nombre: '" + this.getNombre() + "', Domicilio: " + this.getDomicilio() + ", E-mail: " + this.getEmail() + "}";
    }*/
}