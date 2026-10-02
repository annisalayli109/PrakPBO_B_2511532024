package Pekan4_2511532024;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_2511532024 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening_2511532024> daftarRekening = new ArrayList<>();
		Rekening_2511532024 akunAktif = null; // objek sebelum diinisialisasi (null)
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Cek Mutasi (Riwayat)");
			System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
			System.out.println("0. Keluar");
			System.out.print("Pilih Menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); // membersihkan buffer enter
			
			switch (pilihan) {
				case 1:
					System.out.print("Masukkan No Rekening: ");
					String no = input.nextLine();
					
					System.out.print("Masukkan Nama Pemilik: ");
					String nama = input.nextLine();
					
					System.out.print("Masukkan Saldo Awal: ");
					double saldo = input.nextDouble();
					input.nextLine();
					
					System.out.print("Masukkan PIN (6 Digit): ");
					String pin = input.nextLine();
					
					if (pin.length() != 6) {
						System.out.println("PIN harus terdiri dari 6 Digit!");
						break;
					}
					
					System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis | 3. Rekening VIP");
					System.out.print("Pilihan: ");
					int produk = input.nextInt();
					input.nextLine();
					
					Rekening_2511532024 rekeningBaru;
					if (produk == 1) {
						System.out.print("Suku Bunga (%): ");
						double sukuBunga = input.nextDouble();
						input.nextLine();
						rekeningBaru = new RekeningTabungan(no, nama, saldo, pin, sukuBunga);
					} else if (produk == 2) {
						System.out.print("Masukkan Batas Overdraft: ");
						double batasOverdraft = input.nextDouble();
						input.nextLine();
						rekeningBaru = new RekeningGiro(no, nama, saldo, pin, batasOverdraft);
					} else if (produk == 3) {
						rekeningBaru = new RekeningVIP(no, nama, saldo, pin);
					} else {
						System.out.println("Pilihan produk tidak valid!");
						break;
					}
		
					daftarRekening.add(rekeningBaru);
					akunAktif = rekeningBaru;
					System.out.println("Rekening berhasil ditambahkan.");
					break;
					
				case 2:
					if (akunAktif == null) {
						System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
					} else {
						System.out.print("Masukkan nominal setor: ");
						double setor = input.nextDouble();
						akunAktif.setorTunai(setor); // Memanggil Behavior / method
					}
					break;
						
				case 3:
					if (akunAktif == null) {
						System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
					} else {
						System.out.print("Masukkan PIN: ");
						String pinTarik = input.nextLine();
						if (akunAktif.otentikasi(pinTarik)) {
							System.out.print("Masukkan nominal tarik tunai: ");
							double tarik = input.nextDouble();
							input.nextLine();
							
							akunAktif.tarikTunai(tarik); // Memanggil Behavior / method
						} else {
							System.out.println("Akses ditolak: PIN yang anda masukkan salah!");
						}
					}
					break;
					
				case 4:
					if (akunAktif == null) {
						System.out.println("Error: Anda belum membuka rekening!");
					} else {
						akunAktif.cekInformasi();
					}
					break;
					
				case 5:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Error: Belum ada rekening yang tersedia!");
                    } else {
                        System.out.print("Masukkan nomor rekening yang ingin digunakan: ");
                        String nomorCari = input.nextLine();
                        boolean ditemukan = false;

                        for (Rekening_2511532024 rekening : daftarRekening) {
                            if (rekening.getNomorRekening().equals(nomorCari)) {
                                akunAktif = rekening;
                                ditemukan = true;
                                System.out.println(
                                        "Akun berhasil diganti ke rekening " + rekening.getNomorRekening());
                                break;
                            }
                        }
                        if (!ditemukan) {
                            System.out.println("Error: Nomor rekening tidak ditemukan!");
                        }
                    }
                    break;
                    
				case 6:
					System.out.print("Masukkan PIN: ");
					String pinMutasi = input.nextLine();
					if (akunAktif.otentikasi(pinMutasi)) {
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses ditolak PIN yang anda masukkan salah!");
					}
					break;
					
				case 7:
					if (akunAktif instanceof RekeningTabungan ) {
						RekeningTabungan tab = (RekeningTabungan) akunAktif;
						tab.tambahBungaAkhirBulan();
					} else {
						System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan");
					}
					break;
					
				case 0:
					isRunning = false;
					System.out.println("Sistem ditutup. Terima kasih!");
					break;
					
				default:
					System.out.println("Pilihan tidak valid!");	
			}
		}
		input.close();
	}
}
