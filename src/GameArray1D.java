// 1D Game (c) 2022 Baltasar MIT License <baltasarq@gmail.com>
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


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

    public static boolean canWin(final int[] BOARD, int leap)
    {
        boolean toret = true;

        if ( leap < BOARD.length ) {
            toret = canReachEndRecursive( BOARD, leap, 0 );
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
