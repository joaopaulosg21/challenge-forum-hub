package projeto.desafio.forumHub.domain.topico;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projeto.desafio.forumHub.infra.exception.NotFoundException;
import projeto.desafio.forumHub.infra.exception.RegisterException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRepository repository;

    public DadosTopico registrar(DadosRegistroTopico dados) {
        Optional<Topico> optionalTopico = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());

        if(optionalTopico.isPresent()) {
            throw new RegisterException("Não é possivel registrar um topico com o mesmo titulo e mensagem");
        }
        Topico topico = repository.save(new Topico(dados));
        return new DadosTopico(topico);
    }

    public Page<DadosTopico> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable).map(DadosTopico::new);
    }

    public DadosTopico buscarPeloId(Long id) {
        Optional<Topico> optionalTopico = repository.findById(id);

        if(optionalTopico.isEmpty()) {
            throw new NotFoundException("Topico com esse id não encontrado");
        }

        return new DadosTopico(optionalTopico.get());
    }
}
