package com.example.mobilprojem;

public class uyeBilgileri {
    private String uyeismi,uyekullaniciadi,uyesifre;

    public String getUyeismi() {
        return uyeismi;
    }

    public void setUyeismi(String uyeismi) {
        this.uyeismi = uyeismi;
    }

    public String getUyekullaniciadi() {
        return uyekullaniciadi;
    }

    public void setUyekullaniciadi(String uyekullaniciadi) {
        this.uyekullaniciadi = uyekullaniciadi;
    }

    public String getUyesifre() {
        return uyesifre;
    }

    public void setUyesifre(String uyesifre) {
        this.uyesifre = uyesifre;
    }

    @Override
    public String toString() {
        return "uyeBilgileri{" +
                "uyeismi='" + uyeismi + '\'' +
                ", uyekullaniciadi='" + uyekullaniciadi + '\'' +
                ", uyesifre='" + uyesifre + '\'' +
                '}';
    }
}
