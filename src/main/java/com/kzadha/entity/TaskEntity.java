package com.kzadha.entity;

import com.kzadha.model.enums.TaskStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskEntity {
 
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "vdescription", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="nuserid", nullable=false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="nexecutoruserid")
    private UserEntity executor;

    @Column(name = "vstatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

}