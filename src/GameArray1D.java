// 1D Game (c) 2022 Baltasar MIT License <baltasarq@gmail.com>
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import javax.lang.model.util.ElementScanner6;


public class GameArray1D {
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

    public static boolean canWin(final int[] BOARD, int leap)
    {
        boolean toret = true;

        if ( leap < BOARD.length ) {
            toret = canReachEndIterative( BOARD, leap );
        }

        return toret;
    }

    public static void main(String[] args) throws IOException
    {
        final Scanner SCAN = new Scanner( new File( "data.txt" ) );
        int q = SCAN.nextInt();

        while ( q > 0 ) {
            final int N = SCAN.nextInt();
            final int[] BOARD = new int[ N ];
            final int LEAP = SCAN.nextInt();

            for (int i = 0; i < N; i++) {
                BOARD[ i ] = SCAN.nextInt();
            }

            System.out.println( ( canWin( BOARD, LEAP ) ) ? "YES" : "NO" );
            q -= 1;
        }

        SCAN.close();
    }
}
