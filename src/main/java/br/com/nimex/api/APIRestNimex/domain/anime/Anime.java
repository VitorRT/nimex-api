package br.com.nimex.api.APIRestNimex.domain.anime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "TB_ANIME")
public class Anime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anm_id")
    private Long id;

    @Column(name = "anm_title")
    private String title;

    @Column(name = "anm_synopsis")
    private String synopsis;

    @Column(name = "anm_score")
    private Double score;
    
    @Column(name = "anm_status")
    private AnimeStatus status;

    @Column(name = "anm_genres")
    private String genres;

    @Column(name = "anm_img")
    private String imagem;
}
