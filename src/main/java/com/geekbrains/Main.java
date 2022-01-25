package com.geekbrains;

import com.geekbrains.market.Market;
import com.geekbrains.person.customer.Customer;
import com.geekbrains.person.seller.Seller;
import com.geekbrains.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //1) Seller, может добавлять, убирать продукты, выставлять стоимость
    //2) Customer может покупать товар, может что-то желать
    //3) Когда seller продает продукты, у него они отнимаются, но добавляются у customer
    // кэш отнимается у customer, добавляется к seller
    //4) Поиск товара про продавцу и продукту, либо по продукту

    public static void main(String[] args) {
        Market market = new Market();

        Seller firstSeller = createFirstSeller();
        Seller secondSeller = createSecondSeller();
        Seller thirdSeller = createThirdSeller();

        market.addSeller(firstSeller);
        market.addSeller(secondSeller);
        market.addSeller(thirdSeller);

        Customer customer = createFirstCustomer();
        //customer.findProductOnMarket(market);
        System.out.println("------------------Покупатель-------------------");
        customer.info();
        System.out.println("------------------До покупки------------------");

        market.info();
        customer.findProductOnMarket(market,"Василий", "Пупкин");
        //customer.findProductOnMarket(market);
        System.out.println("------------------После покупки----------------");
        market.info();
        System.out.println("------------------Покупатель-------------------");
        customer.info();
    }

    private static Customer createFirstCustomer() {
        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setQuantity(3);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setQuantity(2);

        Product thirdProduct = new Product();
        thirdProduct.setName(MarketConstants.APPLE_PRODUCT_NAME);
        thirdProduct.setQuantity(5);

        Product forthProduct = new Product();
        forthProduct.setName(MarketConstants.CARROT_PRODUCT_NAME);
        forthProduct.setQuantity(11);

        return new Customer(List.of(firstProduct, secondProduct, thirdProduct, forthProduct), 500);
    }

    private static Seller createFirstSeller() {
        Seller seller = new Seller();
        seller.setName("Виталий");
        seller.setLastName("Еремин");
        seller.setCash(0);

        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setPrice(10);
        firstProduct.setQuantity(2);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setPrice(8);
        secondProduct.setQuantity(100);

        Product thirdProduct = new Product();
        thirdProduct.setName(MarketConstants.CARROT_PRODUCT_NAME);
        thirdProduct.setPrice(1);
        thirdProduct.setQuantity(4);

        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);
        products.add(thirdProduct);
        seller.setProducts(products);

        return seller;
    }

    private static Seller createSecondSeller() {
        Seller seller = new Seller();
        seller.setName("Алексей");
        seller.setLastName("Ушаков");
        seller.setCash(0);

        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setPrice(8);
        firstProduct.setQuantity(40);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setPrice(5);
        secondProduct.setQuantity(12);

        Product thirdProduct = new Product();
        thirdProduct.setName(MarketConstants.APPLE_PRODUCT_NAME);
        thirdProduct.setPrice(22);
        thirdProduct.setQuantity(3000);

        Product forthProduct = new Product();
        forthProduct.setName(MarketConstants.CARROT_PRODUCT_NAME);
        forthProduct.setPrice(2);
        forthProduct.setQuantity(5);

        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);
        products.add(thirdProduct);
        products.add(forthProduct);
        seller.setProducts(products);

        return seller;
    }

    private static Seller createThirdSeller() {
        Seller seller = new Seller();
        seller.setName("Василий");
        seller.setLastName("Пупкин");
        seller.setCash(0);

        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setPrice(15);
        firstProduct.setQuantity(100);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setPrice(7);
        secondProduct.setQuantity(120);

        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);
        seller.setProducts(products);

        return seller;
    }

}
