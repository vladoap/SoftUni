package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartSeedDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;
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
public class PartsServiceImpl implements PartsService {

    private static String PARTS_FILE_PATH = "src/main/resources/files/json/parts.json";
    private final PartsRepository partsRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PartsServiceImpl(PartsRepository partsRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.partsRepository = partsRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return partsRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(PARTS_FILE_PATH));
    }

    @Override
    public String importParts() throws IOException {

        StringBuilder sb = new StringBuilder();
        Set<String> uniqueNames = new HashSet<>();
        PartSeedDto[] partSeedDtos = gson.fromJson(readPartsFileContent(), PartSeedDto[].class);

        Arrays.stream(partSeedDtos)
                .filter(part -> {
                    boolean isValid = validationUtil.isValid(part);
                    if (uniqueNames.contains(part.getPartName())) {
                        isValid = false;
                    }

                    uniqueNames.add(part.getPartName());

                    sb
                            .append(isValid ? String.format("Successfully imported part %s - %s", part.getPartName(), part.getPrice())
                                    : "Invalid part")
                            .append(System.lineSeparator());


                    return isValid;
                })
                .map(part -> modelMapper.map(part, Part.class))
                .forEach(partsRepository::save);

        return sb.toString();
    }

    @Override
    public Part findById(Long id) {
        return partsRepository.findById(id).orElse(null);
    }
}
