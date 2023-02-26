package Modelo;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Entity
@Table(name="pedidos")
public class Pedido {

    /**
     * Atributos de la clase
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="numeroPedido")
    private int numeroPedido;
    @Column(name="nifCliente")
    private String nifCliente;
    @Transient
    private Clientes cliente;

    @Transient
    private Articulos articulo;
    @Column(name="articulo")
    String codArticulo;
    @Column(name="cantidadArticulos")
    private int cantidadArticulos;
    @Column(name="fechaHora")
    private LocalDateTime fechaHora;
    @Column(name="precioTotal")
    private double precioTotal;
    @Transient
    private boolean enviado;


    public Pedido() {
    }

    public Pedido(String nifC, String codArticulo, int catArt) {

        this.nifCliente=nifC;
        this.codArticulo= codArticulo;
        this.cantidadArticulos= catArt;
        this.precioTotal=0;
        this.fechaHora=null;
        this.enviado= false;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    /**
     * Metodos Getters y Setters
     */


    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {   //entiendo que en el momento de crear el pedido ya calcula la hora de ese momento "this.fechaHora= LocalDateTime.now()";
        this.fechaHora = fechaHora;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = pedidoEnviado(fechaHora, articulo.getTiempoDePreparacion());
    }

    public boolean getEnviado() {
        return enviado;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioEnvio(articulo.getPrecioDeVenta(), cantidadArticulos, articulo.getGastosDeEnvio(), cliente.getDescuento());
    }
    public void setPrecioTotal2(double pTotal){
        this.precioTotal=pTotal;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }


    public boolean pedidoEnviado(LocalDateTime fechaHora, Long tiempoPreparacion) {        // funcion para saber si el pedido ha sido envido o no
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String fecha1 = fechaHora.toString().replace("T", " ");  //fecha elaboracion
        String fecha2 = LocalDateTime.now().toString().replace("T", " "); //fecha actual
        try {
            Date date1 = sdf.parse(fecha1);
            System.out.println(date1);

            Date date2 = sdf.parse(fecha2);
            System.out.println(date2);
            long diff = date2.getTime() - date1.getTime();
            TimeUnit time = TimeUnit.MINUTES;
            long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
            System.out.println("The difference in days is : "+diffrence);
            System.out.println("diff: "+diff);
            System.out.println("tiempo de preparacion: "+ tiempoPreparacion);
            if (diffrence < tiempoPreparacion) {   //si diff es mayor al tiempo de prep., el pedido ya se ha enviado
                return true;
            } else {    //si diff es menor al tiempo de prep., el pedido aun no se ha enviado
                return false;
            }
        } catch (ParseException e) {
            System.out.println("El calculo de pedidoEnviado() en pedidos, ha fallado.");
            e.printStackTrace();
        }
        return true;  //si hay algun error, el pedido no se devolvera ya que saldra como enviado.
    }



    //Funcion para calcular el precio TOTAL del pedido (precio de todos los articulos + envio - descuento)
    public double precioEnvio(double precioDeVenta, int cantidadArticulos, double gastosEnvio, double descuento) {
        double precio = precioDeVenta * cantidadArticulos;
        if (descuento != 0) {
            return precio + ((1-(descuento / 100)) * gastosEnvio);
        } else {
            return precio + gastosEnvio;
        }

    }


    public double precioEnvio2(Articulos ar, int cantArt, double gastEnv, float desc){ /** Metodo que calcula el precio de envio total */
        float pdv;
        double precio;

        pdv= (float) ar.getPrecioDeVenta();
        precio=  pdv*cantArt;
        if(desc>0){
            return precio + ((1-(desc / 100)) * gastEnv);
        } else{
            return precio+ gastEnv;
        }
    }

    @Override
    public String toString() {
        return
                "numeroPedido=" + numeroPedido +
                ", nifCliente='" + nifCliente + '\'' +
                ", codArticulo='" + codArticulo + '\'' +
                ", cantidadArticulos=" + cantidadArticulos +
                ", fechaHora=" + fechaHora
                ;
    }
}