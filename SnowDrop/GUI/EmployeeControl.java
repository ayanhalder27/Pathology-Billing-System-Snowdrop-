package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;


public class EmployeeControl implements ActionListener {
    private MyFrame frame;
    private JLabel titleLabel, searchLabel;
    private JButton registerBtn, backBtn, deleteBtn, updateBtn;
    private DefaultTableModel head;
	private JTable table;
    private JTextField searchTF;
    private String[] columns = {"Name","Date of Birth","Gender","Phone Number","E-mail","Address", "Username"};
    private String[] rows = new String[7];
    private Register register;
    public JFrame previousPage;
    private File file;

    public EmployeeControl(){
        frame = new MyFrame("Employee Control Room");
		Container c = frame.getContentPane();
		c.setBackground(new Color(147,112,219));
		
		titleLabel = new JLabel("Employee Control Room");
		titleLabel.setBounds(340,20,395,35);
		titleLabel.setFont(new Font("Brush Script MT",Font.BOLD, 45));
        titleLabel.setForeground(new Color(230,230,250));
        c.add(titleLabel);

        searchLabel = new JLabel("Search:");
		searchLabel.setBounds(663,198,100,30);
		searchLabel.setFont(new Font("Tahoma",Font.BOLD, 19));
        searchLabel.setForeground(new Color(230,230,250));
		c.add(searchLabel);

        backBtn = new JButton("B");
        backBtn.setBounds(0,0,50,30);
        backBtn.setFocusable(false);
        backBtn.setIcon(new ImageIcon("./Resources/backBtn.png"));
        backBtn.addActionListener(this);
        c.add(backBtn);

        registerBtn = new JButton("Register Employee");
        registerBtn.setBounds(50,100,200,30);
        registerBtn.setFocusable(false);
        registerBtn.addActionListener(this);
        c.add(registerBtn);

        deleteBtn = new JButton("Delete Employee");
        deleteBtn.setBounds(50,150,200,30);
        deleteBtn.setFocusable(false);
        deleteBtn.addActionListener(this);
        c.add(deleteBtn);

        updateBtn = new JButton("Update Profile");
        updateBtn.setBounds(50,200,200,30);
        updateBtn.setFocusable(false);
        c.add(updateBtn);

        searchTF = new JTextField();
        searchTF.setBounds(750,200,200,30);
        c.add(searchTF);

        head = new DefaultTableModel();
		head.setColumnIdentifiers(columns);
		
		table = new JTable();
		table.setModel(head);
		table.setFont(new Font("Tahoma",Font.PLAIN, 15));
		table.setSelectionBackground(new Color(173, 216, 230));
		table.setBackground(new Color(230, 245, 255));
		table.setRowHeight(30);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(50,250,924,468);
		c.add(scroll);

        reloadData();

        searchTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				head.setRowCount(0);
				if(!searchTF.getText().isEmpty()){
					try{
						file = new File("./Files/data/Employees.txt");
						Scanner sc =new Scanner(file);
						try{
							while(sc.hasNextLine()){
								String data = sc.nextLine();
								if(data.toLowerCase().indexOf(searchTF.getText().trim().toLowerCase())>=0){
									String[] tempData = data.split(";");
                                    rows[0] = tempData[0];
                                    rows[1] = tempData[1] + "." + tempData[2] + "." + tempData[3];
                                    rows[2] = tempData[4]; 
                                    rows[3] = tempData[5]; 
                                    rows[4] = tempData[6]; 
                                    rows[5] = tempData[7]; 
                                    rows[6] = tempData[8]; 
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
			file = new File("./Files/data/Employees.txt");
			Scanner sc = new Scanner(file);
			try{
				while(sc.hasNextLine()){
					String data = sc.nextLine();
					String[] tempData = data.split(";");
                    rows[0] = tempData[0];
                    rows[1] = tempData[1] + "." + tempData[2] + "." + tempData[3];
                    rows[2] = tempData[4]; 
                    rows[3] = tempData[5]; 
                    rows[4] = tempData[6]; 
                    rows[5] = tempData[7]; 
                    rows[6] = tempData[8]; 
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
        // EmployeeControl w = new EmployeeControl();
    // }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn){
            frame.dispose();
            previousPage.setVisible(true);
        }
        else if(e.getSource() == registerBtn){
            register = new Register("Employee");
            register.previousPage = frame;
            frame.setVisible(false);
        }
        else if(e.getSource() == deleteBtn){
            head.removeRow(table.getSelectedRow());
        }
    } 
}
