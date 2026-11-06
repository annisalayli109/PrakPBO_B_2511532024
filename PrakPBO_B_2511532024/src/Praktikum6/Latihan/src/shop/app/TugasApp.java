package shop.app;

import java.util.List;

import shop.model.Product;
import shop.model.PhysicalProduct;
import shop.StudentInfo;
import shop.model.DigitalProduct;

import shop.shipping.ExpressShipping;

import shop.payment.PaymentMethod;
import shop.payment.EWalletPayment;
import shop.payment.BankTransferPayment;
import shop.payment.CreditCardPayment;

import shop.service.CheckoutService;

import shop.notification.*;

public class TugasApp {

    public static void main(String[] args) {
        Product coffee = new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300);
        Product rendang = new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000, 400);
        Product ebook = new DigitalProduct("EBK-003", "E-book Resep Kue Nusantara", 50_000, "https://toko-umkm.example/unduh/ebook-resep-kue-nusantara");

        List<Product> cart = List.of(coffee, rendang, ebook);

        CheckoutService checkout = new CheckoutService();
        ExpressShipping express = new ExpressShipping();

        EWalletPayment wallet = new EWalletPayment("DompetKu", 300_000);
        PaymentMethod transfer = new BankTransferPayment("Bank Nagari");
        CreditCardPayment card = new CreditCardPayment("Siti Rahma", 1_000_000);

        System.out.println("===== GRAND TOTAL =====");
        System.out.println("E-Wallet: Rp" + checkout.grandTotal(cart, express, wallet));
        System.out.println("Bank Transfer: Rp" + checkout.grandTotal(cart, express, transfer));
        System.out.println("Credit Card: Rp" + checkout.grandTotal(cart, express, card));

        System.out.println("\n===== PAYMENT POLICY =====");
        System.out.println("Credit Card: " + card.policy());
        System.out.println("E-Wallet: " + wallet.policy());

        NotificationService service = new NotificationService();

        service.register(new EmailChannel());
        service.register(new SmsChannel(StudentInfo.SMS_MAX_LENGTH));
        service.register(new WhatsAppChannel());
        service.register(new NotificationChannel() {

            @Override 
            public String channelName() {
                return "Console Log";
            }

            @Override 
            public boolean send(CustomerContact contact, String message) {
                System.out.println("[LOG] " + contact.getName() + ": " + message);
                return true;
            }
        });

        String message = "Pesanan Anda telah berubah status menjadi "
            + "sedang diproses. Tim Toko UMKM sedang menyiapkan "
            + "pesanan Anda dengan teliti agar seluruh produk sesuai "
            + "dengan permintaan. Setelah proses persiapan selesai, "
            + "pesanan akan segera diserahkan kepada kurir untuk "
            + "dikirimkan ke alamat tujuan. Terima kasih telah "
            + "berbelanja di Toko UMKM. Kami akan memberikan "
            + "informasi kembali apabila terdapat perubahan status "
            + "pesanan berikutnya.";

        CustomerContact andi = new CustomerContact("Andi", "andi@gmail.com", "081234567890");
        CustomerContact budi = new CustomerContact("Budi", "", "+6281234567890");

        System.out.println("\n===== NOTIFIKASI ANDI =====");
        System.out.println("Berhasil: " + service.broadcast(andi, message) + " kanal");

        System.out.println("\n===== NOTIFIKASI BUDI =====");
        System.out.println("Berhasil: " + service.broadcast(budi, message) + " kanal");
    }
}
