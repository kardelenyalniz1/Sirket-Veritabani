
package veritabaniprojesi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.security.rsa.RSACore;


public class Islemler {
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
            //ex.printStackTrace();
        }
        
        
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
      public int urunOlustur(String urun,int Adet){
        int count=0;
        int i=0;
        String bilesenler[] = new String[3];
        int adet[] = new int[3];
       
        try {
           //ürünün var olup olmadığınu kontrol eder count 1 ise var 0 ise yoktur
           final String queryCheck = "SELECT count(*) from ureticikimyasalurunstok WHERE kimyasalUrunAdı = ?";
           final PreparedStatement ps = con.prepareStatement(queryCheck);
           ps.setString(1, urun);
           final ResultSet resultSet = ps.executeQuery();
           if(resultSet.next()) {
                 count = resultSet.getInt(1);
                 System.out.println("Count:"+count);
            }
            if(count==0){
                return 0;
            }
         
        }
        catch (SQLException ex) {
           Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);  
         }
            
        try {
            String sorgu =  "Select bilesenler,adet From ureticikimyasalurun where kimyasalUrunAdı = ?";
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1,urun);
  
            ResultSet rs =  preparedStatement.executeQuery();
        //int i=0;
            
            
            while(rs.next()){
                bilesenler[i] = rs.getString(1);
                adet[i] = rs.getInt(2);
                i++;
            }
            for(int j=0;j<i;j++){
                System.out.println("--"+bilesenler[j]+" "+adet[j]);
            }
        } catch (Exception ex) {
               Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int flag = 0,stok=0;
            String sorgu2 = "Select StokDurumu from ureticihambilgileri where HammaddeAdı = ?";
            System.out.println("aaa"+i);
             preparedStatement = con.prepareStatement(sorgu2);
             for(int j=0;j<i;j++){
                 System.out.println(bilesenler[j]+" "+adet[j]);
                 preparedStatement.setString(1,bilesenler[j]);
                // preparedStatement.setInt(2, adet[j]);
                 ResultSet rs =  preparedStatement.executeQuery();
                 
                 while (rs.next()) {
                    int sonuc = rs.getInt("StokDurumu");
                    stok = sonuc - adet[j];
                    System.out.println(bilesenler[j]+"*sonuc:"+sonuc);
                    flag = 1;
                 }   
                 if(stok<0){
                      System.out.println("sonuc:0");
                      minHammaddeBul(bilesenler[j],adet[j]);
                 }
                 flag = 0;
             }
  
           
        } catch (Exception e) {
        }
        return 1;
    }
   public void tedarikciEkle(String firmaAdi,String ulke,String sehir,String uretilenMaddeler,
        int miktar,String tarih,float satisFiyati,int rafOmru){
        String sorgu = "Insert Into tedarikcifirmabilgileri (firmaAdi,sehirMerkezi) VALUES(?,?)";
          
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, firmaAdi);
           // preparedStatement.setString(2, ulke);
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

     public int musteriEkle(String musteriAdi,String musteriSoyadi,String adres,String urun){
         String sorgu = "Insert Into musteribilgileri1 (musteriAdı,musteriSoyadı,adresbilgileri,talepurun) VALUES(?,?,?,?)";
         
        try {
            preparedStatement = con.prepareStatement(sorgu);
          
           // preparedStatement.setString(1, ad);
            preparedStatement.setString(1, musteriAdi);
            preparedStatement.setString(2, musteriSoyadi);
            preparedStatement.setString(3, adres);
            preparedStatement.setString(4, urun);
            
            preparedStatement.executeUpdate();
            
        
            String sorgu2 =  "Select kimyasalUrunAdi From ureticikimyasalurun where  kimyasalUrunAdi IN (?) AND ureticikimyasalurun.adet!=0";
             preparedStatement = con.prepareStatement(sorgu2);
             preparedStatement.setString(1,urun);
            
            ResultSet rs =  preparedStatement.executeQuery();
            
              while (rs.next()) {
                String name = rs.getString(1);
                System.out.println(name);
 
              }   
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    /*public void stokDurumuGuncelle(String hammadde){
        String sorgu = "Select "
    }*/
    public void minHammaddeBul(String hammadde,int adet){
        int miktar=0, id=0,x=0;
        float min = 0;
        double mesafe = 0,fiyat = 0;
        while(adet>0){
           
           try {
               String sorgu =  "Select MIN(satisFiyati) From tedarikciurunbilgileri where   uretilenMaddeler = ? AND miktar!=0";
               preparedStatement = con.prepareStatement(sorgu);
               preparedStatement.setString(1, hammadde);
               ResultSet rs =  preparedStatement.executeQuery();
               while (rs.next()) {
                 min = rs.getFloat(1);
                 System.out.println("min:"+min);
                }    
           
            } 
            catch (SQLException ex) {
                Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
            }
          try {
           
          String sorgu2 =  "Select miktar,firmaID From tedarikciurunbilgileri where  uretilenMaddeler = ? and satisFiyati = ?";
           preparedStatement = con.prepareStatement(sorgu2);
          // preparedStatement.setInt(1, adet);
           preparedStatement.setString(1, hammadde);
           preparedStatement.setFloat(2, min);
             System.out.println("dfdsgfdg");
           ResultSet rs =  preparedStatement.executeQuery();
      
           while (rs.next()) {
              System.out.println("333");
               miktar = rs.getInt(1); //firmada bulunan elemntin sayısı
               System.out.println("miktar:"+miktar);
               System.out.println("adet:"+adet);
               id = rs.getInt("firmaID");
               x = adet;
               if(miktar>=adet){
                   miktar-= adet;
                   fiyat = fiyat + (x * min);
                   adet = 0;
               }
               else{
                   adet-= miktar ;
                   fiyat = fiyat +( min * (x-adet));
                   miktar = 0;
               }
            }     
                
        } catch (Exception e) {
            
         }
         try {
            String query =  "Update tedarikciurunbilgileri set miktar = ? where firmaID =? and uretilenMaddeler = ?";
            preparedStatement = con.prepareStatement(query);

             System.out.println("//"+miktar+" "+id+" "+hammadde);
            preparedStatement.setInt(1,miktar);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, hammadde);
            preparedStatement.executeUpdate();
         } catch (Exception e) {
         }
        }
       
         String sorgu5 = "Select m.ulke,m.mesafe from tedarikcifirmabilgileri as f,tedarikcimesafebilgileri as m where f.sehirMerkezi = m.sehir and"
               + " f.firmaID = ?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu5);
            preparedStatement.setInt(1, id);
            ResultSet rs =  preparedStatement.executeQuery();
             while(rs.next()){
                 String ulke = rs.getString(1);
                 mesafe = rs.getDouble(2);
                 System.out.println("Ulke:"+ulke+"mesafe:"+mesafe);
                 if(ulke.equals("Türkiye")){
                     mesafe = mesafe * 0.5;
                 }
             }
             fiyat = fiyat + mesafe;
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
             String sorguGuncelle = "Update ureticihammaliyeti m,ureticihambilgileri b set m.ALISMALIYETI = ?,b.StokDurumu = ?"
                     + " where b.HammaddeAdı = ? and m.hammaddeid = b.hammaddeID ";
            System.out.println("fiyat:"+fiyat);
            preparedStatement = con.prepareStatement(sorguGuncelle);
            preparedStatement.setDouble(1, fiyat);
            preparedStatement.setInt(2, x);
            preparedStatement.setString(3, hammadde);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
  
     public  int yeniUrunEkle(String urunAdı) throws SQLException  {
           String hammaddeBilesen[] = new String[3];
           ArrayList<String>bilesen=new  ArrayList<String>();
           bilesen.add(urunAdı);
           System.out.println(bilesen.get(0));
           hammaddeBilesen[0]=urunAdı;
           System.out.println(hammaddeBilesen[0]);
           String dizi[]=new String[hammaddeBilesen[0].length()];
               for(int k=0;k<hammaddeBilesen[0].length()-1;k++){
               
                   String harf=hammaddeBilesen[0].substring(k,k+1);
                   dizi[k]=harf;
                   System.out.println(dizi[k]);
                   System.out.println("sssssssss");
                   System.out.println(hammaddeBilesen[0].length());
                  
               }
             
             
             
             String dizi2[]=new String[hammaddeBilesen[0].length()/2];
                  int m=0;

               for(int k=0;k<hammaddeBilesen[0].length()-1;k=k+1){
                   /*dizi2[m]=dizi[k];
                // System.out.println(dizi2[m]);
                   m++;*/
                   if(k%2==0){
                        dizi2[m]=dizi[k];
                        m++;
                   }
               }
             for(int k=0;k<hammaddeBilesen[0].length()/2-1;k++){
                  System.out.println(dizi2[k]);
                  
              }
             System.out.println("m:"+m);
            
             
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
           /* 
             while(resultSet.next()){
                hammaddeBilesen[i]=resultSet.getString(1);
                adet1[i]=resultSet.getInt(2);
                 i++;
             }*/
            if(resultSet.next()) {
            int count = resultSet.getInt(1);    
            
            if(count==0){
            
             String sorgu4="Insert Into ureticikimyasalurunstok (firmaID,kimyasalUrunAdı,kimyasalUrunStok) VALUES(1,?,0)";
             
             preparedStatement = con.prepareStatement(sorgu4);
             preparedStatement.setString(1,urunAdı);
             preparedStatement.executeUpdate();
            
            return 1;
    }
    else if(count==1){
        System.out.println("urun ekleme");            
         //JOptionPane.showMessageDialog(null, "O ÜRÜN BULUNMAKTADIR.EKLEME İŞLEMİ BAŞARISIZ!", "Mesaj -1", -1)
    }
}
           
        }   
        catch(SQLException ex){
             Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
                 return 0; 
        }
     /*   try {
              String[] bilesen1=new String[5];
              String sorgu15="Select kimyasalUrunAdı from ureticikimyasalurunstok";
              for(int j=0;j<i;j++){
                preparedStatement=con.prepareStatement(sorgu15);
                preparedStatement.setString(1,hammaddeBilesen[j]);
                ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()){
                    System.out.println(hammaddeBilesen[j]);
                    
                }
                
            } 
         } catch (Exception e) {
         }
        
      /* for(int j=0;j<i;j++){
                preparedStatement=con.prepareStatement(sorgu3);
                preparedStatement.setString(1,hammaddeBilesen[j]);
                ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()){
                    System.out.println(hammaddeBilesen[j]);
                    
                }
                
            } 
        
        String sorgu15="Select kimyasalUrunAdı from ureticikimyasalurunstok";
       final PreparedStatement ps3 = con.prepareStatement(sorgu15);

         ResultSet rs = ps3.executeQuery( "Select kimyasalUrunAdı from ureticikimyasalurunstok;" );
        int columnCount = rs.getMetaData().getColumnCount();
        while(rs.next())
           {
         String[] row = new String[columnCount];
         for ( i=0; i <columnCount ; i++)
      {
       row[i] = rs.getString(i + 1);
     }
            bilesen.add(row);
      }
             for(i=0;i<bilesen.size();i++){
                 System.out.println(bilesen.get(i));
             }
             
             
                 
                
                     
        /* while(resultSet.next()){
                hammaddeBilesen[i]=resultSet.getString(1);
                i++;
            }
            int index=hammaddeBilesen.length;
            for(i=0;i<index;i++){
                System.out.println(hammaddeBilesen[i]);
               
            }
        try{
         String sorgu8="Select urunAdı from urunadı Where urunAdı = ? ";
          preparedStatement = con.prepareStatement(sorgu8);
          preparedStatement.setString(1,urunAdı);
         ResultSet rs =  preparedStatement.executeQuery();
         
         
         
         String sorgu9="Select U.hammadde,A.urunAdı,LEFT(urunAdı,1) as hammadde from urunhammaddeleri U,urunadı A "
                 + "where UrunSId In(1) ";
         
         String sorgu10="Select urunAdı,Length(urunAdı)From urunadı";
         preparedStatement = con.prepareStatement(sorgu10);
         ResultSet rs1 =  preparedStatement.executeQuery();
         
         String sorgu11="SELECT LEFT(urunAdı,1) From urunadı AND ";
         
         //SELECT urunAdı ,hammadde From urunadı,urunhammaddeleri u  INNER JOIN u.hammade ON INSERT LEFT(urunAdı,1) ; 
         //SELECT a.urunAdı ,u.hammadde From urunadı a,urunhammaddeleri u INNER JOIN urunAdı ON LEFT(urunAdı,1)= u.hammadde

         preparedStatement = con.prepareStatement(sorgu11);
         ResultSet rs2 =  preparedStatement.executeQuery();
         
       /*  while(rs.next()){
             hammaddeBilesen[i]=rs.getString(1);
                i++;      
                
         }
         while(rs2.next()){
             hammaddeBilesen[i]=rs.getString(1);
                i++;     
                
         }
         
         
         
         
         
         
        }
        catch(SQLException ex){
             Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
                 return 0; 
        }
        
        */
        
        
        
                return 0;

      }
    public void SiparilAl(String musteriAdi,String musteriSoyadi,String adres,String urun,String adet) throws SQLException{
       
                 try{                    
          String sorgu5="SELECT count(*) From ureticikimyasalurunstok WHERE kimyasalUrunAdı = ?";
          final PreparedStatement ps = con.prepareStatement(sorgu5);
           ps.setString(1,urun);
        //   ps.setString(1,adet);
           final ResultSet resultSet = ps.executeQuery();
              if(resultSet.next()) {
                 final int count = resultSet.getInt(1);
                  if(count==1){   
                String sorgu6="Select From ureticikimyasalurunstok Where kimyasalUrunStok = ?  ";
                final PreparedStatement ps1 = con.prepareStatement(sorgu6);
                ps1.setString(1, adet);
                final ResultSet resultSet1 = ps1.executeQuery();
              // String sorgu7="Select From ureticikimyasalurunstok Where kimyasalUrunStok >=result"
                       int ifade1 = Integer.valueOf(sorgu6);     
                       int ifade2 = Integer.parseInt(sorgu6);
                       int ifade3 = Integer.valueOf(adet);
                       int ifade4 = Integer.parseInt(adet);
                        
                if(ifade2>=ifade4)                  
                {
                    System.out.println("satış yapılır.");   
                }
                else if(ifade4<ifade2){
                    System.out.println("stok yeterli değil");
                  String sorgu7 =  "Select MIN(satisFiyati) From tedarikciurunbilgileri where miktar!=0";
                  

                }
            }                 
            }
                 }
                   catch(SQLException ex){
             Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
                  
        }
         
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
    
    public static void main(String[] args) {
        Islemler islemler = new Islemler();
    }

   
    
}


/*
int yeniUrunEkle(String urunAdı,String uretimtarihi,String rafomru,String iscilikmaliyeti ) throws SQLException{
        
        try{        
            String sorgu3= "SELECT count(*) from urunbilgileri WHERE UrunAdı = ?";
            final PreparedStatement ps = con.prepareStatement(sorgu3);
            ps.setString(1,urunAdı);
            final ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
           final int count = resultSet.getInt(1);
            if(count==0){
                String sorgu4="Insert Into urunbilgileri (urunAdı,uretimtarihi,rafomru,iscilikmaliyeti) VALUES (?,?,?,?)";
            preparedStatement = con.prepareStatement(sorgu4);
            preparedStatement.setString(1,urunAdı);
            preparedStatement.setString(2,uretimtarihi);
            preparedStatement.setString(3,rafomru);
            preparedStatement.setString(4,iscilikmaliyeti);
            
            preparedStatement.executeUpdate();
            return 1;
    }
    else if(count==1){
        System.out.println("urun ekleme");            
         //JOptionPane.showMessageDialog(null, "O ÜRÜN BULUNMAKTADIR.EKLEME İŞLEMİ BAŞARISIZ!", "Mesaj -1", -1);

    }

}
}
        
        
        catch(SQLException ex){
             Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
                  
        }
                return 0;

      }
    public void SiparilAl(String musteriAdi,String musteriSoyadi,String adres,String urun,String adet) throws SQLException{
       
                 try{                    
          String sorgu5="SELECT count(*) From ureticikimyasalurunstok WHERE kimyasalUrunAdı = ?";
          final PreparedStatement ps = con.prepareStatement(sorgu5);
           ps.setString(1,urun);
        //   ps.setString(1,adet);
           final ResultSet resultSet = ps.executeQuery();
              if(resultSet.next()) {
                 final int count = resultSet.getInt(1);
                  if(count==1){   
                String sorgu6="Select From ureticikimyasalurunstok Where kimyasalUrunStok = ?  ";
                final PreparedStatement ps1 = con.prepareStatement(sorgu6);
                ps1.setString(1, adet);
                final ResultSet resultSet1 = ps1.executeQuery();
              // String sorgu7="Select From ureticikimyasalurunstok Where kimyasalUrunStok >=result"
                       int ifade1 = Integer.valueOf(sorgu6);     
                       int ifade2 = Integer.parseInt(sorgu6);
                       int ifade3 = Integer.valueOf(adet);
                       int ifade4 = Integer.parseInt(adet);
                        
                if(ifade2>=ifade4)                  
                {
                    System.out.println("satış yapılır.");   
                }
                else if(ifade4<ifade2){
                    System.out.println("stok yeterli değil");
                  String sorgu7 =  "Select MIN(satisFiyati) From tedarikciurunbilgileri where miktar!=0";
                  

                }
            }                 
            }
                 }
                   catch(SQLException ex){
             Logger.getLogger(Islemler.class.getName()).log(Level.SEVERE, null, ex);
                  
        }
         
     }
*/