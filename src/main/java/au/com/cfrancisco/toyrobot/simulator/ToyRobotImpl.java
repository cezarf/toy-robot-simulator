package au.com.cfrancisco.toyrobot.simulator;


import au.com.cfrancisco.toyrobot.simulator.common.Command;
import au.com.cfrancisco.toyrobot.simulator.common.Direction;

/**
 * {@code ToyRobotImpl} is the implementation of {@link ToyRobot}
 * It simulates a robot moving on a {@link Board}
 * where it is placed
 *
 * @author      Cezar Francisco
 * @version     1.0-SNAPSHOT
 * @since       1.0-SNAPSHOT
 *
 */
public class ToyRobotImpl implements ToyRobot {

    private int posX = 0;
    private int posY = 0;
    private Board board = null;
    private Direction direction = null;

    /**
     * Constructor for ToyRobotImpl
     *
     * @param board     {@link Board} where the toy robot is to be placed
     *
     * @see Board
     */
    public ToyRobotImpl(Board board) {
        this.board = board;
    }


    /**
     * Extracts, Validate and run the command from commandString parameter
     *
     * Accepts the following commandString parameters:
     *
     * PLACE X,Y,F
     * MOVE
     * LEFT
     * RIGHT
     * REPORT
     *
     * PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.
     * MOVE will move the toy robot one unit forward in the direction it is currently facing.
     * LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without
     * changing the position of the robot.
     * REPORT will announce the X, Y and F of the robot
     *
     * @param commandString     command to run
     *
     * @exception Exception     thrown when any invalid command is received
     */

    @Override
    public void runCommand(String commandString) throws Exception {

        String commandStr = commandString;

        if(commandStr.indexOf(COMMAND_SAPARATOR) >= 0) {
            commandStr = commandStr.substring(0, commandStr.indexOf(COMMAND_SAPARATOR));
        }

        Command command = null;
        try {
            command = Command.valueOf(commandStr.toUpperCase());
        }catch (Exception ex){
            throw new Exception(String.format("Ignored: Invalid Command [%1$s]", commandStr));
        }

       switch (command){
           case PLACE:

               String paramString = commandString.substring(commandString.indexOf(COMMAND_SAPARATOR) + 1);
               String[] paramArray = paramString.split(PARAM_SAPARATOR);

               int receivedPosX = 0;
               int receivedPosY = 0;
               Direction receivedDirection = null;
               try {
                   receivedPosX = Integer.parseInt(paramArray[0].trim());
                   receivedPosY = Integer.parseInt(paramArray[1].trim());
                   receivedDirection = Direction.valueOf(paramArray[2].trim().toUpperCase());
               }catch (Exception ex){
                   throw new Exception(String.format("Command Ignored: Invalid parameter values [%1$s]."
                    + "\n Try 'PLACE X,Y,F' where X and Y are a valid numbers(int) and F is valid direction (EAST,WEST,NORTH,SOUTH)", paramString));
               }
               this.place(receivedPosX, receivedPosY, receivedDirection);
               break;
           case MOVE:
               this.move();
               break;
           case LEFT:
               this.rotateLeft();
               break;
           case RIGHT:
               this.rotateRight();
               break;
           case REPORT:
               this.showReport();
               break;
       }

    }


    /**
     * Places the {@link ToyRobot} on the {@link Board}.
     * Ignores the command and prints message on screen if
     * any of the parameter is invalid or the position will cause the robot to
     * fall off the board.
     *
     * @param posX              X position to place the robot
     * @param posY              Y position to place the robot
     * @param direction         Facing the {@link Direction}
     *
     * @exception Exception     thrown when validation error has occurred
     */
    public void place(int posX, int posY, Direction direction) throws Exception {

        if(direction == null){
            throw new Exception("Direction must be specified.");
        }

        if(posX < 0 || posY < 0){
            throw new Exception("X, Y should not be less than 0.");
        }

        this.board.validatePosition(posX, posY);

        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }

    /**
     * Moves the {@link ToyRobot} one unit forward in the {@link Direction} it is currently facing.
     * Displays message and ignores command if move causes the robot to fall off the board
     * or if robot has not been <em>PLACE</>d on the board
     *
     * @exception Exception     thrown by {@see #board.validatePosition}
     *
     */
    public void move() throws Exception {
        if(!this.isPlaced()){
            System.out.println("MOVE ignored. ToyRobot must be 'PLACE'd on the board first.");
            return;
        }

        int xMoveIndex = 0;
        int yMoveIndex = 0;

        switch (this.direction.getValue()){
            case 0: //NORTH
                yMoveIndex = 1;
                break;
            case 1: //EAST
                xMoveIndex = 1;
                break;
            case 2: //SOUTH
                yMoveIndex = -1;
                break;
            case 3: //WEST
                xMoveIndex = -1;
                break;
        }

        this.board.validatePosition(this.posX + xMoveIndex, this.posY + yMoveIndex);

        this.posX = this.posX + xMoveIndex;
        this.posY = this.posY + yMoveIndex;

    }


    /**
     * Rotates the {@link ToyRobot} 90 degrees to the <em>left</em> where it is currently facing
     * without changing its position
     * Displays message and ignores command if robot has not been <em>PLACE</em>d on the board
     *
     * @exception Exception     thrown when toy Robot has not been {@see ToyRobotImpl#place} on the {@link Board}
     *
     */
    public void rotateLeft() throws Exception {
        if(!this.isPlaced()){
            throw new Exception("LEFT ignored. ToyRobot must be 'PLACE'd on the board first.");
        }
        setDirection(-1);

    }

    /**
     * Rotates the {@link ToyRobot} 90 degrees to the <em>right</em> where it is currently facing
     * without changing its position
     * Displays message and ignores command if robot has not been <em>PLACE</em>d on the board
     *
     * @exception Exception     thrown when toy Robot has not been {@see ToyRobotImpl#place} on the {@link Board}
     */
    public void rotateRight() throws Exception {
        if(!this.isPlaced()){
            throw new Exception("RIGHT ignored. ToyRobot must be 'PLACE'd on the board first.");
        }

        setDirection(1);

    }


    /**
     * Prints the current position and facing direction og the {@link ToyRobot}      *
     * Displays message and ignores command if robot has not been <em>PLACE</em>d on the board
     *
     * @exception Exception     thrown when toy Robot has not been {@see ToyRobotImpl#place} on the {@link Board}
     */
    public void showReport() throws Exception {
        if(!this.isPlaced()){
            throw new Exception("REPORT ignored. ToyRobot must be 'PLACE'd on the board first.");

        }
        System.out.println(String.format("%1$s,%2$s,%3$s", this.posX, this.posY, this.direction));
    }


    /**
     * Check if robot is place by checking if {@link #direction} is set
     *
     * @return      true if direction is not null
     */
    private boolean isPlaced(){
        return this.direction != null;
    }


    /**
     * Rotates the robot to the left/right depending on the direction index passed
     *
     * @param dirIndex  -1 rotates to the left, 1 rotates to the right
     *                  in the direction {@link ToyRobot} is currently facing
     */
    private void setDirection(int dirIndex){

        int newDirection = this.direction.getValue() + dirIndex;
        if(newDirection < 0){
            newDirection = 3; //Set to WEST when rotated left from NORTH
        }else if(newDirection > 3){
            newDirection = 0; //Set to NORTH when rotated right from WEST
        }

        this.direction = Direction.fromValue(newDirection);
    }


    /**
     * Getters/Setters
     */

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Direction getDirection() {
        return direction;
    }


}
