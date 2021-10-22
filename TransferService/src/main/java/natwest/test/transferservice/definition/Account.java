package natwest.test.transferservice.definition;

/**
 * Defines an UK account number.
 *  
 * @author Haroldo Macêdo
 *
 */
public class Account {
  String sortCode = "";
  String accountNumber = "";

  public Account() {
  }

  public Account(String sortCodeAccountNumber) throws AccountErrorException {
    if (sortCodeAccountNumber.length() != 15)
      throw new AccountErrorException("Invalid current account number");
    sortCode = sortCodeAccountNumber.substring(0, 8);
    accountNumber = sortCodeAccountNumber.substring(9, 15);
  }

  public String getSortCode() {
    return sortCode;
  }

  public void setSortCode(String sortCode) {
    this.sortCode = sortCode;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  @Override
  public String toString() {
    return sortCode + " " + accountNumber;
  }
}
