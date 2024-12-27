package rabbit.spring.mailer.features.email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

  private EmailService emailService;

  EmailConsumer(EmailService emailService) {
    this.emailService = emailService;
  }

  @RabbitListener(queues = "q.email-delivery")
  public void processEmails(EmailMessage message) {
    this.emailService.sendMail(
      message.getToAddress(), 
      message.getFromAddress(), 
      message.getType());
  }
  
}
