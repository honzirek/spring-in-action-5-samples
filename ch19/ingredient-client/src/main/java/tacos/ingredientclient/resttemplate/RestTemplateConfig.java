package tacos.ingredientclient.resttemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

@Configuration
@Conditional(NotFeignAndNotWebClientCondition.class)
@Slf4j
public class RestTemplateConfig {

  static {
    HttpsURLConnection.setDefaultHostnameVerifier(
            new HostnameVerifier() {
              @Override
              public boolean verify(final String hostname, final SSLSession session) {
                System.out.println("verifying: " + hostname);
                return true;
              }
            }
    );
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
  
  @Bean
  public CommandLineRunner startup() {
    return args -> {
      log.info("**************************************");
      log.info("    Configuring with RestTemplate");
      log.info("**************************************");
    };
  }
  
}
