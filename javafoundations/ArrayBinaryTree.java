//*******************************************************************
//  ArrayBinaryTree.java       Java Foundations
//
//  Implements a binary tree using an array representation with
//  computed linking (array indices for parent-child relationships).
//*******************************************************************

package javafoundations;

import java.util.Iterator;
import javafoundations.*;
import javafoundations.exceptions.*;

public class ArrayBinaryTree<T> implements BinaryTree<T>
{
    private static final int DEFAULT_CAPACITY = 50;
    protected T[] tree;
    protected int count;  // number of elements in the tree
    protected int rootIndex;  // index of the root (usually 0)

    //-----------------------------------------------------------------
    //  Creates an empty binary tree.
    //-----------------------------------------------------------------
    public ArrayBinaryTree()
    {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        count = 0;
        rootIndex = 0;
    }

    //-----------------------------------------------------------------
    //  Creates a binary tree with the specified element as its root.
    //-----------------------------------------------------------------
    public ArrayBinaryTree (T element)
    {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        count = 0;
        rootIndex = 0;
        tree[rootIndex] = element;
        count = 1;
    }

    //-----------------------------------------------------------------
    //  Creates a binary tree with the two specified subtrees.
    //-----------------------------------------------------------------
    public ArrayBinaryTree (T element, ArrayBinaryTree<T> left,
    ArrayBinaryTree<T> right)
    {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        count = 0;
        rootIndex = 0;
        tree[rootIndex] = element;
        count = 1;

        // Copy left subtree starting at index 1 (left child of root)
        if (left != null && !left.isEmpty())
        {
            copySubtree(left, 0, 1);
        }

        // Copy right subtree starting at index 2 (right child of root)
        if (right != null && !right.isEmpty())
        {
            copySubtree(right, 0, 2);
        }
    }

    //-----------------------------------------------------------------
    //  Helper method to copy a subtree from source tree to destination
    //  at the specified indices.
    //-----------------------------------------------------------------
    private void copySubtree(ArrayBinaryTree<T> source, int sourceIndex, 
                             int destIndex)
    {
        if (sourceIndex >= source.tree.length || source.tree[sourceIndex] == null)
            return;

        // Ensure capacity
        while (destIndex >= tree.length)
            expandCapacity();

        // Copy the element
        tree[destIndex] = source.tree[sourceIndex];
        count++;

        // Recursively copy left and right children
        int leftChild = 2 * sourceIndex + 1;
        int rightChild = 2 * sourceIndex + 2;
        int destLeft = 2 * destIndex + 1;
        int destRight = 2 * destIndex + 2;

        copySubtree(source, leftChild, destLeft);
        copySubtree(source, rightChild, destRight);
    }

    //-----------------------------------------------------------------
    //  Expands the capacity of the tree array.
    //-----------------------------------------------------------------
    private void expandCapacity()
    {
        T[] larger = (T[]) (new Object[tree.length * 2]);
        for (int i = 0; i < tree.length; i++)
            larger[i] = tree[i];
        tree = larger;
    }

    //-----------------------------------------------------------------
    //  Returns the element stored in the root of the tree. Throws an
    //  EmptyCollectionException if the tree is empty.
    //-----------------------------------------------------------------
    public T getRootElement()
    {
        if (isEmpty())
            throw new EmptyCollectionException ("Get root operation "
                + "failed. The tree is empty.");

        return tree[rootIndex];
    }

    //-----------------------------------------------------------------
    //  Returns the left subtree of the root of this tree.
    //-----------------------------------------------------------------
    public BinaryTree<T> getLeft()
    {
        if (isEmpty())
            throw new EmptyCollectionException ("Get left operation "
                + "failed. The tree is empty.");

        ArrayBinaryTree<T> result = new ArrayBinaryTree<T>();
        int leftIndex = 2 * rootIndex + 1;
        
        if (leftIndex < tree.length && tree[leftIndex] != null)
        {
            result.copySubtree(this, leftIndex, 0);
        }

        return result;
    }

    //-----------------------------------------------------------------
    //  Returns the right subtree of the root of this tree.
    //-----------------------------------------------------------------
    public BinaryTree<T> getRight()
    {
        if (isEmpty())
            throw new EmptyCollectionException ("Get right operation "
                + "failed. The tree is empty.");

        ArrayBinaryTree<T> result = new ArrayBinaryTree<T>();
        int rightIndex = 2 * rootIndex + 2;
        
        if (rightIndex < tree.length && tree[rightIndex] != null)
        {
            result.copySubtree(this, rightIndex, 0);
        }

        return result;
    }

    //-----------------------------------------------------------------
    //  Returns the element in this binary tree that matches the
    //  specified target. Throws a ElementNotFoundException if the
    //  target is not found.
    //-----------------------------------------------------------------
    public T find (T target)
    {
        int index = findIndex(target, rootIndex);
        
        if (index == -1)
            throw new ElementNotFoundException("Find operation failed. "
                + "No such element in tree.");

        return tree[index];
    }

