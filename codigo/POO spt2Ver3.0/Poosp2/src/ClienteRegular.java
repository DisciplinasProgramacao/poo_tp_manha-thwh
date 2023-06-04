import java.util.HashMap;
import java.util.Map;

// Classe ClienteRegular
class ClienteRegular implements ICliente {
    private Espectador espectador;
    private Map<Integer, Integer> notasMidia;

    public ClienteRegular(Espectador espectador) {
        this.espectador = espectador;
        this.notasMidia = new HashMap<>();
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
    }
}