package nju.ics.huangmaosen;

import java.util.*;

/**
 * Created by mio on 2017/3/24.
 */
public class RandIntegerGenerator {

    public static List<Integer> generateUniqueIntegers(int num) {
        if (num <= 0)
            throw new IllegalArgumentException("num must be positive!");

        Set<Integer> integerSet = new HashSet<>();
        int n = Integer.MAX_VALUE;
        int ni = n - num;

        Random random = new Random();
        for (int i = 0; i < num; i++, ni++) {
            int r = random.nextInt(ni + 1);
            if (integerSet.contains(r))
                r = ni;
            integerSet.add(r+1);
        }

        return new ArrayList<>(integerSet);
    }


    public static List<Integer> generateUniqueIntegers(int num, int maxLen, int minLen) {

        if (num <= 0)
            throw new IllegalArgumentException("num must be positive!");
        if (minLen <= 0)
            throw new IllegalArgumentException("minLen must be greater than 0!");
        if (maxLen < minLen)
            throw new IllegalArgumentException("maxLen must be greater than minLen!");



        Set<Integer> integerSet = new HashSet<>();
        int n = 1;
        for (int i = 0; i < maxLen; i++)
            n *= 10;
        n -= 1;
        if (maxLen == 10)
            n = Integer.MAX_VALUE;
        int ni = n - num;

        int base = 1;
        for (int i = 0; i < minLen - 1; i++)
            base *= 10;

        if (num > n - base)
            throw new IllegalArgumentException("number is too large!");


        Random random = new Random();
        for (int i = 0; i < num; i++, ni++) {
            int r = random.nextInt(ni + 1 - base) + base;
            if (integerSet.contains(r))
                r = ni;
            integerSet.add(r+1);
        }

        return new ArrayList<>(integerSet);
    }

}
