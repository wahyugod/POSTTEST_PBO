package com.wahyutech.app;

import com.wahyutech.core.Laptop;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Scanner;

public class WahyuTechApp {

    public static void main(String[] args) {
        new WahyuTechApp().run();
    }

    void run() {
        ArrayList<Laptop> daftarLaptop = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n============================================");
            System.out.println("| Sistem Manajemen Produk Laptop WahyuTech |");
            System.out.println("============================================");
            System.out.println("| 1. Tambah Data Laptop (Create)           |");
            System.out.println("| 2. Tampilkan Data Laptop (Read)          |");
            System.out.println("| 3. Update Data Laptop (Update)           |");
            System.out.println("| 4. Hapus Data Laptop (Delete)            |");
            System.out.println("| 5. Keluar                                |");
            System.out.println("============================================");
            System.out.print("Pilih menu (1-5): ");

            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahLaptop(daftarLaptop, scanner);
                case 2 -> tampilkanLaptop(daftarLaptop);
                case 3 -> updateLaptop(daftarLaptop, scanner);
                case 4 -> hapusLaptop(daftarLaptop, scanner);
                case 5 -> System.out.println("Terima kasih telah menggunakan sistem WahyuTech!");
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);
    }

    void tambahLaptop(ArrayList<Laptop> daftarLaptop, Scanner scanner) {
        System.out.println("\n============================================");
        System.out.println("|            Tambah Data Laptop            |");
        System.out.println("============================================");
        System.out.print("ID Laptop    : ");
        String id = scanner.nextLine();

        if (!validasiId(daftarLaptop, id)) {
            System.out.println("ID sudah ada. Silakan gunakan ID lain.");
            return;
        }

        System.out.print("Merk         : ");
        String merkRaw = scanner.nextLine();
        String merk = StringUtils.capitalize(merkRaw);

        System.out.print("Tipe/Model   : ");
        String tipe = scanner.nextLine();

        System.out.println("Jenis Laptop :");
        System.out.println("1. Gaming");
        System.out.println("2. Bisnis");
        System.out.print("Pilih jenis (1-2): ");
        int jenis = scanner.nextInt();

        if (jenis != 1 && jenis != 2) {
            System.out.println("Jenis tidak valid. Data batal ditambahkan.");
            return;
        }

        System.out.print("Harga (Rp)   : ");
        double harga = scanner.nextDouble();
        System.out.print("Stok         : ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        Laptop laptopBaru;
        if (jenis == 1) {
            laptopBaru = new LaptopGaming(id, merk, tipe, harga, stok);
        } else if (jenis == 2) {
            laptopBaru = new LaptopBisnis(id, merk, tipe, harga, stok);
        } else {
            System.out.println("Invalid.");
            return;
        }

        daftarLaptop.add(laptopBaru);
        System.out.println("Data laptop berhasil ditambahkan!");
    }

    void tampilkanLaptop(ArrayList<Laptop> daftarLaptop) {
        System.out.println("\n-- Daftar Produk Laptop WahyuTech --");
        if (daftarLaptop.isEmpty()) {
            System.out.println("Belum ada data laptop.");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-20s | %-10s | %-15s | %-4s |\n", "ID", "Merk", "Tipe", "Kategori", "Harga", "Stok");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Laptop laptop : daftarLaptop) {
            laptop.tampilkanInfo();
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    void updateLaptop(ArrayList<Laptop> daftarLaptop, Scanner scanner) {
        System.out.println("\n============================================");
        System.out.println("|            Update Data Laptop            |");
        System.out.println("============================================");
        if (daftarLaptop.isEmpty()) {
            System.out.println("Data masih kosong.");
            return;
        }
        tampilkanLaptop(daftarLaptop);
        System.out.print("Masukkan ID Laptop yang ingin diupdate: ");
        String idCari = scanner.nextLine();
        boolean ditemukan = false;

        for (int i = 0; i < daftarLaptop.size(); i++) {
            Laptop laptop = daftarLaptop.get(i);
            if (laptop.getId().equalsIgnoreCase(idCari)) {
                System.out.println("Data ditemukan! Masukkan data baru (kosongkan jika tidak ingin diubah):");

                String id = laptop.getId();

                System.out.print("Merk baru [" + laptop.getMerk() + "]: ");
                String merk = scanner.nextLine();
                String merkBaru = merk.isEmpty() ? laptop.getMerk() : StringUtils.capitalize(merk);

                System.out.print("Tipe baru [" + laptop.getTipe() + "]: ");
                String tipe = scanner.nextLine();
                String tipeBaru = tipe.isEmpty() ? laptop.getTipe() : tipe;

                int jenisLama = laptop instanceof LaptopGaming ? 1 : 2;
                System.out.println("Jenis lama [" + laptop.getKategori() + "]:");
                System.out.println("1. Gaming");
                System.out.println("2. Bisnis");
                System.out.print("Jenis baru (kosongkan jika tidak diubah): ");
                String jenisStr = scanner.nextLine();

                int jenisBaru = jenisLama;
                if (!jenisStr.isEmpty()) {
                    if ("1".equals(jenisStr) || "2".equals(jenisStr)) {
                        jenisBaru = Integer.parseInt(jenisStr);
                    } else {
                        System.out.println("Jenis tidak valid. Update dibatalkan.");
                        return;
                    }
                }

                System.out.print("Harga baru [" + laptop.getHarga() + "]: ");
                String hargaStr = scanner.nextLine();
                double hargaBaru = hargaStr.isEmpty() ? laptop.getHarga() : Double.parseDouble(hargaStr);

                System.out.print("Stok baru [" + laptop.getStok() + "]: ");
                String stokStr = scanner.nextLine();
                int stokBaru = stokStr.isEmpty() ? laptop.getStok() : Integer.parseInt(stokStr);

                Laptop laptopBaru;
                if (jenisBaru == 1) {
                    laptopBaru = new LaptopGaming(id, merkBaru, tipeBaru, hargaBaru, stokBaru);
                } else {
                    laptopBaru = new LaptopBisnis(id, merkBaru, tipeBaru, hargaBaru, stokBaru);
                }

                daftarLaptop.set(i, laptopBaru);

                System.out.println("Data berhasil diupdate!");
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Laptop dengan ID " + idCari + " tidak ditemukan.");
        }
    }

    void hapusLaptop(ArrayList<Laptop> daftarLaptop, Scanner scanner) {
        System.out.println("\n============================================");
        System.out.println("|             Hapus Data Laptop            |");
        System.out.println("============================================");
        if (daftarLaptop.isEmpty()) {
            System.out.println("Data masih kosong.");
            return;
        }
        tampilkanLaptop(daftarLaptop);
        System.out.print("Masukkan ID Laptop yang ingin dihapus: ");
        String idCari = scanner.nextLine();
        boolean dihapus = false;

        for (int i = 0; i < daftarLaptop.size(); i++) {
            if (daftarLaptop.get(i).getId().equalsIgnoreCase(idCari)) {
                daftarLaptop.remove(i);
                System.out.println("Data laptop berhasil dihapus!");
                dihapus = true;
                break;
            }
        }

        if (!dihapus) {
            System.out.println("Laptop dengan ID " + idCari + " tidak ditemukan.");
        }
    }

    boolean validasiId(ArrayList<Laptop> daftarLaptop, String id) {
        for (Laptop laptop : daftarLaptop) {
            if (laptop.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }
}