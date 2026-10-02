package Pekan4_2511532024;

public class RekeningGiro extends Rekening_2511532024 {
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		// memanggil inisialisasi dasar dari super class
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	// getter khusus giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
}
