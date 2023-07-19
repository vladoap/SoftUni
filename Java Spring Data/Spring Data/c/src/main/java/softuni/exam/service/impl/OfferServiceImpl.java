package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferSeedRootDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XMLParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_FILE_NAME = "src/main/resources/files/xml/offers.xml";
    private final OfferRepository offerRepository;
    private final AgentService agentService;
    private final ApartmentService apartmentService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XMLParser xmlParser;

    public OfferServiceImpl(OfferRepository offerRepository, AgentService agentService, ApartmentService apartmentService, ModelMapper modelMapper, ValidationUtil validationUtil, XMLParser xmlParser) {
        this.offerRepository = offerRepository;
        this.agentService = agentService;
        this.apartmentService = apartmentService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_NAME));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        OfferSeedRootDto offerSeedRootDto = xmlParser.fromFile(OFFERS_FILE_NAME, OfferSeedRootDto.class);

       offerSeedRootDto.getOffers()
                .stream()
                .filter(offerSeedDto -> {
                    boolean isValid = validationUtil.isValid(offerSeedDto);

                    Agent agent = agentService.findByName(offerSeedDto.getAgentName().getFirstName());
                    if (agent == null) {
                        isValid = false;
                    }
                    sb.append(isValid
                                    ? String.format("Successfully imported offer %s", offerSeedDto.getPrice().setScale(2, RoundingMode.HALF_UP))
                                    : "Invalid offer")
                            .append(System.lineSeparator());

                    return isValid;

                })
                .map(offerSeedDto -> {
                    Offer offer = modelMapper.map(offerSeedDto, Offer.class);
                    offer.setAgent(agentService.findByName(offerSeedDto.getAgentName().getFirstName()));
                    offer.setApartment(apartmentService.findById(offerSeedDto.getApartment().getId()));

                    return offer;
                })
                .forEach(offerRepository::save);



        return sb.toString();
    }

    @Override
    public String exportOffers() {
StringBuilder sb = new StringBuilder();
        offerRepository.findAllByApartmentTypeThreeRoomsOrderByApartmentAreaAndPrice()
                .forEach(offer -> sb.append(String.format("Agent %s %s with offer №%d:\n" +
                        "   \t\t-Apartment area: %.2f\n" +
                        "   \t\t--Town: %s\n" +
                        "   \t\t---Price: %s$\n",
                        offer.getAgent().getFirstName(), offer.getAgent().getLastName(), offer.getId(),
                        offer.getApartment().getArea(), offer.getApartment().getTown().getName(),
                        offer.getPrice().setScale(2, RoundingMode.HALF_UP))));

        return sb.toString().trim();
    }
}
