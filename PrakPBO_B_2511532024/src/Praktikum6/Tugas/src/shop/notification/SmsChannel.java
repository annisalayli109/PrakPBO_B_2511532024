package shop.notification;

public class SmsChannel extends AbstractChannel {
    private final int maxLength;

    public SmsChannel(int maxLength) {
        if (maxLength < 3) {
            throw new IllegalArgumentException("Panjang maksimum minimal 3 karakter");
        }

        this.maxLength = maxLength;
    }

    @Override 
    public String channelName() {
        return "SMS";
    }

    @Override 
    protected String resolveRecipient(CustomerContact contact) {
        String phone = contact.getPhone();

        if (phone.startsWith("08")) {
            return phone;
        }
        return null;
    }

    @Override 
    protected boolean deliver(String recipient, String message) {
        System.out.println("[SMS] Kepada: " + recipient);
        System.out.println("Pesan: " + message);

        return true;
    }

    @Override 
    protected String prepare(String message) {
        if (message.length() > maxLength) {
            return message.substring(0, maxLength - 3) + "...";
        }
        return message;
    }

    @Override 
    public String getLastDelivered() {
        return super.getLastDelivered();
    }
}
