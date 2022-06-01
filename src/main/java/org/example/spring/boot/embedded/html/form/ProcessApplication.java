package org.example.spring.boot.embedded.html.form;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableProcessApplication
@Slf4j
public class ProcessApplication {
	private final static String processKey = "process";
	private RuntimeService runtimeService;

	public ProcessApplication(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	@EventListener
	private void processPostDeploy(PostDeployEvent event) {
		if (log.isDebugEnabled()) log.debug("-----> processPostDeploy: Enter");

		Map<String, Object> variables = new HashMap<>();
		variables.put("var1", "val1");
		variables.put("var2", 3);
		variables.put("var3", 3.3d);
		variables.put("var4", 444444444444l);
		variables.put("var5", true);

		for (int pi = 1; pi <= 1; pi++) {
			runtimeService.startProcessInstanceByKey(processKey, processKey + " bk " + pi, variables);
			if ((pi % 1000) == 0) {
				if (log.isDebugEnabled()) log.debug("-----> processPostDeploy created: {} process instances", pi);
			}
		}

		if (log.isDebugEnabled()) log.debug("-----> processPostDeploy: Exit");
	}

	public static void main(String... args) {
		SpringApplication.run(ProcessApplication.class, args);
	}
}