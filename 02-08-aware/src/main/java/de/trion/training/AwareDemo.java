package de.trion.training;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class AwareDemo implements ApplicationContextAware, BeanNameAware, InitializingBean, DisposableBean {

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      applicationContext.getBeansWithAnnotation(Component.class)
      .forEach( (s,o)  -> System.out.println("Context-Bean: " + s));
   }

   @Override
   public void setBeanName(String name) {
      System.out.println("Mein Name: " + name);
   }

   @Override
   public void destroy() throws Exception {
      System.out.println("Ich beende mich!");
   }

   @Override
   public void afterPropertiesSet() throws Exception {
      System.out.println("Ich starte gerade...");
   }

   @PostConstruct
   public void postConstruct() {
      System.out.println("Postconstruct");
   }

   @PreDestroy
   public void preDestroy() {
      System.out.println("Ich beende mich.");
   }
}
