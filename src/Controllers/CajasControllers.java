package Controllers;


import Models.Cajas;
import Models.CajasDao;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class CajasControllers implements ActionListener, MouseListener, KeyListener, TableModelListener{
    
    private final Cajas ca;
    private final CajasDao caDao;
    private final PanelAdmin views;
    Date fecha = new Date();
    DefaultTableModel modeloCaja = new DefaultTableModel();
    //paginador
    private PaginadoTable<Cajas> paginado;
    private JComboBox<Integer> cbxFilasPermitidas;
    public CajasControllers(Cajas ca, CajasDao caDao, PanelAdmin views) {
        this.ca = ca;
        this.caDao = caDao;
        this.views = views;
        this.views.btnGuardarCaja.addActionListener(this);
        this.views.btnNuevoCaja.addActionListener(this);
        this.views.btnModificarCaja.addActionListener(this);
        this.views.TableCaja.addMouseListener(this);
        this.views.btnCaja.addActionListener(this);
        this.views.txtBuscarCaja.addKeyListener(this);
        this.views.txtMontoInicial.addKeyListener(this);
        this.views.btnModificarCaja.setEnabled(false);
        this.views.txtIdCaja.setVisible(false);
        
        this.views.txtFechaApertura.setDate(fecha);
        this.views.txtFechaCierre.setDate(fecha);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarCaja) {
            if (views.txtMontoInicial.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Rellene el campo nombre");
                FrmLogin l = new FrmLogin();
                l.advertencia("Rellene el campo nombre");
            } else {
                ca.setFecha_apertura(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
                ca.setMonto_inicial(Double.parseDouble(views.txtMontoInicial.getText()));
                ca.setUsuario(views.txtUserLogin.getText());
                ca.setCaja(views.txtCajaLogin.getText());
                if (caDao.registrar(ca)) {
                    Nuevo();
                    Listar();
                    views.btnGuardarCaja.setEnabled(true);
                    //JOptionPane.showMessageDialog(null, "Caja Abierto");
                    FrmLogin l = new FrmLogin();
                    l.exito("Caja Abierta");
                }else{
                    //JOptionPane.showMessageDialog(null, "La caja ya esta abierto");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("La caja ya está abierta");
                }
            }
        } else if (e.getSource() == views.btnModificarCaja) {
            if (views.txtMontoFinal.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Rellene el campo nombre");
                FrmLogin l = new FrmLogin();
                l.advertencia("Rellene el campo nombre");
            } else {
                ca.setFecha_cierre(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
                ca.setMonto_final(Double.parseDouble(views.txtMontoFinal.getText()));
                int total = (int) Math.round(Double.parseDouble(views.txtTotalVentas.getText()));
                ca.setVentas(total);
                ca.setEstado("Cerrado");
                ca.setId(Integer.parseInt(views.txtIdCaja.getText()));
                if (caDao.cerrar(ca)) {
                    Nuevo();
                    Listar();
                    views.btnModificarCat.setEnabled(false);
                    views.btnGuardarCat.setEnabled(true);
                    //JOptionPane.showMessageDialog(null, "Caja cerrada");
                    FrmLogin l = new FrmLogin();
                    l.exito("Caja cerrada");
                }
            }
        } else if (e.getSource() == views.btnNuevoCaja) {
            Nuevo();
            views.btnGuardarCaja.setEnabled(true);
        }else if (e.getSource() == views.btnCaja) {
            views.jTabbedPane1.setSelectedIndex(7);
            views.btnModificarCaja.setEnabled(false);
            views.btnGuardarCaja.setEnabled(true);
            //menu();
            Listar();
        }else if(e.getSource() == cbxFilasPermitidas){
            paginado.eventosPag(cbxFilasPermitidas);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableCaja) {
            int fila = views.TableCaja.rowAtPoint(e.getPoint());
            views.txtIdCaja.setText(views.TableCaja.getValueAt(fila, 0).toString());
            views.txtTotalVentas.setText(""+caDao.ventas("COUNT(*)"));
            views.txtMontoFinal.setText(""+caDao.ventas("SUM(total)"));
            views.btnModificarCaja.setEnabled(true);
            views.btnGuardarCaja.setEnabled(false);
        } 
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    private void Nuevo() {
        views.txtIdCaja.setText("");
        views.txtMontoInicial.setText("");
        views.txtMontoFinal.setText("");
        views.txtTotalVentas.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarCaja) {
            Listar();
        }
    }
    //paginador
    private void Listar() {
        views.JPaginarCaja.removeAll();
        views.TableCaja.setModel(ModeloTableCaja());
        TotalRows<Cajas> CliL = ListarCajas();
        paginado = new PaginadoTable(views.TableCaja, CliL, new int[]{20, 50, 100, 200}, 20);
        paginado.crearPermitidas(views.JPaginarCaja);
        cbxFilasPermitidas = paginado.getCbxFilasPermitidas();
        cbxFilasPermitidas.addActionListener(this);
        views.TableCaja.getModel().addTableModelListener(this);
        cbxFilasPermitidas.setSelectedItem(Integer.parseInt("20"));
        JTableHeader header = views.TableCaja.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
    }

    public TotalRows<Cajas> ListarCajas() {
        List<Cajas> listaCajas = caDao.listar(views.txtBuscarCaja.getText(), views.txtUserLogin.getText());
        return new TotalRows<Cajas>() {
            @Override
            public int getTotalRowCount() {
                return listaCajas.size();
            }

            @Override
            public List<Cajas> getRows(int inicio, int fin) {
                return listaCajas.subList(inicio, fin);
            }

        };
    }
    
    private TableModel ModeloTableCaja() {
        return new Paginar<Cajas>() {
            @Override
            public Object getValueAt(Cajas t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getFecha_apertura();
                    case 2:
                        return t.getFecha_cierre();
                    case 3:
                        return t.getMonto_inicial();
                    case 4:
                        return t.getMonto_final();
                    case 5:
                        return t.getVentas();
                    case 6:
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
                        return "Fecha A";
                    case 2:
                        return "Fecha C";
                    case 3:
                        return "Monto Inicial";
                    case 4:
                        return "Monto Final";
                    case 5:
                        return "Ventas";
                    case 6:
                        return "Estado";
                }
                return null;

            }

            @Override
            public int getColumnCount() {
                return 7;
            }

        };
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        paginado.paginate();
    }
}
