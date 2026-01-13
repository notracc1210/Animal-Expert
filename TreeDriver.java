
import java.util.Scanner;  // for reading from keyboard
import javafoundations.LinkedBinaryTree; // for the LinkedBinaryTree and all

public class TreeDriver
{
    public static void main(String[] args) {
        LinkedBinaryTree<String> tree1 = new LinkedBinaryTree<String>("Anne");
        LinkedBinaryTree<String> tree2 = new LinkedBinaryTree<String>("Bob");
        LinkedBinaryTree<String> tree3 = new LinkedBinaryTree<String>("Catie", tree1, tree2);
        System.out.println(tree1);
        System.out.println(tree2);
        System.out.println(tree3);
        
        LinkedBinaryTree<String> t0,t1,t2,t3;
        t0 = new LinkedBinaryTree<String>(); // empty tree
        t1 = new LinkedBinaryTree<String>("john"); // tree with just a root
        // and now a tree created by the 3-argument constructor 
        t2 = new LinkedBinaryTree<String>("paul", 
        					t0 ,
        					new LinkedBinaryTree<String>("george"));
        t3 = new LinkedBinaryTree<String>("ringo", t1, t0);
        LinkedBinaryTree<String> beatles = 
        				new LinkedBinaryTree<String>("elvis",t2,t3);
        
        System.out.println(beatles);
        
        LinkedBinaryTree<String> td, te, tf, tb, tc, ta, tempty;
        td = new LinkedBinaryTree<String>("d");
        te = new LinkedBinaryTree<String>("e");
        tf = new LinkedBinaryTree<String>("f");
        tempty = new LinkedBinaryTree<String>();
        tb = new LinkedBinaryTree<String>("b", td, te);
        tc = new LinkedBinaryTree<String>("c", tf, tempty);
        ta = new LinkedBinaryTree<String>("a", tb,tc);
        System.out.println(ta);
    }
}
