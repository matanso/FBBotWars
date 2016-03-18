/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.urctf.protocol;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

/**
 * A direction *
 */
public enum Direction implements org.apache.thrift.TEnum {
  UP(1),
  RIGHT(2),
  DOWN(3),
  LEFT(4);

  private final int value;

  private Direction(int value) {
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
  public static Direction findByValue(int value) { 
    switch (value) {
      case 1:
        return UP;
      case 2:
        return RIGHT;
      case 3:
        return DOWN;
      case 4:
        return LEFT;
      default:
        return null;
    }
  }
}