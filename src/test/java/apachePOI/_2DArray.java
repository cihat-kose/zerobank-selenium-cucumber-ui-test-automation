package apachePOI;

public class _2DArray {

    public static void main(String[] args) {

        String[][] _array2D = {
                {"0_0", "0_1", "0_2", "0_3"},
                {"1_0", "1_1"},
                {"2_0", "2_1", "2_2"},
                {"3_0", "3_1"},
                {"4_0", "4_1", "4_2", "4_3"},
        };

        for (int i = 0; i < _array2D.length; i++) {
            for (int j = 0; j < _array2D[i].length; j++) {
                System.out.print(_array2D[i][j] + " ");
            }
            System.out.println();
        }

    }
}
