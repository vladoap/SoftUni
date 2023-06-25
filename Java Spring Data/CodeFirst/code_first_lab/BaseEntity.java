package code_first_lab;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigInteger;

@MappedSuperclass
public class BaseEntity {

    @Id
    private BigInteger id;

    public BaseEntity(BigInteger id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
