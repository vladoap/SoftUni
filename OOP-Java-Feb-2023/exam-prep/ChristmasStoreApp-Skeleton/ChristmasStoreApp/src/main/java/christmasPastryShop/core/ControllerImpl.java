package christmasPastryShop.core;

import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import static christmasPastryShop.common.ExceptionMessages.*;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double totalIncome;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
         boolean doesExist = delicacyRepository.getByName(name) != null;

         if (doesExist) {
             String message = String.format(FOOD_OR_DRINK_EXIST, type, name);
             throw new IllegalArgumentException(message);
         }

         Delicacy delicacy = null;
         switch (type) {
             case "Gingerbread":
                 delicacy = new Gingerbread(name, price);
                 break;
             case "Stolen":
                 delicacy = new Stolen(name, price);
                 break;
         }

         if (delicacy != null) {
             delicacyRepository.add(delicacy);
             return String.format(DELICACY_ADDED, name, type);
         }
         return null;
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        boolean doesExist = cocktailRepository.getByName(name) != null;

        if (doesExist) {
            String message = String.format(FOOD_OR_DRINK_EXIST, type, name);
            throw new IllegalArgumentException(message);
        }

        Cocktail cocktail = null;
        switch (type) {
            case "Hibernation":
                cocktail = new Hibernation(name, size, brand);
                break;
            case "MulledWine":
                cocktail = new MulledWine(name, size, brand);
                break;
        }

        if (cocktail != null) {
            cocktailRepository.add(cocktail);
            return String.format(COCKTAIL_ADDED, name, brand);
        }
        return null;
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        boolean doesExist = boothRepository.getByNumber(boothNumber) != null;

        if (doesExist) {
            String message = String.format(BOOTH_EXIST, boothNumber);
            throw new IllegalArgumentException(message);
        }

        Booth booth = null;
        switch (type) {
            case "OpenBooth":
                booth = new OpenBooth(boothNumber, capacity);
                break;
            case "PrivateBooth":
                booth = new PrivateBooth(boothNumber, capacity);
                break;
        }

        if (booth != null) {
             boothRepository.add(booth);
            return String.format(BOOTH_ADDED, boothNumber);
        }
        return null;
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        Booth availableBooth = boothRepository.getAll().stream()
                .filter(b -> b.getCapacity() >= numberOfPeople && !b.isReserved())
                .findFirst()
                .orElse(null);

        if (availableBooth == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        } else {
            availableBooth.reserve(numberOfPeople);
            return String.format(BOOTH_RESERVED, availableBooth.getBoothNumber(), numberOfPeople);
        }
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth boothToLeave = boothRepository.getAll().stream()
                .filter(b -> b.getBoothNumber() == boothNumber)
                .findFirst()
                .orElse(null);

        if (boothToLeave != null) {
            double billPrice = boothToLeave.getBill();
            boothToLeave.clear();
            totalIncome += billPrice;
            return String.format(BILL, boothNumber, billPrice);
        } else {
            return null;
        }
    }

    @Override
    public String getIncome() {
      return String.format(TOTAL_INCOME, totalIncome);
    }
}
