import org.apache.jena.base.Sys;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

public class App {
    public static void main(String[] args) {
        DataBase test = new DataBase("./JOV3_RDF.owl");
        test.readAll();
        test.getAthletics();


        System.out.println("Its Ok");
    }
}
