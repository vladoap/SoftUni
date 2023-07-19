package softuni.exam.models.dto;

import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "apartment")
    private ApartmentIdDto apartment;

    @XmlElement(name = "agent")
    private AgentNameDto agentName;

    @XmlElement(name = "publishedOn")
    private String publishedOn;

    public OfferSeedDto() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ApartmentIdDto getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentIdDto apartment) {
        this.apartment = apartment;
    }

    public AgentNameDto getAgentName() {
        return agentName;
    }

    public void setAgentName(AgentNameDto agentName) {
        this.agentName = agentName;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
}
