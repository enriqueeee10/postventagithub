package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CajasDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(String valor, String user) {
        List<Cajas> lista = new ArrayList();
        String sql;
        try {
            con = cn.getConnection();
            if (valor.equalsIgnoreCase("") || user.equalsIgnoreCase("")) {
                sql = "SELECT * FROM caja WHERE usuario = ?";
            } else {
                sql = "SELECT * FROM caja WHERE LIKE fecha_apertura '%" + valor + "%'";
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cajas c = new Cajas();
                c.setId(rs.getInt("id"));
                c.setFecha_apertura(rs.getString("fecha_apertura"));
                c.setFecha_cierre(rs.getString("fecha_cierre"));
                c.setMonto_inicial(rs.getDouble("monto_inicial"));
                c.setMonto_final(rs.getDouble("monto_final"));
                c.setVentas(rs.getInt("total_ventas"));
                c.setEstado(rs.getString("estado"));
                lista.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }

    public boolean registrar(Cajas c) {
        boolean respuesta;
        String sql = "SELECT * FROM caja WHERE estado = 'Abierto' AND usuario = ?";
        String query = "INSERT INTO caja (fecha_apertura, monto_inicial, usuario, caja) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getUsuario());
            rs = ps.executeQuery();
            respuesta = rs.next();
            if (!respuesta) {
                ps = con.prepareStatement(query);
                ps.setString(1, c.getFecha_apertura());
                ps.setDouble(2, c.getMonto_inicial());
                ps.setString(3, c.getUsuario());
                ps.setString(4, c.getCaja());
                ps.execute();
                respuesta = true;
            }else{
                respuesta = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            respuesta = false;
        }
        return respuesta;
    }

    public boolean caja(String usuario) {
        boolean respuesta = false;
        String sql = "SELECT * FROM caja WHERE estado = 'Abierto' AND usuario = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getRow() > 0) {
                    respuesta = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            respuesta = false;
        }
        return respuesta;
    }
    
    public double ventas(String accion){
        double total = 0.00;
        String sql = "SELECT "+accion+" as total FROM ventas WHERE fecha > CURDATE()";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return total;
    }
    public boolean cerrar(Cajas c) {
        String sql = "UPDATE caja SET fecha_cierre=?, monto_final=?, total_ventas=?, estado=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getFecha_cierre());
            ps.setDouble(2, c.getMonto_final());
            ps.setInt(3, c.getVentas());
            ps.setString(4, c.getEstado());
            ps.setInt(5, c.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
}
