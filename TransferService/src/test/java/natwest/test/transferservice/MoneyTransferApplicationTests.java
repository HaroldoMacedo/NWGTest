package natwest.test.transferservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import natwest.test.transferservice.definition.Account;
import natwest.test.transferservice.entity.Balance;
import natwest.test.transferservice.entity.Transfer;

@SpringBootTest
class MoneyTransferApplicationTests {
  private static final String BASE_URL = "http://localhost:8080/current-account";
  private RestTemplate restTemplate = new RestTemplate();
  private HttpHeaders headersAll = new HttpHeaders();

  @Test
  void getBalanceOK() {
    String url = BASE_URL + "/12.34.56 123456";
    try {
      ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headersAll), Balance.class);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(0, response.getBody().getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(true);
    }
  }

  @Test
  void getBalanceInvalidAccountNumber() {
    String url = BASE_URL + "/12.34.56 1234567";
    try {
      ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headersAll), Balance.class);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(10, response.getBody().getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(true);
    }
  }

  @Test
  void transferMoneyOK() {
    String url = BASE_URL + "/12.34.56 123456/transfer";
    try {
      Transfer transfer = new Transfer();
      transfer.setDestinationAccountNumber(new Account("12.34.56 654321"));
      transfer.setDestinationFullName("John Dole");
      transfer.setTransferAmount(100.00);

      ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(transfer, headersAll), Balance.class);
      assertEquals(HttpStatus.CREATED, response.getStatusCode());
      assertEquals(0, response.getBody().getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(true);
    }
  }

  @Test
  void transferMoneyInvalidAccountNumber() {
    String url = BASE_URL + "/12.34.56 1234567/transfer";
    try {
      Transfer transfer = new Transfer();
      transfer.setDestinationAccountNumber(new Account("12.34.56 654321"));
      transfer.setDestinationFullName("John Dole");
      transfer.setTransferAmount(100.00);

      ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(transfer, headersAll), Balance.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(10, response.getBody().getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(true);
    }
  }
  
  @Test
  void transferMoneyNegativeValue() {
    String url = BASE_URL + "/12.34.56 123456/transfer";
    try {
      Transfer transfer = new Transfer();
      transfer.setDestinationAccountNumber(new Account("12.34.56 654321"));
      transfer.setDestinationFullName("John Dole");
      transfer.setTransferAmount(-100.00);

      ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(transfer, headersAll), Balance.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(1, response.getBody().getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(true);
    }
  }
  
  @Test
  void transferMoneyNoAccountDestination() {
    String url = BASE_URL + "/12.34.56 123456/transfer";
    try {
      Transfer transfer = new Transfer();
      transfer.setDestinationFullName("John Dole");
      transfer.setTransferAmount(100.00);

      ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(transfer, headersAll), Balance.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(1, response.getBody().getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(true);
    }
  }
  
  @Test
  void transferMoneyNoDestinationName() {
    String url = BASE_URL + "/12.34.56 123456/transfer";
    try {
      Transfer transfer = new Transfer();
      transfer.setDestinationAccountNumber(new Account("12.34.56 654321"));
      transfer.setTransferAmount(100.00);

      ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(transfer, headersAll), Balance.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(1, response.getBody().getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      assertFalse(true);
    }
  }
}
