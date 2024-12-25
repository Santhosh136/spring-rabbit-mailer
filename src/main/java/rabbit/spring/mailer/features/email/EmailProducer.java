package rabbit.spring.mailer.features.email;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {
  private RabbitTemplate rabbitTemplate;

  EmailProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendEmail(String type) {
    EmailMessage message = new EmailMessage("san@gmail.com", "support@gmail.com", type);
    this.rabbitTemplate.convertAndSend("q.email-delivery", message);
  }
  
}
