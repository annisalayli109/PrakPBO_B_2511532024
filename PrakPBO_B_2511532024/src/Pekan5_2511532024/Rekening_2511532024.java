package Pekan5_2511532024;

import java.util.ArrayList;


public class Rekening_2511532024 {
	private String nomorRekening;
	private String namaPemilik;
	private String pin;
	
	// Implementasi Asosiasi (l-to-many)
	protected double saldo;
	protected ArrayList<Transaksi_2511532024> riwayatTransaksi;
	
	public Rekening_2511532024(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		// validasi PIN di dalam constructor
		if (pinAwal.length() == 6) {
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan: PIN harus 6 digit! menggunakan PIN default 123456");
			this.pin = "123456";
		}
		
		this.riwayatTransaksi = new ArrayList<>();
		
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
	}
	
	public String getNomorRekening() {
		return nomorRekening;
	}
	public String getNamaPemilik() {
		return namaPemilik;
	}
	
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
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
	
	public boolean tarikTunai(double nominal) {
		if (nominal <= 0) {
			System.out.println("Transaksi Gagal: Nominal tarik harus lebih dari 0!");
			return false;
		} else if (nominal > saldo){
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. saldo anda Rp" + saldo );
			return false;
		} else {
			saldo -= nominal;
			
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi_2511532024 trxBaru = new Transaksi_2511532024(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
			return true;
		}
	}
	
	public void transfer(Rekening_2511532024 tujuan, double nominal, String pinInput) {
		if (!otentikasi(pinInput)) {
			System.out.println("Transfer dibatalkan: PIN salah!");
			return;
		}
		
		boolean berhasil = this.tarikTunai(nominal);
		
		if (berhasil) {
			tujuan.setorTunai(nominal);
			System.out.println("Transfer Rp" + nominal + " ke rekening" + tujuan.getNomorRekening() + " berhasil.");
		} else {
			System.out.println("Transfer Gagal: penarikan dari rekening asal tidak berhasil.");
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
