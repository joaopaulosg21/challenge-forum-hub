package projeto.desafio.forumHub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.desafio.forumHub.domain.topico.DadosRegistroTopico;
import projeto.desafio.forumHub.domain.topico.DadosTopico;
import projeto.desafio.forumHub.domain.topico.Topico;
import projeto.desafio.forumHub.domain.topico.TopicoRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {
    private final TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosTopico> registrar(@Valid @RequestBody DadosRegistroTopico dados, UriComponentsBuilder builder) {
        Topico topico = topicoRepository.save(new Topico(dados));
        URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<?>> buscarTodos(@PageableDefault(size = 10,sort = {"dataCriacao"},direction = Sort.Direction.ASC)
                                                             Pageable pageable) {
        var dadosTopicos = topicoRepository.findAll(pageable).map(DadosTopico::new);
        return ResponseEntity.ok(dadosTopicos);
    }
}
