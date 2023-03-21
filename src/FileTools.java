import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileTools {

    private String nameFile;
    private Path pathFile;
    private String extension;

    public FileTools(String nameFile, String extension) {
        this.nameFile = nameFile;
        this.pathFile = Paths.get(nameFile);
        this.extension = extension;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public Path getPathFile() {
        return pathFile;
    }

    public void setPathFile(Path pathFile) {
        this.pathFile = pathFile;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getNameWithExtension() {
        return this.getNameFile() + this.getExtension();
    }

    public String getNameWithExtensionBin () {
        return this.getNameFile() + ".bin";
    }
    public void writeBinaryFile(String codeTxt) {
        /**
         * Obectif : Permet d'écrire le code binaire du texte [codeText] dans un fichier .bin
         */

        // -- Ajouter des zeros devant le code binaire pour obtenir un multiple de 8
        BitTools binaryNumber = new BitTools(codeTxt);
        binaryNumber.setOkForOctet();

        // -- Get Number Of Octets required
        int numBytes = binaryNumber.getNumberOfOctet(); // Définir le nombre d'octets requis

        // Convertir la chaîne binaire en un tableau de bytes
        byte[] binaryData = new byte[numBytes];
        for (int i = 0; i < numBytes; i++) {
            int start = i * 8;
            int end = (i + 1) * 8;
            String byteStr = binaryNumber.getBinaryNumber().substring(start, end);
            binaryData[i] = (byte) Integer.parseInt(byteStr, 2);
        }


        // Écrire les données binaires dans un fichier
        try (OutputStream os = new FileOutputStream(this.getNameWithExtensionBin())) {
            os.write(binaryData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readTxtFile() throws FileNotFoundException {
        /**
         * Obectif : Permet de lire un fichier texte [pathFile] et de renvoyer
         * une String avec le contenu du fichier texte
         */

        String txt = "";

        File doc =
                new File(this.getNameWithExtension());
        Scanner obj = new Scanner(doc);

        while (obj.hasNextLine())
            txt += obj.nextLine();

        return txt;
    }

    public void writeTxtFile(int sizeAlphabet, HashMap<String, Double> listFrequencesLetter) {
        /**
         * Obectif : Permet d'écrire dans un fichier texte notre alphabet avec les
         * lettres associées à leur fréquence. Au début du fichier nous avons la taille
         * de notre alphabet
         */

        // Exemple de HashMap<String, Double> :
        Map<String, Double> map = new HashMap<>();

        // Nom du fichier de sortie :
        String fileName = this.getNameFile() + "_freq.txt";

        // Ecriture des données dans le fichier :
        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write( String.valueOf(sizeAlphabet) + "\n" );

            for (String key : listFrequencesLetter.keySet()) {
                String line = key + " " + listFrequencesLetter.get(key) + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getCompressionRatio() {
        /**
         * Obectif : Permet d'obtenir de le taux de compression entre le ficiher initial .txt et le fichier final .bin
         */

        // Get path of file
        Path pathInputFile = Paths.get(this.getNameWithExtension());
        Path pathOutputFile = Paths.get(this.getNameWithExtensionBin());

        double inputFileSize = 1;
        double outputFileSize = 1;

        try {
            // Get size of file
            inputFileSize = Files.size(pathInputFile);
            outputFileSize = Files.size(pathOutputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1 - outputFileSize / inputFileSize;
    }

    public double getNbMoyenBitStockage(int sizeText) {
        /**
         * Obectif : Permet de determiner le nombre moyen de bits de stockage
         * d’un caractère du texte compressé
         */

        // Get path of file
        Path pathOutputFile = Paths.get(this.getNameWithExtensionBin());

        double inputFileSize = 1;
        double outputFileSize = 1;

        try {
            // Get size of file (en octet)
            outputFileSize = Files.size(pathOutputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // On multiplie par 8 pour avoir le nombre de bit

        return outputFileSize / sizeText * 8;
    }
}
