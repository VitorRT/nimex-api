package br.com.nimex.api.APIRestNimex.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nimex.api.APIRestNimex.domain.anime.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime,Long>{
  
    /** Busca de todos os animes ativos */
    Page<Anime> findAllByActiveTrue(Pageable pageable);

    /** Busca de animes que começam com a letra especificada no titulo */
    Page<Anime> findByTitleStartingWith(String starting, Pageable pageable);

    /** Busca de animes que terminam com a letra especificada no titulo */
    Page<Anime> findByTitleEndingWith(String ending, Pageable pageable);

    /** Busca de animes que contenha a letra especificada no titulo */
    Page<Anime> findByTitleContaining(String search, Pageable pageable);

}
