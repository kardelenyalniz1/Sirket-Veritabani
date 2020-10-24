
package veritabaniprojesi;
public class Urun {
    private int urunID;
    private String uretimTarihi;
    private int rafOmru;
    private int iscilik;
    private int maliyet;
    private int satisFiyati;

    public Urun(int urunID, String uretimTarihi, int rafOmru, int iscilik, int maliyet, int satisFiyati) {
        this.urunID = urunID;
        this.uretimTarihi = uretimTarihi;
        this.rafOmru = rafOmru;
        this.iscilik = iscilik;
        this.maliyet = maliyet;
        this.satisFiyati = satisFiyati;
    }

    public int getUrunID() {
        return urunID;
    }

    public void setUrunID(int urunID) {
        this.urunID = urunID;
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

    public int getIscilik() {
        return iscilik;
    }

    public void setIscilik(int iscilik) {
        this.iscilik = iscilik;
    }

    public int getMaliyet() {
        return maliyet;
    }

    public void setMaliyet(int maliyet) {
        this.maliyet = maliyet;
    }

    public int getSatisFiyati() {
        return satisFiyati;
    }

    public void setSatisFiyati(int satisFiyati) {
        this.satisFiyati = satisFiyati;
    }
    
    
}
