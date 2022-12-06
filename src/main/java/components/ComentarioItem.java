 package components;

import entidades.Comentario;
import events.EventoEliminarComentario;
import interfaces.IFachadaConexion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import logica.Context;
import logica.FachadaConexion;
import observers.ObserverEliminarComentario;
import peticiones.PeticionComentario;
import swingComponents.JIMSendTextPane;

public class ComentarioItem extends javax.swing.JLayeredPane implements ObserverEliminarComentario{

    private Comentario comentario;
    private PublicacionComentarios parent;
    private IFachadaConexion facConexion;

    public ComentarioItem(Comentario comentario, PublicacionComentarios parent) {
        initComponents();
        EventoEliminarComentario.getInstance().addObserver(this);
        this.comentario = comentario;
        this.parent = parent;
        this.facConexion = new FachadaConexion();
        txtTexto.setEditable(false);
        txtTexto.setBackground(new Color(0, 0, 0, 0));
        this.compararUsuario();
        
        txtTexto.setOpaque(false);

    }

    public void setUsuario(String text) {
        txtTexto.setText(text);
    }

    public void setComentario(String texto) {
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
        txt.setText(texto);
        add(txt);
    }
    
    private void compararUsuario(){
        if(Context.getInstance().getUsuario().equals(comentario.getUsuario())){
            this.setBtnDelete();
        }
    }
    
    public void setBtnDelete(){
        ImageIcon image = new ImageIcon("src/main/java/images/delete.png");
        btnDelete.setIcon(new ImageIcon(image.getImage().getScaledInstance(17, 17, Image.SCALE_DEFAULT)));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcion = JOptionPane.showConfirmDialog(panComentario, "¿Estás seguro de eliminar el comentario?", "Eliminar Comentario", JOptionPane.WARNING_MESSAGE);
                if (JOptionPane.OK_OPTION != opcion) {
                    return;
                }
                facConexion.eliminarComentario(comentario);
            }
        });
    }

    public void hideText() {
        txtTexto.setVisible(false);
    }
    
    private void eliminarComentario(Comentario comentario){
        if(!this.comentario.equals(comentario))return;
        parent.remove(this);
        parent.refresh();
        parent.refreshMuro();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panComentario = new javax.swing.JPanel();
        txtTexto = new swingComponents.JIMSendTextPane();
        btnDelete = new javax.swing.JButton();

        setBackground(new java.awt.Color(241, 242, 246));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        panComentario.setBackground(null);

        txtTexto.setEditable(false);
        txtTexto.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txtTexto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTexto.setForeground(new java.awt.Color(0, 0, 0));
        txtTexto.setSelectionColor(new java.awt.Color(92, 188, 255));

        btnDelete.setBorder(null);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panComentarioLayout = new javax.swing.GroupLayout(panComentario);
        panComentario.setLayout(panComentarioLayout);
        panComentarioLayout.setHorizontalGroup(
            panComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panComentarioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        panComentarioLayout.setVerticalGroup(
            panComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panComentarioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        add(panComentario);
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
    private javax.swing.JButton btnDelete;
    private javax.swing.JPanel panComentario;
    private swingComponents.JIMSendTextPane txtTexto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateEliminarComentario(PeticionComentario peticion) {
        eliminarComentario(peticion.getComentario());
    }
}
