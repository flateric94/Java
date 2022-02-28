package tp11;

import java.util.function.DoubleUnaryOperator;

// TP de Eric CORTIAL

@TablePrint(header = "Fahrenheit Celsius", format = " %6.2f %6.2f\n", values = { -40, 0, 100, 212 })
// on lui donne l'annotation de Tableprint en modifiant les valeurs par défauts des champs
// que l'on veut changer les autres peuvent rester les memes


public class FahrenheitToCelsius implements DoubleUnaryOperator {

    @Override
    public double applyAsDouble(double f) {
        return (f-32.)*5./9.;
    }
}
