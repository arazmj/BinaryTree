public class Main {

    static int[] lst = {7, 10, 5, 8, 4};
    static int[][] a = new int[lst.length][lst.length];
    static int[][] e = new int[lst.length][lst.length];



    public static int c(int l, int r) {
        if (r < l)
            return 0;
        if (l == r)
            return lst[l];

        int min = Integer.MAX_VALUE;
        int minI = Integer.MAX_VALUE;
        int f = 0;
        for (int i = l; i <= r; i++) {
            f += lst[i];
            int cost = c(l, i - 1) + c(i+1, r);
            if (min > cost) {
                min = cost;
                minI = i;
            }
        }
        return min + f;
    }

    public static int A(int i, int j) {
        if (a.length > i && a[0].length > j
                && i >= 0 && j >= 0)
            return a[i][j];
        return 0;
    }

    // a: cost matrix
    // lst: input list i.e {7, 10, 5, 8, 4}
    public static int cdp() {
        for (int size = 0; size < lst.length; size++) {
            for (int l = 0; l < lst.length - size; l++) {
                if (size == 0)
                    a[l][l + size] = lst[l];
                else {
                    int min = Integer.MAX_VALUE;
                    int sel = Integer.MAX_VALUE;
                    int f = 0;
                    for (int i = l; i <= (l + size); i++) {
                        f += lst[i];
                        int cost = A(l, i - 1) + A(i + 1, l + size);
                        if (cost < min) {
                            min = cost;
                            sel = i;
                        }
                    }
                    a[l][l + size] = f + min;
                    e[l][l + size] = sel;
                }
            }
        }
        return a[0][a.length - 1];
    }

    public static void main(String[] args) {
        cdp();
        System.out.println();
        dumpMatrix();
    }

    private static void dumpMatrix() {
        for (int i = 0; i < lst.length; i++) {
            for (int j = 0; j < lst.length; j++) {
                String s = String.format("%d", a[i][j]);
                if (e[i][j] != 0)
                    s += "(" + e[i][j] + ")";
                System.out.print(String.format("%7s", s));

            }
            System.out.println();
        }
    }
}
