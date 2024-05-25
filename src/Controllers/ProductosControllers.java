package Controllers;


import Models.Productos;
import Models.ProductosDao;
import Models.Tables;
import Paginador.PaginadoTable;
import Paginador.Paginar;
import Paginador.TotalRows;
import Views.FrmLogin;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class ProductosControllers implements ActionListener, MouseListener, KeyListener, TableModelListener {

    private Productos pro;
    private final ProductosDao proDao;
    private final PanelAdmin views;
    DefaultTableModel modeloPro = new DefaultTableModel();
    //paginador
    private PaginadoTable<Productos> paginado;
    private JComboBox<Integer> cbxFilasPermitidas;

    public ProductosControllers(Productos pro, ProductosDao proDao, PanelAdmin views) {
        this.pro = pro;
        this.proDao = proDao;
        this.views = views;
        this.views.btnGuardarPro.addActionListener(this);
        this.views.btnNuevoPro.addActionListener(this);
        this.views.btnModificarPro.addActionListener(this);
        this.views.TableProductos.addMouseListener(this);
        this.views.cbxProveedor.addKeyListener(this);
        this.views.cbxMedida.addKeyListener(this);
        this.views.cbxCat.addKeyListener(this);
        this.views.JLabelProductos.addMouseListener(this);
        this.views.JLabelCompras.addMouseListener(this);
        this.views.JLabelVentas.addMouseListener(this);
        this.views.jMenuEliminarProductos.addActionListener(this);
        this.views.jMenuReingresarProductos.addActionListener(this);
        this.views.txtBuscarPro.addKeyListener(this);
        this.views.txtNombrePro.addKeyListener(this);
        //Nueva venta
        this.views.txtCodigoNV.addKeyListener(this);
        this.views.txtCantidadNV.addKeyListener(this);
        this.views.txtClienteNV.addKeyListener(this);
        this.views.btnGenerarVenta.addActionListener(this);
        //Compras
        this.views.txtCodigoC.addKeyListener(this);
        this.views.txtCantidadC.addKeyListener(this);
        this.views.txtProveedorC.addKeyListener(this);
        this.views.btnGenerarC.addActionListener(this);
        this.views.txtCantidadC.setEnabled(false);
        this.views.btnPdfV.addActionListener(this);
        this.views.btnPdfC.addActionListener(this);

        this.views.TableProductos.getModel().addTableModelListener(this);
        this.views.txtPagarCon.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarPro) {
            if (views.txtCodigoPro.getText().equals("")
                    || views.txtNombrePro.getText().equals("")
                    || views.txtPrecioCompraPro.getText().equals("")
                    || views.txtPrecioVentaPro.getText().equals("")
                    || views.cbxProveedor.getSelectedItem().equals("")
                    || views.cbxMedida.getSelectedItem().equals("")
                    || views.cbxCat.getSelectedItem().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios");
                FrmLogin l = new FrmLogin();
                l.advertencia("Todos los campos son obligatorios");
            } else {
                pro.setCodigo(views.txtCodigoPro.getText());
                pro.setNombre(views.txtNombrePro.getText());
                pro.setPrecio_compra(Double.parseDouble(views.txtPrecioCompraPro.getText()));
                pro.setPrecio_venta(Double.parseDouble(views.txtPrecioVentaPro.getText()));
                pro.setProveedor(views.cbxProveedor.getSelectedItem().toString());
                pro.setMedida(views.cbxMedida.getSelectedItem().toString());
                pro.setCategoria(views.cbxCat.getSelectedItem().toString());
                if (proDao.registrar(pro)) {
                    Nuevo();
                    Listar();
                    //JOptionPane.showMessageDialog(null, "Producto Registrado");
                    FrmLogin l = new FrmLogin();
                    l.exito("Producto Registrado");
                } else {
                    //JOptionPane.showMessageDialog(null, "El producto ya existe");
                    FrmLogin l = new FrmLogin();
                    l.error("El producto ya existe");
                }
            }
        } else if (e.getSource() == views.btnModificarPro) {
            if (views.txtCodigoPro.getText().equals("")
                    || views.txtNombrePro.getText().equals("")
                    || views.txtPrecioCompraPro.getText().equals("")
                    || views.txtPrecioVentaPro.getText().equals("")
                    || views.cbxProveedor.getSelectedItem().equals("")
                    || views.cbxMedida.getSelectedItem().equals("")
                    || views.cbxCat.getSelectedItem().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios");
                FrmLogin l = new FrmLogin();
              l.advertencia("Todos los campos son obligatorios");
            } else {
                pro.setCodigo(views.txtCodigoPro.getText());
                pro.setNombre(views.txtNombrePro.getText());
                pro.setPrecio_compra(Double.parseDouble(views.txtPrecioCompraPro.getText()));
                pro.setPrecio_venta(Double.parseDouble(views.txtPrecioVentaPro.getText()));
                pro.setProveedor(views.cbxProveedor.getSelectedItem().toString());
                pro.setMedida(views.cbxMedida.getSelectedItem().toString());
                pro.setCategoria(views.cbxCat.getSelectedItem().toString());
                pro.setId(Integer.parseInt(views.txtIdPro.getText()));
                if (proDao.modificar(pro)) {
                    Nuevo();
                    Listar();
                    //JOptionPane.showMessageDialog(null, "Producto Modificado");
                    FrmLogin l = new FrmLogin();
                    l.exito("Producto modificado");
                }
            }
        } else if (e.getSource() == views.jMenuEliminarProductos) {
            if (views.txtIdPro.getText().equals("") || views.txtIdPro.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para eliminar");
            } else{
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdPro.getText());
                    if (proDao.accion("Inactivo", id)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Producto Eliminado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Producto eliminado");
                    }
                }
            }
        } else if (e.getSource() == views.jMenuReingresarProductos) {
            if (views.txtIdPro.getText().equals("") || views.txtIdPro.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para reingresar");
            } else {
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de reingresar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdPro.getText());
                    if (proDao.accion("Activo", id)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Producto Reingresado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Producto reingresado");
                    }
                }
            }
        } else if (e.getSource() == views.btnNuevoPro) {
            Nuevo();
        } else if (e.getSource() == views.btnGenerarVenta) {
            if (proDao.caja(views.txtUserLogin.getText())) {
                if (views.TableNuevaVenta.getRowCount() > 0) {
                    guardarVenta();
                    guardarDetalle();
                    actualizarStock();
                    limpiarTabla(views.TableNuevaVenta);
                    int idv = proDao.IdVenta("ventas");
                    proDao.pdfV(idv, views.txtClienteNV.getSelectedItem().toString(), views.txtTotalPagar.getText(), "admin");
                    views.txtCantidadNV.setEnabled(false);
                    //JOptionPane.showMessageDialog(null, "Venta Generada");
                    FrmLogin l = new FrmLogin();
                    l.exito("Venta generada");
                } else {
                    //JOptionPane.showMessageDialog(null, "Agrega productos a la tabla para proceder con la venta");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("Agrega productos para proceder con la venta");
                    views.txtCodigoNV.requestFocus();
                }
            } else {
                //JOptionPane.showMessageDialog(null, "Todavia no has abierto la caja");
                FrmLogin l = new FrmLogin();
                l.advertencia("Todavia no has abierto la caja");
            }
        } else if (e.getSource() == views.btnGenerarC) {
            if (views.TableCompra.getRowCount() > 0) {
                guardarCompra();
                guardarDC();
                actualizarStockC();
                int id = proDao.IdVenta("compras");
                proDao.pdfC(id, views.txtProveedorC.getSelectedItem().toString(), views.txtTotalPagarC.getText());
                limpiarTabla(views.TableCompra);
                views.txtCantidadC.setEnabled(false);
                //JOptionPane.showMessageDialog(null, "Compra Generada");
                FrmLogin l = new FrmLogin();
                l.exito("Compra generada");
            } else {
                //JOptionPane.showMessageDialog(null, "Agrega productos a la tabla para proceder con la Compra");
                FrmLogin l = new FrmLogin();
                l.advertencia("Agrega productos para proceder con la compra");
                views.txtCodigoC.requestFocus();
            }
        } else if (e.getSource() == views.btnPdfV) {
            if (views.txtVentasId.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Selecciona una fila");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila");
            } else {
                int idv = Integer.parseInt(views.txtVentasId.getText());
                proDao.pdfV(idv, views.txtVentasCliente.getText(), views.txtVentasTotal.getText(), views.txtVentasUsuario.getText());
            }
        } else if (e.getSource() == views.btnPdfC) {
            if (views.txtVentasId1.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Selecciona una fila");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila");
            } else {
                int idc = Integer.parseInt(views.txtVentasId1.getText());
                proDao.pdfC(idc, views.txtVentasCliente1.getText(), views.txtVentasTotal1.getText());
            }
        } else if (e.getSource() == cbxFilasPermitidas) {
            paginado.eventosPag(cbxFilasPermitidas);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableProductos) {
            int fila = views.TableProductos.rowAtPoint(e.getPoint());
            views.txtIdPro.setText(views.TableProductos.getValueAt(fila, 0).toString());
            String estado = views.TableProductos.getValueAt(fila, 5).toString();
            if (estado.equals("Inactivo")){
                views.JMenuEliminarPro.setVisible(false);
                views.JMenuReingresarPro.setVisible(true);
            }else{
                views.JMenuEliminarPro.setVisible(true);
                views.JMenuReingresarPro.setVisible(false);
            }
            pro = proDao.editar(Integer.parseInt(views.txtIdPro.getText()));
            views.txtCodigoPro.setText(pro.getCodigo());
            views.txtNombrePro.setText(pro.getNombre());
            views.txtPrecioCompraPro.setText("" + pro.getPrecio_compra());
            views.txtPrecioVentaPro.setText("" + pro.getPrecio_venta());
            views.cbxMedida.setSelectedItem(pro.getMedida());
            views.cbxCat.setSelectedItem(pro.getCategoria());
            views.cbxProveedor.setSelectedItem(pro.getProveedor());
        } else if (e.getSource() == views.JLabelProductos) {
            views.jTabbedPane1.setSelectedIndex(2);
            //menu(views.MenuProductos);
    
            views.cbxProveedor.removeAllItems();
            views.cbxMedida.removeAllItems();
            views.cbxCat.removeAllItems();
            proDao.llenarCombo(views.cbxProveedor, "proveedor");
            proDao.llenarCombo(views.cbxMedida, "medidas");
            proDao.llenarCombo(views.cbxCat, "categorias");
            Listar();
        } else if (e.getSource() == views.JLabelCompras) {
            views.jTabbedPane1.setSelectedIndex(6);
            //menu(views.MenuCompras);

            proDao.llenarCombo(views.txtProveedorC, "proveedor");
        } else if (e.getSource() == views.JLabelVentas) {
            views.jTabbedPane1.setSelectedIndex(1);
            //menu(views.MenuVentas);

            views.txtClienteNV.removeAllItems();
            proDao.llenarCombo(views.txtClienteNV, "clientes");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    private void Nuevo() {
        views.txtIdPro.setText("");
        views.txtCodigoPro.setText("");
        views.txtNombrePro.setText("");
        views.txtPrecioCompraPro.setText("");
        views.txtPrecioVentaPro.setText("");
        views.cbxMedida.setSelectedItem("");
        views.cbxCat.setSelectedItem("");
        views.cbxProveedor.setSelectedItem("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char caracter = e.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            e.setKeyChar(Character.toUpperCase(caracter));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == views.txtCodigoNV) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String cod = views.txtCodigoNV.getText();
                pro = proDao.buscarProducto(cod);
                if (pro.getNombre() == null) {
                    //JOptionPane.showMessageDialog(null, "Producto no encontrado, ingrese otro codigo");
                    FrmLogin l = new FrmLogin();
                    l.error("Producto no encontrado, ingrese otro codigo");
                    views.txtCodigoNV.requestFocus();
                } else {
                    views.txtCantidadNV.setEnabled(true);
                    views.txtIdNV.setText("" + pro.getId());
                    views.txtProductoNV.setText(pro.getNombre());
                    views.txtStockDisponible.setText("" + pro.getCantidad());
                    views.txtPrecioNV.setText("" + pro.getPrecio_venta());
                    views.txtTotalNV.setText("" + pro.getPrecio_venta());
                    views.txtCantidadNV.setText("1");
                    views.txtCantidadNV.requestFocus();
                }
            }
        } else if (e.getSource() == views.txtCantidadNV) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int cant = Integer.parseInt(views.txtCantidadNV.getText());
                int stock = Integer.parseInt(views.txtStockDisponible.getText());
                if (cant < 1 || cant > stock) {
                    //JOptionPane.showMessageDialog(null, "Verifica el stock disponible del producto");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("Verifica el stock disponible del producto");
                    views.txtCantidadNV.requestFocus();
                } else {
                    int item = 0;
                    item = item + 1;
                    int id = Integer.parseInt(views.txtIdNV.getText());
                    String producto = views.txtProductoNV.getText();
                    double precio = Double.parseDouble(views.txtPrecioNV.getText());
                    int cantidad = Integer.parseInt(views.txtCantidadNV.getText());
                    DefaultTableModel tmp = (DefaultTableModel) views.TableNuevaVenta.getModel();
                    for (int i = 0; i < views.TableNuevaVenta.getRowCount(); i++) {
                        if (views.TableNuevaVenta.getValueAt(i, 0).equals(id)) {
                            //JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            FrmLogin l = new FrmLogin();
                            l.advertencia("El producto ya está registrado");
                            limpiarBusqueda();
                            views.txtCodigoNV.requestFocus();
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(producto);
                    lista.add(cantidad);
                    lista.add(precio);
                    lista.add(precio * cantidad);
                    Object[] ob = new Object[5];
                    ob[0] = lista.get(1);
                    ob[1] = lista.get(2);
                    ob[2] = lista.get(3);
                    ob[3] = lista.get(4);
                    ob[4] = lista.get(5);
                    tmp.addRow(ob);
                    views.TableNuevaVenta.setModel(tmp);
                    actualizaPago();
                    limpiarBusqueda();
                    views.txtCantidadNV.setEnabled(false);
                    views.txtCodigoNV.requestFocus();
                    
                }
            }
        } else if (e.getSource() == views.txtCodigoC) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String cod = views.txtCodigoC.getText();
                pro = proDao.buscarProducto(cod);
                if (pro.getNombre() == null) {
                    //JOptionPane.showMessageDialog(null, "Producto no encontrado, ingrese otro codigo");
                    FrmLogin l = new FrmLogin();
                    l.error("Producto no encontrado, ingrese otro codigo");
                    views.txtCodigoC.requestFocus();
                } else {
                    views.txtIdC.setText("" + pro.getId());
                    views.txtProductoC.setText(pro.getNombre());
                    views.txtPrecioC.setText("" + pro.getPrecio_compra());
                    views.txtTotalC.setText("" + pro.getPrecio_compra());
                    views.txtCantidadC.setEnabled(true);
                    views.txtCantidadC.requestFocus();
                }
            }
        } else if (e.getSource() == views.txtCantidadC) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int cant = Integer.parseInt(views.txtCantidadC.getText());
                if (cant < 1 || validarNum(views.txtCantidadC.getText().trim())) {
                    //JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("Ingrese una cantidad válida");
                    views.txtCantidadC.requestFocus();
                } else {
                    int item = 0;
                    item = item + 1;
                    int id = Integer.parseInt(views.txtIdC.getText());
                    String producto = views.txtProductoC.getText();
                    double precio = Double.parseDouble(views.txtPrecioC.getText());
                    int cantidad = Integer.parseInt(views.txtCantidadC.getText());
                    DefaultTableModel tmp = (DefaultTableModel) views.TableCompra.getModel();
                    for (int i = 0; i < views.TableCompra.getRowCount(); i++) {
                        if (views.TableCompra.getValueAt(i, 0).equals(id)) {
                            //JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            FrmLogin l = new FrmLogin();
                            l.advertencia("El producto ya está registrado");
                            limpiarBusqueda();
                            views.txtCodigoC.requestFocus();
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(producto);
                    lista.add(cantidad);
                    lista.add(precio);
                    lista.add(precio * cantidad);
                    Object[] ob = new Object[5];
                    ob[0] = lista.get(1);
                    ob[1] = lista.get(2);
                    ob[2] = lista.get(3);
                    ob[3] = lista.get(4);
                    ob[4] = lista.get(5);
                    tmp.addRow(ob);
                    views.TableCompra.setModel(tmp);
                    actualizaPagoC();
                    limpiarBusquedaC();
                    views.txtCantidadC.setEnabled(false);
                    views.txtCodigoC.requestFocus();
                }
            }
        }
    }
    private static boolean validarNum(String campo) {
        return campo.matches("[0-9],[1-5]");
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarPro) {
            views.jTabbedPane1.setSelectedIndex(2);
            //menu(views.MenuProductos);
            Listar();
        } else if (e.getSource() == views.txtCantidadNV) {
            if (views.txtCantidadNV.getText().equals("")) {
                views.txtTotalNV.setText("");
            } else {
                double total = Double.parseDouble(views.txtPrecioNV.getText()) * Double.parseDouble(views.txtCantidadNV.getText());
                views.txtTotalNV.setText("" + total);
            }
        } else if (e.getSource() == views.txtPagarCon) {
            if (views.txtPagarCon.getText().equals("")) {
                views.txtVuelto.setText("");
            } else {
                double pagar = Double.parseDouble(views.txtPagarCon.getText());
                double monto = Double.parseDouble(views.txtTotalPagar.getText());
                views.txtVuelto.setText("" + (pagar - monto));
            }
        }
    }

    private void limpiarBusqueda() {
        views.txtIdNV.setText("");
        views.txtProductoNV.setText("");
        views.txtStockDisponible.setText("");
        views.txtPrecioNV.setText("");
        views.txtTotalNV.setText("");
        views.txtCantidadNV.setText("");
        views.txtCodigoNV.setText("");
    }

    private void limpiarBusquedaC() {
        views.txtIdC.setText("");
        views.txtProductoC.setText("");
        views.txtPrecioC.setText("");
        views.txtTotalC.setText("");
        views.txtCantidadC.setText("");
        views.txtCodigoC.setText("");
    }

    private void actualizaPago() {
        double total = 0.00;
        int numeroFilas = views.TableNuevaVenta.getRowCount();
        for (int a = 0; a < numeroFilas; a++) {
            total = total + Double.parseDouble(String.valueOf(views.TableNuevaVenta.getModel().getValueAt(a, 4)));
        }
        views.txtTotalPagar.setText("" + total);
    }

    private void guardarVenta() {
        String cliente;
        String total = views.txtTotalPagar.getText();
        cliente = views.txtClienteNV.getSelectedItem().toString();
        if (views.txtClienteNV.getSelectedItem().toString().equals("")) {
            cliente = "Publico en general";
        }
        proDao.GuardarVenta("ventas", "cliente", cliente, "admin", total);

    }

    private void guardarDetalle() {
        int idv = proDao.IdVenta("ventas");
        for (int i = 0; i < views.TableNuevaVenta.getRowCount(); i++) {
            int idp = Integer.parseInt(views.TableNuevaVenta.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(views.TableNuevaVenta.getValueAt(i, 2).toString());
            double pre = Double.parseDouble(views.TableNuevaVenta.getValueAt(i, 3).toString());
            proDao.GuardarDetalle("detalle_venta", "id_venta", idv, idp, pre, cant);
        }
    }

    //Compras
    private void guardarCompra() {
        String proveedor;
        String total = views.txtTotalPagarC.getText();
        proveedor = views.txtProveedorC.getSelectedItem().toString();
        if (proveedor.equals("")) {
            proveedor = "Publico en general";
        }
        proDao.GuardarVenta("compras", "proveedor", proveedor, "admin", total);

    }

    private void guardarDC() {
        int idc = proDao.IdVenta("compras");
        for (int i = 0; i < views.TableCompra.getRowCount(); i++) {
            int idp = Integer.parseInt(views.TableCompra.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(views.TableCompra.getValueAt(i, 2).toString());
            double pre = Double.parseDouble(views.TableCompra.getValueAt(i, 3).toString());
            proDao.GuardarDetalle("detalle_compra", "id_compra", idc, idp, pre, cant);
        }
    }

    private void actualizaPagoC() {
        double total = 0.00;
        int numeroFilas = views.TableCompra.getRowCount();
        for (int a = 0; a < numeroFilas; a++) {
           total = total + Double.parseDouble(String.valueOf(views.TableCompra.getModel().getValueAt(a, 4)));
        }
        views.txtTotalPagarC.setText("" + total);
    }

    private void limpiarTabla(JTable table) {
        DefaultTableModel tmp = (DefaultTableModel) table.getModel();
        int filas = table.getRowCount();

        for (int a = 0; filas > a; a++) {
            tmp.removeRow(0);
        }

    }

    private void actualizarStock() {
        for (int i = 0; i < views.TableNuevaVenta.getRowCount(); i++) {
            int idP = Integer.parseInt(views.TableNuevaVenta.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(views.TableNuevaVenta.getValueAt(i, 2).toString());
            proDao.buscarId(idP);
            int sa = pro.getCantidad() - cant;
            proDao.actualizarStock(sa, idP);
        }
    }

    private void actualizarStockC() {
        for (int i = 0; i < views.TableCompra.getRowCount(); i++) {
            int idP = Integer.parseInt(views.TableCompra.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(views.TableCompra.getValueAt(i, 2).toString());
            int id = proDao.buscarId(idP);
            int sa = (pro.getCantidad() + cant);
            proDao.actualizarStock(sa, id);
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        paginado.paginate();
    }

    //paginador
    private void Listar() {
        Tables color = new Tables();
        views.TableProductos.setDefaultRenderer(views.TableProductos.getColumnClass(0), color);
        JTableHeader header = views.TableProductos.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
        views.JPaginarPro.removeAll();
        views.TableProductos.setModel(ModeloTable());
        TotalRows<Productos> proL = ListarProductos();
        paginado = new PaginadoTable(views.TableProductos, proL, new int[]{20, 50, 100, 200}, 20);
        paginado.crearPermitidas(views.JPaginarPro);
        cbxFilasPermitidas = paginado.getCbxFilasPermitidas();
        cbxFilasPermitidas.addActionListener(this);
        views.TableProductos.getModel().addTableModelListener(this);
        cbxFilasPermitidas.setSelectedItem(Integer.parseInt("20"));
    }

    public TotalRows<Productos> ListarProductos() {
        List<Productos> listaProducto = proDao.Listar(views.txtBuscarPro.getText());
        return new TotalRows<Productos>() {
            @Override
            public int getTotalRowCount() {
                return listaProducto.size();
            }

            @Override
            public List<Productos> getRows(int inicio, int fin) {
                return listaProducto.subList(inicio, fin);
            }

        };
    }

    private TableModel ModeloTable() {
        return new Paginar<Productos>() {
            @Override
            public Object getValueAt(Productos t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getNombre();
                    case 2:
                        return t.getPrecio_venta();
                    case 3:
                        return t.getCantidad();
                    case 4:
                        return t.getMedida();
                    case 5:
                        return t.getEstado();
                }
                return null;
            }

            @Override
            public String getColumnName(int columna) {
                switch (columna) {
                    case 0:
                        return "Id";
                    case 1:
                        return "Productos";
                    case 2:
                        return "Precio";
                    case 3:
                        return "Cant";
                    case 4:
                        return "Medida";
                    case 5:
                        return "Estado";
                }
                return null;

            }

            @Override
            public int getColumnCount() {
                return 6;
            }

        };
    }

}
