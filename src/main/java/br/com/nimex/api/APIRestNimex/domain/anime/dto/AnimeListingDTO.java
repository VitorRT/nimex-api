package br.com.nimex.api.APIRestNimex.domain.anime.dto;

import br.com.nimex.api.APIRestNimex.domain.anime.Anime;

public record AnimeListingDTO(Long id, String title, String image, Double score) {
    public AnimeListingDTO(Anime anime) {
        this(anime.getId(),anime.getTitle(), anime.getImage(), anime.getScore());
    }
}
