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

