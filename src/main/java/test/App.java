package test;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
//@Import(WebConfig.class)
public class App {
	
	@Autowired
	private Environment env;
	
	@Value("${username}")
	private String username;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger log = LogManager.getLogger(App.class);
	
	@RequestMapping("/")
	String home(HttpServletRequest request) {
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + ": "+ request.getHeader(header));
		}
		log.info(env.getProperty("username"));
		log.info(username);
		log.error("test", new Exception());
//		try {
//			throw new Exception("exception test");
//		}catch (Exception e) {
//			log.error("my Exception", e);
//		}
		return "test";
	}
	
	@RequestMapping("/user")
	public U
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}
