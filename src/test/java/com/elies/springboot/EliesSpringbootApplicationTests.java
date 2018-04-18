package com.elies.springboot;

import com.alibaba.fastjson.JSONObject;
import com.elies.springboot.domain.User;
import com.elies.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EliesSpringbootApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void insertUser() throws Exception {
		for(int i=0; i<50; i++){
			User user = new User();
			user.setLoginName(String.format("elies_w%s", i));
			user.setLoginPassword("1");
			user.setUserName(String.format("爱丽丝%s", i));
			user.setCreateTime(System.currentTimeMillis());
			user.setUpdateTime(System.currentTimeMillis());
			user.setUserAccount(String.format("account%s", i));
			user.setUserAddress("贵州省道真仡佬族苗族自治县");
			user.setUserAge(18);
			user.setUserSex(1);
			String requestJson = JSONObject.toJSONString(user);
			mockMvc.perform(post("/user/insertUser")
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestJson))
					.andDo(print())
					.andExpect(status().isOk())
					.andReturn();
		}
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");
		System.out.println("....");

	}

	@Test
	public void deleteUser() throws Exception{
		mockMvc.perform(post("/user/deleteUser")
		.param("userId", "100"))
				.andExpect(status().isOk());
		System.out.println("....");
	}

}
