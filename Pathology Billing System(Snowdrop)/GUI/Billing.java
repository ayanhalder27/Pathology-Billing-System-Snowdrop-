package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entity.Patient;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Billing implements ActionListener, MouseListener, KeyListener{
    
    MyFrame frame;
    JLabel titleLabel, itemNameLabel, priceLabel, quantityLabel, subtotalLabel, discountLabel, totalLabel, selectTestLabel, selectedTestLabel, searchLabel;
    JTextField itemNameTf, priceTf, quantityTf, subtotalTf, discountTf, totalTf, searchTf;
    JButton addItemBtn, calculateBtn, resetBtn, finishBtn, backBtn;
    private DefaultTableModel head, head2;
	private JTable table, table2;
	private JScrollPane scroll, scroll2;
    private String[] columns = {"Test ID", "Test Name", "Price"};
	private String[] rows = new String[3];
    double subtotal = 0.0;
    private File file;
    JFrame previousPage;
    Patient patientData;

    public Billing() {
        frame = new MyFrame("Billing System");
        Container c = frame.getContentPane();
		c.setBackground(new Color(135,86,146));
        
        
        titleLabel = new JLabel("Billing System");
        titleLabel.setFont(new Font("Brush Script MT", Font.BOLD, 45));
        titleLabel.setBounds(370, 20, 300, 40);
        titleLabel.setForeground(new Color(255,255,255));
        c.add(titleLabel);

        selectTestLabel = new JLabel("Select Tests:");
        selectTestLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        selectTestLabel.setForeground(new Color(255,255,255));
        selectTestLabel.setBounds(20, 103, 100, 30);
        c.add(selectTestLabel);
        
        selectedTestLabel = new JLabel("Selected Tests:");
        selectedTestLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        selectedTestLabel.setForeground(new Color(255,255,255));
        selectedTestLabel.setBounds(515, 103, 120, 30);
        c.add(selectedTestLabel);

        searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        searchLabel.setForeground(new Color(255,255,255));
        searchLabel.setBounds(223, 103, 120, 30);
        c.add(searchLabel);

        subtotalLabel = new JLabel("Subtotal:");
        subtotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        subtotalLabel.setForeground(new Color(255,255,255));
        subtotalLabel.setBounds(710, 500, 100, 30);
        c.add(subtotalLabel);

        searchTf = new JTextField();
        searchTf.setBounds(297, 105, 200, 30);
        searchTf.addKeyListener(this);
        c.add(searchTf);
        
        subtotalTf = new JTextField();
        subtotalTf.setBounds(798, 500, 200, 30);
        subtotalTf.setEditable(false);
        c.add(subtotalTf);

        
        discountLabel = new JLabel("Discount:");
        discountLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        discountLabel.setForeground(new Color(255,255,255));
        discountLabel.setBounds(710, 550, 100, 30);
        c.add(discountLabel);

        
        discountTf = new JTextField();
        discountTf.setBounds( 798, 550, 200, 30);
        discountTf.addActionListener(this);
        discountTf.addKeyListener(this);
        c.add(discountTf);

        
        totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        totalLabel.setForeground(new Color(255,255,255));
        totalLabel.setBounds(710, 600, 100, 30);
        c.add(totalLabel);

        
        totalTf = new JTextField();
        totalTf.setBounds(798, 600, 200, 30);
        totalTf.setEditable(false);
        c.add(totalTf);

        backBtn = new JButton("B");
        backBtn.setBounds(0,0,50,30);
        backBtn.setFocusable(false);
        backBtn.setIcon(new ImageIcon("./Resources/backBtn.png"));
        backBtn.addActionListener(this);
        c.add(backBtn);
        
        resetBtn = new JButton("Reset");
        resetBtn.setBounds(720, 650, 100, 40);
        resetBtn.setFocusable(false);
        resetBtn.addActionListener(this);
        c.add(resetBtn);

        
        finishBtn = new JButton("Finish & Pay");
        finishBtn.setBounds(850, 650, 150, 40);
        finishBtn.setFocusable(false);
        finishBtn.addActionListener(this);
        c.add(finishBtn);

        head = new DefaultTableModel();
		head.setColumnIdentifiers(columns);
		
		table = new JTable();
		table.setModel(head);
		table.setFont(new Font("Times New Roman",Font.PLAIN, 20));
		table.setSelectionBackground(new Color(173, 216, 230));
		table.setBackground(new Color(230, 245, 255));
		table.setRowHeight(20);
        table.addMouseListener(this);
		
		scroll = new JScrollPane(table);
		scroll.setBounds(18,140,480,570);
		c.add(scroll);
        
        head2 = new DefaultTableModel();
		head2.setColumnIdentifiers(columns);
		
		table2 = new JTable();
		table2.setModel(head2);
		table2.setFont(new Font("Times New Roman",Font.PLAIN, 20));
		table2.setSelectionBackground(new Color(173, 216, 230));
		table2.setBackground(new Color(230, 245, 255));
		table2.setRowHeight(20);
        table2.addMouseListener(this);
		
		scroll2 = new JScrollPane(table2);
		scroll2.setBounds(515,140,480,350);
		c.add(scroll2);

        reloadData();
    }

    public void reloadData(){
		head.setRowCount(0);
		try{
			file = new File("./Files/data/Tests.txt");
			Scanner sc = new Scanner(file);
			try{
				while(sc.hasNextLine()){
					String data = sc.nextLine();
					rows = data.split(";");
					head.addRow(rows);
				}
			}
			finally{
				sc.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    public void removeMatchingRows() {
        reloadData();
        for (int i=0; i<head.getRowCount(); i++) {
            String testID1 = head.getValueAt(i, 0).toString();
            String testName1 = head.getValueAt(i, 1).toString();
            String price1 = head.getValueAt(i, 2).toString();
            
            for (int j=0; j<head2.getRowCount(); j++) {
                String testID2 = head2.getValueAt(j, 0).toString();
                String testName2 = head2.getValueAt(j, 1).toString();
                String price2 = head2.getValueAt(j, 2).toString();
                
                if (testID1.equals(testID2) && testName1.equals(testName2) && price1.equals(price2)) {
                    head.removeRow(i);
                    i--;  
                    break;  
                }
            }
        }
    }

    public void subtotalCalculate(){
        float subtotal = 0;
        for(int i=0; i<head2.getRowCount(); i++){
            subtotal += Float.valueOf(head2.getValueAt(i, 2).toString());
        }
        subtotalTf.setText(String.valueOf(subtotal));
        
        if(discountTf.getText().equals("")){
            totalTf.setText(String.valueOf(subtotal));
        }
        else{
            float total = subtotal-Float.valueOf(discountTf.getText());
            totalTf.setText(String.valueOf(total));
        }
    }

    public void search(){
        head.setRowCount(0);
        if(!searchTf.getText().isEmpty()){
            try{
                file = new File("./Files/data/Tests.txt");
                Scanner sc =new Scanner(file);
                try{
                    while(sc.hasNextLine()){
                        String data = sc.nextLine();
                        if(data.toLowerCase().indexOf(searchTf.getText().trim().toLowerCase())>=0){
                            rows = data.split(";");
                            head.addRow(rows);
                        }
                    }
                }
                finally{
                    sc.close();
                }
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }	
        }
        else{
            reloadData();
        }
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == backBtn){
            frame.dispose();
            previousPage.setVisible(true);
        }
        
        else if(e.getSource() == resetBtn){
            head2.setRowCount(0);
            reloadData();
            subtotalTf.setText("");
            discountTf.setText("");
            totalTf.setText("");
            searchTf.setText("");
        }

        else if(e.getSource() == finishBtn || e.getSource() == discountTf){
            patientData = PatientDataEntry.patientData;
            String hedding = "Apolo Diagnostic Centre\n" + 
                             "\tInvoice\n\n" +
                             "Patient Information: \n";
            String testHeadline = "\n\n\nTest ID\t\t" + "Test Name\t\t\t" + "Price\n\n" ;
            String testData = "";
            for (int i=0; i<head2.getRowCount(); i++) {
                String testID2 = head2.getValueAt(i, 0).toString();
                String testName2 = head2.getValueAt(i, 1).toString();
                String price2 = head2.getValueAt(i, 2).toString();
                testData += testID2 + "\t\t" + testName2 + "\t\t\t" + price2 + "\n";
            }
            String discount ="";
            if(discountTf.getText().equals("")){
                discount ="0";
            }
            else{
                discount = discountTf.getText();
            }

            String ending = "\n\n\t\t\t\tSubtotal: " + subtotalTf.getText() + "\n" +
                            "\t\t\t\tDiscount: " + discount + "\n" +
                            "\t\t\t\tTotal: " + totalTf.getText();
            String invoiceData = hedding + patientData.getPatientAsString() + testHeadline + testData + ending;
            try{
                String fileName = patientData.getName() + "_" + patientData.getCurrentDate();
                FileWriter fw = new FileWriter(new File("./Invoices/" + fileName + ".txt"));
				fw.write(invoiceData);
				fw.flush();
				fw.close();
                JOptionPane.showMessageDialog(frame,"Billing Successfull");
                frame.dispose();
                previousPage.setVisible(true);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    // public static void main(String[] args) {
        // new Billing();
    // }


    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == table){
            int rowNum = table.getSelectedRow();
            rows[0] = table.getValueAt(rowNum, 0).toString();
            rows[1] = table.getValueAt(rowNum, 1).toString();
            rows[2] = table.getValueAt(rowNum, 2).toString();
            head2.addRow(rows);
            removeMatchingRows();
            subtotalCalculate();
        }

        else if(e.getSource() == table2){
            int rowNum = table2.getSelectedRow();
            head2.removeRow(rowNum);
            removeMatchingRows();
            subtotalCalculate();
        }
    }


    public void mousePressed(MouseEvent e) {
        
    }


    public void mouseReleased(MouseEvent e) {
        
    }


    public void mouseEntered(MouseEvent e) {
        
    }


    public void mouseExited(MouseEvent e) {
        
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if(e.getSource() == discountTf){
            if(!discountTf.getText().equals("")){
                float total = Float.valueOf(subtotalTf.getText())-Float.valueOf(discountTf.getText());
                totalTf.setText(String.valueOf(total));
            }
            else{
                totalTf.setText(subtotalTf.getText());
            }
        }
        else if(e.getSource() == searchTf){
            search();
        }
    }

}
