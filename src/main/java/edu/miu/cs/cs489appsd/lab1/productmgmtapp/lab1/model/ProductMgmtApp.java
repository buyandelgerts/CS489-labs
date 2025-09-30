package edu.miu.cs.cs489appsd.lab1.productmgmtapp.lab1.model;

import java.util.*;

public class ProductMgmtApp {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("31288741190182539912", "Banana", new Date(2025, 1, 24), 124, 0.55);
        Product product2 = new Product("29274582650152771644", "Apple", new Date(2024, 12,9), 18, 1.09);
        Product product3 = new Product("91899274600128155167", "Carrot", new Date(2025, 3, 31), 89, 2.99);
        Product product4 = new Product("31288741190182539913", "Banana", new Date(2025, 2, 13), 240, 0.65);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        printProducts(products);
    }

    public static void printProducts(List<Product> products) {
        products.sort((p1, p2) -> Double.compare(p2.unitPrice, p1.unitPrice));

        // ---------- JSON ----------
        System.out.println("Printed in JSON Format");
        System.out.println("[");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println("  {");
            System.out.println("    \"productId\": " + p.productId + ", \"name\": \"" + p.name + "\", "
                    + "\"dateSupplied\": \"" + p.dateSupplied + "\", \"quantityInStock\": " + p.quantityInStock + ",");
            System.out.println("    \"unitPrice\": " + p.unitPrice + " }"
                    + (i < products.size() - 1 ? "," : ""));
        }
        System.out.println("]");
        System.out.println("--------------------------");

        // ---------- XML ----------
        System.out.println("Printed in XML Format");
        System.out.println("<?xml version=\"1.0\"?>");
        System.out.println("<products>");
        for (Product p : products) {
            System.out.printf("  <product productId=\"%s\" name=\"%s\" dateSupplied=\"%s\" quantityInStock=\"%d\" unitPrice=\"%.2f\" />%n",
                    p.productId, p.name, p.dateSupplied, p.quantityInStock, p.unitPrice);
        }
        System.out.println("</products>");
        System.out.println("--------------------------");
    }
}