package natwest.test.transferservice.entity;

import natwest.test.transferservice.definition.Account;

/**
 * Defines the Transfer body in a REST request.
 * 
 * @author Haroldo Macêdo
 *
 */
public class Transfer {
  Account destinationAccountNumber;
  String destinationFullName;
  Double transferAmount;
  String description;

  public Account getDestinationAccountNumber() {
    return destinationAccountNumber;
  }

  public void setDestinationAccountNumber(Account destinationAccountNumber) {
    this.destinationAccountNumber = destinationAccountNumber;
  }

  public String getDestinationFullName() {
    return destinationFullName;
  }

  public void setDestinationFullName(String destinationFullName) {
    this.destinationFullName = destinationFullName;
  }

  public Double getTransferAmount() {
    return transferAmount;
  }

  public void setTransferAmount(Double transferAmount) {
    this.transferAmount = transferAmount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
