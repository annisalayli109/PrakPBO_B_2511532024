package Pekan4_2511532024;

public class RekeningTabungan extends Rekening_2511532024 {
	private double sukuBunga;
	
	public RekeningTabungan(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
	}
	
	public void tambahBungaAkhirBulan() {
		// menghitung bunga
		// mengapa bisa mengakses saldo secara langsung dari class rekeningtabungan
		double nominalBunga = saldo * (sukuBunga / 100);
		saldo += nominalBunga;
		
		// mencatat riwayat transaksi
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi_2511532024(idTrx, "Bunga", nominalBunga));
		
		System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp" + nominalBunga);
	}
}
