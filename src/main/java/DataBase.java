import org.apache.jena.base.Sys;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.io.*;


public class DataBase {
    private String file;
    private OntModel model;

    public DataBase(String file) {
        this.file = file;

        this.model = ModelFactory.createOntologyModel();
        InputStream inputStream = FileManager.get().open(this.file);
        if (inputStream == null) {
            throw new IllegalArgumentException("File: " + this.file + " not found");
        }
        this.model.read(inputStream, "");
        try {
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void savetofile() {
        try {
            FileWriter outWriter = new FileWriter(this.file);
            this.model.write(outWriter);
            outWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savetofile(String fileName) {
        try {
            FileWriter outWriter = new FileWriter("./" + fileName + ".owl");
            this.model.write(outWriter);
            outWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAthletics() {
        OntClass athletics = this.model.getOntClass("http://www.semanticweb.org/hp/ontologies/2019/2/untitled-ontology-9#Athletic");

        System.out.println("List of all the athletics :");
        for (final ExtendedIterator<? extends OntResource> iter = athletics.listInstances(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
        System.out.println("\n");
    }

    public void readAll(){
        this.model.write(System.out);
        System.out.println("\n");
    }
}
