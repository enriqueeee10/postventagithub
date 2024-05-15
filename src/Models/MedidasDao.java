package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MedidasDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List Listar(String valor) {
        List<Medidas> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM medidas ORDER BY estado ASC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT * FROM medidas WHERE nombre LIKE '%" + valor + "%' OR estado LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Medidas med = new Medidas();
                med.setId(rs.getInt("id"));
                med.setNombre(rs.getString("nombre"));
                med.setNombre_corto(rs.getString("nombre_corto"));
                med.setEstado(rs.getString("estado"));
                lista.add(med);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }

    public boolean registrar(Medidas med) {
        boolean res = false;
        String consulta = "SELECT * FROM medidas WHERE nombre = ?";
        String sql = "INSERT INTO medidas (nombre, nombre_corto) VALUES (?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, med.getNombre());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = con.prepareStatement(sql);
                ps.setString(1, med.getNombre());
                ps.setString(2, med.getNombre_corto());
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

    public boolean modificar(Medidas med) {
        con = cn.getConnection();
        String sql = "UPDATE medidas SET nombre = ?, nombre_corto = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, med.getNombre());
            ps.setString(2, med.getNombre_corto());
            ps.setInt(3, med.getId());
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
        String sql = "UPDATE medidas SET estado = ? WHERE id = ?";
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
