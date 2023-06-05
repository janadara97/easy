package com.easy.easyproduct;

import com.easy.easyproduct.model.dto.ProductRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class EasyProductApplicationTests {

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	MockMvc mockMvc;

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.url",mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		String productRequest = objectMapper.writeValueAsString(getProductRequest());
		mockMvc.perform(MockMvcRequestBuilders.post("api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequest));

	}

	public ProductRequestDto getProductRequest(){
		return ProductRequestDto.builder().name("product 1").description("Product description 1").price(BigDecimal.valueOf(100.89)).build();
	}

}
