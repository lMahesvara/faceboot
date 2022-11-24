package components;

import entidades.Publicacion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;

public class PublicacionTitle extends javax.swing.JPanel {
    private Publicacion publicacion;
    
    public PublicacionTitle(Publicacion publicacion) {
        initComponents();
        this.publicacion = publicacion;
        init();
        
        
        repaint();
        revalidate();
    }
    
    private void init(){
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        lblNombre.setText(publicacion.getUsuario().getUsuario());
        
        setImgUser();
        setTime();
        
    }
    
    private void setImgUser(){
        imgUser.setIcon(null);
        ImageIcon image = new ImageIcon("src/main/java/images/perfil.png");
        imgUser.setIcon(new ImageIcon(image.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
    }
    
    private void setTime(){
        lblTime.setText(formatearHora(publicacion.getFecha().getTime()));
    }
    
    private String formatearHora(Date time){
        SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");
        return formateo.format(time);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lblNombre = new javax.swing.JLabel();
        line1 = new swingComponents.Line();
        imgUser = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();

        layer.setLayout(new java.awt.GridLayout(0, 1));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Nombre");

        line1.setForeground(new java.awt.Color(204, 204, 204));

        imgUser.setPreferredSize(new java.awt.Dimension(50, 50));

        lblTime.setBackground(new java.awt.Color(99, 100, 104));
        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblTime.setForeground(new java.awt.Color(99, 100, 104));
        lblTime.setText("22/22/2222");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(462, Short.MAX_VALUE))
            .addComponent(line1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblTime))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(line1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(layer))
        );
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
    private javax.swing.JLabel imgUser;
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTime;
    private swingComponents.Line line1;
    // End of variables declaration//GEN-END:variables
}
