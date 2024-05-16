package Models;

import Views.FrmLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ConfigDao {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public Config ListarConfig() {
        Config conf = new Config();
        String sql = "SELECT * FROM configuracion";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                conf.setId(rs.getInt("id"));
                conf.setRuc(rs.getString("nombre"));
                conf.setNombre(rs.getString("ruc"));
                conf.setTelefono(rs.getString("telefono"));
                conf.setDireccion(rs.getString("direccion"));
                conf.setMensaje(rs.getString("mensaje"));
            } else {
                //JOptionPane.showMessageDialog(null, "No hay Descripción");
                FrmLogin l = new FrmLogin();
                l.advertencia("No hay Descripción");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return conf;
    }
    public boolean empresa(Config conf){
        String sql = "UPDATE configuracion SET nombre = ?, ruc = ?, telefono = ?, direccion = ?, mensaje = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, conf.getNombre());
            ps.setString(2, conf.getRuc());
            ps.setString(3, conf.getTelefono());
            ps.setString(4, conf.getDireccion());
            ps.setString(5, conf.getMensaje());
            ps.setInt(6, conf.getId());
            ps.execute();
            //JOptionPane.showMessageDialog(null, "Información de la empresa actualizada");
            FrmLogin l = new FrmLogin();
            l.exito("Información de la empresa actualizada");
            return true;
        } catch (SQLException e) {
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    public void barra(JPanel panel) {
        try {
            String sql = "SELECT nombre, cantidad FROM productos WHERE cantidad <= 10 ORDER BY cantidad ASC LIMIT 10";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            while (rs.next()) {
                ds.addValue(rs.getDouble("cantidad"), rs.getString("nombre"), rs.getString("nombre"));
            }
            JFreeChart jf = ChartFactory.createBarChart3D("Productos con Stock Mínimo", "", "Total", ds, PlotOrientation.VERTICAL, true, true, false);
            ChartPanel f = new ChartPanel(jf);
            f.setSize(520, 380);
            panel.add(f);

        } catch (NumberFormatException | SQLException e) {
            System.out.println(e.toString());
        }
    }
    public void torta(JPanel panel) {
        try {
            String sql = "SELECT d.id_producto, d.cantidad, p.id, p.nombre, SUM(d.cantidad) as total FROM detalle_venta d INNER JOIN productos p WHERE d.id_producto = p.id group by d.id_producto ORDER BY d.cantidad DESC LIMIT 10";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            while (rs.next()) {
                dataset.setValue(rs.getString("nombre"), rs.getDouble("cantidad"));
            }
            JFreeChart jf = ChartFactory.createPieChart("Productos mas vendidos", dataset);
            ChartPanel f = new ChartPanel(jf);
            f.setSize(520, 380);
            panel.add(f);
            //ChartUtilities.saveChartAsJPEG(new File("./src/Reportes/grafico.jpg"), jf, 500, 300);

        } catch (NumberFormatException | SQLException e) {
            System.out.println(e.toString());
        }
    }
    public int admin(String table){
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM "+table+" WHERE estado = 'Activo'";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    public int adminTotal(String table){
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) as total FROM "+table+" WHERE fecha > CURDATE() AND estado = 'Activo'";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return total;
    }
}
