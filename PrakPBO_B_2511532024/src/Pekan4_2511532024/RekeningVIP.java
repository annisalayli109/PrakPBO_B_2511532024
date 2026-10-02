package Pekan4_2511532024;

public class RekeningVIP extends Rekening_2511532024{
	private static final double Bonus_Awal = 100000;
	
	public RekeningVIP(String nomor, String nama, double saldoAwal, String pinAwal) {
		super(nomor, nama, saldoAwal, pinAwal);
		
		saldo += Bonus_Awal;
		
		String idTrx = "TRX-BONUS-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi_2511532024(idTrx, "Bonus VIP", Bonus_Awal));
		
		System.out.println("Selamat! Bonus VIP Rp" + Bonus_Awal + " Masuk ke rekening anda.");
	}
}
