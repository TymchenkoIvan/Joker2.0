package com.company.enums;

public enum Votes { LIKE("like"), DISLIKE("dislike");

    private String vote;

    Votes(String vote) {
        this.vote = vote;
    }

    public String getVote() {
        return vote;
    }
}