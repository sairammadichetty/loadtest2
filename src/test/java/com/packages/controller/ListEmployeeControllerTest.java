package com.packages.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class ListEmployeeControllerTest {

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
	public void listAllEmployees_whenGetMethod() throws Exception {

		List<Employee> employees = new ArrayList();
		employees.add(new Employee());

		when(service.listAll()).thenReturn(employees);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String expectedJson = this.mapToJson(employees);
        String outputInJson = mvcResult.getResponse().getContentAsString();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		 assertEquals(expectedJson, outputInJson);
	}

}
