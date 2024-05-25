package Controllers;


import Models.Venta;
import Models.VentaDao;
import Paginador.PaginadoTable;
import Paginador.Paginar;
import Paginador.TotalRows;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class VentasControllers implements ActionListener, MouseListener, KeyListener, TableModelListener{

    /**
     * @return the v
     */
    public Venta getV() {
        return v;
    }

    /**
     * @return the vDao
     */
    public VentaDao getvDao() {
        return vDao;
    }

    /**
     * @return the views
     */
    public PanelAdmin getViews() {
        return views;
    }

    /**
     * @return the modeloV
     */
    public DefaultTableModel getModeloV() {
        return modeloV;
    }

    /**
     * @return the paginado
     */
    public PaginadoTable<Venta> getPaginado() {
        return paginado;
    }

    /**
     * @return the cbxFilasPermitidas
     */
    public JComboBox<Integer> getCbxFilasPermitidas() {
        return cbxFilasPermitidas;
    }
    public Venta v;
    public VentaDao vDao;
    public PanelAdmin views;
    public DefaultTableModel modeloV = new DefaultTableModel();
    public PaginadoTable<Venta> paginado;
    public JComboBox<Integer> cbxFilasPermitidas;

    public VentasControllers(Venta v, VentaDao vDao, PanelAdmin views) {
        this.v = v;
        this.vDao = vDao;
        this.views = views;
        this.views.TableCompras.addMouseListener(this);
        this.views.TableVentas.addMouseListener(this);
        this.views.txtBuscarVentas.addKeyListener(this);
        this.views.txtBuscarVentas1.addKeyListener(this);
        this.views.JLabelCompra.addMouseListener(this);
        this.views.JLabelVenta.addMouseListener(this);
        this.views.TableVentas.getModel().addTableModelListener(this);
        this.views.TableCompras.getModel().addTableModelListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == getCbxFilasPermitidas()){
            getPaginado().eventosPag(getCbxFilasPermitidas());
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == getViews().JLabelVenta) {
            //menu(getViews().MenuVentas);
            Listar();
            getViews().jTabbedPane1.setSelectedIndex(11);
        }else if (e.getSource() == getViews().JLabelCompra) {
            //menu(getViews().MenuCompras);
            ListarCompras();
            getViews().jTabbedPane1.setSelectedIndex(12);
        }
        else if(e.getSource() == getViews().TableVentas){
            int fila = getViews().TableVentas.rowAtPoint(e.getPoint());
            getViews().txtVentasId.setText(getViews().TableVentas.getValueAt(fila, 0).toString());
            getViews().txtVentasUsuario.setText(getViews().TableVentas.getValueAt(fila, 1).toString());
            getViews().txtVentasTotal.setText(getViews().TableVentas.getValueAt(fila, 2).toString());
            getViews().txtVentasCliente.setText(getViews().TableVentas.getValueAt(fila, 4).toString());
            
        }else if(e.getSource() == getViews().TableCompras){
            int fila = getViews().TableCompras.rowAtPoint(e.getPoint());
            getViews().txtVentasId1.setText(getViews().TableCompras.getValueAt(fila, 0).toString());
            getViews().txtVentasTotal1.setText(getViews().TableCompras.getValueAt(fila, 1).toString());
            getViews().txtVentasCliente1.setText(getViews().TableCompras.getValueAt(fila, 3).toString());
            
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

    @Override
    public void keyTyped(KeyEvent e) {
        char caracter = e.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            e.setKeyChar(Character.toUpperCase(caracter));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == getViews().txtBuscarVentas) {
            Listar();
        }else if (e.getSource() == getViews().txtBuscarVentas1) {
            ListarCompras();
        }
    }
    //paginador
    private void Listar() {
        getViews().JPanelVentas.removeAll();
        getViews().TableVentas.setModel(ModeloTableVenta());
        TotalRows<Venta> VentaL = ListarVentas();
        paginado = new PaginadoTable(getViews().TableVentas, VentaL, new int[]{20, 50, 100, 200}, 20);
        getPaginado().crearPermitidas(getViews().JPanelVentas);
        cbxFilasPermitidas = getPaginado().getCbxFilasPermitidas();
        getCbxFilasPermitidas().addActionListener(this);
        getViews().TableVentas.getModel().addTableModelListener(this);
        getCbxFilasPermitidas().setSelectedItem(Integer.parseInt("20"));
        JTableHeader header = getViews().TableVentas.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
    }
    private void ListarCompras() {
        getViews().JPanelVentas1.removeAll();
        getViews().TableCompras.setModel(ModeloTableCompra());
        TotalRows<Venta> VentaL = ListarCom();
        paginado = new PaginadoTable(getViews().TableCompras, VentaL, new int[]{20, 50, 100, 200}, 20);
        getPaginado().crearPermitidas(getViews().JPanelVentas1);
        cbxFilasPermitidas = getPaginado().getCbxFilasPermitidas();
        getCbxFilasPermitidas().addActionListener(this);
        getViews().TableCompras.getModel().addTableModelListener(this);
        getCbxFilasPermitidas().setSelectedItem(Integer.parseInt("20"));
        JTableHeader header = getViews().TableCompras.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
    }

    public TotalRows<Venta> ListarVentas() {
        List<Venta> ListarVentas = getvDao().listar(getViews().txtBuscarVentas.getText());
        return new TotalRows<Venta>() {
            @Override
            public int getTotalRowCount() {
                return ListarVentas.size();
            }

            @Override
            public List<Venta> getRows(int inicio, int fin) {
                return ListarVentas.subList(inicio, fin);
            }

        };
    }
    public TotalRows<Venta> ListarCom() {
        List<Venta> ListarCompra= getvDao().listarC(getViews().txtBuscarVentas1.getText());
        return new TotalRows<Venta>() {
            @Override
            public int getTotalRowCount() {
                return ListarCompra.size();
            }

            @Override
            public List<Venta> getRows(int inicio, int fin) {
                return ListarCompra.subList(inicio, fin);
            }

        };
    }
    
    private TableModel ModeloTableVenta() {
        return new Paginar<Venta>() {
            @Override
            public Object getValueAt(Venta t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getUsuario();
                    case 2:
                        return t.getTotal();
                    case 3:
                        return t.getFecha();
                    case 4:
                        return t.getCliente();
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
                        return "Usuario";
                    case 2:
                        return "Total";
                    case 3:
                        return "Fecha";
                    case 4:
                        return "Cliente";
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
    
    private TableModel ModeloTableCompra() {
        return new Paginar<Venta>() {
            @Override
            public Object getValueAt(Venta t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getTotal();
                    case 2:
                        return t.getFecha();
                    case 3:
                        return t.getCliente();
                    case 4:
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
                        return "Total";
                    case 2:
                        return "Fecha";
                    case 3:
                        return "Cliente";
                    case 4:
                        return "Estado";
                }
                return null;

            }

            @Override
            public int getColumnCount() {
                return 5;
            }

        };
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        getPaginado().paginate();
    }

}
