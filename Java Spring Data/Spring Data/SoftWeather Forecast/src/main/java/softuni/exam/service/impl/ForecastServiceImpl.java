package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastSeedRootDto;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.enums.DayOfWeek;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XMLParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {
    private static final String FORECASTS_FILE_NAME = "src/main/resources/files/xml/forecasts.xml";
    private final XMLParser xmlParser;
    private final ModelMapper modelMapper;
    private final ForecastRepository forecastRepository;
    private final CityService cityService;
    private final ValidationUtil validationUtil;

    public ForecastServiceImpl(XMLParser xmlParser, ModelMapper modelMapper, ForecastRepository forecastRepository, CityService cityService, ValidationUtil validationUtil) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.forecastRepository = forecastRepository;
        this.cityService = cityService;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FORECASTS_FILE_NAME));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        ForecastSeedRootDto forecastSeedRootDto = xmlParser.fromFile(FORECASTS_FILE_NAME, ForecastSeedRootDto.class);

       forecastSeedRootDto.getForecasts()
                .stream()
                .filter(forecastSeedDto -> {
                    boolean isValid = validationUtil.isValid(forecastSeedDto)
                            && forecastRepository.findByCityIdAndDayOfWeek(forecastSeedDto.getCity(), forecastSeedDto.getDayOfWeek()).orElse(null) == null;

                    sb.append(isValid
                                    ? String.format("Successfully import forecast %s - %.2f",
                                    forecastSeedDto.getDayOfWeek().toString(), forecastSeedDto.getMaxTemp())
                                    : "Invalid forecast")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(forecastSeedDto -> {
                    Forecast forecast = modelMapper.map(forecastSeedDto, Forecast.class);
                    forecast.setCity(cityService.findById(forecastSeedDto.getCity()));

                    return forecast;
                })
                .forEach(forecastRepository::save);




        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();
        List<Forecast> forecasts = forecastRepository.findAllByDayOfWeekAndCityPopulationLessThan(DayOfWeek.SUNDAY, 150000);

        forecasts.stream()
                .forEach(forecast -> sb.append(String.format("City: %s:\n" +
                        "\t-min temperature: %.2f\n" +
                        "\t--max temperature: %.2f\n" +
                        "\t---sunrise: %s\n" +
                        "        ----sunset: %s",
                        forecast.getCity().getCityName(), forecast.getMinTemp(),
                        forecast.getMaxTemp(), forecast.getSunrise().toString(),
                        forecast.getSunset().toString()))
                        .append(System.lineSeparator()));


        return sb.toString().trim();
    }
}
