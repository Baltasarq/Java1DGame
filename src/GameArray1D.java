// 1D Game (c) 2022 Baltasar MIT License <baltasarq@gmail.com>
import java.io.File;
import java.util.Scanner;


public class GameArray1D {
    public static boolean canReachEnd(final int[] BOARD, int leap)
    {

    }

    public static boolean canWin(final int[] BOARD, int leap)
    {
        boolean toret = true;
        int i = leap;

        if ( i < BOARD.length ) {
            toret = canReachEnd( BOARD, leap );
        }

        return toret;
    }

    public static void main(String[] args) throws Exception
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
