package rabbit.spring.mailer.features.email;

import java.util.HashMap;
import java.util.Map;

import org.simplejavamail.MailException;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Service;

record EmailType(String name, String subject, String templateFile) {}

@Service
public class EmailService {
  private Mailer mailer;
  private static Map<String, EmailType> emailTypes = new HashMap<>();

  static {
    emailTypes.put("test", new EmailType("test", "Test subject", "email-templates/test.html"));
  }

  EmailService() {
    this.mailer = MailerBuilder
      .withSMTPServerHost("localhost")
      .withSMTPServerPort(1025)
      .buildMailer();
  }

  private Email buildEmail(String toAddress, String fromAddress, String subject, String htmlContent) {
    Email email = EmailBuilder.startingBlank()
      .from(fromAddress)
      .to(toAddress)
      .withSubject(subject)
      .withHTMLText(htmlContent)
      .buildEmail();

    return email;
  }

  public void sendMail(String toAddress, String fromAddress, String type) {
    try {
      EmailType emailType = emailTypes.get(type);
      String subject = emailType.subject();
      String content = "<html><p> hello there! </p></html>";

      Email email = this.buildEmail(toAddress, fromAddress, subject, content);

      Boolean isValidEmail = this.mailer.validate(email);
      if (!isValidEmail) {
        System.out.println("Invaild email! skipping...");
        return;
      }

      this.mailer.sendMail(email);
      
    } catch (MailException e) {
      System.err.println(e);
    }
  }

}
