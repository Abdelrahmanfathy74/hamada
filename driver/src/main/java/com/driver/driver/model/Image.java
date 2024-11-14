package com.driver.driver.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}