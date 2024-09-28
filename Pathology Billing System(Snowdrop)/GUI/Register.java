package GUI;

import Entity.Authority;
import EntityList.AuthorityList;
import Files.AuthorityIO;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Register implements ActionListener,FocusListener,KeyListener{
    
    MyFrame frame;
    JLabel createAccountLabel,nameLabel,mobileLabel,emailLabel,usernameLabel,dobLabel,genderLabel,addressLabel,passwordLabel,confirmPasswordLabel,phnNumWarningLabel,emailWarningLabel, usernameWarningLabel;
    JTextField nameTf,mobileTf,emailTf,usernameTf,dayTf,yearTf,addressTf;
    JComboBox<String> monthCb,genderCb;
    JPasswordField passwordTf,confirmpasswordTf;
    JLayeredPane layer;
    JButton createAccountBtn, showPasswordBtn1, showPasswordBtn2, backBtn;
    AuthorityList authorityList;
    private LineBorder border1, border2;
    private String role;
    private String[] months ={"January","February","March","April","May", "June", "July", "August", "September", "October", "November", "December"};
    private String[] genders ={"Male","Female","Other"};
    public JFrame previousPage;


    public Register(String role){
        this.role = role;
        frame = new MyFrame("Register");
        Container c = frame.getContentPane();
		c.setBackground(new Color(106,90,205));

        border1 = new LineBorder(new Color(0,138,255), 5);
	    border2 = new LineBorder(new Color(237,67,55), 3);

        createAccountLabel = new JLabel("Create Account(" + role + ")");
        createAccountLabel.setFont(new Font("Brush Script MT",Font.BOLD, 45));
        createAccountLabel.setForeground(new Color(255,255,255));
        createAccountLabel.setBounds(350,20,450,40);
        c.add(createAccountLabel);

        nameLabel = new JLabel("Your Name:");
        nameLabel.setBounds(310,80,200,30);
        nameLabel.setFont(new Font("Tahoma",Font.BOLD, 13));
        nameLabel.setForeground(new Color(255,255,255));
        c.add(nameLabel);

        phnNumWarningLabel = new JLabel("Phone Number Already Used");
        phnNumWarningLabel.setBounds(440,153,200,30);
        phnNumWarningLabel.setForeground(Color.RED);
        phnNumWarningLabel.setVisible(false);
        c.add(phnNumWarningLabel);

        emailWarningLabel = new JLabel("E-mail Already Used");
        emailWarningLabel.setBounds(440,203,200,30);
        emailWarningLabel.setForeground(Color.RED);
        emailWarningLabel.setVisible(false);
        c.add(emailWarningLabel);

        usernameWarningLabel = new JLabel("Username Already Used");
        usernameWarningLabel.setBounds(440,253,200,30);
        usernameWarningLabel.setForeground(Color.RED);
        usernameWarningLabel.setVisible(false);
        c.add(usernameWarningLabel);

        nameTf = new JTextField();
        nameTf.setBounds(430,80,200,30);
        nameTf.requestFocusInWindow();
        nameTf.addActionListener(this);
        nameTf.addFocusListener(this);
        c.add(nameTf);

        mobileLabel = new JLabel("Mobile Number:");
        mobileLabel.setBounds(310,130,200,30);
        mobileLabel.setFont(new Font("Tahoma",Font.BOLD, 13));
        mobileLabel.setForeground(new Color(255,255,255));
        c.add(mobileLabel);

        mobileTf = new JTextField();
        mobileTf.setBounds(430,130,200,30);
        mobileTf.addActionListener(this);
        mobileTf.addFocusListener(this);
        mobileTf.addKeyListener(this);
        c.add(mobileTf);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(310,180,200,30);
        emailLabel.setFont(new Font("Tahoma",Font.BOLD, 13));
        emailLabel.setForeground(new Color(255,255,255));
        c.add(emailLabel);

        emailTf = new JTextField();
        emailTf.setBounds(430,180,200,30);
        emailTf.addActionListener(this);
        emailTf.addFocusListener(this);
        emailTf.addKeyListener(this);
        c.add(emailTf);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(310,230,100,30);
        usernameLabel.setFont(new Font("Tahoma",Font.BOLD, 13));
        usernameLabel.setForeground(new Color(255,255,255));
        c.add(usernameLabel);

        usernameTf = new JTextField();
        usernameTf.setBounds(430,230,200,30);
        usernameTf.addActionListener(this);
        usernameTf.addFocusListener(this);
        usernameTf.addKeyListener(this);
        c.add(usernameTf);

        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(310,280,100,30);
        dobLabel.setFont(new Font("Tahoma",Font.BOLD, 13));
        dobLabel.setForeground(new Color(255,255,255));
        c.add(dobLabel);
        
        dayTf = new JTextField("Day");
        dayTf.setBounds(420,280,50,30);
        dayTf.addActionListener(this);
        dayTf.addFocusListener(this);
        c.add(dayTf);
        
        monthCb = new JComboBox<>(months);
        monthCb.setBounds(480,280,90,30);
        monthCb.addActionListener(this);
        monthCb.addFocusListener(this);
        c.add(monthCb);

        yearTf = new JTextField("Year");
        yearTf.setBounds(580,280,60,30);
        yearTf.addActionListener(this);
        yearTf.addFocusListener(this);
        c.add(yearTf);

        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(310,330,100,30);
        genderLabel.setFont(new Font("Tahoma",Font.BOLD, 13));
        genderLabel.setForeground(new Color(255,255,255));
        c.add(genderLabel);

        genderCb = new JComboBox<>(genders);
        genderCb.setBounds(430,330,200,30);
        genderCb.addActionListener(this);
        genderCb.addFocusListener(this);
        c.add(genderCb);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(310,380,200,30);
        addressLabel.setFont(new Font("Tahoma",Font.BOLD, 13));
        addressLabel.setForeground(new Color(255,255,255));
        c.add(addressLabel);

        addressTf = new JTextField();
        addressTf.setBounds(430,380,200,30);
        addressTf.addActionListener(this);
        addressTf.addFocusListener(this);
        c.add(addressTf);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(310,430,100,30);
        passwordLabel.setFont(new Font("Tahoma",Font.BOLD, 13));
        passwordLabel.setForeground(new Color(255,255,255));
        c.add(passwordLabel);

        passwordTf = new JPasswordField();
        passwordTf.setBounds(430,430,200,30);
        passwordTf.setEchoChar('*');
        passwordTf.addActionListener(this);
        passwordTf.addFocusListener(this);
        c.add(passwordTf);

        showPasswordBtn1 = new JButton();
        showPasswordBtn1.setIcon(new ImageIcon("./Resources/eyeClose.png"));
        showPasswordBtn1.setBounds(632,435,20,20);
        showPasswordBtn1.addActionListener(this);
        c.add(showPasswordBtn1);

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(310,480,150,30);
        confirmPasswordLabel.setFont(new Font("Tahoma",Font.BOLD, 12));
        confirmPasswordLabel.setForeground(new Color(255,255,255));
        c.add(confirmPasswordLabel);

        confirmpasswordTf = new JPasswordField();
        confirmpasswordTf.setBounds(430,480,200,30);
        confirmpasswordTf.setEchoChar('*');
        confirmpasswordTf.addActionListener(this);
        confirmpasswordTf.addFocusListener(this);
        c.add(confirmpasswordTf);

        showPasswordBtn2 = new JButton();
        showPasswordBtn2.setIcon(new ImageIcon("./Resources/eyeClose.png"));
        showPasswordBtn2.setBounds(632,485,20,20);
        showPasswordBtn2.addActionListener(this);
        c.add(showPasswordBtn2);

        createAccountBtn = new JButton("Create Account");
        createAccountBtn.setBounds(400, 550, 150, 40);
        createAccountBtn.setFocusable(false);
        createAccountBtn.addActionListener(this);
        c.add(createAccountBtn);

        backBtn = new JButton("B");
        backBtn.setBounds(0,0,50,30);
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);
        backBtn.setIcon(new ImageIcon("./Resources/backBtn.png"));
        c.add(backBtn);
        

        authorityList = new AuthorityList();
        AuthorityIO.loadAuthorityFromFile(authorityList);
    }

    // public static void main(String[] args) {
        // Register rr = new Register("Owner");
    // }

    public void actionPerformed(ActionEvent e) {
        Authority a = new Authority();
        if(e.getSource() == createAccountBtn || e.getSource() == confirmpasswordTf){
            
            if(String.valueOf(passwordTf.getPassword()).equals(String.valueOf(confirmpasswordTf.getPassword()))){
                a.setName(nameTf.getText());
                a.setPhoneNum(mobileTf.getText());
                a.setEmail(emailTf.getText());
                a.setUsername(usernameTf.getText());
                a.setDay(Integer.parseInt(dayTf.getText()));
                a.setMonth(monthCb.getSelectedIndex()+1);
                a.setYear(Integer.parseInt(yearTf.getText()));
                a.setGender((String) genderCb.getSelectedItem());
                a.setAddress(addressTf.getText());
                a.setPassword(String.valueOf(passwordTf.getPassword()));
                a.setRole(role);
                if(authorityList.insert(a) == true){
                    AuthorityIO.writeAuthorityInFile(a);
                    JOptionPane.showMessageDialog(frame, "Successfull registered " + role);
                    frame.dispose();
                    previousPage.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Unique Id already exixts","Error",JOptionPane.ERROR_MESSAGE); 
                }  
            }
            else{
                JOptionPane.showMessageDialog(frame,"Password did not match","Error",JOptionPane.ERROR_MESSAGE);  
            }
            
        }
        
        else if(e.getSource() == nameTf){
            a.setName(nameTf.getText());
            if(a.getName() !=null){
                mobileTf.requestFocusInWindow();
            }
            else{
                nameTf.setBorder(border2);
            }
        }

        else if(e.getSource() == mobileTf){
            a.setPhoneNum(mobileTf.getText());
            if(a.getPhoneNum() !=null){
                emailTf.requestFocusInWindow();
            }
            else{
                mobileTf.setBorder(border2);
            }
        }

        else if(e.getSource() == emailTf){
            a.setEmail(emailTf.getText());
            if(a.getEmail() !=null){
                usernameTf.requestFocusInWindow();
            }
            else{
                emailTf.setBorder(border2);
            }
        }

        else if(e.getSource() == usernameTf){
            a.setUsername(usernameTf.getText());
            if(a.getUsername() !=null){
                dayTf.requestFocusInWindow();
            }
            else{
                usernameTf.setBorder(border2);
            }
        }

        else if(e.getSource() == dayTf){
            a.setDay(Integer.parseInt(dayTf.getText()));
            if(a.getDay() !=0){
                monthCb.requestFocusInWindow();
            }
            else{
                dayTf.setBorder(border2);
            }
        }

        else if(e.getSource() == monthCb){
            yearTf.requestFocusInWindow();
        }

        else if (e.getSource() == yearTf){
            a.setYear(Integer.parseInt(yearTf.getText()));
            if(a.getYear() !=0){
                genderCb.requestFocusInWindow();
            }
            else{
                yearTf.setBorder(border2);
            }
        }

        else if(e.getSource() == genderCb){
            addressTf.requestFocusInWindow();
        }

        else if (e.getSource() == addressTf){
            passwordTf.requestFocusInWindow();
        }
        else if (e.getSource() == passwordTf){
            a.setPassword(String.valueOf(passwordTf.getPassword()));
            if(a.getPassword() !=null){
                confirmpasswordTf.requestFocusInWindow();
            }
            else{
                passwordTf.setBorder(border2);
            }
        }
        else if (e.getSource() == backBtn){
            frame.dispose();
            previousPage.setVisible(true);
        }
        else if(e.getSource() == showPasswordBtn1){
            if(passwordTf.getEchoChar()=='*'){
                passwordTf.setEchoChar((char) 0);
                showPasswordBtn1.setIcon(new ImageIcon("./Resources/eyeOpen.png"));
            }
            
            else{
                passwordTf.setEchoChar('*');
                showPasswordBtn1.setIcon(new ImageIcon("./Resources/eyeClose.png"));
            }
            passwordTf.requestFocusInWindow();
        }
        else if(e.getSource() == showPasswordBtn2){
            if(confirmpasswordTf.getEchoChar()=='*'){
                confirmpasswordTf.setEchoChar((char) 0);
                showPasswordBtn2.setIcon(new ImageIcon("./Resources/eyeOpen.png"));
            }
            
            else{
                confirmpasswordTf.setEchoChar('*');
                showPasswordBtn2.setIcon(new ImageIcon("./Resources/eyeClose.png"));
            }
            confirmpasswordTf.requestFocusInWindow();
        }
    }

    public void focusGained(FocusEvent e) {
        
        if(e.getSource() == nameTf){
            nameTf.setBorder(border1);
        }

        else if(e.getSource() == mobileTf){
            mobileTf.setBorder(border1);
        }
        
        else if(e.getSource() == emailTf){
            emailTf.setBorder(border1);
        }

        else if(e.getSource() == usernameTf){
            usernameTf.setBorder(border1);
        }

        else if(e.getSource() == dayTf){
            dayTf.setBorder(border1);
            if(dayTf.getText().equals("Day")){
                dayTf.setText("");
            }
        }

        else if(e.getSource() == monthCb){
            monthCb.setBorder(border1);
        }

        else if(e.getSource() == yearTf){
            yearTf.setBorder(border1);
            if(yearTf.getText().equals("Year")){
                yearTf.setText("");
            }
        }
        else if(e.getSource() == genderCb){
            genderCb.setBorder(border1);
        }

        else if(e.getSource() == addressTf){
            addressTf.setBorder(border1);
        }

        else if(e.getSource() == passwordTf){
            passwordTf.setBorder(border1);
        }

        else if(e.getSource() == confirmpasswordTf){
            confirmpasswordTf.setBorder(border1);
        }

  
      }
  
  
      public void focusLost(FocusEvent e) {
        
        if(e.getSource() == nameTf){
            nameTf.setBorder(null);
        }

        else if(e.getSource() == mobileTf){
            if(mobileTf.getBorder() != border2){
            mobileTf.setBorder(null);
            }
        }
        
        else if(e.getSource() == emailTf){
            if(emailTf.getBorder() != border2){
            emailTf.setBorder(null);
            }
        }

        else if(e.getSource() == usernameTf){
            if(usernameTf.getBorder() != border2){
            usernameTf.setBorder(null);
            }
        }

        else if(e.getSource() == dayTf){
            dayTf.setBorder(null);
        }

        else if(e.getSource() == monthCb){
            monthCb.setBorder(null);
        }

        else if(e.getSource() == yearTf){
            yearTf.setBorder(null);
        }
        else if(e.getSource() == genderCb){
            genderCb.setBorder(null);
        }

        else if(e.getSource() == addressTf){
            addressTf.setBorder(null);
        }

        else if(e.getSource() == passwordTf){
            passwordTf.setBorder(null);
        }

        else if(e.getSource() == confirmpasswordTf){
            confirmpasswordTf.setBorder(null);
        }

        
      }


    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        Authority a = new Authority();
        if(e.getSource() == mobileTf){
            a = authorityList.getById(mobileTf.getText());
            phnNumWarningLabel.setVisible(false);
            if(a.getPhoneNum().equals(mobileTf.getText())){
                mobileTf. setBorder(border2);
                phnNumWarningLabel.setVisible(true);
            }
        }

        else if(e.getSource() == emailTf){
            a = authorityList.getById(emailTf.getText());
            emailWarningLabel.setVisible(false);
            if(a.getEmail().equals(emailTf.getText())){
                emailTf. setBorder(border2);
                emailWarningLabel.setVisible(true);
            }
        }

        else if(e.getSource() == usernameTf){
            a = authorityList.getById(usernameTf.getText());
            usernameWarningLabel.setVisible(false);
            if(a.getUsername().equals(usernameTf.getText())){
                usernameTf. setBorder(border2);
                usernameWarningLabel.setVisible(true);
            }
        }
    }

}