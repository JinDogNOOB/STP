package com.example.demo;

import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;





@SpringBootApplication
@MapperScan(value = {"com.example.demo.board.mapper"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	
	
	/*
	 * SqlSessionFactory configuration
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}
	
	
	
	/*
	@Configuration
	public class WebConfig extends WebMvcConfigurerAdapter{
	
	 //* 로그인 인증 인터셉터 설정
	 
		@Autowired
		AuthenticationInterceptor authenticationInterceptor;
	
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			
			registry.addInterceptor(authenticationInterceptor);
			registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
		
		}
	}*/
	
	
}
