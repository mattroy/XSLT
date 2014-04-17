package cs5200.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matt
 */
@XmlRootElement (name = "sites")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SiteList {
    
    @XmlElement(name = "site")
    private List<Site> sites;

    public SiteList() {
    }

    public SiteList(List<Site> sites) {
        this.sites = sites;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}
