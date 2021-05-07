/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animatromica;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.digest.DigestUtils;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.swing.ImageIcon;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Principal2 extends javax.swing.JFrame {
Login login = new Login();
String netiD=Login.netID;
    /**
     * Creates new form Principal2
     */
    public Principal2() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Animatromica -> Principal (Administrador) NetID-> "+this.netiD);
        java.awt.Image icon = new ImageIcon(getClass().getResource("icono.png")).getImage();
        setIconImage(icon);
    }
    public void registrarUsuario() throws SQLException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("Animatromica");
        ds.setUser("Alan99");
        ds.setPassword("15192252");
        ds.setPortNumber(1433);
        if (cmbEstado.getSelectedItem().equals("Activo")) {
            try {
                Connection cn = ds.getConnection();
                PreparedStatement ps = cn.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?,?,?,?,1)");
                ps.setString(1, this.txtNetID.getText().toUpperCase());
                ps.setString(2, this.cmbTipo.getSelectedItem().toString());
                ps.setString(3, this.txtNombre.getText());
                ps.setString(4, this.txtPaterno.getText());
                ps.setString(5, this.txtMaterno.getText());
                ps.setString(6, this.txtTelefono.getText());
                ps.setString(7, this.txtMovil.getText());
                ps.setString(8, this.txtEmail.getText());
                ps.setString(9, DigestUtils.md5Hex(this.txtPass.getText().toUpperCase()));
                ps.setInt(10, Integer.parseInt(this.txtEdad.getText()));
                ps.setString(11, this.txtFNacimiento.getText());
                ps.setString(12, this.txtFIngreso.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente", "Correcto", 1);
            } catch (SQLServerException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Hubo un error al Registrar el Usuario, o el Usuario Ya Existe", "Error", 0);
            }
        } else if (cmbEstado.getSelectedItem().equals("Inactivo")) {
            try {
                Connection cn = ds.getConnection();
                PreparedStatement ps = cn.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)");
                ps.setString(1, this.txtNetID.getText().toUpperCase());
                ps.setString(2, this.cmbTipo.getSelectedItem().toString());
                ps.setString(3, this.txtNombre.getText());
                ps.setString(4, this.txtPaterno.getText());
                ps.setString(5, this.txtMaterno.getText());
                ps.setString(6, this.txtTelefono.getText());
                ps.setString(7, this.txtMovil.getText());
                ps.setString(8, this.txtEmail.getText());
                ps.setString(9, DigestUtils.md5Hex(this.txtPass.getText().toUpperCase()));
                ps.setInt(10, Integer.parseInt(this.txtEdad.getText()));
                ps.setString(11, this.txtFNacimiento.getText());
                ps.setString(12, this.txtFIngreso.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente", "Correcto", 1);
            } catch (SQLServerException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Hubo un error al Registrar el Usuario, o el Usuario Ya Existe", "Error", 0);
            }
        }
    }
    public void ModificarUsuario() throws SQLException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("Animatromica");
        ds.setUser("Alan99");
        ds.setPassword("15192252");
        ds.setPortNumber(1433);
        if (cmbEstado.getSelectedItem().equals("Activo")) {
            try {
                Connection cn = ds.getConnection();
                PreparedStatement ps = cn.prepareStatement("UPDATE Users SET Tipo = ?, Nombre = ?, "
                        + "ApellidoPaterno = ?, ApellidoMaterno = ?, Telefono = ?, Movil = ?, "
                        + "Email = ?, Pass = ?, Edad = ?, FechaNacimiento = ?, FechaIngreso = ?, Estado = 1 WHERE NetID = ?");
                ps.setString(1, this.cmbTipo.getSelectedItem().toString());
                ps.setString(2, this.txtNombre.getText());
                ps.setString(3, this.txtPaterno.getText());
                ps.setString(4, this.txtMaterno.getText());
                ps.setString(5, this.txtTelefono.getText());
                ps.setString(6, this.txtMovil.getText());
                ps.setString(7, this.txtEmail.getText());
                ps.setString(8, DigestUtils.md5Hex(this.txtPass.getText().toUpperCase()));
                ps.setInt(9, Integer.parseInt(this.txtEdad.getText()));
                ps.setString(10, this.txtFNacimiento.getText());
                ps.setString(11, this.txtFIngreso.getText());
                ps.setString(12,this.txtNetID.getText().toUpperCase());
                ps.executeUpdate();
                
            } catch (SQLServerException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       
            }
        } else if (cmbEstado.getSelectedItem().equals("Inactivo")) {
            try {
                Connection cn = ds.getConnection();
                PreparedStatement ps = cn.prepareStatement("UPDATE Users SET Tipo = ?, Nombre = ?, "
                        + "ApellidoPaterno = ?, ApellidoMaterno = ?, Telefono = ?, Movil = ?, "
                        + "Email = ?, Pass = ?, Edad = ?, FechaNacimiento = ?, FechaIngreso = ?, Estado = 0 WHERE NetID = ?");
                ps.setString(1, this.cmbTipo.getSelectedItem().toString());
                ps.setString(2, this.txtNombre.getText());
                ps.setString(3, this.txtPaterno.getText());
                ps.setString(4, this.txtMaterno.getText());
                ps.setString(5, this.txtTelefono.getText());
                ps.setString(6, this.txtMovil.getText());
                ps.setString(7, this.txtEmail.getText());
                ps.setString(8, DigestUtils.md5Hex(this.txtPass.getText().toUpperCase()));
                ps.setInt(9, Integer.parseInt(this.txtEdad.getText()));
                ps.setString(10, this.txtFNacimiento.getText());
                ps.setString(11, this.txtFIngreso.getText());
                ps.setString(12,this.txtNetID.getText().toUpperCase());
                ps.executeUpdate();
            } catch (SQLServerException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  public ResultSet BuscarUsuario(String NetID){
        Connection cn;
        PreparedStatement stm;
        ResultSet rs = null;
        try{
        cn = Conexion.getConexion();
        String SQL = "SELECT * FROM Users WHERE NetID=?";
        stm = cn.prepareStatement(SQL);
        stm.setString(WIDTH, NetID);
        rs = stm.executeQuery();
        }catch(Exception e){        
        }
        return rs;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        Tabulador = new javax.swing.JTabbedPane();
        DatosFrame = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblNet = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnCerrarSesion = new javax.swing.JMenuItem();
        btnVer = new javax.swing.JMenuItem();
        UsuariosFrame = new javax.swing.JInternalFrame();
        txtNetID = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPaterno = new javax.swing.JTextField();
        txtMaterno = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtMovil = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtFNacimiento = new javax.swing.JTextField();
        txtFIngreso = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();
        btnCodigo = new javax.swing.JButton();
        cmbEstado = new javax.swing.JComboBox<>();
        Imagen = new javax.swing.JLabel();
        btnVerQR = new javax.swing.JButton();
        lblNetID = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblPaterno = new javax.swing.JLabel();
        lblMaterno = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblTelefono = new javax.swing.JLabel();
        lblMovil = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblFechaIngreso = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        btnFechaIngreso = new javax.swing.JButton();
        btnEmail = new javax.swing.JButton();
        btnVerPDF = new javax.swing.JButton();
        MostrarFrame = new javax.swing.JInternalFrame();
        InactivosFrame = new javax.swing.JInternalFrame();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Animatromica -> Principal (Administrador)");
        setIconImage(getIconImage());

        Background.setBackground(new java.awt.Color(255, 255, 255));

        Tabulador.setBackground(new java.awt.Color(255, 255, 255));
        Tabulador.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        Tabulador.setToolTipText("aa");

        DatosFrame.setBackground(new java.awt.Color(255, 255, 255));
        DatosFrame.setBorder(null);
        DatosFrame.setTitle("Mis Datos");
        DatosFrame.setVisible(true);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Datos"), "Datos")), "Tus Datos"));

        lblNet.setText("NetID:");

        lblName.setText("Tipo:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Paterno:");

        jLabel5.setText("Materno:");

        jLabel6.setText("Telefono:");

        jLabel7.setText("Movil:");

        jLabel8.setText("Email:");

        jLabel9.setText("Pass:");

        jLabel10.setText("Edad:");

        jLabel11.setText("Fecha Nacimiento:");

        jLabel12.setText("Fecha Ingreso:");

        jLabel13.setText("Estado:");

        jTextField1.setEditable(false);

        jTextField2.setEditable(false);

        jTextField3.setEditable(false);

        jTextField4.setEditable(false);

        jTextField5.setEditable(false);

        jTextField6.setEditable(false);

        jTextField7.setEditable(false);

        jTextField8.setEditable(false);

        jTextField9.setEditable(false);

        jTextField10.setEditable(false);

        jTextField11.setEditable(false);

        jTextField12.setEditable(false);

        jTextField13.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(lblName)
                    .addComponent(lblNet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jTextField8)
                    .addComponent(jTextField9)
                    .addComponent(jTextField10)
                    .addComponent(jTextField12)
                    .addComponent(jTextField13)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNet)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("Opciones");

        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(btnCerrarSesion);

        btnVer.setText("Ver Datos");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        jMenu1.add(btnVer);

        jMenuBar1.add(jMenu1);

        DatosFrame.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout DatosFrameLayout = new javax.swing.GroupLayout(DatosFrame.getContentPane());
        DatosFrame.getContentPane().setLayout(DatosFrameLayout);
        DatosFrameLayout.setHorizontalGroup(
            DatosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DatosFrameLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DatosFrameLayout.setVerticalGroup(
            DatosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DatosFrameLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addContainerGap())
        );

        Tabulador.addTab("Mis Datos", DatosFrame);
        try {
            DatosFrame.setIcon(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        UsuariosFrame.setBackground(new java.awt.Color(255, 255, 255));
        UsuariosFrame.setBorder(null);
        UsuariosFrame.setTitle("Control de Usuario");
        UsuariosFrame.setToolTipText("");
        UsuariosFrame.setVisible(true);

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Administrador" }));

        btnRegistrar.setText("Registrar Usuario");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnGenerar.setText("Generar PDF");
        btnGenerar.setEnabled(false);
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnCodigo.setText("Generar Código QR");
        btnCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodigoActionPerformed(evt);
            }
        });

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        Imagen.setBackground(new java.awt.Color(102, 255, 255));

        btnVerQR.setText("Ver QR");
        btnVerQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerQRActionPerformed(evt);
            }
        });

        lblNetID.setText("NetID:");

        lblTipo.setText("Tipo:");

        lblNombre.setText("Nombre:");

        lblPaterno.setText("Apellido Paterno:");

        lblMaterno.setText("Apellido Materno:");

        btnActualizar.setText("Actualizar Usuario");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar Usuario");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblTelefono.setText("Telefono:");

        lblMovil.setText("Movil:");

        lblEmail.setText("Email:");

        lblPass.setText("Pass:");

        lblEdad.setText("Edad:");

        lblFechaNacimiento.setText("Fecha Nacimiento:");

        lblFechaIngreso.setText("Fecha Ingreso:");

        lblEstado.setText("Estado:");

        btnFechaIngreso.setText("Generar Fecha ");
        btnFechaIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaIngresoActionPerformed(evt);
            }
        });

        btnEmail.setText("Enviar PDF por Email");
        btnEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailActionPerformed(evt);
            }
        });

        btnVerPDF.setText("Ver PDF");
        btnVerPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UsuariosFrameLayout = new javax.swing.GroupLayout(UsuariosFrame.getContentPane());
        UsuariosFrame.getContentPane().setLayout(UsuariosFrameLayout);
        UsuariosFrameLayout.setHorizontalGroup(
            UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                        .addComponent(btnFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(lblFechaIngreso)
                        .addGap(18, 18, 18)
                        .addComponent(txtFIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblEdad)
                                .addGap(18, 18, 18)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblTipo)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblPaterno)
                                .addGap(18, 18, 18)
                                .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblNetID)
                                .addGap(18, 18, 18)
                                .addComponent(txtNetID, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblMovil)
                                .addGap(18, 18, 18)
                                .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblPass)
                                .addGap(18, 18, 18)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                    .addComponent(lblEmail)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                    .addComponent(lblTelefono)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(UsuariosFrameLayout.createSequentialGroup()
                                    .addComponent(lblMaterno)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblEstado)
                                .addGap(18, 18, 18)
                                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                                .addComponent(lblFechaNacimiento)
                                .addGap(18, 18, 18)
                                .addComponent(txtFNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UsuariosFrameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UsuariosFrameLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVerQR, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVerPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        UsuariosFrameLayout.setVerticalGroup(
            UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsuariosFrameLayout.createSequentialGroup()
                .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNetID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNetID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(UsuariosFrameLayout.createSequentialGroup()
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipo))
                        .addGap(18, 18, 18)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPaterno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaterno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelefono))
                        .addGap(18, 18, 18)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovil))
                        .addGap(21, 21, 21)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPass))
                        .addGap(18, 18, 18)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEdad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaIngreso)
                            .addComponent(btnFechaIngreso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(UsuariosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstado))
                        .addGap(42, 42, 42)
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEmail)
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(UsuariosFrameLayout.createSequentialGroup()
                        .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerQR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenerar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerPDF)
                        .addGap(47, 47, 47))))
        );

        Tabulador.addTab("Control de Usuario", UsuariosFrame);

        MostrarFrame.setBorder(null);
        MostrarFrame.setTitle("Mostrar Usuarios");
        MostrarFrame.setVisible(true);

        javax.swing.GroupLayout MostrarFrameLayout = new javax.swing.GroupLayout(MostrarFrame.getContentPane());
        MostrarFrame.getContentPane().setLayout(MostrarFrameLayout);
        MostrarFrameLayout.setHorizontalGroup(
            MostrarFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        MostrarFrameLayout.setVerticalGroup(
            MostrarFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Tabulador.addTab("Mostrar Usuarios", MostrarFrame);

        InactivosFrame.setVisible(true);

        javax.swing.GroupLayout InactivosFrameLayout = new javax.swing.GroupLayout(InactivosFrame.getContentPane());
        InactivosFrame.getContentPane().setLayout(InactivosFrameLayout);
        InactivosFrameLayout.setHorizontalGroup(
            InactivosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        InactivosFrameLayout.setVerticalGroup(
            InactivosFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Tabulador.addTab("Usuarios Inactivos", InactivosFrame);

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tabulador)
                .addContainerGap())
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(Tabulador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerQRActionPerformed
        // TODO add your handling code here:
        try{
            URL url = getClass().getResource("/QR/QR_"+this.txtNetID.getText().toUpperCase()+".png");
        this.Imagen.setIcon(new ImageIcon(url));
        this.btnGenerar.setEnabled(true);
        JOptionPane.showMessageDialog(this,"QR Encontrado","Correcto",1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"QR no encontrado","Error",0);
        }
    }//GEN-LAST:event_btnVerQRActionPerformed

    private void btnCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodigoActionPerformed
        // TODO add your handling code here:
        this.btnVerQR.setEnabled(false);
        this.btnGenerar.setEnabled(false);
        int qrTamAncho = 400;
        int qrTamAlto = 400;
        String formato = "png";
        String ruta = "src/QR/QR_"+this.txtNetID.getText().toUpperCase()+".png";
        String data = this.txtNetID.getText().toUpperCase();
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(data, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }
        BufferedImage imagen = new BufferedImage(qrTamAncho,
            qrTamAlto, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < qrTamAlto; y++) {
            for (int x = 0; x < qrTamAncho; x++) {
                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
            }
        }
        try{
            FileOutputStream qrCode = new FileOutputStream(ruta);
            ImageIO.write(imagen, formato, qrCode);
            
            System.out.println("Listo!");
            qrCode.close();
            JOptionPane.showMessageDialog(null,"Código generado correctamente","Correcto",JOptionPane.INFORMATION_MESSAGE);
            btnVerQR.setEnabled(true);
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_btnCodigoActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
    try {
        // TODO add your handling code here:
        this.crearPDF("src/PDF/PDF_"+this.txtNetID.getText().toUpperCase()+".pdf");
        JOptionPane.showMessageDialog(this,"PDF Generado Correctamente","Correcto",1);
        this.btnEmail.setEnabled(true);
    } catch (IOException ex) {
        Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(this,"PDF no generado","Error",0);
    }
    }//GEN-LAST:event_btnGenerarActionPerformed

    public void crearPDF(String dest)throws IOException{
    PdfWriter writer = new PdfWriter(dest);
    PdfDocument pdf = new PdfDocument(writer);
    try (Document document = new Document(pdf)) {
        URL url = getClass().getResource("/QR/QR_"+this.txtNetID.getText().toUpperCase()+".png");
        Image img = new Image(ImageDataFactory.create(url));
        document.add(new Paragraph("Animatromica"));
        document.add(new Paragraph("QR NetID : "));
        Paragraph p;
        p = new Paragraph("").add(img);
        document.add(p);
        document.add(new Paragraph("NetID: "+this.txtNetID.getText().toUpperCase()));
        document.add(new Paragraph("Contraseña: "+this.txtPass.getText()));
        document.add(new Paragraph("Nombre: "+this.txtNombre.getText()+" "
                +this.txtPaterno.getText()+" "+this.txtMaterno.getText()));
        document.add(new Paragraph("Tipo de Acceso: "+this.cmbTipo.getSelectedItem().toString()));
        document.add(new Paragraph("Telefono: "+this.txtTelefono.getText()));
        document.add(new Paragraph("Movil: "+this.txtMovil.getText()));
        document.add(new Paragraph("Edad: "+this.txtEdad.getText()));
        document.add(new Paragraph("Fecha de Nacimiento: "+this.txtFNacimiento.getText()));
        document.add(new Paragraph("Fecha de Ingreso: "+this.txtFIngreso.getText()));
        document.close();
    }
}
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            // TODO add your handling code here:
            this.registrarUsuario();

        } catch (SQLException ex) {
            Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.btnGenerar.setEnabled(false);
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
        String NetID = Login.netID;
        Principal2 Conexion = new Principal2();
        ResultSet rs = Conexion.BuscarUsuario(NetID);
        DefaultTableModel registros = new DefaultTableModel();
        this.jTable1.setModel(registros);
        registros.setColumnIdentifiers(new Object[]{
            "NetID",
            "Tipo",
            "Nombre",
            "ApellidoPaterno",
            "ApellidoMaterno",
            "Telefono",
            "Movil",
            "Email",
            "Pass",
            "Edad",
            "FechaNacimiento",
            "FechaIngreso",
            "Estado"});
    try{
        while(rs.next()){
            registros.addRow(new Object[]{
                rs.getString("NetID"),
                rs.getString("Tipo"),
                rs.getString("Nombre"),
                rs.getString("ApellidoPaterno"),
                rs.getString("ApellidoMaterno"),
                rs.getString("Telefono"),
                rs.getString("Movil"),
                rs.getString("Email"),
                rs.getString("Pass"),
                rs.getString("Edad"),
                rs.getString("FechaNacimiento"),
                rs.getString("FechaIngreso"),
                rs.getString("Estado")
            });
                this.jTextField1.setText(rs.getString("NetID"));
                this.jTextField2.setText(rs.getString("Tipo"));
                this.jTextField3.setText(rs.getString("Nombre"));
                this.jTextField4.setText(rs.getString("ApellidoPaterno"));
                this.jTextField5.setText(rs.getString("ApellidoMaterno"));
                this.jTextField6.setText(rs.getString("Telefono"));
                this.jTextField7.setText(rs.getString("Movil"));
                this.jTextField8.setText(rs.getString("Email"));
                this.jTextField9.setText(rs.getString("Pass"));
                this.jTextField10.setText(rs.getString("Edad"));
                this.jTextField11.setText(rs.getString("FechaNacimiento"));
                this.jTextField12.setText(rs.getString("FechaIngreso"));
                this.jTextField13.setText(rs.getString("Estado"));
                
        }
             
        }catch(SQLException e){
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    try {
        // TODO add your handling code here:
        this.ModificarUsuario();
        JOptionPane.showMessageDialog(null,"Usuario Modificado Correctamente","Correcto",1);
    } catch (SQLException ex) {
        Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null,"Error Al Modificar Usuario","Error",0);
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    try {
        // TODO add your handling code here:
        this.EliminarUsuario();
        JOptionPane.showMessageDialog(null,"Usuario Eliminado Correctamente","correcto",JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"No se puedo eliminar el usuario","Error",JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnFechaIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaIngresoActionPerformed
        // TODO add your handling code here:
        Date objDate = new Date();
        String srtFormat="YYYY/MM/dd hh:mm:ss a";
        SimpleDateFormat objSDF = new SimpleDateFormat(srtFormat);
        this.txtFIngreso.setText(objSDF.format(objDate));
    }//GEN-LAST:event_btnFechaIngresoActionPerformed
 public void EliminarUsuario() throws SQLException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("Animatromica");
        ds.setUser("Alan99");
        ds.setPassword("15192252");
        ds.setPortNumber(1433);
            try {
                Connection cn = ds.getConnection();
                PreparedStatement ps = cn.prepareStatement("DELETE FROM Users WHERE NetID = ?");
                ps.setString(1, this.txtNetID.getText().toUpperCase());
                ps.executeUpdate();
                
            } catch (SQLServerException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       
            }
    }
    public void sendMail(){
  Properties props = new Properties();
props.put("mail.smtp.host", "smtp.gmail.com");
props.setProperty("mail.smtp.starttls.enable", "true");
props.setProperty("mail.smtp.port","587");
props.setProperty("mail.smtp.user", "animatromicanetworks@gmail.com");
props.setProperty("mail.smtp.auth", "true");
Session session = Session.getDefaultInstance(props, null);
session.setDebug(true);
  try{
BodyPart texto = new MimeBodyPart();
texto.setText("Datos de Usuario");
BodyPart adjunto = new MimeBodyPart();
adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\aland\\Documents\\ANIMATROMICA\\Animatromica\\src\\PDF/PDF_"+this.txtNetID.getText().toUpperCase()+".pdf")));
adjunto.setFileName("PDF_"+this.txtNetID.getText().toUpperCase()+".pdf");
MimeMultipart multiParte = new MimeMultipart();
multiParte.addBodyPart(texto);
multiParte.addBodyPart(adjunto);
MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress("animatromicanetworks@gmail.com"));
message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.txtEmail.getText()));
message.setSubject("PDF NETID");
message.setContent(multiParte);
Transport t = session.getTransport("smtp");
t.connect("animatromicanetworks@gmail.com","Choko.Pinky#99");
t.sendMessage(message,message.getAllRecipients());
t.close();
   JOptionPane.showMessageDialog(null,"Correo enviado Correctamente","correcto",JOptionPane.INFORMATION_MESSAGE);
  }catch(Exception ex){
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null,"Correo no enviado","error",JOptionPane.ERROR_MESSAGE);
  }
}
public void abrirarchivo(String archivo){

     try {

            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);
        
     }catch (IOException ex) {
            System.out.println(ex);

     }

}  
    private void btnEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailActionPerformed
        // TODO add your handling code here:
        this.sendMail();
    }//GEN-LAST:event_btnEmailActionPerformed

    private void btnVerPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPDFActionPerformed
        // TODO add your handling code here:
        String url = "C:/Users/aland/Documents/ANIMATROMICA/Animatromica/src/PDF/PDF_"+this.txtNetID.getText()+".pdf";
        try{
            this.abrirarchivo(url);
            JOptionPane.showMessageDialog(null,"PDF Encontrado Correctamente","Correcto",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"PDF no Encontrado","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerPDFActionPerformed

//    @Override
//    public Image getIconImage() {
//        Image retValue = Toolkit.getDefaultToolkit().
//                getImage(ClassLoader.getSystemResource("animatromica/icono.png"));
//
//
//        return retValue;
//    }
    /**
////     * @param args the command line arguments
////     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Principal2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Principal2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Principal2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Principal2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Principal2().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JInternalFrame DatosFrame;
    private javax.swing.JLabel Imagen;
    private javax.swing.JInternalFrame InactivosFrame;
    private javax.swing.JInternalFrame MostrarFrame;
    private javax.swing.JTabbedPane Tabulador;
    private javax.swing.JInternalFrame UsuariosFrame;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JMenuItem btnCerrarSesion;
    private javax.swing.JButton btnCodigo;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEmail;
    private javax.swing.JButton btnFechaIngreso;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JMenuItem btnVer;
    private javax.swing.JButton btnVerPDF;
    private javax.swing.JButton btnVerQR;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaIngreso;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblMaterno;
    private javax.swing.JLabel lblMovil;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNet;
    private javax.swing.JLabel lblNetID;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPaterno;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFIngreso;
    private javax.swing.JTextField txtFNacimiento;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtMovil;
    private javax.swing.JTextField txtNetID;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPaterno;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
