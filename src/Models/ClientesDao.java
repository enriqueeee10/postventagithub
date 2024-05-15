package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClientesDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List Listar(String valor) {
        List<Clientes> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM clientes ORDER BY estado ASC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT * FROM clientes WHERE nombre LIKE '%" + valor + "%' OR direccion LIKE '%" + valor + "%' OR estado LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Clientes cl = new Clientes();
                cl.setId(rs.getInt(1));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setEstado(rs.getString("estado"));
                lista.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    public boolean registrar(Clientes Cl) {
        boolean res = false;
        String consulta = "SELECT * FROM clientes WHERE nombre = ?";
        String sql = "INSERT INTO clientes (nombre, telefono, direccion) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, Cl.getNombre());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = con.prepareStatement(sql);
                ps.setString(1, Cl.getNombre());
                ps.setString(2, Cl.getTelefono());
                ps.setString(3, Cl.getDireccion());
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

    public boolean modificar(Clientes Cl) {
        con = cn.getConnection();
        String sql = "UPDATE clientes SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, Cl.getNombre());
            ps.setString(2, Cl.getTelefono());
            ps.setString(3, Cl.getDireccion());
            ps.setInt(4, Cl.getId());
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
        String sql = "UPDATE clientes SET estado = ? WHERE id = ?";
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
