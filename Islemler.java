
package veritabaniprojesi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Islemler {
    public static int fiyat = 0,mesafe=0;
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    public Islemler() {
        
        String url = "jdbc:mysql://" + Baglanti.host + ":" + Baglanti.port + "/" + Baglanti.db_ismi+ "?useUnicode=true&characterEncoding=utf8";
        try {    
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
   
        try {
            con = DriverManager.getConnection(url, Baglanti.kullanici_adi, Baglanti.parola);
            System.out.println("Bağlantı Başarılı...");
      
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
        }
    }
   

    public int urunOlustur(String urun,int urunSayisi,int kar){
        int count=0,sayac=2,satisFiyati=0,maliyet=0;
        int urunID = 0;
      
        try {
           //ürünün var olup olmadığınu kontrol eder count 1 ise var 0 ise yoktur
           final String queryCheck = "SELECT count(*),kimyasalUrunId from ureticikimyasalurunstok WHERE kimyasalUrunAdı = ?";
           final PreparedStatement ps = con.prepareStatement(queryCheck);
           ps.setString(1, urun);
           final ResultSet resultSet = ps.executeQuery();
      
           if(resultSet.next()) {
                 count = resultSet.getInt(1);
                 urunID = resultSet.getInt(2);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(urunOlustur.class.getName()).log(Level.SEVERE, null, ex);  
         }  
        if(count!=0){
           // System.out.println("aa"+urunID);
            maliyet = yeniUrun(urun, urunSayisi);
            String sorgu = "Insert Into urunbilgileri (UrunID,uretimTarihi,rafOmru,iscilikmaliyeti,toplammaliyet,satışfiyatı) VALUES(?,?,?,?,?,?)";
            try {
                DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

                String date = formatter.format(LocalDate.now());
                //System.out.println(date);
                preparedStatement = con.prepareStatement(sorgu);
                preparedStatement.setInt(1, urunID);
                preparedStatement.setString(2, date);
                preparedStatement.setInt(3, sayac);
                preparedStatement.setInt(4, urunSayisi);
                preparedStatement.setInt(5, maliyet);
                satisFiyati = (maliyet*kar)/100 + maliyet;
                if(satisFiyati==0)
                    satisFiyati = 100;
                    preparedStatement.setInt(6, satisFiyati);
                    preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return satisFiyati-maliyet;
    }
    public int yeniUrun(String urun,int urunSayisi){
        int i=0,mesafe2=0;
        int[] fiyatlar = {0,0,0};
        int[] mesafeler1 ={0,0,0};
        List<Integer> mesafeler = Arrays.stream(mesafeler1).boxed().collect(Collectors.toList());
       
        String bilesenler[] = new String[3];
        int adet[] = new int[3];
       
          try {//kimyasal urundeki hammaddeler ve adetler bulunur
            String sorgu =  "Select h.hammaddeAdı,u.adet From ureticikimyasalurun as u,ureticikimyasalurunstok as stok,ureticihammade as h"
                    + " where stok.kimyasalUrunAdı = ? and stok.KimyasalurunId = u.KimyasalurunId and u.hammaddeID = h.hammaddeID ";
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1,urun);
  
            ResultSet rs =  preparedStatement.executeQuery();
            
            while(rs.next()){
                bilesenler[i] = rs.getString(1);
                adet[i] = rs.getInt(2);
                i++;
            }
        } catch (Exception ex) {
               Logger.getLogger(urunOlustur.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int k=0;k<urunSayisi;k++){
            try {//stok durumu kontrol edilir
                int flag = 0,stok=0,flag2=0;
                String[] bilesen =new String[5];
                String sorgu2 = "Select StokDurumu from ureticihammade where HammaddeAdı = ?";
                //System.out.println("aaa"+i);
               
                for(int j=0;j<i;j++){
                     preparedStatement = con.prepareStatement(sorgu2);
                     //System.out.println(bilesenler[j]+" ////"+adet[j]);
                     preparedStatement.setString(1,bilesenler[j]);
                     // preparedStatement.setInt(2, adet[j]);
                     ResultSet rs =  preparedStatement.executeQuery();
                     int sonuc=0;
                     while (rs.next()) {
                        sonuc = rs.getInt("StokDurumu");
                        //System.out.println(bilesenler[j]+"*sonuc:"+sonuc);
                        flag = 1;
                    }   
                   // System.out.println("stok durumu:"+sonuc+"adet:"+adet[j]);
                    if(sonuc<adet[j]){
                        //System.out.println("k:"+k);
                         mesafe2=minHammaddeBul(bilesenler[j],adet[j]-sonuc);
                        //System.out.println("mesafe::"+mesafe);
                        flag2 = 1;
                    }
                   if(flag2==1){
                       //System.out.println(mesafeler.get(j));
                       //System.out.println("mesafe:"+(mesafeler.get(j)!=mesafe && mesafeler.get(j)%mesafe!=0));
                      if(mesafeler.get(j)!=mesafe && (mesafeler.get(j)%mesafe!=0 || mesafeler.get(j)==0) ){
                          mesafeler.set(j, (mesafeler.get(j)+ mesafe));  
                      }
                  
                      fiyatlar[j] = mesafe2+ fiyatlar[j];
                      flag2 = 0;
                    }
                    flag = 0;
                    fiyat = 0;
                }
                stokDurumuGuncelle(urun, 1, bilesenler, adet);
            } 
            catch (Exception e) {
             }
        }
        int toplamMaliyet = 0;
        for(int l=0;l<i;l++){
            System.out.println("j:"+l+"fiyatlar:"+fiyatlar[l]+"mesafe:"+mesafeler.get(l));
            int toplam = fiyatlar[l] + mesafeler.get(l);
            hammaddeStokGuncelle(bilesenler[l],toplam);
            toplamMaliyet+= toplam;
        }
        mesafeler.clear();
        return toplamMaliyet;  
    }
     public ArrayList<Tedarikci> tedarikcileriGetir(){
        ArrayList<Tedarikci>cikti=new ArrayList<Tedarikci>();
   
         try {
            statement=con.createStatement();
            String sorgu18="Select * from tedarikciurunbilgileri";
            ResultSet rs=statement.executeQuery(sorgu18);
            while(rs.next()){
                int id=rs.getInt("firmaID");
                String uretilenmaddeler=rs.getString("uretilenMaddeler");
                int miktar=rs.getInt("miktar");
                String uretimTarihi=rs.getString("uretimTarihi");
                int rafOmru=rs.getInt("rafOmru");
                int satisFiyati=rs.getInt("satisFiyati");   
                cikti.add(new Tedarikci(id, uretilenmaddeler, miktar, uretimTarihi, rafOmru, satisFiyati));
            }
            return cikti;
        } catch (Exception e) {
            return null;
        }
    }
      public ArrayList<Tedarikci> tedarikciFirmaBilgileri(){
        ArrayList<Tedarikci>cikti=new ArrayList<Tedarikci>();
   
         try {
            statement=con.createStatement();
            String sorgu="Select * from tedarikcifirmabilgileri";
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                int id=rs.getInt("firmaID");
                String firmaAdi=rs.getString("firmaAdi");
                String sehirMerkezi=rs.getString("sehirMerkezi");
                cikti.add(new Tedarikci(id, firmaAdi, sehirMerkezi));    
            }
            return cikti;
        } catch (Exception e) {
            return null;
        }
    }
    public void hammaddeStokGuncelle(String hammadde,int fiyat){
        int Id=0;
        
         try {
            String sorgu1 = "Select hammaddeId from ureticihammade where hammaddeAdı = ?";
            preparedStatement = con.prepareStatement(sorgu1);
            preparedStatement.setString(1, hammadde);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){ 
                 Id = rs.getInt(1);
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
           String sorgu2 = "Insert Into ureticialısmaliyeti (hammaddeId,alısmaliyeti)VALUES(?,?)";
           preparedStatement = con.prepareStatement(sorgu2);
           preparedStatement.setInt(1, Id);
           preparedStatement.setInt(2, fiyat);
           preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
       
    }
    public void tedarikciEkle(String firmaAdi,String ulke,String sehir,String uretilenMaddeler,
        int miktar,String tarih,float satisFiyati,int rafOmru){
        String sorgu = "Insert Into tedarikcifirmabilgileri (firmaAdi,sehirMerkezi) VALUES(?,?)";
          
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, firmaAdi);
            preparedStatement.setString(2, sehir);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            statement =  (Statement) con.createStatement();
            String sorgu2 =  "Select MAX(firmaID) From tedarikcifirmabilgileri";
            ResultSet rs =  statement.executeQuery(sorgu2);
            while (rs.next()) {
                int name = rs.getInt(1);
                
                String sorgu3 = "Insert Into tedarikciurunbilgileri (uretilenMaddeler,"
                        + "miktar,uretimTarihi,rafOmru,satisFiyati,firmaID) VALUES(?,?,?,?,?,?)";
                preparedStatement = con.prepareStatement(sorgu3);
                System.out.println(name);
                preparedStatement.setInt(6, name);
            } 
            preparedStatement.setString(1, uretilenMaddeler);
            preparedStatement.setInt(2, miktar);
            preparedStatement.setString(3, tarih);
            preparedStatement.setInt(4, rafOmru);
            preparedStatement.setFloat(5, satisFiyati);
            preparedStatement.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void musteriEkle(String musteriAdi,String musteriSoyadi,String adres,String urun){
         String sorgu = "Insert Into musteribilgileri1 (musteriAdi,musteriSoyadi,adresBilgileri,talepUrun) VALUES(?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, musteriAdi);
            preparedStatement.setString(2, musteriSoyadi);
            preparedStatement.setString(3, adres);
            preparedStatement.setString(4, urun);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  void yeniUrunEkle(String urunAdı) throws SQLException  {
        String hammaddeBilesen[] = new String[3];
        ArrayList<String>bilesen=new  ArrayList<String>();
        bilesen.add(urunAdı);
        //System.out.println(bilesen.get(0));
         hammaddeBilesen[0]=urunAdı;
        // System.out.println(hammaddeBilesen[0]);
        String dizi[]=new String[hammaddeBilesen[0].length()];
        for(int k=0;k<hammaddeBilesen[0].length()-1;k++){
            String harf=hammaddeBilesen[0].substring(k,k+1);
            dizi[k]=harf;
            /*System.out.println(dizi[k]);
             System.out.println("sssssssss");
             System.out.println(hammaddeBilesen[0].length());*/
        }
        String dizi2[]=new String[hammaddeBilesen[0].length()/2];
        int m=0;
        for(int k=0;k<hammaddeBilesen[0].length()-1;k=k+1){
            if(k%2==0){
                dizi2[m]=dizi[k];
                 m++;
             }
        }
        String sorgu16="Insert Into ureticihammade(hammaddeAdı,stokdurumu) VALUES(?,0) ";
        for(int k=0;k<m;k=k+1){
            preparedStatement = con.prepareStatement(sorgu16);
            preparedStatement.setString(1,dizi2[k]);
            preparedStatement.executeUpdate();
        }    
        int adet1[]=new int[3];
        int i=0;
        try{    
            String sorgu3= "SELECT count(*) from ureticikimyasalurunstok WHERE kimyasalUrunAdı = ? ";
            final PreparedStatement ps = con.prepareStatement(sorgu3);
            ps.setString(1,urunAdı);
            final ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                int count = resultSet.getInt(1);    
                if(count==0){
                     String sorgu4="Insert Into ureticikimyasalurunstok (firmaID,kimyasalUrunAdı,kimyasalUrunStok) VALUES(1,?,0)";
                    preparedStatement = con.prepareStatement(sorgu4);
                    preparedStatement.setString(1,urunAdı);
                    preparedStatement.executeUpdate();          
                }
                 else if(count==1){
                    System.out.println("urun ekleme");            
                    //JOptionPane.showMessageDialog(null, "O ÜRÜN BULUNMAKTADIR.EKLEME İŞLEMİ BAŞARISIZ!", "Mesaj -1", -1)
                }
            }
        }   
        catch(SQLException ex){
             Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public void stokDurumuGuncelle(String urun,int urunSayisi,String bilesenler[],int adet[]){
        String sorgu = " Update ureticihammade as u set u.stokdurumu = (u.stokdurumu -?) "
                + "where u.hammaddeAdı = ? ";
        
        try {
            for(int i=0;i<bilesenler.length-1;i++){
                preparedStatement = con.prepareStatement(sorgu);
                preparedStatement.setInt(1, (adet[i]*urunSayisi));
                preparedStatement.setString(2, bilesenler[i]);
                preparedStatement.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sorgu2 = " Update ureticikimyasalurunstok as s set  s.kimyasalurunstok = (s.kimyasalurunstok+?)"
                + " where s.kimyasalurunAdı = ?  ";
        
        try {
            preparedStatement = con.prepareStatement(sorgu2);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, urun);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int minHammaddeBul(String hammadde,int adet){
        int miktar=0, id=0,x=0,index=0;
        float min = 0;
        int[] mesafeler = new int[10];
        int[] fiyatlar = new int[10];
        int[] miktarlar = new int[10];
        int [] firmaID = new int[10];
      
        while(adet>0){
           String s = "Select m.mesafe,u.satisFiyati ,u.miktar,u.firmaID from tedarikciurunbilgileri as u, tedarikcimesafebilgileri as m"
                   + " INNER JOIN tedarikcifirmabilgileri as f ON f.sehirMerkezi = m.sehir "
                   + "where u.uretilenMaddeler = ? and u.miktar>0 and u.firmaId = f.firmaID   ";
           
            try {
                preparedStatement = con.prepareStatement(s);
                preparedStatement.setString(1, hammadde);
              //  preparedStatement.setInt(2, adet);
                ResultSet rs =  preparedStatement.executeQuery();
                int i = 0;
                while(rs.next()){
                    mesafeler[i] = rs.getInt(1);
                    fiyatlar[i] = rs.getInt(2);
                    miktarlar[i] = rs.getInt(3);
                    firmaID[i] = rs.getInt(4);
                    i++;
                }
               int  depo = mesafeler[0] + fiyatlar[0];
        
                for (int j = 0; j < i; j++){
                   if (mesafeler[j]+fiyatlar[j] < depo) {
                     depo = mesafeler[j]+fiyatlar[j];
                     index = j;
                    }
                }
                x = adet;
                miktar = miktarlar[index];
                mesafe = mesafeler[index];
                if(miktar>=adet){
                   miktar-= adet;
                   fiyat = fiyat + (x * fiyatlar[index]);
                   adet = 0;
                }
                else{
                   adet-= miktar ;
                   fiyat = fiyat +(  fiyatlar[index] * (x-adet));
                   miktar = 0;
                }
                //System.out.println("min:"+depo+"fiyat:"+fiyat);
        
            } catch (SQLException ex) {
                Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
            }
         try {
            String query =  "Update tedarikciurunbilgileri set miktar = ? where firmaID =? and uretilenMaddeler = ?";
            preparedStatement = con.prepareStatement(query);

            System.out.println("//"+miktar+" "+id+" "+hammadde);
            preparedStatement.setInt(1,miktar);
            preparedStatement.setInt(2, firmaID[index]);
            preparedStatement.setString(3, hammadde);
            preparedStatement.executeUpdate();
         } catch (Exception e) {
            }
        }
     
        try {
             String sorguGuncelle = "Update ureticihammade m set m.stokdurumu = (m.stokdurumu+?)"
                     + " where m.HammaddeAdı = ? ";
  
             System.out.println("x:"+x);
            preparedStatement = con.prepareStatement(sorguGuncelle);
            preparedStatement.setInt(1, x);
            preparedStatement.setString(2, hammadde);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
     return fiyat;
        
    }
    public ArrayList<urunBilgileri> bilgileriGetir(){
        ArrayList<urunBilgileri> cikti = new ArrayList<urunBilgileri>();
        
        try {
            statement =  (Statement) con.createStatement();
            String sorgu =  "Select * From tedarikciurunbilgileri";
            
            ResultSet rs =  statement.executeQuery(sorgu);
            
            while(rs.next()) {
                String uretilenMaddeler = rs.getString("uretilenMaddeler");
                int miktar = rs.getInt("miktar");
                Date uretimTarihi = rs.getDate("uretimTarihi");
                int rafOmru  = rs.getInt("rafOmru");
                float satisFiyati = rs.getFloat("satisFiyati");
                int firmaID = rs.getInt("firmaID");
                
                cikti.add(new urunBilgileri(uretilenMaddeler, miktar, uretimTarihi, rafOmru, satisFiyati,firmaID)); 
            }
            return cikti;   
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    public ArrayList<Musteri> musteriGetir(){
        ArrayList<Musteri> cikti = new ArrayList<Musteri>();

        try {
             statement =  (Statement) con.createStatement();
             String sorgu =  "Select * From musteribilgileri1";
             ResultSet rs =  statement.executeQuery(sorgu);
                       
             while(rs.next()) {
                int MusteriId=rs.getInt("MusteriId");
                String musteriAdi=rs.getString("MusteriAdı");
                String MusteriSoyadı=rs.getString("MusteriSoyadı");
                String adres=rs.getString("adresbilgileri");
                String talepUrun=rs.getString("talepurun");
                cikti.add(new Musteri(MusteriId, musteriAdi, MusteriSoyadı, adres, talepUrun));
             }
            return cikti;
              
        } catch (Exception ex) {
             Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    }
    public  ArrayList<Siparis> SiparisGetir(){
        ArrayList<Siparis> cikti = new ArrayList<Siparis>();
        try {
            statement =  (Statement) con.createStatement();
            String sorgu =  "Select * From ureticikimyasalurunstok";
            ResultSet rs =  statement.executeQuery(sorgu);
            while(rs.next()) {
                int firmaId=rs.getInt(1);
                int kimyasalurunId=rs.getInt(2);
                String kimyasalUrunAdı=rs.getString(3);
                int kimyasalUrunStok=rs.getInt(4);
                cikti.add(new Siparis(firmaId, kimyasalurunId, kimyasalUrunAdı, kimyasalUrunStok));
            }
             return cikti;
        } catch (Exception e) {
            return null;
        }
    }
     public  ArrayList<Siparis> SiparisGetir2(){
        ArrayList<Siparis> cikti = new ArrayList<Siparis>();
        try {
            statement =  (Statement) con.createStatement();
            String sorgu =  "Select * From ureticihammade";
            ResultSet rs =  statement.executeQuery(sorgu);
            while(rs.next()) {
                int hammaddeID= rs.getInt(1);
                String hammaddeAdi=rs.getString(2);
                int stokDurumu=rs.getInt(3);
                
                cikti.add(new Siparis(hammaddeID, hammaddeAdi, stokDurumu));
            }
             return cikti;
        } catch (Exception e) {
            return null;
        }
    }
      public  ArrayList<Siparis> SiparisGetir3(){
        ArrayList<Siparis> cikti = new ArrayList<Siparis>();
        try {
            statement =  (Statement) con.createStatement();
            String sorgu =  "Select * From ureticialısmaliyeti";
            ResultSet rs =  statement.executeQuery(sorgu);
            while(rs.next()) {
                int hammaddeID= rs.getInt(1);
                int alisMaliyeti=rs.getInt(2);
                
                cikti.add(new Siparis(hammaddeID, alisMaliyeti));
            }
             return cikti;
        } catch (Exception e) {
            return null;
        }
    }
     public ArrayList<Urun> urunBilgileriGetir(){
        ArrayList<Urun> cikti = new ArrayList<>();
        try {
            statement =  (Statement) con.createStatement();
            String sorgu =  "Select * From urunbilgileri";
            ResultSet rs =  statement.executeQuery(sorgu);
            while(rs.next()) {
                int urunID= rs.getInt(1);
                String uretimTarihi = rs.getString(2);
                int rafOmru = rs.getInt(3);
                int iscilikMaliyeti = rs.getInt(4);
                int maliyet = rs.getInt(5);
                int satisFiyati = rs.getInt(6);
                
                cikti.add(new Urun(urunID, uretimTarihi,rafOmru,iscilikMaliyeti,maliyet,satisFiyati));
            }
             return cikti;
        } catch (Exception e) {
            return null;
        }
     }
    public static void main(String[] args) {
        Islemler islemler = new Islemler();
    }
}
