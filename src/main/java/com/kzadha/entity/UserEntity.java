package com.kzadha.entity;

import com.kzadha.model.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
 
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
 
    @Column(name = "vname", length = 255, nullable = false)
    private String name;
    @Column(name = "vemail", length = 255, nullable = false)
    private String email;
    @Column(name = "vpassword", length = 255, nullable = false)
    private String password;
//    @ElementCollection(targetClass = UserRole.class)
    @Column(name = "vroles", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole roles;

}