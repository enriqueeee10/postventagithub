
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public List listar(String valor) {
        List<Venta> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM ventas"; 
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT * FROM ventas WHERE fecha LIKE '%"+valor+"%'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Venta ven = new Venta();
                ven.setId(rs.getInt("id"));
                ven.setCliente(rs.getString("cliente"));
                ven.setUsuario(rs.getString("usuario"));
                ven.setTotal(rs.getDouble("total"));
                ven.setFecha(rs.getString("fecha"));
                ven.setEstado(rs.getString("estado"));
                lista.add(ven);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista;
    }
    public List listarC(String valor) {
        List<Venta> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM compras"; 
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT * FROM compras WHERE fecha LIKE '%"+valor+"%'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Venta ven = new Venta();
                ven.setId(rs.getInt("id"));
                ven.setCliente(rs.getString("proveedor"));
                ven.setTotal(rs.getDouble("total"));
                ven.setFecha(rs.getString("fecha"));
                ven.setEstado(rs.getString("estado"));
                lista.add(ven);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista;
    }
}
