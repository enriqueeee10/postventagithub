package Controllers;


import Models.Categorias;
import Models.CategoriasDao;
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

public class CategoriasControllers implements ActionListener, MouseListener, KeyListener, TableModelListener {

    private final Categorias cat;
    private final CategoriasDao catDao;
    private final PanelAdmin views;
    DefaultTableModel modeloCat = new DefaultTableModel();
    //paginador
    private PaginadoTable<Categorias> paginado;
    private JComboBox<Integer> cbxFilasPermitidas;

    public CategoriasControllers(Categorias cat, CategoriasDao catDao, PanelAdmin views) {
        this.cat = cat;
        this.catDao = catDao;
        this.views = views;
        this.views.btnGuardarCat.addActionListener(this);
        this.views.btnNuevoCat.addActionListener(this);
        this.views.btnModificarCat.addActionListener(this);
        this.views.TableCat.addMouseListener(this);
        this.views.JLabelCat.addMouseListener(this);
        this.views.JMenuEliminarCat.addActionListener(this);
        this.views.JMenuReingresarCat.addActionListener(this);
        this.views.txtBuscarCat.addKeyListener(this);
        this.views.txtNombreCat.addKeyListener(this);
        this.views.btnModificarCat.setEnabled(false);
        this.views.txtIdCat.setVisible(false);
        this.views.TableCat.getModel().addTableModelListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarCat) {
            if (views.txtNombreCat.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Rellene el campo nombre");
                FrmLogin l = new FrmLogin();
                l.advertencia("Rellene el campo nombre");
            } else {
                cat.setNombre(views.txtNombreCat.getText());
                if (catDao.registrar(cat)) {
                    Nuevo();
                    Listar();
                    views.btnGuardarCat.setEnabled(true);
                    //JOptionPane.showMessageDialog(null, "Categoria Registrado");
                    FrmLogin l = new FrmLogin();
                    l.exito("Categoria registrada");
                } else {
                    //JOptionPane.showMessageDialog(null, "La categoria ya existe");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("La categoria ya existe");
                }
            }
        } else if (e.getSource() == views.btnModificarCat) {
            if (views.txtNombreCat.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Rellene el campo nombre");
                FrmLogin l = new FrmLogin();
                l.advertencia("Rellene el campo nombre");
            } else {
                cat.setNombre(views.txtNombreCat.getText());
                cat.setId(Integer.parseInt(views.txtIdCat.getText()));
                if (catDao.modificar(cat)) {
                    Nuevo();
                    Listar();
                    views.btnModificarCat.setEnabled(false);
                    views.btnGuardarCat.setEnabled(true);
                    //JOptionPane.showMessageDialog(null, "Categoria Modificado");
                    FrmLogin l = new FrmLogin();
                    l.exito("Categoria modificada");
                }
            }
        } else if (e.getSource() == views.JMenuEliminarCat) {
            if (views.txtIdCat.getText().equals("") || views.txtIdCat.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para eliminar");
            } else {
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdCat.getText());
                    if (catDao.accion("Inactivo", id)) {
                        Nuevo();
                        Listar();
                        views.btnModificarCat.setEnabled(false);
                        views.btnGuardarCat.setEnabled(true);
                        //JOptionPane.showMessageDialog(null, "Categoria Eliminado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Categoria eliminada");
                    }
                }
            }
        } else if (e.getSource() == views.JMenuReingresarCat) {
            if (views.txtIdCat.getText().equals("") || views.txtIdCat.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para reingresar");
            } else {
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de reingresar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdCat.getText());
                    if (catDao.accion("Activo", id)) {
                        Nuevo();
                        Listar();
                        views.btnModificarCat.setEnabled(false);
                        views.btnGuardarCat.setEnabled(true);
                        //JOptionPane.showMessageDialog(null, "Categoria Reingresado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Categoria reingresada");
                    }
                }
            }
        } else if (e.getSource() == views.btnNuevoCat) {
            Nuevo();
        } else if (e.getSource() == cbxFilasPermitidas) {
            paginado.eventosPag(cbxFilasPermitidas);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableCat) {
            int fila = views.TableCat.rowAtPoint(e.getPoint());
            String estado = views.TableCat.getValueAt(fila, 2).toString();
            if (estado.equals("Inactivo")){
                views.JMenuEliminarCat.setVisible(false);
                views.JMenuReingresarCat.setVisible(true);
            }else{
                views.JMenuEliminarCat.setVisible(true);
                views.JMenuReingresarCat.setVisible(false);
            }
            views.txtIdCat.setText(views.TableCat.getValueAt(fila, 0).toString());
            views.txtNombreCat.setText(views.TableCat.getValueAt(fila, 1).toString());
            views.btnModificarCat.setEnabled(true);
            views.btnGuardarCat.setEnabled(false);
        } else if (e.getSource() == views.JLabelCat) {
            views.jTabbedPane1.setSelectedIndex(9);
            views.btnModificarCat.setEnabled(false);
            views.btnGuardarCat.setEnabled(true);
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
        views.txtIdCat.setText("");
        views.txtNombreCat.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char caracter = e.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            e.setKeyChar(Character.toUpperCase(caracter));
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarCat) {
            Listar();
        }
    }

    //paginador
    private void Listar() {
        Tables color = new Tables();
        views.TableCat.setDefaultRenderer(views.TableCat.getColumnClass(0), color);
        views.JPaginarCat.removeAll();
        views.TableCat.setModel(ModeloTableCategorias());
        TotalRows<Categorias> UsL = ListarCategorias();
        paginado = new PaginadoTable(views.TableCat, UsL, new int[]{20, 50, 100, 200}, 20);
        paginado.crearPermitidas(views.JPaginarCat);
        cbxFilasPermitidas = paginado.getCbxFilasPermitidas();
        cbxFilasPermitidas.addActionListener(this);
        views.TableCat.getModel().addTableModelListener(this);
        cbxFilasPermitidas.setSelectedItem(Integer.parseInt("20"));
        JTableHeader header = views.TableCat.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
    }

    public TotalRows<Categorias> ListarCategorias() {
        List<Categorias> listaCat = catDao.Listar(views.txtBuscarCat.getText());
        return new TotalRows<Categorias>() {
            @Override
            public int getTotalRowCount() {
                return listaCat.size();
            }

            @Override
            public List<Categorias> getRows(int inicio, int fin) {
                return listaCat.subList(inicio, fin);
            }

        };
    }

    private TableModel ModeloTableCategorias() {
        return new Paginar<Categorias>() {
            @Override
            public Object getValueAt(Categorias t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getNombre();
                    case 2:
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
                        return "Estado";
                }
                return null;

            }

            @Override
            public int getColumnCount() {
                return 3;
            }

        };
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        paginado.paginate();
    }

}
