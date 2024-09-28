package Entity;

public class Test {
    private String testID;
    private String testName;
    private float testPrice;

    public Test() {}

    public Test(String testID, String testName, float testPrice) {
         setTestID(testID);
		 setTestName(testName);
		 setTestPrice(testPrice);
	}

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestPrice(float testPrice) {
        if(testPrice>0){
            this.testPrice = testPrice;
        }
    }

    public float getTestPrice() {
        return testPrice;
    }

    public void showDetails() {
        System.out.println("----------------------------");
        System.out.println("Test ID: " + testID);
        System.out.println("Test Name: " + testName);
        System.out.println("Test Price: " + testPrice);
        System.out.println("----------------------------");
    }
}