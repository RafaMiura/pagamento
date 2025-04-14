package com.github.cidarosa.ms_pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cidarosa.ms_pagamento.dto.PagamentoDTO;
import com.github.cidarosa.ms_pagamento.tests.Factory;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PagamentoControllerIT {

    @Autowired
    private MockMvc mockMvc;
    //preparando os dados
    private Long existingId;
    private Long NonexistingId;
    private PagamentoDTO pagamentoDTO;
    //converter obj para JSON para enviar requisições
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach()
    void setUp() throws Exception {
        existingId = 1L;
        existingId = 50L;
        pagamentoDTO = Factory.createPagamentoDTO();
    }

    @Test
    public void getAllShouldReturnListAllPagamentos() throws Exception{

        //Testa a integração entre o controller e o service
        Object print;
        mockMvc.perform(MockMvcRequestBuilders.get("/pagamentos")
                .andDo(print))
                .andExpect()
        );
    }

}
