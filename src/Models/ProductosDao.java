package Models;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
                String sql = "SELECT * FROM productos WHERE codigo LIKE '%" + valor + "%' OR nombre LIKE '%" + valor + "%' OR proveedor LIKE '%" + valor + "%' OR medida LIKE '%" + valor + "%' OR categoria LIKE '%" + valor + "%' OR marca LIKE '%" + valor + "%'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setPrecio_venta(rs.getDouble("precio_venta"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setMedida(rs.getString("medida"));
                pro.setMarca(rs.getString("marca"));
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
        String sql = "INSERT INTO productos (codigo, nombre, precio_compra, precio_venta, proveedor, medida, categoria, marca) VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consulta);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = con.prepareStatement(sql);
                ps.setString(1, pro.getCodigo());
                ps.setString(2, pro.getNombre());
                ps.setDouble(3, pro.getPrecio_compra());
                ps.setDouble(4, pro.getPrecio_venta());
                ps.setString(5, pro.getProveedor());
                ps.setString(6, pro.getMedida());
                ps.setString(7, pro.getCategoria());
                ps.setString(8, pro.getMarca());
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
        String sql = "UPDATE productos SET codigo = ?, nombre = ?, precio_compra = ?, precio_venta = ?, proveedor = ?, medida =?, categoria =?, marca =? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio_compra());
            ps.setDouble(4, pro.getPrecio_venta());
            ps.setString(5, pro.getProveedor());
            ps.setString(6, pro.getMedida());
            ps.setString(7, pro.getCategoria());
            ps.setString(8, pro.getMarca());
            ps.setInt(9, pro.getId());
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
                p.setMarca(rs.getString("marca"));
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
        Document doc = new Document(PageSize.A4); // Tamaño A4
        PdfWriter.getInstance(doc, archivo);
        doc.open();

        // Fuente consistente para todo el documento
        Font fuente = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
        Font fuenteNegrita = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);

        // Agregar logo
        Image img = Image.getInstance(getClass().getResource("/Assets/titulo.png"));
        img.scaleToFit(50, 50); // Ajustar el tamaño del logo
        img.setAlignment(Element.ALIGN_LEFT);
        doc.add(img);

        // Encabezado de la empresa
        Paragraph empresa = new Paragraph("Vado\nRECREO TURÍSTICO \"EL VADO\"\nJR MANCO CAPAC #690 - MORALES\nTel: 968494861\nEmail: vado@gmail.com\nWeb: www.vado.com", fuenteNegrita);
        empresa.setAlignment(Element.ALIGN_CENTER);
        doc.add(empresa);

        // Obtener y actualizar el número de boleta
        String numeroBoleta = "B001-0001"; // Este es el número inicial, debe ser recuperado de una fuente persistente y actualizado
        String boletaConfig = "SELECT numero_boleta FROM configuracion";
        String updateBoletaConfig = "UPDATE configuracion SET numero_boleta = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(boletaConfig);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                numeroBoleta = rs.getString("numero_boleta");
                int numBoleta = Integer.parseInt(numeroBoleta.split("-")[1]) + 1;
                numeroBoleta = "B001-" + String.format("%04d", numBoleta);

                // Actualizar el número de boleta en la base de datos
                try (PreparedStatement psUpdate = con.prepareStatement(updateBoletaConfig)) {
                    psUpdate.setString(1, numeroBoleta);
                    psUpdate.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        // Información de la boleta
        Paragraph boletaInfo = new Paragraph("R.U.C. 20100100100\nBOLETA DE VENTA ELECTRÓNICA\nNº " + numeroBoleta, fuenteNegrita);
        boletaInfo.setAlignment(Element.ALIGN_RIGHT);
        doc.add(boletaInfo);

        // Espacio
        doc.add(Chunk.NEWLINE);

        // Información del cliente
        Paragraph cli = new Paragraph("Cliente: " + Cliente + "\nDirección: ...\nDNI: ...", fuente);
        cli.setAlignment(Element.ALIGN_LEFT);
        doc.add(cli);

        // Observaciones
        Paragraph observaciones = new Paragraph("Observaciones: ...", fuente);
        observaciones.setAlignment(Element.ALIGN_LEFT);
        doc.add(observaciones);

        // Espacio
        doc.add(Chunk.NEWLINE);

        // Tabla de productos
        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.getDefaultCell().setBorder(Rectangle.BOX);
        float[] columnWidths = new float[]{10f, 15f, 40f, 10f, 15f, 15f, 15f};
        tabla.setWidths(columnWidths);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell c1 = new PdfPCell(new Phrase("Cant.", fuenteNegrita));
        PdfPCell c2 = new PdfPCell(new Phrase("U.M.", fuenteNegrita));
        PdfPCell c3 = new PdfPCell(new Phrase("Descripción", fuenteNegrita));
        PdfPCell c4 = new PdfPCell(new Phrase("P. Unit.", fuenteNegrita));
        PdfPCell c5 = new PdfPCell(new Phrase("Importe", fuenteNegrita));
        PdfPCell c6 = new PdfPCell(new Phrase("Fecha Emisión", fuenteNegrita));
        PdfPCell c7 = new PdfPCell(new Phrase("Cond. de Pago", fuenteNegrita));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
        c6.setHorizontalAlignment(Element.ALIGN_CENTER);
        c7.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        tabla.addCell(c2);
        tabla.addCell(c3);
        tabla.addCell(c4);
        tabla.addCell(c5);
        tabla.addCell(c6);
        tabla.addCell(c7);

        // Añadir productos
        String product = "SELECT d.cantidad, p.nombre, d.precio FROM detalle_venta d INNER JOIN productos p ON d.id_producto = p.id WHERE d.id_venta = ?";
        try {
            Connection con = cn.getConnection();
            PreparedStatement ps = con.prepareStatement(product);
            ps.setInt(1, idventa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double subTotal = rs.getInt("cantidad") * rs.getDouble("precio");
                tabla.addCell(new Phrase(rs.getString("cantidad"), fuente));
                tabla.addCell(new Phrase("UND", fuente));
                tabla.addCell(new Phrase(rs.getString("nombre"), fuente));
                tabla.addCell(new Phrase(String.format("%.2f", rs.getDouble("precio")), fuente));
                tabla.addCell(new Phrase(String.format("%.2f", subTotal), fuente));
                tabla.addCell(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(date), fuente));
                tabla.addCell(new Phrase("Contado", fuente));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        doc.add(tabla);
        
        // Espacio
        doc.add(Chunk.NEWLINE);

        // Total
        PdfPTable totales = new PdfPTable(2);
        totales.setWidthPercentage(100);
        totales.setWidths(new float[]{80f, 20f});
        totales.addCell(new Phrase("Importe Total (S/)", fuenteNegrita));
        totales.addCell(new Phrase(total, fuente));
        doc.add(totales);
        
        // Espacio
        doc.add(Chunk.NEWLINE);
        
        double totalAmount = Double.parseDouble(total);
        Paragraph info = new Paragraph("SON: " + convertirNumeroALetras(totalAmount) + "\n\n", fuente);
        info.setAlignment(Element.ALIGN_LEFT);
        doc.add(info);

        // Espacio
        doc.add(Chunk.NEWLINE);

        // Código QR
        BarcodeQRCode qrCode = new BarcodeQRCode("https://www.facturas.net", 100, 100, null);
        Image qrImage = qrCode.getImage();
        qrImage.scaleAbsolute(50, 50);
        qrImage.setAlignment(Element.ALIGN_LEFT);
        doc.add(qrImage);

        // Mensaje final
        Paragraph mensajeFinal = new Paragraph("Representación impresa de BOLETA DE VENTA ELECTRÓNICA\nAutorizado por Resolución 0340050007241/SUNAT", fuenteNegrita);
        mensajeFinal.setAlignment(Element.ALIGN_CENTER);
        doc.add(mensajeFinal);

        doc.close();
        archivo.close();
        Desktop.getDesktop().open(salida);
    } catch (DocumentException | IOException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
}

// Método para convertir el monto total en letras
private String convertirNumeroALetras(double total) {
    int parteEntera = (int) total;
    int parteDecimal = (int) Math.round((total - parteEntera) * 100);
    return NumeroALetras.convertNumberToLetter(parteEntera) + " CON " + String.format("%02d", parteDecimal) + "/100 SOLES";
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
        String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        File salida = new File(url + "/boleta.pdf");
        FileOutputStream archivo = new FileOutputStream(salida);
        Document doc = new Document(new Rectangle(80 * 2.835f, 297 * 2.835f)); // 80mm x 297mm
        PdfWriter.getInstance(doc, archivo);
        doc.open();

        // Fuente consistente para todo el documento
        Font fuente = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL, BaseColor.BLACK);
        Font fuenteNegrita = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD, BaseColor.BLUE);

        // Agregar logo
        Image img = Image.getInstance(getClass().getResource("/Assets/titulo.png"));
        img.scaleToFit(40, 40); // Ajustar el tamaño del logo para que se ajuste al ancho
        img.setAlignment(Element.ALIGN_LEFT);
        doc.add(img);

        // Información fija de la empresa
        String nombreEmpresa = "";
        String ruc = "";
        String direccion = "";
        String telefono = "";
        String email = "";

        // Información de la empresa desde la base de datos
        String config = "SELECT * FROM configuracion";
        String mensaje = "";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(config);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nombreEmpresa = rs.getString("nombre");
                ruc = rs.getString("ruc");
                direccion = rs.getString("direccion");
                telefono = rs.getString("telefono");
                email = rs.getString("email");
                mensaje = rs.getString("mensaje");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        Paragraph empresaInfoFija = new Paragraph(
            nombreEmpresa + "\n" +
            "RUC: " + ruc + "\n" +
            direccion + "\n" +
            "Teléfono: " + telefono + "\n" +
            //"Email: " + email + "\n\n" +
            "BOLETA DE COMPRA ELECTRÓNICA\nNúmero: B001-001\n\n", 
            fuenteNegrita
        );
        empresaInfoFija.setAlignment(Element.ALIGN_CENTER);
        doc.add(empresaInfoFija);

        // Fecha y folio
        Paragraph fecha = new Paragraph("Fecha y hora: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date) + "\n\n", fuente);
        fecha.setAlignment(Element.ALIGN_RIGHT);
        doc.add(fecha);

        // Información del proveedor
        PdfPTable prov = new PdfPTable(4); // Actualizar a 4 columnas
        prov.setWidthPercentage(100);
        prov.getDefaultCell().setBorder(0);
        float[] columnWidthsCliente = new float[]{30f, 30f, 30f, 30f}; // Ajuste de las columnas para que quepan en el espacio
        prov.setWidths(columnWidthsCliente);
        prov.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Encabezados de la tabla
        PdfPCell cliNom = new PdfPCell(new Phrase("Nombre", fuenteNegrita));
        PdfPCell cliCOD = new PdfPCell(new Phrase("CodCompra", fuenteNegrita));
        PdfPCell cliTel = new PdfPCell(new Phrase("Teléfono", fuenteNegrita));
        PdfPCell cliDir = new PdfPCell(new Phrase("Dirección", fuenteNegrita));

        cliNom.setBorder(Rectangle.NO_BORDER);
        cliCOD.setBorder(Rectangle.NO_BORDER);
        cliTel.setBorder(Rectangle.NO_BORDER);
        cliDir.setBorder(Rectangle.NO_BORDER);

        prov.addCell(cliNom);
        prov.addCell(cliCOD);
        prov.addCell(cliTel);
        prov.addCell(cliDir);

        String prove = "SELECT * FROM proveedor WHERE nombre = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(prove)) {
            ps.setString(1, proveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Aquí puedes establecer un número de 6 dígitos para CodCompra
                    String codCompra = String.format("%06d", idcompra);

                    prov.addCell(new Phrase(rs.getString("nombre"), fuente));
                    prov.addCell(new Phrase(codCompra, fuente)); // Agregamos el número de 6 dígitos
                    prov.addCell(new Phrase(rs.getString("telefono"), fuente));

                    // Ajustar la dirección para que se ajuste al ancho de la celda
                    PdfPCell direccionCell = new PdfPCell(new Phrase(rs.getString("direccion"), fuente));
                    direccionCell.setBorder(Rectangle.NO_BORDER);
                    direccionCell.setNoWrap(false); // Permitir ajuste de texto dentro de la celda
                    prov.addCell(direccionCell);
                } else {
                    // Cuando no hay proveedor encontrado, asignamos valores por defecto
                    prov.addCell(new Phrase("Publico en General", fuente));
                    prov.addCell(new Phrase("S/N", fuente)); // CodCompra por defecto
                    prov.addCell(new Phrase("S/N", fuente));
                    prov.addCell(new Phrase("S/N" + "\n\n", fuente)); // Dirección por defecto
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        doc.add(prov);

        // Tabla de productos
        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.getDefaultCell().setBorder(0);
        float[] columnWidths = new float[]{10f, 50f, 15f, 15f};
        tabla.setWidths(columnWidths);
        tabla.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell c1 = new PdfPCell(new Phrase("Cant.", fuenteNegrita));
        PdfPCell c2 = new PdfPCell(new Phrase("Artículo", fuenteNegrita));
        PdfPCell c3 = new PdfPCell(new Phrase("P. Unit.", fuenteNegrita));
        PdfPCell c4 = new PdfPCell(new Phrase("P. Total", fuenteNegrita));
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

        double subtotal = 0.0;
        String product = "SELECT d.cantidad, p.nombre, d.precio FROM detalle_compra d INNER JOIN productos p ON d.id_producto = p.id WHERE d.id_compra = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(product)) {
            ps.setInt(1, idcompra);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double subTotal = rs.getInt("cantidad") * rs.getDouble("precio");
                    subtotal += subTotal;
                    tabla.addCell(new Phrase(rs.getString("cantidad"), fuente));
                    tabla.addCell(new Phrase(rs.getString("nombre"), fuente));
                    tabla.addCell(new Phrase(String.format("%.2f", rs.getDouble("precio")), fuente));
                    tabla.addCell(new Phrase(String.format("%.2f", subTotal), fuente));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        doc.add(tabla);

        double igv = subtotal * 0;
        double totalVenta = subtotal + igv;

        // Total y resumen de pagos
        Paragraph totales = new Paragraph(String.format(
                "\n\nSub-total: S/ %.2f\nIGV: S/ %.2f\nTotal venta: S/ %.2f\n\n", subtotal, igv, totalVenta), fuente);
        totales.setAlignment(Element.ALIGN_RIGHT);
        doc.add(totales);

        Paragraph son = new Paragraph(String.format("Son: %s con %d/100 soles\n\n", NumeroALetras.convertNumberToLetter((int) totalVenta), (int) ((totalVenta - (int) totalVenta) * 100)), fuente);
        son.setAlignment(Element.ALIGN_LEFT);
        doc.add(son);

        // Forma de pago
        Paragraph formaDePago = new Paragraph("Forma de pago\n\n", fuenteNegrita);
        formaDePago.setAlignment(Element.ALIGN_LEFT);
        doc.add(formaDePago);

        PdfPTable pago = new PdfPTable(3);
        pago.setWidthPercentage(100);
        pago.getDefaultCell().setBorder(0);
        float[] columnWidthsPago = new float[]{50f, 25f, 25f};
        pago.setWidths(columnWidthsPago);
        pago.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell fpEfectivo = new PdfPCell(new Phrase("Efectivo", fuenteNegrita));
        PdfPCell fpPagoCon = new PdfPCell(new Phrase("Pago con", fuenteNegrita));
        PdfPCell fpVuelto = new PdfPCell(new Phrase("Vuelto", fuenteNegrita));
        fpEfectivo.setBorder(Rectangle.NO_BORDER);
        fpPagoCon.setBorder(Rectangle.NO_BORDER);
        fpVuelto.setBorder(Rectangle.NO_BORDER);
        pago.addCell(fpEfectivo);
        pago.addCell(fpPagoCon);
        pago.addCell(fpVuelto);

        pago.addCell(new Phrase("S/ " + total, fuente));
        pago.addCell(new Phrase("S/ 0.00", fuente)); // Assuming no payment with other methods
        pago.addCell(new Phrase("S/ 0.00", fuente)); // Assuming no change required
        doc.add(pago);

        // Firma
        Paragraph firma = new Paragraph("\nCancelación\n\n------------------------------------\nFirma\n", fuenteNegrita);
        firma.setAlignment(Element.ALIGN_CENTER);
        doc.add(firma);

        // Mensaje
        Paragraph gr = new Paragraph(mensaje, fuenteNegrita);
        gr.setAlignment(Element.ALIGN_CENTER);
        doc.add(gr);

        // Agradecimiento
        Paragraph agradecimiento = new Paragraph("\n\nBienes Producidos y Consumidos en la Amazonía!!", fuenteNegrita);
        agradecimiento.setAlignment(Element.ALIGN_CENTER);
        doc.add(agradecimiento);

        doc.close();
        archivo.close();
        Desktop.getDesktop().open(salida);
    } catch (DocumentException | IOException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
}

    // Conexión a la base de datos (asume que tienes un método cn.getConnection() configurado)
    private static class cn {
        public static Connection getConnection() throws SQLException {
            // Implementa tu conexión a la base de datos aquí
            // Por ejemplo:
            String url = "jdbc:mysql://localhost:3306/tu_base_de_datos";
            String user = "tu_usuario";
            String password = "tu_contraseña";
            return DriverManager.getConnection(url, user, password);
        }
    }

    // Clase para convertir números a letras
    public static class NumeroALetras {
        private static final String[] UNIDADES = {
                "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve",
                "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"
        };

        private static final String[] DECENAS = {
                "", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"
        };

        private static final String[] CENTENAS = {
                "", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"
        };

        public static String convertNumberToLetter(int number) {
            if (number == 0) {
                return "cero";
            }

            if (number >= 1000000) {
                return convertNumberToLetter(number / 1000000) + " millones " + convertNumberToLetter(number % 1000000);
            } else if (number >= 1000) {
                if (number / 1000 == 1) {
                    return "mil " + convertNumberToLetter(number % 1000);
                } else {
                    return convertNumberToLetter(number / 1000) + " mil " + convertNumberToLetter(number % 1000);
                }
            } else if (number >= 100) {
                if (number == 100) {
                    return "cien";
                } else {
                    return CENTENAS[number / 100] + " " + convertNumberToLetter(number % 100);
                }
            } else if (number >= 20) {
                return DECENAS[number / 10] + (number % 10 != 0 ? " y " + UNIDADES[number % 10] : "");
            } else {
                return UNIDADES[number];
            }
        }
    }
}