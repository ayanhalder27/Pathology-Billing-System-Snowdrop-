package Files;

import Entity.Authority;
import Entity.Test;
import EntityList.AuthorityList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AuthorityIO {
    
	public static String name;
	
	public static String checkUser(String username,String userPass){
		try{
			Scanner fsc = new Scanner(new File("./Files/data/Authorities.txt"));
			while(fsc.hasNextLine()){
				String line = fsc.nextLine();
				String data[] = line.split(";");
				name = data[0];
				if(username.equals(data[5]) || username.equals(data[6]) || username.equals(data[8])){
					if(userPass.equals(data[9])){
						return data[10];
					}
					else{
						return "warning2";
					}
				}
			}
			fsc.close();
		}
		catch(Exception ex){
			System.out.println("Cannot Read File");
		}
		return "warning1";
	}

	public static void writeAuthorityInFile(Authority a){
		try{
			AuthorityList authorityList = new AuthorityList(100);
			loadAuthorityFromFile(authorityList);
			
			if(authorityList.insert(a)){		
				String line = a.getName() + ";" + 
							  a.getDay() + ";" + 
							  a.getMonth() + ";" + 
							  a.getYear() + ";" + 
							  a.getGender() + ";" + 
							  a.getPhoneNum() + ";" + 
							  a.getEmail() + ";" + 
							  a.getAddress() + ";" +
							  a.getUsername() + ";" +
							  a.getPassword() + ";" + 
							  a.getRole() + "\n";
							  
				FileWriter fw = new FileWriter(new File("./Files/data/Authorities.txt"),true);
				fw.write(line);
				fw.flush();
				fw.close();
				
				if(a.getRole().equals("Owner")){					  
					FileWriter f1 = new FileWriter(new File("./Files/data/Owners.txt"),true);
					f1.write(line);
					f1.flush();
					f1.close();
				}

				else if(a.getRole().equals("Employee")){					  
					FileWriter f2 = new FileWriter(new File("./Files/data/Employees.txt"),true);
					f2.write(line);
					f2.flush();
					f2.close();
				}
				
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    public static void loadAuthorityFromFile(AuthorityList authorityList){
		try{
			Scanner fsc = new Scanner(new File("./Files/data/Authorities.txt"));
			while(fsc.hasNextLine()){
				String line = fsc.nextLine();
				String data[] = line.split(";");
				Authority a = new Authority(data[0],
								  Integer.parseInt(data[1]),
				                  Integer.parseInt(data[2]),
                                  Integer.parseInt(data[3]),
                                  data[4],
                                  data[5],
                                  data[6],
                                  data[7],
                                  data[8],
								  data[9],
								  data[10]);
				authorityList.insert(a);
			}
			fsc.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
