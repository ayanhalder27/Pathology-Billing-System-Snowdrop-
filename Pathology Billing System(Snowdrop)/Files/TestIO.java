package Files;

import Entity.Test;
import EntityList.TestList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class TestIO {
    
    
    public static void writeTestInFile(Test t){
		try{
			TestList testList = new TestList(1000);
			loadTestFromFile(testList);
			
			if(testList.insert(t)){
				String line = t.getTestID() + ";" + t.getTestName() + ";" + t.getTestPrice() + "\n";
				FileWriter fw = new FileWriter(new File("./Files/data/Tests.txt"),true);
				fw.write(line);
				fw.flush();
				fw.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    public static void loadTestFromFile(TestList testList){
		try{
			Scanner fsc = new Scanner(new File("./Files/data/Tests.txt"));
			while(fsc.hasNextLine()){
				String line = fsc.nextLine();
				String data[] = line.split(";");
				Test t = new Test(data[0],
								  data[1],
								  Float.parseFloat(data[2]));
				testList.insert(t);
			}
			fsc.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void deleteTestInFile(String testID) {
		try {
			TestList testList = new TestList(1000); 
			loadTestFromFile(testList);
	
			
			Test testToRemove = testList.getById(testID);
			if (testToRemove != null) {
				testList.removeById(testID);
				
				
				FileWriter fw = new FileWriter(new File("./Files/data/Tests.txt"), false); 
				for (int i = 0; i < testList.getTests().length; i++) {
					Test t = testList.getTests()[i];
					if (t != null) {
						String line = t.getTestID() + ";" + t.getTestName() + ";" + t.getTestPrice() + "\n";
						fw.write(line);
					}
				}
				fw.flush();
				fw.close();
				System.out.println("Test with ID " + testID + " deleted successfully.");
			} else {
				System.out.println("Test with ID " + testID + " not found.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
