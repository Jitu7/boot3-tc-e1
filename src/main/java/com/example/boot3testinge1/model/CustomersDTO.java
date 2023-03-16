package com.example.boot3testinge1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class CustomersDTO {
    private List<CustomerDTO> data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty("isFirst")
    private boolean isFirst;
    @JsonProperty("isLast")
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public CustomersDTO(Page<CustomerDTO> customerPage) {
        this.setData(customerPage.getContent());
        this.setTotalElements(customerPage.getTotalElements());
        this.setTotalPages(customerPage.getTotalPages());
        this.setCurrentPage(customerPage.getNumber() + 1);
        this.setFirst(customerPage.isFirst());
        this.setLast(customerPage.isLast());
        this.setHasNext(customerPage.hasNext());
        this.setHasPrevious(customerPage.hasPrevious());
    }

}
