package pl.agh.soa.rest.dao;

import pl.agh.soa.rest.model.Cat;
import pl.agh.soa.rest.xmlHandler.JAXBXMLHandler;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public enum CatsDao {
    instance;

    private final Logger logger;

    private List<Cat> contentProvider;

    CatsDao() {

        logger = Logger.getLogger(String.valueOf(CatsDao.class));
        contentProvider = new ArrayList<>();
        try {
            contentProvider = JAXBXMLHandler.unmarshal(new File("data/cats.xml"));
            logger.info("wczytanie cats z pliku xml");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Cat> getContentProvider() {
        return contentProvider;
    }

    public synchronized void saveContentProvider() {
        try {
            JAXBXMLHandler.marshal(this.contentProvider, new File("data/cats.xml"));
            contentProvider = JAXBXMLHandler.unmarshal(new File("data/cats.xml"));
            logger.info("zapis cats do pliku xml");
            this.contentProvider = contentProvider;
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
