package com.mackie;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.mackie.dao.UserDAO;
import com.mackie.domain.UserDO;

@SpringBootApplication
@Controller
public class App {

	@Autowired
	private Environment env;

	@Value("${username}")
	private String username;

	@Autowired
	private UserDAO userDAO;

	private static Logger log = LogManager.getLogger(App.class);

	@RequestMapping("/aaa")
	String home(HttpServletRequest request) {
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + ": " + request.getHeader(header));
		}
		log.info(env.getProperty("username"));
		log.info(username);
		// log.error("test", new Exception());
		// try {
		// throw new Exception("exception test");
		// }catch (Exception e) {
		// log.error("my Exception", e);
		// }
		return "nihao";
	}

	@RequestMapping("/test1")
	public String test() {
		return "test";
	}

	@RequestMapping("/user")
	@ResponseBody
	public UserDO user() {
		UserDO user = userDAO.queryUserOne(3);
		System.out.println(JSONObject.toJSON(user));
		return user;
	}

	@Bean
	@ConditionalOnClass({ JSON.class })
	public HttpMessageConverter<?> fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		return fastConverter;
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/res/");
		resolver.setSuffix(".html");
		return resolver;
	}
	


	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}
