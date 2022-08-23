# Java1DGame

Pequeño ejercicio sobre un vector simple. Básicamente,se trata de recorrer el vector _BOARD_ desde el principio (posición 0), hasta más allá del final (posción _n_ - 1, siendo _n_ el tamaño del vector), evitando las casillas con obstáculos (que estarán marcadas con un 1).

## Descripción

Empiezas en el índice 0 del vector _BOARD_ (de tamaño _n_) con un valor dado _leap_, de forma que _i_ = 0. Está garantizado que _BOARD[0]_ siempre es 0 (los obstáculos se marcarán con un 1). Dada cualquier posición _i_, es posible moverse de las maneras siguientes:

1. Walk forward: Si _i_ + 1 == 0, entonces puede moverse hacia adelante.

2. Walk backward: Si _i_ - 1 == 0, entonces puede moverse hacia atrás.

3. Jump: si la posición _i_ + _leap_ de _BOARD_ contiene un 0, entonces puede saltar de la posición _i_ a la posición _i_ + _leap_.

El juego termina cuando se sale del vector, es decir, caminando o saltando se llega a la casilla _n_ o más allá.

## Formato de entrada

El primer dato de entrada es el número de ejercicios, en su propia línea. A continuación, el algoritmo leerá el tamaño del vector y el dato _leap_ en una línea separados por un espacio, mientras que en la siguiente línea aparecerán los unos y ceros que constituyen las posiciones del vector, también separados por espacios.

El archivo de entrada es _data.txt_. Por ejemplo:

    1
    10 3
    0001100011

## Formato de salida

Si un determinado juego puede ganarse, entonces se muestra "YES" en una sola línea. En caso contrario, se muestra "NO". En el caso explicado en el formato de entrada, la salida sería:

    YES

## Solución

En esta rama, se resolverá mediante _backtracking_. Se toma nota de cada paso, y cuando no se puede llegar a la solución, se elimina el último paso hasta completar todas las posibilidades del movimiento anterior, y se continúa retrocediendo (_backtracking_ o _vuelta atrás_) hasta que se han deshecho todos los pasos sin alcanzar la solución, o hasta que esta, finalmente se logra.

## Ejemplo

En el caso explicado en la sección de formato de entrada, tendremos un vector de 10 posiciones, y una capacidad de salto de 3 casillas.

|0|1|2|3|4|5|6|7|8|9|
|-|-|-|-|-|-|-|-|-|-|
|0|0|0|1|1|0|0|0|1|1|

El proceso de juego es el siguiente:

1. Caminamos hasta la casilla 2, dado que en la 3 hay un 1.

    |0|1|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |0|0|*|1|1|0|0|0|1|1|

2. Saltamos a la casilla 5, puesto que saltamos de 3 en 3 casillas.

    |0|1|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |0|0|0|1|1|*|0|0|1|1|

3. Caminamos hasta la casilla 7, dado que en la 8 hay un 1.

    |0|1|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |0|0|0|1|1|0|0|*|1|1|

4. Saltamos a la casilla 10, que está fuera del vector (el final es la casilla 9), y por lo tanto termina el juego.

## Discusión

La estrategia _backtracking_ no es la más sencilla, y de hecho normalmente suele utilizarse recursividad para simplificarla. En cualquier caso, la recursividad siempre es equivalente a un algoritmo iterativo con una pila, que es la aproximación que se aplicará como segundo paso.

## Solución recursiva

La solución recursiva es bastante intuitiva, aunque probablemente es la más compleja de entender en profundidad. El algoritmo puede verse a continuación:

```java
    public static boolean canReachEndRecursive(final int[] BOARD, int leap, int pos)
    {
        boolean toret = ( pos >= BOARD.length );

        if ( !toret
          && pos >= 0
          && BOARD[ pos ] == 0 )
        {
            BOARD[ pos ] = -1;

            if ( canReachEndRecursive( BOARD, leap, pos + 1 )
              || canReachEndRecursive( BOARD, leap, pos + leap )
              || canReachEndRecursive( BOARD, leap, pos - 1 ) )
            {
                toret = true;
            }
        }

        return toret;
    }
```

