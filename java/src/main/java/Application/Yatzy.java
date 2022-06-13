package Application;

import java.util.Arrays;
import java.util.List;

public class Yatzy {

    protected int[] dice;

    public Yatzy(List<Integer> values) {
        dice = values.stream().mapToInt(i -> i).toArray();
    }

    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        int total = 0;
        total += d1;
        total += d2;
        total += d3;
        total += d4;
        total += d5;
        return total;
    }

    public static int yatzy(int... dice) {
        int[] counts = new int[6];
        for (int die : dice)
            counts[die - 1]++;
        for (int i = 0; i != 6; i++)
            if (counts[i] == 5)
                return 50;
        return 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        int sum = 0;
        if (d1 == 1) sum++;
        if (d2 == 1) sum++;
        if (d3 == 1) sum++;
        if (d4 == 1) sum++;
        if (d5 == 1)
            sum++;

        return sum;
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        int sum = 0;
        if (d1 == 2) sum += 2;
        if (d2 == 2) sum += 2;
        if (d3 == 2) sum += 2;
        if (d4 == 2) sum += 2;
        if (d5 == 2) sum += 2;
        return sum;
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        int s;
        s = 0;
        if (d1 == 3) s += 3;
        if (d2 == 3) s += 3;
        if (d3 == 3) s += 3;
        if (d4 == 3) s += 3;
        if (d5 == 3) s += 3;
        return s;
    }

    public static int[] getTallies(List<Integer> values) {
        int[] tallies = getEmptyArray();
        values.forEach(value -> tallies[value - 1]++);
        return tallies;
    }

    public int fours() {
        int sum = 0;

        for (int at = 0; at != 5; at++) {
            if (dice[at] == 4) {
                sum += 4;
            }
        }
        return sum;
    }

    public int fives() {
        int s = 0;
        int i;
        for (i = 0; i < dice.length; i++)
            if (dice[i] == 5)
                s = s + 5;
        return s;
    }

    public int sixes() {
        int sum = 0;
        for (int die : dice)
            if (die == 6)
                sum = sum + 6;
        return sum;
    }

    public static int scorePair(int d1, int d2, int d3, int d4, int d5) {
        var counts = counts(List.of(d1, d2, d3, d4, d5));
        int at;
        for (at = 0; at != 6; at++)
            if (counts[6 - at - 1] >= 2)
                return (6 - at) * 2;
        return 0;
    }

    private static int[] getEmptyArray() {
        return new int[6];
    }

    private static int[] counts(List<Integer> values) {
        int[] counts = getEmptyArray();
        for (int die : values)
            counts[die - 1]++;
        return counts;
    }

    public static int twoPair(int d1, int d2, int d3, int d4, int d5) {
        var counts = counts(List.of(d1, d2, d3, d4, d5));

        int n = 0;
        int score = 0;

        for (int i = 0; i < 6; i += 1) {
            if (counts[6 - i - 1] >= 2) {
                n++;
                score += (6 - i);
            }
        }

        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    public static int fourOfAKind(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies = getTallies(Arrays.asList(d1, d2, d3, d4, d5));
        for (int i = 0; i < 6; i++) {
            if (tallies[i] >= 4) {
                return (i + 1) * 4;
            }
        }

        return 0;
    }

    public static int threeOfAKind(int d1, int d2, int d3, int d4, int d5) {
        int[] t = getTallies(List.of(d1, d2, d3, d4, d5));

        for (int i = 0; i < 6; i++) {
            if (t[i] >= 3) {
                return (i + 1) * 3;
            }
        }

        return 0;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies = getTallies(Arrays.asList(d1, d2, d3, d4, d5));

        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies = getTallies(Arrays.asList(d1, d2, d3, d4, d5));

        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies = getTallies(List.of(d1, d2, d3, d4, d5));
        boolean isTallies = false;
        boolean isTalliesAux = false;

        int talliesCounter = 0;
        int talliesCounterAux = 0;

        for (int i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                isTallies = true;
                talliesCounter = i + 1;
            }

        for (int i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                isTalliesAux = true;
                talliesCounterAux = i + 1;
            }

        if (isTallies && isTalliesAux) {
            return talliesCounter * 2 + talliesCounterAux * 3;
        }
        return 0;
    }
}



