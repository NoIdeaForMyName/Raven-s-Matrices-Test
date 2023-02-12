package Tests;

import Figures.Figure;
import Figures.RotatableTriangle;

import java.util.Arrays;
import java.util.Random;

public class Test2 {

    private final int tri_amount_hori;
    private final int tri_amount_vert;
    private final int verses_amount = 3;
    private final int rows_amount = 3;

    private boolean[][][] rects = new boolean[verses_amount][rows_amount][4];
    private boolean[][] triangles_inside = new boolean[verses_amount][2];

    Figure figure_a;

    public Figure getFigure_a() {
        return figure_a;
    }

    public Test2() {

        Random random = new Random();

        figure_a = new RotatableTriangle();

        tri_amount_hori = random.nextInt(1,3);
        tri_amount_vert = random.nextInt(1,3);

        for (int i = 0; i < verses_amount; i++) {

            triangles_inside[i][0] = random.nextBoolean();
            triangles_inside[i][1] = random.nextBoolean();
            int[] temp_tri_amounts = new int[]{tri_amount_hori, tri_amount_hori, tri_amount_vert, tri_amount_vert};

            int temp_rect_nb;
            int temp_rect_side;

            while (Arrays.stream(temp_tri_amounts).sum() > 0) {

                temp_rect_nb = random.nextInt(0, rows_amount);
                temp_rect_side = random.nextInt(0, 4);

                if (temp_tri_amounts[temp_rect_side] == 0)
                    continue;

                if (!rects[i][temp_rect_nb][temp_rect_side]) {
                    rects[i][temp_rect_nb][temp_rect_side] = true;
                    temp_tri_amounts[temp_rect_side]--;
                }
            }
        }

        //System.out.println(Arrays.deepToString(rect));

    }

    public boolean[][][] getRect() {
        return rects;
    }

    public boolean[][] getTriangles_inside() {
        return triangles_inside;
    }
}

