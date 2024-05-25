
package Views;

import Controllers.CajasControllers;
import Controllers.CategoriasControllers;
import Controllers.ClientesControllers;
import Controllers.ConfigControllers;
import Controllers.MedidasControllers;
import Controllers.ProductosControllers;
import Controllers.ProveedorControllers;
import Controllers.UsuariosControllers;
import Controllers.VentasControllers;
import Models.Cajas;
import Models.CajasDao;
import Models.Categorias;
import Models.CategoriasDao;
import Models.Clientes;
import Models.ClientesDao;
import Models.Config;
import Models.ConfigDao;
import Models.DetallePedido;
import Models.Eventos;
import Models.Medidas;
import Models.MedidasDao;

import Models.Pedidos;
import Models.PedidosDao;
import Models.Platos;
import Models.PlatosDao;
import Models.Productos;
import Models.ProductosDao;
import Models.Proveedor;
import Models.ProveedorDao;
import Models.Salas;
import Models.SalasDao;
import Models.Tables;
import Models.Usuarios;
import Models.UsuariosDao;
import Models.Venta;
import Models.VentaDao;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import notification.Notification;

public class PanelAdmin extends javax.swing.JFrame {
    
    Salas sl = new Salas();
    SalasDao slDao = new SalasDao();
    Eventos event = new Eventos();

    Platos pla = new Platos();
    PlatosDao plaDao = new PlatosDao();

    Pedidos ped = new Pedidos();
    PedidosDao pedDao = new PedidosDao();
    DetallePedido detPedido = new DetallePedido();
    
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    
    Medidas med = new Medidas();
    MedidasDao medDao = new MedidasDao();
    Productos pro = new Productos();
    ProductosDao proDao = new ProductosDao();
    Clientes cli = new Clientes();
    ClientesDao clDao = new ClientesDao();
    Categorias cat = new Categorias();
    CategoriasDao catDao = new CategoriasDao();
    Proveedor prov = new Proveedor();
    ProveedorDao provDao = new ProveedorDao();
    Cajas ca = new Cajas();
    CajasDao caDao = new CajasDao();
    Config con= new Config();
    ConfigDao conDao = new ConfigDao();
    Venta v= new Venta();
    VentaDao vDao = new VentaDao();
    int item;
    double Totalpagar = 0.00;

