package components;

import entidades.Publicacion;
import events.EventoEditarPublicacion;
import helpers.ConvertirImagen;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import observers.ObserverEditarPublicacion;
import peticiones.PeticionPublicacion;

public class PublicacionBody extends javax.swing.JLayeredPane implements ObserverEditarPublicacion{

    private Publicacion publicacion;

    public PublicacionBody(Publicacion publicacion) {
        initComponents();
        this.publicacion=publicacion;
        EventoEditarPublicacion.getInstance().addObserver(this);
        init();
    }
    
    /**
     * ESTE metodo
     */
    private void init(){
        txtTexto.setEditable(false);
        txtTexto.setBackground(new Color(0, 0, 0, 0));
        txtTexto.setOpaque(false);
        if(publicacion.getImagen()!=null){
           this.setImagen();
        }
        setText(publicacion.getTexto());
        refresh();
    }

    public void setText(String text) {
        txtTexto.setText(text);
    }
    
    private void setImagen(){
        lblImagen.setIcon(null);
        ImageIcon image = ConvertirImagen.bytesAImageIcon(publicacion.getImagen());//Metodo de transformar a imagenicon el arreglo
        lblImagen.setIcon(new ImageIcon(image.getImage().getScaledInstance(255, 255, Image.SCALE_DEFAULT)));
    }

    public void hideText() {
        txtTexto.setVisible(false);
    }
    
    private void editarPublicacion(Publicacion publicacion){
        if(!publicacion.equals(this.publicacion))return;
        txtTexto.setText(publicacion.getTexto());
        //Aqui va para cambiar la imagen
        
        refresh();
    }
    
    private void refresh(){
        repaint();
        revalidate();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTexto = new swingComponents.JIMSendTextPane();
        lblImagen = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txtTexto.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txtTexto.setForeground(new java.awt.Color(0, 0, 0));
        txtTexto.setSelectionColor(new java.awt.Color(0, 102, 204));
        add(txtTexto);
        add(lblImagen);
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
    private javax.swing.JLabel lblImagen;
    private swingComponents.JIMSendTextPane txtTexto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateEditarPublicacion(PeticionPublicacion peticion) {
        editarPublicacion(peticion.getPublicacion());
    }
}
