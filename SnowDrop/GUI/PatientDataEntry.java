package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Entity.Patient;
import EntityList.PatientList;
import Files.PatientIO;

public class PatientDataEntry implements ActionListener,FocusListener{

    JLabel title, phoneNumLabel, nameLabel, dobLabel, genderLabel, doctorLabel;
    JTextField phoneNumTf, nameTf, dayTf, yearTf, doctorTf;
    JComboBox<String> monthCb, genderCb;
    JButton backBtn, nextBtn;
    JFrame previousPage;
    private LineBorder border1, border2;
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String[] genders = {"Male", "Female", "Lesbian","Gay","Bisexual","Transgender","Queer"};
    MyFrame frame;
    Billing billing;
    public static Patient patientData;
    public PatientDataEntry() {
        
        frame = new MyFrame("Patient Data Entry");
        Container c = frame.getContentPane();
        c.setBackground(new Color(158,123,181));
        
        border1 = new LineBorder(new Color(0,138,255), 5);
		    border2 = new LineBorder(new Color(237,67,55), 3);
        
        title = new JLabel("Patient Data Entry");
        title.setFont(new Font("Brush Script MT", Font.BOLD, 40));
        title.setForeground(new Color(255,255,255));
        title.setBounds(350, 20, 300, 40);
        c.add(title);
        
        phoneNumLabel = new JLabel("Phone Number:");
        phoneNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        phoneNumLabel.setForeground(new Color(255,255,255));
        phoneNumLabel.setBounds(320, 180, 150, 30);
        c.add(phoneNumLabel);

        
        phoneNumTf = new JTextField();
        phoneNumTf.setBounds(450, 180, 205, 30);
        c.add(phoneNumTf);
        phoneNumTf.addActionListener(this);
        phoneNumTf.addFocusListener(this);

        
        nameLabel = new JLabel("Patient Name:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nameLabel.setForeground(new Color(255,255,255));
        nameLabel.setBounds(320, 230, 150, 30);
        c.add(nameLabel);

        
        nameTf = new JTextField();
        nameTf.setBounds(450, 230, 205, 30);
        c.add(nameTf);
        nameTf.addActionListener(this);
        nameTf.addFocusListener(this);

        
        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dobLabel.setForeground(new Color(255,255,255));
        dobLabel.setBounds(320, 280, 150, 30);
        c.add(dobLabel);

        
        dayTf = new JTextField("Day");
        dayTf.setBounds(445, 280, 50, 30);
        c.add(dayTf);
        dayTf.addActionListener(this);
        dayTf.addFocusListener(this);

        
        monthCb = new JComboBox<>(months);
        monthCb.setBounds(503, 280, 90, 30);
        c.add(monthCb);
        monthCb.addActionListener(this);
        monthCb.addFocusListener(this);

        yearTf = new JTextField("Year");
        yearTf.setBounds(599, 280, 60, 30);
        c.add(yearTf);
        yearTf.addActionListener(this);
        yearTf.addFocusListener(this);

        genderLabel = new JLabel("Sex:");
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        genderLabel.setForeground(new Color(255,255,255));
        genderLabel.setBounds(320, 330, 150, 30);
        c.add(genderLabel);

        genderCb = new JComboBox<>(genders);
        genderCb.setBounds(450, 330, 200, 30);
        c.add(genderCb);
        genderCb.addActionListener(this);
        genderCb.addFocusListener(this);

        doctorLabel = new JLabel("Doctor Name:");
        doctorLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        doctorLabel.setForeground(new Color(255,255,255));
        doctorLabel.setBounds(320, 380, 150, 30);
        c.add(doctorLabel);

        doctorTf = new JTextField("Dr. ");
        doctorTf.setBounds(450, 380, 205, 30);
        c.add(doctorTf);
        doctorTf.addActionListener(this);
        doctorTf.addFocusListener(this);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(800, 600, 100, 40);
        nextBtn.setFocusable(false);
        c.add(nextBtn);
        nextBtn.addActionListener(this);

        backBtn = new JButton("B");
        backBtn.setBounds(0,0,50,30);
        backBtn.setFocusable(false);
        backBtn.setIcon(new ImageIcon("./Resources/backBtn.png"));
        c.add(backBtn);
        backBtn.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn){
          frame.dispose();
          previousPage.setVisible(true);
        }

        else if(e.getSource() == phoneNumTf){
          PatientList patientList = new PatientList();
          PatientIO.loadPatientFromFile(patientList);
          Patient p = patientList.getByPhoneNum(phoneNumTf.getText());
          if(p!=null){
            nameTf.setText(p.getName());
            dayTf.setText(Integer.toString(p.getDay()));
            monthCb.setSelectedIndex(p.getMonth()-1);
            yearTf.setText(Integer.toString(p.getYear()));
            genderCb.setSelectedItem(p.getGender());
            doctorTf.setText(p.getDoctorName());
          }
          else{
            nameTf.requestFocusInWindow();
          }
        }

        else if(e.getSource() == nameTf){
          dayTf.requestFocusInWindow();
        }

        else if(e.getSource() == dayTf){
          monthCb.requestFocusInWindow();
        }

        else if(e.getSource() == monthCb){
          yearTf.requestFocusInWindow();
        }

        else if(e.getSource() == yearTf){
          genderCb.requestFocusInWindow();
        }

        else if(e.getSource() == genderCb){
          doctorTf.requestFocusInWindow();
        }

        else if(e.getSource() == doctorTf || e.getSource() == nextBtn){
          patientData = new Patient();
          patientData.setName(nameTf.getText());
          patientData.setPhoneNum(phoneNumTf.getText());
          patientData.setDay(Integer.parseInt(dayTf.getText()));
          patientData.setMonth(monthCb.getSelectedIndex()+1);
          patientData.setYear(Integer.parseInt(yearTf.getText()));
          patientData.setGender(String.valueOf(genderCb.getSelectedItem()));
          patientData.setDoctorName(doctorTf.getText());
          PatientIO.writePatientInFile(patientData);

          billing = new Billing();
          billing.previousPage = frame;
          frame.setVisible(false);
          nameTf.setText("");
          phoneNumTf.setText("");
          dayTf.setText("");
          yearTf.setText("");
          doctorTf.setText("");
        }
    }

    // public static void main(String[] args) {
        // new PatientDataEntry();
    // }


    public void focusGained(FocusEvent e) {
     
      if(e.getSource() == phoneNumTf){
        phoneNumTf.setBorder(border1);
      }

      else if(e.getSource() == nameTf){
        nameTf.setBorder(border1);
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

      else if(e.getSource() == doctorTf){
        doctorTf.setBorder(border1);
      }

    }


    public void focusLost(FocusEvent e) {
     
      if(e.getSource() == phoneNumTf){
        phoneNumTf.setBorder(null);
      }

      else if(e.getSource() == nameTf){
        nameTf.setBorder(null);
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

      else if(e.getSource() == doctorTf){
        doctorTf.setBorder(null);
      }
    }
}