    Date fechaActual = new Date();
    String fechaFormato = new SimpleDateFormat("yyyy-MM-dd").format(fechaActual);
//    public PanelAdmin() {
//        initComponents();      
//    }
    Usuarios us;
    public PanelAdmin(Usuarios us){
        initComponents(); 
        this.setLocationRelativeTo(null);
        this.us = us;
        if (us.getRol().equals("MOZO")) {
            JLabelConfig.setVisible(false);
            jPanel5.setVisible(true);
            JLabelUsuarios.setVisible(false);
            JLabelClientes.setVisible(false);
            JLabelCompra.setVisible(true);
            JLabelCompras.setVisible(false);
            JLabelMedidas.setVisible(false);
            JLabelProductos.setVisible(false);
            JLabelProveedor.setVisible(false);
            JLabelTotalVentas.setVisible(false);
            JLabelVenta.setVisible(true);
            JLabelTotalVentas.setVisible(true);
            JLabelVentas.setVisible(false);
            JLabelCat.setVisible(false);
            JMenuEliminarPro.setVisible(false);
            JMenuReingresarPro.setVisible(false);
            JMenuEliminarProv.setVisible(false);
            JMenuReingresarProv.setVisible(false);
            JMenuEliminarMed.setVisible(false);
            JMenuReingresarMed.setVisible(false);
            JMenuEliminarCat.setVisible(false);
            JMenuReingresarCat.setVisible(false);
            
        }
        btnCambiarPass.setText(us.getRol());
        txtUserLogin.setText(us.getUsuario());
        txtCajaLogin.setText(us.getCaja());
        txtIdLogin.setText("" + us.getId());
        us = new Usuarios();
        UsuariosDao usDao = new UsuariosDao();
        MedidasControllers Medidas = new MedidasControllers(med, medDao, this);
        CategoriasControllers Categ = new CategoriasControllers(cat, catDao, this);
        ProductosControllers p = new ProductosControllers(pro, proDao, this);
        ClientesControllers cl = new ClientesControllers(cli, clDao, this);
        ProveedorControllers proveedor = new ProveedorControllers(prov, provDao, this);
        CajasControllers caja = new CajasControllers(ca, caDao, this);
        UsuariosControllers users = new UsuariosControllers(us, usDao, this);
        ConfigControllers conf = new ConfigControllers(con, conDao, this);
        VentasControllers vent = new VentasControllers(v, vDao, this);
        this.setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPopupMedida = new javax.swing.JPopupMenu();
        JMenuEliminarMed = new javax.swing.JMenuItem();
        JMenuReingresarMed = new javax.swing.JMenuItem();
        JPopupCat = new javax.swing.JPopupMenu();
        JMenuEliminarCat = new javax.swing.JMenuItem();
        JMenuReingresarCat = new javax.swing.JMenuItem();
        JPopupClientes = new javax.swing.JPopupMenu();
        JMenuEliminarCli = new javax.swing.JMenuItem();
        JMenuReingresarCli = new javax.swing.JMenuItem();
        JPopupProveedor = new javax.swing.JPopupMenu();
        JMenuEliminarProv = new javax.swing.JMenuItem();
        JMenuReingresarProv = new javax.swing.JMenuItem();
        JPopupPro = new javax.swing.JPopupMenu();
        JMenuEliminarPro = new javax.swing.JMenuItem();
        JMenuReingresarPro = new javax.swing.JMenuItem();
        JPopupUser = new javax.swing.JPopupMenu();
        JMenuEliminarUser = new javax.swing.JMenuItem();
        JMenuReingresarUser = new javax.swing.JMenuItem();
        jPopupUsuarios = new javax.swing.JPopupMenu();
        JMenuEliminarUsuarios = new javax.swing.JMenuItem();
        JMenuReingresarUsuarios = new javax.swing.JMenuItem();
        jPopupProductos = new javax.swing.JPopupMenu();
        jMenuEliminarProductos = new javax.swing.JMenuItem();
        jMenuReingresarProductos = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        MenuCompras = new javax.swing.JPanel();
        JLabelCompras = new javax.swing.JLabel();
        MenuProductos = new javax.swing.JPanel();
        JLabelProductos = new javax.swing.JLabel();
        MenuClientes = new javax.swing.JPanel();
        JLabelClientes = new javax.swing.JLabel();
        MenuUsuarios = new javax.swing.JPanel();
        JLabelUsuarios = new javax.swing.JLabel();
        MenuProv = new javax.swing.JPanel();
        JLabelProveedor = new javax.swing.JLabel();
        MenuMedidas = new javax.swing.JPanel();
        JLabelMedidas = new javax.swing.JLabel();
        MenuCat = new javax.swing.JPanel();
        JLabelCat = new javax.swing.JLabel();
        MenuConfig = new javax.swing.JPanel();
        JLabelConfig = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        btnPlatos = new javax.swing.JButton();
        btnSala = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        MenuVentas = new javax.swing.JPanel();
        JLabelVentas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtBuscarPro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        btnCambiarPass = new javax.swing.JButton();
        txtIdLogin = new javax.swing.JTextField();
        txtUserLogin = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtCajaLogin = new javax.swing.JTextField();
        LabelVendedor1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        JLHome = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        JPanelGrafico = new javax.swing.JPanel();
        JPBarra = new javax.swing.JPanel();
        JPTorta = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        TotalPro = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        JLabelCompra = new javax.swing.JLabel();
        TotalCompras = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        TotalClientes = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        JLabelVenta = new javax.swing.JLabel();
        JLabelTotalVentas = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txtCodigoNV = new javax.swing.JTextField();
        txtCantidadNV = new javax.swing.JTextField();
        txtPrecioNV = new javax.swing.JTextField();
        txtStockDisponible = new javax.swing.JTextField();
        txtTotalNV = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnGenerarVenta = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableNuevaVenta = new javax.swing.JTable();
        btnCaja = new javax.swing.JButton();
        txtIdNV = new javax.swing.JTextField();
        txtProductoNV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtPagarCon = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        txtClienteNV = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        txtNombrePro = new javax.swing.JTextField();
        txtPrecioCompraPro = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnGuardarPro = new javax.swing.JButton();
        btnModificarPro = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtPrecioVentaPro = new javax.swing.JTextField();
        btnNuevoPro = new javax.swing.JButton();
        txtIdPro = new javax.swing.JTextField();
        cbxProveedor = new javax.swing.JComboBox<>();
        cbxMedida = new javax.swing.JComboBox<>();
        cbxCat = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProductos = new javax.swing.JTable();
        JPaginarPro = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtNombreCli = new javax.swing.JTextField();
        txtTelefonoCli = new javax.swing.JTextField();
        txtDireccionCli = new javax.swing.JTextField();
        btnGuardarCli = new javax.swing.JButton();
        btnModificarCli = new javax.swing.JButton();
        btnNuevoCli = new javax.swing.JButton();
        txtBuscarCli = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtIdCli = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableClientes = new javax.swing.JTable();
        JPaginarCli = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtNombreProv = new javax.swing.JTextField();
        txtTelefonoProv = new javax.swing.JTextField();
        btnGuardarProv = new javax.swing.JButton();
        btnModificarProv = new javax.swing.JButton();
        btnNuevoProv = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtRucProv = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDireccionProv = new javax.swing.JTextPane();
        txtBuscarProv = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txtIdProv = new javax.swing.JTextField();
        JPaginarProv = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableUsers = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtNombreUser = new javax.swing.JTextField();
        btnGuardarUser = new javax.swing.JButton();
        btnModificarUser = new javax.swing.JButton();
        btnNuevoUser = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtClaveUser = new javax.swing.JPasswordField();
        jLabel31 = new javax.swing.JLabel();
        cbxRolUser = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        txtIdUser = new javax.swing.JTextField();
        txtBuscarUser = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        cbxCajaUser = new javax.swing.JComboBox<>();
        JPaginarUser = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtCodigoC = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableCompra = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        txtCantidadC = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtPrecioC = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtTotalC = new javax.swing.JTextField();
        btnGenerarC = new javax.swing.JButton();
        txtProductoC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTotalPagarC = new javax.swing.JLabel();
        txtIdC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtProveedorC = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtMontoInicial = new javax.swing.JTextField();
        txtMontoFinal = new javax.swing.JTextField();
        txtTotalVentas = new javax.swing.JTextField();
        btnGuardarCaja = new javax.swing.JButton();
        btnModificarCaja = new javax.swing.JButton();
        btnNuevoCaja = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        txtFechaApertura = new com.toedter.calendar.JDateChooser();
        txtBuscarCaja = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtIdCaja = new javax.swing.JTextField();
        txtFechaCierre = new com.toedter.calendar.JDateChooser();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableCaja = new javax.swing.JTable();
        JPaginarCaja = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtNombreMed = new javax.swing.JTextField();
        txtNombreCortoMed = new javax.swing.JTextField();
        btnGuardarMed = new javax.swing.JButton();
        btnModificarMed = new javax.swing.JButton();
        btnNuevoMed = new javax.swing.JButton();
        txtIdMed = new javax.swing.JTextField();
        txtBuscarMed = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TableMedidas = new javax.swing.JTable();
        JPaginarMedida = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TableCat = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        txtNombreCat = new javax.swing.JTextField();
        btnGuardarCat = new javax.swing.JButton();
        btnModificarCat = new javax.swing.JButton();
        btnNuevoCat = new javax.swing.JButton();
        txtBuscarCat = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtIdCat = new javax.swing.JTextField();
        JPaginarCat = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtRucEmpresa = new javax.swing.JTextField();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        btnModificarEmpresa = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtDireccionEmpresa = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextPane();
        txtIdConfig = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        TableVentas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarVentas = new javax.swing.JTextField();
        txtVentasId = new javax.swing.JTextField();
        txtVentasTotal = new javax.swing.JTextField();
        txtVentasUsuario = new javax.swing.JTextField();
        txtVentasCliente = new javax.swing.JTextField();
        btnPdfV = new javax.swing.JButton();
        JPanelVentas = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TableCompras = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        txtBuscarVentas1 = new javax.swing.JTextField();
        txtVentasId1 = new javax.swing.JTextField();
        txtVentasTotal1 = new javax.swing.JTextField();
        txtVentasCliente1 = new javax.swing.JTextField();
        btnPdfC = new javax.swing.JButton();
        JPanelVentas1 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel118 = new javax.swing.JPanel();
        txtActual = new javax.swing.JTextField();
        txtNueva = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnCambiar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtActual1 = new javax.swing.JTextField();
        jPanel37 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        txtActual2 = new javax.swing.JTextField();
        jPanel40 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tableSala = new javax.swing.JTable();
        jPanel44 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        txtNombreSala = new javax.swing.JTextField();
        btnRegistrarSala = new javax.swing.JButton();
        btnActualizarSala = new javax.swing.JButton();
        btnNuevoSala = new javax.swing.JButton();
        btnEliminarSala = new javax.swing.JButton();
        txtIdSala = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        txtMesas = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        PanelMesas = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        txtBuscarPlato = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblTemPlatos = new javax.swing.JTable();
        btnAddPlato = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tableMenu = new javax.swing.JTable();
        jLabel78 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextPane();
        jLabel79 = new javax.swing.JLabel();
        totalMenu = new javax.swing.JLabel();
        btnGenerarPedido = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnEliminarTempPlato = new javax.swing.JButton();
        txtTempIdSala = new javax.swing.JTextField();
        txtTempNumMesa = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        btnFinalizar = new javax.swing.JButton();
        totalFinalizar = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tableFinalizar = new javax.swing.JTable();
        txtIdPedido = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        txtFechaHora = new javax.swing.JTextField();
        txtSalaFinalizar = new javax.swing.JTextField();
        txtNumMesaFinalizar = new javax.swing.JTextField();
        btnPdfPedido = new javax.swing.JButton();
        txtIdHistorialPedido = new javax.swing.JTextField();
        jPanel49 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        TablePedidos = new javax.swing.JTable();
        jLabel84 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        txtNombrePlato = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        txtPrecioPlato = new javax.swing.JTextField();
        btnGuardarPlato = new javax.swing.JButton();
        btnEditarPlato = new javax.swing.JButton();
        btnEliminarPlato = new javax.swing.JButton();
        btnNuevoPlato = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        txtIdPlato = new javax.swing.JTextField();
        jScrollPane24 = new javax.swing.JScrollPane();
        TablePlatos = new javax.swing.JTable();
        jLabel93 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        PanelSalas = new javax.swing.JPanel();
        LabelVendedor = new javax.swing.JLabel();

        JMenuEliminarMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        JMenuEliminarMed.setText("Eliminar");
        JPopupMedida.add(JMenuEliminarMed);

        JMenuReingresarMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exchange.png"))); // NOI18N
        JMenuReingresarMed.setText("Reingresar");
        JPopupMedida.add(JMenuReingresarMed);

        JMenuEliminarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        JMenuEliminarCat.setText("Eliminar");
        JPopupCat.add(JMenuEliminarCat);

        JMenuReingresarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exchange.png"))); // NOI18N
        JMenuReingresarCat.setText("Reingresar");
        JPopupCat.add(JMenuReingresarCat);

        JMenuEliminarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        JMenuEliminarCli.setText("Eliminar");
        JPopupClientes.add(JMenuEliminarCli);

        JMenuReingresarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exchange.png"))); // NOI18N
        JMenuReingresarCli.setText("Reingresar");
        JPopupClientes.add(JMenuReingresarCli);

        JMenuEliminarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        JMenuEliminarProv.setText("Eliminar");
        JMenuEliminarProv.setToolTipText("");
        JPopupProveedor.add(JMenuEliminarProv);

        JMenuReingresarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exchange.png"))); // NOI18N
        JMenuReingresarProv.setText("Reingresar");
        JPopupProveedor.add(JMenuReingresarProv);

        JMenuEliminarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        JMenuEliminarPro.setText("Eliminar");
        JPopupPro.add(JMenuEliminarPro);

        JMenuReingresarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exchange.png"))); // NOI18N
        JMenuReingresarPro.setText("Reingresar");
        JPopupPro.add(JMenuReingresarPro);

        JMenuEliminarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        JMenuEliminarUser.setText("Eliminar");
        JPopupUser.add(JMenuEliminarUser);

        JMenuReingresarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exchange.png"))); // NOI18N
        JMenuReingresarUser.setText("Reingresar");
        JPopupUser.add(JMenuReingresarUser);

        JMenuEliminarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        JMenuEliminarUsuarios.setText("Eliminar");
        jPopupUsuarios.add(JMenuEliminarUsuarios);

        JMenuReingresarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exchange.png"))); // NOI18N
        JMenuReingresarUsuarios.setText("Reingresar");
        jPopupUsuarios.add(JMenuReingresarUsuarios);

        jMenuEliminarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        jMenuEliminarProductos.setText("Eliminar");
        jPopupProductos.add(jMenuEliminarProductos);

        jMenuReingresarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/exchange.png"))); // NOI18N
        jMenuReingresarProductos.setText("Reingresar");
        jMenuReingresarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReingresarProductosActionPerformed(evt);
            }
        });
        jPopupProductos.add(jMenuReingresarProductos);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de venta");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(14, 76, 117));

        MenuCompras.setBackground(new java.awt.Color(14, 76, 117));
        MenuCompras.setFocusTraversalPolicyProvider(true);

        JLabelCompras.setBackground(new java.awt.Color(255, 255, 255));
        JLabelCompras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelCompras.setForeground(new java.awt.Color(255, 255, 255));
        JLabelCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/carro-de-compras-rapido.png"))); // NOI18N
        JLabelCompras.setText("Nueva Compra");
        JLabelCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelCompras.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelCompras.setIconTextGap(40);
        JLabelCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelComprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelComprasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuComprasLayout = new javax.swing.GroupLayout(MenuCompras);
        MenuCompras.setLayout(MenuComprasLayout);
        MenuComprasLayout.setHorizontalGroup(
            MenuComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuComprasLayout.setVerticalGroup(
            MenuComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        MenuProductos.setBackground(new java.awt.Color(14, 76, 117));
        MenuProductos.setFocusTraversalPolicyProvider(true);

        JLabelProductos.setBackground(new java.awt.Color(255, 255, 255));
        JLabelProductos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelProductos.setForeground(new java.awt.Color(255, 255, 255));
        JLabelProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/proveedor-alternativo.png"))); // NOI18N
        JLabelProductos.setText("Productos");
        JLabelProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelProductos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelProductos.setIconTextGap(40);
        JLabelProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelProductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelProductosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuProductosLayout = new javax.swing.GroupLayout(MenuProductos);
        MenuProductos.setLayout(MenuProductosLayout);
        MenuProductosLayout.setHorizontalGroup(
            MenuProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuProductosLayout.setVerticalGroup(
            MenuProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        MenuClientes.setBackground(new java.awt.Color(14, 76, 117));
        MenuClientes.setFocusTraversalPolicyProvider(true);

        JLabelClientes.setBackground(new java.awt.Color(255, 255, 255));
        JLabelClientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelClientes.setForeground(new java.awt.Color(255, 255, 255));
        JLabelClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/revisar.png"))); // NOI18N
        JLabelClientes.setText("Clientes");
        JLabelClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelClientes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelClientes.setIconTextGap(40);
        JLabelClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelClientesMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuClientesLayout = new javax.swing.GroupLayout(MenuClientes);
        MenuClientes.setLayout(MenuClientesLayout);
        MenuClientesLayout.setHorizontalGroup(
            MenuClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuClientesLayout.setVerticalGroup(
            MenuClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        MenuUsuarios.setBackground(new java.awt.Color(14, 76, 117));
        MenuUsuarios.setFocusTraversalPolicyProvider(true);

        JLabelUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        JLabelUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        JLabelUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/usuarios-alt (2).png"))); // NOI18N
        JLabelUsuarios.setText("Usuarios");
        JLabelUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelUsuarios.setIconTextGap(40);
        JLabelUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelUsuariosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuUsuariosLayout = new javax.swing.GroupLayout(MenuUsuarios);
        MenuUsuarios.setLayout(MenuUsuariosLayout);
        MenuUsuariosLayout.setHorizontalGroup(
            MenuUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuUsuariosLayout.setVerticalGroup(
            MenuUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuProv.setBackground(new java.awt.Color(14, 76, 117));
        MenuProv.setFocusTraversalPolicyProvider(true);

        JLabelProveedor.setBackground(new java.awt.Color(255, 255, 255));
        JLabelProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelProveedor.setForeground(new java.awt.Color(255, 255, 255));
        JLabelProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/proveedor (2).png"))); // NOI18N
        JLabelProveedor.setText("Proveedores");
        JLabelProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelProveedor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelProveedor.setIconTextGap(40);
        JLabelProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelProveedorMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuProvLayout = new javax.swing.GroupLayout(MenuProv);
        MenuProv.setLayout(MenuProvLayout);
        MenuProvLayout.setHorizontalGroup(
            MenuProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );
        MenuProvLayout.setVerticalGroup(
            MenuProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        MenuMedidas.setBackground(new java.awt.Color(14, 76, 117));
        MenuMedidas.setFocusTraversalPolicyProvider(true);

        JLabelMedidas.setBackground(new java.awt.Color(255, 255, 255));
        JLabelMedidas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelMedidas.setForeground(new java.awt.Color(255, 255, 255));
        JLabelMedidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/equilibrio-balanza-izquierda.png"))); // NOI18N
        JLabelMedidas.setText("Medidas");
        JLabelMedidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelMedidas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelMedidas.setIconTextGap(40);
        JLabelMedidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLabelMedidasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelMedidasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelMedidasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuMedidasLayout = new javax.swing.GroupLayout(MenuMedidas);
        MenuMedidas.setLayout(MenuMedidasLayout);
        MenuMedidasLayout.setHorizontalGroup(
            MenuMedidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelMedidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuMedidasLayout.setVerticalGroup(
            MenuMedidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelMedidas, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        MenuCat.setBackground(new java.awt.Color(14, 76, 117));
        MenuCat.setFocusTraversalPolicyProvider(true);

        JLabelCat.setBackground(new java.awt.Color(255, 255, 255));
        JLabelCat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelCat.setForeground(new java.awt.Color(255, 255, 255));
        JLabelCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/categoria.png"))); // NOI18N
        JLabelCat.setText("Categorias");
        JLabelCat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelCat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelCat.setIconTextGap(40);
        JLabelCat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelCatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelCatMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuCatLayout = new javax.swing.GroupLayout(MenuCat);
        MenuCat.setLayout(MenuCatLayout);
        MenuCatLayout.setHorizontalGroup(
            MenuCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelCat, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );
        MenuCatLayout.setVerticalGroup(
            MenuCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelCat, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        MenuConfig.setBackground(new java.awt.Color(14, 76, 117));
        MenuConfig.setFocusTraversalPolicyProvider(true);

        JLabelConfig.setBackground(new java.awt.Color(255, 255, 255));
        JLabelConfig.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelConfig.setForeground(new java.awt.Color(255, 255, 255));
        JLabelConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/ajustes-deslizadores.png"))); // NOI18N
        JLabelConfig.setText("Configuración");
        JLabelConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelConfig.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelConfig.setIconTextGap(40);
        JLabelConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelConfigMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelConfigMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuConfigLayout = new javax.swing.GroupLayout(MenuConfig);
        MenuConfig.setLayout(MenuConfigLayout);
        MenuConfigLayout.setHorizontalGroup(
            MenuConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuConfigLayout.setVerticalGroup(
            MenuConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        labelLogo.setBackground(new java.awt.Color(255, 153, 0));
        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/panel (2).png"))); // NOI18N
        labelLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLogoMouseClicked(evt);
            }
        });

        btnPlatos.setBackground(new java.awt.Color(14, 76, 117));
        btnPlatos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPlatos.setForeground(new java.awt.Color(255, 255, 255));
        btnPlatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/utensilios-de-plato (2).png"))); // NOI18N
        btnPlatos.setText("Platos");
        btnPlatos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPlatos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlatos.setFocusable(false);
        btnPlatos.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnPlatos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPlatos.setIconTextGap(45);
        btnPlatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPlatosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPlatosMouseExited(evt);
            }
        });
        btnPlatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlatosActionPerformed(evt);
            }
        });

        btnSala.setBackground(new java.awt.Color(14, 76, 117));
        btnSala.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSala.setForeground(new java.awt.Color(255, 255, 255));
        btnSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/escritorio.png"))); // NOI18N
        btnSala.setText("Salas");
        btnSala.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSala.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSala.setFocusable(false);
        btnSala.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnSala.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSala.setIconTextGap(45);
        btnSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalaMouseExited(evt);
            }
        });
        btnSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalaActionPerformed(evt);
            }
        });

        btnVentas.setBackground(new java.awt.Color(14, 76, 117));
        btnVentas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/notificacion-de-campana-en-redes-sociales.png"))); // NOI18N
        btnVentas.setText("Pedidos");
        btnVentas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.setFocusable(false);
        btnVentas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnVentas.setIconTextGap(45);
        btnVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVentasMouseExited(evt);
            }
        });
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        MenuVentas.setBackground(new java.awt.Color(14, 76, 117));
        MenuVentas.setForeground(new java.awt.Color(255, 255, 255));

        JLabelVentas.setBackground(new java.awt.Color(242, 173, 80));
        JLabelVentas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelVentas.setForeground(new java.awt.Color(255, 255, 255));
        JLabelVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/dolar-de-saco.png"))); // NOI18N
        JLabelVentas.setText("Nueva Venta");
        JLabelVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLabelVentas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabelVentas.setIconTextGap(40);
        JLabelVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLabelVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLabelVentasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuVentasLayout = new javax.swing.GroupLayout(MenuVentas);
        MenuVentas.setLayout(MenuVentasLayout);
        MenuVentasLayout.setHorizontalGroup(
            MenuVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuVentasLayout.setVerticalGroup(
            MenuVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MenuCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(MenuMedidas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(MenuProv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPlatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSala, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVentas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(MenuVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(72, 72, 72)
                                            .addComponent(labelLogo))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(MenuProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(MenuCompras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(MenuConfig, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(MenuUsuarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(MenuClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlatos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSala, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(MenuVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuMedidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MenuConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 220, 620));

        jPanel2.setBackground(new java.awt.Color(14, 76, 117));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarPro.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarPro.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarPro.setBorder(null);
        jPanel2.add(txtBuscarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 230, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/lupa_1.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 50, 50));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        btnCambiarPass.setBackground(new java.awt.Color(255, 255, 255));
        btnCambiarPass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCambiarPass.setForeground(new java.awt.Color(51, 51, 51));
        btnCambiarPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/User.png"))); // NOI18N
        btnCambiarPass.setText("User");
        btnCambiarPass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCambiarPass.setFocusable(false);
        jPanel2.add(btnCambiarPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 200, 40));

        txtIdLogin.setBackground(new java.awt.Color(14, 76, 117));
        txtIdLogin.setBorder(null);
        txtIdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdLoginActionPerformed(evt);
            }
        });
        jPanel2.add(txtIdLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 10, -1));

        txtUserLogin.setBackground(new java.awt.Color(14, 76, 117));
        txtUserLogin.setBorder(null);
        jPanel2.add(txtUserLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 10, -1));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Sistema Restaurante");
        jPanel2.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 18, -1, 40));

        txtCajaLogin.setBackground(new java.awt.Color(14, 76, 117));
        txtCajaLogin.setBorder(null);
        jPanel2.add(txtCajaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 10, -1));

        LabelVendedor1.setForeground(new java.awt.Color(14, 76, 117));
        LabelVendedor1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelVendedor1.setText("Administrador");
        jPanel2.add(LabelVendedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1080, 90));

        jPanel5.setBackground(new java.awt.Color(14, 76, 117));

        JLHome.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLHome.setForeground(new java.awt.Color(255, 255, 255));
        JLHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLHome.setText("EL VADO");
        JLHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel3.setBackground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(JLHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        JPanelGrafico.setBackground(new java.awt.Color(255, 255, 255));
        JPanelGrafico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPBarra.setBackground(new java.awt.Color(255, 255, 255));
        JPBarra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout JPBarraLayout = new javax.swing.GroupLayout(JPBarra);
        JPBarra.setLayout(JPBarraLayout);
        JPBarraLayout.setHorizontalGroup(
            JPBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        JPBarraLayout.setVerticalGroup(
            JPBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        JPanelGrafico.add(JPBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 520, 380));

        JPTorta.setBackground(new java.awt.Color(255, 255, 255));
        JPTorta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout JPTortaLayout = new javax.swing.GroupLayout(JPTorta);
        JPTorta.setLayout(JPTortaLayout);
        JPTortaLayout.setHorizontalGroup(
            JPTortaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        JPTortaLayout.setVerticalGroup(
            JPTortaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        JPanelGrafico.add(JPTorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 520, 380));

        jPanel4.setBackground(new java.awt.Color(0, 255, 0));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/proveedor-alternativo.png"))); // NOI18N
        jLabel10.setText("Productos");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        TotalPro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalPro.setForeground(new java.awt.Color(51, 51, 51));
        TotalPro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalPro.setText("...");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addComponent(TotalPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalPro, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        JPanelGrafico.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 220, -1));

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));

        JLabelCompra.setBackground(new java.awt.Color(51, 51, 51));
        JLabelCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JLabelCompra.setForeground(new java.awt.Color(255, 255, 255));
        JLabelCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/carro-de-compras-rapido.png"))); // NOI18N
        JLabelCompra.setText("Compras");
        JLabelCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JLabelCompra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JLabelCompra.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        TotalCompras.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalCompras.setForeground(new java.awt.Color(51, 51, 51));
        TotalCompras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalCompras.setText("...");
        TotalCompras.setToolTipText("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addComponent(TotalCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(JLabelCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        JPanelGrafico.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 220, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 0));

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/revisar.png"))); // NOI18N
        jLabel12.setText("Clientes");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        TotalClientes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalClientes.setForeground(new java.awt.Color(51, 51, 51));
        TotalClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalClientes.setText("...");
        TotalClientes.setToolTipText("");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addComponent(TotalClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        JPanelGrafico.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 220, -1));

        jPanel9.setBackground(new java.awt.Color(255, 51, 204));

        JLabelVenta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JLabelVenta.setForeground(new java.awt.Color(255, 255, 255));
        JLabelVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/dolar-de-saco.png"))); // NOI18N
        JLabelVenta.setText("Ventas");
        JLabelVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JLabelVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JLabelVenta.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        JLabelTotalVentas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JLabelTotalVentas.setForeground(new java.awt.Color(51, 51, 51));
        JLabelTotalVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelTotalVentas.setText("...");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addComponent(JLabelTotalVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(JLabelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLabelTotalVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        JPanelGrafico.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 220, -1));

        jTabbedPane1.addTab("tab13", JPanelGrafico);

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigoNV.setBackground(java.awt.Color.white);
        txtCodigoNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCodigoNV.setForeground(new java.awt.Color(51, 51, 51));
        txtCodigoNV.setBorder(null);
        jPanel15.add(txtCodigoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 137, 30));

        txtCantidadNV.setBackground(java.awt.Color.white);
        txtCantidadNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidadNV.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidadNV.setBorder(null);
        jPanel15.add(txtCantidadNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 39, 30));

        txtPrecioNV.setEditable(false);
        txtPrecioNV.setBackground(java.awt.Color.white);
        txtPrecioNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrecioNV.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioNV.setBorder(null);
        jPanel15.add(txtPrecioNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 85, 30));

        txtStockDisponible.setEditable(false);
        txtStockDisponible.setBackground(java.awt.Color.white);
        txtStockDisponible.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtStockDisponible.setForeground(new java.awt.Color(51, 51, 51));
        txtStockDisponible.setBorder(null);
        jPanel15.add(txtStockDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 60, 30));

        txtTotalNV.setEditable(false);
        txtTotalNV.setBackground(java.awt.Color.white);
        txtTotalNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalNV.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalNV.setBorder(null);
        jPanel15.add(txtTotalNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 76, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Cant.");
        jPanel15.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Producto");
        jPanel15.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Código");
        jPanel15.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Precio");
        jPanel15.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Total");
        jPanel15.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, -1, -1));

        btnGenerarVenta.setBackground(new java.awt.Color(0, 51, 153));
        btnGenerarVenta.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGenerarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/imprimir (2).png"))); // NOI18N
        btnGenerarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerarVenta.setFocusable(false);
        jPanel15.add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(969, 490, 90, 45));

        TableNuevaVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TableNuevaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripción", "Cant.", "Precio unitario", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableNuevaVenta.setRowHeight(25);
        jScrollPane2.setViewportView(TableNuevaVenta);

        jPanel15.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 1051, 390));

        btnCaja.setBackground(new java.awt.Color(153, 255, 153));
        btnCaja.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCaja.setForeground(new java.awt.Color(51, 51, 51));
        btnCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/caja-registradora (3).png"))); // NOI18N
        btnCaja.setText("Caja");
        btnCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCaja.setFocusable(false);
        jPanel15.add(btnCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 40, 120, 35));
        jPanel15.add(txtIdNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, 20, -1));

        txtProductoNV.setEditable(false);
        txtProductoNV.setBackground(java.awt.Color.white);
        txtProductoNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtProductoNV.setForeground(new java.awt.Color(51, 51, 51));
        txtProductoNV.setBorder(null);
        jPanel15.add(txtProductoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 340, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/monedas.png"))); // NOI18N
        jLabel8.setText("Total: ");
        jPanel15.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 494, 90, 30));

        txtTotalPagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalPagar.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalPagar.setText("00.00");
        jPanel15.add(txtTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 492, 170, 30));

        jLabel20.setBackground(new java.awt.Color(51, 51, 51));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/revisar (1).png"))); // NOI18N
        jLabel20.setText("Cliente");
        jPanel15.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Stock");
        jPanel15.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, -1));

        jLabel65.setBackground(new java.awt.Color(51, 51, 51));
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Pagar con:");
        jPanel15.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, -1, 30));

        txtPagarCon.setBackground(java.awt.Color.white);
        txtPagarCon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPagarCon.setBorder(null);
        jPanel15.add(txtPagarCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 492, 90, 30));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setForeground(java.awt.Color.red);
        jLabel66.setText("Vuelto:");
        jPanel15.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 492, -1, 30));

        txtVuelto.setEditable(false);
        txtVuelto.setBackground(java.awt.Color.white);
        txtVuelto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVuelto.setBorder(null);
        jPanel15.add(txtVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 492, 90, 30));

        txtClienteNV.setBackground(java.awt.Color.white);
        txtClienteNV.setEditable(true);
        txtClienteNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtClienteNV.setBorder(null);
        jPanel15.add(txtClienteNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 492, 210, 30));

        jTabbedPane1.addTab("tab1", jPanel15);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Código");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Nombre");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Precio Compra");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        txtCodigoPro.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigoPro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCodigoPro.setBorder(null);
        jPanel7.add(txtCodigoPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 200, 30));

        txtNombrePro.setBackground(new java.awt.Color(255, 255, 255));
        txtNombrePro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombrePro.setBorder(null);
        jPanel7.add(txtNombrePro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 200, 30));

        txtPrecioCompraPro.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioCompraPro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrecioCompraPro.setBorder(null);
        jPanel7.add(txtPrecioCompraPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 90, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Proveedor");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 90, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Medida");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Categoria");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        btnGuardarPro.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGuardarPro.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        jPanel7.add(btnGuardarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        btnModificarPro.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnModificarPro.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        jPanel7.add(btnModificarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Precio Venta");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, -1));

        txtPrecioVentaPro.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioVentaPro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrecioVentaPro.setBorder(null);
        jPanel7.add(txtPrecioVentaPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 90, 30));

        btnNuevoPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        jPanel7.add(btnNuevoPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, -1, -1));

        txtIdPro.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdPro.setBorder(null);
        jPanel7.add(txtIdPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 80, -1));

        cbxProveedor.setBackground(new java.awt.Color(255, 255, 255));
        cbxProveedor.setEditable(true);
        cbxProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxProveedor.setBorder(null);
        jPanel7.add(cbxProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 200, 30));

        cbxMedida.setBackground(new java.awt.Color(255, 255, 255));
        cbxMedida.setEditable(true);
        cbxMedida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxMedida.setBorder(null);
        jPanel7.add(cbxMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 200, 30));

        cbxCat.setBackground(new java.awt.Color(255, 255, 255));
        cbxCat.setEditable(true);
        cbxCat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxCat.setBorder(null);
        jPanel7.add(cbxCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 200, 30));

        jPanel16.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 490));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        TableProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Producto", "Precio", "Cant", "Medida", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableProductos.setComponentPopupMenu(jPopupProductos);
        TableProductos.setRowHeight(25);
        jScrollPane1.setViewportView(TableProductos);

        jPanel16.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 790, 430));

        JPaginarPro.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.add(JPaginarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 790, 50));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("> Productos");
        jPanel16.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("tab2", jPanel16);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(204, 204, 204));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Nombre");
        jPanel23.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Teléfono");
        jPanel23.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Dirección");
        jPanel23.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtNombreCli.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreCli.setBorder(null);
        jPanel23.add(txtNombreCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 200, 30));

        txtTelefonoCli.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefonoCli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTelefonoCli.setBorder(null);
        jPanel23.add(txtTelefonoCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 200, 30));

        txtDireccionCli.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionCli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDireccionCli.setBorder(null);
        jPanel23.add(txtDireccionCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 200, 30));

        btnGuardarCli.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGuardarCli.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        jPanel23.add(btnGuardarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, 40));

        btnModificarCli.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnModificarCli.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        jPanel23.add(btnModificarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, -1, 40));

        btnNuevoCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        jPanel23.add(btnNuevoCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, -1, 40));

        txtBuscarCli.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarCli.setBorder(null);
        jPanel23.add(txtBuscarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 200, 30));

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/busqueda (1).png"))); // NOI18N
        jPanel23.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 50, 40));

        txtIdCli.setBackground(new java.awt.Color(255, 255, 255));
        txtIdCli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdCli.setBorder(null);
        jPanel23.add(txtIdCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 70, 80, -1));

        jPanel17.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 490));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));

        TableClientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Teléfono", "Dirección", "estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableClientes.setComponentPopupMenu(JPopupClientes);
        TableClientes.setRowHeight(25);
        jScrollPane3.setViewportView(TableClientes);

        jPanel17.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 790, 420));

        JPaginarCli.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.add(JPaginarCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 790, 50));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("> Clientes");
        jPanel17.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("tab3", jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(null);
        jScrollPane4.setForeground(new java.awt.Color(255, 255, 255));

        TableProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Ruc", "Nombre", "Teléfono", "Dirección", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableProveedor.setComponentPopupMenu(JPopupProveedor);
        TableProveedor.setRowHeight(25);
        jScrollPane4.setViewportView(TableProveedor);

        jPanel18.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 790, 420));

        jPanel24.setBackground(new java.awt.Color(204, 204, 204));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Proveedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Nombre");
        jPanel24.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Teléfono");
        jPanel24.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Dirección");
        jPanel24.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        txtNombreProv.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreProv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreProv.setBorder(null);
        jPanel24.add(txtNombreProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 200, 30));

        txtTelefonoProv.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefonoProv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTelefonoProv.setBorder(null);
        jPanel24.add(txtTelefonoProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 200, 30));

        btnGuardarProv.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGuardarProv.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        jPanel24.add(btnGuardarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        btnModificarProv.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnModificarProv.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        jPanel24.add(btnModificarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, -1, -1));

        btnNuevoProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        jPanel24.add(btnNuevoProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Ruc");
        jPanel24.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        txtRucProv.setBackground(new java.awt.Color(255, 255, 255));
        txtRucProv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtRucProv.setBorder(null);
        jPanel24.add(txtRucProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 200, 30));

        txtDireccionProv.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionProv.setBorder(null);
        txtDireccionProv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane5.setViewportView(txtDireccionProv);

        jPanel24.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 200, 60));

        txtBuscarProv.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarProv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarProv.setBorder(null);
        jPanel24.add(txtBuscarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, 30));

        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/busqueda (1).png"))); // NOI18N
        jPanel24.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtIdProv.setBackground(new java.awt.Color(255, 255, 255));
        txtIdProv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdProv.setBorder(null);
        jPanel24.add(txtIdProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 70, -1));

        jPanel18.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 490));

        JPaginarProv.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.add(JPaginarProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 790, 50));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 51));
        jLabel73.setText("> Proveedores");
        jPanel18.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("tab4", jPanel18);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane6.setBorder(null);
        jScrollPane6.setForeground(new java.awt.Color(255, 255, 255));

        TableUsers.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Usuario", "Nombre", "Caja", "Rol", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableUsers.setComponentPopupMenu(jPopupUsuarios);
        TableUsers.setRowHeight(25);
        jScrollPane6.setViewportView(TableUsers);

        jPanel19.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 790, 420));

        jPanel25.setBackground(new java.awt.Color(204, 204, 204));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Nombre");
        jPanel25.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Contraseña");
        jPanel25.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        txtNombreUser.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreUser.setBorder(null);
        jPanel25.add(txtNombreUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 200, 30));

        btnGuardarUser.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGuardarUser.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        jPanel25.add(btnGuardarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        btnModificarUser.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnModificarUser.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        jPanel25.add(btnModificarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, -1, -1));

        btnNuevoUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        jPanel25.add(btnNuevoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Usuario");
        jPanel25.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        txtUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuario.setBorder(null);
        jPanel25.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 200, 30));

        txtClaveUser.setBackground(new java.awt.Color(255, 255, 255));
        txtClaveUser.setBorder(null);
        jPanel25.add(txtClaveUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 30));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Rol");
        jPanel25.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        cbxRolUser.setBackground(new java.awt.Color(255, 255, 255));
        cbxRolUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxRolUser.setForeground(new java.awt.Color(102, 102, 102));
        cbxRolUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "MOZO", "CAJERO" }));
        cbxRolUser.setBorder(null);
        jPanel25.add(cbxRolUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 200, 30));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Caja");
        jPanel25.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        txtIdUser.setBackground(new java.awt.Color(255, 255, 255));
        txtIdUser.setBorder(null);
        jPanel25.add(txtIdUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 70, -1));

        txtBuscarUser.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarUser.setBorder(null);
        jPanel25.add(txtBuscarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 200, 30));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/busqueda (1).png"))); // NOI18N
        jPanel25.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cbxCajaUser.setBackground(new java.awt.Color(255, 255, 255));
        cbxCajaUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxCajaUser.setForeground(new java.awt.Color(102, 102, 102));
        cbxCajaUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERAL", "SECUNDARIO" }));
        cbxCajaUser.setBorder(null);
        jPanel25.add(cbxCajaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, 30));

        jPanel19.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 490));

        JPaginarUser.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.add(JPaginarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 790, 50));

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("> Usuarios");
        jPanel19.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("tab5", jPanel19);

        jPanel20.setBackground(new java.awt.Color(204, 204, 204));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Código");
        jPanel20.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtCodigoC.setBackground(java.awt.Color.white);
        txtCodigoC.setForeground(new java.awt.Color(51, 51, 51));
        txtCodigoC.setBorder(null);
        jPanel20.add(txtCodigoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 137, 30));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Producto");
        jPanel20.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        TableCompra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TableCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripción", "Cant.", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCompra.setRowHeight(25);
        jScrollPane7.setViewportView(TableCompra);

        jPanel20.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 115, 1051, 370));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Cant.");
        jPanel20.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, -1, -1));

        txtCantidadC.setBackground(java.awt.Color.white);
        txtCantidadC.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidadC.setBorder(null);
        jPanel20.add(txtCantidadC, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 39, 30));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Precio");
        jPanel20.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, -1, -1));

        txtPrecioC.setEditable(false);
        txtPrecioC.setBackground(java.awt.Color.white);
        txtPrecioC.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioC.setBorder(null);
        jPanel20.add(txtPrecioC, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 85, 30));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Total");
        jPanel20.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, -1, -1));

        txtTotalC.setEditable(false);
        txtTotalC.setBackground(java.awt.Color.white);
        txtTotalC.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalC.setBorder(null);
        jPanel20.add(txtTotalC, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 76, 30));

        btnGenerarC.setBackground(new java.awt.Color(0, 51, 153));
        btnGenerarC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGenerarC.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/imprimir (2).png"))); // NOI18N
        btnGenerarC.setText("Generar");
        btnGenerarC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerarC.setFocusable(false);
        jPanel20.add(btnGenerarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, -1, 40));

        txtProductoC.setEditable(false);
        txtProductoC.setBackground(java.awt.Color.white);
        txtProductoC.setForeground(new java.awt.Color(51, 51, 51));
        txtProductoC.setBorder(null);
        jPanel20.add(txtProductoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 340, 30));

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/monedas.png"))); // NOI18N
        jLabel4.setText("Total:");
        jPanel20.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 494, -1, 40));

        txtTotalPagarC.setBackground(new java.awt.Color(51, 51, 51));
        txtTotalPagarC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalPagarC.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalPagarC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalPagarC.setText("00.00");
        jPanel20.add(txtTotalPagarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 500, 160, 30));

        txtIdC.setBackground(new java.awt.Color(255, 255, 255));
        txtIdC.setBorder(null);
        jPanel20.add(txtIdC, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 30, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/revisar (1).png"))); // NOI18N
        jLabel5.setText("Proveedor");
        jPanel20.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, 50));

        txtProveedorC.setBackground(new java.awt.Color(255, 255, 255));
        txtProveedorC.setEditable(true);
        jPanel20.add(txtProveedorC, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 502, 290, 30));

        jTabbedPane1.addTab("tab6", jPanel20);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(204, 204, 204));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Caja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Monto Inicial");
        jPanel26.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("Fecha Apertura");
        jPanel26.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Total Ventas");
        jPanel26.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        txtMontoInicial.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoInicial.setBorder(null);
        jPanel26.add(txtMontoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 200, 30));

        txtMontoFinal.setEditable(false);
        txtMontoFinal.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoFinal.setBorder(null);
        jPanel26.add(txtMontoFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 200, 30));

        txtTotalVentas.setEditable(false);
        txtTotalVentas.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalVentas.setBorder(null);
        jPanel26.add(txtTotalVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 200, 30));

        btnGuardarCaja.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGuardarCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        jPanel26.add(btnGuardarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        btnModificarCaja.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnModificarCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        jPanel26.add(btnModificarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, -1, -1));

        btnNuevoCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        jPanel26.add(btnNuevoCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Monto Final");
        jPanel26.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        txtFechaApertura.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaApertura.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.add(txtFechaApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 200, 30));

        txtBuscarCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCaja.setBorder(null);
        jPanel26.add(txtBuscarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 200, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/busqueda (1).png"))); // NOI18N
        jPanel26.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtIdCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtIdCaja.setBorder(null);
        jPanel26.add(txtIdCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 80, -1));
        jPanel26.add(txtFechaCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, 30));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Fecha Cierre");
        jPanel26.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jPanel21.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 490));

        jScrollPane8.setBorder(null);
        jScrollPane8.setForeground(new java.awt.Color(255, 255, 255));

        TableCaja.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableCaja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Fecha A.", "Fecha C.", "Monto I.", "Monto F.", "Total Ventas", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCaja.setRowHeight(25);
        jScrollPane8.setViewportView(TableCaja);

        jPanel21.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 790, 420));

        JPaginarCaja.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.add(JPaginarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 480, 790, 60));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(51, 51, 51));
        jLabel88.setText("Nueva Venta > Caja");
        jPanel21.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("tab7", jPanel21);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel27.setBackground(new java.awt.Color(204, 204, 204));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Medida", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("Nombre");
        jPanel27.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Nombre corto");
        jPanel27.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        txtNombreMed.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreMed.setBorder(null);
        jPanel27.add(txtNombreMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 200, 30));

        txtNombreCortoMed.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCortoMed.setBorder(null);
        jPanel27.add(txtNombreCortoMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 200, 30));

        btnGuardarMed.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGuardarMed.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        jPanel27.add(btnGuardarMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, 40));

        btnModificarMed.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnModificarMed.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        jPanel27.add(btnModificarMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, 40));

        btnNuevoMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        jPanel27.add(btnNuevoMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, 40));

        txtIdMed.setBackground(new java.awt.Color(255, 255, 255));
        txtIdMed.setBorder(null);
        txtIdMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMedActionPerformed(evt);
            }
        });
        jPanel27.add(txtIdMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 40, -1));

        txtBuscarMed.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarMed.setBorder(null);
        jPanel27.add(txtBuscarMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 160, 30));

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/busqueda (1).png"))); // NOI18N
        jPanel27.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 40, 50));

        jPanel22.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 420));

        jScrollPane9.setBorder(null);
        jScrollPane9.setForeground(new java.awt.Color(255, 255, 255));

        TableMedidas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableMedidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Nombre Corto", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableMedidas.setComponentPopupMenu(JPopupMedida);
        TableMedidas.setRowHeight(25);
        jScrollPane9.setViewportView(TableMedidas);

        jPanel22.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 790, 420));

        JPaginarMedida.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.add(JPaginarMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 790, 50));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 51, 51));
        jLabel89.setText("> Medidas");
        jPanel22.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("tab8", jPanel22);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane10.setBorder(null);
        jScrollPane10.setForeground(new java.awt.Color(255, 255, 255));

        TableCat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableCat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCat.setComponentPopupMenu(JPopupCat);
        TableCat.setRowHeight(25);
        jScrollPane10.setViewportView(TableCat);

        jPanel28.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 790, 420));

        jPanel29.setBackground(new java.awt.Color(204, 204, 204));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Categoria", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setBackground(new java.awt.Color(51, 51, 51));
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setText("Nombre");
        jPanel29.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        txtNombreCat.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCat.setBorder(null);
        jPanel29.add(txtNombreCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 200, 30));

        btnGuardarCat.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGuardarCat.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        jPanel29.add(btnGuardarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, 40));

        btnModificarCat.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnModificarCat.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        jPanel29.add(btnModificarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, 40));

        btnNuevoCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        jPanel29.add(btnNuevoCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, 40));

        txtBuscarCat.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCat.setBorder(null);
        jPanel29.add(txtBuscarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 200, 30));

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/busqueda (1).png"))); // NOI18N
        jPanel29.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 50, 40));

        txtIdCat.setBackground(new java.awt.Color(255, 255, 255));
        txtIdCat.setBorder(null);
        jPanel29.add(txtIdCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 90, -1));

        jPanel28.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 250, 420));

        JPaginarCat.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.add(JPaginarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 790, 50));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(51, 51, 51));
        jLabel90.setText("> Categorias");
        jPanel28.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("tab9", jPanel28);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel32.setBackground(new java.awt.Color(204, 204, 204));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Empresa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Ruc");
        jPanel32.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Nombre");
        jPanel32.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Teléfono");
        jPanel32.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        txtRucEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtRucEmpresa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtRucEmpresa.setForeground(new java.awt.Color(51, 51, 51));
        txtRucEmpresa.setBorder(null);
        jPanel32.add(txtRucEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 300, 33));

        txtTelefonoEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefonoEmpresa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTelefonoEmpresa.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefonoEmpresa.setBorder(null);
        jPanel32.add(txtTelefonoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 300, 33));

        btnModificarEmpresa.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnModificarEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        btnModificarEmpresa.setFocusable(false);
        jPanel32.add(btnModificarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 90, 40));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("Dirección");
        jPanel32.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setText("Mensaje");
        jPanel32.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtNombreEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreEmpresa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreEmpresa.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreEmpresa.setBorder(null);
        jPanel32.add(txtNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 300, 33));

        txtDireccionEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionEmpresa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDireccionEmpresa.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane11.setViewportView(txtDireccionEmpresa);

        jPanel32.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 300, 50));

        txtMensaje.setBackground(new java.awt.Color(255, 255, 255));
        txtMensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane12.setViewportView(txtMensaje);

        jPanel32.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 300, 50));

        txtIdConfig.setBackground(new java.awt.Color(204, 204, 204));
        txtIdConfig.setBorder(null);
        jPanel32.add(txtIdConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 70, -1));

        jPanel30.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 340, 490));

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/vadoimg3.jpg"))); // NOI18N
        jLabel55.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel30.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 700, 490));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(51, 51, 51));
        jLabel91.setText("> Configuración");
        jPanel30.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("tab10", jPanel30);

        jPanel31.setBackground(new java.awt.Color(204, 204, 204));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableVentas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Usuario", "Total", "Fecha", "Cliente", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableVentas.setRowHeight(25);
        jScrollPane13.setViewportView(TableVentas);

        jPanel31.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 1040, 380));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/busqueda (1).png"))); // NOI18N
        jPanel31.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtBuscarVentas.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarVentas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarVentas.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarVentas.setBorder(null);
        jPanel31.add(txtBuscarVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 230, 30));

        txtVentasId.setBackground(new java.awt.Color(255, 255, 255));
        txtVentasId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVentasId.setForeground(new java.awt.Color(51, 51, 51));
        txtVentasId.setBorder(null);
        jPanel31.add(txtVentasId, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 70, 30));

        txtVentasTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtVentasTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVentasTotal.setForeground(new java.awt.Color(51, 51, 51));
        txtVentasTotal.setBorder(null);
        jPanel31.add(txtVentasTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 80, 30));

        txtVentasUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtVentasUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVentasUsuario.setForeground(new java.awt.Color(51, 51, 51));
        txtVentasUsuario.setBorder(null);
        jPanel31.add(txtVentasUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 80, 30));

        txtVentasCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtVentasCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVentasCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtVentasCliente.setBorder(null);
        jPanel31.add(txtVentasCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 60, 30));

        btnPdfV.setBackground(new java.awt.Color(204, 204, 204));
        btnPdfV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/pdf_1_1.png"))); // NOI18N
        btnPdfV.setBorder(null);
        jPanel31.add(btnPdfV, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 150, 40));
        jPanel31.add(JPanelVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 1040, 50));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("Historial de Ventas");
        jPanel31.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, -1));

        jTabbedPane1.addTab("tab11", jPanel31);

        jPanel33.setBackground(new java.awt.Color(204, 204, 204));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCompras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TableCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Total", "Fecha", "Proveedor", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCompras.setRowHeight(25);
        jScrollPane14.setViewportView(TableCompras);

        jPanel33.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 1040, 380));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/busqueda (1).png"))); // NOI18N
        jPanel33.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtBuscarVentas1.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarVentas1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarVentas1.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarVentas1.setBorder(null);
        jPanel33.add(txtBuscarVentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 230, 30));

        txtVentasId1.setBackground(new java.awt.Color(255, 255, 255));
        txtVentasId1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVentasId1.setForeground(new java.awt.Color(51, 51, 51));
        txtVentasId1.setBorder(null);
        jPanel33.add(txtVentasId1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 70, 30));

        txtVentasTotal1.setBackground(new java.awt.Color(255, 255, 255));
        txtVentasTotal1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVentasTotal1.setForeground(new java.awt.Color(51, 51, 51));
        txtVentasTotal1.setBorder(null);
        jPanel33.add(txtVentasTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 80, 30));

        txtVentasCliente1.setBackground(new java.awt.Color(255, 255, 255));
        txtVentasCliente1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVentasCliente1.setForeground(new java.awt.Color(51, 51, 51));
        txtVentasCliente1.setBorder(null);
        jPanel33.add(txtVentasCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 60, 30));

        btnPdfC.setBackground(new java.awt.Color(204, 204, 204));
        btnPdfC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/pdf_1_1.png"))); // NOI18N
        btnPdfC.setBorder(null);
        jPanel33.add(btnPdfC, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 140, 40));
        jPanel33.add(JPanelVentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 1040, 50));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(51, 51, 51));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Historial de Compras");
        jPanel33.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, -1));

        jTabbedPane1.addTab("tab12", jPanel33);

        jPanel118.setBackground(new java.awt.Color(204, 204, 204));
        jPanel118.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtActual.setBackground(new java.awt.Color(255, 255, 255));
        txtActual.setForeground(new java.awt.Color(51, 51, 51));
        txtActual.setBorder(null);
        jPanel118.add(txtActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 200, 30));

        txtNueva.setBackground(new java.awt.Color(255, 255, 255));
        txtNueva.setForeground(new java.awt.Color(51, 51, 51));
        txtNueva.setBorder(null);
        jPanel118.add(txtNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 200, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Contraseña actual");
        jPanel118.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Nueva Contraseña");
        jPanel118.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        btnCambiar.setBackground(new java.awt.Color(51, 102, 255));
        btnCambiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCambiar.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiar.setText("Cambiar");
        btnCambiar.setFocusable(false);
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });
        jPanel118.add(btnCambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, 35));

        btnCancelar.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cerrar Sesión");
        btnCancelar.setFocusable(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel118.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, -1, 35));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel118.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 40, 30));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel118.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 40, 30));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Perfil");
        jPanel118.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 340, -1));

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/logo_1.png"))); // NOI18N
        jPanel118.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 130, 130));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Correo");
        jPanel118.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        txtActual1.setBackground(new java.awt.Color(255, 255, 255));
        txtActual1.setForeground(new java.awt.Color(51, 51, 51));
        txtActual1.setBorder(null);
        jPanel118.add(txtActual1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 200, 30));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel118.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 40, 30));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
        jLabel68.setText("Usuario");
        jPanel118.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        txtActual2.setBackground(new java.awt.Color(255, 255, 255));
        txtActual2.setForeground(new java.awt.Color(51, 51, 51));
        txtActual2.setBorder(null);
        txtActual2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActual2ActionPerformed(evt);
            }
        });
        jPanel118.add(txtActual2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 200, 30));

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel118.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 40, 30));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 43, Short.MAX_VALUE)
                    .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 42, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab14", jPanel10);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableSala.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableSala.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "Mesas"
            }
        ));
        tableSala.setRowHeight(23);
        tableSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSalaMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tableSala);

        jPanel43.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 490, 470));

        jPanel44.setBackground(new java.awt.Color(204, 204, 204));
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Nombre:");
        jPanel44.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        txtNombreSala.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreSala.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreSala.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreSala.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreSala.setBorder(null);
        jPanel44.add(txtNombreSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 190, 30));

        btnRegistrarSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        btnRegistrarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarSalaActionPerformed(evt);
            }
        });
        jPanel44.add(btnRegistrarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 100, 40));

        btnActualizarSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        btnActualizarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarSalaActionPerformed(evt);
            }
        });
        jPanel44.add(btnActualizarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 100, 40));

        btnNuevoSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        btnNuevoSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoSalaActionPerformed(evt);
            }
        });
        jPanel44.add(btnNuevoSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 100, 40));

        btnEliminarSala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        btnEliminarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSalaActionPerformed(evt);
            }
        });
        jPanel44.add(btnEliminarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 100, 40));

        txtIdSala.setBackground(new java.awt.Color(204, 204, 204));
        txtIdSala.setBorder(null);
        jPanel44.add(txtIdSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 24, -1));

        jPanel35.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel44.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 190, 2));

        jPanel38.setBackground(new java.awt.Color(0, 0, 0));
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel76.setBackground(new java.awt.Color(204, 204, 204));
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Nuevo Sala");
        jPanel38.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 30));

        jPanel44.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 35));

        jPanel36.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel44.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 190, 2));

        txtMesas.setBackground(new java.awt.Color(204, 204, 204));
        txtMesas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMesas.setForeground(new java.awt.Color(51, 51, 51));
        txtMesas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMesas.setBorder(null);
        jPanel44.add(txtMesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 190, 30));

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 51, 51));
        jLabel77.setText("Mesas:");
        jPanel44.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jPanel43.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 310, 370));

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(51, 51, 51));
        jLabel92.setText("> Salas");
        jPanel43.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane1.addTab("Salas", jPanel43);

        PanelMesas.setLayout(new java.awt.GridLayout(0, 5));
        jScrollPane18.setViewportView(PanelMesas);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Mesas", jPanel45);

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));

        jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Platos del Dia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtBuscarPlato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarPlato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPlatoKeyReleased(evt);
            }
        });

        tblTemPlatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblTemPlatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Nombre", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTemPlatos.setRowHeight(23);
        jScrollPane19.setViewportView(tblTemPlatos);

        btnAddPlato.setBackground(new java.awt.Color(0, 0, 153));
        btnAddPlato.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnAddPlato.setForeground(new java.awt.Color(255, 255, 255));
        btnAddPlato.setText("+");
        btnAddPlato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPlato.setFocusable(false);
        btnAddPlato.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPlatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(txtBuscarPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(btnAddPlato)))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarPlato, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnAddPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );

        tableMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Plato", "Cant", "Precio", "SubTotal", "Comentario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMenu.setRowHeight(23);
        jScrollPane20.setViewportView(tableMenu);

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(51, 51, 51));
        jLabel78.setText("Comentario:");

        txtComentario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtComentario.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane21.setViewportView(txtComentario);

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/monedas.png"))); // NOI18N
        jLabel79.setText("Total a Pagar");

        totalMenu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalMenu.setForeground(new java.awt.Color(51, 51, 51));
        totalMenu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalMenu.setText("00.00");

        btnGenerarPedido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGenerarPedido.setForeground(new java.awt.Color(51, 51, 51));
        btnGenerarPedido.setText("Realizar Pedido");
        btnGenerarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPedidoActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setText("Agregar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnEliminarTempPlato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar (1).png"))); // NOI18N
        btnEliminarTempPlato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarTempPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTempPlatoActionPerformed(evt);
            }
        });

        txtTempIdSala.setBackground(new java.awt.Color(255, 255, 255));
        txtTempIdSala.setBorder(null);

        txtTempNumMesa.setBackground(new java.awt.Color(255, 255, 255));
        txtTempNumMesa.setBorder(null);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarTempPlato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTempIdSala)
                                    .addComponent(txtTempNumMesa, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                                        .addComponent(totalMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13))
                                    .addComponent(btnGenerarPedido, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarTempPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTempIdSala, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79))
                                .addGap(14, 14, 14)
                                .addComponent(txtTempNumMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(totalMenu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGenerarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39))
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Platos", jPanel46);

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFinalizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(51, 51, 51));
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        jPanel48.add(btnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 440, 110, 40));

        totalFinalizar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalFinalizar.setForeground(new java.awt.Color(51, 51, 51));
        totalFinalizar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalFinalizar.setText("00.00");
        jPanel48.add(totalFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 390, 120, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(51, 51, 51));
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/monedas.png"))); // NOI18N
        jLabel80.setText("Total a Pagar");
        jPanel48.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 340, -1, -1));

        tableFinalizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableFinalizar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Plato", "Cant", "Precio", "SubTotal", "Comentario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableFinalizar.setRowHeight(23);
        jScrollPane22.setViewportView(tableFinalizar);

        jPanel48.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 13, 1030, 316));

        txtIdPedido.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPedido.setBorder(null);
        jPanel48.add(txtIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 50, -1));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(51, 51, 51));
        jLabel81.setText("Fecha y Hora:");
        jPanel48.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 51, 51));
        jLabel82.setText("Sala:");
        jPanel48.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 51, 51));
        jLabel83.setText("N° Mesa:");
        jPanel48.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        txtFechaHora.setEditable(false);
        txtFechaHora.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel48.add(txtFechaHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 240, 30));

        txtSalaFinalizar.setEditable(false);
        txtSalaFinalizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel48.add(txtSalaFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 240, 30));

        txtNumMesaFinalizar.setEditable(false);
        txtNumMesaFinalizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel48.add(txtNumMesaFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 240, 30));

        btnPdfPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/pdf_1_1.png"))); // NOI18N
        btnPdfPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfPedidoActionPerformed(evt);
            }
        });
        jPanel48.add(btnPdfPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 440, 110, 40));

        txtIdHistorialPedido.setBackground(new java.awt.Color(255, 255, 255));
        txtIdHistorialPedido.setBorder(null);
        jPanel48.add(txtIdHistorialPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 450, 50, -1));

        jTabbedPane1.addTab("Finalizar Pedido", jPanel48);

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablePedidos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Sala", "Atendido", "N° Mesa", "Fecha", "Total", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablePedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TablePedidos.setRowHeight(23);
        TablePedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePedidosMouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(TablePedidos);

        jPanel49.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 1020, 450));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 51));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Historial Pedidos");
        jPanel49.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 280, -1));

        jTabbedPane1.addTab("Historial Pedidos", jPanel49);

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(51, 51, 51));
        jLabel85.setText("Nombre:");
        jPanel11.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        txtNombrePlato.setBackground(new java.awt.Color(204, 204, 204));
        txtNombrePlato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombrePlato.setForeground(new java.awt.Color(51, 51, 51));
        txtNombrePlato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombrePlato.setBorder(null);
        jPanel11.add(txtNombrePlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 170, 30));

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(51, 51, 51));
        jLabel86.setText("Precio:");
        jPanel11.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txtPrecioPlato.setBackground(new java.awt.Color(204, 204, 204));
        txtPrecioPlato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrecioPlato.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioPlato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioPlato.setBorder(null);
        txtPrecioPlato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioPlatoKeyTyped(evt);
            }
        });
        jPanel11.add(txtPrecioPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 170, 30));

        btnGuardarPlato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/salvar.png"))); // NOI18N
        btnGuardarPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPlatoActionPerformed(evt);
            }
        });
        jPanel11.add(btnGuardarPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 110, 50));

        btnEditarPlato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/flecha-de-carga.png"))); // NOI18N
        btnEditarPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPlatoActionPerformed(evt);
            }
        });
        jPanel11.add(btnEditarPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 270, 100, 50));

        btnEliminarPlato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/eliminar.png"))); // NOI18N
        btnEliminarPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPlatoActionPerformed(evt);
            }
        });
        jPanel11.add(btnEliminarPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 110, 50));

        btnNuevoPlato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/nuevo-documento.png"))); // NOI18N
        btnNuevoPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPlatoActionPerformed(evt);
            }
        });
        jPanel11.add(btnNuevoPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 100, 50));

        jPanel51.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 170, 2));

        jPanel52.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 170, 2));

        jPanel39.setBackground(new java.awt.Color(0, 0, 0));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("Platos del Día");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 50));

        txtIdPlato.setBackground(new java.awt.Color(204, 204, 204));
        txtIdPlato.setBorder(null);
        jPanel11.add(txtIdPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 80, -1));

        TablePlatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TablePlatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DESCRIPCIÓN", "PRECIO"
            }
        ));
        TablePlatos.setRowHeight(23);
        TablePlatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePlatosMouseClicked(evt);
            }
        });
        jScrollPane24.setViewportView(TablePlatos);

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(51, 51, 51));
        jLabel93.setText("> Platos");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jLabel93)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane24))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Platos", jPanel50);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        PanelSalas.setBackground(new java.awt.Color(255, 255, 255));
        PanelSalas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PanelSalas.setLayout(new java.awt.GridLayout(0, 5));
        jScrollPane15.setViewportView(PanelSalas);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Panel", jPanel34);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 1080, 610));

        LabelVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelVendedor.setText("Administrador");
        getContentPane().add(LabelVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void ListarPedidos() {
        Tables color = new Tables();
        List<Pedidos> Listar = pedDao.listarPedidos();
        modelo = (DefaultTableModel) TablePedidos.getModel();
        Object[] ob = new Object[7];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getId();
            ob[1] = Listar.get(i).getSala();
            ob[2] = Listar.get(i).getUsuario();
            ob[3] = Listar.get(i).getNum_mesa();
            ob[4] = Listar.get(i).getFecha();
            ob[5] = Listar.get(i).getTotal();
            ob[6] = Listar.get(i).getEstado();
            modelo.addRow(ob);
        }
        colorHeader(TablePedidos);
        TablePedidos.setDefaultRenderer(Object.class, color);
    }
    

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void ListarSalas() {
        List<Salas> Listar = slDao.Listar();
        modelo = (DefaultTableModel) tableSala.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getId();
            ob[1] = Listar.get(i).getNombre();
            ob[2] = Listar.get(i).getMesas();
            modelo.addRow(ob);
        }
        colorHeader(tableSala);

    }

    private void colorHeader(JTable tabla) {
        tabla.setModel(modelo);
        JTableHeader header = tabla.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 110, 255));
        header.setForeground(Color.white);
    }

    private void LimpiarSala() {
        txtIdSala.setText("");
        txtNombreSala.setText("");
        txtMesas.setText("");
    }
    
    private void LimpiarPlatos() {
        txtIdPlato.setText("");
        txtNombrePlato.setText("");
        txtPrecioPlato.setText("");
    }
    
 private void ListarPlatos(JTable tabla) {
        List<Platos> Listar = plaDao.Listar(txtBuscarPlato.getText(), fechaFormato);
        modelo = (DefaultTableModel) tabla.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getId();
            ob[1] = Listar.get(i).getNombre();
            ob[2] = Listar.get(i).getPrecio();
            modelo.addRow(ob);
        }
        colorHeader(tabla);
    }
 
    public void exito(String texto){
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, texto);
        panel.showNotification();
    }
    
    public void advertencia(String texto){
        Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, texto);
        panel.showNotification();
    }
    
    public void error(String texto){
        Notification panel = new Notification(this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, texto);
        panel.showNotification();
    }
    
    private void RegistrarPedido() {
        int id_sala = Integer.parseInt(txtTempIdSala.getText());
        int num_mesa = Integer.parseInt(txtTempNumMesa.getText());
        double monto = Totalpagar;
        ped.setId_sala(id_sala);
        ped.setNum_mesa(num_mesa);
        ped.setTotal(monto);
        ped.setUsuario(LabelVendedor.getText());
        pedDao.RegistrarPedido(ped);
    }

    private void detallePedido() {
        int id = pedDao.IdPedido();
        for (int i = 0; i < tableMenu.getRowCount(); i++) {
            String nombre = tableMenu.getValueAt(i, 1).toString();
            int cant = Integer.parseInt(tableMenu.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(tableMenu.getValueAt(i, 3).toString());
            detPedido.setNombre(nombre);
            detPedido.setCantidad(cant);
            detPedido.setPrecio(precio);
            detPedido.setComentario(tableMenu.getValueAt(i, 5).toString());
            detPedido.setId_pedido(id);
            pedDao.RegistrarDetalle(detPedido);

        }
    }
    
    
    private void PanelSalas() {
        List<Salas> Listar = slDao.Listar();
        for (int i = 0; i < Listar.size(); i++) {
            int id = Listar.get(i).getId();
            int cantidad = Listar.get(i).getMesas();
            JButton boton = new JButton(Listar.get(i).getNombre(), new ImageIcon(getClass().getResource("/Assets/comedor.png")));
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            boton.setHorizontalTextPosition(JButton.CENTER);
            boton.setVerticalTextPosition(JButton.BOTTOM);
            boton.setBackground(new Color(204, 204, 204));
            PanelSalas.add(boton);
            boton.addActionListener((ActionEvent e) -> {
                LimpiarTable();
                PanelMesas.removeAll();
                panelMesas(id, cantidad);
                jTabbedPane1.setSelectedIndex(15);
            });
        }
    }
    
        private void panelMesas(int id_sala, int cant) {
        for (int i = 1; i <= cant; i++) {
            int num_mesa = i;
            //verificar estado
            JButton boton = new JButton("MESA N°: " + i, new ImageIcon(getClass().getResource("/Assets/mesa.png")));
            boton.setHorizontalTextPosition(JButton.CENTER);
            boton.setVerticalTextPosition(JButton.BOTTOM);
            int verificar = pedDao.verificarStado(num_mesa, id_sala);
            if (verificar > 0) {
                boton.setBackground(new Color(255, 51, 51));
            } else {
                boton.setBackground(new Color(0, 102, 102));
            }
            boton.setForeground(Color.WHITE);
            boton.setFocusable(false);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            PanelMesas.add(boton);
            boton.addActionListener((ActionEvent e) -> {
                if (verificar > 0) {
                    LimpiarTable();
                    verPedido(verificar);
                    verPedidoDetalle(verificar);
                    btnFinalizar.setEnabled(true);
                    btnPdfPedido.setEnabled(false);
                    jTabbedPane1.setSelectedIndex(17);
                } else {
                    LimpiarTable();
                    ListarPlatos(tblTemPlatos);
                    jTabbedPane1.setSelectedIndex(16);
                    txtTempIdSala.setText("" + id_sala);
                    txtTempNumMesa.setText("" + num_mesa);
                }
            });
        }
    }
    
        
    public void verPedidoDetalle(int id_pedido) {
        List<DetallePedido> Listar = pedDao.verPedidoDetalle(id_pedido);
        modelo = (DefaultTableModel) tableFinalizar.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getId();
            ob[1] = Listar.get(i).getNombre();
            ob[2] = Listar.get(i).getCantidad();
            ob[3] = Listar.get(i).getPrecio();
            ob[4] = Listar.get(i).getCantidad() * Listar.get(i).getPrecio();
            ob[5] = Listar.get(i).getComentario();
            modelo.addRow(ob);
        }
        colorHeader(tableFinalizar);
    }
    
    public void verPedido(int id_pedido) {
        ped = pedDao.verPedido(id_pedido);
        totalFinalizar.setText("" + ped.getTotal());
        txtFechaHora.setText("" + ped.getFecha());
        txtSalaFinalizar.setText("" + ped.getSala());
        txtNumMesaFinalizar.setText("" + ped.getNum_mesa());
        txtIdPedido.setText("" + ped.getId());
    }

    private void TotalPagar(JTable tabla, JLabel label) {
        Totalpagar = 0.00;
        int numFila = tabla.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(tabla.getModel().getValueAt(i, 4)));
            Totalpagar += cal;
        }
        label.setText(String.format("%.2f", Totalpagar));
    }
    
    private void LimpiarTableMenu() {
        tmp = (DefaultTableModel) tableMenu.getModel();
        int fila = tableMenu.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }
    private void btnPlatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlatosActionPerformed
        jTabbedPane1.setSelectedIndex(19);
        LimpiarTable();
        ListarPlatos(TablePlatos);
    }//GEN-LAST:event_btnPlatosActionPerformed

    private void btnSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalaActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarSalas();
        jTabbedPane1.setSelectedIndex(14);
    }//GEN-LAST:event_btnSalaActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarPedidos();
        jTabbedPane1.setSelectedIndex(18);
    }//GEN-LAST:event_btnVentasActionPerformed

    private void TablePlatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePlatosMouseClicked
        // TODO add your handling code here:
        int fila = TablePlatos.rowAtPoint(evt.getPoint());
        txtIdPlato.setText(TablePlatos.getValueAt(fila, 0).toString());
        txtNombrePlato.setText(TablePlatos.getValueAt(fila, 1).toString());
        txtPrecioPlato.setText(TablePlatos.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_TablePlatosMouseClicked

    private void btnNuevoPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPlatoActionPerformed
        // TODO add your handling code here:
        LimpiarPlatos();
    }//GEN-LAST:event_btnNuevoPlatoActionPerformed

    private void btnEliminarPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPlatoActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdPlato.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdPlato.getText());
                plaDao.Eliminar(id);
                FrmLogin l = new FrmLogin();
                l.exito("Plato eliminado");
                LimpiarTable();
                LimpiarPlatos();
                ListarPlatos(TablePlatos);
            }
        } else {
            //JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }
    }//GEN-LAST:event_btnEliminarPlatoActionPerformed

    private void btnEditarPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPlatoActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdPlato.getText())) {
            //JOptionPane.showMessageDialog(null, "Seleecione una fila");
            FrmLogin l = new FrmLogin();
            l.advertencia( "Seleccione una fila");
        } else {
            if (!"".equals(txtNombrePlato.getText()) || !"".equals(txtPrecioPlato.getText())) {
                pla.setNombre(txtNombrePlato.getText());
                pla.setPrecio(Double.parseDouble(txtPrecioPlato.getText()));
                pla.setId(Integer.parseInt(txtIdPlato.getText()));
                if (plaDao.Modificar(pla)) {
                    //JOptionPane.showMessageDialog(null, "Plato Modificado");
                    FrmLogin l = new FrmLogin();
                    l.exito("Plato Modificado");
                    LimpiarTable();
                    ListarPlatos(TablePlatos);
                    LimpiarPlatos();
                }

            }
        }
    }//GEN-LAST:event_btnEditarPlatoActionPerformed

    private void btnGuardarPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlatoActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtNombrePlato.getText()) || !"".equals(txtPrecioPlato.getText())) {
            pla.setNombre(txtNombrePlato.getText());
            pla.setPrecio(Double.parseDouble(txtPrecioPlato.getText()));
            pla.setFecha(fechaFormato);
            if (plaDao.Registrar(pla)) {
                //JOptionPane.showMessageDialog(null, "Plato Registrado");
                FrmLogin l = new FrmLogin();
                l.exito("Plato Registrado");
                LimpiarTable();
                ListarPlatos(TablePlatos);
                LimpiarPlatos();
            }

        } else {
            //JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            FrmLogin l = new FrmLogin();
            l.advertencia("Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarPlatoActionPerformed

    private void txtPrecioPlatoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioPlatoKeyTyped
        // TODO add your handling code here:
        event.numberDecimalKeyPress(evt, txtPrecioPlato);
    }//GEN-LAST:event_txtPrecioPlatoKeyTyped

    private void TablePedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePedidosMouseClicked
        // TODO add your handling code here:
       int fila = TablePedidos.rowAtPoint(evt.getPoint());
        int id_pedido = Integer.parseInt(TablePedidos.getValueAt(fila, 0).toString());
        LimpiarTable();
        verPedido(id_pedido);
        verPedidoDetalle(id_pedido);
        jTabbedPane1.setSelectedIndex(17);
        btnFinalizar.setEnabled(false);
        txtIdHistorialPedido.setText(""+id_pedido);
    }//GEN-LAST:event_TablePedidosMouseClicked

    private void btnPdfPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfPedidoActionPerformed

        if (txtIdHistorialPedido.getText().equals("")) {
            //JOptionPane.showMessageDialog(null, "Selecciona una fila");
            FrmLogin l = new FrmLogin();
            l.advertencia("Selecciona una fila");
        } else {
            pedDao.pdfPedido(Integer.parseInt(txtIdHistorialPedido.getText()));
            txtIdHistorialPedido.setText("");
        }
    }//GEN-LAST:event_btnPdfPedidoActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        // TODO add your handling code here:
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Está seguro de finalizar?");
        if (pregunta == 0) {
            if (pedDao.actualizarEstado(Integer.parseInt(txtIdPedido.getText()))) {
                pedDao.pdfPedido(Integer.parseInt(txtIdPedido.getText()));
            }
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnEliminarTempPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTempPlatoActionPerformed
        modelo = (DefaultTableModel) tableMenu.getModel();
        modelo.removeRow(tableMenu.getSelectedRow());
        TotalPagar(tableMenu, totalMenu);
    }//GEN-LAST:event_btnEliminarTempPlatoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (txtComentario.getText().equals("")) {
            //JOptionPane.showMessageDialog(null, "Seleccione una fila");
            FrmLogin l = new FrmLogin();
            l.advertencia("Selecciona una fila");
        } else {
            int id = Integer.parseInt(tableMenu.getValueAt(tableMenu.getSelectedRow(), 0).toString());
            for (int i = 0; i < tableMenu.getRowCount(); i++) {
                if (tableMenu.getValueAt(i, 0).equals(id)) {
                    tmp.setValueAt(txtComentario.getText(), i, 5);
                    txtComentario.setText("");
                    tableMenu.clearSelection();
                    return;
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGenerarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPedidoActionPerformed
        if (tableMenu.getRowCount() > 0) {
            RegistrarPedido();
            detallePedido();
            LimpiarTableMenu();
            //JOptionPane.showMessageDialog(null, "Pedido Registrado");
            FrmLogin l = new FrmLogin();
            l.exito("Pedido Registrado");
            jTabbedPane1.setSelectedIndex(20);
        } else {
            //JOptionPane.showMessageDialog(null, "NO HAY PRODUCTO EN LA PEDIDO");
            FrmLogin l = new FrmLogin();
            l.advertencia("No hay pedido registrado");
        }
    }//GEN-LAST:event_btnGenerarPedidoActionPerformed

    private void btnAddPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPlatoActionPerformed
        if (tblTemPlatos.getSelectedRow() >= 0) {
            int id = Integer.parseInt(tblTemPlatos.getValueAt(tblTemPlatos.getSelectedRow(), 0).toString());
            String descripcion = tblTemPlatos.getValueAt(tblTemPlatos.getSelectedRow(), 1).toString();
            double precio = Double.parseDouble(tblTemPlatos.getValueAt(tblTemPlatos.getSelectedRow(), 2).toString());
            double total = 1 * precio;
            item = item + 1;
            tmp = (DefaultTableModel) tableMenu.getModel();
            for (int i = 0; i < tableMenu.getRowCount(); i++) {
                if (tableMenu.getValueAt(i, 0).equals(id)) {
                    int cantActual = Integer.parseInt(tableMenu.getValueAt(i, 2).toString());
                    int nuevoCantidad = cantActual + 1;
                    double nuevoSub = precio * nuevoCantidad;
                    tmp.setValueAt(nuevoCantidad, i, 2);
                    tmp.setValueAt(nuevoSub, i, 4);
                    TotalPagar(tableMenu, totalMenu);
                    return;
                }
            }
            ArrayList lista = new ArrayList();
            lista.add(item);
            lista.add(id);
            lista.add(descripcion);
            lista.add(1);
            lista.add(precio);
            lista.add(total);
            Object[] O = new Object[6];
            O[0] = lista.get(1);
            O[1] = lista.get(2);
            O[2] = lista.get(3);
            O[3] = lista.get(4);
            O[4] = lista.get(5);
            O[5] = "";
            tmp.addRow(O);
            tableMenu.setModel(tmp);
            TotalPagar(tableMenu, totalMenu);
        } else {
            //JOptionPane.showMessageDialog(null, "SELECCIONA UNA FILA");
             FrmLogin l = new FrmLogin();
            l.advertencia("Seleccione una fila");
        }
    }//GEN-LAST:event_btnAddPlatoActionPerformed

    private void txtBuscarPlatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPlatoKeyReleased
        LimpiarTable();
        ListarPlatos(tblTemPlatos);
    }//GEN-LAST:event_txtBuscarPlatoKeyReleased

    private void btnEliminarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSalaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdSala.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdSala.getText());
                slDao.Eliminar(id);
                LimpiarSala();
                LimpiarTable();
                ListarSalas();
            }
        } else {
            //JOptionPane.showMessageDialog(null, "Seleccione una fila");
            FrmLogin l = new FrmLogin();
            l.advertencia("Seleccione una fila");
        }
    }//GEN-LAST:event_btnEliminarSalaActionPerformed

    private void btnNuevoSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoSalaActionPerformed
        // TODO add your handling code here:
        LimpiarSala();
    }//GEN-LAST:event_btnNuevoSalaActionPerformed

    private void btnActualizarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarSalaActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdSala.getText())) {
            //JOptionPane.showMessageDialog(null, "Seleecione una fila");
            FrmLogin l = new FrmLogin();
            l.advertencia("Seleccione una fila");
        } else {
            if (!"".equals(txtNombreSala.getText())) {
                sl.setNombre(txtNombreSala.getText());
                sl.setId(Integer.parseInt(txtIdSala.getText()));
                slDao.Modificar(sl);
                //JOptionPane.showMessageDialog(null, "Sala Modificado");
                FrmLogin l = new FrmLogin();
                l.exito("Sala modificada");
                LimpiarSala();
                LimpiarTable();
                ListarSalas();
            }
        }
    }//GEN-LAST:event_btnActualizarSalaActionPerformed

    private void btnRegistrarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSalaActionPerformed
        // TODO add your handling code here:
        if (txtNombreSala.getText().equals("") || txtMesas.getText().equals("")) {
            //JOptionPane.showMessageDialog(null, "Los campos esta vacios");
            FrmLogin l = new FrmLogin();
            l.advertencia("Los campos están vacíos ");
        } else {
            sl.setNombre(txtNombreSala.getText());
            sl.setMesas(Integer.parseInt(txtMesas.getText()));
            slDao.RegistrarSala(sl);
            //JOptionPane.showMessageDialog(null, "Sala Registrado");
            FrmLogin l = new FrmLogin();
            l.exito("Sala registrada");
            LimpiarSala();
            LimpiarTable();
            ListarSalas();
        }
    }//GEN-LAST:event_btnRegistrarSalaActionPerformed

    private void tableSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSalaMouseClicked
        // TODO add your handling code here:
        int fila = tableSala.rowAtPoint(evt.getPoint());
        txtIdSala.setText(tableSala.getValueAt(fila, 0).toString());
        txtNombreSala.setText(tableSala.getValueAt(fila, 1).toString());
        txtMesas.setText(tableSala.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_tableSalaMouseClicked

    private void labelLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLogoMouseClicked
        jTabbedPane1.setSelectedIndex(20);
        PanelSalas.removeAll();
        PanelSalas();
    }//GEN-LAST:event_labelLogoMouseClicked

    private void txtIdMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdMedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMedActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
            // Crear una nueva instancia de FrmLogin
    FrmLogin loginFrame = new FrmLogin();
    
    // Hacer visible el FrmLogin
    loginFrame.setVisible(true);
    
    // Ocultar (o cerrar) el JFrame actual
    this.dispose(); 
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtIdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdLoginActionPerformed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void txtActual2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActual2ActionPerformed
        //String usuario = txtActual2.getText();
        
        // Mostrar el nombre de usuario en la etiqueta
        //txtActual2.setText("Nombre de Usuario: " + usuario);
    }//GEN-LAST:event_txtActual2ActionPerformed

    private void jMenuReingresarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReingresarProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuReingresarProductosActionPerformed

    private void JLabelClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelClientesMouseEntered
        Color clr = new Color (0,153,204);
        MenuClientes.setBackground(clr);
    }//GEN-LAST:event_JLabelClientesMouseEntered

    private void JLabelClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelClientesMouseExited
        Color clr = new Color (14,76,117);
        MenuClientes.setBackground(clr);
    }//GEN-LAST:event_JLabelClientesMouseExited

    private void JLabelVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelVentasMouseEntered
        Color clr = new Color (0,153,204);
        MenuVentas.setBackground(clr);
    }//GEN-LAST:event_JLabelVentasMouseEntered

    private void JLabelVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelVentasMouseExited
        Color clr = new Color (14,76,117);
        MenuVentas.setBackground(clr);
    }//GEN-LAST:event_JLabelVentasMouseExited

    private void JLabelComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelComprasMouseEntered
        Color clr = new Color (0,153,204);
        MenuCompras.setBackground(clr);
    }//GEN-LAST:event_JLabelComprasMouseEntered

    private void JLabelComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelComprasMouseExited
        Color clr = new Color (14,76,117);
        MenuCompras.setBackground(clr);
    }//GEN-LAST:event_JLabelComprasMouseExited

    private void JLabelProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelProductosMouseEntered
        Color clr = new Color (0,153,204);
        MenuProductos.setBackground(clr);
    }//GEN-LAST:event_JLabelProductosMouseEntered

    private void JLabelProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelProductosMouseExited
        Color clr = new Color (14,76,117);
        MenuProductos.setBackground(clr);
    }//GEN-LAST:event_JLabelProductosMouseExited

    private void JLabelProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelProveedorMouseEntered
        Color clr = new Color (0,153,204);
        MenuProv.setBackground(clr);
    }//GEN-LAST:event_JLabelProveedorMouseEntered

    private void JLabelProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelProveedorMouseExited
        Color clr = new Color (14,76,117);
        MenuProv.setBackground(clr);
    }//GEN-LAST:event_JLabelProveedorMouseExited

    private void JLabelMedidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelMedidasMouseClicked
        
    }//GEN-LAST:event_JLabelMedidasMouseClicked

    private void JLabelMedidasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelMedidasMouseEntered
        Color clr = new Color (0,153,204);
        MenuMedidas.setBackground(clr);
    }//GEN-LAST:event_JLabelMedidasMouseEntered

    private void JLabelMedidasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelMedidasMouseExited
        Color clr = new Color (14,76,117);
        MenuMedidas.setBackground(clr);
    }//GEN-LAST:event_JLabelMedidasMouseExited

    private void JLabelCatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelCatMouseEntered
        Color clr = new Color (0,153,204);
        MenuCat.setBackground(clr);
    }//GEN-LAST:event_JLabelCatMouseEntered

    private void JLabelCatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelCatMouseExited
        Color clr = new Color (14,76,117);
        MenuCat.setBackground(clr);
    }//GEN-LAST:event_JLabelCatMouseExited

    private void btnPlatosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlatosMouseEntered
        Color clr = new Color (0,153,204);
        btnPlatos.setBackground(clr);
    }//GEN-LAST:event_btnPlatosMouseEntered

    private void btnPlatosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlatosMouseExited
        Color clr = new Color (14,76,117);
        btnPlatos.setBackground(clr);
    }//GEN-LAST:event_btnPlatosMouseExited

    private void btnSalaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalaMouseEntered
        Color clr = new Color (0,153,204);
        btnSala.setBackground(clr);
    }//GEN-LAST:event_btnSalaMouseEntered

    private void btnSalaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalaMouseExited
        Color clr = new Color (14,76,117);
        btnSala.setBackground(clr);
    }//GEN-LAST:event_btnSalaMouseExited

    private void btnVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseEntered
        Color clr = new Color (0,153,204);
        btnVentas.setBackground(clr);
    }//GEN-LAST:event_btnVentasMouseEntered

    private void btnVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseExited
        Color clr = new Color (14,76,117);
        btnVentas.setBackground(clr);
    }//GEN-LAST:event_btnVentasMouseExited

    private void JLabelUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelUsuariosMouseEntered
        Color clr = new Color (0,153,204);
        MenuUsuarios.setBackground(clr);
    }//GEN-LAST:event_JLabelUsuariosMouseEntered

    private void JLabelUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelUsuariosMouseExited
        Color clr = new Color (14,76,117);
        MenuUsuarios.setBackground(clr);
    }//GEN-LAST:event_JLabelUsuariosMouseExited

    private void JLabelConfigMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelConfigMouseEntered
        Color clr = new Color (0,153,204);
        MenuConfig.setBackground(clr);
    }//GEN-LAST:event_JLabelConfigMouseEntered

    private void JLabelConfigMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelConfigMouseExited
        Color clr = new Color (14,76,117);
        MenuConfig.setBackground(clr);
    }//GEN-LAST:event_JLabelConfigMouseExited

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel JLHome;
    public javax.swing.JLabel JLabelCat;
    public javax.swing.JLabel JLabelClientes;
    public javax.swing.JLabel JLabelCompra;
    public javax.swing.JLabel JLabelCompras;
    public javax.swing.JLabel JLabelConfig;
    public javax.swing.JLabel JLabelMedidas;
    public javax.swing.JLabel JLabelProductos;
    public javax.swing.JLabel JLabelProveedor;
    public javax.swing.JLabel JLabelTotalVentas;
    public javax.swing.JLabel JLabelUsuarios;
    public javax.swing.JLabel JLabelVenta;
    public javax.swing.JLabel JLabelVentas;
    public javax.swing.JMenuItem JMenuEliminarCat;
    public javax.swing.JMenuItem JMenuEliminarCli;
    public javax.swing.JMenuItem JMenuEliminarMed;
    public javax.swing.JMenuItem JMenuEliminarPro;
    public javax.swing.JMenuItem JMenuEliminarProv;
    public javax.swing.JMenuItem JMenuEliminarUser;
    public javax.swing.JMenuItem JMenuEliminarUsuarios;
    public javax.swing.JMenuItem JMenuReingresarCat;
    public javax.swing.JMenuItem JMenuReingresarCli;
    public javax.swing.JMenuItem JMenuReingresarMed;
    public javax.swing.JMenuItem JMenuReingresarPro;
    public javax.swing.JMenuItem JMenuReingresarProv;
    public javax.swing.JMenuItem JMenuReingresarUser;
    public javax.swing.JMenuItem JMenuReingresarUsuarios;
    public javax.swing.JPanel JPBarra;
    public javax.swing.JPanel JPTorta;
    public javax.swing.JPanel JPaginarCaja;
    public javax.swing.JPanel JPaginarCat;
    public javax.swing.JPanel JPaginarCli;
    public javax.swing.JPanel JPaginarMedida;
    public javax.swing.JPanel JPaginarPro;
    public javax.swing.JPanel JPaginarProv;
    public javax.swing.JPanel JPaginarUser;
    private javax.swing.JPanel JPanelGrafico;
    public javax.swing.JPanel JPanelVentas;
    public javax.swing.JPanel JPanelVentas1;
    private javax.swing.JPopupMenu JPopupCat;
    private javax.swing.JPopupMenu JPopupClientes;
    public javax.swing.JPopupMenu JPopupMedida;
    private javax.swing.JPopupMenu JPopupPro;
    private javax.swing.JPopupMenu JPopupProveedor;
    public javax.swing.JPopupMenu JPopupUser;
    private javax.swing.JLabel LabelVendedor;
    private javax.swing.JLabel LabelVendedor1;
    public javax.swing.JPanel MenuCat;
    public javax.swing.JPanel MenuClientes;
    public javax.swing.JPanel MenuCompras;
    public javax.swing.JPanel MenuConfig;
    public javax.swing.JPanel MenuMedidas;
    public javax.swing.JPanel MenuProductos;
    public javax.swing.JPanel MenuProv;
    public javax.swing.JPanel MenuUsuarios;
    public javax.swing.JPanel MenuVentas;
    private javax.swing.JPanel PanelMesas;
    private javax.swing.JPanel PanelSalas;
    public javax.swing.JTable TableCaja;
    public javax.swing.JTable TableCat;
    public javax.swing.JTable TableClientes;
    public javax.swing.JTable TableCompra;
    public javax.swing.JTable TableCompras;
    public javax.swing.JTable TableMedidas;
    public javax.swing.JTable TableNuevaVenta;
    private javax.swing.JTable TablePedidos;
    private javax.swing.JTable TablePlatos;
    public javax.swing.JTable TableProductos;
    public javax.swing.JTable TableProveedor;
    public javax.swing.JTable TableUsers;
    public javax.swing.JTable TableVentas;
    public javax.swing.JLabel TotalClientes;
    public javax.swing.JLabel TotalCompras;
    public javax.swing.JLabel TotalPro;
    private javax.swing.JButton btnActualizarSala;
    private javax.swing.JButton btnAddPlato;
    public javax.swing.JButton btnCaja;
    public javax.swing.JButton btnCambiar;
    public javax.swing.JButton btnCambiarPass;
    public javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditarPlato;
    private javax.swing.JButton btnEliminarPlato;
    private javax.swing.JButton btnEliminarSala;
    private javax.swing.JButton btnEliminarTempPlato;
    private javax.swing.JButton btnFinalizar;
    public javax.swing.JButton btnGenerarC;
    private javax.swing.JButton btnGenerarPedido;
    public javax.swing.JButton btnGenerarVenta;
    public javax.swing.JButton btnGuardarCaja;
    public javax.swing.JButton btnGuardarCat;
    public javax.swing.JButton btnGuardarCli;
    public javax.swing.JButton btnGuardarMed;
    private javax.swing.JButton btnGuardarPlato;
    public javax.swing.JButton btnGuardarPro;
    public javax.swing.JButton btnGuardarProv;
    public javax.swing.JButton btnGuardarUser;
    public javax.swing.JButton btnModificarCaja;
    public javax.swing.JButton btnModificarCat;
    public javax.swing.JButton btnModificarCli;
    public javax.swing.JButton btnModificarEmpresa;
    public javax.swing.JButton btnModificarMed;
    public javax.swing.JButton btnModificarPro;
    public javax.swing.JButton btnModificarProv;
    public javax.swing.JButton btnModificarUser;
    public javax.swing.JButton btnNuevoCaja;
    public javax.swing.JButton btnNuevoCat;
    public javax.swing.JButton btnNuevoCli;
    public javax.swing.JButton btnNuevoMed;
    private javax.swing.JButton btnNuevoPlato;
    public javax.swing.JButton btnNuevoPro;
    public javax.swing.JButton btnNuevoProv;
    private javax.swing.JButton btnNuevoSala;
    public javax.swing.JButton btnNuevoUser;
    public javax.swing.JButton btnPdfC;
    private javax.swing.JButton btnPdfPedido;
    public javax.swing.JButton btnPdfV;
    private javax.swing.JButton btnPlatos;
    private javax.swing.JButton btnRegistrarSala;
    private javax.swing.JButton btnSala;
    private javax.swing.JButton btnVentas;
    public javax.swing.JComboBox<String> cbxCajaUser;
    public javax.swing.JComboBox<String> cbxCat;
    public javax.swing.JComboBox<String> cbxMedida;
    public javax.swing.JComboBox<String> cbxProveedor;
    public javax.swing.JComboBox<String> cbxRolUser;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    public javax.swing.JMenuItem jMenuEliminarProductos;
    public javax.swing.JMenuItem jMenuReingresarProductos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JPopupMenu jPopupProductos;
    private javax.swing.JPopupMenu jPopupUsuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    public javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JTable tableFinalizar;
    private javax.swing.JTable tableMenu;
    public javax.swing.JTable tableSala;
    private javax.swing.JTable tblTemPlatos;
    private javax.swing.JLabel totalFinalizar;
    private javax.swing.JLabel totalMenu;
    public javax.swing.JTextField txtActual;
    public javax.swing.JTextField txtActual1;
    public javax.swing.JTextField txtActual2;
    public javax.swing.JTextField txtBuscarCaja;
    public javax.swing.JTextField txtBuscarCat;
    public javax.swing.JTextField txtBuscarCli;
    public javax.swing.JTextField txtBuscarMed;
    private javax.swing.JTextField txtBuscarPlato;
    public javax.swing.JTextField txtBuscarPro;
    public javax.swing.JTextField txtBuscarProv;
    public javax.swing.JTextField txtBuscarUser;
    public javax.swing.JTextField txtBuscarVentas;
    public javax.swing.JTextField txtBuscarVentas1;
    public javax.swing.JTextField txtCajaLogin;
    public javax.swing.JTextField txtCantidadC;
    public javax.swing.JTextField txtCantidadNV;
    public javax.swing.JPasswordField txtClaveUser;
    public javax.swing.JComboBox<String> txtClienteNV;
    public javax.swing.JTextField txtCodigoC;
    public javax.swing.JTextField txtCodigoNV;
    public javax.swing.JTextField txtCodigoPro;
    private javax.swing.JTextPane txtComentario;
    public javax.swing.JTextField txtDireccionCli;
    public javax.swing.JTextPane txtDireccionEmpresa;
    public javax.swing.JTextPane txtDireccionProv;
    public com.toedter.calendar.JDateChooser txtFechaApertura;
    public com.toedter.calendar.JDateChooser txtFechaCierre;
    private javax.swing.JTextField txtFechaHora;
    public javax.swing.JTextField txtIdC;
    public javax.swing.JTextField txtIdCaja;
    public javax.swing.JTextField txtIdCat;
    public javax.swing.JTextField txtIdCli;
    public javax.swing.JTextField txtIdConfig;
    private javax.swing.JTextField txtIdHistorialPedido;
    public javax.swing.JTextField txtIdLogin;
    public javax.swing.JTextField txtIdMed;
    public javax.swing.JTextField txtIdNV;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextField txtIdPlato;
    public javax.swing.JTextField txtIdPro;
    public javax.swing.JTextField txtIdProv;
    private javax.swing.JTextField txtIdSala;
    public javax.swing.JTextField txtIdUser;
    public javax.swing.JTextPane txtMensaje;
    private javax.swing.JTextField txtMesas;
    public javax.swing.JTextField txtMontoFinal;
    public javax.swing.JTextField txtMontoInicial;
    public javax.swing.JTextField txtNombreCat;
    public javax.swing.JTextField txtNombreCli;
    public javax.swing.JTextField txtNombreCortoMed;
    public javax.swing.JTextField txtNombreEmpresa;
    public javax.swing.JTextField txtNombreMed;
    private javax.swing.JTextField txtNombrePlato;
    public javax.swing.JTextField txtNombrePro;
    public javax.swing.JTextField txtNombreProv;
    private javax.swing.JTextField txtNombreSala;
    public javax.swing.JTextField txtNombreUser;
    public javax.swing.JPasswordField txtNueva;
    private javax.swing.JTextField txtNumMesaFinalizar;
    public javax.swing.JTextField txtPagarCon;
    public javax.swing.JTextField txtPrecioC;
    public javax.swing.JTextField txtPrecioCompraPro;
    public javax.swing.JTextField txtPrecioNV;
    private javax.swing.JTextField txtPrecioPlato;
    public javax.swing.JTextField txtPrecioVentaPro;
    public javax.swing.JTextField txtProductoC;
    public javax.swing.JTextField txtProductoNV;
    public javax.swing.JComboBox<String> txtProveedorC;
    public javax.swing.JTextField txtRucEmpresa;
    public javax.swing.JTextField txtRucProv;
    private javax.swing.JTextField txtSalaFinalizar;
    public javax.swing.JTextField txtStockDisponible;
    public javax.swing.JTextField txtTelefonoCli;
    public javax.swing.JTextField txtTelefonoEmpresa;
    public javax.swing.JTextField txtTelefonoProv;
    private javax.swing.JTextField txtTempIdSala;
    private javax.swing.JTextField txtTempNumMesa;
    public javax.swing.JTextField txtTotalC;
    public javax.swing.JTextField txtTotalNV;
    public javax.swing.JLabel txtTotalPagar;
    public javax.swing.JLabel txtTotalPagarC;
    public javax.swing.JTextField txtTotalVentas;
    public javax.swing.JTextField txtUserLogin;
    public javax.swing.JTextField txtUsuario;
    public javax.swing.JTextField txtVentasCliente;
    public javax.swing.JTextField txtVentasCliente1;
    public javax.swing.JTextField txtVentasId;
    public javax.swing.JTextField txtVentasId1;
    public javax.swing.JTextField txtVentasTotal;
    public javax.swing.JTextField txtVentasTotal1;
    public javax.swing.JTextField txtVentasUsuario;
    public javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables

}
