import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Classe ClienteEspecialista
class ClienteEspecialista implements ICliente {
    private Espectador espectador;
    private Map<Integer, Integer> notasMidia;
    private int avaliacoesMesAnterior;

    public ClienteEspecialista(Espectador espectador) {
        this.espectador = espectador;
        this.notasMidia = new HashMap<>();
        this.avaliacoesMesAnterior = 0;
    }

    @Override
    public void atribuirNota(int idMidia, int nota) {
        if (nota < 1 || nota > 5) {
            System.out.println("A nota deve estar entre 1 e 5.");
            return;
        }

        if (notasMidia.containsKey(idMidia)) {
            System.out.println("Você já atribuiu uma nota para essa mídia.");
            return;
        }

        notasMidia.put(idMidia, nota);
        avaliacoesMesAnterior++;
    }

    public boolean isEspecialista() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate mesAnterior = dataAtual.minusMonths(1);

        return avaliacoesMesAnterior >= 5 && espectador.getAvaliacoesNoMes(mesAnterior) >= 5;
    }
}
