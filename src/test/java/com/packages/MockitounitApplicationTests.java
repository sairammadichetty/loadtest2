package com.packages;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.packages.controllers.EmployeeController;

@SpringBootTest
class MockitounitApplicationTests {

	@Autowired
	private EmployeeController controller;
	
	
	@Test
	public void contextLoads()  throws Exception {
		assertThat(controller).isNotNull();
	}


}
