package com.ordecon.testdatagenerator;

/**
 * @author Ivan Stojic
 */
public class GeneratePerson {
    private static String[] NAMES = {
            "Ivan", "Ivana", "Ana", "Marko", "Jasnica", "Boris", "Kresimir", "Ivica", "Jure", "Hrvoje", "Juraj", "Nikola",
            "Ante", "Petar", "Dinko", "Slavko", "Mile", "Milan", "Predrag", "Karlo", "Denis", "Damir", "Davor", "Dario", "Danijel",
            "Branko", "Branimir", "Erik", "Erol", "Ernest", "Franko", "Goran", "Gordan", "Jasmin", "Josip", "Luka", "Mislav", "Neven",
            "Niksa", "Ognjen", "Perica", "Ratko", "Ranko", "Aurelija", "Ankica", "Anita", "Amanda", "Bojana", "Branka", "Biljana",
            "Boba", "Dinka", "Darija", "Danijela", "Dejana", "Erika", "Ermina", "Ena", "Ela", "Franka"
    };

    private static String[] SURNAMES = {
            "Arunovic", "Bobinac", "Baskaric", "Brekalo", "Bajaga", "Beric", "Crnkovic", "Cicak", "Canjuga", "Cep", "Cota",
            "Degen", "De Hoot", "Deville", "Karacic", "Stojic", "Premuzic", "Pavic", "Popovic", "Malkoc", "Mornar", "Saric",
            "Krajnovic", "Bojic", "Sostaric", "Kukulj", "Galijanic", "Galenovic", "Rozic", "Zubrinic", "Fustin", "Fajfer", "Kovacevic",
            "Ceci", "Novak", "Spalj", "Gulin", "Lukavecki", "Malic", "Madjarevic", "Madjaric"
    };

    private static int MAXSIZE = SURNAMES.length * NAMES.length;
    private static int GENSIZE = 1500;

    public static void main(String[] args) {
        System.out.println("Maximum number of names: " + (NAMES.length * SURNAMES.length) );

        if (GENSIZE > MAXSIZE) {
            System.out.println("Barf. Can't generate that much data!");
            System.exit(-1);
        }

        int position = 1;

        System.out.println("objid\tfirst_name\tlast_name");
        for (String surname : SURNAMES) {
            if (position >= GENSIZE) {
                break;
            }

            for (String name : NAMES) {
                if (position >= GENSIZE) {
                    break;
                }

                System.out.println(position + "\t" + name + "\t" + surname);
                position++;

            }
        }
    }
}
