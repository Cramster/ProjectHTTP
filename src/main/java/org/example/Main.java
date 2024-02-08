package org.example;

import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {

        ProductController productController = new ProductController();
        Javalin api = productController.getAPI();
        api.start(9003);

    }
}