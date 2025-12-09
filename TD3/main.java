public class main {
    
    public static void main(String[] args) {

        Point p1 = new Point(3, 4);
        
        System.out.println("=== Test Point 1 ===");
        p1.afficher();
        System.out.println("Module : " + p1.getModule());
        

        System.out.println("\n=== Après setX(5) ===");
        p1.setX(5);
        p1.afficher();
        System.out.println("Module : " + p1.getModule());
        

        System.out.println("\n=== Après translater(2, 3) ===");
        p1.translater(2, 3);
        p1.afficher();
        System.out.println("Module : " + p1.getModule());
        

        System.out.println("\n=== Test Point 2 ===");
        Point p2 = new Point(1, 1);
        p2.afficher();
        System.out.println("Module : " + p2.getModule());
    }
}
