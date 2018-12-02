package au.com.cfrancisco.toyrobot.simulator;


/**
 * {@code ToyRobot} is the interface for {@link ToyRobotImpl}
 *
 * @author      Cezar Francisco
 * @version     1.0-SNAPSHOT
 * @since       1.0-SNAPSHOT
 *
 */
public interface ToyRobot {

    /**
     * Command and parameter separator
     */
    String COMMAND_SAPARATOR = " ";

    /**
     * Command parameter separator
     */
    String PARAM_SAPARATOR = ",";

    void runCommand(String commandString) throws Exception;
}
