package com.heylichen.amq.jms.connectors;

import com.heylichen.amq.jms.commons.TopicAsynConsumer;
import com.heylichen.amq.jms.commons.TopicProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import static com.heylichen.amq.jms.connectors.ConnectorPublisher.TOPIC;

/**
 * Created by lc on 2016/6/10.
 */
public class ConnectorSubscriber implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(TopicProducer.class);
  private String url;
  private int holdForSeconds = 5;
  private boolean durable = true;

  public ConnectorSubscriber(String url) {
    this.url = url;
  }

  public ConnectorSubscriber(String url, int holdForSeconds) {
    this.url = url;
    this.holdForSeconds = holdForSeconds;
  }

  @Override
  public void run() {
    TopicAsynConsumer consumer = new TopicAsynConsumer(url, TOPIC, durable, new MessageListener() {
      @Override
      public void onMessage(Message message) {
        if(message instanceof TextMessage){
          TextMessage textMsg = (TextMessage) message;
          try{
            logger.info("got:{}", textMsg.getText());
          }catch (JMSException e){
            logger.error("", e);
          }
        }
      }
    });
    try {
      Thread.sleep(holdForSeconds*1000);
    } catch (InterruptedException e) {
      logger.error("", e);
    }
    consumer.close();
  }

  public boolean isDurable() {
    return durable;
  }

  public void setDurable(boolean durable) {
    this.durable = durable;
  }
}
