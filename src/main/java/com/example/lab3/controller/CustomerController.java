package com.example.lab3.controller;

import com.example.lab3.model.criteria.CustomerCriteria;
import com.example.lab3.model.criteria.PageCriteria;
import com.example.lab3.model.dto.CustomerSaveDto;
import com.example.lab3.model.response.CustomerResponse;
import com.example.lab3.model.response.PageableResponse;
import com.example.lab3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveCustomer(@RequestBody CustomerSaveDto request){
        customerService.saveCustomer(request);
    }

    @GetMapping
    public PageableResponse<CustomerResponse> getCustomers(PageCriteria pageCriteria,
                                                           CustomerCriteria customerCriteria){
        return customerService.getCustomers(pageCriteria, customerCriteria);
    }
}
