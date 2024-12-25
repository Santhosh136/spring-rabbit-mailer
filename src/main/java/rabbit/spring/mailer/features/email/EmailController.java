package rabbit.spring.mailer.features.email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/email")
public class EmailController {
  private EmailProducer emailProducer;

  EmailController(EmailProducer emailProducer) {
    this.emailProducer = emailProducer;
  }
  
  @PostMapping("")
  public ResponseEntity<String> sendEmail() {
    String type = "test";
    this.emailProducer.sendEmail(type);
    return new ResponseEntity<>("Ok", HttpStatus.OK);  
  }
  

}
