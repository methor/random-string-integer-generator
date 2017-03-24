package nju.ics.huangmaosen;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mio on 2017/3/24.
 */
public class RandStringGenerator {


    private String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "0123456789!@#$%^&*()_+-=,./<>?;':\"`~";
    private Map<Character, Integer> characterMap = new HashMap<>();

    public List<String> generateStrings(int num, int maxLen, int minLen, String regex) {
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

        if (characters.length() < 1)
            throw new IllegalArgumentException("regex matches no element!");

        List<String> stringList = new ArrayList<>();



        for (int i = 0; i < num; i++)
            stringList.add(randomString(maxLen, minLen));
        return stringList;
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
}
