import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.*;


public class DataBase {
    private String file;
    private Model model;

    public DataBase(String file) {
        this.file = file;

        this.model = ModelFactory.createDefaultModel();

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

    public void readAll(){
        this.model.write(System.out);
        System.out.println("\n");
    }

    public void getAllAthletics() {
        System.out.println("List of the athletics:");

        String queryString =
                "prefix : <http://www.uliege.be/ontologies/2019/2/JO#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT * WHERE {?a rdf:type :Athletic}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        try {
            ResultSet results = qexec.execSelect() ;
            ResultSetFormatter.out(System.out, results, query) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        qexec.close();
        System.out.println();
    }

    public void getSport(int year) {
        System.out.println("List of the Sport in the " + year + ":");

        String queryString =
                "prefix : <http://www.uliege.be/ontologies/2019/2/JO#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT *\n" +
                        " WHERE{\n" +
                        "        ?entity rdf:type :Discipline .\n" +
                        "        ?ordeal :Associate ?entity .\n" +
                        "        ?ordeal rdf:type :Ordeal .\n" +
                        "        ?ordeal :IsPartOf ?jo .\n" +
                        "        ?jo rdf:type :JO .\n" +
                        "        ?jo :year " + year + " .\n" +
                        "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        try {
            ResultSet results = qexec.execSelect() ;
            ResultSetFormatter.out(System.out, results, query) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        qexec.close();
        System.out.println();
    }

    public addEntities(){
    }

    public addPtopertie(){

    }

    public editPropertie(){

    }
}
