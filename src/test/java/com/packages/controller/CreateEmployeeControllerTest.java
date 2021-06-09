package com.packages.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.packages.controllers.EmployeeController;
import com.packages.models.Employee;
import com.packages.services.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class CreateEmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private EmployeeService service;
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	   protected <T> T mapFromJson(String json, Class<T> clazz)
	      throws JsonParseException, JsonMappingException, IOException {

	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.readValue(json, clazz);
	   }
	@Test
	public void createEmployee_whenPostMethod() throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Test");
		employee.setId(10);
		String inputInJson = this.mapToJson(employee);
		when(service.saveEmloyee(employee)).thenReturn(employee);
		RequestBuilder requestBuilder =  MockMvcRequestBuilders.post("/employee")
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
	       MockHttpServletResponse response = mvcResult.getResponse();
	       assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
}
