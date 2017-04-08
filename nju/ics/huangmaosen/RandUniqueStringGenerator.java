package nju.ics.huangmaosen;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mio on 2017/3/23.
 */
public final class RandUniqueStringGenerator {



    private String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "0123456789!@#$%^&*()_+-=,./<>?;':\"`~";
    private Map<Character, Integer> characterMap = new HashMap<>();

    public RandUniqueStringGenerator() {
        setCharacters(this.characters);
    }

    public RandUniqueStringGenerator(String characters) {
        setCharacters(characters);
    }

    public String getCharacters() {
        return characters;
    }

    public RandUniqueStringGenerator setCharacters(String characters) {
        if (characters != null && characters.length() > 0)
            this.characters = characters;
        characterMap.clear();
        for (int i = 0; i < this.characters.length(); i++) {
            characterMap.put(this.characters.charAt(i), i);
        }
        return this;
    }




    public List<String> generateUniqueStrings(int num, int maxLen, int minLen, String regex) {

        if (minLen <= 0)
            throw new IllegalArgumentException("minimum length must be greater than 0!");
        if (maxLen < minLen)
            throw new IllegalArgumentException("maxLen must be greater than minLen!");

        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(characters);

        while (matcher.find()) {
            stringBuilder.append(matcher.group());
        }
        characters = stringBuilder.toString();



        stringBuilder = new StringBuilder();
        for (int i = 0; i < maxLen; i++)
            stringBuilder.append(characters.charAt(characters.length() - 1));

        String nString = stringBuilder.toString();
        if (stringCompare(nString, numberToString(num)) < 0)
            throw new IllegalArgumentException("num is too large!");
        String inString = stringAddNum(nString, -num);
        Set<String> stringSet = new HashSet<>();

        int im = 0;
        while (im < num) {
            String inStringPlus1 = stringAddNum(inString, 1);
            String randString = randomString(maxLen, minLen);
            randString = stringModString(randString, inStringPlus1);

            if (stringSet.contains(randString))
                randString = inStringPlus1;
            stringSet.add(randString);

            im++;
            inString = inStringPlus1;
        }


        return new ArrayList<>(stringSet);
    }



    private String numberToString(int number) {
        int radix = characters.length();
        StringBuilder stringBuilder = new StringBuilder();
        while (number > 0) {
            int remainder = number % radix;
            stringBuilder.append(characters.charAt(remainder));
            number /= radix;
        }
        return stringBuilder.reverse().toString();
    }

    private  String stringModString(String s, String s1) {

        int compare = stringCompare(s, s1);
        if (compare == -1)
            return s;
        else if (compare == 0) {
            return stringAddNum(s, -1);
        }
        else
            return stringAddString(s1, s, -1);

    }

    private int stringCompare(String s, String s1) {
        if (s.length() < s1.length())
            return -1;
        else if (s.length() > s1.length())
            return 1;
        else {
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char charS = s.charAt(s.length() - 1 - i);
                char charS1 = s1.charAt(s1.length() - 1 - i);
                int idxS = characterMap.get(charS);     // must not be null
                int idxS1 = characterMap.get(charS1);   // must not be null
                if (idxS < idxS1)
                    return -1;
                else if (idxS > idxS1)
                    return 1;
            }
            return 0;
        }
    }

    private String randomString(int maxLen, int minLen) {
        Random random = new Random();
        int len = random.nextInt(maxLen - minLen + 1) + minLen;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < len; i++) {
            int idx = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(idx));
        }
        return stringBuilder.toString();
    }


    private String stringAddNum(String s, int number) {
        int sign = (number < 0) ? -1 : 1;
        String s1 = numberToString(Math.abs(number));

        return stringAddString(s, s1, sign);
    }

    private String stringAddString(String s, String s1, int sign) {
        StringBuilder stringBuilder = new StringBuilder();

        int len = (s.length() > s1.length()) ? s.length() : s1.length();
        int carry = 0;
        for (int i = 0; i < len; i++) {
            char charS = (s.length() >= (i + 1)) ? s.charAt(s.length() - 1 - i) : '\n';
            char charS1 = (s1.length() >= (i + 1)) ? s1.charAt(s1.length() - 1 - i) : '\n';
            Integer idxS = characterMap.get(charS);
            Integer idxS1 = characterMap.get(charS1);
            idxS = (idxS == null) ? 0 : idxS;
            idxS1 = (idxS1 == null) ? 0 : idxS1 * sign;
            stringBuilder.append(characters.charAt(Math.floorMod(idxS + idxS1 + carry, characters.length())));
            if (idxS + idxS1 + carry < 0)
                carry = -1;
            else
                carry = (idxS + idxS1 + carry) / characters.length();
        }
        return stringBuilder.reverse().toString();
    }
}