En primer lugar, se comprueba si hemos salido ya del vector. En caso negativo, y si estamos al menos en la posición 0, y la posición actual contenga un 0, podemos probar alguno de los tres posibles movimientos (avanzar una casilla, saltar _leap_ casillas, o retroceder una casilla). El algoritmo necesita marcar cada pasilla pasada para no volver a ella en el siguiente paso o posteriores; de ahí que en un primer momento se marque la casilla actual como -1. Si alguno de los tres posibles movimientos retorna **true**, entonces se devuelve **true**. En otro caso, todos habrán devuelto **false** y se devuelve **false** como resultado final.

Nótese que el estado lo "lleva" el parámetro _pos_. Como es posible que volvamos a pasar por una casilla ya visitada, será necesario marcarla con -1: este marcaje la segunda parte del estado.

Un caso concreto sería el siguiente, con _n_ = 10 y _leap_ = 3:

|0|1|2|3|4|5|6|7|8|9|
|-|-|-|-|-|-|-|-|-|-|
|0|0|0|1|0|1|0|0|1|1|

La secuencia de pasos para resolverlo sería la siguiente:

1. _canReachRecursive( BOARD, 3, 0)_
    |*|1|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |0|0|0|1|0|1|0|0|1|1|

2. _canReachRecursive( BOARD, 3, 1)_
    |0|*|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|0|0|1|0|1|0|0|1|1|

3. _canReachRecursive( BOARD, 3, 2)_
    |0|1|*|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|0|1|0|1|0|0|1|1|

4. _canReachRecursive( BOARD, 3, 3)_
    |0|1|2|*|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|0|1|0|0|1|1|

    Aquí nos encontramos con el primer obstáculo, BOARD[ 3 ] == 1. Por ello, se produce el primer retorno: se devuelve **false** a la llamada anterior, en el paso 3, y entonces continúa con la ejecución donde se quedó, es decir, intentará el salto.

5. _canReachRecursive( BOARD, 3, 2)_ _(continuación del paso 3)_
    |0|1|*|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|0|1|0|0|1|1|

    En esta llamada se va a intentar el salto.

6. _canReachRecursive( BOARD, 3, 5)_
    |0|1|2|3|4|*|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|0|1|0|0|1|1|

    Tras el salto, aterrizamos en un obstáculo, por lo que se devuelve **false** y se retorna al paso 5.

7. _canReachRecursive( BOARD, 3, 2)_ _(continuación del paso 5)_
    |0|1|*|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|0|1|0|0|1|1|

    Tras intentar avanzar y saltar, solo resta intentar retroceder.

8. _canReachRecursive( BOARD, 3, 1)_
    |0|*|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|0|1|0|0|1|1|

    Retroceso a la posición 1, habiendo ya eliminado el avance, queda el salto y el retroceso.

9. _canReachRecursive( BOARD, 3, 4)_
    |0|1|2|3|*|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|0|1|0|0|1|1|

    El salto "aterriza" en lugar correcto, ahora continuamos con la secuencia normal, intentando avanzar.

10. _canReachRecursive( BOARD, 3, 5)_
    |0|1|2|3|4|*|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|-1|1|0|0|1|1|

    Sin embargo, no se puede avanzar porque en la posición 5 hay un obstáculo. Se retorna **false** y volvemos al paso 9.

11. _canReachRecursive( BOARD, 3, 4)_ _(continuación del paso 9)_
    |0|1|2|3|*|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|0|1|0|0|1|1|

    Descartado avanzar, realizamos el salto.

12. _canReachRecursive( BOARD, 3, 7)_
    |0|1|2|3|4|5|6|*|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|-1|1|0|-1|1|1|

    El salto se realiza con éxito. Ahora avanzamos.

13. _canReachRecursive( BOARD, 3, 8)_
    |0|1|2|3|4|5|6|7|*|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|-1|1|0|-1|1|1|

    El avance no funciona, pues nos encontramos con un obstáculo. Así que se retorna **false** y volvemos al paso 12.

14. _canReachRecursive( BOARD, 3, 7)_ _continuación del paso 12_
    |0|1|2|3|4|5|6|*|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|-1|1|0|-1|1|1|

    Descartado avanzar, realizamos el salto.

15. _canReachRecursive( BOARD, 3, 10)_
    |0|1|2|3|4|5|6|*|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |-1|-1|-1|1|-1|1|0|-1|1|1|

    Hemos llegado al final ya que (_pos_ = 10) >= (_n_ = 10), por lo que retornamos **true** y se retorna **true** en toda la secuencia de llamadas recursivas.

## Solución iterativa

