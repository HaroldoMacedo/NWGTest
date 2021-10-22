package natwest.test.transferservice.api;

public class TransferServiceAPIException extends Exception {

  private static final long serialVersionUID = 600143519151856433L;
  private String description;

  public TransferServiceAPIException(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
