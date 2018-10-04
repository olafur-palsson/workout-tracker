/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package server.exercise;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class ExerciseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    long id;
	private String name;
	private String description;
	//String link

    public ExerciseEntity(String name) {
	    this.name = name;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

