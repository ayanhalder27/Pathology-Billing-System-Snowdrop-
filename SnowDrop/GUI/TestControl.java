package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import Entity.Test;
import EntityList.TestList;
import Files.TestIO;

import javax.swing.JTable;
import javax.swing.JScrollPane;


public class TestControl implements ActionListener,FocusListener{
    
    private MyFrame frame;
	private JLabel title, testIdLabel, testNameLabel, priceLabel, searchLabel;
    private JTextField testIdTf, testNameTf, priceTf, searchTF;
    private JButton addButton, updateButton, deleteButton, clearButton, backBtn;
	private LineBorder border1, border2;
    private DefaultTableModel head;
	private JTable table;
	JScrollPane scroll; 
	private String[] columns = {"Test ID", "Test Name", "Price"};
	private String[] rows = new String[3];
	private Dashboard d;
	private TestList testList;
	private File file;
	public JFrame previousPage;


    public TestControl(){
        frame = new MyFrame("Test Control Centre");
        Container c = frame.getContentPane();
        c.setBackground(new Color(132,94,132));

        border1 = new LineBorder(new Color(227,85,255), 3);
		border2 = new LineBorder(new Color(237,67,55), 3);
		
		title = new JLabel("Test Control Centre");
		title.setBounds(350,50,395,35);
		title.setFont(new Font("Brush Script MT",Font.BOLD, 45));
		title.setForeground(new Color(255,255,255));
		c.add(title);
		
		testIdLabel = new JLabel("Test ID:");
		testIdLabel.setBounds(120,170,100,30);
		testIdLabel.setFont(new Font("Tahoma",Font.PLAIN, 18));
		testIdLabel.setForeground(new Color(255,255,255));
		c.add(testIdLabel);
		
		testNameLabel = new JLabel("Test Name:");
		testNameLabel.setBounds(115,222,100,30);
		testNameLabel.setFont(new Font("Tahoma",Font.PLAIN, 18));
		testNameLabel.setForeground(new Color(255,255,255));
		c.add(testNameLabel);
		
		priceLabel = new JLabel("Price:");
		priceLabel.setBounds(120,275,100,30);
		priceLabel.setFont(new Font("Tahoma",Font.PLAIN, 18));
		priceLabel.setForeground(new Color(255,255,255));
		c.add(priceLabel);

		searchLabel = new JLabel("Search:");
		searchLabel.setBounds(630,240,100,25);
		searchLabel.setFont(new Font("Tahoma",Font.PLAIN, 18));
		searchLabel.setForeground(new Color(255,255,255));
		c.add(searchLabel);
		
		testIdTf = new JTextField("#TID");
		testIdTf.setBounds(230,170,200,30);
		testIdTf.setFont(new Font("Tahoma",Font.PLAIN, 20));
		testIdTf.requestFocusInWindow();
		testIdTf.addActionListener(this);
		testIdTf.addFocusListener(this);
		c.add(testIdTf);
	
		testNameTf = new JTextField();
		testNameTf.setBounds(230,225,200,30);
		testNameTf.setFont(new Font("Tahoma",Font.PLAIN, 20));
		testNameTf.addActionListener(this);
		testNameTf.addFocusListener(this);
		c.add(testNameTf);
		
		priceTf = new JTextField();
		priceTf.setBounds(230,280,200,30);
		priceTf.setFont(new Font("Tahoma",Font.PLAIN, 20));
		priceTf.addActionListener(this);
		priceTf.addFocusListener(this);
		c.add(priceTf);

		searchTF = new JTextField();
		searchTF.setBounds(710,240,200,30);
		searchTF.setFont(new Font("Tahoma",Font.PLAIN, 20));
		searchTF.addFocusListener(this);
		c.add(searchTF);

        backBtn = new JButton("B");
        backBtn.setBounds(0,0,50,30);
        backBtn.setFocusable(false);
		backBtn.setIcon(new ImageIcon("./Resources/backBtn.png"));
        backBtn.addActionListener(this);
        c.add(backBtn);
		
		addButton = new JButton("Add");
		addButton.setBounds(480,150,100,30);
		addButton.setFont(new Font("Georgia",Font.PLAIN, 20));
        addButton.addActionListener(this);
		c.add(addButton);
		
		updateButton = new JButton("Update");
		updateButton.setBounds(480,200,100,30);
		updateButton.setFont(new Font("Georgia",Font.PLAIN, 20));
        updateButton.addActionListener(this);
		c.add(updateButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(480,250,100,30);
		deleteButton.setFont(new Font("Georgia",Font.PLAIN, 20));
        deleteButton.addActionListener(this);
		c.add(deleteButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(480,300,100,30);
		clearButton.setFont(new Font("Georgia",Font.PLAIN, 20));
        clearButton.addActionListener(this);
		c.add(clearButton);
		
		head = new DefaultTableModel();
		head.setColumnIdentifiers(columns);
		
		table = new JTable();
		table.setModel(head);
		table.setFont(new Font("Georgia",Font.PLAIN, 20));
		table.setSelectionBackground(new Color(173, 216, 230));
		table.setBackground(new Color(230, 245, 255));
		table.setRowHeight(30);
		
		scroll = new JScrollPane(table);
		scroll.setBounds(50,350,924,368);
		c.add(scroll);

		reloadData();

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int rowNum = table.getSelectedRow();
				testIdTf.setText(table.getValueAt(rowNum, 0).toString());
				testNameTf.setText(table.getValueAt(rowNum, 1).toString());
				priceTf.setText(table.getValueAt(rowNum, 2).toString());
				//oldData = fNameTF.getText() + "\t" + lNameTF.getText() + "\t" + phoneTF.getText() + "\t" + CGPATF.getText();
			}
		});

		searchTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				head.setRowCount(0);
				if(!searchTF.getText().isEmpty()){
					try{
						file = new File("./Files/data/Tests.txt");
						Scanner sc =new Scanner(file);
						try{
							while(sc.hasNextLine()){
								String data = sc.nextLine();
								if(data.toLowerCase().indexOf(searchTF.getText().trim().toLowerCase())>=0){
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
		});
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

    // public static void main(String[] args) {
        // TestControl s = new TestControl();
    // }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton){
			rows[0] = testIdTf.getText().trim();
			rows[1] = testNameTf.getText().trim();
			rows[2] = priceTf.getText().trim();
			Test t = new Test(rows[0],rows[1],Float.parseFloat(rows[2]));
			TestIO.writeTestInFile(t);
			reloadData();
			testIdTf.setText("");
			testNameTf.setText("");
			priceTf.setText("");
        }
        else if(e.getSource() == updateButton){
			rows[0] = testIdTf.getText().trim();
			rows[1] = testNameTf.getText().trim();
			rows[2] = priceTf.getText().trim();
			head.setValueAt(rows[0], table.getSelectedRow(), 0);				
			head.setValueAt(rows[1], table.getSelectedRow(), 1);
			head.setValueAt(rows[2], table.getSelectedRow(), 2);
        }
        else if(e.getSource() == deleteButton){
			//head.removeRow(table.getSelectedRow());
			TestIO.deleteTestInFile(testIdTf.getText());
			reloadData();
        }
        else if(e.getSource() == clearButton){
			testIdTf.setText("");
			testNameTf.setText("");
			priceTf.setText("");
			searchTF.setText("");
        }
        else if(e.getSource() == backBtn){
            frame.dispose();
            previousPage.setVisible(true);
        }
    }

	public void focusGained(FocusEvent e) {

		if(e.getSource() == testIdTf){
			testIdTf.setBorder(border1);
		}

		else if(e.getSource() == testNameTf){
			testNameTf.setBorder(border1);
		}

		else if(e.getSource() == priceTf){
			priceTf.setBorder(border1);
		}

		else if(e.getSource() == searchTF){
			searchTF.setBorder(border1);
		}
	}

	public void focusLost(FocusEvent e) {
		
		if(e.getSource() == testIdTf){
			testIdTf.setBorder(null);
		}

		else if(e.getSource() == testNameTf){
			testNameTf.setBorder(null);
		}

		else if(e.getSource() == priceTf){
			priceTf.setBorder(null);
		}

		else if(e.getSource() == searchTF){
			searchTF.setBorder(null);
		}
	}
}
