package natwest.test.transferservice.definition;

public class AccountErrorException extends Exception {

  private static final long serialVersionUID = 6094186316039874618L;
  private String description;

  public AccountErrorException(String description) {
  }

  public String toString() {
    return description;
  }
}
