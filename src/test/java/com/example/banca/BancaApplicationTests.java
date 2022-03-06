package com.example.banca;

import com.example.banca.controller.BankAccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
class BancaApplicationTests {
	private long accountId;
	private String from = "2020-03-01";
	private String to = "2020-04-01";

	String body = "{\n" +
			"  \"creditor\": {\n" +
			"    \"name\": \"John Doe\",\n" +
			"    \"account\": {\n" +
			"      \"accountCode\": \"IT23A0336844430152923804660\",\n" +
			"      \"bicCode\": \"SELBIT2BXXX\"\n" +
			"    },\n" +
			"    \"address\": {\n" +
			"      \"address\": null,\n" +
			"      \"city\": null,\n" +
			"      \"countryCode\": null\n" +
			"    }\n" +
			"  },\n" +
			"  \"executionDate\": \"2019-04-01\",\n" +
			"  \"uri\": \"REMITTANCE_INFORMATION\",\n" +
			"  \"description\": \"Payment invoice 75/2017\",\n" +
			"  \"amount\": 800,\n" +
			"  \"currency\": \"EUR\",\n" +
			"  \"isUrgent\": false,\n" +
			"  \"isInstant\": false,\n" +
			"  \"feeType\": \"SHA\",\n" +
			"  \"feeAccountId\": \"45685475\",\n" +
			"  \"taxRelief\": {\n" +
			"    \"taxReliefId\": \"L449\",\n" +
			"    \"isCondoUpgrade\": false,\n" +
			"    \"creditorFiscalCode\": \"56258745832\",\n" +
			"    \"beneficiaryType\": \"NATURAL_PERSON\",\n" +
			"    \"naturalPersonBeneficiary\": {\n" +
			"      \"fiscalCode1\": \"MRLFNC81L04A859L\",\n" +
			"      \"fiscalCode2\": null,\n" +
			"      \"fiscalCode3\": null,\n" +
			"      \"fiscalCode4\": null,\n" +
			"      \"fiscalCode5\": null\n" +
			"    },\n" +
			"    \"legalPersonBeneficiary\": {\n" +
			"      \"fiscalCode\": null,\n" +
			"      \"legalRepresentativeFiscalCode\": null\n" +
			"    }\n" +
			"  }\n" +
			"}";
	@Autowired
	private BankAccountController controller;



	@Test
	void getBalanceTest(){
		assertThat(controller.getBalance(accountId)).isNotNull();
		assertThat(controller.getBalance(accountId).toString()).isEqualTo("8.40");

	}

	@Test
	void getTransactionsTest() {
		assertThat(controller.getTransactions(accountId, from, to)).isNotNull();
	}


	@Test
	void moneyTransfer() {
		assertThat(controller.getTransactions(accountId, from, to)).isNotNull();

	}



	private MockMvc mvc;

	@Test
	public void createEmployeeAPI() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
				.post("/money-transfers/"+accountId)
				.content(body)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

//	public static String asJsonString(final Object obj) {
//		try {
//			return new ObjectMapper().writeValueAsString(obj);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

	}
