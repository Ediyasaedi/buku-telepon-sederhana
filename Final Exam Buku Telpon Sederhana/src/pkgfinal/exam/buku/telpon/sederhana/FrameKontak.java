/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.exam.buku.telpon.sederhana;
import Tabel.DataKontak;
import Tabel.TabelDataKontak;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author ediyasa
 */
public class FrameKontak extends javax.swing.JFrame {
    private Connection koneksi;
    private Statement script;
    private Tabel.TabelDataKontak ModelDataKontak;
    
    private void KoneksiDb( ){ try{
    //koneksi ke database
    Class.forName("com.mysql.jdbc.Driver");
    koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kontak_db", "root","");
    script = koneksi.createStatement( );
    }
    catch(SQLException ex){
    System.err.print(ex);
    }
    catch(ClassNotFoundException ex){
    System.err.print(ex);
    }
    }

    private void tampil( ){
    try{
    int baris = TblKontak.getRowCount( );
    for(int i=0; i<baris; i++){
    ModelDataKontak.delete(0, baris);
    }
    String sql = "select * from tb_kontak";
    ResultSet rs = script.executeQuery(sql);

    while (rs.next( )){
    DataKontak kontak = new DataKontak( );

    kontak.setNama(rs.getString(1));
    kontak.setTelepon(rs.getString(2));
    kontak.setE_mail(rs.getString(3));

    ModelDataKontak.add(kontak);
    }
    }
    catch(SQLException ex){
    System.err.print(ex);
    }
    }
    //data yang di input dari program akan di simpan database
    private void bersih( ){
    TxtNama.setText(null);
    TxtTelepon.setText(null);
    TxtEmail.setText(null);
    TxtCari.setText(null);
    BtnSimpan.setEnabled(true);
    BtnUbah.setEnabled(false);
    BtnHapus.setEnabled(false);
    }

    private void tabel( ){
    TblKontak.getSelectionModel( ).addListSelectionListener(new ListSelectionListener( ) {

    @Override
    public void valueChanged(ListSelectionEvent e) {
    int baris = TblKontak.getSelectedRow( );

    if(baris != -1){
    DataKontak kontak = ModelDataKontak.get(baris);
    TxtNama.setText(kontak.getNama( ));
    TxtTelepon.setText(kontak.getTelepon( ));
    TxtEmail.setText(kontak.getE_mail( ));
    }
    }
    });
    BtnSimpan.setEnabled(false);
    BtnUbah.setEnabled(true);
    BtnHapus.setEnabled(true);
    }
    //UNTUK FUNGSI TOMBOL SIMPAN()
    private void simpan( ){
    try{
    String sql = "insert into tb_kontak values ("
    +"'"+TxtNama.getText( )+"',"
    +"'"+TxtTelepon.getText( )+"',"
    +"'"+TxtEmail.getText( )+"'"
    +")";

    script.executeUpdate(sql);

    tampil( );
    JOptionPane.showMessageDialog(null, TxtNama.getText( )+" berhasil Disimpan");
    bersih( );
    }
    catch(SQLException ex){

    JOptionPane.showMessageDialog(this, "No Telepon sudah Ada");
    bersih();

    }
    }
    //UNTUK FUNGSI TOMBOL UBAH ()
    private void ubah( ){
    int app;

    if((app = JOptionPane.showConfirmDialog(null, "Ubah kontak"
    +" ?","Perhatian",JOptionPane.YES_NO_OPTION))==0){
    try{
    String sql = "update tb_kontak set"
    +" nama= '"+TxtNama.getText()+"',"
    +" email = '"+TxtEmail.getText()+"' where"
    +" telepon = '"+TxtTelepon.getText()+"'";

    script.executeUpdate(sql);

    tampil( );
    JOptionPane.showMessageDialog(null, "Kontak berhasil dirubah");
    bersih( );
    }
    catch(SQLException ex){
    System.err.print(ex);
    }
    }
    }
    //UNTUK FUNGSI TOMBOL HAPUS ()
    private void hapus( ){
    int app, bantu;

    if((app = JOptionPane.showConfirmDialog(null, "Hapus data"
    +" ?","Perhatian",JOptionPane.YES_NO_OPTION))==0){
    try{
    String sql = "delete from tb_kontak where"
    +" nama = '"+TxtNama.getText()+"'";

    bantu = script.executeUpdate(sql);

    tampil( );
    JOptionPane.showMessageDialog(null, "Kontak berhasil dihapus");
    bersih( );
    }
    catch(SQLException ex){
    System.err.print(ex);
    }
    }
    }
     //UNTUK FUNGSI CARI 
    private void cari( ){
    int baris = TblKontak.getRowCount( );
    String bantu = CmbCari.getSelectedItem( ).toString( );

    for(int i=0; i<baris; i++){
    ModelDataKontak.delete(i, baris);
    }

    try{
    String sql = "select * from tb_kontak where "
    +bantu+" like '%"+TxtCari.getText()+"%'";

    ResultSet rs = script.executeQuery(sql);

    while(rs.next( )){
    DataKontak kontak = new DataKontak( );

    kontak.setNama(rs.getString(1));
    kontak.setTelepon(rs.getString(2));
    kontak.setE_mail(rs.getString(3));

    ModelDataKontak.add(kontak);
    }
    }
    catch(SQLException ex){
    System.err.print(ex);
    }
    }

