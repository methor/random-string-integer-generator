# Random-string-integer-generator
Random string/integer generator without trial-and-error iterations.

## RandUniqueStringGenerator
Generate random unique strings using the Floyd algorithm(trial-and-error free).

#### ```public List<String> generateUniqueStrings(int num, int maxLen, int minLen, String regex)``` 
The last argument `regex` chooses characters to consititute random strings from a set of characters, which defaults to `"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=,./<>?;':"~"`. You can override this set by passing your string to one of the constructors though normally it's unnecessary.

## RandStringGenerator
Generate random strings allowing repetition.

#### ```public List<String> generateStrings(int num, int maxLen, int minLen, String regex)```.

## RandIntegerGenerator
Like the first one, generate random integers using the Floyd algorithm.(Note: the following two functions are **static**)

#### ```public static List<Integer> generateUniqueIntegers(int num)```
Randomly select non-repetitive integers from 1 to `Integer.MAX_VALUE`.

#### ```public static List<Integer> generateUniqueIntegers(int num, int maxLen, int minLen)```
Randomly select integers with digit length from `minLen` to `maxLen`.
