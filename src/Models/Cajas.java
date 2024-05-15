package Models;

import java.util.Date;

public class Cajas {
    private int id;
    private String fecha_apertura;
    private String fecha_cierre;
    private double monto_inicial;
    private double monto_final;
    private String usuario;
    private String caja;
    private int ventas;
    private String estado;

    public Cajas() {
    }

    public Cajas(int id, String fecha_apertura, String fecha_cierre, double monto_inicial, double monto_final, String usuario, String caja, int ventas, String estado) {
        this.id = id;
        this.fecha_apertura = fecha_apertura;
        this.fecha_cierre = fecha_cierre;
        this.monto_inicial = monto_inicial;
        this.monto_final = monto_final;
        this.usuario = usuario;
        this.caja = caja;
        this.ventas = ventas;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(String fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public String getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(String fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public double getMonto_inicial() {
        return monto_inicial;
    }

    public void setMonto_inicial(double monto_inicial) {
        this.monto_inicial = monto_inicial;
    }

    public double getMonto_final() {
        return monto_final;
    }

    public void setMonto_final(double monto_final) {
        this.monto_final = monto_final;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    
}
