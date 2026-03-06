import java.util.ArrayList;
import java.util.Scanner;

class Laptop {
    String id;
    String merk;
    String tipe;
    double harga;
    int stok;

    Laptop() {}

    Laptop(String id, String merk, String tipe, double harga, int stok) {
        this.id = id;
        this.merk = merk;
        this.tipe = tipe;
        this.harga = harga;
        this.stok = stok;
    }

    void tampilkanInfo() {
        System.out.printf("| %-5s | %-10s | %-20s | Rp %-12.2f | %-4d |\n", id, merk, tipe, harga, stok);
    }
}

public class WahyuTechApp {

    void main() {
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
            return; // Menghentikan proses tambah data dan kembali ke menu
        }
        
        System.out.print("Merk         : ");
        String merk = scanner.nextLine();
        System.out.print("Tipe/Model   : ");
        String tipe = scanner.nextLine();
        System.out.print("Harga (Rp)   : ");
        double harga = scanner.nextDouble();
        System.out.print("Stok         : ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        Laptop laptopBaru = new Laptop(id, merk, tipe, harga, stok);
        daftarLaptop.add(laptopBaru);
        System.out.println("Data laptop berhasil ditambahkan!");
    }

    void tampilkanLaptop(ArrayList<Laptop> daftarLaptop) {
        System.out.println("\n-- Daftar Produk Laptop WahyuTech --");
        if (daftarLaptop.isEmpty()) {
            System.out.println("Belum ada data laptop.");
            return;
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-20s | %-15s | %-4s |\n", "ID", "Merk", "Tipe", "Harga", "Stok");
        System.out.println("-----------------------------------------------------------------------");
        for (Laptop laptop : daftarLaptop) {
            laptop.tampilkanInfo();
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    void updateLaptop(ArrayList<Laptop> daftarLaptop, Scanner scanner) {
        System.out.println("\n============================================");
        System.out.println("|            Update Data Laptop            |");
        System.out.println("============================================");
        if (daftarLaptop.isEmpty()) {
            System.out.println("Data masih kosong.");
            return;
        }

        System.out.print("Masukkan ID Laptop yang ingin diupdate: ");
        String idCari = scanner.nextLine();
        boolean ditemukan = false;

        for (Laptop laptop : daftarLaptop) {
            if (laptop.id.equalsIgnoreCase(idCari)) {
                System.out.println("Data ditemukan! Masukkan data baru (kosongkan jika tidak ingin diubah):");

                System.out.print("Merk baru [" + laptop.merk + "]: ");
                String merk = scanner.nextLine();
                if (!merk.isEmpty()) laptop.merk = merk;

                System.out.print("Tipe baru [" + laptop.tipe + "]: ");
                String tipe = scanner.nextLine();
                if (!tipe.isEmpty()) laptop.tipe = tipe;

                System.out.print("Harga baru [" + laptop.harga + "]: ");
                String hargaStr = scanner.nextLine();
                if (!hargaStr.isEmpty()) laptop.harga = Double.parseDouble(hargaStr);

                System.out.print("Stok baru [" + laptop.stok + "]: ");
                String stokStr = scanner.nextLine();
                if (!stokStr.isEmpty()) laptop.stok = Integer.parseInt(stokStr);

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

        System.out.print("Masukkan ID Laptop yang ingin dihapus: ");
        String idCari = scanner.nextLine();
        boolean dihapus = false;

        for (int i = 0; i < daftarLaptop.size(); i++) {
            if (daftarLaptop.get(i).id.equalsIgnoreCase(idCari)) {
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
            if (laptop.id.equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }
}