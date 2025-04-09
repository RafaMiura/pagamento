package com.github.cidarosa.ms_pagamento.service;


import com.github.cidarosa.ms_pagamento.repository.PagamentoRepository;
import com.github.cidarosa.ms_pagamento.service.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class PagamentoServiceIT {

    @Autowired
    private PagamentoService service;

    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonexistingId;
    private Long countTotalPagamentos;

    @BeforeEach
    void setup() throws Exception {

        existingId = 1L;
        nonexistingId = 100L;
        countTotalPagamentos = 6L;
    }

    @Test
    public void deletePagamentoShouldDeleteResourceWhenIdExists(){

        service.deletePagamento(existingId);
        Assertions.assertEquals(countTotalPagamentos - 1, repository.count());
    }

    @Test
    public void deletePagamentoShouldDeleteResourceWhenIdDoesNotExists(){

        Assertions.assertThrows(ResourceNotFoundException.class,
                ()-> {
            service.deletePagamento(nonexistingId);
                }
                );

    }

}
