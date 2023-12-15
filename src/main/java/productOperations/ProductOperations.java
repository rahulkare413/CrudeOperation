package productOperations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class ProductOperations {
    private  static Scanner sc = new Scanner(System.in);
    static SessionFactory sessionFactory;
    static {
        Configuration cfg = new Configuration();
        cfg.configure().addAnnotatedClass(Product.class);
        sessionFactory = cfg.buildSessionFactory();
    }
    private  static  void operation(){
        boolean status = true;
        while (status){
            System.out.println("1.ADD PRODUCT ");
            System.out.println("2.UPDATE PRODUCT ");
            System.out.println("3.DISPLAY PRODUCT ");
            System.out.println("4.DELETE PRODUCT ");
            System.out.println("5.EXIT ");
            System.out.println("ENTER YOUR CHOICE ");
            int ch = sc.nextInt();

            switch (ch){
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    displayProduct();

                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    status=false;
                    break;
                default:
                    System.out.println("INVALID CHOICE ");
            }
        }
    }

    private static void addProduct(){
        System.out.println("ENTER PRODUCT ID");
        int id = sc.nextInt();
        System.out.println("ENTER PRODUCT NAME ");
        String name = sc.next();
        System.out.println("ENTER PRODUCT QTY");
        int qty = sc.nextInt();
        System.out.println("ENTER PRODUCT CATEGORY ");
        String cat = sc.next();
        System.out.println("ENTER PRODUCT PRICE");
        double price = sc.nextDouble();

        Product p = new Product();
        p.setProductId(id);
        p.setProductName(name);
        p.setProductQuantity(qty);
        p.setProductCategory(cat);
        p.setProductPrice(price);

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();

    }
        private static void updateProduct(){
            System.out.println("ENTER PRODUCT NAME ");
             String name = sc.next();
            System.out.println("ENTER PRODUCT PRICE ");
            double price = sc.nextDouble();

            Session session = sessionFactory.openSession();
            Product p1 = session.get(Product.class,name);

            p1.setProductPrice(price);
            Transaction tx = session.beginTransaction();
            session.update(p1);
            tx.commit();
            session.close();
        }
        private static void deleteProduct(){
            System.out.println("ENTER PRODUCT NAME ");
            String name = sc.next();

            Session session = sessionFactory.openSession();
            Product p2 = session.get(Product.class,name);

            p2.setProductName(name);
            Transaction tx = session.beginTransaction();
            session.delete(p2);
            tx.commit();
            session.close();
        }

        private static void displayProduct(){
            System.out.println("ENTER PRODUCT NAME ");
            String name = sc.next();

            Session session = sessionFactory.openSession();
            Product p2 = session.get(Product.class,name);

            System.out.println("PRODUCT ID "+p2.getProductId());
            System.out.println("PRODUCT NAME "+p2.getProductName());
            System.out.println("PRODUCT CATEGORY "+p2.getProductCategory());
            System.out.println("PRODUCT QUANTITY "+p2.getProductQuantity());
            System.out.println("PRODUCT PRICE "+p2.getProductPrice());

            p2.setProductName(name);
            Transaction tx = session.beginTransaction();

            tx.commit();
            session.close();

        }



    public static void main(String[] args) {
        operation();

    }
}
