package au.com.cfrancisco.toyrobot.simulator;

import au.com.cfrancisco.toyrobot.simulator.common.Direction;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Test the instantiation and methods of {@link ToyRobotImpl}
 *
 * @author      Cezar Francisco
 */
public class ToyRobotImplTest extends TestCase {

    private Board board = new  Board();
    private ToyRobotImpl toyRobotImpl = new ToyRobotImpl(board);


    @Test
    public void testPlace() throws Exception {
        toyRobotImpl.place(3,4, Direction.EAST);
        assertEquals(Direction.EAST.getValue(), toyRobotImpl.getDirection().getValue());
        assertEquals(3, toyRobotImpl.getPosX());
        assertEquals(4, toyRobotImpl.getPosY());
    }

    @Test
    public void testInvalidPosition() {
        try {
            toyRobotImpl.place(8, 7, Direction.EAST);
            //Direction should not be set if position is invalid
            assertNull(toyRobotImpl.getDirection());
        }catch(Exception e){

        }
    }

    @Test
    public void testInvalidNegativePosition() throws Exception {
        try{
            toyRobotImpl.place(-1,0, Direction.EAST);
            //Direction should not be set if position is invalid
            assertNull(toyRobotImpl.getDirection());
        }catch(Exception e){

        }
    }


    @Test
    public void testNullDirection() {
        try {
            toyRobotImpl.place(3, 4, null);
        }catch(Exception ex) {}

        //Direction should not be set if position is invalid
        assertNull(toyRobotImpl.getDirection());
    }


    @Test
    public void testMove() throws Exception {
        toyRobotImpl.place(3,4, Direction.EAST);
        assertEquals(Direction.EAST, toyRobotImpl.getDirection());
        assertEquals(1, toyRobotImpl.getDirection().getValue());
        toyRobotImpl.move();
        assertEquals(4, toyRobotImpl.getPosX());
        assertEquals(4, toyRobotImpl.getPosY());
    }

    @Test
    public void testRotateLeft() throws Exception {
        toyRobotImpl.place(3,3, Direction.EAST);
        toyRobotImpl.rotateLeft();
        assertEquals(Direction.NORTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateLeft();
        assertEquals(Direction.WEST.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateLeft();
        assertEquals(Direction.SOUTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateLeft();
        assertEquals(Direction.EAST.getValue(), toyRobotImpl.getDirection().getValue());

    }

    @Test
    public void testRotateRight() throws Exception {
        toyRobotImpl.place(3,3, Direction.EAST);
        toyRobotImpl.rotateRight();
        assertEquals(Direction.SOUTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateRight();
        assertEquals(Direction.WEST.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateRight();
        assertEquals(Direction.NORTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.rotateRight();
        assertEquals(Direction.EAST.getValue(), toyRobotImpl.getDirection().getValue());

    }

    @Test
    public void testShowReport() throws Exception {
        toyRobotImpl.place(0,0, Direction.NORTH);
        toyRobotImpl.move();
        assertEquals(0, toyRobotImpl.getPosX());
        assertEquals(1, toyRobotImpl.getPosY());
        assertEquals(Direction.NORTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.showReport();

        toyRobotImpl.place(0,0, Direction.NORTH);
        toyRobotImpl.rotateLeft();
        assertEquals(0, toyRobotImpl.getPosX());
        assertEquals(0, toyRobotImpl.getPosY());
        assertEquals(Direction.WEST.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.showReport();

        toyRobotImpl.place(1,2, Direction.EAST);
        toyRobotImpl.move();
        toyRobotImpl.move();
        toyRobotImpl.rotateLeft();
        toyRobotImpl.move();
        assertEquals(3, toyRobotImpl.getPosX());
        assertEquals(3, toyRobotImpl.getPosY());
        assertEquals(Direction.NORTH.getValue(), toyRobotImpl.getDirection().getValue());
        toyRobotImpl.showReport();
    }

    @Test(expected = Test.None.class /* no exception expected */)
    public void testRunCommand() throws Exception {
        toyRobotImpl.runCommand("PLACE 0,0,EAST");
        toyRobotImpl.showReport();
        toyRobotImpl.runCommand("MOVE");
        toyRobotImpl.showReport();
        toyRobotImpl.runCommand("LEFT");
        toyRobotImpl.runCommand("MOVE");
        toyRobotImpl.showReport();
        toyRobotImpl.runCommand("RIGHT");
        toyRobotImpl.runCommand("MOVE");
        toyRobotImpl.showReport();
        assertEquals(2, toyRobotImpl.getPosX());
        assertEquals(1, toyRobotImpl.getPosY());
        assertEquals(Direction.EAST, toyRobotImpl.getDirection());
    }
}