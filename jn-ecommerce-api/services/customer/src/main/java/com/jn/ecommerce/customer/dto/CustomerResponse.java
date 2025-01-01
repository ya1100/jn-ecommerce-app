package com.jn.ecommerce.customer.dto;

import java.util.List;

public record CustomerResponse(
        List<CustomerDTO> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean lastPage,
        boolean firstPage
) {
}
