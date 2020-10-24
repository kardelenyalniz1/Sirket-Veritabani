
package veritabaniprojesi;

public class Musteri {
    private int MusteriId;
    private String MusteriAdı;
    private String MusteriSoyadı;
    private String adresbilgileri;
    private String talepurun;

    public Musteri(int MusteriId, String MusteriAdı, String MusteriSoyadı, String adresbilgileri, String talepurun) {
        this.MusteriId = MusteriId;
        this.MusteriAdı = MusteriAdı;
        this.MusteriSoyadı = MusteriSoyadı;
        this.adresbilgileri = adresbilgileri;
        this.talepurun = talepurun;
    }
    public int getMusteriId() {
        return MusteriId;
    }
    public void setMusteriId(int MusteriId) {
        this.MusteriId = MusteriId;
    }
    public String getMusteriAdı() {
        return MusteriAdı;
    }
    public void setMusteriAdı(String MusteriAdı) {
        this.MusteriAdı = MusteriAdı;
    }
    public String getMusteriSoyadı() {
        return MusteriSoyadı;
    }
    public void setMusteriSoyadı(String MusteriSoyadı) {
        this.MusteriSoyadı = MusteriSoyadı;
    }
    public String getAdresbilgileri() {
        return adresbilgileri;
    }
    public void setAdresbilgileri(String adresbilgileri) {
        this.adresbilgileri = adresbilgileri;
    }
    public String getTalepurun() {
        return talepurun;
    }
    public void setTalepurun(String talepurun) {
        this.talepurun = talepurun;
    } 
}
