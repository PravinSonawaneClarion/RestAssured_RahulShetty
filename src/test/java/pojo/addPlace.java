package pojo;

import java.util.List;

public class addPlace {
	
	
	private int accuracy;
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public location getLocation() {
		return location;
	}
	public void setLocation(location location) {
		this.location = location;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public List<types> getTypes() {
		return types;
	}
	public void setTypes(List<types> types) {
		this.types = types;
	}
	private String name;
	private String address;
	private String website;
	private String language; 
	private location location;
	private String phone_number;
	private List<types> types;
	
	
	
	
	
	
	
	
	
	

}