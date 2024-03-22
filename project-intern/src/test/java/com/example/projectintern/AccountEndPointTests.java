package com.example.projectintern;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AccountEndPointTests {
    private final String URL = "http://localhost:8080/applicationService/application.wsdl";

    @Autowired
    private WebTestClient webTestClient;


    /**
     * Check case username and password is valid
     * Create LongTND
     * Date 27/02
     */
    @Test
    public void whenEnterValidUsernameAndPassword_thenLoginSuccessfully() throws Exception{
        EntityExchangeResult<byte[]> responseResult =
        this.webTestClient.post()
                .uri(URL)
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.TEXT_XML)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult();
        byte[] responseBody = responseResult.getResponseBody();
        String responeseBodyString = new String(responseBody);
        assertTrue(responeseBodyString.length() > 0);
    }

    static String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.soap.springboot.vkakarla.com\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <int:loginRequest>\n" +
            "         <int:username>dinhlong1110</int:username>\n" +
            "         <int:password>123456</int:password>\n" +
            "      </int:loginRequest>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    /**
     * Check case username and password is invalid
     * Create LongTND
     * Date 27/02
     */
    @Test
    public void whenEnterInvalidUsernameAndPassword_thenLoginFailed() throws Exception{
        this.webTestClient.post()
                .uri(URL)
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.TEXT_XML)
                .bodyValue(request)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody().returnResult();
    }


//    @Test
//    void shouldReturn2When1Plus1() {
//        assertEquals(2, 1+1);
//    }

}
