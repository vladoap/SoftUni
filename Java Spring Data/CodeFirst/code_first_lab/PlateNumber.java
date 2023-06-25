package code_first_lab;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "plate_numbers")
public class PlateNumber extends BaseEntity{


    private String number;

    public PlateNumber(BigInteger id, String number) {
        super(id);
        this.number = number;
    }

    public PlateNumber() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
