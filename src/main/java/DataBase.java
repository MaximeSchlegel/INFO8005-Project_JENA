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
    private static final String ontPrefix = "http://www.uliege.be/ontologies/2019/2/JO#";

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
                "SELECT *\n" +
                "WHERE {\n" +
                "       ?a rdf:type :Athletic\n" +
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

    public void getSport(int year) {
        System.out.println("List of the Sport in the " + year + ":");

        String queryString =
                "prefix : <http://www.uliege.be/ontologies/2019/2/JO#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?entity\n" +
                "WHERE{\n" +
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

    public void getCountry(int year) {
        System.out.println("List of the attending coutry of the JO " + year + " :");

        String queryString =
                "prefix : <http://www.uliege.be/ontologies/2019/2/JO#>\n" +
                        "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        " SELECT *\n" +
                        " WHERE{\n" +
                        "       ?entity rdf:type :Delegation .\n" +
                        "       ?entity :Attend ?jo .\n" +
                        "       ?jo rdf:type :JO .\n" +
                        "       ?jo :year " + year + " .\n" +
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

    public void getDiscipline(int year) {
        System.out.println("List of the Sport in the " + year + ":");

        String queryString =
                "prefix : <http://www.uliege.be/ontologies/2019/2/JO#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT * \n" +
                "WHERE{\n" +
                "?entity rdf:type :Sport\n" +
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

    public void getOrdeal(int year) {
        System.out.println("List of the Ordeal in the " + year + ":");

        String queryString =
                "prefix : <http://www.uliege.be/ontologies/2019/2/JO#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                " SELECT ?ordeal\n" +
                " WHERE{\n" +
                "       ?ordeal rdf:type :Ordeal .\n" +
                "       ?ordeal :IsPartOf ?jo .\n" +
                "       ?jo rdf:type :JO .\n" +
                "       ?jo :year " + year + " .\n" +
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

    public void getMedals(String country) {
        System.out.println("List of the Medal of " + country + ":");

        String queryString =
                "prefix : <http://www.uliege.be/ontologies/2019/2/JO#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT *\n" +
                "WHERE{\n" +
                "       ?result rdf:type :Result .\n" +
                "       ?result :rank ?rank .\n" +
                "       FILTER( ?rank < 3) .\n" +
                "       ?result :ResultParticipant ?participant .\n" +
                "       ?participant rdf:type :Athletic .\n" +
                "       ?participant :From :" + country + " .\n" +
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

    public void getMedalsSpport(String sport) {
        System.out.println("List of the Medal of " + sport + ":");

        String queryString =
                "prefix : <http://www.uliege.be/ontologies/2019/2/JO#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT *\n" +
                " WHERE{\n" +
                "        ?athletic rdf:type :Athletic .\n" +
                "        ?result :ResultParticipant ?athletic.\n" +
                "        ?result rdf:type :Result .\n" +
                "        ?result :rank 1 .\n" +
                "        ?result :ResultMatch ?match .\n" +
                "        ?match rdf:type :Match .\n" +
                "        ?match :IsPartOf ?ordeal .\n" +
                "        ?ordeal rdf:type :Ordeal .\n" +
                "        ?ordeal :Associate :" + sport + " .\n" +
                "}\n";
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

    public void addAthletic(String fullname) {
        Property nameProp = this.model.getProperty(ontPrefix + "name");
        this.model.createResource(ontPrefix + fullname);
//                .addProperty(RDF.type, "http://www.uliege.be/ontologies/2019/2/JO#Athletic")
//                .addProperty(nameProp, fullname)
    }
}
