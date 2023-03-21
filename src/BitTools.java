import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;


public class BitTools {

    private String binaryNumber;

    public BitTools(String binaryNumber) {
        this.binaryNumber = binaryNumber;
    }

    public String getBinaryNumber() {
        return binaryNumber;
    }

    public void setBinaryNumber(String binaryNumber) {
        this.binaryNumber = binaryNumber;
    }

    public void setOkForOctet() {
        /**
         * Param : code binaire composé de 0 et de 1
         *
         * Obectif : Si la longueur du code n'est pas un multiple de 8 -> on ajoute
         * des 0 devant le code pour qu'il soit un multiple de 8
         *
         * Return : code binaire SET OK FOR OCTET
         */

        // Vérifiez si la longueur de la chaîne n'est pas un multiple de 8
        int remainder = this.getBinaryNumber().length() % 8;
        StringBuilder sb = new StringBuilder();
        if (remainder != 0) {
            // Ajouter des zéros devant la chaîne pour la rendre un multiple de 8
            int numZerosToAdd = 8 - remainder;

            for (int i = 0; i < numZerosToAdd; i++) {
                sb.append("0");
            }

        }

        sb.append(this.getBinaryNumber());
        this.setBinaryNumber(sb.toString());
    }

    public int getNumberOfOctet() {
        /**
         * Obectif : Permet de connaître le nombre d'octet que notre fichier .bin va peser
         */
        this.setOkForOctet();
        return this.getBinaryNumber().length() / 8;
    }




// ------------------------------------------- BROUILLON

    //    public static String concatAllCodeLetter(ArrayList<String> allcodeLetter) {
//        /**
//         * Permet d'obtenir
//         */
//
//        String codes = "";
//        for (String code: allcodeLetter) {
//            codes += code;
//        }
//
//        return codes;
//    }


    //    public static BigInteger binaryToInteger(String binaryNumber) {
////        return BigInteger.valueOf( Integer.parseInt(binaryNumber, 2) );
//
//        return new BigInteger(binaryNumber, 2);
//    }

//    public static String integerToBinary(BigInteger intNumber) {
//        return intNumber.toString(2);
//    }


    //    public static void writeInFileBin(String path, String binaryNumber) {
//        File file = new File(path);
//        byte[] data = binaryNumber.getBytes();
//
//        try (FileOutputStream fos = new FileOutputStream(file))
//        {
//            fos.write(data);
//            System.out.println("Successfully written data to the file");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
