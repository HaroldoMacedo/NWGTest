package natwest.test.transferservice.api;

import natwest.test.transferservice.definition.Account;
import natwest.test.transferservice.entity.Balance;
import natwest.test.transferservice.entity.Transfer;

/**
 * This Class mocks the implementation of the service.
 * 
 * If a real service were to be implemented, this class would contain the
 * business implementation.
 * 
 * @author Haroldo Macêdo
 *
 */
public class TransferServiceAPIimpl implements TransferServiceAPI {

  @Override
  public Balance getBalance(Account account) {
    return new Balance(100, 200);
  }

  @Override
  public Balance transferMoney(Account account, Transfer transfer) throws TransferServiceAPIException {

    if (transfer.getDestinationAccountNumber() == null)
      throw new TransferServiceAPIException("Unknown destination account number");
    if (transfer.getTransferAmount() <= 0)
      throw new TransferServiceAPIException("Invalid transfer amount. Amount = " + transfer.getTransferAmount());
    if (transfer.getDestinationFullName() == null || transfer.getDestinationFullName().isBlank())
      throw new TransferServiceAPIException("Destination full name must be provided");

    return new Balance(100, 200);
  }
}
