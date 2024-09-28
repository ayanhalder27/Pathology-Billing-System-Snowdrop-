package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class Dashboard implements MouseListener,ActionListener {
    
    MyFrame frame;
    JLabel welcomeLabel, titleLabel, background, imageLabel;
    JButton logOutBtn, billBtn, ownerBtn,employeeBtn,testBtn;
    JLayeredPane layer;
    JPanel panel;
    LoginFrame login;
    OwnerControl owner;
    EmployeeControl employee;
    TestControl test;
    PatientDataEntry patientDataEntry;

    public Dashboard(String role, String name){
        frame = new MyFrame("Dashboard(" + role + ")");
        layer = new JLayeredPane();
        layer.setBounds(0,0,1024,768);
        frame.setContentPane(layer);

        background = new JLabel(new ImageIcon("./Resources/dashboard.jpg"));
        background.setBounds(0,0,1024,768);
        layer.add(background,JLayeredPane.FRAME_CONTENT_LAYER);

        imageLabel = new JLabel();
        imageLabel.setBounds(480,180,400,400);
        layer.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        titleLabel = new JLabel("Dashboard(" + role + ")");
		titleLabel.setBounds(400,20,450,35);
		titleLabel.setFont(new Font("Brush Script MT",Font.BOLD, 45));
        titleLabel.setForeground(Color.WHITE);
        layer.add(titleLabel,JLayeredPane.DEFAULT_LAYER);

        welcomeLabel = new JLabel("Welcome " + name);
        welcomeLabel.setBounds(650,80,400,30);
        welcomeLabel.setFont(new Font("Brush Script MT",Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        layer.add(welcomeLabel,JLayeredPane.DEFAULT_LAYER);

        logOutBtn = new JButton("Logout");
        logOutBtn.setBounds(20,10,110,30);
        logOutBtn.setFont(new Font("Tahoma",Font.BOLD, 20));
        logOutBtn.setFocusable(false);
        logOutBtn.addActionListener(this);
        layer.add(logOutBtn,JLayeredPane.DEFAULT_LAYER);
        
        billBtn = new JButton("Bill");
        billBtn.setBounds(130,130,220,60);
        billBtn.setFont(new Font("Bahnschrift Light Condensed",Font.BOLD, 15));
        billBtn.setFocusable(false);
        billBtn.addMouseListener(this);
        billBtn.addActionListener(this);
        layer.add(billBtn,JLayeredPane.DEFAULT_LAYER);

        ownerBtn = new JButton("Owner Control Room");
        ownerBtn.setBounds(130,210,220,60);
        ownerBtn.setFont(new Font("Bahnschrift Light Condensed",Font.BOLD, 15));
        ownerBtn.setFocusable(false);
        ownerBtn.addMouseListener(this);
        ownerBtn.addActionListener(this);
        
		if(role.equals("Owner"))
		layer.add(ownerBtn,JLayeredPane.DEFAULT_LAYER);

        employeeBtn = new JButton("Employee Control Room");
        employeeBtn.setBounds(130,290,220,60);
        employeeBtn.setFont(new Font("Bahnschrift Light Condensed",Font.BOLD, 15));
        employeeBtn.setFocusable(false);
        employeeBtn.addMouseListener(this);
        employeeBtn.addActionListener(this);
        if(role.equals("Owner"))
		layer.add(employeeBtn,JLayeredPane.DEFAULT_LAYER);

        testBtn = new JButton("Test Control Centre");
        testBtn.setBounds(130,370,220,60);
        testBtn.setFont(new Font("Bahnschrift Light Condensed",Font.BOLD, 15));
        testBtn.setFocusable(false);
        testBtn.addMouseListener(this);
        testBtn.addActionListener(this);
		if(role.equals("Owner"))
        layer.add(testBtn,JLayeredPane.DEFAULT_LAYER);

    }

    // public static void main(String[] args) {
        // Dashboard d = new Dashboard("Owner", "Ayan Halder");
    // }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == billBtn){
            imageLabel.setIcon(new ImageIcon("./Resources/bill.jpg"));
        }
        else if(e.getSource() == ownerBtn){
            imageLabel.setIcon(new ImageIcon("./Resources/owner.jpg"));
        }
        else if(e.getSource() == employeeBtn){
            imageLabel.setIcon(new ImageIcon("./Resources/employee.jpg"));
        }
        else if(e.getSource() == testBtn){
            imageLabel.setIcon(new ImageIcon("./Resources/test.jpg"));
        }
    }

    public void mouseExited(MouseEvent e) {
        if(e.getSource() == billBtn){
            imageLabel.setIcon(null);
        }
        else if(e.getSource() == ownerBtn){
            imageLabel.setIcon(null);
        }
        else if(e.getSource() == employeeBtn){
            imageLabel.setIcon(null);
        }
        else if(e.getSource() == testBtn){
            imageLabel.setIcon(null);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == billBtn){
            patientDataEntry = new PatientDataEntry();
            patientDataEntry.previousPage = frame;
            frame.setVisible(false);
        }
        else if(e.getSource() == ownerBtn){
            owner = new OwnerControl();
            owner.previousPage = frame;
            frame.setVisible(false);
        }
        else if(e.getSource() == employeeBtn){
            employee = new EmployeeControl();
            employee.previousPage = frame;
            frame.setVisible(false);
        }
        else if(e.getSource() == testBtn){
            test = new TestControl();
            test.previousPage = frame;
            frame.setVisible(false);
        }
        else if(e.getSource() == logOutBtn){
            frame.dispose();
            login = new LoginFrame();
        }
    }
}
