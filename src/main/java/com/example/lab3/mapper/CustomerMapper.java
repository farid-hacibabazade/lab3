package com.example.lab3.mapper;

import com.example.lab3.dao.entity.CustomerEntity;
import com.example.lab3.model.dto.CustomerSaveDto;
import com.example.lab3.model.response.CustomerResponse;

public class CustomerMapper {

    public static CustomerEntity buildCustomerEntity(CustomerSaveDto dto){
        return CustomerEntity.builder()
                .pin(dto.getPin())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static CustomerResponse buildCustomerResponse(CustomerEntity customer){
        return CustomerResponse.builder()
                .pin(customer.getPin())
                .fullName(customer.getFullName())
                .phoneNumber(customer.getPhoneNumber())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }
}
