package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentSeedDto;
import softuni.exam.models.dto.ApartmentSeedRootDto;
import softuni.exam.models.dto.ApartmentWithTownAndArea;
import softuni.exam.models.entity.Apartment;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XMLParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private static final String APARTMENTS_FILE_NAME = "src/main/resources/files/xml/apartments.xml";
    private final ApartmentRepository apartmentRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XMLParser xmlParser;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownService townService, ModelMapper modelMapper, ValidationUtil validationUtil, XMLParser xmlParser) {
        this.apartmentRepository = apartmentRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }


    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(APARTMENTS_FILE_NAME));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
       StringBuilder sb = new StringBuilder();

       Set<ApartmentWithTownAndArea> seededApartments = new HashSet<>();
        ApartmentSeedRootDto apartmentSeedRootDto = xmlParser.fromFile(APARTMENTS_FILE_NAME, ApartmentSeedRootDto.class);

        apartmentSeedRootDto.getApartments()
                .stream()
                .filter(apartmentSeedDto -> {
                    boolean isValid = validationUtil.isValid(apartmentSeedDto);

                    String townName = apartmentSeedDto.getTownName();
                    Double area = apartmentSeedDto.getArea();

                    ApartmentWithTownAndArea currentApartment = new ApartmentWithTownAndArea(area, townName);
                    if (seededApartments.contains(currentApartment) || townService.findByName(townName) == null) {
                        isValid = false;
                    } else {
                        seededApartments.add(currentApartment);
                    }


                    sb.append(isValid
                                    ? String.format("Successfully imported apartment %s - %.2f",
                                    apartmentSeedDto.getApartmentType().toString(), apartmentSeedDto.getArea())
                                    : "Invalid apartment")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(apartmentSeedDto -> {
                    Apartment apartment = modelMapper.map(apartmentSeedDto, Apartment.class);
                    apartment.setTown(townService.findByName(apartmentSeedDto.getTownName()));

                    return apartment;
                })
                .forEach(apartmentRepository::save);



        return sb.toString();
    }

    @Override
    public Apartment findById(Long id) {

     return apartmentRepository.findById(id).orElse(null);
    }
}
