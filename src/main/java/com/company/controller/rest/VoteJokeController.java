package com.company.controller.rest;

import com.company.entity.bean.dtobean.impl.JokeDTO;
import com.company.enums.Votes;
import com.company.service.JokeService;
import com.company.service.VoteService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/rest/joke")
public class VoteJokeController {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "{jokeId}/{vote}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<JokeDTO> likeAction(@PathVariable("jokeId") int jokeId,
                                              @PathVariable("vote") String vote,
                                              Principal principal) {
        String userLogin = principal.getName();

        if(!EnumUtils.isValidEnum(Votes.class, vote) || voteService.isVotePossible(jokeId, userLogin))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (Votes.LIKE.getVote().equals(vote))
            voteService.addLike(jokeId, userLogin);
        else if (Votes.DISLIKE.getVote().equals(vote))
            voteService.addDislike(jokeId, userLogin);

        return new ResponseEntity<>(jokeService.getJokeById(jokeId), HttpStatus.OK);
    }
}
