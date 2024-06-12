package projeto.desafio.forumHub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.desafio.forumHub.domain.topico.DadosRegistroTopico;
import projeto.desafio.forumHub.domain.topico.DadosTopico;
import projeto.desafio.forumHub.domain.topico.Topico;
import projeto.desafio.forumHub.domain.topico.TopicoRepository;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {
    private final TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrar(@Valid @RequestBody DadosRegistroTopico dados, UriComponentsBuilder builder) {
        Topico topico = topicoRepository.save(new Topico(dados));
        URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosTopico(topico));
    }
}
