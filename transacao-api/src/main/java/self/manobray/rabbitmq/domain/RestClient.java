package self.manobray.rabbitmq.domain;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {

  private String server = "http://localhost:8084";
  private RestTemplate rest;
  private HttpHeaders headers;
  private HttpStatus status;

  public RestClient() {
    this.rest = new RestTemplate();
    this.headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
  }

  public String post(String uri, EmailInput json) {   
    HttpEntity<EmailInput> requestEntity = new HttpEntity<EmailInput>(json, headers);
    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, String.class);
    this.setStatus(responseEntity.getStatusCode());
    return responseEntity.getBody();
  }

    public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  } 
}