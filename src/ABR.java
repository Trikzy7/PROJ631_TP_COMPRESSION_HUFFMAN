import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ABR {

    private Node root;
    private double freq;

    public ABR(Node root, double freq) {
        this.root = root;
        this.freq = freq;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public double getFreq() {
        return freq;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "ABR{" +
                "root=" + root +
                ", freq=" + freq +
                '}';
    }


    public static ABR buildABR(ArrayList<ABR> listABR) {
        /**
         * Objectif : Permet de construire l'arbre suivant l'algorithme donnée dans l'énoncé
         *
         * Params : ArrayList<ABR> listABR | la liste de tous les ABR construit et basé sur chaque lettre
         *
         * Return : ABR | l'arbre construit à partir de la liste donnée en paramètre
         */

        // Tant qu'il ne reste pas qu'un seul arbre dans notre listABR (l'arbre total qui a été construit)
        while (listABR.size() != 1) {
            // On prend les deux premier arbre car ils la liste est triée par fréquence donc t1.freq <= t2.freq
            ABR t1 = listABR.get(0);
            ABR t2 = listABR.get(1);

            // Remove t1 et t2 from listABR
            listABR.remove(t1);
            listABR.remove(t2);

            // Build new ABR t
            ABR t = new ABR(
                    new Node(null, t1, t2),
                    t1.getFreq() + t2.getFreq()
            );

            listABR.add(t);

            listABR.sort(Comparator.comparingDouble(a -> a.freq));
        }

        return listABR.get(0);
    }

    public HashMap<String, String> getAllCodeLetter(String codeChemin, HashMap<String, String> listCodeChemin) {

        /**
         * Objectif : Permet d'avoir le code de chaque lettre en parcourant l'arbre en profondeur
         *
         * Params : String codeChemin -> Strign vide | HashMap<String, String> listCodeChemin -> dico vide
         *
         * Return : HashMap<String, String> listCodeChemin | contenant les codes de chaque lettre avec la lettre in key
         */

        // Si on est sur une feuille -> ajout du code à la liste
        if (this.getRoot().isLeaf()) {
//            System.out.println(this.getRoot().getLibele());
//            System.out.println(codeChemin);
//            System.out.println("------------------------------------------");
            listCodeChemin.put(this.getRoot().getLibele(), codeChemin);

        }
        // Sinon on creuse jusqu'à trouver une feuille
        else {

            // Va creuser à gauche
            if (this.getRoot().getLeftChild() != null) {
                this.getRoot().getLeftChild().getAllCodeLetter(codeChemin + "0", listCodeChemin);
            }

            // Va creuser à droite
            if (this.getRoot().getRightChild() != null) {
                this.getRoot().getRightChild().getAllCodeLetter(codeChemin + "1",listCodeChemin );
            }

        }

        return listCodeChemin;
    }


}

