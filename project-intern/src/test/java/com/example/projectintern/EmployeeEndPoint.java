package com.example.projectintern;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class EmployeeEndPoint {

    private final String URL ="http://localhost:8080/applicationService/application.wsdl";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Check case authorization and authentication success
     * @throws Exception Created By LongTND
     * Date 28/02
     */
    @Test
    public void getAllEmployee_valid() throws Exception{
        this.webTestClient.post()
                .uri(URL)
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.TEXT_XML)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaW5obG9uZzExMTAiLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpZCI6MSwiZXhwIjoxNzA5NjQ4NjIxLCJpYXQiOjE3MDg1Njg2MjF9.1vO66mkOxYd5O9vmNXcZod3gHFCOx3j1DxqYVVH67rTFApqvmu9XC4dPbWRrGDIYcJiVuLrwGhXIvXU9DUFfhg")
                .bodyValue(requestGetList)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().returnResult();
    }

    static String requestGetList = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.soap.springboot.vkakarla.com\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <int:getAllEmployeeRequest>\n" +
            "         <int:username></int:username>\n" +
            "         <int:employeeName></int:employeeName>\n" +
            "         <int:employeePhoneNumber></int:employeePhoneNumber>\n" +
            "      </int:getAllEmployeeRequest>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    /**
     * Check case authorization and authentication failed
     * @throws Exception Created by LongTND
     * Date 28/02/2024
     */
    @Test
    public void getAllEmployee_invalid() throws Exception{
        this.webTestClient.post()
                .uri(URL)
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.TEXT_XML)
//                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaW5obG9uZzExMTAiLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpZCI6MSwiZXhwIjoxNzA5NjQ4NjIxLCJpYXQiOjE3MDg1Njg2MjF9.1vO66mkOxYd5O9vmNXcZod3gHFCOx3j1DxqYVVH67rTFApqvmu9XC4dPbWRrGDIYcJiVuLrwGhXIvXU9DUFfhg")
                .bodyValue(requestGetList)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody().returnResult();
    }

    /**
     *Check validate data input error
     * @throws Exception Create by LongTND
     * Date 28/02/2024
     */
    @Test
    public void updateEmployee_username_invalid() throws Exception{
        String requestUpdateEmployee = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.soap.springboot.vkakarla.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <int:updateEmployeeRequest>\n" +
                "         <int:employeeInfo>\n" +
                "            <int:id>1</int:id>\n" +
                "            <int:name>Tsai Văn</int:name>\n" +
                "            <int:phoneNumber>01283685099</int:phoneNumber>\n" +
                "            <int:flag>?</int:flag>\n" +
                "            <int:account>\n" +
                "               <int:id>2</int:id>\n" +
                "               <int:username>dinhlong1110</int:username>\n" +
                "               <int:password>$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m</int:password>\n" +
                "               <int:role>\n" +
                "                  <int:id>2</int:id>\n" +
                "                  <int:name>?</int:name>\n" +
                "                  <int:flag>?</int:flag>\n" +
                "               </int:role>\n" +
                "               <int:flag>?</int:flag>\n" +
                "            </int:account>\n" +
                "            <int:version>12</int:version>\n" +
                "         </int:employeeInfo>\n" +
                "         <int:confirmPassword>$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m</int:confirmPassword>\n" +
                "      </int:updateEmployeeRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        this.webTestClient.post()
                .uri(URL)
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.TEXT_XML)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaW5obG9uZzExMTAiLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpZCI6MSwiZXhwIjoxNzA5NjQ4NjIxLCJpYXQiOjE3MDg1Njg2MjF9.1vO66mkOxYd5O9vmNXcZod3gHFCOx3j1DxqYVVH67rTFApqvmu9XC4dPbWRrGDIYcJiVuLrwGhXIvXU9DUFfhg")
                .bodyValue(requestUpdateEmployee)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody().returnResult();
    }

    /**
     * Check validate password invalid
     * @throws Exception Created by LongTND
     * Date 28/02/2024
     */
    @Test
    public void updateEmployee_password_invalid() throws Exception {
        String requestUpdateEmployee = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.soap.springboot.vkakarla.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <int:updateEmployeeRequest>\n" +
                "         <int:employeeInfo>\n" +
                "            <int:id>1</int:id>\n" +
                "            <int:name>Tsai Văn</int:name>\n" +
                "            <int:phoneNumber>01283685099</int:phoneNumber>\n" +
                "            <int:flag>?</int:flag>\n" +
                "            <int:account>\n" +
                "               <int:id>2</int:id>\n" +
                "               <int:username>nhavan0305</int:username>\n" +
                "               <int:password></int:password>\n" +
                "               <int:role>\n" +
                "                  <int:id>2</int:id>\n" +
                "                  <int:name>?</int:name>\n" +
                "                  <int:flag>?</int:flag>\n" +
                "               </int:role>\n" +
                "               <int:flag>?</int:flag>\n" +
                "            </int:account>\n" +
                "            <int:version>12</int:version>\n" +
                "         </int:employeeInfo>\n" +
                "         <int:confirmPassword></int:confirmPassword>\n" +
                "      </int:updateEmployeeRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        this.webTestClient.post()
                .uri(URL)
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.TEXT_XML)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaW5obG9uZzExMTAiLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpZCI6MSwiZXhwIjoxNzA5NjQ4NjIxLCJpYXQiOjE3MDg1Njg2MjF9.1vO66mkOxYd5O9vmNXcZod3gHFCOx3j1DxqYVVH67rTFApqvmu9XC4dPbWRrGDIYcJiVuLrwGhXIvXU9DUFfhg")
                .bodyValue(requestUpdateEmployee)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody().returnResult();
    }

    /**
     * Check validate version update invalid
     * @throws Exception Created by LongTND
     * Date 28/02/2024
     */
    @Test
    public void updateEmployee_version_invalid() throws Exception {
        String requestUpdateEmployee = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.soap.springboot.vkakarla.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <int:updateEmployeeRequest>\n" +
                "         <int:employeeInfo>\n" +
                "            <int:id>1</int:id>\n" +
                "            <int:name>Tsai Văn</int:name>\n" +
                "            <int:phoneNumber>01283685099</int:phoneNumber>\n" +
                "            <int:flag>?</int:flag>\n" +
                "            <int:account>\n" +
                "               <int:id>2</int:id>\n" +
                "               <int:username>nhavan0305</int:username>\n" +
                "               <int:password>$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m</int:password>\n" +
                "               <int:role>\n" +
                "                  <int:id>2</int:id>\n" +
                "                  <int:name>?</int:name>\n" +
                "                  <int:flag>?</int:flag>\n" +
                "               </int:role>\n" +
                "               <int:flag>?</int:flag>\n" +
                "            </int:account>\n" +
                "            <int:version>11</int:version>\n" +
                "         </int:employeeInfo>\n" +
                "         <int:confirmPassword>$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m</int:confirmPassword>\n" +
                "      </int:updateEmployeeRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        this.webTestClient.post()
                .uri(URL)
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.TEXT_XML)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaW5obG9uZzExMTAiLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpZCI6MSwiZXhwIjoxNzA5NjQ4NjIxLCJpYXQiOjE3MDg1Njg2MjF9.1vO66mkOxYd5O9vmNXcZod3gHFCOx3j1DxqYVVH67rTFApqvmu9XC4dPbWRrGDIYcJiVuLrwGhXIvXU9DUFfhg")
                .bodyValue(requestUpdateEmployee)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody().returnResult();
    }

    /**
     * Update employee successful
     * @throws Exception Created by LongTND
     * Date 28/02/2024
     */
    @Test
    public void updateEmployee_successful() throws Exception {
        String requestUpdateEmployee = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.soap.springboot.vkakarla.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <int:updateEmployeeRequest>\n" +
                "         <int:employeeInfo>\n" +
                "            <int:id>1</int:id>\n" +
                "            <int:name>Tsai Nhã Văn</int:name>\n" +
                "            <int:phoneNumber>01283685099</int:phoneNumber>\n" +
                "            <int:flag>?</int:flag>\n" +
                "            <int:account>\n" +
                "               <int:id>2</int:id>\n" +
                "               <int:username>nhavan03052001</int:username>\n" +
                "               <int:password>$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m</int:password>\n" +
                "               <int:role>\n" +
                "                  <int:id>2</int:id>\n" +
                "                  <int:name>?</int:name>\n" +
                "                  <int:flag>?</int:flag>\n" +
                "               </int:role>\n" +
                "               <int:flag>?</int:flag>\n" +
                "            </int:account>\n" +
                "            <int:version>17</int:version>\n" +
                "         </int:employeeInfo>\n" +
                "         <int:confirmPassword>$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m</int:confirmPassword>\n" +
                "      </int:updateEmployeeRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        this.webTestClient.post()
                .uri(URL)
                .contentType(MediaType.TEXT_XML)
                .accept(MediaType.TEXT_XML)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaW5obG9uZzExMTAiLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpZCI6MSwiZXhwIjoxNzA5NjQ4NjIxLCJpYXQiOjE3MDg1Njg2MjF9.1vO66mkOxYd5O9vmNXcZod3gHFCOx3j1DxqYVVH67rTFApqvmu9XC4dPbWRrGDIYcJiVuLrwGhXIvXU9DUFfhg")
                .bodyValue(requestUpdateEmployee)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().returnResult();
    }
}
