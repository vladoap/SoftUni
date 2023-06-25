package code.first.exercise.hospital;


import code.first.exercise.sales.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "prescribed_medicaments")
public class PrescribedMedicament extends BaseEntity {

    @Column
    private String name;

    @ManyToMany(mappedBy = "prescribedMedicaments")
    private Set<Patient> patients;

    public PrescribedMedicament() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
