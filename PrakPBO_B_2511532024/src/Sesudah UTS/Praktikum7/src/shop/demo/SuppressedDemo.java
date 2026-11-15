package shop.demo;

public class SuppressedDemo {
    static class Resource implements AutoCloseable {
        @Override 
        public void close() {
            throw new IllegalArgumentException("gagal menutup resource");
        }
    }

    public static void main(String[] args) {
        try (Resource resource = new Resource()) {
            throw new IllegalArgumentException("kegagalan di blok try");
        } catch (Exception e) {
            System.out.println("Exception utama: " + e.getMessage());
            for (Throwable s : e.getSuppressed()) {
                System.out.println("Suppressed: " + s.getMessage());
            }
        }
    }
}
