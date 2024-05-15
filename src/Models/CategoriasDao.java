package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriasDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List Listar(String valor) {
        List<Categorias> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM categorias ORDER BY estado ASC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT * FROM categorias WHERE nombre LIKE '%" + valor + "%' OR estado LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Categorias cat = new Categorias();
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));
                cat.setEstado(rs.getString("estado"));
                lista.add(cat);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    public boolean registrar(Categorias cat) {
        boolean res = false;
        String consulta = "SELECT * FROM categorias WHERE nombre = ?";
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, cat.getNombre());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = con.prepareStatement(sql);
                ps.setString(1, cat.getNombre());
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

    public boolean modificar(Categorias cat) {
        con = cn.getConnection();
        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getNombre());
            ps.setInt(2, cat.getId());
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
        String sql = "UPDATE categorias SET estado = ? WHERE id = ?";
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
