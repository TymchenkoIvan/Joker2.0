package com.company.populator.entity.impl;

import com.company.entity.Joke;
import com.company.entity.bean.formbean.impl.JokeForm;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class JokePopulatorTest {

    private JokePopulator populator = new JokePopulator();
    private JokeForm jokeForm;

    @Before
    public void initTestData(){
        jokeForm = new JokeForm();
        jokeForm.setText("Text example.");
    }

    @Test
    public void givenJokeFormThenPopulatesAllFieldsCorrectlyToJoke() {
        assertThat(populator.populate(jokeForm))
                .isNotNull()
                .isInstanceOf(Joke.class)
                .hasFieldOrPropertyWithValue("text", jokeForm.getText())
                .hasFieldOrProperty("date");
    }

}