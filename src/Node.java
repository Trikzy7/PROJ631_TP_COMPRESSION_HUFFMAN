import java.util.ArrayList;

public class Node {
    private String libele;
    private ABR leftChild;
    private ABR rightChild;

    public Node(String libele, ABR leftChild, ABR rightChild) {
        this.libele = libele;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(String libele) {
        this.libele = libele;
        this.leftChild = null;
        this.rightChild = null;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public ABR getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ABR leftChild) {
        this.leftChild = leftChild;
    }

    public ABR getRightChild() {
        return rightChild;
    }

    public void setRightChild(ABR rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isLeaf() {
        /**
         * Obectif : Permet de savoir si le Node est une feuille
         */
        return this.leftChild == null && this.rightChild == null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "libele='" + libele + '\'' +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }


    // ------------------------------- BROUILLON

    //    public ArrayList<ABR> getChildren() {
//        ArrayList<ABR> listChildren = new ArrayList<ABR>();
//
//        if (this.getLeftChild() != null) listChildren.add(this.getLeftChild());
//        if (this.getRightChild() != null) listChildren.add(this.getRightChild());
//
//        return listChildren;
//    }
}
