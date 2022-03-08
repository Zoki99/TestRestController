package com.example.banca;

import com.example.banca.controller.BankAccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
class BancaApplicationTests {
	private long accountId = 14537780;
	private String from = "2020-03-01";
	private String to = "2020-04-01";

	@Autowired
	private BankAccountController controller;



	@Test
	void getBalanceTest(){
		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth-Schema", "S2S");
		headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		assertThat(controller.getBalance(request, accountId)).isNotNull();
		assertThat(controller.getBalance(request, accountId).toString()).isEqualTo("8.37");

	}



}
