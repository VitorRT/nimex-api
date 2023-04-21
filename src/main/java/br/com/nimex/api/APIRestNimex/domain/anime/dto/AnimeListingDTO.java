package br.com.nimex.api.APIRestNimex.domain.anime.dto;

import br.com.nimex.api.APIRestNimex.domain.anime.Anime;

public record AnimeListingDTO(String title, String image, Double score) {
    public AnimeListingDTO(Anime anime) {
        this(anime.getTitle(), anime.getImage(), anime.getScore());
    }
}
