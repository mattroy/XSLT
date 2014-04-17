package dao;

import cs5200.entity.Site;
import cs5200.entity.SiteList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Matt
 */
public class SiteDao {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("XSLTPU");
    EntityManager em = null;

    public Site findSite(int sideId) {
        Site site;

        em = factory.createEntityManager();
        em.getTransaction().begin();

        site = em.find(Site.class, sideId);

        em.getTransaction().commit();
        em.close();

        return site;
    }

    public List<Site> findAllSites() {
        List<Site> sites = new ArrayList<>();

        em = factory.createEntityManager();
        em.getTransaction().begin();

        Query q = em.createNamedQuery("Site.findAll");
        sites = q.getResultList();

        em.getTransaction().commit();
        em.close();

        return sites;
    }

    public void exportSiteDatabaseToXmlFile(SiteList siteList, String xmlFileName) {
        File xmlFile = new File(xmlFileName);
        try {
            JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
            Marshaller marshaller = jaxb.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(siteList, xmlFile);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void exportXmlFileToOutputFile(String inputXmlFileName, String outputXmlFileName, String xsltFileName) {
        File inputXmlFile = new File(inputXmlFileName);
        File outputXmlFile = new File(outputXmlFileName);
        File xsltFile = new File(xsltFileName);

        StreamSource source = new StreamSource(inputXmlFile);
        StreamSource xslt = new StreamSource(xsltFile);
        StreamResult output = new StreamResult(outputXmlFile);

        TransformerFactory transFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer(xslt);
            transformer.transform(source, output);
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        SiteDao sd = new SiteDao();

        List<Site> siteList = sd.findAllSites();

        sd.exportSiteDatabaseToXmlFile(new SiteList(siteList), "xml/sites.xml");
        
        sd.exportXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
        sd.exportXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
    }
}
