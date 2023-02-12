package Tests;

import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Test3
{
    public Figure[][] figures;
    public Color[][] figureColors;

    public Figure[][] getFigures() {
        return figures;
    }

    public Color[][] getFigureColors() {
        return figureColors;
    }

    public Test3(boolean hardCoreMode, int m)
    {
        figures = new Figure[m][3];
        figureColors = new Color[m][3];

        Random random = new Random();

        boolean hardcoreExampleRow = false;
        boolean notHardcoreExampleRow = false;

        for (int i = 0; i < m; i++)
        {
            ArrayList<Figure> figuresLeft = new ArrayList<>();

            figuresLeft.add(new Circle());
            figuresLeft.add(new Rectangle());
            figuresLeft.add(new Triangle());

            // ten wiersz bedzie mial 3 takie same figury kazda o roznym kolorze r, g lub b
            if ((hardCoreMode && random.nextFloat() >= 0.5f && !(i == m -1 && !hardcoreExampleRow)) || (i == m - 1 && !notHardcoreExampleRow))
            {
                hardcoreExampleRow = true;

                Figure figureChosen = figuresLeft.get(random.nextInt(3));

                figuresLeft = new ArrayList<>();

                figuresLeft.add(figureChosen);
                figuresLeft.add(figureChosen);
                figuresLeft.add(figureChosen);
            }
            else{
                notHardcoreExampleRow = true;
            }

            ArrayList<Color> colorsLeft = new ArrayList<>(Arrays.asList(Color.GREEN, Color.RED, Color.BLUE));

            for (int j = 0; j < 3; j++)
            {
                int indexOfChosenFigure = random.nextInt(figuresLeft.size());
                Figure figure = figuresLeft.remove(indexOfChosenFigure);

                int indexOfChosenColor = random.nextInt(colorsLeft.size());
                Color color = colorsLeft.remove(indexOfChosenColor);

                figures[i][j] = figure;
                figureColors[i][j] = color;
            }
        }
    }
}
