package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProveedorDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List Listar(String valor) {
        List<Proveedor> listaProveedor = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM proveedor ORDER BY estado ASC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT * FROM proveedor WHERE ruc LIKE '%" + valor + "%' OR nombre LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getString("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setEstado(rs.getString("estado"));
                listaProveedor.add(pr);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaProveedor;
    }

    public boolean registrar(Proveedor pr) {
        boolean res = false;
        String consulta = "SELECT * FROM proveedor WHERE ruc = ?";
        String sql = "INSERT INTO proveedor (ruc, nombre, telefono, direccion) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, pr.getRuc());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = con.prepareStatement(sql);
                ps.setString(1, pr.getRuc());
                ps.setString(2, pr.getNombre());
                ps.setString(3, pr.getTelefono());
                ps.setString(4, pr.getDireccion());
                ps.execute();
                res = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            res = false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return res;
    }

    public boolean modificar(Proveedor pr) {
        con = cn.getConnection();
        String sql = "UPDATE proveedor SET ruc = ?, nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setInt(5, pr.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean accion(String estado, int id) {
        String sql = "UPDATE proveedor SET estado = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
}
