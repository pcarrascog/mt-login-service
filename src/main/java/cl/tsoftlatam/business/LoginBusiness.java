package cl.tsoftlatam.business;

import org.springframework.stereotype.Component;

import cl.tsoftlatam.model.LoginDTO;

@Component
public class LoginBusiness {

	public boolean isLogin(LoginDTO log) {

		try {
			if (!"admin".equals(log.getUser()))
				return Boolean.FALSE;
			
			if ("admin".equals(log.getPass()))
				return Boolean.TRUE;
			
		} catch (Exception e) {
			return Boolean.FALSE;
		}
		return Boolean.FALSE;

	}
}
