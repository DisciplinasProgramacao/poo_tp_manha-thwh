import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.EspectadorAvaliaException;
import exceptions.EspectadorException;

public class Espectador {
    private String nome;
    private String login;
    private String senha;
    private List<Midia> assistirFuturo; 
    private List<Midia> jaAssistida; 
    private List<Avaliacao> listaNotas;
    private List<LocalDate> datasSeriesAssistidas;

    public Espectador(String nome, String login, String senha, Profissional profissional) {
        

        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.assistirFuturo = new ArrayList<>();
        this.jaAssistida = new ArrayList<>();
        this.listaNotas = new ArrayList<>();
        this.datasSeriesAssistidas = new ArrayList<>();
    }

    public void adicionarNaLista(Midia midia) {
        if (!assistirFuturo.contains(midia)) {
            this.assistirFuturo.add(midia);
        }
    }

    public void retirarDaLista(Midia midia) {
        this.assistirFuturo.remove(midia);
    }

    public List<Midia> buscarGenero(Genero genero) {
        return assistirFuturo.stream()
                .filter(s -> s.getGenero() == genero)
                .collect(Collectors.toList());
    }

    public List<Midia> buscarIdioma(Idioma idioma) {
        return assistirFuturo.stream()
                .filter(s -> s.getIdioma() == idioma)
                .collect(Collectors.toList());
    }

    public List<Midia> buscarQtdEpisodios(int qtdEpisodios) {
        if (qtdEpisodios <= 1) {
            throw new IllegalArgumentException("A quantidade de episódios deve ser um valor válido e maior que 1.");
        }

        return assistirFuturo.stream()
                .filter(s -> s.getQuantidadeEpisodios() == qtdEpisodios)
                .collect(Collectors.toList());
    }

    public void registrarVisualizacao(Midia midia) {
        if (!this.jaAssistida.contains(midia)) {
            this.jaAssistida.add(midia);
            midia.registrarVisualizacao();
            this.datasSeriesAssistidas.add(LocalDate.now());
        }
    }

    public boolean podeAvaliarMidia(Midia midia) throws EspectadorAvaliaException {
        for (Avaliacao avaliacao : listaNotas) {
            if (avaliacao.getMidia().equals(midia) || !this.jaAssistida.contains(midia)) {
                return false;
            }
        }
        return true;
    }

    public void avaliarMidia(double nota, Midia midia) throws EspectadorAvaliaException, InvalidParameterException {
        if (podeAvaliarMidia(midia)) {
            Avaliacao avaliacao = new Avaliacao(this, midia, nota);
            this.listaNotas.add(avaliacao);
            midia.adicionarAvaliacao(avaliacao);
        } else {
            throw new InvalidParameterException("O cliente não pode avaliar esta mídia.");
        }
    }

   
    public List<Avaliacao> getAvaliacoes() {
        return this.listaNotas;
    }

    public List<LocalDate> getDatasSeriesAssistidas() {
        return this.datasSeriesAssistidas;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Midia> getAssistirFuturo() {
        return this.assistirFuturo;
    }

    public void setAssistirFuturo(List<Midia> assistirFuturo) {
        this.assistirFuturo = assistirFuturo;
    }

    public List<Midia> getJaAssistida() {
        return this.jaAssistida;
    }

    public void setJaAssistida(List<Midia> jaAssistida) {
        this.jaAssistida = jaAssistida;
    }	

}
