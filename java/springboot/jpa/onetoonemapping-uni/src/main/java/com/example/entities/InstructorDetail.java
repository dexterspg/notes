package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * InstructorDetail
 */
@Data
@NoArgsConstructor
@Entity
public class InstructorDetail {

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    @Id
    @GeneratedValue
    private int id;


    @Column
    private String youtubeChannel;

    @Column
    private String hobby;

    
}
