package br.com.dio.models;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List; import java.util.stream.Stream;
import br.com.dio.exceptions.LetraJaEscolhidaException;
import static br.com.dio.models.JogoDaForcaStatus.*;


public class JogoDaForca {

    private static final int TAMANHO_INICIAL_LINHA = 9;
    private static final int LIMITE_ERROS = 6;

    private final int tamanhoDaLinha;
    private final int tamanhoInicialForca;
    private final Deque<JogoDaForcaCaractere> bonecoForca;
    private final List<JogoDaForcaCaractere> caracteres;
    private final List<Character> tentativasFalhas = new ArrayList<>();

    private String forca;
    private JogoDaForcaStatus statusJogo = PENDENTE;

    public JogoDaForca(List<JogoDaForcaCaractere> caracteres) {
        String espacoEmBranco = " ".repeat(caracteres.size());
        String caracteresDeEspaco = "-".repeat(caracteres.size());

        this.tamanhoDaLinha = TAMANHO_INICIAL_LINHA + espacoEmBranco.length() + System.lineSeparator().length();
        this.bonecoForca = new ArrayDeque<>(construirBonecoForca());
        construirForca(espacoEmBranco, caracteresDeEspaco);
        this.caracteres = colocarCaracteresEspacoNoJogo(new ArrayList<>(caracteres));
        this.tamanhoInicialForca = forca.length();
    }

    public JogoDaForcaStatus getStatusDoJogo() { return statusJogo; }

    @Override
    public String toString() { return forca; }

    public String finalizarJogo() {
        return statusJogo == VITORIA ? "Parabéns! Você ganhou o jogo" : "Você perdeu! Tente novamente";
    }

    public void adicionarCaractere(char caractere) {
        if (tentativasFalhas.contains(caractere) || letraJaRevelada(caractere)) {
            throw new LetraJaEscolhidaException(mensagemLetraJaInformada(caractere));
        }

        var encontrados = caracteres.stream()
                .filter(c -> c.getCaractere() == caractere)
                .toList();

        if (encontrados.isEmpty()) {
            registrarErro(caractere);
        } else {
            revelarLetras(encontrados);
        }
    }

    private void registrarErro(char caractere) {
        tentativasFalhas.add(caractere);
        if (tentativasFalhas.size() >= LIMITE_ERROS) statusJogo = DERROTA;
        reconstruirForca(bonecoForca.pollFirst());
    }

    private void revelarLetras(List<JogoDaForcaCaractere> encontrados) {
        encontrados.forEach(JogoDaForcaCaractere::habilitarVisibilidade);
        if (caracteres.stream().noneMatch(JogoDaForcaCaractere::isInvisivel)) statusJogo = VITORIA;
        reconstruirForca(encontrados.toArray(JogoDaForcaCaractere[]::new));
    }

    private boolean letraJaRevelada(char caractere) {
        return caracteres.stream().anyMatch(c -> c.getCaractere() == caractere && c.isVisivel());
    }

    private String mensagemLetraJaInformada(char c) {
        return "A letra '" + c + "' já foi informada.";
    }

    private void reconstruirForca(final JogoDaForcaCaractere... JogoDaForcaCaracteres){
        var construtorForca = new StringBuilder(this.forca);
        Stream.of(JogoDaForcaCaracteres).forEach(
                h -> construtorForca.setCharAt(h.getPosicao(), h.getCaractere()
                ));
        var mensagemFalha = this.tentativasFalhas.isEmpty() ? "" : " Tentativas: " + this.tentativasFalhas;
        this.forca = construtorForca.substring(0, tamanhoInicialForca) + mensagemFalha;
    }

    private List<JogoDaForcaCaractere> construirBonecoForca(){
        final var LINHA_DA_CABECA = 3;
        final var LINHA_DO_CORPO = 4;
        final var LINHA_DA_PERNA = 5;
        return new ArrayList<>(
                List.of(
                        new JogoDaForcaCaractere('O', this.tamanhoDaLinha * LINHA_DA_CABECA + 6),
                        new JogoDaForcaCaractere('|', this.tamanhoDaLinha * LINHA_DO_CORPO + 6),
                        new JogoDaForcaCaractere('/', this.tamanhoDaLinha * LINHA_DO_CORPO + 5),
                        new JogoDaForcaCaractere('\\', this.tamanhoDaLinha * LINHA_DO_CORPO + 7),
                        new JogoDaForcaCaractere('/', this.tamanhoDaLinha * LINHA_DA_PERNA + 5),
                        new JogoDaForcaCaractere('\\', this.tamanhoDaLinha * LINHA_DA_PERNA + 7)
                )
        );
    }

    private List<JogoDaForcaCaractere> colocarCaracteresEspacoNoJogo(final List<JogoDaForcaCaractere> caracteres){
        final var LINHA_DAS_LETRAS = 7;
        for (int i = 0; i < caracteres.size(); i++) {
            caracteres.get(i).setPosicao(this.tamanhoDaLinha * LINHA_DAS_LETRAS + TAMANHO_INICIAL_LINHA + i);
        }
        return caracteres;
    }

    private void construirForca(final String espacoEmBranco, final String caracteresDeEspaco){
        this.forca = "  _____  " + espacoEmBranco + System.lineSeparator() +
                "  |   |  " + espacoEmBranco + System.lineSeparator() +
                "  |   |  " + espacoEmBranco + System.lineSeparator() +
                "  |      " + espacoEmBranco + System.lineSeparator() +
                "  |      " + espacoEmBranco + System.lineSeparator() +
                "  |      " + espacoEmBranco + System.lineSeparator() +
                "  |      " + espacoEmBranco + System.lineSeparator() +
                "=========" + caracteresDeEspaco + System.lineSeparator();
    }

}
