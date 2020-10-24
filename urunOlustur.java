package veritabaniprojesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class urunOlustur extends javax.swing.JFrame {
    DefaultTableModel model,model2,model3;
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    Islemler Islemler = new Islemler();
    public urunOlustur() {
        initComponents();
        model = (DefaultTableModel) ureticikimyasalurunstoktablo.getModel();
        model2 = (DefaultTableModel) alısmaliyetitablo.getModel();
        model3 = (DefaultTableModel) ureticihammaddetablo.getModel();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        urunAlani = new javax.swing.JTextField();
        girisButonu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        urun_adet = new javax.swing.JTextField();
        menu_butonu = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        karOrani = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        alısmaliyetitablo = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        ureticikimyasalurunstoktablo = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        ureticihammaddetablo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Oluşturmak istediğiniz ürünü girin:");

        girisButonu.setText("Giris");
        girisButonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                girisButonuActionPerformed(evt);
            }
        });

        jLabel2.setText("Oluşturulacak ürün adedi:");

        urun_adet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urun_adetActionPerformed(evt);
            }
        });

        menu_butonu.setText("Menü");
        menu_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_butonuActionPerformed(evt);
            }
        });

        jLabel3.setText("Kar oranı:");

        alısmaliyetitablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hammadde Id", "Alış Maliyeti"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(alısmaliyetitablo);

        ureticikimyasalurunstoktablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "firma ID", "Urun ID", "Urun Adı", "Stok"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ureticikimyasalurunstoktablo);

        ureticihammaddetablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hammadde ID", "Hammadde Adı", "stok "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ureticihammaddetablo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(girisButonu, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(menu_butonu, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(urunAlani, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(urun_adet)
                                    .addComponent(karOrani))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(urunAlani, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(urun_adet, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(karOrani, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(menu_butonu)
                            .addComponent(girisButonu))))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void girisButonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_girisButonuActionPerformed
         String urun = urunAlani.getText();
         String adetString = urun_adet.getText();
         int adet = Integer.parseInt(adetString);
         String karOrani = this.karOrani.getText();
         int kar = Integer.parseInt(karOrani);
         Islemler islemler = new Islemler();
         islemler.urunOlustur(urun,adet,kar);
         kimyasalUrunGöster();
         AlisMaliyetiGöster();
         HammaddeGöster();
    }//GEN-LAST:event_girisButonuActionPerformed
 
    public  void kimyasalUrunGöster(){
        model.setRowCount(0);
        ArrayList<Siparis>siparis=new ArrayList<Siparis>();
        siparis=Islemler.SiparisGetir();
        if(siparis!=null){
            for(Siparis siparis1:siparis){
                Object[]eklenecek={siparis1.getFirmaID(),siparis1.getKimyasalUrunId(),siparis1.getKimyasalUrunAdı(),siparis1.getKimyasalUrunStok()};
                model.addRow(eklenecek);
            }
         }   
    }
    public void AlisMaliyetiGöster(){
        model2.setRowCount(0);
        ArrayList<Siparis>siparis=new ArrayList<Siparis>();
        siparis=Islemler.SiparisGetir3();
        if(siparis!=null){
           for(Siparis siparis1:siparis){
                Object[] eklenecek={siparis1.getHammaddeId(),siparis1.getAlısmaliyeti()};
                model2.addRow(eklenecek);
           }
        }   
    }
    public void HammaddeGöster(){
        model3.setRowCount(0);
        ArrayList<Siparis>siparis=new ArrayList<Siparis>();
        siparis=Islemler.SiparisGetir2();
        if(siparis!=null){
            for(Siparis siparis1:siparis){
                Object[]eklenecek={siparis1.getHammaddeId(),siparis1.getHammmaddeAdı(),siparis1.getStokdurumu() };
                 model3.addRow(eklenecek);
            }
        }   
     }

    private void urun_adetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urun_adetActionPerformed
        
    }//GEN-LAST:event_urun_adetActionPerformed

    private void menu_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_butonuActionPerformed
        GirisEkrani girisEkrani = new GirisEkrani();
        setVisible(false);
        girisEkrani.setVisible(true);
    }//GEN-LAST:event_menu_butonuActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new urunOlustur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable alısmaliyetitablo;
    private javax.swing.JButton girisButonu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField karOrani;
    private javax.swing.JButton menu_butonu;
    private javax.swing.JTable ureticihammaddetablo;
    private javax.swing.JTable ureticikimyasalurunstoktablo;
    private javax.swing.JTextField urunAlani;
    private javax.swing.JTextField urun_adet;
    // End of variables declaration//GEN-END:variables
}
