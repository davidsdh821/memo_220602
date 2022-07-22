package com.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.common.FileManagerService;

//이미지 주소 매핑
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	//웹의 이미지주소와 실제 파일 경로를 매핑해주는 설정(실제 파일의 경로를 웹주소로 변환시키는 과정이라 생각하면 편하다)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.
		addResourceHandler("/images/**") //이미지의 웹 주소 http://localhost/images/abc_213415151513/sun.png
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); //실제 파일이 있는 곳이다
		//FILE_UPLOAD_PATH은 file매니저에 있다
		
	}
	
	
	
}
