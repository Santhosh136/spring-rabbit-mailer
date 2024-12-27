package rabbit.spring.mailer.common.health;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HealthCheckController {
  @GetMapping("/health")
  public ResponseEntity<String> getMethodName(@RequestParam String param) {
      return new ResponseEntity<>("Ok", HttpStatus.OK);
  }
  
}