package com.wahyutech.core;

public abstract class Laptop implements LaptopOperable {
    private String id;
    private String merk;
    private String tipe;
    protected double harga;
    int stok;

    public Laptop() {}

    public Laptop(String id, String merk, String tipe, double harga, int stok) {
        this.id = id;
        this.merk = merk;
        this.tipe = tipe;
        setHarga(harga);
        setStok(stok);
    }

    public Laptop(String id, String merk, String tipe, double harga, int stok, double diskonPersen) {
        this.id = id;
        this.merk = merk;
        this.tipe = tipe;
        setHarga(harga, diskonPersen);
        setStok(stok);
    }

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

    public void setHarga(double harga, double diskonPersen) {
        if (diskonPersen < 0 || diskonPersen > 100) {
            System.out.println("Diskon harus di antara 0 sampai 100.");
            setHarga(harga);
            return;
        }

        double hargaSetelahDiskon = harga - (harga * diskonPersen / 100.0);
        setHarga(hargaSetelahDiskon);
    }

    public int getStok() { return stok; }
    public void setStok(int stok) {
        if (stok < 0) {
            System.out.println("Stok tidak boleh negatif.");
            this.stok = 0;
            return;
        }
        this.stok = stok;
    }

    public void tambahStok(int jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah tambah stok harus lebih dari 0.");
            return;
        }
        this.stok += jumlah;
    }

    public void tambahStok(int jumlah, int bonusUnit) {
        if (bonusUnit < 0) {
            System.out.println("Bonus unit tidak boleh negatif.");
            return;
        }
        tambahStok(jumlah + bonusUnit);
    }

    public abstract String getKategori();

    public abstract double hitungHargaJual();

    public void tampilkanInfo() {
        System.out.printf("| %-5s | %-10s | %-20s | %-10s | Rp %-14.2f | Rp %-17.2f | %-4d |\n",
                id, merk, tipe, getKategori(), getHarga(), hitungHargaJual(), stok);
    }
}