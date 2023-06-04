public class Avaliacao {
    private Midia midia;
    private int nota;
    private String comentario;

    public Avaliacao(Midia midia, int nota) {
        this.midia = midia;
        this.nota = nota;
    }

    public Avaliacao(Midia midia, int nota, String comentario) {
        this.midia = midia;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Midia getMidia() {
        return midia;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }
}