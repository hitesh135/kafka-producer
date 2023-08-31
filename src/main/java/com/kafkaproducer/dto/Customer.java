package com.kafkaproducer.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = -4755211684842868386L;

    private Long id;
    private String name;
    private String email;
    private String contactNo;
}
