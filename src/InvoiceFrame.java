import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame {
    JPanel mainPnl;
    JPanel addressPnl;
    JPanel itemPnl = new JPanel();
    JPanel invoicePnl = new JPanel();

    JLabel nameLbl;
    JTextField nameTxt;

    JLabel streetLbl;
    JTextField streetTxt;

    JLabel cityLbl;
    JTextField cityTxt;

    JLabel stateLbl;
    JTextField stateTxt;

    JLabel zipLbl;
    JTextField zipTxt;

    JPanel itemAndControlPnl;

    JButton addItemBtn;
    JLabel itemNameLbl;
    JTextField itemNameTxt;
    JLabel itemPriceLbl;
    JTextField itemPriceTxt;
    JButton invoiceBtn;
    JButton quitBtn;
    JTextField quantityTxt;

    JTextArea invoiceArea;
    JScrollPane scroll = new JScrollPane();
    ArrayList<LineItem> currentLineItems = new ArrayList<>();

    public InvoiceFrame() {
        setTitle("Invoice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createCenterFrame();
        mainPnl = new JPanel(new BorderLayout());
        add(mainPnl);

        //Panels
        createAddressPnl();
        mainPnl.add(addressPnl, BorderLayout.NORTH);

        createInvoicePnl();
        mainPnl.add(invoicePnl, BorderLayout.CENTER);

        createItemAndControlPnl();
        mainPnl.add(itemAndControlPnl, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void createAddressPnl(){
        addressPnl = new JPanel(new GridLayout(3,1));

        JPanel customerPnl = new JPanel();
        nameLbl = new JLabel("Name:");
        nameTxt = new JTextField(25);
        customerPnl.add(nameLbl);
        customerPnl.add(nameTxt);
        addressPnl.add(customerPnl);

        JPanel streetPnl = new JPanel();
        streetLbl = new JLabel("Street:");
        streetTxt = new JTextField(25);
        streetPnl.add(streetLbl);
        streetPnl.add(streetTxt);
        addressPnl.add(streetPnl);

        JPanel cityStatZipPnl = new JPanel(new GridLayout(1,3));
        JPanel cityPnl = new JPanel();
        cityLbl = new JLabel("City:");
        cityTxt = new JTextField(25);
        cityPnl.add(cityLbl);
        cityPnl.add(cityTxt);
        cityStatZipPnl.add(cityPnl);

        JPanel statePnl = new JPanel();
        stateLbl = new JLabel("State:");
        stateTxt = new JTextField(25);
        statePnl.add(stateLbl);
        statePnl.add(stateTxt);
        cityStatZipPnl.add(statePnl);

        JPanel zipPnl = new JPanel();
        zipLbl = new JLabel("Zip:");
        zipTxt = new JTextField(25);
        zipPnl.add(zipLbl);
        zipPnl.add(zipTxt);
        cityStatZipPnl.add(zipPnl);

        addressPnl.add(cityStatZipPnl);
    }

    public void createItemAndControlPnl(){
        itemAndControlPnl = new JPanel(new GridLayout(2,1));
        JPanel addItemPnl = new JPanel(new GridLayout(1,10));
        itemNameLbl = new JLabel("Item: ");
        itemNameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        itemNameTxt = new JTextField(25);
        itemNameTxt.setHorizontalAlignment(SwingConstants.LEFT);
        addItemPnl.add(itemNameLbl);
        addItemPnl.add(itemNameTxt);
        JLabel quantityLbl = new JLabel("Qty: ");
        quantityLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        addItemPnl.add(quantityLbl);
        quantityTxt = new JTextField(3);
        addItemPnl.add(quantityTxt);

        itemPriceLbl = new JLabel("Price: ");
        itemPriceTxt = new JTextField(5);
        addItemPnl.add(itemPriceLbl);
        addItemPnl.add(itemPriceTxt);
        itemPriceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        itemPriceTxt.setHorizontalAlignment(SwingConstants.LEFT);

        addItemBtn = new JButton("Add Item");
        addItemPnl.add(addItemBtn);
        itemAndControlPnl.add(addItemPnl);
        addItemBtn.addActionListener(e -> {
            Product tempProduct = new Product(itemNameTxt.getText(),Double.valueOf(itemPriceTxt.getText()));
            LineItem tempLineItem = new LineItem(tempProduct, Integer.valueOf(quantityTxt.getText()));
            currentLineItems.add(tempLineItem);
            itemNameTxt.setText("");
            quantityTxt.setText("");
            itemPriceTxt.setText("");
            JOptionPane.showMessageDialog(invoiceArea,"Item Added");
        });

        JPanel cmdPnl = new JPanel(new GridLayout(1,2));
        invoiceBtn = new JButton("Create Invoice");
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            System.exit(0);
        });
        cmdPnl.add(invoiceBtn);
        invoiceBtn.addActionListener(e -> {
            Invoice currentInvoice = new Invoice(currentLineItems);
            Address currentCustomer = new Address(nameTxt.getText(),streetTxt.getText(),cityTxt.getText(),stateTxt.getText(),zipTxt.getText());
            invoiceArea.setText(currentInvoice.getInvoice(currentCustomer));
        });
        cmdPnl.add(quitBtn);
        itemAndControlPnl.add(cmdPnl);
    }

    public void createInvoicePnl(){
        invoicePnl = new JPanel();
        invoiceArea = new JTextArea(50,50);
        scroll.add(invoiceArea);
        invoicePnl.add(invoiceArea);
        invoicePnl.add(scroll);
        invoiceArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        invoiceArea.setEditable(false);
    }

    private void createCenterFrame(){
        //screen dimensions
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        //center frame
        setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        setLocation(screenWidth / 8, screenHeight / 8);
    }
}