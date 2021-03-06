/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package thrift.tutorial;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum Operation implements org.apache.thrift.TEnum {
  ADD(1),
  SUBTRACT(2),
  MULTYPLY(3),
  DIVEDE(4);

  private final int value;

  private Operation(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static Operation findByValue(int value) { 
    switch (value) {
      case 1:
        return ADD;
      case 2:
        return SUBTRACT;
      case 3:
        return MULTYPLY;
      case 4:
        return DIVEDE;
      default:
        return null;
    }
  }
}
