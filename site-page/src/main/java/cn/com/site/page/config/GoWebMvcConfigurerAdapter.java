/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年9月15日 下午1:39:08
 * GoWebMvcConfigurerAdapter.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * 
 */
package cn.com.site.page.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.com.site.os.EPlatform;
import cn.com.site.os.OSinfo;
import cn.com.site.page.util.PathUtil;


@Configuration
public class GoWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	
	private Logger log = LoggerFactory.getLogger(this.getClass()); 

	// 配置可访问的资源路径
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		String resourcePath = PathUtil.resourcePath();
		String imageParentPath = "file://" + resourcePath.replace("siteSearchEngine/", "") + "snapShotSite/";
		if (EPlatform.Linux == OSinfo.getOSname()) {
			imageParentPath = "file://" + resourcePath + "snapshot/snapShotSite/";
		}
		log.info("image path is {}", imageParentPath);
        //配置静态资源处理
        registry.addResourceHandler("/**")
                .addResourceLocations("resources/", "static/", "public/", 
                "META-INF/resources/")
                .addResourceLocations("classpath:resources/", "classpath:static/", 
                "classpath:public/", "classpath:META-INF/resources/")
                .addResourceLocations(imageParentPath);
    }
}

