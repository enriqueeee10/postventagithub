/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lrami
 */
public class Tables extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int col) {
        super.getTableCellRendererComponent(jtable, o, bln, bln1, row, col);
        switch (jtable.getValueAt(row, col).toString()) {
            case "Inactivo":
                setBackground(Color.red);
                setForeground(Color.white);
                break;
                
                case "Activo":
                setBackground(Color.green);
                setForeground(Color.white);
                break;
                
                case "Pendiente":
                setBackground(Color.blue);
                setForeground(Color.white);
                break;
                
                case "Finalizado":
                setBackground(Color.red);
                setForeground(Color.white);
                break;
            default:
                setBackground(Color.white);
                setForeground(Color.black);
                break;
        }
        
        return this;
    }
}