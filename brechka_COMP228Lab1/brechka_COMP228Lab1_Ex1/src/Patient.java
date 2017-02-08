
public class Patient {
	private int patientId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String province;
	private String postalCode;
	
	public Patient(int patientId, String firstName, String lastName,
                   String address, String city, String province, String postalCode){
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
	}
	public Patient(){}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince() {
		return province;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPostalCode() {
		return postalCode;
	}

	public String getPatientInfo()
	{
		return String.format("\n--------------------------\n" +
						"Patient ID: %d\n" +
						"Patient Name: %s %s\n" +
						"Patient address:" +
						"\n\t\t%s" +
						"\n\t\t%s, %s" +
						"\n\t\t%s" +
						"\n--------------------------\n",
				patientId, firstName, lastName, address, city, province, postalCode);
	}
}
