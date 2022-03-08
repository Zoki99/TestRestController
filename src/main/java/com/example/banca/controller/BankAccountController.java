package com.example.banca.controller;
import com.example.banca.model.ClientModel.ClientBalance;
import com.example.banca.model.TransactionsModel.TransactionsRoot;
import com.example.banca.model.TransactionsModel.TranscationsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.ArrayList;


@RestController
public class BankAccountController {
    Logger logger = LoggerFactory.getLogger(BankAccountController.class);
    private final String URL = "https://sandbox.platfr.io";

    @GetMapping("/getBalance/{accountId}")
    public BigDecimal getBalance(HttpEntity<String> httpEntity, @PathVariable long accountId) {
        // request url
        String url = URL+"/api/gbs/banking/v4.0/accounts/"+accountId+"/balance";
        BigDecimal balance = null;

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(httpEntity.getHeaders());

        // make an HTTP GET request with headers
        ResponseEntity<ClientBalance> response = restTemplate.exchange(url, HttpMethod.GET, request, ClientBalance.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            balance = response.getBody().getPayload().getBalance();
            logger.info(response.getStatusCode() + " Request Successful.");
            logger.info(response.getBody().getPayload().toString());
        } else {
            logger.error("Request Failed");
            logger.error(response.getStatusCode().toString());
        }
        return balance;
    }



    @GetMapping("/getTransactions/{accountId}/{from}/{to}")
    public ArrayList<TranscationsList> getTransactions(HttpEntity<String> httpEntity, @PathVariable long accountId, @PathVariable String from, @PathVariable String to) {
        // request url
        String url = URL+"/api/gbs/banking/v4.0/accounts/"+accountId+"/transactions?fromAccountingDate="+from+"&toAccountingDate="+to+"/balance";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(httpEntity.getHeaders());

        // make an HTTP GET request with headers
        ResponseEntity<TransactionsRoot> response = restTemplate.exchange(url, HttpMethod.GET, request, TransactionsRoot.class);
        ArrayList<TranscationsList> list = new ArrayList<TranscationsList>();

        // check response
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            list = response.getBody().getPayload().getList();
            // transactions = response.getBody().getPayload().getList();
            logger.info(response.getStatusCode() + " Request Successful.");
            logger.info(list.toString());
        } else {
            logger.error("Request Failed");
            logger.error(response.getStatusCode().toString());
        }
        return list;
    }

    @RequestMapping(value = "/money-transfers/{accountId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void moneyTransfer(HttpEntity<String> httpEntity, @PathVariable long accountId) {
        RestTemplate restTemplate = new RestTemplate();
        String url  = URL+"/api/gbs/banking/v4.0/accounts/"+accountId+"/payments/money-transfers";
        HttpEntity<String> entity = new HttpEntity<String>(httpEntity.getBody(), httpEntity.getHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        logger.info(response.toString());
    }



    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException exception) {
        logger.error("Request Failed");
        logger.error(ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage()).toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }




}
