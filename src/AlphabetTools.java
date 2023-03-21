import java.util.*;

public class AlphabetTools {

    private String txt;

    public AlphabetTools(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    private ArrayList<String> getAlphabet() {
        /**
         * Permet d'obtenir tous les caractères contenu dans un texte (en supprimant les doublons)
         */
        String[] lettersList = this.getTxt().split("");
        ArrayList<String> lettersArrayList = new ArrayList<String>(Arrays.asList(lettersList));

        return this.removeDuplicates(lettersArrayList);
    }

    public HashMap<String, Double> getFreqencesLetters() {
        /**
         * Return HashMap whith Key: Letter -> Value: frequence
         */

        // Création du HashMap
        HashMap<String, Double> listLettersFrequences = new HashMap<String, Double>();

        // On met chaque caractère du texte dans une ArrayList
        ArrayList<String> txtArrayList = this.StringToArrayList();

        // On determine la taille du texte
        int size = txtArrayList.size();
        double sizeDouble = size;

        // On determine l'alphabet du texte (tous les caractères sans les doublons)
        ArrayList<String> alphabet = this.getAlphabet();

        // On remplit le HashMap avec Key: Letter -> Value -> Nb d'apparition dans le texte
        for (String aLetter : alphabet) {
            listLettersFrequences.put(aLetter, this.countNbLetterInTexte(aLetter, txtArrayList));
        }

        return listLettersFrequences;
    }

    private double countNbLetterInTexte(String letter, ArrayList<String> letters) {
        /**
         * Permet de compter combien de fois une lettre apparait dans une liste
         */

        double count = 0;
        for (String aletter: letters) {
            if (aletter.equals(letter)) {
                count++;
            }
        }

        return count;
    }

    private ArrayList<String> removeDuplicates(ArrayList<String> letters){
        /**
         * Permet de supprimer les doublons dans une liste
         */

        ArrayList<String> newListLetters = new ArrayList<String>();

        for (String letter : letters) {
            if (!newListLetters.contains(letter)) {
                newListLetters.add(letter);
            }
        }

        return newListLetters;
    }

    private ArrayList<String> StringToArrayList() {
        /**
         * Permet de mettre chaque caractère d'un String dans une ArrayList
         */

        String[] strSplit = this.getTxt().split("");

        return new ArrayList<String>(Arrays.asList(strSplit));
    }

    public HashMap<String, Double> sortByFrequenceAscii(HashMap<String, Double> listFrequencesLetter) {
        /**
         * Permet de trier le dictionnaire par fréquence
         * croissante puis par ordre de codage des caractères ASCII.
         */

        // Convertir les entrées de la hashmap en une liste
        List<Map.Entry<String, Double>> list =
                new ArrayList<>(listFrequencesLetter.entrySet());


        // Trier la liste en utilisant un comparateur personnalisé
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                int freqComp = o1.getValue().compareTo(o2.getValue());
                if (freqComp != 0) {
                    return freqComp;
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });


        // Création du LinkedHashMap trié
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }


        return sortedMap;
    }

    public String getBinarayCodeText(HashMap<String, String> dicoCodeLetter) {
        /**
        * Permet d'avoir le code du texte. Chaque lettre du texte est remplacée par son code binaire 01
        */

        String[] lettersList = this.getTxt().split("");
        ArrayList<String> lettersArrayList = new ArrayList<String>(Arrays.asList(lettersList));

        String codeTxt = "";
        for (String letter: lettersArrayList) {
            codeTxt += dicoCodeLetter.get(letter);
        }

        return codeTxt;
    }
}

//c = 12
//b = 10
//a = 10
//d = 16
//
//-- TRI PAR ASCII
//a = 10
//b = 10
//c = 12
//d = 16
//
//-- TRI PAR Frequence
//d = 16
//c = 12
//a = 10
//b = 10


