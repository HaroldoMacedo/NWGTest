package natwest.test.transferservice.entity;

/**
 * Defines the Balance body in a REST request/respond.
 * 
 * @author Haroldo Macêdo
 *
 */
public class Balance extends FunctionalError {
  double balance;
  double available;
  double limit;

  public Balance() {
    limit = available = this.balance = 0;
  }

  public Balance(double balance) {
    limit = available = this.balance = balance;
  }

  public Balance(double balance, double limit) {
    this.balance = balance;
    this.limit = limit;
    this.available = limit + balance;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public double getAvailable() {
    return available;
  }

  public void setAvailable(double available) {
    this.available = available;
  }

  public double getLimit() {
    return limit;
  }

  public void setLimit(double limit) {
    this.limit = limit;
  }
}
