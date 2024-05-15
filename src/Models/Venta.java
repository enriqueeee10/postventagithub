
package Models;

public class Venta {
    private int id;
    private String cliente;
    private String usuario;
    private double total;
    private String fecha;
    private String estado;

    public Venta() {
    }

    public Venta(int id, String cliente, String usuario, double total, String fecha, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.usuario = usuario;
        this.total = total;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