La solución iterativa consiste en "convertir" la recursividad en un bucle que utilice una pila o _stack_:

```java
    public static boolean canReachEndIterative(final int[] BOARD, int leap)
    {
        final Stack<Integer> STACK_POS = new Stack<>();
        boolean toret = false;

        STACK_POS.add( 0 );
        while( !STACK_POS.isEmpty() ) {
            int pos = STACK_POS.peek();

            if ( pos >= BOARD.length ) {
                toret = true;
                STACK_POS.clear();
            }
            else
            if ( ( pos + 1 ) >= BOARD.length
              || ( STACK_POS.search( pos + 1 ) < 0
                && BOARD[ pos + 1 ] == 0 ) )
            {
                STACK_POS.add( pos + 1 );
            }
            else
            if ( ( pos + leap ) >= BOARD.length
              || ( STACK_POS.search( pos + leap ) < 0
                && BOARD[ pos + leap ] == 0 ) )
            {
                STACK_POS.add( pos + leap );
            }
            else
            if ( ( pos - 1 ) >= 0
              && ( STACK_POS.search( pos - 1 ) < 0 )
              && BOARD[ pos - 1 ] == 0 )
            {
                STACK_POS.add( pos - 1 );
            }
            else {
                BOARD[ pos ] = -1;
                STACK_POS.pop();
            }
        }

        return toret;
    }
```

Comenzamos creando una pila _STACK_POS_ con un solo elemento, el 0. Esto indicará que la posición actual es la 0. Esta pila, junto con el marcaje de las casillas desde las que no se puede realizar ningún movimiento como -1, supondrña el estado del algoritmo en cada momento.

Tomando el mismo ejemplo que en el caso recursivo, con _n_ = 10 y _leap_ = 3:

|0|1|2|3|4|5|6|7|8|9|
|-|-|-|-|-|-|-|-|-|-|
|0|0|0|1|0|1|0|0|1|1|

La secuencia de pasos sería la siguiente. Indicaremos el contenido de la pila mediante corchetes y las posiciones separadas por comas en su interior. Este contenido irá cambiando con cada vuelta de bucle.

1. _STACK_POS_ = [0]

    |*|1|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |0|0|0|1|0|1|0|0|1|1|

    Comenzamos en la posición 0, y dado que es posible se intenta avanzar.

2. _STACK_POS_ = [0, 1]

    |0|*|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |0|0|0|1|0|1|0|0|1|1|

    De nuevo es posible avanzar.

3. _STACK_POS_ = [0, 1, 2]

    |0|1|*|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |0|0|0|1|0|1|0|0|1|1|

    No es posible continuar avanzando, así que en el siguiente _if_ se intenta el salto. Sin embargo, este no es factible tampoco (la posición 5 contiene un 1), así que se retrocede. Pero retroceder tampoco es posible, porque el resultado del retroceso nos llevaría a la posición 1, que ya existe en la pila, por lo que se descarta el tope de la pila, se marca la casilla como sin salida (-1), y se continúa.

4. _STACK_POS_ = [0, 1]

    |0|*|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    |0|0|-1|1|0|1|0|0|1|1|

    La siguiente posibilidad es saltar, ya que el avance nos llevaría a la casilla marcada como sin salida o -1.

5. _STACK_POS_ = [0, 1, 4]

    |0|1|2 |3|*|5|6|7|8|9|
    |-|-|- |-|-|-|-|-|-|-|
    |0|0|-1|1|0|1|0|0|1|1|

    El salto ha resultado correcto, por lo que la secuencia del algoritmo continúa con el intento de avance. Pero no se puede avanzar, puesto que la siguiente casilla contiene un obstáculo. Así que de nuevo "toca" el salto.

6. _STACK_POS_ = [0, 1, 4, 7]

    |0|1|2 |3|4|5|6|*|8|9|
    |-|-|- |-|-|-|-|-|-|-|
    |0|0|-1|1|0|1|0|0|1|1|

    De nuevo, se intenta avanzar pero no es posible. Así que se intenta el salto.

7. _STACK_POS_ = [0, 1, 4, 7, 10]

    |0|1|2 |3|4|5|6|7|8|9|
    |-|-|- |-|-|-|-|-|-|-|
    |0|0|-1|1|0|1|0|0|1|1|

    Hemos llegado al final del juego, puesto (_pos_ = 10) >= (_n_ = 10).
