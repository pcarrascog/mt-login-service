package cl.tsoftlatam.controllers;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cl.tsoftlatam.business.LoginBusiness;
import cl.tsoftlatam.model.LoginDTO;

@Configuration
@EnableAutoConfiguration
//@EnableEurekaClient
@RestController
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Boolean isLogin(@RequestParam(value = "dataFront", required = false) String dataFront) throws IOException {
		
		Gson gson = new Gson();
		
		LoginDTO param = gson.fromJson(dataFront, LoginDTO.class);
		
		System.out.println(param.toString());
		
		LoginBusiness loginBusiness = new LoginBusiness();
		
		return loginBusiness.isLogin(param);
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Boolean getLoginMock() throws IOException {
		
		return Boolean.TRUE;
	}
}
