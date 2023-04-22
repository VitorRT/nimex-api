package br.com.nimex.api.APIRestNimex.domain.anime.dto;


import br.com.nimex.api.APIRestNimex.domain.anime.AnimeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AnimeRegisterAndUpdateDTO(
    @NotBlank 
    @Size(min = 4, max = 180, message = "O Titulo deve ser significativo.")
    String title,

    @NotBlank(message = "Sinopse não informada.")  @Size(min = 8, message = "A sinopse deve ser significativa.")
    String synopsis,

    @NotNull(message = "Score não informada.")
    Double score,

    @NotNull(message = "Status não informado.")
    AnimeStatus status,

    @NotBlank(message = "Gênero não informado.")
    String genres,

    @NotBlank(message = "Imagem não informado.") 
    @Size(min = 5, message = "A Imagem é muito pequena.")
    @Pattern(regexp = ".*\\.(png|jpg)$", message = "A imagem não é um png ou um jpg.")
    String image
) { }
