package com.kzadha.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userstotasks")
@Data
@Builder
public class UserTaskEntity {
 
    @EmbeddedId
    private UserTaskKey id;
 
    @ManyToOne
    @MapsId("nuserid")
    @JoinColumn(name = "nuserid")
    private UserEntity user;
 
    @ManyToOne
    @MapsId("ntaskid")
    @JoinColumn(name = "ntaskid")
    private TaskEntity task;
 
    boolean signed;

}