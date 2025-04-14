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


    @Test
    public void getAllShouldReturnListPagamentoDTO(){

        var result = service.getAll();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalPagamentos, result.size());
        Assertions.assertEquals(Double.valueOf(35.55), result.get(0).getValor().doubleValue());
        Assertions.assertEquals("Amadeus Mozart", result.get(0).getNome());
        Assertions.assertEquals("Chiquinha Gonzaga", result.get(1).getNome());
        Assertions.assertNull(result.get(5).getNome());
        // ou Assertions.assertEquals(null, result.get(5).getNome());

    }
}
