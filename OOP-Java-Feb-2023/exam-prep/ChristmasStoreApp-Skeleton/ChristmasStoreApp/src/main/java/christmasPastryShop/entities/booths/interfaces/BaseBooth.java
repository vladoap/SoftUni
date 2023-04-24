package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

import static christmasPastryShop.common.ExceptionMessages.*;

public abstract class BaseBooth implements Booth {

    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        delicacyOrders = new ArrayList<>();
        cocktailOrders = new ArrayList<>();
        this.boothNumber = boothNumber;
        setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        isReserved = false;
    }

    private void setCapacity(int capacity) {
        if (capacity < 0) {
            // FIXME: "It can’t be less than zero"
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    // TODO: check the access modifier
    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }


    @Override
    public int getBoothNumber() {
        return boothNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void reserve(int numberOfPeople) {
           setNumberOfPeople(numberOfPeople);
           price = numberOfPeople * pricePerPerson;
           isReserved = true;
    }

    @Override
    public double getBill() {
       double totalPriceCocktails = cocktailOrders.stream()
               .mapToDouble(Cocktail::getPrice)
               .sum();

       double totalPriceDelicacies = delicacyOrders.stream()
               .mapToDouble(Delicacy::getPrice)
               .sum();

        return totalPriceCocktails + totalPriceDelicacies + getPrice();
    }

    @Override
    public void clear() {
        delicacyOrders.clear();
        cocktailOrders.clear();
        isReserved = false;
        numberOfPeople = 0;
        price = 0;
    }
}
