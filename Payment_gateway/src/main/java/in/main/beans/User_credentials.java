package in.main.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User_credentials {

	
	@Id
	 private int userId;
	  private String name;
	  private String password;
	  private Long  Amount;
	  private String FatherName;
	  private String City;
	  private int UPI;
	  
	  
	  
	  public int getUserId() {
		  return userId;
	  }
	  public void setUserId(int userId) {
		  this.userId = userId;
	  }
	  public String getName() {
		  return name;
	  }
	  public void setName(String name) {
		  this.name = name;
	  }
	  
	  public String getPassword() {
		  return password;
	  }
	  public void setPassword(String password) {
		  this.password = password;
	  }
	  public Long getAmount() {
		  return Amount;
	  }
	  public void setAmount(Long amount) {
		  Amount = amount;
	  }
	  public String getFatherName() {
		  return FatherName;
	  }
	  public void setFatherName(String fatherName) {
		  FatherName = fatherName;
	  }
	  public String getCity() {
		  return City;
	  }
	  public void setCity(String city) {
		  City = city;
	  }
	  public int getUPI() {
		  return UPI;
	  }
	  public void setUPI(int uPI) {
		  UPI = uPI;
	  }
	  
	  
	  
}
