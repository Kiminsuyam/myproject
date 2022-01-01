package myproject.configuration;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Slf4j
@Component
public class GlobalConfig {

	final Logger logger = LoggerFactory.getLogger(getClass());

/*	@Autowired
	private ApplicationContext context;
	@Autowired
	private ResourceLoader resourceLoader;
	*/

	private final ApplicationContext context;
	private final ResourceLoader resourceLoader;

	public GlobalConfig(ApplicationContext context,ResourceLoader resourceLoader) {
		this.context = context;
		this.resourceLoader = resourceLoader;
	}

	private String uploadFilePath;
	private String schedulerCronExample1;
	private String uploadResourcePath;

	private boolean local;
	private boolean dev;
	private boolean prod;


	@PostConstruct
	public void init() {
		logger.info("init");
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		//activeProfiles에 null,length가 없을 수도 있기 때문에 default값은 local로 지정.
		String activeProfile = "local";
		if (ObjectUtils.isNotEmpty(activeProfiles)) {
			//인덱스 0번만 가져와서 사용
			activeProfile = activeProfiles[0];
		}
		//프로퍼티 파일 경로 생성
		String resourcePath = String.format("classpath:globals/global-%s.properties", activeProfile);
		try {
			Resource resource = resourceLoader.getResource(resourcePath);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			//프로퍼티에 있는 파일 매핑
			this.uploadFilePath = properties.getProperty("uploadFile.path");
			this.schedulerCronExample1 = properties.getProperty("scheduler.cron.example1");
			this.uploadResourcePath = properties.getProperty("uploadFile.resourcePath");
			this.local = activeProfile.equals("local");
			this.dev = activeProfile.equals("dev");
			this.prod = activeProfile.equals("prod");
		} catch (Exception e) {
			logger.error("e", e);
		}
	}
	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public String getSchedulerCronExample1() {
		return schedulerCronExample1;
	}

	public String getUploadResourcePath() {
		return uploadResourcePath;
	}

	public boolean isLocal() {
		return local;
	}

	public boolean isDev() {
		return dev;
	}
	public boolean isProd() {
		return prod;
	}

}
