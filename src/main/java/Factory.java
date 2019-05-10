import com.sun.rmi.rmid.ExecPermission;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Factory {
    public static boolean isValid(OntModel model) {
        return model.validate().isValid();
    }

    public static Model loadModel(String inputFileName) {
        Model model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        ClassLoader classLoader = Factory.class.getClassLoader();

        InputStream in = null;

        try {
            in = new FileInputStream(new File(classLoader.getResource(inputFileName).getFile()));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        // read the OWL file
        model.read(in, null);
        return model;
    }
}

