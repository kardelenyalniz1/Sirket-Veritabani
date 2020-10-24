
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
    
     
    /**
     * @return the uretilenMaddeler
     */
    public String getUretilenMaddeler() {
        return uretilenMaddeler;
    }

    /**
     * @param uretilenMaddeler the uretilenMaddeler to set
     */
    public void setUretilenMaddeler(String uretilenMaddeler) {
        this.uretilenMaddeler = uretilenMaddeler;
    }

    /**
     * @return the miktar
     */
    public int getMiktar() {
        return miktar;
    }

    /**
     * @param miktar the miktar to set
     */
    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    /**
     * @return the uretimTarihi
     */
    public Date getUretimTarihi() {
        return uretimTarihi;
    }

    /**
     * @param uretimTarihi the uretimTarihi to set
     */
    public void setUretimTarihi(Date uretimTarihi) {
        this.uretimTarihi = uretimTarihi;
    }

    /**
     * @return the rafOmru
     */
    public int getRafOmru() {
        return rafOmru;
    }

    /**
     * @param rafOmru the rafOmru to set
     */
    public void setRafOmru(int rafOmru) {
        this.rafOmru = rafOmru;
    }

    /**
     * @return the satisFiyati
     */
    public float getSatisFiyati() {
        return satisFiyati;
    }

    /**
     * @param satisFiyati the satisFiyati to set
     */
    public void setSatisFiyati(float satisFiyati) {
        this.satisFiyati = satisFiyati;
    }

    /**
     * @return the firmaID
     */
    public int getFirmaID() {
        return firmaID;
    }

    /**
     * @param firmaID the firmaID to set
     */
    public void setFirmaID(int firmaID) {
        this.firmaID = firmaID;
    }
    
    
}
