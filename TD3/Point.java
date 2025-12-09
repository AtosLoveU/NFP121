
public class Point {

    private double x, y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void afficher(){
        System.out.println(" Les coordonnées sont : " + this.x + " : " + this.y);
    }

    public void translater(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    public double getModule(){
        return (double) Math.sqrt(x*x+y*y);
    }
}
