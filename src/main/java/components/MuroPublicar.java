package components;

import entidades.Usuario;
import guis.FrmCrearPublicacion;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MuroPublicar extends javax.swing.JPanel {

    private Usuario usuario;

    /**
     * Creates new form MuroPublicar
     */
    public MuroPublicar(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        btnPublicar.setText("¿Qué estás pensando, " + usuario.getUsuario() + "?");
        setImgUser();
    }

    private void setImgUser() {
        ImageIcon image = new ImageIcon("src/main/java/images/perfil.png");
        imgUser.setIcon(new ImageIcon(image.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPublicar = new javax.swing.JButton();
        imgUser = new javax.swing.JLabel();

        setBackground(null);
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(190, 193, 199), 1, true));
        setForeground(null);

        btnPublicar.setBackground(new java.awt.Color(189, 191, 200));
        btnPublicar.setForeground(new java.awt.Color(99, 100, 104));
        btnPublicar.setText("¿Qué estás pensando, Jorge?");
        btnPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicarActionPerformed(evt);
            }
        });

        imgUser.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(imgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPublicar, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPublicar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPublicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicarActionPerformed
        new FrmCrearPublicacion(usuario).setVisible(true);
    }//GEN-LAST:event_btnPublicarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPublicar;
    private javax.swing.JLabel imgUser;
    // End of variables declaration//GEN-END:variables
}