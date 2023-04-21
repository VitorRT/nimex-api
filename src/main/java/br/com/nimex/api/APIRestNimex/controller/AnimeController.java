package br.com.nimex.api.APIRestNimex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nimex.api.APIRestNimex.domain.anime.Anime;
import br.com.nimex.api.APIRestNimex.domain.anime.dto.AnimeListingDTO;
import br.com.nimex.api.APIRestNimex.domain.anime.dto.AnimeRegisterAndUpdateDTO;
import br.com.nimex.api.APIRestNimex.exception.RestNotFoundException;
import br.com.nimex.api.APIRestNimex.repository.AnimeRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/anime")
@Slf4j
public class AnimeController {

    @Autowired
    private AnimeRepository repository;

    
    @GetMapping
    public Page<AnimeListingDTO> getAnimes(
        @RequestParam(required = false) String search, 
        @RequestParam(required = false) String starting, 
        @RequestParam(required = false) String ending, 
        @PageableDefault(size = 10) Pageable pageable
        ) {
            log.info("[ Search ] - Buscando todos os animes no banco.");
            if (search == null && starting == null && ending == null) {
                return repository.findAllByActiveTrue(pageable).map(AnimeListingDTO::new);
            }
            
            if (starting != null) {
                return repository.findByTitleStartingWith(starting, pageable).map(AnimeListingDTO::new);
            }
            
            if (ending != null) {
                return repository.findByTitleEndingWith(ending, pageable).map(AnimeListingDTO::new);
            }
        
            // utiliza a especificação quando houver um valor para search
            return repository.findByTitleContaining(ending, pageable).map(AnimeListingDTO::new);

    }

    @PostMapping
    public ResponseEntity<Anime> postAnime(
        @RequestBody @Valid AnimeRegisterAndUpdateDTO dto
        ) {
        log.info("[ Create ] - Cadastrando novo anime: " + dto.title() + ".");
        Anime anime = new Anime();
        anime.toAnime(dto);
        repository.save(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(anime);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        log.info("[ Show ] - Detalhando um anime com o id: " + id + ".");
        return ResponseEntity.ok(getAnime(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Anime> deleteAnimeById(@PathVariable Long id) {
        log.info("[ Delete ] - Deletando um anime com o id: " + id + ".");
        Anime anime = getAnime(id);
        repository.delete(anime);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anime> updateAnime(
        @PathVariable Long id, 
        @RequestBody @Valid AnimeRegisterAndUpdateDTO dto
        ) {
        log.info("[ Delete ] - Deletando um anime com o id: " + id + ".");
        getAnime(id);
        Anime anime = new Anime();
        anime.toAnime(dto);
        anime.setId(id);
        repository.save(anime);
        return ResponseEntity.ok(anime);
    }


 

    private Anime getAnime(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            return new RestNotFoundException("Anime com o id [ " + id + " ] não encontrado.");
        });
    }
}
