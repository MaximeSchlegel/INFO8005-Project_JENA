public class App {
    public static void main(String[] args) {
        DataBase test = new DataBase("./JOV4_RDF.owl");
//        test.readAll();
//        test.getAllAthletics();

        test.getSport(2016);
        test.getOrdeal(2016);
        test.getMedals("Country11_Jamaica");
        test.getMedalsSpport("Discipline3_Athletics_100M");
        test.getDiscipline(2016);

        test.getAllAthletics();
        test.addAthletic("Dupond");
        test.getAllAthletics();

        test.savetofile();

    }
}
