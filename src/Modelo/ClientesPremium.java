package Modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/** Clase abstracta "ClientesPremium" */
//CLIENTE PREMIUM
//CUOTA ANUAL - 30€
//20% DE DESCUENTO EN LOS GASTOS DE ENVIO DE CADA PEDIDO.

@Entity
@Table(name="clientes")
@DiscriminatorValue(value = "Premium")
public class ClientesPremium extends Clientes {


    public ClientesPremium() {
    }

    public ClientesPremium(String nif, String name, String Ad, String mail, float quota, float desc) {
        super(nif,name, Ad,  mail, quota, desc);
    }


    @Override
    public String toString() {
        return super.toString() + "Cuota anual: " + calcAnual()+ ", Descuento en los gastos de envio: " + descuentoEnv()+"\n";
    }


    //MÉTODOS ABSTRACTOS
    public Clientes copiaCliente(Clientes clientes){
        ClientesPremium e = (ClientesPremium) clientes;
        Clientes copiaCliente = new ClientesPremium(e.getNombre(), e.getDomicilio(), e.getNif(),e.getEmail(), e.getCuota(), e.getDescuento());
        return copiaCliente(e);
    }
    public String tipoCliente(){
        return "El cliente " + this.getNombre() + "es de tipo Premium";
    }
    public float calcAnual(){
        return 30;
    }
    public float descuentoEnv(){
        return (float)0.2;
    }

}