package au.com.cfrancisco.toyrobot;

import static org.junit.Assert.assertTrue;

import au.com.cfrancisco.toyrobot.simulator.Board;
import au.com.cfrancisco.toyrobot.simulator.ToyRobot;
import org.junit.Test;

import java.util.*;


/**
 * Test running sequential commands from {@link App}
 *
 * @author      Cezar Francisco
 */

public class AppTest {

    private List<String[]> getTestData(){
        List<String[]> data = new ArrayList<String[]>();

        data.add(new String[]  {
                "PLACE 324234234243234,324213412341234,EAST"
                , "PLACE -12312,-23432,EAST"
                , "PLACE 0,0,INVALID-DIRECTION"
        });

        data.add(new String[]  {
                "PLACE 0,0,NORTH"
                , "MOVE"
                , "REPORT"
        });

        data.add(new String[]  {
                "PLACE 0,0,NORTH"
                , "LEFT"
                , "REPORT"
        });

        data.add(new String[]  {
                "PLACE 1,2,EAST"
                , "MOVE"
                , "MOVE"
                , "LEFT"
                , "MOVE"
                , "REPORT"
        });

        return data;

    }

    @Test
    public void runCommand(){

        this.getTestData().forEach((i)->{
            Board board = new  Board();
            ToyRobot toyRobot = board.createToyRobot();

            Arrays.asList(i).forEach((j) ->{
                System.out.println("running command string : " + j);
                try {
                    toyRobot.runCommand(j);
                }catch(Exception e) {

                }
            });
        });

    }

}
