package com.example.proje2;

public class TakipEdilenSosyalMedya {
    private IsAlani isAlani;
    private String sosyalMedyaHesabi;

    public TakipEdilenSosyalMedya(IsAlani isAlani, String sosyalMedyaHesabi) {
        this.isAlani = isAlani;
        this.sosyalMedyaHesabi = sosyalMedyaHesabi;
    }

    public IsAlani getIsAlani() {
        return isAlani;
    }

    public void setIsAlani(IsAlani isAlani) {
        this.isAlani = isAlani;
    }

    public String getSosyalMedyaHesabi() {
        return sosyalMedyaHesabi;
    }

    public void setSosyalMedyaHesabi(String sosyalMedyaHesabi) {
        this.sosyalMedyaHesabi = sosyalMedyaHesabi;
    }

}
