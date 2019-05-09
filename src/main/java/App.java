import org.apache.jena.rdf.model.*;

public class App {
    public static void main(String[] args) {
        Model model = Factory.loadModel("./JOV2.owl");

        StmtIterator iter = model.listStatements();
        try {
            while ( iter.hasNext() ) {
                Statement stmt = iter.next();

                Resource s = stmt.getSubject();
                Resource p = stmt.getPredicate();
                RDFNode o = stmt.getObject();

                if ( s.isURIResource() ) {
                    System.out.print("URI");
                } else if ( s.isAnon() ) {
                    System.out.print("blank");
                }

                if ( p.isURIResource() )
                    System.out.print(" URI ");

                if ( o.isURIResource() ) {
                    System.out.print("URI");
                } else if ( o.isAnon() ) {
                    System.out.print("blank");
                } else if ( o.isLiteral() ) {
                    System.out.print("literal");
                }

                System.out.println();
            }
        } finally {
            if ( iter != null ) iter.close();
        }

        System.out.println("Its Ok");
    }
}
