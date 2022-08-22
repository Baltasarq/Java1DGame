# Java1DGame

Pequeño ejercicio sobre un vector simple.

## Descripción

 You're standing at index of an -element array named . From some index (where

), you can perform one of the following moves:

    Move Backward: If cell 

exists and contains a , you can walk back to cell
.
Move Forward:

    If cell 

contains a zero, you can walk to cell
.
If cell
contains a zero, you can jump to cell
.
If you're standing in cell
or the value of

        , you can walk or jump off the end of the array and win the game.

In other words, you can move from index
to index , , or as long as the destination index is a cell containing a . If the destination index is greater than

, you win the game.

Function Description

Complete the canWin function in the editor below.

canWin has the following parameters:

    int leap: the size of the leap
    int game[n]: the array to traverse

Returns

    boolean: true if the game can be won, otherwise false

Input Format

The first line contains an integer,
, denoting the number of queries (i.e., function calls).
The

subsequent lines describe each query over two lines:

    The first line contains two space-separated integers describing the respective values of 

and
.
The second line contains
space-separated binary integers (i.e., zeroes and ones) describing the respective values of

Constraints

It is guaranteed that the value of is always.

## Solución

En esta rama, se resolverá de forma _eager_.

