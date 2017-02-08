import java.util.*;

public class brechka_COMP228Lab6 {
    static final int N = 100000;
    static final String[] type = {"ArrayList", "LinkedList", "HasMap", "TreeMap"};

    public static void main(String[] args) {
        List<Integer>[] lists = new List[]{new ArrayList<Integer>(N), new LinkedList<Integer>()};
        long[][] listTimes = new long[2][6];
        Map<Number, Number>[] maps = new Map[]{new HashMap<Number, Number>(), new TreeMap<Number, Number>()};
        long[][] mapTimes = new long[2][6];
        long start, end;

        for (int i = 0; i < 2; i++) {
            int j = 0;
            for (int k = 0; k < 3; k++) {
                start = System.nanoTime();
                listInsert(k, lists[i]);
                end = System.nanoTime();
                listTimes[i][j++] = end - start;
                start = end;
                listDelete(k, lists[i]);
                end = System.nanoTime();
                listTimes[i][j++] = end - start;
            }
        }

        for (int i = 0; i < 2; i++) {
            start = System.nanoTime();
            for (int j = 1; j <= N; j++) {
                maps[i].put(j, j);
            }
            end = System.nanoTime();
            mapTimes[i][0] = end - start;
        }

        for (int i = 0; i < 2; i++) {
            start = System.nanoTime();
            mapDelete(0, maps[i]);
            end = System.nanoTime();
            mapTimes[i][3] = end - start;
        }

        for (int i = 0; i < 2; i++) {
            start = System.nanoTime();
            for (int j = N; j > 0; j--) {
                maps[i].put(j, j);
            }
            end = System.nanoTime();
            mapTimes[i][1] = end - start;
        }

        for (int i = 0; i < 2; i++) {
            start = System.nanoTime();
            mapDelete(1, maps[i]);
            end = System.nanoTime();
            mapTimes[i][4] = end - start;
        }

        for (int i = 0; i < 2; i++) {
            start = System.nanoTime();
            for (int j = 1; j <= (int)(N * 0.1); j++) {
               maps[i].put(j, j);
            }
            for (int j = (int)(N * 0.9 + 1); j <= N; j++) {
                maps[i].put(j, j);
            }
            for (int j = (int)(N * 0.1 + 1); j <= (N * 0.9); j++) {
                maps[i].put(j, j);
            }
            end = System.nanoTime();
            mapTimes[i][2] = end - start;
        }

        for (int i = 0; i < 2; i++) {
            start = System.nanoTime();
            mapDelete(2, maps[i]);
            end = System.nanoTime();
            mapTimes[i][5] = end - start;
        }

        System.out.println("\t\t\tInStart\tInMid\tInFin\tDelSt\tDelMid\tDelFin");

        for (int j = 0; j < 2; j++) {
            System.out.print(type[j]);
            for (int i = 0; i < 6; i += 2) {
                System.out.printf("\t%.3f ", listTimes[j][i] / 1e9);
            }
            for (int i = 1; i < 6; i += 2) {
                System.out.printf("\t%.3f ", listTimes[j][i] / 1e9);
            }
            System.out.println();
        }

        for (int j = 0; j < 2; j++)  {
            System.out.print(type[j + 2] + "\t");
            for (int i = 0; i < 6; i++) {
                System.out.printf("\t%.3f ", mapTimes[j][i] / 1e9);
            }
            System.out.println();
        }

    }

    private static void listInsert(int position, List<Integer> listToInsert) {
        switch (position) {
            case 0:
                for (int l = 1; l <= N; l++) {
                    listToInsert.add(0, l);
                }
                break;
            case 1:
                for (int l = 1; l <= N; l++) {
                    listToInsert.add(listToInsert.size() / 2, l);
                }
                break;
            case 2:
                for (int l = 1; l <= N; l++) {
                    listToInsert.add(l);
                }
                break;
        }
    }

    private static void listDelete(int position, List<Integer> list) {
        switch (position) {
            case 0:
                for (int l = 1; l <= N ; l++) {
                    list.remove(0);
                }
                break;
            case 1:
                for (int l = 1; l <= N ; l++) {
                    list.remove(list.size() / 2);
                }
                break;
            case 2:
                for (int l = 1; l <= N ; l++) {
                    list.remove(list.size() - 1);
                }
                break;
        }
    }

    private static void mapDelete(int deleteCase, Map<Number, Number> mapToDeleteFrom) {
        switch (deleteCase) {
            case 0:
                for (int i = 1; i <= (int)(N * 0.8) ; i++) {
                    mapToDeleteFrom.remove(i);
                }
                break;
            case 1:
                for (int i = (int)(N * 0.1 + 1); i <= (int)(N * 0.9) ; i++) {
                    mapToDeleteFrom.remove(i);
                }
                break;
            case 2:
                for (int i = (int)(N * 0.2 + 1); i <= N ; i++) {
                    mapToDeleteFrom.remove(i);
                }
                break;
        }
    }
}
