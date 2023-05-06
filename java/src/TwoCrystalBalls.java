package src;

public class TwoCrystalBalls {
    public static int twoCrystalBalls(boolean[] breaks) {
        int jmpAmount = (int) Math.floor(Math.sqrt(breaks.length));

        int i = jmpAmount;
        for (; i < breaks.length; i += jmpAmount) {
            if (breaks[i]) {
                break;
            }
        }

        i -= jmpAmount;

        for (int j = 0; j <= jmpAmount && i < breaks.length; ++j, ++i) {
            if (breaks[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int twoCrystalBalls2(boolean[] breaks) {
        int jmpAmount = 1;
        int i = jmpAmount;
        for (; i < breaks.length; i += jmpAmount) {
            if (breaks[i]) {
                break;
            }
            jmpAmount++;
        }

        i -= jmpAmount;

        for (int j = 0; j <= jmpAmount && i < breaks.length; ++j, ++i) {
            if (breaks[i]) {
                return i;
            }
        }

        return -1;
    }
}
