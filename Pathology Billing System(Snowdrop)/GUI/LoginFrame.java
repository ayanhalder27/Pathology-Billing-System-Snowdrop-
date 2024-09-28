package GUI;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Files.AuthorityIO;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;


public class LoginFrame implements ActionListener,FocusListener {
    
    private MyFrame frame;
    private JLabel usernameLable, passwordLable, background, warning1, warning2;
    private JTextField usernameTF;
    private JPasswordField passwordField;
    private JButton loginBtn, showPasswordBtn;
    private JLayeredPane layer;
    private JPanel panel;
    private LineBorder border1, border2;
    private Dashboard d;

    public LoginFrame(){
        frame = new MyFrame("Login");
        layer = new JLayeredPane();
        layer.setBounds(0,0,1024,768);
        frame.setContentPane(layer);

        border1 = new LineBorder(new Color(0,138,255), 5);
		border2 = new LineBorder(new Color(237,67,55), 3);

        background = new JLabel(new ImageIcon("./Resources/Login Background.jpg"));
        background.setBounds(0,0,1024,768);
        layer.add(background,JLayeredPane.FRAME_CONTENT_LAYER);

        JPanel panel = new JPanel();
        panel.setBounds(284, 200, 400, 300);
        panel.setBackground(new Color(98, 138, 168, 200)); 
        layer.add(panel, JLayeredPane.DEFAULT_LAYER);

        usernameLable = new JLabel("Username:");
        usernameLable.setBounds(320,244,100,30);
        usernameLable.setFont(new Font("Tahoma",Font.PLAIN, 20));
        usernameLable.setForeground(Color.white);
        layer.add(usernameLable,JLayeredPane.PALETTE_LAYER);

        warning1 = new JLabel("Username not found");
        warning1.setBounds(440,275,200,30);
        warning1.setFont(new Font("Helvetica",Font.PLAIN, 15));
        warning1.setForeground(Color.RED);
        warning1.setVisible(false);
        layer.add(warning1,JLayeredPane.PALETTE_LAYER);

        passwordLable = new JLabel("Password:");
        passwordLable.setBounds(320,340,100,30);
        passwordLable.setFont(new Font("Tahoma",Font.PLAIN, 20));
        passwordLable.setForeground(Color.white);
        layer.add(passwordLable,JLayeredPane.PALETTE_LAYER);

        warning2 = new JLabel("Incorrect Password");
        warning2.setBounds(440,365,200,30);
        warning2.setFont(new Font("Helvetica",Font.PLAIN, 15));
        warning2.setForeground(Color.RED);
        warning2.setVisible(false);
        layer.add(warning2,JLayeredPane.PALETTE_LAYER);

        usernameTF = new JTextField();
        usernameTF.setText("Username");
        usernameTF.setBounds(430,247,200,30);
        usernameTF.setFont(new Font("Tahoma",Font.PLAIN, 15));
        usernameTF.setBackground(new Color(252,251,255));
        usernameTF.addFocusListener(this);
        usernameTF.addActionListener(this);
        layer.add(usernameTF,JLayeredPane.PALETTE_LAYER);

        passwordField = new JPasswordField();
        passwordField.setText("Password");
        passwordField.setBounds(430,340,200,30);
        passwordField.setFont(new Font("Tahoma",Font.PLAIN, 15));
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(this);
        passwordField.addActionListener(this);
        layer.add(passwordField,JLayeredPane.PALETTE_LAYER);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(436, 423, 100, 30);
        loginBtn.setFont(new Font("Tahoma",Font.PLAIN, 20));
        loginBtn.addActionListener(this);
        layer.add(loginBtn,JLayeredPane.PALETTE_LAYER);

        showPasswordBtn = new JButton();
        showPasswordBtn.setIcon(new ImageIcon("./Resources/eyeClose.png"));
        showPasswordBtn.setBounds(605,345,20,20);
        showPasswordBtn.addActionListener(this);
        layer.add(showPasswordBtn, JLayeredPane.MODAL_LAYER);

        loginBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				loginBtn.setBackground(new Color(46, 90, 136));
                loginBtn.setForeground(Color.white);
			}
            public void mouseExited(MouseEvent e){
				loginBtn.setBackground(null);
                loginBtn.setForeground(Color.black);
			}
		});
    }

    // public static void main(String[] args) {
        // LoginFrame f = new LoginFrame();
    // }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBtn || e.getSource() == passwordField){
            String userName = usernameTF.getText();
			String password = String.valueOf(passwordField.getPassword());

            if(AuthorityIO.checkUser(userName, password).equals("warning1")){
                warning1.setVisible(true);
                usernameTF.setBorder(border2);
            }
            else if(AuthorityIO.checkUser(userName, password).equals("warning2")){
                warning2.setVisible(true);
                passwordField.setBorder(border2);
            }
            else if(AuthorityIO.checkUser(userName, password).equals("Owner")){
                frame.dispose();
                d = new Dashboard("Owner", AuthorityIO.name);
            }
            else if(AuthorityIO.checkUser(userName, password).equals("Employee")){
                frame.dispose();
                d = new Dashboard("Employee", AuthorityIO.name);
            }
        }
        else if(e.getSource() == usernameTF){
            passwordField.requestFocusInWindow();
        }
        else if(e.getSource() == showPasswordBtn){
            if(passwordField.getEchoChar()=='*'){
                passwordField.setEchoChar((char) 0);
                showPasswordBtn.setIcon(new ImageIcon("./Resources/eyeOpen.png"));
            }
            
            else{
                passwordField.setEchoChar('*');
                showPasswordBtn.setIcon(new ImageIcon("./Resources/eyeClose.png"));
            }
            passwordField.requestFocusInWindow();
        }
    }


    public void focusGained(FocusEvent e) {
        if(e.getSource() == usernameTF){
            usernameTF.setBorder(border1);
            usernameTF.setBounds(430,242,200,40);
            warning1.setVisible(false);
            if(usernameTF.getText().equals("Username")){
                usernameTF.setText("");
            }
        }
        else if(e.getSource() == passwordField){
            passwordField.setBorder(border1);
            passwordField.setBounds(430,335,200,40);
            warning2.setVisible(false);
            if(String.valueOf(passwordField.getPassword()).equals("Password")){
                passwordField.setText("");
                passwordField.setEchoChar('*');
            }
        }
    }

    public void focusLost(FocusEvent e) {
        if(e.getSource() == usernameTF){
            usernameTF.setBorder(null);
            usernameTF.setBounds(430,247,200,30);
        }
        else if(e.getSource() == passwordField){
            passwordField.setBorder(null);
            passwordField.setBounds(430,340,200,30);
        }
    }



}
