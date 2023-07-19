package softuni.exam.models.entity;

import softuni.exam.models.enums.CarType;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {


    private Long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


