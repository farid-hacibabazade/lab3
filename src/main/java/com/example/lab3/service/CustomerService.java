package com.example.lab3.service;

import com.example.lab3.dao.repository.CustomerRepository;
import com.example.lab3.mapper.CustomerMapper;
import com.example.lab3.mapper.PageableMapper;
import com.example.lab3.model.criteria.CustomerCriteria;
import com.example.lab3.model.criteria.PageCriteria;
import com.example.lab3.model.dto.CustomerSaveDto;
import com.example.lab3.model.response.CustomerResponse;
import com.example.lab3.model.response.PageableResponse;
import com.example.lab3.service.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.example.lab3.mapper.PageableMapper.PAGEABLE_MAPPER;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void saveCustomer(CustomerSaveDto request) {
        var customer = CustomerMapper.buildCustomerEntity(request);
        customerRepository.save(customer);
    }

    public PageableResponse<CustomerResponse> getCustomers(PageCriteria pageCriteria,
                                                           CustomerCriteria customerCriteria) {
        var pageRequest = PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount());
        var specification = new CustomerSpecification(customerCriteria);
        var customerPage = customerRepository.findAll(specification, pageRequest);
        return PAGEABLE_MAPPER.mapPageToPageableResponse(customerPage, CustomerMapper::buildCustomerResponse);
    }
}
