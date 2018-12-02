package au.com.cfrancisco.toyrobot;

import au.com.cfrancisco.toyrobot.simulator.Board;
import au.com.cfrancisco.toyrobot.simulator.ToyRobot;

import java.util.Scanner;

/**
 * {@code App} is a simulator to control {@link ToyRobot} placed on {@link Board}
 *
 * @author      Cezar Francisco
 * @version     1.0-SNAPSHOT
 * @since       1.0-SNAPSHOT
 *
 */
public class App {

    public static void main( String[] args ) {
        boolean exit = false;
        String command = "";
        Board board = new Board();
        ToyRobot toyRobot = board.createToyRobot();

        Scanner in = new Scanner(System.in);

        System.out.println("Toy Robot Simulator: Control the Toy Robot using any of the following commands: 'PLACE X,Y,NORTH', 'MOVE', 'LEFT', 'RIGHT', 'REPORT' OR 'EXIT' to end simulation.");

        while(!exit){

            command = in.nextLine();

            if(command.equalsIgnoreCase("exit")){
                exit = true;
                break;
            }

            try {
                toyRobot.runCommand(command);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
}
