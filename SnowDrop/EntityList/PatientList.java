package EntityList;
import Entity.Patient;

public class PatientList {
    private Patient patients[];
    
    public PatientList(){
        patients = new Patient[1000];
    }
    
    public PatientList(int size){
        patients = new Patient[size];
    }

    public boolean insert(Patient p){
        boolean flag = false;
        boolean numberTaken = false;

        for(int i=0; i<patients.length;i++){
            if(patients[i] !=null){
                if(patients[i].getPhoneNum().equals(p.getPhoneNum())){
                System.out.println(p.getPhoneNum() + " Number is already taken");
                numberTaken = true;
                break; 
                }
            }
        }

        if(!numberTaken){
            for(int i=0; i<patients.length; i++){
                if(patients[i] == null){
                    patients[i] = p;
                    flag = true;
                    break;
                }
            }
        }

        if(flag){
			System.out.println("Inserted Successfully");
		}
		else{
			System.out.println("Insert Failed");
		}
        return !numberTaken;
    }

    public Patient getByPhoneNum(String phoneNum){
        Patient p = null;
        for(int i=0; i<patients.length; i++){
            if(patients[i] !=null){
                if(patients[i].getPhoneNum().equals(phoneNum)){
                    p = patients[i];
                    break;
                }
            }
        }
        return p;
    }

    public void removeByPhoneNum(String phoneNum){
        boolean flag = false;
        for(int i=0; i<patients.length; i++){
            if(patients[i] !=null){
                if(patients[i].getPhoneNum().equals(phoneNum)){
                    patients[i] = null;
                    flag = true;
                    break;
                }
            }
        }

        if(flag){
			System.out.println("Phone Number: " + phoneNum + " Removed successfully..");
		}
		else{
			System.out.println("Failed to remove.(Recheck Id)!");
		}
    }

    public void showAll(){
		for(int i=0; i<patients.length; i++){
			if(patients[i] != null){
				patients[i].showDetails();
			}
		}
	}
}
