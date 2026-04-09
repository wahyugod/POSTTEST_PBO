package com.wahyutech.app;

import com.wahyutech.core.Laptop;

public class LaptopGaming extends Laptop {

    public LaptopGaming(String id, String merk, String tipe, double harga, int stok) {
        super(id, merk, tipe, harga, stok);
    }

    public LaptopGaming(String id, String merk, String tipe, double harga, int stok, double diskonPersen) {
        super(id, merk, tipe, harga, stok, diskonPersen);
    }

    @Override
    public String getKategori() {
        return "Gaming";
    }

    @Override
    public double hitungHargaJual() {
        return getHarga() * 1.12;
    }
}