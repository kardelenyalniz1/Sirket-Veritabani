
package veritabaniprojesi;

import java.sql.Date;


public class urunBilgileri {
    private String uretilenMaddeler;
    private int miktar;
    private Date uretimTarihi;
    private int rafOmru;
    private float satisFiyati;
    private int firmaID;

    public urunBilgileri(String uretilenMaddeler, int miktar, Date uretimTarihi, int rafOmru, float satisFiyati, int firmaID) {
        this.uretilenMaddeler = uretilenMaddeler;
        this.miktar = miktar;
        this.uretimTarihi = uretimTarihi;
        this.rafOmru = rafOmru;
        this.satisFiyati = satisFiyati;
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
    public Date getUretimTarihi() {
        return uretimTarihi;
    }
    public void setUretimTarihi(Date uretimTarihi) {
        this.uretimTarihi = uretimTarihi;
    }
    public int getRafOmru() {
        return rafOmru;
    }
    public void setRafOmru(int rafOmru) {
        this.rafOmru = rafOmru;
    }
    public float getSatisFiyati() {
        return satisFiyati;
    }
    public void setSatisFiyati(float satisFiyati) {
        this.satisFiyati = satisFiyati;
    }
    public int getFirmaID() {
        return firmaID;
    }
    public void setFirmaID(int firmaID) {
        this.firmaID = firmaID;
    }
  
}
