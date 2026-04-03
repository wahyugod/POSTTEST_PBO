package com.wahyutech.app;

import com.wahyutech.core.Laptop;

public class LaptopBisnis extends Laptop {

    public LaptopBisnis(String id, String merk, String tipe, double harga, int stok) {
        super(id, merk, tipe, harga, stok);
    }

    @Override
    public String getKategori() {
        return "Bisnis";
    }
}
