package shop.notification;

public class EmailChannel extends AbstractChannel {

    @Override 
    public String channelName() {
        return "Email";
    }

    @Override 
    protected String resolveRecipient(CustomerContact contact) {
        String email = contact.getEmail();

        if (email.contains("@")) {
            return email;
        }
        return null;
    }

    @Override 
    protected boolean deliver(String recipient, String message) {
        System.out.println("[EMAIL] Kepada: " + recipient);
        System.out.println("Pesan: " + message);
        
        return true;
    }
}
