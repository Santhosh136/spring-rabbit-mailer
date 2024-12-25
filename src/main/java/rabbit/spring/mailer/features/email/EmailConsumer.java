package rabbit.spring.mailer.features.email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

  @RabbitListener(queues = "q.email-delivery")
  public void processEmails(EmailMessage message) {
    System.out.println("Recieving messages.. " + message);
  }
  
}
