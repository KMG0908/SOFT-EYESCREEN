package dto;

public class DTO {
	private int id;
	private String passwd;
	private String name;
	private int isStudent;
	
	public DTO(int id, String passwd, String name, int isStudent) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.isStudent = isStudent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(int isStudent) {
		this.isStudent = isStudent;
	}
}
