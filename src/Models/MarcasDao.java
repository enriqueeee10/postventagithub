package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcasDao {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
        public List Listar(String valor) {
        List<Marcas> lista = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM marcas ORDER BY estado ASC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT * FROM marcas WHERE nombre LIKE '%" + valor + "%' OR estado LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Marcas marc = new Marcas();
                marc.setId(rs.getInt("id"));
                marc.setNombre(rs.getString("nombre"));
                marc.setEstado(rs.getString("estado"));
                lista.add(marc);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return lista;
    }
        
    
public boolean registarMarcas(Marcas marc) {
    
     boolean res = false;
        String consulta = "SELECT * FROM marcas WHERE nombre = ?";
        String sql = "INSERT INTO marcas (nombre) VALUES (?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, marc.getNombre());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = con.prepareStatement(sql);
                ps.setString(1, marc.getNombre());
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

    public boolean modificarMarcas(Marcas marc) {
        con = cn.getConnection();
        String sql = "UPDATE marcas SET nombre = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, marc.getNombre());
            ps.setInt(2, marc.getId());
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
        String sql = "UPDATE marcas SET estado = ? WHERE id = ?";
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



