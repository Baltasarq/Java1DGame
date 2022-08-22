// 1D Game (c) 2022 Baltasar MIT License <baltasarq@gmail.com>
import java.io.File;
import java.util.Scanner;


public class GameArray1D {
    public static int walk(final int[] BOARD, int pos)
    {
        while( pos < BOARD.length
            && BOARD[ pos ] == 0 )
        {
            pos += 1;
        }

        if ( pos < BOARD.length
          && pos > 0 )
        {
            pos -= 1;
        }

        return pos;
    }

    public static int jump(final int[] BOARD, int leap, int pos)
    {
        int oldPos = pos;
        int toret = pos + leap;

        while( toret < BOARD.length
            && BOARD[ toret ] != 0 )
        {
            // Try to go back, if possible, and jump again
            // in order to try to find a 0 in between the 1's.
            if ( pos > 0
              && BOARD[ pos - 1 ] == 0 )
            {
                pos -= 1;
                toret -= 1;
            } else {
                break;
            }
        }

        if ( toret < BOARD.length
          && BOARD[ toret ] != 0 )
        {
            toret = oldPos;
        }

        return toret;
    }

    public static boolean canReachEnd(final int[] BOARD, int leap)
    {
        int oldie = -1;
        int i = walk( BOARD, 0 );

        while( i < BOARD.length
            && oldie != i )
        {
            oldie = i;

            i = jump( BOARD, leap, i );
            i = walk( BOARD, i );
        }

        return i >= BOARD.length;
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
