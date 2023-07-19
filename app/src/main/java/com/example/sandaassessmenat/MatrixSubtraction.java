package com.example.sandaassessmenat;

public class MatrixSubtraction {
    public static void main(String[] args) {
        int[][] matrixA = {{4, 5, 6}, {3, 4, 1}, {1, 2, 3}};
        int[][] matrixB = {{2, 0, 3}, {2, 3, 1}, {1, 1, 1}};

        int rows = matrixA.length;
        int columns = matrixA[0].length;

        int[][] result = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }

        System.out.println("Resultant Matrix after subtraction:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
