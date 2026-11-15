package review.fixed;

import java.util.ArrayList;
import java.util.List;

interface Payable {
    boolean pay(long amount);
}

interface Refundable {
    boolean refund(long amount);
}

interface Shippable {
    long ship(int weightGrams);
}

interface InvoicePrintable {
    String printInvoice();
}

final class GiftVoucher implements Payable, InvoicePrintable {
    private double balance = 100000.0;

    @Override 
    public boolean pay(long amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    @Override 
    public String printInvoice() {
        return "sisa Rp" + (long) balance;
    }
}

abstract class Courier {
    public abstract long calculateCost(int kilograms);
}

class JneCourier extends Courier {
    @Override 
    public long calculateCost(int kilograms) {
        if (kilograms <= 0) {
            throw new IllegalArgumentException("Berat harus lebih dari 0");
        }

        return kilograms * 18000L;
    }
}

class Cart {
    private final List<Item> items = new ArrayList<>();

    static class Item {
        private final String name;
        private final long price;
        private final int quantity;

        Item(String name, long price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        long subtotal() {
            return price * quantity;
        }

        String getName() {
            return name;
        }
    }

    void add(Item item) {
        items.add(item);
    }

    long total() {
        long total = 0;

        for (Item item : items) {
            total += item.subtotal();
        }

        return total;
    }
}

final class Address {
    private final String street;
    private final String city;

    Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    @Override 
    public String toString() {
        return street + ", " + city;
    }
}

final class SimpleOrder {
    private final String id;
    private final Address address;

    SimpleOrder(String id, Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Alamat tidak boleh null");
        }

        this.id = id;
        this.address = address;
    }

    Address getAddress() {
        return address;
    }

    @Override 
    public String toString() {
        return id + " -> " + address;
    }
}

interface Discount {
    long apply(long price);
}

class MemberDiscount implements Discount {
    @Override 
    public long apply(long price) {
        return price - price * 5 / 100;
    }
}

class FlashSaleDiscount implements Discount {
    @Override 
    public long apply(long price) {
        return price - price * 30 / 100;
    }
}

public class BadShopRefactored {
    public static void main(String[] args) {

        Cart cart = new Cart();
        cart.add(new Cart.Item("Kopi Gayo", 85000, 2));
        System.out.println("Total cart: " + cart.total());

        Courier courier = new JneCourier();
        System.out.println("Ongkir Ekspres 2 kg: " + courier.calculateCost(2));

        Address home = new Address("Jl. Sudirman 10", "Padang");
        Address otherAddress = new Address("Jl. Sudirman 10", "Bukittinggi");

        SimpleOrder first = new SimpleOrder("ORD-1", home);
        SimpleOrder second = new SimpleOrder("ORD-2", otherAddress);

        System.out.println(first);
        System.out.println(second);

        Discount flashSale = new FlashSaleDiscount();
        System.out.println("Harga flash sale: " + flashSale.apply(100000));

        GiftVoucher voucher = new GiftVoucher();
        System.out.println(voucher.pay(30000) + " | " + voucher.printInvoice());
    }
}
