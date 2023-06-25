package code_first_lab;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

    private String name;


    public Company(BigInteger id, String name) {
        super(id);
        this.name = name;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
