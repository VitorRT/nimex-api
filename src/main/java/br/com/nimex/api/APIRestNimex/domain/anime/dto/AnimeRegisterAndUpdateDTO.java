package br.com.nimex.api.APIRestNimex.domain.anime.dto;

import br.com.nimex.api.APIRestNimex.domain.anime.AnimeStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimeRegisterAndUpdateDTO(
    @NotBlank @Min(value = 4, message = "Titulo muito pequeno.") @Max(value = 180, message = "Titulo muito grande.")
    String title,

    @NotBlank(message = "Sinopse não informada.") @Min(value = 8, message = "Sinopse muito pequena.")
    String synopsis,

    @NotNull(message = "Score não informada.")
    Double score,

    @NotNull(message = "Status não informado.")
    AnimeStatus status,

    @NotBlank(message = "Gênero não informado.")
    String genres,

    @NotBlank(message = "Imagem não informado.")
    String image
) { }
