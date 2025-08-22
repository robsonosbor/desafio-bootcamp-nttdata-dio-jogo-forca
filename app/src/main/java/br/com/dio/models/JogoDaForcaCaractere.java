package br.com.dio.models;

import java.util.Objects;

public class JogoDaForcaCaractere {
    private final char caractere;
    private boolean visivel;
    private int posicao;

    // Construtor para caractere oculto
    public JogoDaForcaCaractere(char caractere) {
        this.caractere = caractere;
        this.visivel = false;
    }

    // Construtor para caractere já visível e com posição definida
    public JogoDaForcaCaractere(char caractere, int posicao) {
        this.caractere = caractere;
        this.posicao = posicao;
        this.visivel = true;
    }

    public char getCaractere() {
        return caractere;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public boolean isInvisivel() {
        return !visivel;
    }

    public void habilitarVisibilidade() {
        this.visivel = true;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(caractere, visivel, posicao);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JogoDaForcaCaractere that &&
                caractere == that.caractere &&
                visivel == that.visivel &&
                posicao == that.posicao;
    }

    @Override
    public String toString() {
        return "JogoDaForcaCaractere{" +
                "caractere=" + caractere +
                ", visivel=" + visivel +
                ", posicao=" + posicao +
                '}';
    }
}