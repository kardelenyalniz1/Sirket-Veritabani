
package veritabaniprojesi;

public class Tedarikci {
 private int firmaID;
 private String uretilenMaddeler;
 private int miktar;
 private String uretimTarihi;
 private int rafOmru;
 private int satisFiyati;
 private String firmaAdi,sehir;

    public Tedarikci(int firmaID, String uretilenMaddeler, int miktar, String uretimTarihi, int rafOmru, int satisFiyati) {
        this.firmaID = firmaID;
        this.uretilenMaddeler = uretilenMaddeler;
        this.miktar = miktar;
        this.uretimTarihi = uretimTarihi;
        this.rafOmru = rafOmru;
        this.satisFiyati = satisFiyati;
    }
     public Tedarikci(int firmaID,String firmaAdi,String sehir) {
       this.firmaID = firmaID;
       this.firmaAdi = firmaAdi;
       this.sehir = sehir;
    }
    public String getFirmaAdi() {
        return firmaAdi;
    }
    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }
    public String getSehir() {
        return sehir;
    }
    public void setSehir(String sehir) {
        this.sehir = sehir;
    }
    public int getFirmaID() {
        return firmaID;
    }
    public void setFirmaID(int firmaID) {
        this.firmaID = firmaID;
    }
    public String getUretilenMaddeler() {
        return uretilenMaddeler;
    }
    public void setUretilenMaddeler(String uretilenMaddeler) {
        this.uretilenMaddeler = uretilenMaddeler;
    }
    public int getMiktar() {
        return miktar;
    }
    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }
    public String getUretimTarihi() {
        return uretimTarihi;
    }
    public void setUretimTarihi(String uretimTarihi) {
        this.uretimTarihi = uretimTarihi;
    }
    public int getRafOmru() {
        return rafOmru;
    }
    public void setRafOmru(int rafOmru) {
        this.rafOmru = rafOmru;
    }
    public int getSatisFiyati() {
        return satisFiyati;
    }
    public void setSatisFiyati(int satisFiyati) {
        this.satisFiyati = satisFiyati;
    }   
}
