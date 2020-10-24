
package veritabaniprojesi;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class MusteriEkle extends javax.swing.JFrame {
 Islemler islemler=new Islemler();
  DefaultTableModel model;
    
    public MusteriEkle() {
         initComponents();
       model = (DefaultTableModel) MusteriTablo.getModel();

    }
    public void MusteriGörüntüle(){
        model.setRowCount(0);
        ArrayList<Musteri>musteri=new ArrayList<Musteri>();
        musteri=islemler.musteriGetir();
        if(musteri!=null){
           for(Musteri musterik:musteri) {
               Object[]eklenecek={musterik.getMusteriId(),musterik.getMusteriAdı(),musterik.getMusteriSoyadı(),musterik.getAdresbilgileri(),musterik.getTalepurun()};
           model.addRow(eklenecek);           
        }
          
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        musteriadı = new javax.swing.JTextField();
        musterisoyadı = new javax.swing.JTextField();
        Adres = new javax.swing.JTextField();
        TalepUrun = new javax.swing.JTextField();
        ekleButon = new javax.swing.JButton();
        menubutonu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MusteriTablo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Müsteri Adı :");

        jLabel2.setText("Müsteri Soyadı :");

        jLabel3.setText("Adres:");

        jLabel4.setText("Talep Edilen Ürün:");

        musteriadı.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musteriadıActionPerformed(evt);
            }
        });

        TalepUrun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TalepUrunActionPerformed(evt);
            }
        });

        ekleButon.setText("EKLE");
        ekleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ekleButonActionPerformed(evt);
            }
        });

        menubutonu.setText("MENÜ");
        menubutonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menubutonuActionPerformed(evt);
            }
        });

        MusteriTablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Musteri ID", "Musteri Adı", "Musteri Soyadı", "Adres", "Talep Urun"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(MusteriTablo);
        if (MusteriTablo.getColumnModel().getColumnCount() > 0) {
            MusteriTablo.getColumnModel().getColumn(0).setResizable(false);
            MusteriTablo.getColumnModel().getColumn(1).setResizable(false);
            MusteriTablo.getColumnModel().getColumn(2).setResizable(false);
            MusteriTablo.getColumnModel().getColumn(3).setResizable(false);
            MusteriTablo.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(musteriadı, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(musterisoyadı, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TalepUrun)
                            .addComponent(Adres, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(menubutonu, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(ekleButon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(musteriadı, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(musterisoyadı, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(ekleButon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Adres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TalepUrun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(menubutonu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ekleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ekleButonActionPerformed
 
        Islemler islemler = new Islemler();
        String musteriAdı=musteriadı.getText();
        String musteriSoyadı=musterisoyadı.getText();
        String adres=Adres.getText();
        String talepurun=TalepUrun.getText();
        islemler.musteriEkle(musteriAdı, musteriSoyadı, adres,talepurun);
        MusteriGörüntüle();
    }//GEN-LAST:event_ekleButonActionPerformed

    private void menubutonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menubutonuActionPerformed
        GirisEkrani girisEkrani = new GirisEkrani();
        setVisible(false);
        girisEkrani.setVisible(true);
    }//GEN-LAST:event_menubutonuActionPerformed

    private void musteriadıActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musteriadıActionPerformed
       
    }//GEN-LAST:event_musteriadıActionPerformed

    private void TalepUrunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TalepUrunActionPerformed
       
    }//GEN-LAST:event_TalepUrunActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MusteriEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MusteriEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MusteriEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MusteriEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MusteriEkle().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Adres;
    private javax.swing.JTable MusteriTablo;
    private javax.swing.JTextField TalepUrun;
    private javax.swing.JButton ekleButon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton menubutonu;
    private javax.swing.JTextField musteriadı;
    private javax.swing.JTextField musterisoyadı;
    // End of variables declaration//GEN-END:variables
}