    /**
     * Creates new form FrameKontak
     */
    public FrameKontak() {
        initComponents();
        ModelDataKontak = new TabelDataKontak();
        TblKontak.setModel(ModelDataKontak);
        KoneksiDb( );
        tampil( );
        bersih( );
        }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxtNama = new javax.swing.JTextField();
        TxtTelepon = new javax.swing.JTextField();
        TxtEmail = new javax.swing.JTextField();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        BtnSimpan = new javax.swing.JButton();
        BtnUbah = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblKontak = new javax.swing.JTable();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel5 = new javax.swing.JLabel();
        CmbCari = new javax.swing.JComboBox();
        TxtCari = new javax.swing.JTextField();
        BtnCari = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kontak"));

        jLabel2.setText("Nama");

        jLabel3.setText("Telepon");

        jLabel4.setText("E-mail");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtNama)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(TxtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(TxtNama, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(TxtTelepon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(TxtEmail, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tombol"));

        BtnSimpan.setText("Simpan");
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });

        BtnUbah.setText("Ubah");
        BtnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUbahActionPerformed(evt);
            }
        });

        BtnHapus.setText("Hapus");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(BtnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnSimpan)
                .addGap(18, 18, 18)
                .addComponent(BtnUbah)
                .addGap(18, 18, 18)
                .addComponent(BtnHapus)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jLayeredPane2.setLayer(BtnSimpan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(BtnUbah, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(BtnHapus, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar Kontak"));

        TblKontak.setModel(new javax.swing.table.DefaultTableModel(
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
        TblKontak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblKontakMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(TblKontak);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );
        jLayeredPane3.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Cari Kontak"));

        jLabel5.setText("Cari Berdasarkan");

        CmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nama", "Telepon", "E_mail" }));

        TxtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCariKeyReleased(evt);
            }
        });

        BtnCari.setText("Cari");

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(CmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(BtnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCari))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jLayeredPane4.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(CmbCari, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(TxtCari, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(BtnCari, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Buku Telepon Sederhana");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane4))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(191, 191, 191))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1)
                    .addComponent(jLayeredPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // TODO add your handling code here
        simpan();
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUbahActionPerformed
        // TODO add your handling code here:
        ubah();
    }//GEN-LAST:event_BtnUbahActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void TblKontakMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblKontakMousePressed
        // TODO add your handling code here:
        tabel();
    }//GEN-LAST:event_TblKontakMousePressed

    private void TxtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCariKeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_TxtCariKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameKontak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameKontak().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCari;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnSimpan;
    private javax.swing.JButton BtnUbah;
    private javax.swing.JComboBox CmbCari;
    private javax.swing.JTable TblKontak;
    private javax.swing.JTextField TxtCari;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtNama;
    private javax.swing.JTextField TxtTelepon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}