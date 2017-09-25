package com.chw.spb.config;

import com.chw.spb.system.service.ResultMapService;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(ApplicationStartup.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        SqlSessionFactory sessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        Configuration configuration = sessionFactory.getConfiguration();
        Collection<ResultMap> resultMaps = configuration.getResultMaps();
        Iterator iterator = resultMaps.iterator();
        ResultMapService.clear();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next instanceof ResultMap) {
                ResultMap resultMap = (ResultMap) next;
                Class type = resultMap.getType();
                List<ResultMapping> resultMappings = resultMap.getResultMappings();
                for (ResultMapping resultMapping : resultMappings) {
                    log.info("cache resultMapping key : {},value :{}",type.getName() + "." + resultMapping.getProperty(),resultMapping.getColumn());
                    ResultMapService.addResultMap(type.getName() + "." + resultMapping.getProperty(), resultMapping.getColumn());
                }
            }
        }
        log.info("cached resultMapping over!! {} properties",ResultMapService.getResultMap().size());
    }
}
