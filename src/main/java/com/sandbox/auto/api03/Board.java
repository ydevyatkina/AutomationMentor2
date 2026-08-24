package com.sandbox.auto.api03;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

    @JsonAlias("id")
    private String boardId;
    @JsonAlias("name")
    private String boardName;
    private Prefs prefs;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Prefs {
        private String background;
    }
}
