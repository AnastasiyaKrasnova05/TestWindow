public class Polynom {
    public double[] coef;

    public Polynom(double[] coef) {
        this.coef = coef;
    }

    public int getlength() {
        return coef.length;
    }


    public static Polynom sum(Polynom a, Polynom b) {
        int clength = Math.max(a.getlength(), b.getlength());
        double rez[] = new double[clength];
        for (int i = 0; i < a.getlength(); i++) {
            rez[i] += a.coef[i];
        }
        for (int i = 0; i < b.getlength(); i++) {
            rez[i] += b.coef[i];
        }
        return new Polynom(rez);
    }

    public Polynom mult(Polynom b) {
        int poly_size = this.getlength() + b.getlength() - 1;
        double[] result = new double[poly_size];
        for (int i = 0; i < this.getlength(); i++) {
            for (int j = 0; j < b.getlength(); j++) {
                result[i + j] += this.coef[i] * b.coef[j];
            }
        }
        return new Polynom(result);
    }

    public double valueOnPoint(double point) {
        double result = 0;
        double power = 1;
        for (int i = 0; i < getlength(); i++) {
            result += coef[i] * power;
            power = power * point;
        }
        return result;
    }

    public Polynom mult( double num) {
        double[] tempArray = new double[1];
        tempArray[0] = num;
        Polynom polynom1 = new Polynom(tempArray);
        return this.mult(polynom1);
    }

    static private String get_x_symbol(int power)
    {
        if (power == 0)
            return "";
        if (power == 1)
            return "x";
        return "x^" + power;
    }

    private String GetSignSymbol(int power)
    {
        if (power == coef.length - 1)
            return coef[power] > 0? "" : "-";
        String sign =  coef[power] > 0? "+" : "-";
        return " " + sign  + " ";
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = coef.length - 1; i >= 0; i--) {

            String x_symbol = get_x_symbol(i);

            if (i == coef.length - 1)
            {
                result += coef[i] + x_symbol;
            }
            else if (coef[i] != 0) {
                String sign = GetSignSymbol(i);
                result += sign + Math.abs(coef[i]) + x_symbol;
            }
        }
        return result;
    }
}