package veritabaniprojesi;

class Siparis {
    private int firmaID;
    private int kimyasalUrunId;
    private String kimyasalUrunAdı;
    private int kimyasalUrunStok;
    private int hammaddeId;
    private int alısmaliyeti;
    private String hammmaddeAdı;
    private int stokdurumu;
    
    public Siparis(int hammaddeId, int alısmaliyeti) {
        this.hammaddeId = hammaddeId;
        this.alısmaliyeti = alısmaliyeti;
    }
    public Siparis(int hammaddeId, String hammmaddeAdı, int stokdurumu) {
        this.hammaddeId = hammaddeId;
        this.hammmaddeAdı = hammmaddeAdı;
        this.stokdurumu = stokdurumu;
    }
     public Siparis(int firmaID, int kimyasalUrunUd, String kimyasalUrunAdı, int kimyasalUrunStok) {
        this.firmaID = firmaID;
        this.kimyasalUrunId = kimyasalUrunUd;
        this.kimyasalUrunAdı = kimyasalUrunAdı;
        this.kimyasalUrunStok = kimyasalUrunStok;    
    }   
    public int getFirmaID() {
        return firmaID;
    }
    public void setFirmaID(int firmaID) {
        this.firmaID = firmaID;
    }
    public int getKimyasalUrunId() {
        return kimyasalUrunId;
    }
    public void setKimyasalUrunId(int kimyasalUrunUd) {
        this.kimyasalUrunId = kimyasalUrunUd;
    }
    public String getKimyasalUrunAdı() {
        return kimyasalUrunAdı;
    }
    public void setKimyasalUrunAdı(String kimyasalUrunAdı) {
        this.kimyasalUrunAdı = kimyasalUrunAdı;
    }
    public int getKimyasalUrunStok() {
        return kimyasalUrunStok;
    }
    public void setKimyasalUrunStok(int kimyasalUrunStok) {
        this.kimyasalUrunStok = kimyasalUrunStok;
    }
    public int getHammaddeId() {
        return hammaddeId;
    }
    public void setHammaddeId(int hammaddeId) {
        this.hammaddeId = hammaddeId;
    }

    public int getAlısmaliyeti() {
        return alısmaliyeti;
    }
    public void setAlısmaliyeti(int alısmaliyeti) {
        this.alısmaliyeti = alısmaliyeti;
    }
    public String getHammmaddeAdı() {
        return hammmaddeAdı;
    }
    public void setHammmaddeAdı(String hammmaddeAdı) {
        this.hammmaddeAdı = hammmaddeAdı;
    }
    public int getStokdurumu() {
        return stokdurumu;
    }
    public void setStokdurumu(int stokdurumu) {
        this.stokdurumu = stokdurumu;
    }
}
