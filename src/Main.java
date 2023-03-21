import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // --------------- Get Files
        String nameFile1 = "textesimple";
        FileTools txtSimpleTxt = new FileTools(nameFile1, ".txt");

        String txt = txtSimpleTxt.readTxtFile();
//        System.out.println(txt);

        // -------------- ETAPE 1 - Détermination de l’alphabet et des fréquences de caractères

        // -- Get l'alphabet avec la fréquence de chaque caractère
        AlphabetTools alphabet = new AlphabetTools(txt);
        HashMap<String, Double> listFrequencesLetter = alphabet.getFreqencesLetters();

        // -- On trie notre liste par fréquence puis par ASCII
        listFrequencesLetter = alphabet.sortByFrequenceAscii(listFrequencesLetter);
//        System.out.println(listFrequencesLetter);

        // -- On ecrit l'alphabet avec la frequence
        txtSimpleTxt.writeTxtFile(listFrequencesLetter.size(), listFrequencesLetter);


        // -------------- ETAPE 2 - Construction de l’arbre

        // -- On construit un ABR pour chaque lettre
        ArrayList<ABR> listABR = new ArrayList<ABR>();

        for (String keyLetter : listFrequencesLetter.keySet()) {
            listABR.add(
                    new ABR( new Node(keyLetter), listFrequencesLetter.get(keyLetter) )
            );
        }

        // -- On construit l'abre
        ABR ABRBuild = ABR.buildABR(listABR);


        // -------------- ETAPE 3 - Codage du texte

        // -- On obtient le code binaire 01 de chaque lettre
        HashMap<String, String> allcodeLetter = ABRBuild.getAllCodeLetter("", new HashMap<String, String>());
//        System.out.println(allcodeLetter);

        // -- On obtient le code du texte : chaque lettre est remplacée par son code binaire 01
        String codeTxt = alphabet.getBinarayCodeText(allcodeLetter);
//        System.out.println(codeTxt);


        // -- Écrire dans le fichier binaire
        txtSimpleTxt.writeBinaryFile(codeTxt);



        // -------------- ETAPE 4 - Détermination du taux de compression
        double compressionRatio =  txtSimpleTxt.getCompressionRatio();
        System.out.println("---------------------------- TAUX DE COMPRESSION ----------------------------");
        System.out.println("----------- Taux de compression de " + nameFile1 + ".txt : " + compressionRatio);



        // -------------- ETAPE 5 - Détermination du nombre moyen de bits de stockage d’un caractère du texte compressé
        double nbMoyenBitStockage = txtSimpleTxt.getNbMoyenBitStockage(txt.length());
        System.out.println("----------- Nombre moyen de bits de stockage d’un caractère du texte " + nameFile1 + ".txt : " + nbMoyenBitStockage);


    }
}














