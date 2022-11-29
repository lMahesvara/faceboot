package components;

import entidades.Notificacion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import swingComponents.JIMSendTextPane;

public class NotificacionItem extends javax.swing.JLayeredPane {
    Notificacion notificacion;
    private JLabel label;

    public NotificacionItem(Notificacion notificacion) {
        initComponents();
        
        this.notificacion = notificacion;
        txtTexto.setEditable(false);
        txtTexto.setBackground(new Color(0, 0, 0, 0));
        txtTexto.setOpaque(false);
        
        init();
    }
    
    public void init(){
        setDestinatario(notificacion.getDestinatario().getUsuario());
        setMensaje(notificacion.getMensaje());
    }

    public void setDestinatario(String text) {
        txtTexto.setText("Destinatario: "+ text);
    }

    public void setMensaje(String texto) {
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
        txt.setText(texto);
        add(txt);
    }

    public void hideText() {
        txtTexto.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTexto = new swingComponents.JIMSendTextPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txtTexto.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txtTexto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTexto.setForeground(new java.awt.Color(0, 0, 0));
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
