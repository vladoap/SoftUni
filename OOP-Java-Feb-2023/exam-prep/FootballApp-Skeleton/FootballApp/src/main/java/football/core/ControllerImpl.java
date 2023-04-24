package football.core;


import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.*;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplement;
    private Map<String, Field> fields;

    public ControllerImpl() {
        supplement = new SupplementRepositoryImpl();
        fields = new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field fieldToAdd;
        if (fieldType.equals("ArtificialTurf")) {
            fieldToAdd = new ArtificialTurf(fieldName);
        } else if (fieldType.equals("NaturalGrass")) {
            fieldToAdd = new NaturalGrass(fieldName);
        } else {
            throw new NullPointerException(INVALID_FIELD_TYPE);
        }

        fields.put(fieldName, fieldToAdd);
        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplementToAdd;
        if (type.equals("Powdered")) {
            supplementToAdd = new Powdered();
        } else if (type.equals("Liquid")) {
            supplementToAdd = new Liquid();
        } else {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        supplement.add(supplementToAdd);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Field field = fields.get(fieldName);
        Supplement s = supplement.findByType(supplementType);
        if (s == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        field.addSupplement(s);
        supplement.remove(s);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Field field = fields.get(fieldName);
        Player player;
        if (playerType.equals("Women")) {
            player = new Women(playerName, nationality, strength);
        } else if (playerType.equals("Men")) {
            player = new Men(playerName, nationality, strength);
        } else {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        String output;
        if (!(playerCanPlay(playerType, field))) {
            output = FIELD_NOT_SUITABLE;
        } else {
            field.addPlayer(player);
            output = String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
        }

        return output;
    }

    private boolean playerCanPlay(String playerType, Field field) {
        String fieldType = field.getClass().getSimpleName();
        if (playerType.equals("Women") && fieldType.equals("ArtificialTurf")) {
            return true;
        } else if (playerType.equals("Men") && fieldType.equals("NaturalGrass")) {
            return true;
        }
        return false;
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fields.get(fieldName);

        field.drag();

        return String.format(PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.get(fieldName);

        int sumStrength = field.getPlayers().stream()
                .mapToInt(Player::getStrength)
                .sum();


        return String.format(STRENGTH_FIELD, fieldName, sumStrength);
    }

    @Override
    public String getStatistics() {
        return fields.values()
                .stream()
                .map(Field::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
