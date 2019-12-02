import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Window extends JFrame {


    ArrayList<Point> points;
    Animator animator;

    public void PolynomСomputation(){
        animator.resetImg();
        animator.drawAxis();

        for (int i=0; i < points.size(); i++) {
            Point p = points.get(i);
            int x = animator.XTransCoor(p.x);
            int y = animator.YTransCoor(p.y);
            animator.setXandY(x, y);
        }

        Point[] p = points.toArray(new Point[0]);
        Polynom lagrange = LagrangeInterpolation.Interpolate(p);
        System.out.println(lagrange);
        double x2=0;
        double y2=0;
        double step=0.01;
        for (double x=-100; x < 100; x+=step)
        {
            double y =0;
            for(int j=0;j<lagrange.coef.length;j++){
                y+=Math.pow(x,j)*lagrange.coef[j];
            }
            double x3=step+x;
            double y3=0;
            for(int k=0;k<lagrange.coef.length;k++){
                y3+=Math.pow(x3,k)*lagrange.coef[k];
            }
            double x4=animator.XTransCoor(x3);
            double y4=animator.YTransCoor(y3);
            x2=animator.XTransCoor(x);
            y2=animator.YTransCoor(y);

            animator.drawLine(x2,y2,x4,y4);
        }
        animator.drawToScreen();

    }


    public Window(){
        points = new ArrayList<>();

//        this.setSize(600, 400);

        //Этому методу передаются четыре параметра,
        // определяющих новое расположение и размеры компонента:
        this.setBounds(350,
                150, 600, 480);

        //Метод используется ,
        // чтобы указать один из нескольких вариантов для кнопки закрытия.
        this.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);


        //Если параметр имеет значение false,
        // пользователь не может изменить размер кадра.
        this.setResizable(true);
        this.setLayout(null);

        //заставляет появиться на экране
        this.setVisible(true);



        animator = new Animator(this.getGraphics());



        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
// щелкнули левой клавишой мыши
                    Point point = new Point(animator.coorTransX(e.getX()),
                            animator.coorTransY(e.getY()));
                    points.add(point);
                    animator.setXandY(e.getX(), e.getY());
                }
                else {
                    PolynomСomputation();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                System.out.println(e.getWheelRotation());
                animator.changeCellSize(e.getWheelRotation(),
                        e.getX(), e.getY());

                for (int i=0; i < points.size(); i++) {
                    Point p = points.get(i);
                    int x = animator.XTransCoor(p.x);
                    int y = animator.YTransCoor(p.y);
                    animator.setXandY(x, y);
                }

            }
        });

        this.setVisible(true);

        //чтобы сразу при загрузке оси прорисовывались
        animator.drawAxis();
        animator.drawToScreen();

    }
}
