package sba.sms.models;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "id", length = 50, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "instructor", length = 50, nullable = false)
    private String instructor;

    @ManyToMany
    @JoinColumn(name = "students")
    @ElementCollection
    private List<Student> students;

    public Course() {
    }

    public Course(Integer id, String name, String instructor, List<Student> students) {

        this.id = id;
        this.name = name;

        this.instructor = instructor;
        this.students = students;

    }

    public Course(Integer id, String name, String instructor) {

        this.id = id;
        this.name = name;

        this.instructor = instructor;

    }

    public Course(String name, String instructor) {

        this.name = name;
        this.instructor = instructor;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);

        hash = 79 * hash + Objects.hashCode(this.instructor);
        hash = 79 * hash + Objects.hashCode(this.students);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Course other = (Course) obj;

        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        if (!Objects.equals(this.instructor, other.instructor)) {
            return false;
        }

        return Objects.equals(this.students, other.students);
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", instructor=" + instructor + '}';
    }

}
