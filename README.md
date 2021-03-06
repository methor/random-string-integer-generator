# Random-string-integer-generator
*Random string/integer generator without trial-and-error iterations.*

## RandUniqueStringGenerator
Generate random unique strings using the Floyd algorithm(trial-and-error free).

#### ```public List<String> generateUniqueStrings(int num, int maxLen, int minLen, String regex)``` 
The last argument `regex` chooses characters to consititute random strings from a set of characters, which defaults to `abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=,./<>?;':"~`. You can override this set by passing your string to one of the constructors though normally it's unnecessary. For example, to choose all alphabets and numbers, `regex` can be `[a-zA-Z0-9]` or `[a-zA-Z0-9]+`.(be cautious not using `[a-zA-Z0-9]*`, because it can match no character thus causing infinite loop)

## RandStringGenerator
Generate random strings allowing repetition.

#### ```public List<String> generateStrings(int num, int maxLen, int minLen, String regex)```.

## RandIntegerGenerator
Like the first one, generate random integers using the Floyd algorithm.(Note: the following two functions are **static**)

#### ```public static List<Integer> generateUniqueIntegers(int num)```
Randomly select non-repetitive integers from 1 to `Integer.MAX_VALUE`.

#### ```public static List<Integer> generateUniqueIntegers(int num, int maxLen, int minLen)```
Randomly select integers with digit length from `minLen` to `maxLen`(also excludes 0, as previous).
