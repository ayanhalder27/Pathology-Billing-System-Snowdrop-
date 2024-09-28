package Files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Entity.Authority;
import Entity.Patient;
import EntityList.AuthorityList;
import EntityList.PatientList;

public class PatientIO {
    public static void writePatientInFile(Patient p){
		try{
			PatientList patientList = new PatientList(1000);
			loadPatientFromFile(patientList);

			if(patientList.insert(p)){
				String line = p.getName() + ";" + 
							p.getDay() + ";" + 
							p.getMonth() + ";" + 
							p.getYear() + ";" + 
							p.getGender() + ";" + 
							p.getPhoneNum() + ";" + 
							p.getDoctorName() + "\n";
				FileWriter fw = new FileWriter(new File("./Files/data/Patients.txt"),true);
				fw.write(line);
				fw.flush();
				fw.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    public static void loadPatientFromFile(PatientList patientList){
		try{
			Scanner fsc = new Scanner(new File("./Files/data/Patients.txt"));
			while(fsc.hasNextLine()){
				String line = fsc.nextLine();
				String data[] = line.split(";");
				Patient p = new Patient(data[0],
								  Integer.parseInt(data[1]),
				                  Integer.parseInt(data[2]),
                                  Integer.parseInt(data[3]),
                                  data[4],
                                  data[5],
                                  data[6]);
				patientList.insert(p);
			}
			fsc.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
