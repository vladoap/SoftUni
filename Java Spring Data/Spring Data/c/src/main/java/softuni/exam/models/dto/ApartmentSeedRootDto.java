package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentSeedRootDto {

    @XmlElement(name = "apartment")
    List<ApartmentSeedDto> apartments;

    public ApartmentSeedRootDto() {
    }

    public List<ApartmentSeedDto> getApartments() {
        return apartments;
    }

    public void setApartments(List<ApartmentSeedDto> apartments) {
        this.apartments = apartments;
    }
}
