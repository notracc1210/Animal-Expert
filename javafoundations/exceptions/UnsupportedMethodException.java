/**
 * UnsupportedMethodException.java 
 * To be used when a method is not implemented (yet)
 */

package javafoundations.exceptions;

public class UnsupportedMethodException extends RuntimeException
{
  /**
   * Constructor. Creates an exception with the provided message
   * @param The message to be presented when the exception is thrown
   **/
  public UnsupportedMethodException (String message)
  {
    super (message);
  }
}

