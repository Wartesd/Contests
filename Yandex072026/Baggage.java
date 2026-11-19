package Yandex072026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baggage {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);

        int n = 40;
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        int mid = n / 2;
        int leftSize = mid;
        int rightSize = n - mid;

        int leftCount = 1 << leftSize;
        long[] leftEncoded = new long[leftCount];
        for (int i = 0; i < leftCount; i++) {
            long s = 0;
            for (int j = 0; j < leftSize; j++) {
                if ((i & (1 << j)) != 0) {
                    s += nums[j];
                }
            }
            leftEncoded[i] = (s << 20) | i;
        }
        Arrays.sort(leftEncoded);

        int rightCount = 1 << rightSize;
        long[] rightEncoded = new long[rightCount];
        for (int i = 0; i < rightCount; i++) {
            long s = 0;
            for (int j = 0; j < rightSize; j++) {
                if ((i & (1 << j)) != 0) {
                    s += nums[mid + j];
                }
            }
            rightEncoded[i] = (s << 20) | i;
        }
        Arrays.sort(rightEncoded);


        if (findInside(leftEncoded, 0, leftSize)) {
            return;
        }
        // Поиск внутри правой половины
        if (findInside(rightEncoded, mid, rightSize)) {
            return;
        }


        for (int i = 0; i < rightCount; i++) {
            long rightSum = 0;
            for (int j = 0; j < rightSize; j++) {
                if ((i & (1 << j)) != 0) {
                    rightSum += nums[mid + j];
                }
            }

            int lo = 0, hi = leftCount - 1, idx = -1;
            while (lo <= hi) {
                int m = (lo + hi) >>> 1;
                long s = leftEncoded[m] >> 20;
                if (s == rightSum) { idx = m; break; }
                else if (s < rightSum) lo = m + 1;
                else hi = m - 1;
            }

            if (idx >= 0) {
                int l = idx;
                while (l > 0 && (leftEncoded[l - 1] >> 20) == rightSum) l--;
                int r = idx;
                while (r < leftCount - 1 && (leftEncoded[r + 1] >> 20) == rightSum) r++;

                for (int k = l; k <= r; k++) {
                    long lMask = leftEncoded[k] & 0xFFFFF;
                    if (lMask != 0 && i != 0) {
                        printResult(lMask, i, mid);
                        return;
                    }
                }
            }
        }
    }


    private static boolean findInside(long[] encoded, int offset, int size) {
        for (int i = 0; i < encoded.length; ) {
            long sum = encoded[i] >> 20;
            int j = i;
            while (j < encoded.length && (encoded[j] >> 20) == sum) j++;


            for (int a = i; a < j; a++) {
                long maskA = encoded[a] & 0xFFFFF;
                for (int b = a + 1; b < j; b++) {
                    long maskB = encoded[b] & 0xFFFFF;
                    long diffA = maskA & ~maskB;
                    long diffB = maskB & ~maskA;
                    if (diffA != 0 && diffB != 0) {
                        printInside(diffA, diffB, offset, size);
                        return true;
                    }
                }
            }
            i = j;
        }
        return false;
    }


    private static void printInside(long mask1, long mask2, int offset, int size) {
        StringBuilder setA = new StringBuilder();
        StringBuilder setB = new StringBuilder();
        int countA = 0, countB = 0;

        for (int i = 0; i < size; i++) {
            if ((mask1 & (1 << i)) != 0) {
                if (countA > 0) setA.append(" ");
                setA.append(offset + i + 1);
                countA++;
            }
        }
        for (int i = 0; i < size; i++) {
            if ((mask2 & (1 << i)) != 0) {
                if (countB > 0) setB.append(" ");
                setB.append(offset + i + 1);
                countB++;
            }
        }

        System.out.println(countA);
        System.out.println(setA);
        System.out.println(countB);
        System.out.println(setB);
    }


    private static void printResult(long leftMask, long rightMask, int mid) {
        StringBuilder setA = new StringBuilder();
        StringBuilder setB = new StringBuilder();
        int countA = 0, countB = 0;

        for (int i = 0; i < mid; i++) {
            if ((leftMask & (1 << i)) != 0) {
                if (countA > 0) setA.append(" ");
                setA.append(i + 1);
                countA++;
            }
        }
        for (int i = 0; i < (40 - mid); i++) {
            if ((rightMask & (1 << i)) != 0) {
                if (countB > 0) setB.append(" ");
                setB.append(mid + i + 1);
                countB++;
            }
        }

        System.out.println(countA);
        System.out.println(setA);
        System.out.println(countB);
        System.out.println(setB);
    }
}