/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.urctf.protocol;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
/**
 * A bot's reply to a game turn
 */
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-03-17")
public class Move implements org.apache.thrift.TBase<Move, Move._Fields>, java.io.Serializable, Cloneable, Comparable<Move> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Move");

  private static final org.apache.thrift.protocol.TField REINFORCEMENTS_FIELD_DESC = new org.apache.thrift.protocol.TField("reinforcements", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField ACTIONS_FIELD_DESC = new org.apache.thrift.protocol.TField("actions", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new MoveStandardSchemeFactory());
    schemes.put(TupleScheme.class, new MoveTupleSchemeFactory());
  }

  /**
   * List of reinforcement instructions
   */
  public List<Reinforcement> reinforcements; // required
  /**
   * List of move instructions
   */
  public List<Action> actions; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * List of reinforcement instructions
     */
    REINFORCEMENTS((short)1, "reinforcements"),
    /**
     * List of move instructions
     */
    ACTIONS((short)2, "actions");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // REINFORCEMENTS
          return REINFORCEMENTS;
        case 2: // ACTIONS
          return ACTIONS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REINFORCEMENTS, new org.apache.thrift.meta_data.FieldMetaData("reinforcements", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Reinforcement.class))));
    tmpMap.put(_Fields.ACTIONS, new org.apache.thrift.meta_data.FieldMetaData("actions", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Action.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Move.class, metaDataMap);
  }

  public Move() {
  }

  public Move(
    List<Reinforcement> reinforcements,
    List<Action> actions)
  {
    this();
    this.reinforcements = reinforcements;
    this.actions = actions;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Move(Move other) {
    if (other.isSetReinforcements()) {
      List<Reinforcement> __this__reinforcements = new ArrayList<Reinforcement>(other.reinforcements.size());
      for (Reinforcement other_element : other.reinforcements) {
        __this__reinforcements.add(new Reinforcement(other_element));
      }
      this.reinforcements = __this__reinforcements;
    }
    if (other.isSetActions()) {
      List<Action> __this__actions = new ArrayList<Action>(other.actions.size());
      for (Action other_element : other.actions) {
        __this__actions.add(new Action(other_element));
      }
      this.actions = __this__actions;
    }
  }

  public Move deepCopy() {
    return new Move(this);
  }

  @Override
  public void clear() {
    this.reinforcements = null;
    this.actions = null;
  }

  public int getReinforcementsSize() {
    return (this.reinforcements == null) ? 0 : this.reinforcements.size();
  }

  public java.util.Iterator<Reinforcement> getReinforcementsIterator() {
    return (this.reinforcements == null) ? null : this.reinforcements.iterator();
  }

  public void addToReinforcements(Reinforcement elem) {
    if (this.reinforcements == null) {
      this.reinforcements = new ArrayList<Reinforcement>();
    }
    this.reinforcements.add(elem);
  }

  /**
   * List of reinforcement instructions
   */
  public List<Reinforcement> getReinforcements() {
    return this.reinforcements;
  }

  /**
   * List of reinforcement instructions
   */
  public Move setReinforcements(List<Reinforcement> reinforcements) {
    this.reinforcements = reinforcements;
    return this;
  }

  public void unsetReinforcements() {
    this.reinforcements = null;
  }

  /** Returns true if field reinforcements is set (has been assigned a value) and false otherwise */
  public boolean isSetReinforcements() {
    return this.reinforcements != null;
  }

  public void setReinforcementsIsSet(boolean value) {
    if (!value) {
      this.reinforcements = null;
    }
  }

  public int getActionsSize() {
    return (this.actions == null) ? 0 : this.actions.size();
  }

  public java.util.Iterator<Action> getActionsIterator() {
    return (this.actions == null) ? null : this.actions.iterator();
  }

  public void addToActions(Action elem) {
    if (this.actions == null) {
      this.actions = new ArrayList<Action>();
    }
    this.actions.add(elem);
  }

  /**
   * List of move instructions
   */
  public List<Action> getActions() {
    return this.actions;
  }

  /**
   * List of move instructions
   */
  public Move setActions(List<Action> actions) {
    this.actions = actions;
    return this;
  }

  public void unsetActions() {
    this.actions = null;
  }

  /** Returns true if field actions is set (has been assigned a value) and false otherwise */
  public boolean isSetActions() {
    return this.actions != null;
  }

  public void setActionsIsSet(boolean value) {
    if (!value) {
      this.actions = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case REINFORCEMENTS:
      if (value == null) {
        unsetReinforcements();
      } else {
        setReinforcements((List<Reinforcement>)value);
      }
      break;

    case ACTIONS:
      if (value == null) {
        unsetActions();
      } else {
        setActions((List<Action>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case REINFORCEMENTS:
      return getReinforcements();

    case ACTIONS:
      return getActions();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case REINFORCEMENTS:
      return isSetReinforcements();
    case ACTIONS:
      return isSetActions();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Move)
      return this.equals((Move)that);
    return false;
  }

  public boolean equals(Move that) {
    if (that == null)
      return false;

    boolean this_present_reinforcements = true && this.isSetReinforcements();
    boolean that_present_reinforcements = true && that.isSetReinforcements();
    if (this_present_reinforcements || that_present_reinforcements) {
      if (!(this_present_reinforcements && that_present_reinforcements))
        return false;
      if (!this.reinforcements.equals(that.reinforcements))
        return false;
    }

    boolean this_present_actions = true && this.isSetActions();
    boolean that_present_actions = true && that.isSetActions();
    if (this_present_actions || that_present_actions) {
      if (!(this_present_actions && that_present_actions))
        return false;
      if (!this.actions.equals(that.actions))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_reinforcements = true && (isSetReinforcements());
    list.add(present_reinforcements);
    if (present_reinforcements)
      list.add(reinforcements);

    boolean present_actions = true && (isSetActions());
    list.add(present_actions);
    if (present_actions)
      list.add(actions);

    return list.hashCode();
  }

  @Override
  public int compareTo(Move other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetReinforcements()).compareTo(other.isSetReinforcements());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReinforcements()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.reinforcements, other.reinforcements);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetActions()).compareTo(other.isSetActions());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetActions()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.actions, other.actions);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Move(");
    boolean first = true;

    sb.append("reinforcements:");
    if (this.reinforcements == null) {
      sb.append("null");
    } else {
      sb.append(this.reinforcements);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("actions:");
    if (this.actions == null) {
      sb.append("null");
    } else {
      sb.append(this.actions);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (reinforcements == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'reinforcements' was not present! Struct: " + toString());
    }
    if (actions == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'actions' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class MoveStandardSchemeFactory implements SchemeFactory {
    public MoveStandardScheme getScheme() {
      return new MoveStandardScheme();
    }
  }

  private static class MoveStandardScheme extends StandardScheme<Move> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Move struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // REINFORCEMENTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.reinforcements = new ArrayList<Reinforcement>(_list0.size);
                Reinforcement _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new Reinforcement();
                  _elem1.read(iprot);
                  struct.reinforcements.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setReinforcementsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ACTIONS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list3 = iprot.readListBegin();
                struct.actions = new ArrayList<Action>(_list3.size);
                Action _elem4;
                for (int _i5 = 0; _i5 < _list3.size; ++_i5)
                {
                  _elem4 = new Action();
                  _elem4.read(iprot);
                  struct.actions.add(_elem4);
                }
                iprot.readListEnd();
              }
              struct.setActionsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Move struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.reinforcements != null) {
        oprot.writeFieldBegin(REINFORCEMENTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.reinforcements.size()));
          for (Reinforcement _iter6 : struct.reinforcements)
          {
            _iter6.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.actions != null) {
        oprot.writeFieldBegin(ACTIONS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.actions.size()));
          for (Action _iter7 : struct.actions)
          {
            _iter7.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MoveTupleSchemeFactory implements SchemeFactory {
    public MoveTupleScheme getScheme() {
      return new MoveTupleScheme();
    }
  }

  private static class MoveTupleScheme extends TupleScheme<Move> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Move struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.reinforcements.size());
        for (Reinforcement _iter8 : struct.reinforcements)
        {
          _iter8.write(oprot);
        }
      }
      {
        oprot.writeI32(struct.actions.size());
        for (Action _iter9 : struct.actions)
        {
          _iter9.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Move struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list10 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.reinforcements = new ArrayList<Reinforcement>(_list10.size);
        Reinforcement _elem11;
        for (int _i12 = 0; _i12 < _list10.size; ++_i12)
        {
          _elem11 = new Reinforcement();
          _elem11.read(iprot);
          struct.reinforcements.add(_elem11);
        }
      }
      struct.setReinforcementsIsSet(true);
      {
        org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.actions = new ArrayList<Action>(_list13.size);
        Action _elem14;
        for (int _i15 = 0; _i15 < _list13.size; ++_i15)
        {
          _elem14 = new Action();
          _elem14.read(iprot);
          struct.actions.add(_elem14);
        }
      }
      struct.setActionsIsSet(true);
    }
  }

}

