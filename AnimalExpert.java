import javafoundations.LinkedBinaryTree;
import java.util.Scanner;
/**
 * 在这里给出对类 AnimalExpert 的描述。
 * 
 * @author Tracy
 * @version 2025.11.29
 */
public class AnimalExpert
{
    private LinkedBinaryTree<String> t1;
    
    /**
     * AnimalExpert Constructor
     *
     */
    public AnimalExpert(){
        String s1 = "is it big?";
        String s2  = "does it live in a cage?";
        String s3 = "does it eat grass?";
        String s4 = "does it meow?";
        String s5 = "does it chirp?";
        String s6 = "does it have a long neck?";
        String s7 = "does it moo?";
        String s8 = "it's a dog!";
        String s9 = "it's a cat!";
        String s10 = "it's a hamster!";
        String s11 = "it's a bird!";
        String s12 = "it's a bear!";
        String s13 = "it's a giraffe!";
        String s14 = "it's a horse!";
        String s15 = "it's a cow!";
        
        LinkedBinaryTree<String> t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16;
        t8 = new LinkedBinaryTree<String>(s8);
        t9 = new LinkedBinaryTree<String>(s9);
        t10 = new LinkedBinaryTree<String>(s10);
        t11 = new LinkedBinaryTree<String>(s11);
        t12 = new LinkedBinaryTree<String>(s12);
        t13 = new LinkedBinaryTree<String>(s13);
        t14 = new LinkedBinaryTree<String>(s14);
        t15 = new LinkedBinaryTree<String>(s15);
        
        t4 = new LinkedBinaryTree<String>(s4,t8,t9);
        t5 = new LinkedBinaryTree<String>(s5,t10,t11);
        t6 = new LinkedBinaryTree<String>(s6,t12,t13);
        t7 = new LinkedBinaryTree<String>(s7,t14,t15);
        
        t2 = new LinkedBinaryTree<String>(s2,t4,t5);
        t3 = new LinkedBinaryTree<String>(s3,t6,t7);
        
        t1 = new LinkedBinaryTree<String>(s1,t2,t3);
    }
    
    /**
     * Method guessMyAnimal
     *
     */
    public void guessMyAnimal(){
        Scanner scan = new Scanner(System.in);
        LinkedBinaryTree<String> current = t1;
        System.out.println("=== I will try the guess the animal you are thinking of! ===");
        while(current.size() > 1){
            System.out.println("Q: " + current.getRootElement() + "(y/n)");
            if(scan.nextLine().equalsIgnoreCase("n")){
                current = current.getLeft();
            }
            else{
                current = current.getRight();
            }
        }
        System.out.println("AND...: " + current.getRootElement());
    }
    
    public static void main(String[] args){
        AnimalExpert test  = new AnimalExpert();
        Scanner scan = new Scanner(System.in);
        String response;
        do{
            test.guessMyAnimal();
            System.out.println("Would you like to play again? (y/n)");
            response = scan.nextLine();
        }while(response.equals("y"));
        System.out.println("OK! Thanks for playing my game. Bye now!");
    }
}