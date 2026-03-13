package com.wahyutech.app;

import com.wahyutech.core.Laptop;

public class LaptopGaming extends Laptop {

    public LaptopGaming(String id, String merk, String tipe, double harga, int stok) {
        setId(id);
        setMerk(merk);
        setTipe(tipe);
        this.harga = harga;
        setStok(stok);
    }
}