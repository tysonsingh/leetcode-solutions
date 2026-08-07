import java.util.*;

class Solution {

    private static final Map<Integer, Map<Integer, Integer>> FACTOR_COUNTS = Map.of(
            0, Map.of(),
            1, Map.of(),
            2, Map.of(2, 1),
            3, Map.of(3, 1),
            4, Map.of(2, 2),
            5, Map.of(5, 1),
            6, Map.of(2, 1, 3, 1),
            7, Map.of(7, 1),
            8, Map.of(2, 3),
            9, Map.of(3, 2)
    );

    public String smallestNumber(String num, long t) {

        PrimeResult result = getPrimeCount(t);

        if (!result.valid)
            return "-1";

        Map<Integer, Integer> need = result.count;

        Map<Integer, Integer> factorCount = getFactorCount(need);

        if (sum(factorCount) > num.length())
            return construct(factorCount);

        Map<Integer, Integer> prefix = getPrimeCount(num);

        int firstZero = num.indexOf('0');

        if (firstZero == -1) {
            firstZero = num.length();

            if (isSubset(need, prefix))
                return num;
        }

        for (int i = num.length() - 1; i >= 0; i--) {

            int d = num.charAt(i) - '0';

            prefix = subtract(prefix, FACTOR_COUNTS.get(d));

            int remainingSpace = num.length() - 1 - i;

            if (i > firstZero)
                continue;

            for (int bigger = d + 1; bigger <= 9; bigger++) {

                Map<Integer, Integer> required =
                        getFactorCount(
                                subtract(
                                        subtract(need, prefix),
                                        FACTOR_COUNTS.get(bigger)
                                )
                        );

                if (sum(required) <= remainingSpace) {

                    int ones = remainingSpace - sum(required);

                    return num.substring(0, i)
                            + bigger
                            + "1".repeat(ones)
                            + construct(required);
                }
            }
        }

        Map<Integer, Integer> extension = getFactorCount(need);

        return "1".repeat(num.length() + 1 - sum(extension))
                + construct(extension);
    }

    static class PrimeResult {
        Map<Integer, Integer> count;
        boolean valid;

        PrimeResult(Map<Integer, Integer> c, boolean v) {
            count = c;
            valid = v;
        }
    }

    private PrimeResult getPrimeCount(long t) {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(2, 0);
        map.put(3, 0);
        map.put(5, 0);
        map.put(7, 0);

        int[] primes = {2, 3, 5, 7};

        for (int p : primes) {
            while (t % p == 0) {
                t /= p;
                map.put(p, map.get(p) + 1);
            }
        }

        return new PrimeResult(map, t == 1);
    }

    private Map<Integer, Integer> getPrimeCount(String num) {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(2, 0);
        map.put(3, 0);
        map.put(5, 0);
        map.put(7, 0);

        for (char c : num.toCharArray()) {

            Map<Integer, Integer> factors = FACTOR_COUNTS.get(c - '0');

            for (Map.Entry<Integer, Integer> e : factors.entrySet()) {

                map.put(e.getKey(), map.get(e.getKey()) + e.getValue());
            }
        }

        return map;
    }

    private Map<Integer, Integer> getFactorCount(Map<Integer, Integer> cnt) {

        int count8 = cnt.get(2) / 3;
        int remain2 = cnt.get(2) % 3;

        int count9 = cnt.get(3) / 2;
        int count3 = cnt.get(3) % 2;

        int count4 = remain2 / 2;
        int count2 = remain2 % 2;

        int count6 = 0;

        if (count2 == 1 && count3 == 1) {
            count2 = 0;
            count3 = 0;
            count6 = 1;
        }

        if (count3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            count3 = 0;
            count4 = 0;
        }

        Map<Integer, Integer> res = new HashMap<>();

        res.put(2, count2);
        res.put(3, count3);
        res.put(4, count4);
        res.put(5, cnt.get(5));
        res.put(6, count6);
        res.put(7, cnt.get(7));
        res.put(8, count8);
        res.put(9, count9);

        return res;
    }

    private String construct(Map<Integer, Integer> factors) {

        StringBuilder sb = new StringBuilder();

        for (int d = 2; d <= 9; d++) {

            int freq = factors.getOrDefault(d, 0);

            while (freq-- > 0)
                sb.append(d);
        }

        return sb.toString();
    }

    private boolean isSubset(Map<Integer, Integer> need,
                             Map<Integer, Integer> have) {

        for (Map.Entry<Integer, Integer> e : need.entrySet()) {

            if (have.get(e.getKey()) < e.getValue())
                return false;
        }

        return true;
    }

    private Map<Integer, Integer> subtract(Map<Integer, Integer> a,
                                           Map<Integer, Integer> b) {

        Map<Integer, Integer> res = new HashMap<>(a);

        for (Map.Entry<Integer, Integer> e : b.entrySet()) {

            int key = e.getKey();

            res.put(key,
                    Math.max(0,
                            res.get(key) - e.getValue()));
        }

        return res;
    }

    private int sum(Map<Integer, Integer> map) {

        int ans = 0;

        for (int x : map.values())
            ans += x;

        return ans;
    }
}