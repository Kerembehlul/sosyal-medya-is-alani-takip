package com.example.proje2;

import java.util.ArrayList;

public class IsAlani {
    private String isAlaniAdi;
    private String kategori;
    private ArrayList<Integer> sayilar;

    public IsAlani(String isAlaniAdi, String kategori) {
        this.isAlaniAdi = isAlaniAdi;
        this.kategori = kategori;
        this.sayilar = new ArrayList<>();
    }

    public void sayiEkle(int sayi) {
        sayilar.add(sayi);
    }

    // Overloading: Farklı tipte bir sayı ekleme
    public void sayiEkle(double sayi) {
        sayilar.add((int) sayi);
    }

    public void sayiSil(int index) {
        if (index >= 0 && index < sayilar.size()) {
            sayilar.remove(index);
        }
    }

    public int toplam() {
        int toplam = 0;
        for (int sayi : sayilar) {
            toplam += sayi;
        }
        return toplam;
    }

    public double ortalama() {
        if (sayilar.isEmpty()) {
            return 0.0;
        }

        int toplam = toplam();
        return (double) toplam / sayilar.size();
    }

    public String getIsAlaniAdi() {
        return isAlaniAdi;
    }

    public void setIsAlaniAdi(String isAlaniAdi) {
        this.isAlaniAdi = isAlaniAdi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public ArrayList<Integer> getSayilar() {
        return sayilar;
    }

    public void setSayilar(ArrayList<Integer> sayilar) {
        this.sayilar = sayilar;
    }
}
