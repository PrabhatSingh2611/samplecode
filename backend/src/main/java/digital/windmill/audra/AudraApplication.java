package digital.windmill.audra;

import com.azure.spring.cloud.autoconfigure.context.AzureTokenCredentialAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {AzureTokenCredentialAutoConfiguration.class})
public class AudraApplication {

    public static void main(String[] args) {
        SpringApplication.run(AudraApplication.class, args);
    }

}
