package com.example.lab3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveDto {
    private String pin;
    private String fullName;
    private String phoneNumber;
}
