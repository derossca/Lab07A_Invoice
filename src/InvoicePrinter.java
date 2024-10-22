import javax.swing.*;

/**
 * prints a sample invoice
 */

public class InvoicePrinter {
    public static void main(String[] args) {
        InvoiceFrame frame = new InvoiceFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}