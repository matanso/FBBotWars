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
 * A type of square *
 */
public enum Terrain implements org.apache.thrift.TEnum {
  /**
   * Normal square (white)
   */
  NORMAL(1),
  /**
   * Obstacle (blocked) square (grey)
   */
  OBSTACLE(2),
  /**
   * Defenser advantage (green)
   */
  DEF_ADV(3),
  /**
   * Attacker advantage (red)
   */
  ATT_ADV(4);

  private final int value;

  private Terrain(int value) {
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
  public static Terrain findByValue(int value) { 
    switch (value) {
      case 1:
        return NORMAL;
      case 2:
        return OBSTACLE;
      case 3:
        return DEF_ADV;
      case 4:
        return ATT_ADV;
      default:
        return null;
    }
  }
}