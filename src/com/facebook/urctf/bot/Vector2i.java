package com.facebook.urctf.bot;

/**
 * A simple class to represent 2-dimensional vectors with integer coordinates
 */
public class Vector2i {

    /** X coordinate */
    public int x;

    /** Y coordinate */
    public int y;

    /** A single square up */
    public static final Vector2i UP = new Vector2i(0, -1);

    /** A single square to the right */
    public static final Vector2i RIGHT = new Vector2i(1, 0);

    /** A single square down */
    public static final Vector2i DOWN = new Vector2i(0, 1);

    /** A single square to the left */
    public static final Vector2i LEFT = new Vector2i(-1, 0);

    /** Initialized a zero vector */
    public Vector2i() {
        this(0, 0);
    }

    /**
     * Constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs == null) {
            return false;
        }
        if (rhs == this) {
            return true;
        }
        if (!(rhs instanceof Vector2i)) {
            return false;
        }

        return equals((Vector2i)rhs);
    }

    public boolean equals(Vector2i rhs) {
        return x == rhs.x && y == rhs.y;
    }

    @Override
    public int hashCode() {
        // technique is ripped off from boost::hash_combine
        int seed = x;
        seed ^= y + 0x9e3779b9 + (seed << 6) + (seed >> 2);
        return seed;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
    }
}
