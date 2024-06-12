package projeto.desafio.forumHub.domain.topico;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @CreatedDate
    private LocalDateTime dataCriacao;

    private String curso;

    //Alterar quando criar o model User
    private String autor;

    public Topico(DadosRegistroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = dados.curso();
        this.autor = dados.autor();
    }
}
