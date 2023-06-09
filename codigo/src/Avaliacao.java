import java.security.InvalidParameterException;

public class Avaliacao {
    private Espectador espectador;
    private Midia midia;
    private double nota;

    public Avaliacao(Espectador espectador, Midia midia, double nota) {
        this.espectador = espectador;
        this.midia = midia;
        setNota(nota); // Utiliza o método setter para validar a nota
    }

    public Espectador getEspectador() {
        return espectador;
    }

    public void setEspectador(Espectador espectador) {
        this.espectador = espectador;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        if (nota < 0 || nota > 5) {
            throw new InvalidParameterException("A nota deve ser entre 0 e 5!");
        }
        this.nota = nota;
    }
}


   

