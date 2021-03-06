package com.thoughtmechanix.organization.event.source;

import com.thoughtmechanix.organization.event.models.OrganizationChangeModel;
import com.thoughtmechanix.organization.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author LucasLiu
 * @date 2021/10/25
 */
@Component
public class SimpleSourceBean {

    private Source source;
    private Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source) {
        this.source = source;
    }

    public void publishOrChange(String action, String orgId) {
        logger.debug("Sending Kafka message {} for Organization Id: {}"
                , action, orgId);
        OrganizationChangeModel change = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                orgId,
                UserContext.getCorrelationId()
        );
        source.output().send(MessageBuilder.withPayload(change).build());

    }
}
