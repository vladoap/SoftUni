package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Enter task number:");
        int taskNum = Integer.parseInt(scanner.nextLine());

        switch (taskNum) {
            case 1 -> exOne();
            case 2 -> exTwo();
            case 3 -> exThree();
            case 4 -> exFour();
            case 5 -> exFive();
            case 6 -> exSix();
            case 7 -> exSeven();
            case 8 -> exEight();
            case 9 -> exNine();
            case 10 -> exTen();
            case 11 -> exEleven();

        }
    }

    private void exEleven() {
        System.out.println("Enter price modifier:");
        BigDecimal modifier = new BigDecimal(scanner.nextLine());
        System.out.println("Enter ingredients names:");
        List<String> names = Arrays.stream(scanner.nextLine().split(", ")).toList();

        ingredientService.updatePriceByModifier(modifier, names);
    }

    private void exTen() {
        System.out.println("Enter price modifier:");
        BigDecimal modifier = new BigDecimal(scanner.nextLine());

        ingredientService.updatePriceByModifier(modifier);
    }

    private void exNine() {
        System.out.println("Enter ingredient names to be deleted:");
        List<String> names = Arrays.stream(scanner.nextLine().split(", ")).toList();

        ingredientService.deleteAllByNameIn(names);
    }

    private void exEight() {
        System.out.println("Enter count of ingredients:");
        Long count = Long.parseLong(scanner.nextLine());

        shampooService.findAllByByIngredientsCountLessThan(count)
                .forEach(System.out::println);
    }

    private void exSeven() {
        System.out.println("Enter ingredient names");
        List<String> ingredientNames = Arrays.stream(scanner.nextLine().split(", ")).toList();

        shampooService.findAllByIngredientsWithNamesIn(ingredientNames)
                .forEach(System.out::println);
    }

    private void exSix() {
        System.out.println("Enter price:");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        Integer count = shampooService.countShampooByPriceLessThan(price);
        System.out.println(count);
    }

    private void exFive() {
        System.out.println("Enter ingredient names");
        List<String> ingredientNames = Arrays.stream(scanner.nextLine().split(", "))
                .toList();

        
           ingredientService.findAllByNameIn(ingredientNames)
                   .forEach(System.out::println);
    }

    private void exFour() {
        System.out.println("Enter starting letter:");
        String letter = scanner.nextLine();

        ingredientService.findAllByNameStartingWith(letter)
                .forEach(System.out::println);

    }

    private void exThree() {
        System.out.println("Enter price:");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        shampooService.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .forEach(System.out::println);
    }

    private void exTwo() {
        System.out.println("Enter size:");
        Size size = Size.valueOf(scanner.nextLine());
        System.out.println("Enter label id:");
        Long labelId = Long.parseLong(scanner.nextLine());

        shampooService.findAllBySizeOrLabelIdOrderByPrice(size, labelId)
                .forEach(System.out::println);
    }

    private void exOne() {
        System.out.println("Enter size (MEDIUM, SMALL, LARGE");
        Size size = Size.valueOf(scanner.nextLine());
        shampooService.findAllBySizeOrderById(size)
                .forEach(System.out::println);
    }
}
