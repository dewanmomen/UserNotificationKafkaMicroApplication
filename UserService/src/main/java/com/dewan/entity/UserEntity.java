package com.dewan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long userid;

    @Column(name = "USERNAME")
    @Length(min = 8, message = "*Your user name must have at least 8 characters long")
    @NotEmpty(message = "*Please provide a unique user name")
    private String username;

    @Column(name = "PASSWORD")
    @Length(min = 10, message = "*Your password must have at least 10 characters long")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "EMAIL")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide your valid email")
    private String email;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
