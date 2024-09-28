package Entity;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Patient extends Person {
    private String doctorName;
    private LocalDate birthDate;

    public Patient(){}

    public Patient(String name, int day, int month, int year, String gender, String phoneNum, String doctorName) {
        super(name, day, month, year, gender, phoneNum);
        setDoctorName(doctorName);
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public int getAge() { 
        birthDate = LocalDate.of(getYear(), getMonth(), getDay());
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        } 
		else {
            return 0;
        }
    }

    public String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(formatter);
    }

    public void showDetails() {
        System.out.println("----------------------------");
        super.showDetails();
        System.out.println("Doctor Name: " + doctorName);
        System.out.println("Age: " + getAge() + " Years");
        System.out.println("----------------------------");
    }

    public String getPatientAsString(){
        String s = "----------------------------\n" + 
                   "Name: " + getName() + "\n" +
                   "Date of Birth: " + getDay() + "." + getMonth() + "." + getYear() + "\n" +
                   "Age: " + getAge() + "\n" +
                   "Sex: " + getGender() + "\n" +
                   "Phone Number: " + getPhoneNum() + "\n" + 
                   "Doctor Name: " + doctorName + "\n" +
                   "----------------------------";
        
        return s;
    }
}
