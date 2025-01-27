package com.example.lab3.mapper;


import com.example.lab3.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import java.util.function.Function;

public enum PageableMapper {
    PAGEABLE_MAPPER;

    public <T, E> PageableResponse<T> mapPageToPageableResponse(Page<E> pageRequest, Function<E, T> mapper){
       return PageableResponse.<T>builder()
                .content(pageRequest.getContent().stream().map(mapper).toList())
                .totalElements(pageRequest.getTotalElements())
                .lastPageNumber(pageRequest.getTotalPages())
                .hasNextPage(pageRequest.hasNext())
                .build();
    }
}
