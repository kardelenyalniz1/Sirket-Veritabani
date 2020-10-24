package veritabaniprojesi;

public class GirisEkrani extends javax.swing.JFrame {
    Islemler islemler = new Islemler();

    public GirisEkrani() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        giris = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        urunEkleButon = new javax.swing.JButton();
        urunOlusturButonu = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        giris.setText("Sipariş Ver");
        giris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                girisActionPerformed(evt);
            }
        });

        jButton1.setText("Tedarikçi Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        urunEkleButon.setText("Yeni Ürün Ekle");
        urunEkleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urunEkleButonActionPerformed(evt);
            }
        });

        urunOlusturButonu.setText("Ürün Oluştur");
        urunOlusturButonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urunOlusturButonuActionPerformed(evt);
            }
        });

        jButton2.setText("Müşteri Ekle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(giris, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(urunEkleButon, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(urunOlusturButonu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(giris)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(urunEkleButon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(urunOlusturButonu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void girisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_girisActionPerformed
        urunSipariş uruSipariş = new urunSipariş();
        setVisible(false);
        uruSipariş.setVisible(true);
    }//GEN-LAST:event_girisActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        tedarikciEkle tedarikci = new tedarikciEkle();
        setVisible(false);
        tedarikci.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void urunEkleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urunEkleButonActionPerformed
        yeniUrunEkle yeEkle = new yeniUrunEkle();
        setVisible(false);
        yeEkle.setVisible(true);
    }//GEN-LAST:event_urunEkleButonActionPerformed

    private void urunOlusturButonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urunOlusturButonuActionPerformed
        urunOlustur uruOlustur = new urunOlustur();
        setVisible(false);
        uruOlustur.setVisible(true);
    }//GEN-LAST:event_urunOlusturButonuActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MusteriEkle musteriEkle = new MusteriEkle();
        setVisible(false);
        musteriEkle.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GirisEkrani().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton giris;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton urunEkleButon;
    private javax.swing.JButton urunOlusturButonu;
    // End of variables declaration//GEN-END:variables
}
