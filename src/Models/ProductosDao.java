package Models;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class ProductosDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List Listar(String valor) {
        List<Productos> listaProducto = new ArrayList();
        try {
            con = cn.getConnection();
            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT * FROM productos ORDER BY estado ASC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT * FROM productos WHERE codigo LIKE '%" + valor + "%' OR nombre LIKE '%" + valor + "%' OR proveedor LIKE '%" + valor + "%' OR medida LIKE '%" + valor + "%' OR categoria LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio_venta(rs.getDouble("precio_venta"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setMedida(rs.getString("medida"));
                pro.setEstado(rs.getString("estado"));
                listaProducto.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaProducto;
    }

    public boolean registrar(Productos pro) {
        boolean res = false;
        String consulta = "SELECT * FROM productos WHERE codigo = ?";
        String sql = "INSERT INTO productos (codigo, nombre, proveedor, precio_compra, precio_venta, medida, categoria) VALUES (?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = con.prepareStatement(sql);
                ps.setString(1, pro.getCodigo());
                ps.setString(2, pro.getNombre());
                ps.setString(3, pro.getProveedor());
                ps.setDouble(4, pro.getPrecio_compra());
                ps.setDouble(5, pro.getPrecio_venta());
                ps.setString(6, pro.getMedida());
                ps.setString(7, pro.getCategoria());
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

    public boolean modificar(Productos pro) {
        con = cn.getConnection();
        String sql = "UPDATE productos SET codigo = ?, nombre = ?, proveedor = ?, precio_compra = ?, precio_venta = ?, medida =?, categoria =? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setDouble(4, pro.getPrecio_compra());
            ps.setDouble(5, pro.getPrecio_venta());
            ps.setString(6, pro.getMedida());
            ps.setString(7, pro.getCategoria());
            ps.setInt(8, pro.getId());
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
        String sql = "UPDATE productos SET estado = ? WHERE id = ?";
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

    public void llenarCombo(JComboBox combo, String tabla) {
        try {
            con = cn.getConnection();
            String sql = "SELECT * FROM " + tabla + " WHERE estado = 'Activo'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public Productos editar(int id) {
        Productos p = new Productos();
        String sql = "SELECT * FROM productos WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio_venta(rs.getDouble("precio_venta"));
                p.setPrecio_compra(rs.getDouble("precio_compra"));
                p.setProveedor(rs.getString("proveedor"));
                p.setMedida(rs.getString("medida"));
                p.setCategoria(rs.getString("categoria"));
                p.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return p;
    }

    public Productos buscarProducto(String cod) {
        Productos p = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo = ? AND estado = 'Activo'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio_venta(rs.getDouble("precio_venta"));
                p.setPrecio_compra(rs.getDouble("precio_compra"));
                p.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
        return p;
    }

    //Ventas
    public int buscarId(int id) {
        int res = 0;
        String sql = "SELECT * FROM productos WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return res;
    }

    public int IdVenta(String nombre) {
        int id = 0;
        String sql = "SELECT MAX(id) as id from " + nombre;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return id;
    }

    public boolean GuardarVenta(String tabla, String accion, String cliente, String vendedor, String total) {
        String sql = "INSERT INTO " + tabla + " (" + accion + ",usuario,total) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente);
            ps.setString(2, vendedor);
            ps.setString(3, total);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean GuardarDetalle(String tabla, String accion, int id, int id_pro, double precio, int cant) {
        String sql = "INSERT INTO " + tabla + " (" + accion + ", id_producto, precio, cantidad) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id_pro);
            ps.setDouble(3, precio);
            ps.setInt(4, cant);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean actualizarStock(int cant, int id) {
        String sql = "UPDATE productos SET cantidad=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean caja(String users) {
        boolean respuesta = false;
        String sql = "SELECT * FROM caja WHERE estado = 'Abierto' AND usuario = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, users);
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

      public void pdfV(int idventa, String Cliente, String total, String usuario) {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + "/venta.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance(getClass().getResource("/Assets/titulo.png"));
            //Fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            fecha.add("Vendedor: " + usuario + "\nFolio: " + idventa + "\nFecha: "
                    + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);
            Encabezado.addCell("");
            //info empresa
            String config = "SELECT * FROM configuracion";
            String mensaje = "";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(config);
                rs = ps.executeQuery();
                if (rs.next()) {
                    mensaje = rs.getString("mensaje");
                    Encabezado.addCell("Ruc:    " + rs.getString("ruc") + "\nNombre: " + rs.getString("nombre") + "\nTeléfono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion") + "\n\n");
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            //
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            //cliente
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("DATOS DEL CLIENTE" + "\n\n");
            doc.add(cli);

            PdfPTable proveedor = new PdfPTable(3);
            proveedor.setWidthPercentage(100);
            proveedor.getDefaultCell().setBorder(0);
            float[] columnWidthsCliente = new float[]{50f, 25f, 25f};
            proveedor.setWidths(columnWidthsCliente);
            proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliNom = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cliTel = new PdfPCell(new Phrase("Télefono", negrita));
            PdfPCell cliDir = new PdfPCell(new Phrase("Dirección", negrita));
            cliNom.setBorder(Rectangle.NO_BORDER);
            cliTel.setBorder(Rectangle.NO_BORDER);
            cliDir.setBorder(Rectangle.NO_BORDER);
            proveedor.addCell(cliNom);
            proveedor.addCell(cliTel);
            proveedor.addCell(cliDir);
            String prove = "SELECT * FROM clientes WHERE nombre = ?";
            try {
                ps = con.prepareStatement(prove);
                ps.setString(1, Cliente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    proveedor.addCell(rs.getString("nombre"));
                    proveedor.addCell(rs.getString("telefono"));
                    proveedor.addCell(rs.getString("direccion") + "\n\n");
                } else {
                    proveedor.addCell("Publico en General");
                    proveedor.addCell("S/N");
                    proveedor.addCell("S/N" + "\n\n");
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            doc.add(proveedor);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{10f, 50f, 15f, 15f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell c1 = new PdfPCell(new Phrase("Cant.", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Descripción.", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("P. unt.", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("P. Total", negrita));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            String product = "SELECT d.id, d.id_venta, d.id_producto, d.precio, d.cantidad, p.id, p.id, p.nombre FROM detalle_venta d INNER JOIN productos p WHERE d.id_producto = p.id AND d.id_venta = ?";
            try {
                ps = con.prepareStatement(product);
                ps.setInt(1, idventa);
                rs = ps.executeQuery();
                while (rs.next()) {
                    double subTotal = rs.getInt("cantidad") * rs.getDouble("precio");
                    tabla.addCell(rs.getString("cantidad"));
                    tabla.addCell(rs.getString("nombre"));
                    tabla.addCell(rs.getString("precio"));
                    tabla.addCell(String.valueOf(subTotal));
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            doc.add(tabla);
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + total);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion \n\n");
            firma.add("------------------------------------\n");
            firma.add("Firma \n");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            gr.add(mensaje);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    } 
    
    /* public void pdfV(int idventa, String Cliente, String total, String usuario) {
    Document doc = null;
    try {
        Date date = new Date();
        FileOutputStream archivo;
        String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        File salida = new File(url + "/venta.pdf");
        archivo = new FileOutputStream(salida);
        doc = new Document(new Rectangle(226.77f, 806.77f)); // 80mm width
        PdfWriter.getInstance(doc, archivo);
        doc.open();
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 6);
        Paragraph fecha = new Paragraph();
        fecha.add(Chunk.NEWLINE);
        fecha.add(new Phrase("Vendedor: " + usuario + "\nFolio: " + idventa + "\nFecha: "
                + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n", font));
        fecha.setAlignment(Element.ALIGN_CENTER);
        PdfPTable Encabezado = new PdfPTable(4);
        Encabezado.setWidthPercentage(100);
        Encabezado.getDefaultCell().setBorder(0);
        float[] columnWidthsEncabezado = new float[]{25f, 25f, 25f, 25f};
        Encabezado.setWidths(columnWidthsEncabezado);
        Encabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        PdfPCell cell = new PdfPCell(new Phrase("RUC:\n123456789", font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        Encabezado.addCell(cell);
        
        PdfPCell cell2 = new PdfPCell(new Phrase("Nombre:\nvado eirl asdas", font));
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        Encabezado.addCell(cell2);
        
        PdfPCell cell3 = new PdfPCell(new Phrase("Teléfono:\n112391923", font));
        cell3.setBorder(Rectangle.NO_BORDER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        Encabezado.addCell(cell3);
        
        PdfPCell cell4 = new PdfPCell(new Phrase("Dirección:\nasidiw", font));
        cell4.setBorder(Rectangle.NO_BORDER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        Encabezado.addCell(cell4);
        
        Encabezado.addCell(fecha);
        doc.add(Encabezado);
        
        Paragraph cli = new Paragraph();
        cli.add(Chunk.NEWLINE);
        cli.add(new Phrase("DATOS DEL CLIENTE\n\n", font));
        cli.setAlignment(Element.ALIGN_CENTER);
        doc.add(cli);

        PdfPTable proveedor = new PdfPTable(3);
        proveedor.setWidthPercentage(100);
        proveedor.getDefaultCell().setBorder(0);
        float[] columnWidthsCliente = new float[]{33.33f, 33.33f, 33.33f};
        proveedor.setWidths(columnWidthsCliente);
        proveedor.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cliNom = new PdfPCell(new Phrase("Nombre", font));
        PdfPCell cliTel = new PdfPCell(new Phrase("Teléfono", font));
        PdfPCell cliDir = new PdfPCell(new Phrase("Dirección", font));
        cliNom.setBorder(Rectangle.NO_BORDER);
        cliTel.setBorder(Rectangle.NO_BORDER);
        cliDir.setBorder(Rectangle.NO_BORDER);
        proveedor.addCell(cliNom);
        proveedor.addCell(cliTel);
        proveedor.addCell(cliDir);
        
        String prove = "SELECT * FROM clientes WHERE nombre = ?";
        try {
            ps = con.prepareStatement(prove);
            ps.setString(1, Cliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                proveedor.addCell(new Phrase(rs.getString("nombre"), font));
                proveedor.addCell(new Phrase(rs.getString("telefono"), font));
                proveedor.addCell(new Phrase(rs.getString("direccion") + "\n\n", font));
            } else {
                proveedor.addCell(new Phrase("Publico en General", font));
                proveedor.addCell(new Phrase("S/N", font));
                proveedor.addCell(new Phrase("S/N\n\n", font));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        doc.add(proveedor);

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.getDefaultCell().setBorder(0);
        float[] columnWidths = new float[]{10f, 40f, 20f, 20f};
        tabla.setWidths(columnWidths);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell c1 = new PdfPCell(new Phrase("Cant.", font));
        PdfPCell c2 = new PdfPCell(new Phrase("Descripción.", font));
        PdfPCell c3 = new PdfPCell(new Phrase("P. unit.", font));
        PdfPCell c4 = new PdfPCell(new Phrase("Total", font));
        c1.setBorder(Rectangle.NO_BORDER);
        c2.setBorder(Rectangle.NO_BORDER);
        c3.setBorder(Rectangle.NO_BORDER);
        c4.setBorder(Rectangle.NO_BORDER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tabla.addCell(c1);
        tabla.addCell(c2);
        tabla.addCell(c3);
        tabla.addCell(c4);
        
        String product = "SELECT d.id, d.id_venta, d.id_producto, d.precio, d.cantidad, p.id, p.id, p.nombre FROM detalle_venta d INNER JOIN productos p WHERE d.id_producto = p.id AND d.id_venta = ?";
        try {
            ps = con.prepareStatement(product);
            ps.setInt(1, idventa);
            rs = ps.executeQuery();
            while (rs.next()) {
                double subTotal = rs.getInt("cantidad") * rs.getDouble("precio");
                tabla.addCell(new Phrase(rs.getString("cantidad"), font));
                tabla.addCell(new Phrase(rs.getString("nombre"), font));
                tabla.addCell(new Phrase(rs.getString("precio"), font));
                tabla.addCell(new Phrase(String.valueOf(subTotal), font));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        doc.add(tabla);
        
        Paragraph info = new Paragraph();
        info.add(Chunk.NEWLINE);
        info.add(new Phrase("Total S/: " + total, font));
        info.setAlignment(Element.ALIGN_RIGHT);
        doc.add(info);
        
        Paragraph firma = new Paragraph();
        firma.add(Chunk.NEWLINE);
        firma.add(new Phrase("Cancelación \n\n", font));
        firma.add(new Phrase("------------------------------------\n", font));
        firma.add(new Phrase("Firma \n", font));
        firma.setAlignment(Element.ALIGN_CENTER);
        doc.add(firma);
        
        Paragraph gr = new Paragraph();
        gr.add(Chunk.NEWLINE);
        gr.add(new Phrase("mensaje", font));
        gr.setAlignment(Element.ALIGN_CENTER);
        doc.add(gr);
        
        doc.close();
        archivo.close();
        Desktop.getDesktop().open(salida);
    } catch (DocumentException | IOException e) {
        System.out.println(e.toString());
    }
} */

    public void pdfC(int idcompra, String proveedor, String total) {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + "/compra.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance(getClass().getResource("/Assets/logo.jpg"));
            //Fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            fecha.add("Folio: " + idcompra + "\nFecha: "
                    + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);
            Encabezado.addCell("");
            //info empresa
            String config = "SELECT * FROM configuracion";
            String mensaje = "";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(config);
                rs = ps.executeQuery();
                if (rs.next()) {
                    mensaje = rs.getString("mensaje");
                    Encabezado.addCell("Ruc:   " + rs.getString("ruc") + "\nNombre: " + rs.getString("nombre") + "\nTeléfono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion") + "\n\n");
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            //
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            //cliente
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos Del Proveedor" + "\n\n");
            doc.add(cli);

            PdfPTable prov = new PdfPTable(3);
            prov.setWidthPercentage(100);
            prov.getDefaultCell().setBorder(0);
            float[] columnWidthsCliente = new float[]{50f, 25f, 25f};
            prov.setWidths(columnWidthsCliente);
            prov.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliNom = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cliTel = new PdfPCell(new Phrase("Télefono", negrita));
            PdfPCell cliDir = new PdfPCell(new Phrase("Dirección", negrita));
            cliNom.setBorder(Rectangle.NO_BORDER);
            cliTel.setBorder(Rectangle.NO_BORDER);
            cliDir.setBorder(Rectangle.NO_BORDER);
            prov.addCell(cliNom);
            prov.addCell(cliTel);
            prov.addCell(cliDir);
            String prove = "SELECT * FROM proveedor WHERE nombre = ?";
            try {
                ps = con.prepareStatement(prove);
                ps.setString(1, proveedor);
                rs = ps.executeQuery();
                if (rs.next()) {
                    prov.addCell(rs.getString("nombre"));
                    prov.addCell(rs.getString("telefono"));
                    prov.addCell(rs.getString("direccion") + "\n\n");
                } else {
                    prov.addCell("Publico en General");
                    prov.addCell("S/N");
                    prov.addCell("S/N" + "\n\n");
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            doc.add(prov);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{10f, 50f, 15f, 15f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell c1 = new PdfPCell(new Phrase("Cant.", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Descripción.", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("P. unt.", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("P. Total", negrita));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            String product = "SELECT d.id, d.id_compra, d.id_producto, d.precio, d.cantidad, p.id, p.id, p.nombre FROM detalle_compra d INNER JOIN productos p WHERE d.id_producto = p.id AND d.id_compra = ?";
            try {
                ps = con.prepareStatement(product);
                ps.setInt(1, idcompra);
                rs = ps.executeQuery();
                while (rs.next()) {
                    double subTotal = rs.getInt("cantidad") * rs.getDouble("precio");
                    tabla.addCell(rs.getString("cantidad"));
                    tabla.addCell(rs.getString("nombre"));
                    tabla.addCell(rs.getString("precio"));
                    tabla.addCell(String.valueOf(subTotal));
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            doc.add(tabla);
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + total);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion \n\n");
            firma.add("------------------------------------\n");
            firma.add("Firma \n");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            gr.add(mensaje);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
