/**
* Author: Olafur Palsson
* HImail: olp6@gmail.com
* Actual: olafur.palsson
* Heiti verkefnis: server
*/

package server.data.exercise;

import javax.persistence.*;
import java.util.ArrayList;

@Entity // This tells Hibernate to make a table out of this class
public class ExerciseEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  long id;
  private String name;
  private String description;
  private String imgURL;

  public ExerciseEntity(String name) {
    this.name = name;
  }
  public ExerciseEntity() {
    this.name = "No name";
  }

  /** Getters and setters ... */

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

  public void setDescription(ArrayList<String> description) {
    String desc = "";
    for(String s : description)
    desc += s;
    this.description = desc;
  }

  public String getImgURL() {
    return imgURL;
  }

  public void setImgURL(String imgURL) {
    this.imgURL = imgURL;
  }
}
