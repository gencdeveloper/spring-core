package com.cydeo.streoType_Annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan //sprint scan all the class which one has @Components then it will create @Bean as a default
@ComponentScan(basePackages = "com.cydeo")//package level, we can check under the this page
public class ConfigCourse {

}
