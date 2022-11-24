package components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public class ComentarioItem extends javax.swing.JLayeredPane {

    private JLabel label;

    public ComentarioItem() {
        initComponents();
        txtTexto.setEditable(false);
        txtTexto.setBackground(new Color(0, 0, 0, 0));
        txtTexto.setOpaque(false);
    }

    public void setUsuario(String text) {
        txtTexto.setText(text);
    }

    
    public void setComentario(String texto) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        layer.setBorder(new EmptyBorder(0, 5, 10, 5));
        label = new JLabel(texto);
        label.setForeground(new Color(113, 164, 155));
        label.setHorizontalTextPosition(JLabel.LEFT);
        layer.add(label);
        add(label);
    }

    public void hideText() {
        txtTexto.setVisible(false);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTexto = new swingComponents.JIMSendTextPane();

        setBackground(new java.awt.Color(60, 63, 65));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txtTexto.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txtTexto.setForeground(new java.awt.Color(255, 255, 255));
        txtTexto.setSelectionColor(new java.awt.Color(92, 188, 255));
        add(txtTexto);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swingComponents.JIMSendTextPane txtTexto;
    // End of variables declaration//GEN-END:variables
}