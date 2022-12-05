package guis;

import entidades.Sexo;
import entidades.Usuario;
import helpers.EncriptadorAES;
import interfaces.IFachadaConexion;
import java.awt.Color;
import java.util.Calendar;
import static java.util.Calendar.YEAR;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import logica.FachadaConexion;

/**
 *
 * @author erick
 */
public class FrmRegistrarUsuario extends javax.swing.JFrame {

    IFachadaConexion facConexion;

    /**
     * Creates new form FrmRegistrarUsuario
     */
    public FrmRegistrarUsuario() {
        initComponents();
        facConexion = new FachadaConexion();
        passwordPlaceholder();
    }

    /**
     * Agrega un nuevo usuario al sistema
     */
    private void agregar() {
        EncriptadorAES encriptador = new EncriptadorAES();
        String username = txtUsuario.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());
        String passwordEncriptada = null;
        try {
            passwordEncriptada = encriptador.encriptar(password);
        } catch (Exception ex) {
            Logger.getLogger(FrmRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String correo = txtCorreo.getText().trim();
        String numero = txtNumero.getText().trim();
        int dia = Integer.parseInt(txtDia.getText().trim());
        int mes = Integer.parseInt(txtMes.getText().trim());
        int anio = Integer.parseInt(txtAnio.getText().trim());
        Calendar fecha = new GregorianCalendar(anio, mes - 1, dia);
        Sexo sexo = radHombre.isSelected() ? Sexo.HOMBRE : Sexo.MUJER;

        Usuario usuario = new Usuario(username, passwordEncriptada, correo, numero, fecha, sexo, null);
        facConexion.regitrarUsuario(usuario);
    }
    
    /**
     * Placeholder de la contrase;a
     */
    private void passwordPlaceholder() {
        String password = String.valueOf(txtPassword.getPassword());

        if (password.toLowerCase().equals("password") || password.toLowerCase().equals("")) {
            txtPassword.setText("Contraseña");
            txtPassword.setEchoChar((char) 0);
            txtPassword.setForeground(new Color(153, 153, 153));
        }
    }
    
    /**
     * Valida los espacios vacios
     */
    public boolean validarVacios(){
        if(txtUsuario.getText().isBlank())return false;
        if(String.valueOf(txtPassword.getPassword()).isBlank())return false;
        if(txtCorreo.getText().isBlank())return false;
        if(txtNumero.getText().isBlank())return false;
        if(txtDia.getText().isBlank())return false;
        if(txtMes.getText().isBlank())return false;
        if(txtAnio.getText().isBlank())return false;
        if(!radHombre.isSelected() && !radMujer.isSelected())return false;
        return true;
    }
    
    /**
     * Valida el formato del texto introducido
     */
    public boolean validarFormato(){
        if(!radHombre.isSelected() && !radMujer.isSelected())return false;
        if(isNumeric(txtAnio.getText()) || isNumeric(txtMes.getText()) || isNumeric(txtDia.getText())){
            JOptionPane.showMessageDialog(this, "Los valores de fecha deben ser numeros", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(Integer.parseInt(txtAnio.getText())> Calendar.getInstance().get(YEAR)){
            JOptionPane.showMessageDialog(this, "El anio debe ser menor al anio actual", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(Integer.parseInt(txtAnio.getText())< 1900){
            JOptionPane.showMessageDialog(this, "Anio mayor a 1900", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(Integer.parseInt(txtMes.getText()) > 12 || Integer.parseInt(txtMes.getText()) < 1){
            JOptionPane.showMessageDialog(this, "Elegir un mes entre 1-12", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(Integer.parseInt(txtDia.getText()) > 31 || Integer.parseInt(txtDia.getText()) < 1){
            JOptionPane.showMessageDialog(this, "Elegir un dia entre 1-31", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(Integer.parseInt(txtMes.getText()) == 2 && Integer.parseInt(txtDia.getText()) > 29){
            JOptionPane.showMessageDialog(this, "Febrero tiene 29 dias (si es bisiesto)", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(!validarEmail()){
            JOptionPane.showMessageDialog(this, "Escribir un correo correcto", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(!validarNumero()){
            JOptionPane.showMessageDialog(this, "El numero de telefono debe de ser de 10 digitos", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(!validarUsuario()){
            JOptionPane.showMessageDialog(this, "El usuario no debe llevar espacios", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
            
        return true;
    }
    
    /**
     * Pregunta si la cadena recibida es numerica
     */
    public static boolean isNumeric(String cadena) {
        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = false;
        } catch (NumberFormatException excepcion) {
            resultado = true;
        }
        return resultado;
    }

    /**
     *  Valida el formato del correo
     */
    public boolean validarEmail(){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(txtCorreo.getText());
        return matcher.find();
    }
    
    /**
     *  Valida el formato del numero telefonico
     */
    public boolean validarNumero(){
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(txtNumero.getText());
        return matcher.find();//
    }
    
    /**
     *  Valida el formato del Usuario
     */
    public boolean validarUsuario(){
        Pattern pattern = Pattern.compile("^(\\w)+(\\S)$");
        Matcher matcher = pattern.matcher(txtUsuario.getText());
        return matcher.find();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexoGrupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        radMujer = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        radHombre = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDia = new swingComponents.JIMSendTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtMes = new swingComponents.JIMSendTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtAnio = new swingComponents.JIMSendTextPane();
        btnCrear = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtUsuario = new swingComponents.JIMSendTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCorreo = new swingComponents.JIMSendTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNumero = new swingComponents.JIMSendTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel1.setText("Registrar Usuario");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(117, 119, 122));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(117, 119, 122));
        jLabel2.setText("Fecha de nacimiento:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 223, 226), 1, true));

        radMujer.setBackground(new java.awt.Color(255, 255, 255));
        sexoGrupo.add(radMujer);
        radMujer.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        radMujer.setText("Mujer");
        radMujer.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(radMujer, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(radMujer, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 223, 226), 1, true));

        radHombre.setBackground(new java.awt.Color(255, 255, 255));
        sexoGrupo.add(radHombre);
        radHombre.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        radHombre.setText("Hombre");
        radHombre.setBorder(null);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(radHombre, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(radHombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        jLabel3.setBackground(new java.awt.Color(117, 119, 122));
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(117, 119, 122));
        jLabel3.setText("Sexo:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtDia.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtDia.setAlignmentY(1.5F);
        txtDia.setHintText("15");
        txtDia.setMargin(new java.awt.Insets(2, 10, 2, 6));
        jScrollPane4.setViewportView(txtDia);

        txtMes.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtMes.setAlignmentY(1.5F);
        txtMes.setHintText("10");
        txtMes.setMargin(new java.awt.Insets(2, 10, 2, 6));
        jScrollPane5.setViewportView(txtMes);

        txtAnio.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtAnio.setAlignmentY(1.5F);
        txtAnio.setHintText("2002");
        txtAnio.setMargin(new java.awt.Insets(2, 10, 2, 6));
        jScrollPane6.setViewportView(txtAnio);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnCrear.setBackground(new java.awt.Color(0, 0, 0));
        btnCrear.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Crear cuenta nueva");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtPassword.setMargin(new java.awt.Insets(2, 10, 2, 6));
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFocusLost(evt);
            }
        });

        txtUsuario.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtUsuario.setAlignmentY(1.5F);
        txtUsuario.setHintText("Usuario");
        txtUsuario.setMargin(new java.awt.Insets(2, 10, 2, 6));
        jScrollPane1.setViewportView(txtUsuario);

        txtCorreo.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtCorreo.setAlignmentY(1.5F);
        txtCorreo.setHintText("Correo electrónico");
        txtCorreo.setMargin(new java.awt.Insets(2, 10, 2, 6));
        jScrollPane2.setViewportView(txtCorreo);

        txtNumero.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtNumero.setAlignmentY(1.5F);
        txtNumero.setHintText("Número de celular");
        txtNumero.setMargin(new java.awt.Insets(2, 10, 2, 6));
        jScrollPane3.setViewportView(txtNumero);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnCrear)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        if(validarVacios()){
            if(validarFormato()){
                agregar();
                this.dispose();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Rellenar los campos vacios", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        txtPassword.setEchoChar('*');
        String password = String.valueOf(txtPassword.getPassword());

        if (password.toLowerCase().equals("contraseña")) {
            txtPassword.setText("");
            txtPassword.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtPasswordFocusGained

    private void txtPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusLost
        passwordPlaceholder();
    }//GEN-LAST:event_txtPasswordFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radHombre;
    private javax.swing.JRadioButton radMujer;
    private javax.swing.ButtonGroup sexoGrupo;
    private swingComponents.JIMSendTextPane txtAnio;
    private swingComponents.JIMSendTextPane txtCorreo;
    private swingComponents.JIMSendTextPane txtDia;
    private swingComponents.JIMSendTextPane txtMes;
    private swingComponents.JIMSendTextPane txtNumero;
    private javax.swing.JPasswordField txtPassword;
    private swingComponents.JIMSendTextPane txtUsuario;
    // End of variables declaration//GEN-END:variables
}
