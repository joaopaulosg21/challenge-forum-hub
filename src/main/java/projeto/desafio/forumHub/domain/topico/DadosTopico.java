package projeto.desafio.forumHub.domain.topico;

import java.time.LocalDateTime;

public record DadosTopico(Long id,String titulo, String mensagem, LocalDateTime dataCriacao) {

    public DadosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao());
    }
}
