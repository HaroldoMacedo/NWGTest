package natwest.test.transferservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import natwest.test.transferservice.api.TransferServiceAPI;
import natwest.test.transferservice.definition.Account;
import natwest.test.transferservice.definition.AccountErrorException;
import natwest.test.transferservice.entity.Balance;
import natwest.test.transferservice.entity.Transfer;

/**
 * Controller in a MVC pattern.
 * 
 * The methods here are the HTTP requests entry points.
 * 
 * @author Haroldo Macêdo
 *
 */
@RestController
public class MoneyTransferController {

  private TransferServiceAPI transferService;

  @Autowired
  public MoneyTransferController(TransferServiceAPI transferService) {
    this.transferService = transferService;
  }

  /**
   * GET Balance
   * 
   * @return Balance
   */
  @GetMapping(path = "/current-account/{accountNumber}", produces = "application/json")
  public ResponseEntity<Balance> getBalance(@PathVariable String accountNumber) {
    try {
      Balance balance = transferService.getBalance(new Account(accountNumber));
      return ResponseEntity.ok().body(balance);

    } catch (AccountErrorException e) {
      Balance balance = new Balance();
      balance.setError(10, "Invalid account number");
      return ResponseEntity.ok().body(balance);

    } catch (Exception e) {
      Balance balance = new Balance();
      balance.setError(1, e.getMessage());
      return ResponseEntity.ok().body(balance);
    }
  }

  /**
   * POST Transfer
   * 
   * @return Balance
   */
  @PostMapping(path = "/current-account/{accountNumber}/transfer", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Balance> transferMoney(@PathVariable String accountNumber, @RequestBody Transfer transfer) {
    try {
      Balance balance = transferService.transferMoney(new Account(accountNumber), transfer);
      return ResponseEntity.created(null).body(balance);

    } catch (AccountErrorException e) {
      Balance balance = new Balance();
      balance.setError(10, "Invalid account number");
      return ResponseEntity.ok().body(balance);

    } catch (Exception e) {
      Balance balance = new Balance();
      balance.setError(1, e.getMessage());
      return ResponseEntity.ok().body(balance);
    }
  }
}
