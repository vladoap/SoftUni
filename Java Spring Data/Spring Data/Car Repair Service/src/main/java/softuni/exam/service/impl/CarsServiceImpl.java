package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XMLParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CarsServiceImpl implements CarsService {

    private static String CARS_FILE_PATH = "src/main/resources/files/xml/cars.xml";
    private final CarsRepository carsRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XMLParser xmlParser;

    public CarsServiceImpl(CarsRepository carsRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XMLParser xmlParser) {
        this.carsRepository = carsRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return carsRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        Set<String> plateNumbers = new HashSet<>();
        CarSeedRootDto carSeedRootDto = xmlParser.fromFile(CARS_FILE_PATH, CarSeedRootDto.class);

        carSeedRootDto.getCars()
                .stream()
                .filter(carDto -> {
                    boolean isValid = validationUtil.isValid(carDto);
                    if (plateNumbers.contains(carDto.getPlateNumber())) {
                        isValid = false;
                    }
                    plateNumbers.add(carDto.getPlateNumber());

                    sb.append(isValid
                    ? String.format("Successfully imported car %s - %s", carDto.getCarMake(), carDto.getCarModel())
                            : "Invalid car")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(carDto -> modelMapper.map(carDto, Car.class))
                .forEach(carsRepository::save);



        return sb.toString();
    }

    @Override
    public Car findById(Long id) {
        return carsRepository.findById(id).orElse(null);
    }
}
