
public class Khachhang {
	private String id;
	private String name;
	private double phone;
	private String dateOfBirth;
	
	public Khachhang() {
	}

	public Khachhang(String id, String name, double phone, String dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPhone() {
		return phone;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}	