package natwest.test.transferservice.entity;

/**
 * Defines a structured error code / error description body to return in a
 * response.
 * 
 * These errors describe functional errors only.
 * 
 * @author Haroldo Macêdo
 *
 */
public class FunctionalError {
  int errorCode = 0;
  String errorDescription = "";

  public void setError(int errorCode, String errorDescription) {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  @Override
  public String toString() {
    return errorCode + " - " + errorDescription;
  }
}
