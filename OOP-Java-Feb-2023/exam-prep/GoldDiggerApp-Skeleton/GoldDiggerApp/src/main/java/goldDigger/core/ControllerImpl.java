package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.*;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private Repository<Discoverer> discoversRepository;
    private Repository<Spot> spotsRepository;
    private Operation operation;
    private int countInspectedSpots = 0;

    public ControllerImpl() {
        discoversRepository = new DiscovererRepository();
        spotsRepository = new SpotRepository();
        operation = new OperationImpl();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        switch (kind) {
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
        discoversRepository.add(discoverer);

        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }

        spotsRepository.add(spot);

        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discovererToRemove = discoversRepository.byName(discovererName);

        if (discovererToRemove != null) {
            discoversRepository.remove(discovererToRemove);
            return String.format(DISCOVERER_EXCLUDE, discovererName);
        } else {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> suitableDiscoversUnmodified = discoversRepository.getCollection()
                .stream()
                .filter(d -> d.getEnergy() > 45)
                .collect(Collectors.toList());

//        List<Discoverer> suitableDiscoversModified = new ArrayList<>(suitableDiscoversUnmodified);

        Spot spot = spotsRepository.byName(spotName);

        if (suitableDiscoversUnmodified.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }



        operation.startOperation(spot, suitableDiscoversUnmodified);
        long tiredDiscovers = suitableDiscoversUnmodified.stream()
                .filter(d -> d.getEnergy() == 0)
                .count();
        countInspectedSpots++;

        return String.format(INSPECT_SPOT, spotName, tiredDiscovers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_SPOT_INSPECT, countInspectedSpots))
                .append(System.lineSeparator())
                .append(FINAL_DISCOVERER_INFO)
                .append(System.lineSeparator());

        for (Discoverer discoverer : discoversRepository.getCollection()) {

            Collection<String> exhibits = discoverer.getMuseum().getExhibits();

            sb.append(String.format(FINAL_DISCOVERER_NAME, discoverer.getName()))
                    .append(System.lineSeparator())
                    .append(String.format(FINAL_DISCOVERER_ENERGY, discoverer.getEnergy()))
                    .append(System.lineSeparator());

            if (exhibits.isEmpty()) {
                sb.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None")).append(System.lineSeparator());
            } else {
                String s = String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, exhibits);
                sb.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, s)).append(System.lineSeparator());
            }

        }

        return sb.toString().trim();
    }
}
