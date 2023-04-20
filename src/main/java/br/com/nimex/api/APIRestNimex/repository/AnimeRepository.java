package br.com.nimex.api.APIRestNimex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nimex.api.APIRestNimex.domain.anime.Anime;

public interface AnimeRepository extends JpaRepository<Anime,Long>{
    
}
