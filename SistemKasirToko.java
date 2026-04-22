import java.util.*;

class Pelanggan {
    String nomorAntrian;
    String nama;
    double totalBelanja;

    public Pelanggan(String nomorAntrian, String nama, double totalBelanja) {
        this.nomorAntrian = nomorAntrian;
        this.nama = nama;
        this.totalBelanja = totalBelanja;
    }
}

public class SistemKasirToko {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Queue<Pelanggan> antrian = new LinkedList<>();
        Stack<Pelanggan> riwayat = new Stack<>();

        int pilihan;

        do {
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); // buang newline

            switch (pilihan) {
                case 1:
                    if (antrian.size() >= 5) {
                        System.out.println("Antrian sudah memenuhi minimal 5 pelanggan.");
                    }

                    System.out.print("Masukkan Nomor Antrian: ");
                    String no = input.nextLine();

                    System.out.print("Masukkan Nama Pelanggan: ");
                    String nama = input.nextLine();

                    System.out.print("Masukkan Total Belanja: ");
                    double total = input.nextDouble();
                    input.nextLine();

                    antrian.add(new Pelanggan(no, nama, total));
                    System.out.println("Data pelanggan ditambahkan ke antrian!");
                    break;

                case 2:
                    if (antrian.isEmpty()) {
                        System.out.println("Antrian kosong!");
                    } else {
                        Pelanggan p = antrian.poll(); // dequeue
                        System.out.println("Melayani pelanggan " + p.nomorAntrian + " (" + p.nama + ")");
                        
                        riwayat.push(p); // push ke stack
                        System.out.println("Transaksi disimpan ke riwayat.");
                    }
                    break;

                case 3:
                    if (antrian.isEmpty()) {
                        System.out.println("Antrian kosong!");
                    } else {
                        System.out.println("=== ANTRIAN SAAT INI ===");
                        for (Pelanggan p : antrian) {
                            System.out.println(p.nomorAntrian + " - " + p.nama + " - Rp" + p.totalBelanja);
                        }
                    }
                    break;

                case 4:
                    if (riwayat.isEmpty()) {
                        System.out.println("Belum ada transaksi.");
                    } else {
                        System.out.println("=== RIWAYAT TRANSAKSI (Terbaru ke Lama) ===");
                        for (int i = riwayat.size() - 1; i >= 0; i--) {
                            Pelanggan p = riwayat.get(i);
                            System.out.println(p.nomorAntrian + " - " + p.nama + " - Rp" + p.totalBelanja);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 5);

        input.close();
    }
}