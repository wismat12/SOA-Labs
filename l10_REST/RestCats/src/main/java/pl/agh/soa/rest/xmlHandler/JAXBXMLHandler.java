package pl.agh.soa.rest.xmlHandler;

import pl.agh.soa.rest.model.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JAXBXMLHandler {

    // Export
    public static void marshal(List<Cat> cats, File selectedFile) throws IOException, JAXBException {

        JAXBContext context;
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter(selectedFile));
        context = JAXBContext.newInstance(Cats.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(new Cats(cats), writer);
        writer.close();
    }

    // Import
    public static List<Cat> unmarshal(File importFile) throws JAXBException {

        Cats cats = new Cats();

        JAXBContext context = JAXBContext.newInstance(Cats.class);
        Unmarshaller um = context.createUnmarshaller();
        cats = (Cats) um.unmarshal(importFile);

        return cats.getCats();
    }
}
