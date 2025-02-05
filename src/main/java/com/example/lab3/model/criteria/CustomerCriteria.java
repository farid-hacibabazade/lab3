package com.example.lab3.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCriteria {
    private String pin;
    private String fullName;
    private String phoneNumber;
}
