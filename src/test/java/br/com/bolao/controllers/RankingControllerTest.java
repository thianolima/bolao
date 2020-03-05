package br.com.bolao.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bolao.forms.RankingForm;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RankingControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
		
	@Before
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
    }    			
	
	@Test 
    @WithMockUser(roles= {"ADMIN"})
    public void deveProcessarJogo1() throws Exception {		
		RankingForm form = new RankingForm();
		form.setBolao(1);
		form.setJogo(1);
		
		String json = new ObjectMapper().writeValueAsString(form);
		
		mockMvc.perform(put("/rankings")
			.content(json)
			.contentType(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk())
			.andExpect(jsonPath("$.content[*]").isNotEmpty());
			//.andExpect(jsonPath("$.pontos", is(10)));
    }
}
