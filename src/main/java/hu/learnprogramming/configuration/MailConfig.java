package hu.learnprogramming.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
/*play.mailer {
  host = "smtp.mailtrap.io"
  port = 2525
  ssl = no
  tls = yes
  user = "7e5174450322a1"
  password = "54562bcde25ffc"
}*/
	
	@Value("${mail.smtp.host}")
	private String host;
	@Value("${mail.smtp.port}")
	private Integer port;
	@Value("${mail.smtp.user}")
	private String user;
	@Value("${mail.smtp.password}")
	private String password;
	
	@Bean
	public JavaMailSender mailSender() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(user);
		mailSender.setPassword(password);
		
		return mailSender;
	}
}
