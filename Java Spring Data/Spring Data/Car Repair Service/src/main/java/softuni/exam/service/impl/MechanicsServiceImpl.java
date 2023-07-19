package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicSeedDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MechanicsServiceImpl implements MechanicsService {

    private static String MECHANICS_FILE_PATH = "src/main/resources/files/json/mechanics.json";
    private final MechanicsRepository mechanicsRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.mechanicsRepository = mechanicsRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return mechanicsRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANICS_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();

        Set<String> firstNames = new HashSet<>();
        Set<String> phoneNumbers = new HashSet<>();
        Set<String> emails = new HashSet<>();
        MechanicSeedDto[] mechanicSeedDtos = gson.fromJson(readMechanicsFromFile(), MechanicSeedDto[].class);

        Arrays.stream(mechanicSeedDtos)
                .filter(mechanic -> {
                    boolean isValid = validationUtil.isValid(mechanic);
                    if (firstNames.contains(mechanic.getFirstName()) || phoneNumbers.contains(mechanic.getPhone())
                            || emails.contains(mechanic.getEmail())) {
                        isValid = false;
                    }
                    firstNames.add(mechanic.getFirstName());
                    phoneNumbers.add(mechanic.getPhone());
                    emails.add(mechanic.getEmail());

                    sb.append(isValid
                                    ? String.format("Successfully imported mechanic %s %s",
                                    mechanic.getFirstName(), mechanic.getLastName())
                                    : "Invalid mechanic")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(mechanic -> modelMapper.map(mechanic, Mechanic.class))
                .forEach(mechanicsRepository::save);

        return sb.toString();
    }

    @Override
    public Mechanic findByFirstName(String firstName) {
        return mechanicsRepository.findByFirstName(firstName);
    }
}
