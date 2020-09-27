package br.com.apirest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import br.com.apirest.controller.CustomerController;
import br.com.apirest.model.CustomerModel;
import br.com.apirest.repository.CustomerRepository;
import br.com.apirest.repository.PurchaseRepository;
import br.com.apirest.repository.TransferRepository;
import br.com.apirest.service.CustomerService;
import br.com.apirest.service.PurchaseService;
import br.com.apirest.service.ReportService;
import br.com.apirest.service.TransferService;
import io.restassured.http.ContentType;

@ActiveProfiles("test")
@WebMvcTest
public class CustomerControllerTests {

	@Autowired
	private CustomerController customerController;
	
	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private PurchaseService purchaseService;
	
	@MockBean
	private PurchaseRepository purchaseRepository;
	
	@MockBean
	private TransferService transferService;	
	
	@MockBean
	private TransferRepository transferRepository;		
	
	@MockBean
	private ReportService reportService;	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.customerController);
	}
	
	@Test
	public void testRetrieveSuccess_WhenFindCustomer() {

		when(this.customerRepository.findById(1L))
		.thenReturn(Optional.of(new CustomerModel(1L, "Customer Name ", "123456789")));
		
		when(this.customerService.getById(1L))
			.thenReturn(new CustomerModel(1L, "Customer Name ", "123456789"));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/customers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testRetrieveNotFound() {
		
		when(this.customerService.getById(5L))
			.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/customers/{id}", 5L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
}