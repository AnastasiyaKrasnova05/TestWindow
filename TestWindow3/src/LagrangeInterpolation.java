 public class LagrangeInterpolation {
        public static Polynom Interpolate(Point[] points) {
            Polynom[] l = new Polynom[points.length];
            for (int i = 0; i < l.length; i++) {
                l[i] = new Polynom(new double[]{1});
                for (int j = 0; j < l.length; j++) {
                    if (i != j) {
                        l[i] = l[i].mult(new Polynom(new double[]{-points[j].getX(), 1}));
                        l[i] = l[i].mult(1 / (points[i].getX() - points[j].getX()));
                    }
                }
            }
            Polynom result = new Polynom(new double[]{0});
            for (int i = 0; i < points.length ; i++) {
                result = Polynom.sum(result, l[i].mult(new Polynom(new double[]{points[i].getY()})));
            }
            return result;
        }
    }
