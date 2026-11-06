package shop.payment;

public interface PaymentMethod {
    String getName();

    /** @return true if the payment succedes */
    boolean pay(long amount);

    default long adminFee(long amount) {
        return 0;
    }
}
