package server.data.user;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class UserEntity {

    private Long id;
    @Id
    private String email;
	private String name;
    private String password;

    public UserEntity() {}

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}