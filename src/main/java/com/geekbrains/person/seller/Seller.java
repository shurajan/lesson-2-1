package com.geekbrains.person.seller;

import com.geekbrains.person.Person;
import com.geekbrains.person.customer.Customer;
import com.geekbrains.product.Product;

import java.util.List;

public class Seller extends Person {
    private String name;
    private String lastName;
    private List<Product> products;

    public boolean sellProducts(Customer customer, Product expectedProduct) {
        for (Product product : products) {
            // Проверяем по имени товара, что у продавца есть продукт
            if (product.equals(expectedProduct)) {
                int sellableQuantity = Math.min(expectedProduct.getQuantity(), product.getQuantity());
                long requiredCash = (long) product.getPrice() * sellableQuantity;

                if (sellableQuantity > 0 && customer.getCash() >= requiredCash) {

                    // Уменьшаем количество продукта у продавца
                    product.setQuantity(product.getQuantity() - sellableQuantity);

                    //Создаем новый объект для покупателя, чтобы ссылка не дублировалась
                    Product customerProduct = new Product();
                    customerProduct.setQuantity(sellableQuantity);
                    customerProduct.setName(expectedProduct.getName());

                    //Добавляем количество продуктов у покупателя
                    customer.addPurchase(customerProduct);
                    //Увеличиваем кэш продавца
                    setCash(getCash() + requiredCash);
                    //Уменьшаем кэш покупателя
                    customer.setCash(customer.getCash() - requiredCash);
                    //Сообщаем потребителю метода, что покупка совершена
                    if (sellableQuantity == expectedProduct.getQuantity()) {
                        return true;
                    } else{
                        return false;
                    }
                }
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void info() {
        StringBuilder result = new StringBuilder("У продавца ");
        result.append(this.name);
        result.append(" ");
        result.append(this.lastName);
        result.append(": \n");

        if (products.size() == 0) {
            result.append("ничего");
        } else {
            for (Product product : products) {
                result.append(".....");
                result.append(product.getName());
                result.append(": ");
                result.append(product.getQuantity());
                result.append(" - ");
                result.append(product.getPrice());
                result.append(" руб");
                result.append("\n");
            }
        }

        result.append(".....Рублей: ");
        result.append(getCash());

        System.out.println(result);
    }
}
