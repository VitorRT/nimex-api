package br.com.nimex.api.APIRestNimex.domain.anime.dto;

import br.com.nimex.api.APIRestNimex.domain.anime.AnimeStatus;

public record AnimeRegisterAndUpdateDTO(
    String title,
    String synopsis,
    Double score,
    AnimeStatus status,
    String genres,
    String image
) { }
