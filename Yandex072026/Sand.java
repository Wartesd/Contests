package Yandex072026;

import java.io.*;

public class Sand {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int terrariumRows = Integer.parseInt(br.readLine().trim());
        int terrariumCols = Integer.parseInt(br.readLine().trim());

        byte[] gridData = new byte[terrariumRows * terrariumCols];
        int[] grainCounts = new int[terrariumCols];
        int[] highestSandPos = new int[terrariumCols];
        int[] nextSandRowIndex = new int[terrariumCols];

        for (int r = 0; r < terrariumRows; r++) {
            String line = br.readLine();
            int col = 0;
            for (int idx = 0; idx < line.length() && col < terrariumCols; idx++) {
                char ch = line.charAt(idx);
                if (ch == '0' || ch == 'x' || ch == 'X') {
                    if (ch == '0') {
                        gridData[r * terrariumCols + col] = 1;
                        grainCounts[col]++;
                        if (grainCounts[col] == 1) {
                            highestSandPos[col] = terrariumRows - r;
                            nextSandRowIndex[col] = r;
                        }
                    }
                    col++;
                }
            }
        }

        for (int col = 0; col < terrariumCols; col++) {
            while (true) {
                int rightHeight = (col + 1 < terrariumCols) ? highestSandPos[col + 1] : 0;
                int leftHeight = (col - 1 >= 0) ? grainCounts[col - 1] : 0;

                if (rightHeight >= grainCounts[col] + 2) {
                    grainCounts[col]++;
                    grainCounts[col + 1]--;
                    if (grainCounts[col + 1] == 0) {
                        highestSandPos[col + 1] = 0;
                    } else {
                        int searchRow = nextSandRowIndex[col + 1] + 1;
                        while (searchRow < terrariumRows && gridData[searchRow * terrariumCols + col + 1] == 0) {
                            searchRow++;
                        }
                        nextSandRowIndex[col + 1] = searchRow;
                        highestSandPos[col + 1] = terrariumRows - searchRow;
                    }
                } else if (leftHeight >= grainCounts[col] + 2) {
                    grainCounts[col]++;
                    grainCounts[col - 1]--;
                } else {
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder(2 * terrariumCols);
        for (int r = 0; r < terrariumRows; r++) {
            sb.setLength(0);
            int threshold = terrariumRows - r;
            for (int c = 0; c < terrariumCols; c++) {
                if (grainCounts[c] >= threshold) {
                    sb.append('0');
                } else {
                    sb.append('-');
                }
                if (c < terrariumCols - 1) {
                    sb.append(' ');
                }
            }
            out.println(sb);
        }
        out.flush();
    }
}
