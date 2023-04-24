import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private Instock instock;

    @Before
    public void setUp() {
        instock = new Instock();
    }

    @Test
    public void testAddShouldAddProductToStock() {
        int sizeBefore = instock.getCount();
        Product productToAdd = addSingleProduct();
        int sizeAfter = instock.getCount();

        assertEquals(sizeAfter, sizeBefore + 1);
        assertTrue(instock.contains(productToAdd));
    }

    @Test
    public void testAddShouldNotAddExistingProduct() {
        List<Product> products = addMultipleProducts();
        int sizeBefore = instock.getCount();
        instock.add(products.get(2));
        int sizeAfter = instock.getCount();

        assertTrue(instock.getCount() > 0);
        assertEquals(sizeAfter, sizeBefore);
    }

    @Test
    public void testContainShouldReturnTrue() {
        List<Product> products = addMultipleProducts();
        assertTrue(instock.contains(products.get(1)));
    }

    @Test
    public void testFindShouldReturnProductAtNthIndex() {
        List<Product> products = addMultipleProducts();
        int indexToFind = 2;
        Product productToFind = instock.find(indexToFind);
        assertEquals(products.get(indexToFind), productToFind);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowWhenIndexInvalid() {
        instock.find(instock.getCount());
    }

    @Test
    public void testChangeQuantityShouldChangeQuantity() {
        List<Product> products = addMultipleProducts();
        int indexToChange = 3;
        int newQuantity = 50;

        Product productToChange = products.get(indexToChange);
        instock.changeQuantity(products.get(indexToChange).getLabel(), 50);

        assertEquals(newQuantity, productToChange.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityThrowsWhenProductIsMissing() {
        addMultipleProducts();
        instock.changeQuantity("Invalid Product", 30);
    }

    @Test
    public void testFindByLabelShouldReturnTheProduct() {
        List<Product> products = addMultipleProducts();
        Product expectedProduct = products.get(2);
        Product actualProduct = instock.findByLabel(expectedProduct.getLabel());
        assertEquals(expectedProduct, actualProduct);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelThrowsWhenProductIsMissing() {
        instock.findByLabel("Invalid Product");
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnNthProductsSortedAlphabetical() {
        List<Product> sortedProducts = addMultipleProducts().stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .toList();

        int countOfProducts = 4;
        List<Product> resultProducts = iterableToList(instock.findFirstByAlphabeticalOrder(countOfProducts));
        assertEquals(4, resultProducts.size());

        for (int i = 0; i < resultProducts.size(); i++) {
            assertEquals(sortedProducts.get(i), resultProducts.get(i));
        }
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionWhenNegativeRange() {
        addMultipleProducts();
        int countOfProducts = -1;
        List<Product> resultProducts = iterableToList(instock.findFirstByAlphabeticalOrder(countOfProducts));

        assertEquals(0, resultProducts.size());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionWhenBigRange() {
        addMultipleProducts();
        int countOfProducts = instock.getCount() + 1;
        List<Product> resultProducts = iterableToList(instock.findFirstByAlphabeticalOrder(countOfProducts));

        assertEquals(0, resultProducts.size());
    }

    @Test
    public void testFindAllInPriceRangeShouldReturnProductsWithinPriceRange() {
        double priceLimitLow = 2.00;
        double priceLimitHigh = 6.00;
        List<Product> sortedProducts = addMultipleProducts().stream()
                .filter(product -> product.getPrice() > priceLimitLow
                        && product.getPrice() <= priceLimitHigh)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());

        List<Product> resultProducts = iterableToList(instock.findAllInRange(priceLimitLow, priceLimitHigh));

        assertEquals(sortedProducts.size(), resultProducts.size());

        areListsEqualByPrice(sortedProducts, resultProducts);
    }

    @Test
    public void testFindAllInPriceRangeShouldReturnEmptyCollectionWhenNoSuchProduct() {
        addMultipleProducts();
        double priceLimitLow = 50;
        double priceLimitHigh = 100;

        List<Product> resultProducts = iterableToList(instock.findAllInRange(priceLimitLow, priceLimitHigh));

        assertEquals(0, resultProducts.size());
    }

    @Test
    public void testFindAllByPriceShouldReturnTheProductsWithTheGivenPrice() {
        double givenPrice = 6.00;
        List<Product> sortedProducts = addMultipleProducts().stream()
                .filter(product -> product.getPrice() == givenPrice)
                .collect(Collectors.toList());

        List<Product> resultProducts = iterableToList(instock.findAllByPrice(givenPrice));

        assertEquals(sortedProducts.size(), resultProducts.size());
        assertTrue(areListsEqualByPrice(sortedProducts, resultProducts));
    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollection() {
        addMultipleProducts();
        double givenPrice = 1000.00;
        List<Product> resultProducts = iterableToList(instock.findAllByPrice(givenPrice));

        assertEquals(0, resultProducts.size());
    }


    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnNthMostExpensiveProducts() {
        int countOfProducts = 3;
        List<Product> sortedProducts = addMultipleProducts().stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(countOfProducts)
                .collect(Collectors.toList());

        List<Product> resultProducts = iterableToList(instock.findFirstMostExpensiveProducts(countOfProducts));

        assertEquals(sortedProducts.size(), resultProducts.size());

        areListsEqualByPrice(sortedProducts, resultProducts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsThrowsWhenInvalidNumber() {
        int countOfProducts = -1;
        addMultipleProducts();

        iterableToList(instock.findFirstMostExpensiveProducts(countOfProducts));
    }

    @Test
    public void testFindAllByQuantityShouldReturnTheProductsWithGivenQuantity() {
        int givenQuantity = 10;
        List<Product> filteredProducts = addMultipleProducts().stream()
                .filter(product -> product.getQuantity() == givenQuantity)
                .collect(Collectors.toList());

        List<Product> resultProducts = iterableToList(instock.findAllByQuantity(givenQuantity));

        assertEquals(filteredProducts.size(), resultProducts.size());
        areListsEqualByQuantity(filteredProducts, resultProducts);
    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollection() {
        int givenQuantity = 100;
        addMultipleProducts();

        List<Product> resultProducts = iterableToList(instock.findAllByQuantity(givenQuantity));
        assertEquals(0, resultProducts.size());
    }

    @Test
    public void testGetIterableShouldReturnAllProducts() {
        List<Product> productList = addMultipleProducts();
        List<Product> resultProducts = iteratorToList(instock.iterator());

        assertEquals(productList.size(), resultProducts.size());

        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), resultProducts.get(i));
        }
    }


    private boolean areListsEqualByPrice(List<Product> sortedProducts, List<Product> resultProducts) {
        boolean areEquals = true;
        for (int i = 0; i < resultProducts.size(); i++) {
            if (sortedProducts.get(i).getPrice() != resultProducts.get(i).getPrice()) {
                areEquals = false;
                break;
            }
        }
        return areEquals;
    }

    private boolean areListsEqualByQuantity(List<Product> sortedProducts, List<Product> resultProducts) {
        boolean areEquals = true;
        for (int i = 0; i < resultProducts.size(); i++) {
            if (sortedProducts.get(i).getQuantity() != resultProducts.get(i).getQuantity()) {
                areEquals = false;
                break;
            }
        }
        return areEquals;
    }


    private List<Product> iterableToList(Iterable<Product> products) {
        List<Product> productList = new ArrayList<>();

        products.forEach(productList::add);
        return productList;
    }

    private List<Product> iteratorToList(Iterator<Product> products) {
        List<Product> productList = new ArrayList<>();
        products.forEachRemaining(productList::add);
        return productList;
    }


    private List<Product> addMultipleProducts() {
        List<Product> products = List.of(
                new Product("label_1", 2.00, 10),
                new Product("label_2", 1.00, 2),
                new Product("label_7", 0.20, 10),
                new Product("label_5", 6.00, 6),
                new Product("label_3", 3.50, 3),
                new Product("label_6", 9.80, 6),
                new Product("label_4", 6.00, 10));

        products.forEach(instock::add);
        return products;
    }

    private Product addSingleProduct() {
        Product product = new Product("label_8", 5.00, 8);
        instock.add(product);
        return product;
    }

}