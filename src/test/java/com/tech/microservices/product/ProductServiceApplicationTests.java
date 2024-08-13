package com.tech.microservices.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer container = new MongoDBContainer("mongo:7.0.5");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		container.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
			{
			    "name": "Iphone 15",
			    "description": "Iphone is an Apple device",
			    "price": 1000
			}
			""";

		RestAssured.given()
			.contentType("application/json")
			.body(requestBody)
			.when()
			.post("/api/product/create")
			.then()
			.statusCode(201)
			.body("id", Matchers.notNullValue())
			.body("name", Matchers.equalTo("Iphone 15"))
			.body("description", Matchers.equalTo("Iphone is an Apple device"))
			.body("price", Matchers.equalTo(1000));

	}

}
