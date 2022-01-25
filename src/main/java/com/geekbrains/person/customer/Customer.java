package com.geekbrains.person.customer;

import com.geekbrains.market.Market;
import com.geekbrains.person.Person;
import com.geekbrains.person.seller.Seller;
import com.geekbrains.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Product> expectedPurchaseList;
    private List<Product> purchaseList;

    public Customer(List<Product> expectedPurchaseList, int cash) {
        this.purchaseList = new ArrayList<>();
        this.expectedPurchaseList = expectedPurchaseList;
        this.setCash(cash);
    }

    public void addPurchase(Product product) {
        if (purchaseList == null) {
            purchaseList = new ArrayList<>();
        }

        boolean isNewProduct = true;
        for(Product existingPurchase : purchaseList){
            if (product.equals(existingPurchase)) {
                existingPurchase.setQuantity(product.getQuantity() + existingPurchase.getQuantity());
                isNewProduct = false;
            }
        }
        if (isNewProduct) purchaseList.add(product);

        //Refactor
        //Уменьшаем после покупки размер запроса
        for (Product expectedProduct : expectedPurchaseList) {
            if (product.equals(expectedProduct)) {
                expectedProduct.setQuantity(expectedProduct.getQuantity() - product.getQuantity());
            }
        }

    }

    public void findProductOnMarket(Market market) {
        for (Product product : getExpectedPurchaseList()) {
            for (Seller seller : market.getSellers()) {
                boolean isBought = seller.sellProducts(this, product);
                if (isBought) {
                    break;
                }
            }
        }
    }

    public void findProductOnMarket(Market market, String sellerName, String sellerLastName) {
        for (Product product : getExpectedPurchaseList()) {
            boolean isBought = false;
            for (Seller seller : market.getSellers()) {
                if (seller.getName().equals(sellerName) && seller.getLastName().equals(sellerLastName)) {
                    isBought = seller.sellProducts(this, product);
                    if (isBought) break;
                }
            }
            //если не нашли у нужного продавца, то ищем продукт на рынке
            if (!isBought) {
                for (Seller seller : market.getSellers()) {
                    isBought = seller.sellProducts(this, product);
                    if (isBought) break;
                }
            }
        }

    }

    public void info() {
        StringBuilder result = new StringBuilder("Я купил ");
        if (purchaseList.size() == 0) {
            result.append("ничего");
        } else {
            for (Product product : purchaseList) {
                result.append(product.getName());
                result.append(" в количестве ");
                result.append(product.getQuantity());
                result.append(" ");
            }
        }

        result.append(". У меня осталось: ");
        result.append(getCash());
        result.append(" рублей\n");
        result.append("Список покупок: ");
        if (expectedPurchaseList.size() > 0) {
            for (Product product : expectedPurchaseList) {
                result.append(product.getName());
                result.append(" в количестве ");
                result.append(product.getQuantity());
                result.append(" ");
            }
        }

        System.out.println(result);
    }

    public List<Product> getExpectedPurchaseList() {
        return expectedPurchaseList;
    }

    public List<Product> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Product> purchaseList) {
        this.purchaseList = purchaseList;
    }

}
