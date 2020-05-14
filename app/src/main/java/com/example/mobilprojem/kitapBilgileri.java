package com.example.mobilprojem;

public class kitapBilgileri {
    private int kitapid;
    private String kitapadi,yazaradi,sayfasayisi,kitapturu,ozet;



    public kitapBilgileri(){
    }

    public kitapBilgileri(String kitapadi, String yazaradi, String sayfasayisi, String kitapturu, String ozet) {
        this.kitapadi = kitapadi;
        this.yazaradi = yazaradi;
        this.sayfasayisi = sayfasayisi;
        this.kitapturu = kitapturu;
        this.ozet = ozet;
    }

    public int getKitapid() {
        return kitapid;
    }

    public void setKitapid(int kitapid) {
        this.kitapid = kitapid;
    }

    public String getKitapadi() {
        return kitapadi;
    }

    public void setKitapadi(String kitapadi) {
        this.kitapadi = kitapadi;
    }

    public String getYazaradi() {
        return yazaradi;
    }

    public void setYazaradi(String yazaradi) {
        this.yazaradi = yazaradi;
    }

    public String getSayfasayisi() {
        return sayfasayisi;
    }

    public void setSayfasayisi(String sayfasayisi) {
        this.sayfasayisi = sayfasayisi;
    }

    public String getKitapturu() {
        return kitapturu;
    }

    public void setKitapturu(String kitapturu) {
        this.kitapturu = kitapturu;
    }

    public String getOzet() {
        return ozet;
    }

    public void setOzet(String ozet) {
        this.ozet = ozet;
    }

    @Override
    public String toString() {
        return "kitapBilgileri{" +
                "kitapid=" + kitapid +
                ", kitapadi='" + kitapadi + '\'' +
                ", yazaradi='" + yazaradi + '\'' +
                ", sayfasayisi='" + sayfasayisi + '\'' +
                ", kitapturu='" + kitapturu + '\'' +
                ", ozet='" + ozet + '\'' +
                '}';
    }
}
