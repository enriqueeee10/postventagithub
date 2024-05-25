package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuariosDao extends Conexion{

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrar(Usuarios us) {
        boolean res = false;
        String consulta = "SELECT * FROM usuarios WHERE correo = ?";
        String sql = "INSERT INTO usuarios (usuario, nombre, correo, clave, caja, rol) VALUES (?,?,?,?,?,?)";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, us.getUsuario());
            rs = ps.executeQuery();
            if (!rs.next()) {
                    ps = con.prepareStatement(sql);
                    ps.setString(1, us.getUsuario());
                    ps.setString(2, us.getNombre());
                    ps.setString(3, us.getCorreo());
                    ps.setString(4, us.getClave());
                    ps.setString(5, us.getCaja());
                    ps.setString(6, us.getRol());
                    ps.execute();
                    res = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            res = false;
        }
        return res;
    }
    
    
        public boolean modificar(Usuarios us) {
        String sql = "INSERT INTO usuarios (usuario, nombre, correo, clave, caja, rol) VALUES (?,?,?,?,?,?)";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getUsuario());
            ps.setString(2, us.getNombre());
            ps.setString(3, us.getCaja());
            ps.setString(4, us.getRol());
            ps.setInt(5, us.getId());
            ps.execute();
            return true;
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString() );
                return false;
                
            }
        }
        
        public boolean action (String estado, int id) {
            String sql = "UPDATE usuarios SET estado = ? WHERE id = ?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, estado);
                ps.setInt(2, id);
                ps.execute();
                return true;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }   return false;
            
        }

    public boolean actualizar(Usuarios reg) {
        boolean res;
        String sql = "UPDATE usuarios SET usuario=?, nombre=?, caja=?, rol=? WHERE id = ?";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getUsuario());
            ps.setString(2, reg.getNombre());
            ps.setString(3, reg.getCorreo());
            ps.setString(4, reg.getCaja());
            ps.setString(5, reg.getRol());
            ps.setInt(6, reg.getId());
            ps.execute();
            res = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            res = false;
        }
        return res;
    }

    public boolean actualizarPass(String pass, int id) {
        String sql = "UPDATE usuarios SET clave=? WHERE id = ?";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    
        public boolean EliminarUsuarios(int id){
        String sql = "DELETE FROM usuarios WHERE id = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    } 

    public List Listar(String valor) {
        List<Usuarios> listaUsuario = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM usuarios ORDER BY estado ASC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT * FROM usuarios WHERE nombre LIKE '%" + valor + "%' OR usuario LIKE '%" + valor + "%' OR rol LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuarios user = new Usuarios();
                user.setId(rs.getInt("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setCaja(rs.getString("caja"));
                user.setRol(rs.getString("rol"));
                user.setEstado(rs.getString("estado"));
                listaUsuario.add(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaUsuario;
    }

    public boolean accion(String estado, int id) {

        String sql = "UPDATE usuarios SET estado = ? WHERE id = ?";
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
    
    public Usuarios obtenerUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuarios usuario = null;

        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuarios();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setClave(rs.getString("clave"));
                    usuario.setCaja(rs.getString("caja"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }
    

    public Usuarios login(String correo, String clave) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND clave = ?";
        Usuarios l = new Usuarios();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if (rs.next()) {
                 if ("Inactivo".equals(rs.getString("estado"))) {
                    JOptionPane.showMessageDialog(null, "Usuario inactivo");
                    
                    return l;
                }
                    l.setId(rs.getInt("id"));
                    l.setNombre(rs.getString("nombre"));
                    l.setCorreo(rs.getString("correo"));
                    l.setClave(rs.getString("clave"));
                    l.setUsuario(rs.getString("usuario"));
                    l.setCaja(rs.getString("caja"));
                    l.setRol(rs.getString("rol")); 
                    l.setEstado(rs.getString("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
    
    public boolean cambiar(String correo, String pass) {
        boolean respuesta = false;
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND clave = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
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
    
    /* public String validarestado(String correo) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConnection(); // Obtener conexión desde la clase padre
    String query = "SELECT estado FROM usuarios WHERE correo = ?";
    try {
        ps = con.prepareStatement(query);
        ps.setString(1, correo);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("estado");
        }
    } catch (SQLException ex) {
        Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return null;
} */

    /*public boolean cambiarPass(String correo, String nuevaContraseña) {
    String sql = "UPDATE usuarios SET clave = ? WHERE correo = ?";
    try {
        ps = con.prepareStatement(sql);
        ps.setString(1, correo);
        ps.setString(2, nuevaContraseña);
        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    } finally {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}*/

    public boolean cambiarPass(String correo, String nuevaContraseña) {
    String sql = "UPDATE usuarios SET clave = ? WHERE correo = ?";
    Connection con = null;
    PreparedStatement ps = null;
    try {
        con = getConnection(); // Obtener la conexión desde la clase padre
        ps = con.prepareStatement(sql);
        ps.setString(1, nuevaContraseña);
        ps.setString(2, correo);
        ps.executeUpdate(); // Usar executeUpdate() en lugar de execute() para consultas de actualización
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    } finally {
        // Cerrar la conexión y el PreparedStatement en el bloque finally
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}

    
    
    
    
    
    public boolean usuarioExiste(String nombreUsuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConnection(); // Obtener conexión desde la clase padre
        String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean correoExiste(String correo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConnection(); // Obtener conexión desde la clase padre
        String query = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean Recuperacion(Usuarios usuarios){
        PreparedStatement ps= null;
        Connection con=(Connection)getConnection();
        ResultSet res = null;
        String query="SELECT * FROM usuarios WHERE usuario=?";
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, usuarios.getNombre());
            res=ps.executeQuery();
            if(res.next()){
                usuarios.setId(res.getInt("id"));
                usuarios.setNombre(res.getString("usuario"));
                usuarios.setCorreo(res.getString("correo"));
                usuarios.setClave(res.getString("clave"));
                //usuarios.setPassword(res.getString("rol"));
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
    }
    
}
