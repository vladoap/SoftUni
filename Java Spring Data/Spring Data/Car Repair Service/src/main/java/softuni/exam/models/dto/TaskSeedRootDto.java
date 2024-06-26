package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskSeedRootDto {

    @XmlElement(name = "task")
    List<TaskSeedDto> tasks;

    public TaskSeedRootDto() {
    }

    public List<TaskSeedDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskSeedDto> tasks) {
        this.tasks = tasks;
    }
}
