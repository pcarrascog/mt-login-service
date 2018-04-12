package cl.tsoftlatam.cors.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.custom-configuration.cors")
@Component
public class CorsProperties {

	private List<String> allowedDomains = new ArrayList<String>();
	private List<String> allowedMethods = new ArrayList<String>();

	public List<String> getAllowedDomains() {
		return allowedDomains;
	}

	public void setAllowedDomains(List<String> allowedDomains) {
		this.allowedDomains = allowedDomains;
	}

	public List<String> getAllowedMethods() {
		return allowedMethods;
	}

	public void setAllowedMethods(List<String> allowedMethods) {
		this.allowedMethods = allowedMethods;
	}

	@Override
	public String toString() {
		return "CorsProperties [allowedDomains=" + allowedDomains + ", allowedMethods=" + allowedMethods + "]";
	}
}