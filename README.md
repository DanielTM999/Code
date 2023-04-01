Para solucioná-lo, podemos utilizar semáforos para representar os garfos e controlar o acesso a eles.

O algoritmo é composto por várias threads que representam os filósofos. Cada thread passa por várias etapas para garantir que possa comer usando os dois garfos próximos a ela, sem entrar em deadlock (bloqueio de processos) ou causar starvation (inanição de processos) em outras threads.

O processo consiste em inicializar um objeto Semaphore que representa um garfo, definindo que ele tem apenas uma permissão (representando que só pode ser usado por uma pessoa por vez). Também é criado um objeto do tipo Random para gerar números aleatórios.

Dentro de um loop infinito que simboliza a vida do filósofo, ele começa pensando por um tempo aleatório, utilizando o método sleep da classe TimeUnit.

Depois, tenta pegar o garfo à sua direita utilizando o método tryAcquire do objeto Semaphore que representa o garfo. Se conseguir pegá-lo, passa para a próxima etapa. Caso contrário, volta a pensar.

Tenta pegar o garfo à sua esquerda utilizando o mesmo método tryAcquire. Se conseguir pegá-lo, passa para a próxima etapa. Caso contrário, solta o garfo à sua direita e volta a pensar.

Agora que conseguiu obter os dois garfos, come por um tempo aleatório.

Por fim, devolve os garfos, soltando os semáforos correspondentes.

Esse processo é repetido enquanto o filósofo estiver vivo. A utilização dos semáforos permite que os filósofos compartilhem os garfos de maneira segura e evita problemas de sincronização de recursos.
