package au.com.cfrancisco.toyrobot.simulator.common;

import au.com.cfrancisco.toyrobot.simulator.ToyRobot;

/**
 * {@code Direction} provides enumeration of possible directions when controlling the {@link ToyRobot}
 *
 * @author      Cezar Francisco
 * @version     1.0-SNAPSHOT
 * @since       1.0-SNAPSHOT
 */
public enum Direction {
    NORTH(0)
    , EAST(1)
    , SOUTH(2)
    , WEST(3);

    int value;

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Return equivalent Direction for an int value.
     * This is used when rotating the {@link ToyRobot}
     *
     * @see ToyRobot#setDirection(int)
     *
     * @param value     int value of Direction
     * @return          Direction based on value parameter
     */
    public static Direction fromValue(int value) {
        switch(value) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
        }
        return null;
    }

}

