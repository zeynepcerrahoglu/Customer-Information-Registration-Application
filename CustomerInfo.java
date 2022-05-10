package proje2;

import java.util.ArrayList;

public class CustomerInfo {
    
    
    //Değişkenler

    //Müşerinin adlarını ve soyadını tutar
    private String adSoyad;

    //Müşterinin adresini tutar
    private String adres;

    //Müşterinin telefon numaralarını tutar
    private ArrayList<Object> list;



    //Constructorlar

    //Parametresiz constructor
    public CustomerInfo()
    {
        adSoyad = "";
        adres = "";
        list = new ArrayList<>();
    }

    //Parametreli contructor
    public CustomerInfo(String newAdSoyad, String newAdres, ArrayList<Object> newList)
    {
        setAdSoyad(newAdSoyad);
        setAdres(newAdres);
        setList(newList);
    }


    //Get ve Set Metodları

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }


    //toString metodu

    @Override
    public String toString()
    {
        return "ad soyad: " + adSoyad + "\nadres: " + adres + "\ntelefonlar: " + list;
    }
}
