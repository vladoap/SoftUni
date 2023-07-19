package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TaskSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.models.enums.CarType;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.CarsService;
import softuni.exam.service.MechanicsService;
import softuni.exam.service.PartsService;
import softuni.exam.service.TasksService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XMLParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TasksServiceImpl implements TasksService {
    private static String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";

    private final TasksRepository tasksRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XMLParser xmlParser;
    private final MechanicsService mechanicsService;
    private final PartsService partsService;
    private final CarsService carsService;

    public TasksServiceImpl(TasksRepository tasksRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XMLParser xmlParser, MechanicsService mechanicsService, PartsService partsService, CarsService carsService) {
        this.tasksRepository = tasksRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.mechanicsService = mechanicsService;
        this.partsService = partsService;
        this.carsService = carsService;
    }

    @Override
    public boolean areImported() {
        return tasksRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        Set<String> mechanicsNames = new HashSet<>();

        TaskSeedRootDto taskSeedRootDto = xmlParser.fromFile(TASKS_FILE_PATH, TaskSeedRootDto.class);


       taskSeedRootDto.getTasks()
                .stream()
                .filter(task -> {
                    boolean isValid = validationUtil.isValid(task);
                    if (mechanicsService.findByFirstName(task.getMechanic().getFirstName()) == null) {
                        isValid = false;
                    }
                    mechanicsNames.add(task.getMechanic().getFirstName());

                    sb.append(isValid
                    ? String.format("Successfully imported task %.2f", task.getPrice())
                            : "Invalid task")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(task -> {
                    Task tsk = modelMapper.map(task, Task.class);
                    Mechanic mechanic = mechanicsService.findByFirstName(task.getMechanic().getFirstName());
                    Part part = partsService.findById(task.getPart().getId());
                    Car car = carsService.findById(task.getCar().getId());

                    tsk.setMechanic(mechanic);
                    tsk.setPart(part);
                    tsk.setCar(car);

                    return tsk;

                })
                .forEach(tasksRepository::save);


        return sb.toString();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        StringBuilder sb = new StringBuilder();
        tasksRepository.findAllByCar_CarTypeOrderByPriceDesc(CarType.coupe)
                .forEach(task -> {
                    sb.append(String.format("Car %s %s with %dkm\n" +
                                            "-Mechanic: %s %s - task №%d:\n" +
                                            " --Engine: %.1f\n" +
                                            "---Price: %s$",
                            task.getCar().getCarMake(), task.getCar().getCarModel(), task.getCar().getKilometers(),
                            task.getMechanic().getFirstName(), task.getMechanic().getLastName(), task.getId(),
                            task.getCar().getEngine(), task.getPrice().setScale(2, RoundingMode.HALF_UP)))
                            .append(System.lineSeparator());
                });

        return sb.toString().trim();

    }
}
