package org.egov.tradelicence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.ToString;

@Configuration
@Getter
@ToString
public class PropertiesManager {

	@Value("${kafka.config.bootstrap_server_config}")
	private String kafkaServerConfig;

	@Value("${auto.offset.reset.config}")
	private String kafkaOffsetConfig;

	@Value("${invalid.input}")
	private String invalidInput;

	@Value("${duplicate.code}")
	private String duplicateCode;
	
	@Value("${success.status}")
	private String successStatus;
	
	@Value("${failed.status}")
	private String failedStatus;
	
	@Value("${default.page.size}")
	private String defaultPageSize;
	
	@Value("${default.page.number}")
	private String defaultPageNumber;
	
	@Value("${default.offset}")
	private String defaultOffset;
}