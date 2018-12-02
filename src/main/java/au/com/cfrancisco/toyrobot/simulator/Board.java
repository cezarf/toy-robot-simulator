package au.com.cfrancisco.toyrobot.simulator;

/**
 * {@code Board} simulates a flat surface where the {@link ToyRobot}
 * is placed
 *
 * @author      Cezar Francisco
 * @version     1.0-SNAPSHOT
 * @since       1.0-SNAPSHOT
 *
 */
public class Board {

    /**
     * MIN_SIZE is the minimum size of a board that can be created
     */
    final int MIN_SIZE = 1;

    /**
     * Default sizeX (5) if not specified during construction
     */
    private int sizeX = 5;
    /**
     * Default sizeY (5) if not specified during construction
     */
    private int sizeY = 5;

    /**
     * Create default 5 X 5 board
     */
    public Board() {
    }

    /**
     * Cater for non-square boards (sizeX and sizeY are not the same)
     *
     * @param sizeX     X size of the board being created
     * @param sizeY     y size of the board being created
     */
    public Board(int sizeX, int sizeY) throws Exception {
        this.validateSize(sizeX, sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     *  Cater for square boards
     *
     * @param size      X/Y size of the board being created
     */
    public Board(int size) throws Exception {
        this.validateSize(size, size);
        this.sizeX = size;
        this.sizeY = size;
    }

    /**
     * Creates ToyRobot to be placed on the {@link Board}
     *
     * @return      Instance of {@link ToyRobot}
     */
    public ToyRobot createToyRobot(){
        return new ToyRobotImpl(this);
    }


    /**
     * Validates Board size
     *
     * @param sizeX         X size of the board being created
     * @param sizeY         Y size of the board being created
     * @throws Exception    Thrown if size is not valid
     */
    private void validateSize(int sizeX, int sizeY) throws Exception {
        if(sizeX < MIN_SIZE || sizeY < MIN_SIZE){
            throw new Exception(String.format("The minimum Board size is '%1$s X %1$s'", MIN_SIZE) );
        }

    }

    /**
     * Validates if position (X,Y) is within the {@link Board}'s dimension
     *
     * @param posX          Y position to validate
     * @param posY          X position to validate
     * @throws Exception    if X and Y position is outside the boards dimension
     */
    public void validatePosition(int posX, int posY) throws Exception {

        if(!(posX >= 0 && posX < this.sizeX && posY >= 0 && posY < sizeY)){
            throw new Exception(String.format("Invalid position: The robot will fall off the %1$sX%2$s Board.", this.sizeX, this.sizeY));
        }
    }

    /**
     * sizeX getter
     *
     * @return      sizeX of the Board
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * sizeY getter
     *
     * @return      sizeY of the Board
     */
    public int getSizeY() {
        return sizeY;
    }
}
