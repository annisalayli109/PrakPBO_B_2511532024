package shop.exception;

public class MinimumOrderException extends ShopException {
    private static final long serialVersionUID = 1L;

    public MinimumOrderException(long minimum, long subtotal) {
        super("Pesanan minimal Rp" + minimum + " untuk pengiriman ini, subtotal Rp" + subtotal);
    }
}
