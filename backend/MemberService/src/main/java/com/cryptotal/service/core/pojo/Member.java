package com.cryptotal.service.core.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false, unique = true)
    private Long userId;

    @Column(name = "firstName", nullable = false, length = 255)
    private String firstName;

    @Column(name = "middleName", length = 255)
    private String middleName;

    @Column(name = "lastName", nullable = false, length = 255)
    private String lastName;

    @Column(name = "preferredName", length = 255)
    private String preferredName;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "cellPhone", nullable = false, length = 20)
    private String cellPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "ENUM('Male', 'Female', 'Others', 'Prefer not to tell')")
    private Gender gender;

    @Column(name = "birthday")
    private Date birthday;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "memberSince", nullable = false)
    private LocalDateTime memberSince;

    @Column(name = "remainingCredit", nullable = false)
    private Long remainingCredit;
}

enum Gender {
    Male, Female, Others, Prefer_not_to_tell
}
