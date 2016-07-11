package com.company.config;

import com.company.entity.bean.formbean.FormBeans;
import com.company.populator.formbean.AddJokePopulator;
import com.company.populator.formbean.FormBeanFactory;
import com.company.populator.formbean.FormBeanPopulator;
import com.company.populator.formbean.SignUpPopulator;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PopulatorConfig {

    private static final Map<FormBeans, FormBeanPopulator> populatorFactoryMap =
            Collections.unmodifiableMap(new HashMap<FormBeans, FormBeanPopulator>() {{
                put(FormBeans.ADD_JOKE, new AddJokePopulator());
                put(FormBeans.SIGN_UP, new SignUpPopulator());
                put(FormBeans.LOG_IN, new AddJokePopulator());
            }});

    @Bean
    public FormBeanFactory beanFactory(){
        return new FormBeanFactory(populatorFactoryMap);
    }
}
