package Paginador;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class PaginadoTable<T> {
    private final JTable tabla;
    private final TotalRows<T> tablePaginador;
    private final int [] arrayFilasPermitidas;
    private int FilasPermitidas;
    private Paginar<T> paginador;
    private int paginaActual=1;
    private JComboBox<Integer> cbxFilasPermitidas;
    private JPanel JPaginacion;
    private final int limiteBotones = 9;
    public PaginadoTable(JTable tabla, 
            TotalRows<T> tablePaginador, 
            int [] arrayFilasPermitidas, int FilasPermitidas) {
        this.tabla = tabla;
        this.tablePaginador = tablePaginador;
        this.arrayFilasPermitidas = arrayFilasPermitidas;
        this.FilasPermitidas = FilasPermitidas;
        init();
    }
    private void init(){
        inicializarModelo();
        paginar();
    }
    private void inicializarModelo(){
        try {
            this.paginador = (Paginar<T>) this.tabla.getModel();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void crearPermitidas(JPanel panelPag){
        JPaginacion = new JPanel(new GridLayout(1, limiteBotones, 3, 3));
        panelPag.add(JPaginacion);
        if (arrayFilasPermitidas != null) {
            Integer array[] = new Integer[arrayFilasPermitidas.length];
            for (int i = 0; i < arrayFilasPermitidas.length; i++) {
                array[i] = arrayFilasPermitidas[i];
            }
            cbxFilasPermitidas = new JComboBox<>(array);
            panelPag.add(Box.createHorizontalStrut(15));
            panelPag.add(new JLabel("Filtro"));
            panelPag.add(cbxFilasPermitidas);
        }
        
    }
    public void eventosPag (JComboBox<Integer> pageCombo){
        int filaInicialP = ((paginaActual - 1) * FilasPermitidas + 1);
        FilasPermitidas = (Integer) pageCombo.getSelectedItem();
        paginaActual = ((filaInicialP - 1)/ FilasPermitidas)+1;
        paginar();
    }
    private void agregarBotones(JPanel panelPadre, int numeroPag){
        JToggleButton toggleButton = new JToggleButton(Integer.toString(numeroPag));
        toggleButton.setFont(new Font("Arial", 14, 14));
        toggleButton.setMargin(new Insets(3, 15, 3, 15));
        panelPadre.add(toggleButton);
        if (numeroPag == paginaActual) {
            toggleButton.setSelected(true);
            toggleButton.setForeground(Color.BLUE);
        }
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaActual = Integer.parseInt(e.getActionCommand());
                paginar();
            }
        });
    }
    private void rango(JPanel panelPadre, int inicio, int fin){
        int init = inicio;
        for (inicio=init; inicio <= fin; inicio++) {
            agregarBotones(panelPadre, inicio);
        }
    }
    private JLabel crearPuntos(){
        return new JLabel("...", SwingConstants.CENTER);
    }
    public void paginate(){
        JPaginacion.removeAll();
        int totalfilas = tablePaginador.getTotalRowCount();
        int paginas = (int) Math.ceil((double)totalfilas / FilasPermitidas);
        if (paginas>limiteBotones) {
            agregarBotones(JPaginacion, 1);
            if (paginaActual<=(limiteBotones+1)/2) {
                rango(JPaginacion, 2, limiteBotones-2);
                JPaginacion.add(crearPuntos());
                agregarBotones(JPaginacion, paginas); 
            }else if(paginaActual > (paginas-((limiteBotones+1)/2))){
               JPaginacion.add(crearPuntos());
                rango(JPaginacion, paginas - limiteBotones + 3, paginas);
            }else{
                JPaginacion.add(crearPuntos());
                int inicio = paginaActual - (limiteBotones - 4)/2;
                int fin = inicio + limiteBotones - 5;
                rango(JPaginacion, inicio, fin);
                JPaginacion.add(crearPuntos());
                agregarBotones(JPaginacion, paginas);
            }
        }else{
            rango(JPaginacion, 1, paginas); 
        }
        JPaginacion.getParent().validate();
        JPaginacion.getParent().repaint();
    }
    private void paginar(){
        int inicio = (paginaActual - 1) * FilasPermitidas;
        int fin = inicio + FilasPermitidas;
        if (fin > tablePaginador.getTotalRowCount()) {
            fin = tablePaginador.getTotalRowCount();
        }
        List<T> lista = tablePaginador.getRows(inicio, fin);
        paginador.setLista(lista);
        paginador.fireTableDataChanged();
    }

    public JComboBox<Integer> getCbxFilasPermitidas() {
        return cbxFilasPermitidas;
    }

    public void setCbxFilasPermitidas(JComboBox<Integer> cbxFilasPermitidas) {
        this.cbxFilasPermitidas = cbxFilasPermitidas;
    }
    
    
         
}
