import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> products;

    public Instock() {
        products = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public boolean contains(Product product) {
        return products.contains(product);
    }

    @Override
    public void add(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        Product currentProduct = products.stream()
                .filter(pr -> pr.getLabel().equals(product))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The product is not in stock!"));

        currentProduct.setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        if (index < 0 || index >= products.size()) {
            throw new IndexOutOfBoundsException();
        }
        return products.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        return products.stream().filter(product -> product.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product is not in the stock!"));
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        List<Product> productList = new ArrayList<>();
        if (!(count < 0 || count >= products.size())) {
            productList = products.stream()
                    .sorted(Comparator.comparing(Product::getLabel))
                    .limit(count)
                    .collect(Collectors.toList());
        }
        return productList;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
    return products.stream()
               .filter(product -> product.getPrice() > lo && product.getPrice() <= hi)
               .sorted(Comparator.comparing(Product::getPrice).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return products.stream()
                .filter(product -> product.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count < 0 || count > products.size()) {
            throw new IllegalArgumentException("Number out of valid range");
        }
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return products.stream()
                .filter(product -> product.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
       return products.iterator();
    }
}
