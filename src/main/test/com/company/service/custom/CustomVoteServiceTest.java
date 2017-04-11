package com.company.service.custom;

import com.company.DAO.JokeDAO;
import com.company.DAO.UserDAO;
import com.company.DAO.VoteDAO;
import com.company.entity.Joke;
import com.company.entity.User;
import com.company.service.JokeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomVoteServiceTest {

    private static final String CORRECT = "correct";
    private static final String WRONG = "wrong";
    private static final int JOKE_ID = 1;
    private static final int TEST_STRING_SIZE = 10;

    private Joke joke;
    private User user;

    @Mock
    private VoteDAO voteDAOMock;

    @Mock
    private UserDAO userDAOMock;

    @Mock
    private JokeDAO jokeDAOMock;

    @Mock
    private JokeService jokeServiceMock;

    @InjectMocks
    private  CustomVoteService voteService = new CustomVoteService();

    @Before
    public void setUp(){
        joke = new Joke();
        joke.setId(JOKE_ID);

        user = new User(CORRECT, randomString(), randomString());

        when(voteDAOMock.isVotePossible(JOKE_ID, CORRECT)).thenReturn(true);
        when(voteDAOMock.isVotePossible(JOKE_ID, WRONG)).thenReturn(false);

        when(userDAOMock.getUserByLogin(CORRECT)).thenReturn(user);
        when(jokeDAOMock.getJoke(JOKE_ID)).thenReturn(joke);

        doNothing().when(voteDAOMock).addVote(any(Joke.class), any(User.class));
        doNothing().when(jokeServiceMock).addLike(joke);
        doNothing().when(jokeServiceMock).addDislike(joke);
    }

    @Test
    public void whenIsVotePossibleCalledThenVoteDaoMethodCalledWithCorrectParameters(){
        voteService.isVotePossible(JOKE_ID, CORRECT);
        verify(voteDAOMock).isVotePossible(JOKE_ID, CORRECT);
    }

    @Test
    public void whenDaoIsVotePossibleReturnsBooleanThenServiceMethodReturnsTheSame(){
        assertTrue(voteService.isVotePossible(JOKE_ID, CORRECT));
        assertFalse(voteService.isVotePossible(JOKE_ID, WRONG));
    }

    @Test
    public void whenAddLikeCalledThenCallingUserDaoMethodWithCorrectParam(){
        voteService.addLike(JOKE_ID, CORRECT);
        verify(userDAOMock).getUserByLogin(CORRECT);
    }

    @Test
    public void whenAddLikeCalledThenCallingJokeDaoMethodWithCorrectParam(){
        voteService.addLike(JOKE_ID, CORRECT);
        verify(jokeDAOMock).getJoke(JOKE_ID);
    }

    @Test
    public void whenAddLikeCalledThenVoteDaoCallsWithCorrectArguments(){
        ArgumentCaptor<User> userArg = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Joke> jokeArg = ArgumentCaptor.forClass(Joke.class);

        voteService.addLike(JOKE_ID, CORRECT);

        verify(voteDAOMock).addVote(jokeArg.capture(), userArg.capture());
        assertEquals(joke.getText(), jokeArg.getValue().getText());
        assertEquals(user.getId(), userArg.getValue().getId());
    }

    @Test
    public void whenAddLikeCalledThenJokeServiceCallsWithCorrectArguments(){
        ArgumentCaptor<Joke> jokeArg = ArgumentCaptor.forClass(Joke.class);

        voteService.addLike(JOKE_ID, CORRECT);

        verify(jokeServiceMock).addLike(jokeArg.capture());
        assertEquals(joke.getText(), jokeArg.getValue().getText());
    }

    @Test
    public void whenAddDislikeCalledThenCallingUserDaoMethodWithCorrectParam(){
        voteService.addDislike(JOKE_ID, CORRECT);
        verify(userDAOMock).getUserByLogin(CORRECT);
    }

    @Test
    public void whenAddDislikeCalledThenCallingJokeDaoMethodWithCorrectParam(){
        voteService.addDislike(JOKE_ID, CORRECT);
        verify(jokeDAOMock).getJoke(JOKE_ID);
    }

    @Test
    public void whenAddDislikeCalledVoteDaoCallsWithCorrectArguments(){
        ArgumentCaptor<User> userArg = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Joke> jokeArg = ArgumentCaptor.forClass(Joke.class);

        voteService.addDislike(JOKE_ID, CORRECT);

        verify(voteDAOMock).addVote(jokeArg.capture(), userArg.capture());
        assertEquals(joke.getText(), jokeArg.getValue().getText());
        assertEquals(user.getId(), userArg.getValue().getId());
    }

    @Test
    public void whenAddDislikeCalledJokeServiceCallsWithCorrectArguments(){
        ArgumentCaptor<Joke> jokeArg = ArgumentCaptor.forClass(Joke.class);

        voteService.addDislike(JOKE_ID, CORRECT);

        verify(jokeServiceMock).addDislike(jokeArg.capture());
        assertEquals(joke.getText(), jokeArg.getValue().getText());
    }

    private String randomString(){
        return RandomStringUtils.randomAlphabetic(TEST_STRING_SIZE);
    }
}