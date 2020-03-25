package com.kzadha.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
public class UserTaskKey implements Serializable {
 
    @Column(name = "nuserid")
    private Long userId;
 
    @Column(name = "ntaskid")
    private Long taskId;

}