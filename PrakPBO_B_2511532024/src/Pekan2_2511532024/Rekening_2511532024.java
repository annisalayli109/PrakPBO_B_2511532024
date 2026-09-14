package Pekan2_2511532024;

import java.util.ArrayList;

public class Rekening_2511532024 {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	// Implementasi Asosiasi (l-to-many)
	ArrayList<Transaksi_2511532024> riwayatTransaksi;
	
	public Rekening_2511532024(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		
		this.riwayatTransaksi = new ArrayList<>();
		
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			// merekam riwayat (pembuatan objek transaksi didalam method
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi_2511532024 trxBaru = new Transaksi_2511532024(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	
	public void tarikTunai(double nominal) {
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal: Minimal penarikan RP10.000");
		} else if (nominal > saldo){
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. saldo anda Rp" + saldo );
		} else {
			saldo -= nominal;
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi_2511532024 trxBaru = new Transaksi_2511532024(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		}
	}
	
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  : Rp" + saldo);
		System.out.println("---------------------");
	}
	
	public void cetakMutasi() {
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini");
		} else {
			System.out.println("--- MUTASI REKENING ---");
			for (Transaksi_2511532024 transaksi : riwayatTransaksi) {
				transaksi.cetakDetail();
			}
		}
	}
}
