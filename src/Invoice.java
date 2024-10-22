import java.util.ArrayList;

/**
 * Describes an invoice for a set of purchased products
 */

public class Invoice {

    private double totalAmountDue = 0.0;
    public ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

    public Invoice(ArrayList<LineItem> lineItems){
        this.lineItems = lineItems;
    }

    public void addLineItem(LineItem item){
        lineItems.add(item);
    }

    public String getInvoice(Address currentAddress){
        String returnVal = "";
        returnVal += "\n\t\t\t    Invoice\n";
        returnVal += currentAddress.getAddress() + "\n=============================================================\n";
        returnVal += "Item\t\t\t\tQty\tPrice\tTotal\n";

        for(LineItem item : lineItems){
            returnVal +=  item.getProduct().getName() + "\t\t\t\t" + item.getQuantity() + "\t" +item.getProduct().getUnitPrice() + "\t" + item.calculateTotal() + "\n";
        }

        returnVal += "\n=============================================================\n" + "Amount Due: $" + getAmountDue();

        return returnVal;
    }

    public double getAmountDue(){
        double amountDue = 0.0;

        for(LineItem item : lineItems){
            amountDue += item.calculateTotal();
        }
        return amountDue;
    }
}
