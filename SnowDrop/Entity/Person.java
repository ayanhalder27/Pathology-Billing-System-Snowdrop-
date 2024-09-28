package Entity;

public abstract class Person{
	private String name;
	private int day;
	private int month;
	private int year;
	private String gender;
	private String phoneNum;
	
	public Person(){};
	
	public Person(String name,int day,int month,int year,String gender, String phoneNum){
		setName(name);
		setDay(day);
		setMonth(month);
		setYear(year);
		setGender(gender);
		setPhoneNum(phoneNum);
	}
	
	public void setName(String name){
		if(!name.isEmpty()){
			this.name = name;
		}
	}
	
	public String getName(){
		return name;
	}
	
	public void setDay(int day){
		if(day>=1 && day<=31){
			this.day = day;
		}
	}
		
	public int getDay(){
		return day;
	}
	public void setMonth(int month){
		if(month>=1 && month<=12){
			this.month = month;
		}
	}
	
	public int getMonth(){
		return month;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	public int getYear(){
		return year;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getGender(){
		return gender;
	}
	
	public void setPhoneNum(String phoneNum){
		if(phoneNum.indexOf("01")==0 && phoneNum.length()==11 && phoneNum.matches("\\d+") && phoneNum.indexOf(' ')==-1){
			this.phoneNum = phoneNum;
		}
	}
	
	public String getPhoneNum(){
		return phoneNum;
	}
	
	public void showDetails(){
		System.out.println("Name: "+name);
		System.out.println("Date of Birth: " + day + "." + month + "." + year);
		System.out.println("Gender: "+gender);
		System.out.println("Phone Number: "+phoneNum);
	}
}
