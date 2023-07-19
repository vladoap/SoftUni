package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentNameDto;
import softuni.exam.models.dto.AgentSeedDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.service.AgentService;
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
public class AgentServiceImpl implements AgentService {

    private static final String AGENTS_FILE_NAME = "src/main/resources/files/json/agents.json";
    private final AgentRepository agentRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public AgentServiceImpl(AgentRepository agentRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.agentRepository = agentRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of(AGENTS_FILE_NAME));
    }

    @Override
    public String importAgents() throws IOException {
        StringBuilder sb = new StringBuilder();
        Set<String> firstNames = new HashSet<>();
        Set<String> emails = new HashSet<>();

        AgentSeedDto[] agentSeedDtos = gson.fromJson(readAgentsFromFile(), AgentSeedDto[].class);

        Arrays.stream(agentSeedDtos)
                .filter(agentSeedDto -> {
                    boolean isValid = validationUtil.isValid(agentSeedDto);

                    if (firstNames.contains(agentSeedDto.getFirstName())
                            || emails.contains(agentSeedDto.getEmail())) {
                        isValid = false;
                    }

                    firstNames.add(agentSeedDto.getFirstName());
                    emails.add(agentSeedDto.getEmail());

                    sb.append(isValid
                                    ? String.format("Successfully imported agent - %s %s", agentSeedDto.getFirstName(), agentSeedDto.getLastName())
                                    : "Invalid agent")
                            .append(System.lineSeparator());

                    return isValid;

                })
                .map(agentSeedDto -> modelMapper.map(agentSeedDto, Agent.class))
                .forEach(agentRepository::save);


        return sb.toString();
    }

    @Override
    public Agent findByName(String agentName) {
        return agentRepository.findByFirstName(agentName);
    }
}
