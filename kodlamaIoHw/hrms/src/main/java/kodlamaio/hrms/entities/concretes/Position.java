package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;

@Entity
@Table(name = "job_position_names")
public class Position {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "job_name")
    private String job_name;

    public Position() {
    }

    public Position(int id, String job_name) {
        super();
        this.id = id;
        this.job_name = job_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String positionName) {
        this.job_name = positionName;
    }
}
