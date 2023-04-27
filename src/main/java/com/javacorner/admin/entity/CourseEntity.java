package com.javacorner.admin.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long courseId;
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @Column(name = "course_duration", nullable = false)
    private String courseDuration;
    @Column(name = "course_desc", nullable = false)
    private String courseDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id", nullable = false)
    private InstructorEntity instructorEntity;


    /**
     * Establishing the relationship between courses and students
     * */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrolled_in",
        joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<StudentEntity> studentEntitySet = new HashSet<>();


    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", courseDesc='" + courseDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return courseId.equals(that.courseId) && Objects.equals(courseName, that.courseName) && Objects.equals(courseDuration, that.courseDuration) && Objects.equals(courseDesc, that.courseDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseDuration, courseDesc);
    }


    public void assignStudentToCourse(StudentEntity student){
        this.studentEntitySet.add(student);
        student.getCourseEntitySet().add(this);
    }

    public void removeStudentFromCourse(StudentEntity student){
        this.studentEntitySet.remove(student);
        student.getCourseEntitySet().remove(this);
    }



    public CourseEntity() {
    }

    public CourseEntity(String courseName, String courseDuration, String courseDesc, InstructorEntity instructorEntity) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseDesc = courseDesc;
        this.instructorEntity = instructorEntity;
    }


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public InstructorEntity getInstructor() {
        return instructorEntity;
    }

    public void setInstructor(InstructorEntity instructorEntity) {
        this.instructorEntity = instructorEntity;
    }

    public Set<StudentEntity> getStudentSet() {
        return studentEntitySet;
    }

    public void setStudentSet(Set<StudentEntity> studentEntitySet) {
        this.studentEntitySet = studentEntitySet;
    }
}