    //-----------------------------------------------------------------
    //  Helper method to find the index of the target element.
    //-----------------------------------------------------------------
    private int findIndex(T target, int currentIndex)
    {
        if (currentIndex >= tree.length || tree[currentIndex] == null)
            return -1;

        if (tree[currentIndex].equals(target))
            return currentIndex;

        // Search left subtree
        int leftIndex = 2 * currentIndex + 1;
        int result = findIndex(target, leftIndex);
        if (result != -1)
            return result;

        // Search right subtree
        int rightIndex = 2 * currentIndex + 2;
        return findIndex(target, rightIndex);
    }

    //-----------------------------------------------------------------
    //  Returns true if the binary tree contains an element that
    //  matches the specified element and false otherwise.
    //-----------------------------------------------------------------
    public boolean contains (T target) 
    {
        try
        {
            find(target);
        }
        catch(ElementNotFoundException enfe)
        {
            return false;
        }

        return true;
    }

    //-----------------------------------------------------------------
    //  Returns true if the binary tree contains no elements, and
    //  false otherwise.
    //-----------------------------------------------------------------
    public boolean isEmpty() 
    {
        return count == 0;
    }

    //-----------------------------------------------------------------
    //  Returns the number of elements in this binary tree.
    //-----------------------------------------------------------------
    public int size()
    {
        return count;
    }

    //-----------------------------------------------------------------
    //  Returns a string representation of the binary tree.
    //-----------------------------------------------------------------
    public String toString() 
    {
        String result = "";
        Iterator<T> iter = iterator();
        while(iter.hasNext())
            result += iter.next() + "\t";
        return result;
    }

    //-----------------------------------------------------------------
    //  Populates and returns an iterator containing the elements in
    //  this binary tree using a preorder traversal.
    //-----------------------------------------------------------------
    public Iterator<T> preorder() 
    {
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (!isEmpty())
            preorderHelper(rootIndex, iter);

        return iter;
    }

    //-----------------------------------------------------------------
    //  Helper method for preorder traversal.
    //-----------------------------------------------------------------
    private void preorderHelper(int index, ArrayIterator<T> iter)
    {
        if (index >= tree.length || tree[index] == null)
            return;

        iter.add(tree[index]);

        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        preorderHelper(leftIndex, iter);
        preorderHelper(rightIndex, iter);
    }

    //-----------------------------------------------------------------
    //  Populates and returns an iterator containing the elements in
    //  this binary tree using an inorder traversal.
    //-----------------------------------------------------------------
    public Iterator<T> inorder()
    {
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (!isEmpty())
            inorderHelper(rootIndex, iter);

        return iter;
    }

    //-----------------------------------------------------------------
    //  Helper method for inorder traversal.
    //-----------------------------------------------------------------
    private void inorderHelper(int index, ArrayIterator<T> iter)
    {
        if (index >= tree.length || tree[index] == null)
            return;

        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        inorderHelper(leftIndex, iter);
        iter.add(tree[index]);
        inorderHelper(rightIndex, iter);
    }

    //-----------------------------------------------------------------
    //  Populates and returns an iterator containing the elements in
    //  this binary tree using a postorder traversal.
    //-----------------------------------------------------------------
    public Iterator<T> postorder() 
    {
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (!isEmpty())
            postorderHelper(rootIndex, iter);

        return iter;
    }

    //-----------------------------------------------------------------
    //  Helper method for postorder traversal.
    //-----------------------------------------------------------------
    private void postorderHelper(int index, ArrayIterator<T> iter)
    {
        if (index >= tree.length || tree[index] == null)
            return;

        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        postorderHelper(leftIndex, iter);
        postorderHelper(rightIndex, iter);
        iter.add(tree[index]);
    }

    //-----------------------------------------------------------------
    //  Populates and returns an iterator containing the elements in
    //  this binary tree using a levelorder traversal.
    //-----------------------------------------------------------------
    public Iterator<T> levelorder()
    {
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (!isEmpty())
        {
            queue.enqueue(rootIndex);
            while (!queue.isEmpty())
            {
                int currentIndex = queue.dequeue();

                if (currentIndex < tree.length && tree[currentIndex] != null)
                {
                    iter.add(tree[currentIndex]);

                    int leftIndex = 2 * currentIndex + 1;
                    int rightIndex = 2 * currentIndex + 2;

                    if (leftIndex < tree.length && tree[leftIndex] != null)
                        queue.enqueue(leftIndex);
                    if (rightIndex < tree.length && tree[rightIndex] != null)
                        queue.enqueue(rightIndex);
                }
            }
        }

        return iter;
    }

    //-----------------------------------------------------------------
    //  Satisfies the Iterable interface using an inorder traversal.
    //-----------------------------------------------------------------
    public Iterator<T> iterator()
    {
        return inorder();
    }
}

