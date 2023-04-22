package br.com.nimex.api.APIRestNimex.domain.anime;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.nimex.api.APIRestNimex.domain.anime.dto.AnimeRegisterAndUpdateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="TB_ANIME", uniqueConstraints=@UniqueConstraint(columnNames={"title"}))

public class Anime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String synopsis;

    private Double score;
    
    @Enumerated(EnumType.STRING)
    private AnimeStatus status;

    private String genres;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Boolean active;

    public void toAnime(AnimeRegisterAndUpdateDTO dto) {
        this.title = dto.title();
        this.synopsis = dto.synopsis();
        this.score = dto.score();
        this.status = dto.status();
        this.genres = dto.genres();
        this.image = dto.image();
        this.active = true;
    }

}
