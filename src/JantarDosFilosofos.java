import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class JantarDosFilosofos {

    private static final int NUM_FILOSOFOS = 5;
    private static final int TEMPO_MAXIMO_ESPERA = 1000;
    private static final int TEMPO_MAXIMO_COMER = 10000;
    private static final Semaphore[] GARFOS = new Semaphore[NUM_FILOSOFOS];

    public static void main(String[] args) {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            GARFOS[i] = new Semaphore(1);
        }

        Thread[] filosofos = new Thread[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new Thread(new Filosofo(i, GARFOS[i], GARFOS[(i + 1) % NUM_FILOSOFOS]));
            filosofos[i].start();
        }

        for (Thread filosofo : filosofos) {
            try {
                filosofo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Filosofo implements Runnable {

        private final int id;
        private final Semaphore garfoEsquerdo;
        private final Semaphore garfoDireito;

        public Filosofo(int id, Semaphore garfoEsquerdo, Semaphore garfoDireito) {
            this.id = id;
            this.garfoEsquerdo = garfoEsquerdo;
            this.garfoDireito = garfoDireito;
        }

        @Override
        public void run() {
            while (true) {
                pensar();
                pegarGarfos();
                comer();
                devolverGarfos();
            }
        }

        private void pensar() {
            System.out.println("Filósofo " + id + " está pensando");
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * TEMPO_MAXIMO_ESPERA));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void pegarGarfos() {
            while (!tentarObterGarfos()) {
                esperar();
            }
        }

        private boolean tentarObterGarfos() {
            if (!garfoEsquerdo.tryAcquire()) {
                return false;
            }
            System.out.println("Filósofo " + id + " pegou o garfo esquerdo");
            if (!garfoDireito.tryAcquire()) {
                garfoEsquerdo.release();
                return false;
            }
            System.out.println("Filósofo " + id + " pegou o garfo direito");
            return true;
        }

        private void esperar() {
            System.out.println("Filósofo " + id + " está esperando");
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * TEMPO_MAXIMO_ESPERA));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void comer() {
            System.out.println("Filósofo " + id + " está comendo");
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * TEMPO_MAXIMO_COMER));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void devolverGarfos() {
            garfoDireito.release();
            System.out.println("Filósofo " + id + " devolveu o garfo direito");
            garfoEsquerdo.release();
            System.out.println("Filósofo " + id + " devolveu o garfo esquerdo");
        }
    }
}

// Eu criei um objeto do tipo Semaphore que representa um garfo, definindo que
// ele tem apenas.
// uma permissão (representando que só pode ser usado por uma pessoa por vez).
// Também criei um objeto do tipo Random para gerar números aleatórios.
// Dentro de um loop infinito que simboliza minha vida, eu começo pensando por
// um tempo aleatório, utilizando o método {sleep} da classe TimeUnit.
// Depois, tento pegar o garfo à minha direita utilizando o método {tryAcquire}
// do objeto Semaphore que representa o garfo. Se eu conseguir pegá-lo, passo
// para a próxima etapa. Caso contrário, volto a pensar.
// Tento pegar o garfo à minha esquerda utilizando o mesmo método {tryAcquire}.
// Se eu conseguir pegá-lo, passo para a próxima etapa. Caso contrário, solto o
// garfo à minha direita e volto a pensar.
// Agora que consegui obter os dois garfos, como por um tempo aleatório.
// Por fim, devolvo os garfos, soltando os semáforos correspondentes.
// Esse processo é repetido enquanto eu estiver vivo.



private void pensar() {
            System.out.println("Filósofo " + id + " está pensando");
            try {
                long tempoInicio = System.currentTimeMillis();
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * TEMPO_MAXIMO_ESPERA));
                tempoPensando += System.currentTimeMillis() - tempoInicio;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
import java.util.concurrent.TimeUnit;
