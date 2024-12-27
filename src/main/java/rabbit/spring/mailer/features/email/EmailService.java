package rabbit.spring.mailer.features.email;

import java.util.HashMap;
import java.util.Map;

import org.simplejavamail.MailException;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

record EmailType(String name, String subject, String templateFile) {}

@Service
public class EmailService {
  private Mailer mailer;
  private SpringTemplateEngine templateEngine;
  private static Map<String, EmailType> emailTypes = new HashMap<>();

  static {
    emailTypes.put("test", new EmailType(
      "test", 
      "Test subject", 
      "email-templates/test.html"));
  }

  EmailService(SpringTemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
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

  private String buildHtmlContent(String templateFile, String toAddress) {

    String fullName = toAddress.replaceAll("((@.*)|[^a-zA-Z])+", " ").trim();

    Context context = new Context();
    context.setVariable("fullName", fullName);
    String content = this.templateEngine.process(templateFile, context);
    return content;
  }

  public void sendMail(String toAddress, String fromAddress, String type) {
    try {
      EmailType emailType = emailTypes.get(type);
      String subject = emailType.subject();
      String templateFilePath = emailType.templateFile();

      String htmlContent = this.buildHtmlContent(templateFilePath, toAddress);

      Email email = this.buildEmail(toAddress, fromAddress, subject, htmlContent);

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
