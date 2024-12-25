package rabbit.spring.mailer.features.email;

public class EmailMessage {
  
  private String toAddress;
  private String fromAddress;
  private String type;

  public EmailMessage(String toAddress, String fromAddress, String type) {
    this.fromAddress = fromAddress;
    this.toAddress = toAddress;
    this.type = type;
  }

  @Override
  public String toString() {
    return "EmailMessage [toAddress=" + toAddress + ", fromAddress=" + fromAddress + ", type=" + type + "]";
  }

  public String getToAddress() {
    return toAddress;
  }

  public void setToAddress(String toAddress) {
    this.toAddress = toAddress;
  }

  public String getFromAddress() {
    return fromAddress;
  }

  public void setFromAddress(String fromAddress) {
    this.fromAddress = fromAddress;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  

}
