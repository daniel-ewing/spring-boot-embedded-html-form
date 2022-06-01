package org.example.spring.boot.embedded.html.form.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.Spin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("SetSelectValues")
@Slf4j
public class SetSelectValues implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if (log.isDebugEnabled()) log.debug("-----> execute: Enter - {}", delegateExecution.getCurrentActivityId());

        Map<String, String> slaSelectValues = new HashMap<String, String>();
        slaSelectValues.put("001", "Advanced");
        slaSelectValues.put("002", "Premium");
        slaSelectValues.put("003", "Standard");

        delegateExecution.setVariable("slaSelectValues",  Spin.JSON(slaSelectValues));

        if (log.isDebugEnabled()) log.debug("-----> execute: Exit - {}", delegateExecution.getCurrentActivityId());
    }
}
