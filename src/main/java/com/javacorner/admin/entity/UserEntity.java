package com.javacorner.admin.entity;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "user_email", nullable = false)
    private String userEmail;
    @Column(name = "user_pwd", nullable = false)
    private String userPwd;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = {@JoinColumn(name = "user_Id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<RoleEntity> roleEntitySet = new HashSet<>();

    @OneToOne(mappedBy = "userEntity")
    private StudentEntity studentEntity;

    @OneToOne(mappedBy = "userEntity")
    private InstructorEntity instructorEntity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId.equals(that.userId) && Objects.equals(userEmail, that.userEmail) && Objects.equals(userPwd, that.userPwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userEmail, userPwd);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

    public void assignRoleToUser(RoleEntity role){
        this.roleEntitySet.add(role);
        role.getUserEntitySet().add(this);
    }

    public void removeRoleFromUser(RoleEntity role){
        this.roleEntitySet.remove(this);
        role.getUserEntitySet().remove(this);
    }

    public UserEntity() {
    }

    public UserEntity(String userEmail, String userPwd) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Set<RoleEntity> getRoleEntitySet() {
        return roleEntitySet;
    }

    public void setRoleEntitySet(Set<RoleEntity> roleEntitySet) {
        this.roleEntitySet = roleEntitySet;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public InstructorEntity getInstructorEntity() {
        return instructorEntity;
    }

    public void setInstructorEntity(InstructorEntity instructorEntity) {
        this.instructorEntity = instructorEntity;
    }
}
