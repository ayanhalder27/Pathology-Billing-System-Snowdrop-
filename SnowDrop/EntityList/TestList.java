package EntityList;
import Entity.Test;

public class TestList{
	private Test tests[];
	
	public TestList(){
		tests = new Test[5];
	}
	
	public TestList(int size){
		tests = new Test[size];
	}
	
	public boolean insert(Test t){
		boolean flag = false;
		boolean idTaken = false;
		
		for(int i=0;i<tests.length;i++){
			if(tests[i] != null){
				if(tests[i].getTestID().equals(t.getTestID())){
					System.out.println(t.getTestID() + " Id already taken.");
					idTaken = true;
					break;
				}
			}
		}
		
		if(!idTaken){
			for(int i=0; i<tests.length; i++){
				if(tests[i] == null){
				tests[i] = t;
				flag = true; //completed
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
	
	public Test getById(String id){
		Test t = null;
		for(int i=0; i<tests.length; i++){
			if(tests[i] != null){
				if(tests[i].getTestID().equals(id)){
					t = tests[i];
					break;
				}
			}
		}
		return t;
	}
	
	public void removeById(String id){
		boolean flag = false;
		for(int i=0; i<tests.length; i++){
			if(tests[i] !=null){
				if(tests[i].getTestID().equals(id)){
					tests[i] = null;
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
		for(int i=0; i<tests.length; i++){
			if(tests[i] != null){
				tests[i].showDetails();
			}
		}
	}

	public Test[] getTests() {
		return tests;
	}
}