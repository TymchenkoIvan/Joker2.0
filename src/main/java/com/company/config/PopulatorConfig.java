package com.company.config;

import com.company.entity.Joke;
import com.company.entity.User;
import com.company.entity.bean.dtobean.DTOBeans;
import com.company.entity.bean.formbean.FormBeans;
import com.company.populator.dtobean.DTOPopulator;
import com.company.populator.dtobean.impl.JokeDTOPopulator;
import com.company.populator.dtobean.impl.UserDTOPopulator;
import com.company.populator.entity.EntityPopulator;
import com.company.populator.entity.impl.JokePopulator;
import com.company.populator.entity.impl.UserPopulator;
import com.company.populator.factory.DTOBeanFactory;
import com.company.populator.factory.EntityFactory;
import com.company.populator.factory.FormBeanFactory;
import com.company.populator.formbean.FormBeanPopulator;
import com.company.populator.formbean.impl.AddJokePopulator;
import com.company.populator.formbean.impl.LogInPopulator;
import com.company.populator.formbean.impl.SignUpPopulator;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PopulatorConfig {

    private static final Map<FormBeans, FormBeanPopulator> BEAN_FACTORY_MAP =
            Collections.unmodifiableMap(new HashMap<FormBeans, FormBeanPopulator>() {{
                put(FormBeans.ADD_JOKE, new AddJokePopulator());
                put(FormBeans.SIGN_UP, new SignUpPopulator());
                put(FormBeans.LOG_IN, new LogInPopulator());
            }});

    private static final Map<Class, EntityPopulator> ENTITY_FACTORY_MAP =
            Collections.unmodifiableMap(new HashMap<Class, EntityPopulator>() {{
                put(Joke.class, new JokePopulator());
                put(User.class, new UserPopulator());
            }});

    private static final Map<DTOBeans, DTOPopulator> DTO_FACTORY_MAP =
            Collections.unmodifiableMap(new HashMap<DTOBeans, DTOPopulator>() {{
                put(DTOBeans.JokeDTO, new JokeDTOPopulator());
                put(DTOBeans.UserDTO, new UserDTOPopulator());
            }});

    @Bean
    public FormBeanFactory beanFactory(){
        return new FormBeanFactory(BEAN_FACTORY_MAP);
    }

    @Bean
    public EntityFactory entityFactory(){
        return new EntityFactory(ENTITY_FACTORY_MAP);
    }

    @Bean
    public DTOBeanFactory dtoFactory(){
        return new DTOBeanFactory(DTO_FACTORY_MAP);
    }
}
