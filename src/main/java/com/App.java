package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        EmploeeBean emploeeBean = (EmploeeBean) ctx.getBean("employee");
        System.out.println(emploeeBean);
    }
}
