package Entity;

public class Authority extends Person{
    private String email;
    private String address;
    private String username;
    private String password;
    private String role;

    public Authority() {}

    public Authority(String name,int day,int month,int year,String gender, String phoneNum,String email, String address, String username, String password, String role) {
		super(name,day,month,year,gender,phoneNum);
        setEmail(email);
        setAddress(address);
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.indexOf('@')>0 && email.indexOf(".com")>0 && email.indexOf(' ')==-1){
            this.email = email;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username.indexOf(' ') == -1){
			this.username = username;
		}
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() >= 6) {
            this.password = password;
        } 
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

 
    public void showDetails() {
		System.out.println("----------------------------");
        super.showDetails();
        System.out.println("Email: " + getEmail());
        System.out.println("Address: " + getAddress());
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
        System.out.println("Role: " + getRole());
        System.out.println("----------------------------");
    }
}
   
   