package tree;

import estrut.Tree;
import java.util.ArrayList;

public class BinarySearchTree implements Tree{
    private Node root;

    private class Node {
        int valor;
        Node left, right;

        public Node(int valor) {
            this.valor = valor;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElemento(root, valor);
    }

    private boolean buscaElemento(Node node, int valor) {
        if (node == null) {
            return false;
        }
        if (valor < node.valor) {
            return buscaElemento(node.left, valor);
        } else if (valor > node.valor) {
            return buscaElemento(node.right, valor);
        } else {
            return true;
        }
    }

    @Override
    public int minimo() {
        return minimo(root).valor;
    }

    private Node minimo(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minimo(node.left);
        }
    }

    @Override
    public int maximo() {
        return maximo(root).valor;
    }

    private Node maximo(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return maximo(node.right);
        }
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElemento(root, valor);
    }

    private Node insereElemento(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }
        if (valor < node.valor) {
            node.left = insereElemento(node.left, valor);
        } else if (valor > node.valor) {
            node.right = insereElemento(node.right, valor);
        }
        return node;
    }

    @Override
    public void remove(int valor) {
        root = remove(root, valor);
    }

    private Node remove(Node node, int valor) {
        if (node == null) {
            return null;
        }
        if (valor < node.valor) {
            node.left = remove(node.left, valor);
        } else if (valor > node.valor) {
            node.right = remove(node.right, valor);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node t = node;
            node = minimo(t.right);
            node.right = removeMinimo(t.right);
            node.left = t.left;
        }
        return node;
    }

    private Node removeMinimo(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMinimo(node.left);
        return node;
    }

    private ArrayList<Integer> preOrderList = new ArrayList<>();
    private ArrayList<Integer> inOrderList = new ArrayList<>();
    private ArrayList<Integer> postOrderList = new ArrayList<>();

    @Override
    public int[] preOrdem() {
        preOrderList.clear();
        preOrdem(root);
        return preOrderList.stream().mapToInt(i->i).toArray();
    }

    private void preOrdem(Node node) {
        if (node != null) {
            preOrderList.add(node.valor);
            preOrdem(node.left);
            preOrdem(node.right);
        }
    }

    @Override
    public int[] emOrdem() {
        inOrderList.clear();
        emOrdem(root);
        return inOrderList.stream().mapToInt(i->i).toArray();
    }

    private void emOrdem(Node node) {
        if (node != null) {
            emOrdem(node.left);
            inOrderList.add(node.valor);
            emOrdem(node.right);
        }
    }

    @Override
    public int[] posOrdem() {
        postOrderList.clear();
        posOrdem(root);
        return postOrderList.stream().mapToInt(i->i).toArray();
    }

    private void posOrdem(Node node) {
        if (node != null) {
            posOrdem(node.left);
            posOrdem(node.right);
            postOrderList.add(node.valor);
        }
    }
}
