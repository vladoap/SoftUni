package code_first_lab;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver extends BaseEntity{

    @Column(name = "full_name")
    private String fullName;

    @ManyToMany(mappedBy = "drivers")
    private Set<Truck> trucks;

    public Driver(BigInteger id, String fullName) {
        super(id);
        this.fullName = fullName;
    }

    public Driver() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
