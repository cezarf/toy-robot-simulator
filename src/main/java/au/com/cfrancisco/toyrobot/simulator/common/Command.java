package au.com.cfrancisco.toyrobot.simulator.common;


import au.com.cfrancisco.toyrobot.simulator.ToyRobot;

/**
 *{@code Command} provides enumeration of possible commands when controlling the {@link ToyRobot}
 *
 * @author      Cezar Francisco
 * @version     1.0-SNAPSHOT
 * @since       1.0-SNAPSHOT
 */
public enum Command {

    PLACE("PLACE")
    , MOVE("MOVE")
    , LEFT("LEFT")
    , RIGHT("RIGHT")
    , REPORT("REPORT");

    String value;

    private Command(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

}
