package com.recepcion.DataJSON.rest.processor;


import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class process implements Processor {

	
	@BeanInject("loggerRef")
	private Logger log = LoggerFactory.getLogger(process.class);
	public void process(Exchange exchange) throws Exception {
		
		
		 String body = exchange.getIn().getBody(String.class);
		    exchange.getOut().setBody("Este es el resultado de la consulta a la base de datos --> \n\n" + body);
		    log.info(body);
	}
}
