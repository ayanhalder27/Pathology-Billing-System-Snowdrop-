package EntityList;
import Entity.Authority;

public class AuthorityList {
    private Authority authorities[];

    public AuthorityList(){
        authorities = new Authority[5];
    }

    public AuthorityList(int size){
        authorities = new Authority[size];
    }

    public boolean insert(Authority a){
        boolean flag = false;
        boolean idTaken = false;
        
        for(int i=0; i<authorities.length; i++){
            if(authorities[i] != null){
                if(authorities[i].getUsername().equals(a.getUsername())){
                    System.out.println(a.getUsername() + " Username already taken");
                    idTaken = true;
                }
                if(authorities[i].getEmail().equals(a.getEmail())){
                    System.out.println(a.getEmail() + " E-mail already taken");
                    idTaken = true;
                }
                if(authorities[i].getPhoneNum().equals(a.getPhoneNum())){
                    System.out.println(a.getPhoneNum() + " Phone number already taken");
                    idTaken = true;
                    break;
                }
            }
	
        }

        if(!idTaken){
            for(int i=0; i<authorities.length; i++){
                if(authorities[i] == null){
                    authorities[i] = a;
                    flag =true;
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
		return !idTaken;
    }

    public Authority getById(String id){
        Authority a = null;
        for(int i=0; i<authorities.length; i++){
            if(authorities[i] != null){
                if(authorities[i].getUsername().equals(id) || authorities[i].getEmail().equals(id) || authorities[i].getPhoneNum().equals(id)){
                    a = authorities[i];
                    break;
                }
            }
        }

        return a;
    }

    public void removeById(String id){
        boolean flag = false;
        for(int i=0; i<authorities.length; i++){
			if(authorities[i] !=null){
				if(authorities[i].getUsername().equals(id) || authorities[i].getEmail().equals(id) || authorities[i].getPhoneNum().equals(id)){
					authorities[i] = null;
					flag = true;
					break;
				}
			}
		}
		
		if(flag){
			System.out.println("Id: " + id + " Removed successfully..");
		}
		else{
			System.out.println("Failed to remove.(Recheck Id)!");
		}
    }

    public void showAll(){
        for(int i=0; i<authorities.length; i++){
            if(authorities[i] !=null){
                authorities[i].showDetails();
            }
        }
    }
}
