package zoo.core;

import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

import static zoo.common.ConstantMessages.*;
import static zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area;
        switch (areaType) {
            case "WaterArea":
                area = new WaterArea(areaName);
                break;
            case "LandArea":
                area = new LandArea(areaName);
                break;
            default:
                throw new NullPointerException(INVALID_AREA_TYPE);
        }
        areas.add(area);
        return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;
        switch (foodType) {
            case "Meat":
                food = new Meat();
                break;
            case "Vegetable":
                food = new Vegetable();
                break;
            default:
                throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }
        foodRepository.add(food);
        return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food food = foodRepository.findByType(foodType);

        if (food == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }

        Area area = areas.stream()
                .filter(a -> a.getName().equals(areaName))
                .findFirst()
                .orElse(null);

        if (area != null) {
            area.addFood(food);
            foodRepository.remove(food);
            return String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
        }
        return null;
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal;
        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, kind, price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }

        Area area = areas.stream()
                .filter(a -> a.getName().equals(areaName))
                .findFirst()
                .orElse(null);

        if (area != null) {
            if ((animal.getClass().getSimpleName().equals("AquaticAnimal") && area.getClass().getSimpleName().equals("LandArea"))
            || (animal.getClass().getSimpleName().equals("TerrestrialAnimal") && area.getClass().getSimpleName().equals("WaterArea"))) {
                return AREA_NOT_SUITABLE;
            } else {
                area.addAnimal(animal);
               return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
            }
        }
        return null;
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = areas.stream()
                .filter(a -> a.getName().equals(areaName))
                .findFirst()
                .orElse(null);

        if (area != null) {
            int countAnimals = area.getAnimals().size();
            area.getAnimals().forEach(Animal::eat);
            return String.format(ANIMALS_FED, countAnimals);
        }
        return null;
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = areas.stream()
                .filter(a -> a.getName().equals(areaName))
                .findFirst()
                .orElse(null);

        if (area != null) {
            double sumKg = area.getAnimals().stream()
                    .mapToDouble(Animal::getKg)
                    .sum();

            return String.format(KILOGRAMS_AREA, areaName, sumKg);
        }
        return null;
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        for (Area area : areas) {
            sb.append(area.getInfo()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
