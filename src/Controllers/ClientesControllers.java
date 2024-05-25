package Controllers;


import Models.Clientes;
import Models.ClientesDao;
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
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class ClientesControllers implements ActionListener, MouseListener, KeyListener, TableModelListener {

    private final Clientes cli;
    private final ClientesDao cliDao;
    private final PanelAdmin views;
    DefaultTableModel modeloCli = new DefaultTableModel();
    //paginador
    private PaginadoTable<Clientes> paginadoC;
    private JComboBox<Integer> cbxFilasPermitidasC;

    public ClientesControllers(Clientes cli, ClientesDao cliDao, PanelAdmin views) {
        this.cli = cli;
        this.cliDao = cliDao;
        this.views = views;
        this.views.btnGuardarCli.addActionListener(this);
        this.views.btnNuevoCli.addActionListener(this);
        this.views.btnModificarCli.addActionListener(this);
        this.views.TableClientes.addMouseListener(this);
        this.views.JLabelClientes.addMouseListener(this);
        this.views.JMenuEliminarCli.addActionListener(this);
        this.views.JMenuReingresarCli.addActionListener(this);
        this.views.txtBuscarCli.addKeyListener(this);
        this.views.txtTelefonoCli.addKeyListener(this);
        this.views.txtNombreCli.addKeyListener(this);
        this.views.txtDireccionCli.addKeyListener(this);
        this.views.TableClientes.getModel().addTableModelListener(this);
        this.views.txtIdCli.setVisible(false);
        this.views.btnModificarCli.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarCli) {
            if (views.txtNombreCli.getText().equals("") || views.txtTelefonoCli.getText().equals("") || views.txtDireccionCli.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios");
                FrmLogin l = new FrmLogin();
                l.advertencia("Todos los campos son obligatorios");
            } else {
                cli.setNombre(views.txtNombreCli.getText());
                cli.setTelefono(views.txtTelefonoCli.getText());
                cli.setDireccion(views.txtDireccionCli.getText());
                if (cliDao.registrar(cli)) {
                    Nuevo();
                    Listar();
                    //JOptionPane.showMessageDialog(null, "Cliente Registrado");
                    FrmLogin l = new FrmLogin();
                    l.exito("Cliente registrado");
                } else {
                    //JOptionPane.showMessageDialog(null, "El cliente ya existe");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("El cliente ya existe");
                }
            }
        } else if (e.getSource() == views.btnModificarCli) {
            if (views.txtIdCli.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila");
                FrmLogin l = new FrmLogin();
            l.advertencia("Seleccione una fila");
            } else {
                if (views.txtNombreCli.getText().equals("") || views.txtTelefonoCli.getText().equals("") || views.txtDireccionCli.getText().equals("")) {
                    //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("Todos los campos son obligatorios");
                } else {
                    cli.setNombre(views.txtNombreCli.getText());
                    cli.setTelefono(views.txtTelefonoCli.getText());
                    cli.setDireccion(views.txtDireccionCli.getText());
                    cli.setId(Integer.parseInt(views.txtIdCli.getText()));
                    if (cliDao.modificar(cli)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Cliente Modificado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Cliente modificado");
                    }
                }
            }
        } else if (e.getSource() == views.JMenuEliminarCli) {
            if (views.txtIdCli.getText().equals("") || views.txtIdCli.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para eliminar");
            } else {
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdCli.getText());
                    if (cliDao.accion("Inactivo", id)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Cliente Eliminado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Cliente eliminado");
                    }
                }
            }
        } else if (e.getSource() == views.JMenuReingresarCli) {
            if (views.txtIdCli.getText().equals("") || views.txtIdCli.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para reingresar");
            } else {
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de reingresar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdCli.getText());
                    if (cliDao.accion("Activo", id)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Cliente Reingresado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Cliente reingresado");
                    }
                }
            }
        } else if (e.getSource() == views.btnNuevoCli) {
            Nuevo();
        } else if (e.getSource() == cbxFilasPermitidasC) {
            paginadoC.eventosPag(cbxFilasPermitidasC);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.views.btnModificarCli.setEnabled(true);
        if (e.getSource() == views.TableClientes) {
            int fila = views.TableClientes.rowAtPoint(e.getPoint());
            String estado = views.TableClientes.getValueAt(fila, 4).toString();
            if (estado.equals("Inactivo")){
                views.JMenuEliminarCli.setVisible(false);
                views.JMenuReingresarCli.setVisible(true);
            }else{
                views.JMenuEliminarCli.setVisible(true);
                views.JMenuReingresarCli.setVisible(false);
            }
            views.txtIdCli.setText(views.TableClientes.getValueAt(fila, 0).toString());
            views.txtNombreCli.setText(views.TableClientes.getValueAt(fila, 1).toString());
            views.txtTelefonoCli.setText(views.TableClientes.getValueAt(fila, 2).toString());
            views.txtDireccionCli.setText(views.TableClientes.getValueAt(fila, 3).toString());
        } else if (e.getSource() == views.JLabelClientes) {
            views.jTabbedPane1.setSelectedIndex(3);
            //menu();
            Listar();
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
        views.txtIdCli.setText("");
        views.txtNombreCli.setText("");
        views.txtTelefonoCli.setText("");
        views.txtDireccionCli.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == views.txtTelefonoCli) {
            if (views.txtTelefonoCli.getText().length() >= 9) {
                e.consume();
            } else {
                validarNum(views.txtTelefonoCli.getText().trim());
            }
        } else {
            char caracter = e.getKeyChar();
            if (Character.isLowerCase(caracter)) {
                e.setKeyChar(Character.toUpperCase(caracter));
            }
        }
    }

    private static boolean validarNum(String campo) {
        return campo.matches("[0-9][1,9]");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == views.txtNombreCli) {
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtTelefonoCli.requestFocus();
           }
        }else{
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtDireccionCli.requestFocus();
           } 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarCli) {
            Listar();
        }
    }


    //paginador
    private void Listar() {
        Tables color = new Tables();
        views.TableClientes.setDefaultRenderer(views.TableClientes.getColumnClass(0), color);
        views.JPaginarCli.removeAll();
        views.TableClientes.setModel(ModeloTableCli());
        TotalRows<Clientes> CliL = ListarClientes();
        paginadoC = new PaginadoTable(views.TableClientes, CliL, new int[]{20, 50, 100, 200}, 20);
        paginadoC.crearPermitidas(views.JPaginarCli);
        cbxFilasPermitidasC = paginadoC.getCbxFilasPermitidas();
        cbxFilasPermitidasC.addActionListener(this);
        views.TableClientes.getModel().addTableModelListener(this);
        cbxFilasPermitidasC.setSelectedItem(Integer.parseInt("20"));
        JTableHeader header = views.TableClientes.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
    }

    public TotalRows<Clientes> ListarClientes() {
        List<Clientes> listaCliente = cliDao.Listar(views.txtBuscarCli.getText());
        return new TotalRows<Clientes>() {
            @Override
            public int getTotalRowCount() {
                return listaCliente.size();
            }

            @Override
            public List<Clientes> getRows(int inicio, int fin) {
                return listaCliente.subList(inicio, fin);
            }

        };
    }

    private TableModel ModeloTableCli() {
        return new Paginar<Clientes>() {
            @Override
            public Object getValueAt(Clientes t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getNombre();
                    case 2:
                        return t.getTelefono();
                    case 3:
                        return t.getDireccion();
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
                        return "Nombre";
                    case 2:
                        return "Telefono";
                    case 3:
                        return "Direccion";
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
        paginadoC.paginate();
    }
}
