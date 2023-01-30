package sba.sms.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "courses")
    @ElementCollection
    @ManyToMany(
            //  targetEntity = Course.class,
            mappedBy = "email",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(name = "student_courses",
            joinColumns = {
                @JoinColumn(name = "student_email", referencedColumnName = "email")},
            inverseJoinColumns = {
                @JoinColumn(name = "courses_id", referencedColumnName = "id")})
    private List<Course> courses;

    public Student() {
    }

    public Student(String email, String name, String password) {

        this.email = email;
        this.name = name;

        this.password = password;

    }

    public Student(String email, String name, String password, List<Course> courses) {

        this.email = email;
        this.name = name;

        this.password = password;
        this.courses = courses;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public int hashCode() {

        int hash = 3;

        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.name);

        hash = 37 * hash + Objects.hashCode(this.password);
        hash = 37 * hash + Objects.hashCode(this.courses);

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

        final Student other = (Student) obj;

        if (!Objects.equals(this.email, other.email)) {
            return false;
        }

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        if (!Objects.equals(this.password, other.password)) {
            return false;
        }

        return Objects.equals(this.courses, other.courses);
    }

    @Override
    public String toString() {
        return "Student{" + "email=" + email + ", name=" + name + ", password=" + password + '}';
    }

}
