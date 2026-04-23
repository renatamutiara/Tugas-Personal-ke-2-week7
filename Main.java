import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue antrian = new Queue();
        Stack riwayat = new Stack();

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
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nomor Antrian: ");
                    String nomor = input.nextLine();

                    System.out.print("Masukkan Nama Pelanggan: ");
                    String nama = input.nextLine();

                    System.out.print("Masukkan Total Belanja: ");
                    int total = input.nextInt();
                    input.nextLine();

                    antrian.enqueue(nomor, nama, total);
                    break;

                case 2:
                    Node dilayani = antrian.dequeue();
                    if (dilayani != null) {
                        System.out.println("Melayani pelanggan " + dilayani.nomor + " (" + dilayani.nama + ")");
                        riwayat.push(dilayani);
                        System.out.println("Transaksi disimpan ke riwayat.");
                    }
                    break;

                case 3:
                    antrian.display();
                    break;

                case 4:
                    riwayat.display();
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
// Class Node untuk menyimpan data pelanggan
class Node {
    String nomor;
    String nama;
    int total;
    Node next;

    public Node(String nomor, String nama, int total) {
        this.nomor = nomor;
        this.nama = nama;
        this.total = total;
        this.next = null;
    }
}
// Queue untuk antrian pelanggan (FIFO)
class Queue {
    Node front, rear;
    int size = 0;

    // Enqueue (tambah antrian)
    public void enqueue(String nomor, String nama, int total) {
        if (size >= 5) {
            System.out.println("Antrian penuh! Maksimal 5 pelanggan.");
            return;
        }

        Node newNode = new Node(nomor, nama, total);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
        System.out.println("Data pelanggan ditambahkan ke antrian!");
    }

    // Dequeue (layani pelanggan)
    public Node dequeue() {
        if (front == null) {
            System.out.println("Antrian kosong!");
            return null;
        }

        Node temp = front;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return temp;
    }

    // Tampilkan antrian
    public void display() {
        if (front == null) {
            System.out.println("Antrian kosong.");
            return;
        }

        Node temp = front;
        System.out.println("\nDaftar Antrian:");
        while (temp != null) {
            System.out.println(temp.nomor + " - " + temp.nama + " - " + temp.total);
            temp = temp.next;
        }
    }
}
// Stack untuk riwayat transaksi (LIFO)
class Stack {
    Node top;

    // Push (simpan riwayat)
    public void push(Node data) {
        Node newNode = new Node(data.nomor, data.nama, data.total);
        newNode.next = top;
        top = newNode;
    }

    // Tampilkan riwayat
    public void display() {
        if (top == null) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        Node temp = top;
        System.out.println("\nRiwayat Transaksi (Terbaru -> Lama):");
        while (temp != null) {
            System.out.println(temp.nomor + " - " + temp.nama + " - " + temp.total);
            temp = temp.next;
        }
    }
}