package au.com.cfrancisco.toyrobot.simulator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the instantiation of {@link Board}
 *
 * @author      Cezar Francisco
 */
public class BoardTest {

    @Test
    public void testCreateDefaultBoard(){
        Board board = new Board();
        assertEquals("Default board sizeX", 5, board.getSizeX());
        assertEquals("Default board sizeY", 5, board.getSizeY());
    }

    /**
     *
     */
    @Test
    public void testCreateSquareBoard() throws Exception {
        Board board = new Board(4);
        assertEquals("Default board sizeX", 4, board.getSizeX());
        assertEquals("Default board sizeY", 4, board.getSizeY());

    }

    @Test
    public void testCreateNonSquareBoard() throws Exception {
        Board board = new Board(4,8);
        assertEquals("Default board sizeX", 4, board.getSizeX());
        assertEquals("Default board sizeY", 8, board.getSizeY());

    }

    @Test(expected = Exception.class)
    public void testIllegalDimension() throws Exception {
        Board board = new Board(-1,8);
    }


    @Test(expected = Exception.class)
    public void testCreateInvalidBoard() throws Exception {
        Board board = new Board(0);

    }

    @Test(expected = Exception.class)
    public void testValidatePosition() throws Exception {
        Board board = new Board();
        board.validatePosition(0,6);
        board.validatePosition(-1,6);

    }

}