package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Random;

public class StylingMethods {
    public static void textStyle(Text text, int font) {
        text.setFont(Font.font("Arial", FontWeight.BOLD, font));
        text.setFill(Color.BLACK);
        text.setStroke(Color.BLUEVIOLET);
        text.setStrokeWidth(0.5);
        text.setFontSmoothingType(FontSmoothingType.LCD);
    }

    public static Paint generateRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b, 0.3);
    }
}
