package com.recepcion.DataJSON.rest.processor;

import java.util.Properties;

import org.apache.camel.BeanInject;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LeerQueue implements Processor {

	private ConsumerTemplate consumer;
	private static final String PROPERTIES_FILE_DIR = "src/main/resources/";

	
	@BeanInject("loggerRef")
	private Logger log = LoggerFactory.getLogger(LeerQueue.class);
	@BeanInject(value = "props")
    Properties properties;
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		// CONSUMING QUEUE ORIGEN
		this.consumer = exchange.getContext().createConsumerTemplate();
		//String property =properties.getProperty("amq.queue.name");
		String queue = "activemq://queue:ARCHIVO.JSON";
		Exchange exchangeMessage = this.consumer.receive(queue);
		String body = exchangeMessage.getIn().getBody(String.class);
		log.info("Este es el mensaje leido"+ body);

	}

}
