package com.example.proje2;

import java.util.ArrayList;

// interface
interface ITakipEdilebilir {
    void isAlaniniTakipEt(IsAlani isAlani, String sosyalMedyaHesabi);
    ArrayList<TakipEdilenSosyalMedya> getTakipEdilenIsAlanlari();
    void setTakipEdilenIsAlanlari(ArrayList<TakipEdilenSosyalMedya> takipEdilenIsAlanlari);
}

public class Kullanici implements ITakipEdilebilir {
    private String kullaniciAdi;
    private String sifre;
    private String email;
    private ArrayList<TakipEdilenSosyalMedya> takipEdilenIsAlanlari;

    public Kullanici(String kullaniciAdi, String sifre, String email) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.email = email;
        this.takipEdilenIsAlanlari = new ArrayList<>();
    }

    // ITakipEdilebilir interface'ını uygulanan metotlar
    @Override
    public void isAlaniniTakipEt(IsAlani isAlani, String sosyalMedyaHesabi) {
        TakipEdilenSosyalMedya takipEdilen = new TakipEdilenSosyalMedya(isAlani, sosyalMedyaHesabi);
        takipEdilenIsAlanlari.add(takipEdilen);
    }

    @Override
    public ArrayList<TakipEdilenSosyalMedya> getTakipEdilenIsAlanlari() {
        return new ArrayList<>(takipEdilenIsAlanlari);
    }

    @Override
    public void setTakipEdilenIsAlanlari(ArrayList<TakipEdilenSosyalMedya> takipEdilenIsAlanlari) {
        this.takipEdilenIsAlanlari = new ArrayList<>(takipEdilenIsAlanlari);
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
