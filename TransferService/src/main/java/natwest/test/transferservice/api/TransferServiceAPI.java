package natwest.test.transferservice.api;

import natwest.test.transferservice.definition.Account;
import natwest.test.transferservice.entity.Balance;
import natwest.test.transferservice.entity.Transfer;

public interface TransferServiceAPI {

  public Balance getBalance(Account account);

  public Balance transferMoney(Account account, Transfer transfer) throws TransferServiceAPIException;

}
