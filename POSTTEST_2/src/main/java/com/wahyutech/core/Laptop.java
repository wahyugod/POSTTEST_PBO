package com.wahyutech.core;

public class Laptop {
    private String id;
    private String merk;
    private String tipe;
    protected double harga;
    int stok;

    public Laptop() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMerk() { return merk; }
    public void setMerk(String merk) { this.merk = merk; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) {
        if (harga < 0) {
            System.out.println("Harga tidak boleh negatif.");
            this.harga = 0;
        } else {
            this.harga = harga;
        }
    }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tampilkanInfo() {
        System.out.printf("| %-5s | %-10s | %-20s | Rp %-12.2f | %-4d |\n", id, merk, tipe, harga, stok);
    }
}