package com.mcb.assessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.assessment.model.auth.AuthToken;
import com.mcb.assessment.model.auth.LoginUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class StudentControllerAcceptanceTest {
	@Autowired
	MockMvc mockMvc;

	String baseUrl = "/api/v1/students";

	String accessToken;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void login () throws Exception {
		LoginUser loginUser = new LoginUser ("Harish", "password");

		String jsonRequestString = objectMapper.writeValueAsString (loginUser);
		MockHttpServletResponse response =
				mockMvc.perform (MockMvcRequestBuilders.post ("/api/v1/login").content (jsonRequestString).contentType (MediaType.APPLICATION_JSON)).andExpect (MockMvcResultMatchers.status ().isOk ()).andReturn ().getResponse ();
		AuthToken authToken = new ObjectMapper ().readValue (response.getContentAsString (), AuthToken.class);

		this.accessToken = "Bearer " + authToken.getToken ();
	}


	@Test
	void getStudentById () throws Exception {
		long id = 1L;
		mockMvc.perform (MockMvcRequestBuilders.get (baseUrl + "/" + id).header ("Authorization"
						, accessToken)).
				andExpect (MockMvcResultMatchers.status ().isOk ());
	}

	@Test
	void failgetStudentById () throws Exception {
		long id = 100L;
		mockMvc.perform (MockMvcRequestBuilders.get (baseUrl + "/" + id).header ("Authorization"
						, accessToken)).
				andExpect (MockMvcResultMatchers.status ().isNotFound ());
	}

	@Test
	void unauthorizedgetStudentById () throws Exception {
		long id = 100L;
		mockMvc.perform (MockMvcRequestBuilders.get (baseUrl + "/" + id)).
				andExpect (MockMvcResultMatchers.status ().is4xxClientError ());
	}

	@Test
	void getMarksByStudentId () throws Exception {
		long id = 1L;
		mockMvc.perform (MockMvcRequestBuilders.get (baseUrl + "/" + id + "/marks").header ("Authorization"
						, accessToken)).
				andExpect (MockMvcResultMatchers.status ().isOk ()).andDo (print ()).andReturn ();
	}

	@Test
	void GetMarksByStudentId () throws Exception {
		long id = 1L;
		mockMvc.perform (MockMvcRequestBuilders.get (baseUrl + "/" + id + "/marks").header ("Authorization"
				, accessToken)).andExpect (MockMvcResultMatchers.status ().isOk ()).andDo (print ()).andReturn ();
	}

	@Test
	void failGetMarksByStudentId () throws Exception {
		long id = 100L;
		mockMvc.perform (MockMvcRequestBuilders.get (baseUrl + "/" + id + "/marks").header ("Authorization"
				, accessToken)).andExpect (MockMvcResultMatchers.status ().isNotFound ()).andDo (print ()).andReturn ();
	}

	@Test
	void getListOfMarksByStudentId () throws Exception {
		long id = 1L;
		mockMvc.perform (MockMvcRequestBuilders.get (baseUrl + "/" + id + "/subjects/marks").header ("Authorization"
						, accessToken)).andExpect (MockMvcResultMatchers.status ().isOk ()).
				andExpect (MockMvcResultMatchers.jsonPath ("$.[1].mark").value ("10.0")
				).andDo (print ());

	}

	@Test
	void getStudentsByTeacherId () throws Exception {
		long id = 1L;
		mockMvc.perform (MockMvcRequestBuilders.get (baseUrl + "/teachers/" + id).header ("Authorization"
				, accessToken)).andExpect (MockMvcResultMatchers.status ().isOk ()).andDo (print ());


	}
}