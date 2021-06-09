package com.packages.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
public class UpdateEmployeeControllerTest {
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
	public void updateEmploye_whenPutEmployee() throws Exception {

		Employee employee = new Employee();
		employee.setFirstName("Test");
		employee.setId(10);
		String inputInJson = this.mapToJson(employee);
		when(service.get(10)).thenReturn(employee);
		when(service.update(employee)).thenReturn(employee);
				
		RequestBuilder requestBuilder =  MockMvcRequestBuilders.put("/employee/10")
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
	       MockHttpServletResponse response = mvcResult.getResponse();
	       assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	@Test
	public void should_throw_exception_when_employee_doesnt_exist() throws Exception {

		Employee employee = new Employee();
		employee.setFirstName("Test");
		employee.setId(10);
		String inputInJson = this.mapToJson(employee);
		when(service.update(employee)).thenReturn(employee);
		RequestBuilder requestBuilder =  MockMvcRequestBuilders.put("/employee/10")
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
	       MockHttpServletResponse response = mvcResult.getResponse();
	       assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}

	

}
