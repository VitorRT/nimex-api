package br.com.nimex.api.APIRestNimex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nimex.api.APIRestNimex.domain.anime.Anime;
import br.com.nimex.api.APIRestNimex.repository.AnimeRepository;

@RestController
@RequestMapping("/api/v1/anime")
public class AnimeController {

    @Autowired
    private AnimeRepository repository;

    @GetMapping
    public List<Anime> getAnimes() {
       return repository.findAll();
    }
}
