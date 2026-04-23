import java.util.Scanner;

public class DaftarBukuPustaka {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList list = new LinkedList();

        int pilihan;

        do {
            System.out.println("\n===== SISTEM DATA BUKU =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); // buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Kode Buku: ");
                    String kode = input.nextLine();

                    // Validasi kode buku maksimal 5 karakter
                    if (kode.length() > 5) {
                        System.out.println("Kode buku maksimal 5 karakter!");
                        break;
                    }

                    System.out.print("Masukkan Judul: ");
                    String judul = input.nextLine();

                    System.out.print("Masukkan Penulis: ");
                    String penulis = input.nextLine();

                    list.tambahBuku(kode, judul, penulis);
                    break;

                case 2:
                    list.hapusBuku();
                    break;

                case 3:
                    System.out.print("Masukkan Kode Buku yang dicari: ");
                    String cari = input.nextLine();
                    list.cariBuku(cari);
                    break;

                case 4:
                    list.tampilkanBuku();
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


// Class Node untuk menyimpan data buku
class Node {
    String kodeBuku;
    String judul;
    String penulis;
    Node next;

    // Constructor
    public Node(String kodeBuku, String judul, String penulis) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.next = null;
    }
}
// Class Single Linked List
class LinkedList {
    Node head;

    // Tambah buku (Push ke akhir)
    public void tambahBuku(String kode, String judul, String penulis) {
        Node newNode = new Node(kode, judul, penulis);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Data berhasil ditambahkan!");
    }

    // Hapus buku terakhir (Pop)
    public void hapusBuku() {
        if (head == null) {
            System.out.println("Tidak ada data untuk dihapus.");
        } else if (head.next == null) {
            head = null;
            System.out.println("Data berhasil dihapus.");
        } else {
            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
            System.out.println("Data terakhir berhasil dihapus.");
        }
    }

    // Cari buku berdasarkan kode
    public void cariBuku(String kode) {
        Node temp = head;
        while (temp != null) {
            if (temp.kodeBuku.equals(kode)) {
                System.out.println("Buku ditemukan:");
                System.out.println("Kode: " + temp.kodeBuku);
                System.out.println("Judul: " + temp.judul);
                System.out.println("Penulis: " + temp.penulis);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Buku tidak ditemukan.");
    }

    // Tampilkan semua buku
    public void tampilkanBuku() {
        if (head == null) {
            System.out.println("Daftar buku kosong.");
            return;
        }

        Node temp = head;
        int count = 0;

        System.out.println("\nDaftar Buku:");
        while (temp != null) {
            System.out.println("Kode: " + temp.kodeBuku +
                    " | Judul: " + temp.judul +
                    " | Penulis: " + temp.penulis);
            temp = temp.next;
            count++;
        }
        System.out.println("Total Buku: " + count);
    }
}
