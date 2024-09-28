package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class OwnerControl implements ActionListener {
    
    private MyFrame frame;
    private JLabel titleLabel, searchLabel;
    private JButton registerBtn, backBtn;
    private DefaultTableModel head;
	private JTable table;
    private JTextField searchTF;
    private String[] columns = {"Name","Date of Birth","Gender","Phone Number","E-mail","Address", "Username"};
    private String[] rows = new String[7];
    private Register register;
    public JFrame previousPage,nextPage;
    private File file;

    public OwnerControl(){
        frame = new MyFrame("Owner Control Room");
		Container c = frame.getContentPane();
		c.setBackground(new Color(168,101,181));
		
		titleLabel = new JLabel("Owner Control Room");
		titleLabel.setBounds(350,20,395,35);
        titleLabel.setFont(new Font("Brush Script MT",Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        c.add(titleLabel);

        searchLabel = new JLabel("Search:");
		searchLabel.setBounds(670,100,100,30);
		searchLabel.setFont(new Font("Tahoma",Font.PLAIN, 20));
        searchLabel.setForeground(Color.WHITE);
		c.add(searchLabel);

        backBtn = new JButton("B");
        backBtn.setBounds(0,0,50,30);
        backBtn.setFocusable(false);
        backBtn.setIcon(new ImageIcon("./Resources/backBtn.png"));
        backBtn.addActionListener(this);
        c.add(backBtn);

        registerBtn = new JButton("Register Owner");
        registerBtn.setBounds(50,100,200,30);
        registerBtn.setFocusable(false);
        registerBtn.addActionListener(this);
        c.add(registerBtn);

        searchTF = new JTextField();
        searchTF.setBounds(750,100,200,30);
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
		scroll.setBounds(50,150,924,568);
		c.add(scroll);

        reloadData();

        searchTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				head.setRowCount(0);
				if(!searchTF.getText().isEmpty()){
					try{
						file = new File("./Files/data/Owners.txt");
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
			file = new File("./Files/data/Owners.txt");
			Scanner sc = new Scanner(file);
			try{
				while(sc.hasNextLine()){
					String data = sc.nextLine();
					String[] tempData = data.split(";");
                    if(tempData[10].equals("Owner")){
                        rows[0] = tempData[0];
                        rows[1] = tempData[1] + "." + tempData[2] + "." + tempData[3];
                        rows[2] = tempData[4]; 
                        rows[3] = tempData[5]; 
                        rows[4] = tempData[6]; 
                        rows[5] = tempData[7]; 
                        rows[6] = tempData[8]; 
                    }
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
        // OwnerControl w = new OwnerControl();
    // }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn){
            frame.dispose();
            previousPage.setVisible(true);
        }
        else if(e.getSource() == registerBtn){
            register = new Register("Owner");
            register.previousPage = frame;
            frame.setVisible(false);
        }
    }
}
