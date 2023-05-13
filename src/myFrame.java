import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class myFrame extends JFrame implements ActionListener, KeyListener {

    private int [][] board;
    private final int [][] level;
    //ask why level is updating along with board
    private final JPanel game;
    private final JButton up;
    private final JButton left;
    private final JButton down;
    private final JButton right;
    private final JButton reset;
    private final JPanel buttons;
    private int playerX;
    private int playerY;
    private int originalX;
    private int originalY;
    myFrame(int[][] b, int playerXCoordinate, int playerYCoordinate){
        board = b;
        playerX = playerXCoordinate;
        playerY = playerYCoordinate;
        originalX = playerXCoordinate;
        originalY = playerYCoordinate;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        level = b;
        game = new JPanel();
        game.setLayout(null);

        this.setName("Sokoban");
        game.setSize(board[0].length*50 + 20,board.length*50);
        this.setSize(board[0].length*50 + 20,board.length*50 + 200);
        this.setResizable(false);

        updateBoard();
        printBoard();

        buttons = new JPanel(null);
        buttons.setSize(150,100);

        up = new JButton("↑");
        down = new JButton("↓");
        left = new JButton("←");
        right = new JButton("→");
        reset = new JButton("R");

        up.addActionListener(this);
        left.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);
        reset.addActionListener(this);
        this.addKeyListener(this);

        buttons.add(up);
        buttons.add(down);
        buttons.add(left);
        buttons.add(right);
        buttons.add(reset);
        this.add(game);

        up.setBounds(50,0,50,50);
        left.setBounds(0,50,50,50);
        down.setBounds(50,50,50,50);
        right.setBounds(100,50,50,50);
        reset.setBounds(0,0,50,50);

        this.add(buttons);
        buttons.setBounds(0,board.length * 50,150,100);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();

    }

    myFrame(int x, int y, int playerXCoord, int playerYCoord) {
        board = new int[y][x];
        playerX = playerXCoord;
        playerY = playerYCoord;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        level = new int[y][x];

        game = new JPanel();
        game.setLayout(null);

        board[1][1] = 2;
        board[1][3] = 2;
        board[3][1] = 4;
        board[3][3] = 4;

        this.setName("Sokoban");
        game.setSize(board[0].length*50 + 20,board.length*50);
        this.setSize(board[0].length*50 + 20,board.length*50 + 200);
        this.setResizable(false);

        updateBoard();
        printBoard();

        buttons = new JPanel(null);
        buttons.setSize(150,100);

        up = new JButton("↑");
        down = new JButton("↓");
        left = new JButton("←");
        right = new JButton("→");
        reset = new JButton("R");

        up.addActionListener(this);
        left.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);
        reset.addActionListener(this);
        this.addKeyListener(this);

        buttons.add(up);
        buttons.add(down);
        buttons.add(left);
        buttons.add(right);
        buttons.add(reset);
        this.add(game);

        up.setBounds(50,0,50,50);
        left.setBounds(0,50,50,50);
        down.setBounds(50,50,50,50);
        right.setBounds(100,50,50,50);
        reset.setBounds(0,0,50,50);

        this.add(buttons);
        buttons.setBounds(0,y * 50,150,100);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
    }
    public boolean checkWin(){
        boolean win = true;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if (board[j][i] == 4) {
                    win = false;
                    break;
                }
            }
        }
        return win;
    }
    public void printBoard(){
        for(int[] a: board){
            for(int b: a){
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println("player's X = " + playerX + "\nplayer's Y = " + playerY);
    }

    public void makeEmptyTile(int xCoord, int yCoord){
        JPanel tile = new JPanel();
        game.add(tile);
        tile.setBounds(xCoord,yCoord,50,50);
        tile.setOpaque(true);
        tile.setBackground(Color.lightGray);
    }
    public void makePlayerTile(int xCoord, int yCoord){
        ImageIcon player = new ImageIcon("Red_50x50.png");
        JLabel label = new JLabel();
        game.add(label);
        label.setBounds(xCoord,yCoord,50,50);
        label.setIcon(player);
        label.setBackground(Color.lightGray);
    }
    public void makeBoxTile(int xCoord, int yCoord){
        ImageIcon box = new ImageIcon("box.jpg");
        JLabel label = new JLabel();
        game.add(label);
        label.setBounds(xCoord,yCoord,50,50);
        label.setIcon(box);
    }
    public void makeWallTile(int xCoord, int yCoord){
        ImageIcon wall = new ImageIcon("wall.jpg");
        JLabel label = new JLabel();
        game.add(label);
        label.setBounds(xCoord,yCoord,50,50);
        label.setIcon(wall);
    }
    public void makeGoalTile(int xCoord, int yCoord){
        ImageIcon flag = new ImageIcon("star.jpg");
        JLabel label = new JLabel();
        game.add(label);
        label.setBounds(xCoord,yCoord,50,50);
        label.setIcon(flag);
    }
    /*public void makePlayerGoalTile(int xCoord, int yCoord){
        ImageIcon player = new ImageIcon("mogusyellowtint.png");
        JLabel label = new JLabel();
        game.add(label);
        label.setBounds(xCoord,yCoord,50,50);
        label.setIcon(player);
    }*/
    public void makeBoxGoalTile(int xCoord, int yCoord){
        ImageIcon box = new ImageIcon("boxyellowtint.png");
        JLabel label = new JLabel();
        game.add(label);
        label.setBounds(xCoord,yCoord,50,50);
        label.setIcon(box);
    }
    public void updateBoard(){
        game.removeAll();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(i == playerY && j == playerX){
                    makePlayerTile(j*50,i*50);
                }
                else if(board[i][j] == 0) {//air tile
                    makeEmptyTile(j*50, i*50);
                }
                /*else if(board[j][i] == 1){//player tile
                    makePlayerTile(j*50,i*50);
                }*/
                else if(board[i][j] == 2){//box tile
                    makeBoxTile(j*50,i*50);
                }
                else if(board[i][j] == 3){//wall tile
                    makeWallTile(j*50,i*50);
                }
                else if(board[i][j] == 4){//goal tile
                    makeGoalTile(j*50,i*50);
                }
                else if(board[i][j] == 5) {//box on goal tile
                    makeBoxGoalTile(j*50,i*50);
                }
                else if(board[i][j] == 6) {//player on goal tile
                    makeBoxGoalTile(j*50,i*50);
                }
            }
        }
        if(checkWin()){
            //JPanel winScreen = new JPanel();
            JLabel winMessage = new JLabel("You Win!");
            ImageIcon winIcon = new ImageIcon("star.jpg");
            //winScreen.setSize(game.getWidth(),game.getHeight());
            winMessage.setFont(new Font("Cambria", Font.BOLD,25));
            winMessage.setIcon(winIcon);
            this.add(winMessage);
            winMessage.setBounds(200,game.getHeight(), 200, 100);
        }
        this.requestFocus();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == up && playerY >=1 && board[playerY - 1][playerX] != 3){
            if(board[playerY - 1][playerX] == 2){
                if(playerY - 2 >= 0 && (board[playerY - 2][playerX] == 0 || board[playerY - 2][playerX] == 4)){
                    if(board[playerY - 2][playerX] == 4){
                        board[playerY - 2][playerX] = 5;
                        board[playerY - 1][playerX] = 0;
                        playerY--;
                        printBoard();
                        updateBoard();
                    }
                    else{
                        board[playerY - 2][playerX] = 2;
                        board[playerY - 1][playerX] = 0;
                        playerY--;
                        printBoard();
                        updateBoard();
                    }
                }
            }
            else if(board[playerY - 1][playerX] == 5){
                if(playerY - 2 >= 0 && board[playerY - 2][playerX] == 0 ){
                    board[playerY - 2][playerX] = 2;
                    board[playerY - 1][playerX] = 4;
                    playerY--;
                    printBoard();
                    updateBoard();
                }
                if(playerY - 2 >= 0 && board[playerY - 2][playerX] == 4 ){
                    board[playerY - 2][playerX] = 5;
                    board[playerY - 1][playerX] = 4;
                    playerY--;
                    printBoard();
                    updateBoard();
                }
            }
            else{
                System.out.println("up");
                playerY--;
                printBoard();
                updateBoard();
            }
        }
        if(e.getSource() == left && playerX >=1 && board[playerY][playerX - 1] != 3){
            if(board[playerY][playerX - 1] == 2){
                if(playerX - 2 >= 0 && (board[playerY][playerX - 2] == 0 || board[playerY][playerX - 2] == 4)){
                    if(board[playerY][playerX - 2] == 4){
                        board[playerY][playerX - 2] = 5;
                        board[playerY][playerX - 1] = 0;
                        playerX--;
                        printBoard();
                        updateBoard();
                    }
                    else{
                        board[playerY][playerX - 2] = 2;
                        board[playerY][playerX - 1] = 0;
                        playerX--;
                        printBoard();
                        updateBoard();
                    }
                }
            }
            else if(board[playerY][playerX - 1] == 5){
                if(playerX - 2 >= 0 && board[playerY][playerX - 2] == 0 ){
                    board[playerY][playerX - 2] = 2;
                    board[playerY][playerX - 1] = 4;
                    playerX--;
                    printBoard();
                    updateBoard();
                }
                if(playerY - 2 >= 0 && board[playerY - 2][playerX] == 4 ){
                    board[playerY][playerX - 2] = 5;
                    board[playerY][playerX - 1] = 4;
                    playerX--;
                    printBoard();
                    updateBoard();
                }
            }
            else{
                System.out.println("left");
                playerX--;
                printBoard();
                updateBoard();
            }
        }
        if(e.getSource() == down && playerY < board.length - 1 && board[playerY + 1][playerX] != 3){
            if(board[playerY + 1][playerX] == 2){
                if(playerY + 2 < board.length && (board[playerY + 2][playerX] == 0 || board[playerY + 2][playerX] == 4)){
                    if(board[playerY + 2][playerX] == 4){
                        board[playerY + 2][playerX] = 5;
                        board[playerY + 1][playerX] = 0;
                        playerY++;
                        printBoard();
                        updateBoard();
                    }
                    else{
                        board[playerY + 2][playerX] = 2;
                        board[playerY+ 1][playerX] = 0;
                        playerY++;
                        printBoard();
                        updateBoard();
                    }
                }
            }
            else if(board[playerY + 1][playerX] == 5){
                if(playerY + 2 < board.length && board[playerY + 2][playerX] == 0 ){
                    board[playerY + 2][playerX] = 2;
                    board[playerY + 1][playerX] = 4;
                    playerY++;
                    printBoard();
                    updateBoard();
                }
                if(playerY + 2 < board.length && board[playerY - 2][playerX] == 4 ){
                    board[playerY + 2][playerX] = 5;
                    board[playerY + 1][playerX] = 4;
                    playerY++;
                    printBoard();
                    updateBoard();
                }
            }
            else{
                System.out.println("down");
                playerY++;
                printBoard();
                updateBoard();
            }
        }
        if(e.getSource() == right && playerX < board[playerY].length - 1 && board[playerY][playerX + 1] != 3){
            if(board[playerY][playerX + 1] == 2){
                if(playerX + 2 < board.length && (board[playerY][playerX + 2] == 0 || board[playerY][playerX + 2] == 4)){
                    if(board[playerY][playerX + 2] == 4){
                        board[playerY][playerX + 2] = 5;
                        board[playerY][playerX + 1] = 0;
                        playerX++;
                        printBoard();
                        updateBoard();
                    }
                    else{
                        board[playerY][playerX + 2] = 2;
                        board[playerY][playerX + 1] = 0;
                        playerX++;
                        printBoard();
                        updateBoard();
                    }
                }
            }
            else if(board[playerY][playerX + 1] == 5){
                if(playerX + 2 < board.length && board[playerY][playerX + 2] == 0 ){
                    board[playerY][playerX + 2] = 2;
                    board[playerY][playerX + 1] = 4;
                    playerX++;
                    printBoard();
                    updateBoard();
                }
                if(playerY + 2 < board.length && board[playerY - 2][playerX] == 4 ){
                    board[playerY][playerX + 2] = 5;
                    board[playerY][playerX + 1] = 4;
                    playerX++;
                    printBoard();
                    updateBoard();
                }
            }
            else{
                System.out.println("right");
                playerX++;
                printBoard();
                updateBoard();
            }
        }
        else if(e.getSource() == reset){
            board = level;
            playerX = originalX;
            playerY = originalY;
            printBoard();
            updateBoard();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 38 && playerY >=1 && board[playerY - 1][playerX] != 3){
            if(board[playerY - 1][playerX] == 2){
                if(playerY - 2 >= 0 && (board[playerY - 2][playerX] == 0 || board[playerY - 2][playerX] == 4)){
                    if(board[playerY - 2][playerX] == 4){
                        board[playerY - 2][playerX] = 5;
                        board[playerY - 1][playerX] = 0;
                        playerY--;
                        printBoard();
                        updateBoard();
                    }
                    else{
                        board[playerY - 2][playerX] = 2;
                        board[playerY - 1][playerX] = 0;
                        playerY--;
                        printBoard();
                        updateBoard();
                    }
                }
            }
            else if(board[playerY - 1][playerX] == 5){
                if(playerY - 2 >= 0 && board[playerY - 2][playerX] == 0 ){
                    board[playerY - 2][playerX] = 2;
                    board[playerY - 1][playerX] = 4;
                    playerY--;
                    printBoard();
                    updateBoard();
                }
                if(playerY - 2 >= 0 && board[playerY - 2][playerX] == 4 ){
                    board[playerY - 2][playerX] = 5;
                    board[playerY - 1][playerX] = 4;
                    playerY--;
                    printBoard();
                    updateBoard();
                }
            }
            else{
                System.out.println("up");
                playerY--;
                printBoard();
                updateBoard();
            }
        }
        if(e.getKeyCode() == 37 && playerX >=1 && board[playerY][playerX - 1] != 3){
            if(board[playerY][playerX - 1] == 2){
                if(playerX - 2 >= 0 && (board[playerY][playerX - 2] == 0 || board[playerY][playerX - 2] == 4)){
                    if(board[playerY][playerX - 2] == 4){
                        board[playerY][playerX - 2] = 5;
                        board[playerY][playerX - 1] = 0;
                        playerX--;
                        printBoard();
                        updateBoard();
                    }
                    else{
                        board[playerY][playerX - 2] = 2;
                        board[playerY][playerX - 1] = 0;
                        playerX--;
                        printBoard();
                        updateBoard();
                    }
                }
            }
            else if(board[playerY][playerX - 1] == 5){
                if(playerX - 2 >= 0 && board[playerY][playerX - 2] == 0 ){
                    board[playerY][playerX - 2] = 2;
                    board[playerY][playerX - 1] = 4;
                    playerX--;
                    printBoard();
                    updateBoard();
                }
                if(playerY - 2 >= 0 && board[playerY - 2][playerX] == 4 ){
                    board[playerY][playerX - 2] = 5;
                    board[playerY][playerX - 1] = 4;
                    playerX--;
                    printBoard();
                    updateBoard();
                }
            }
            else{
                System.out.println("left");
                playerX--;
                printBoard();
                updateBoard();
            }
        }
        if(e.getKeyCode() == 40 && playerY < board.length - 1 && board[playerY + 1][playerX] != 3){
            if(board[playerY + 1][playerX] == 2){
                if(playerY + 2 < board.length && (board[playerY + 2][playerX] == 0 || board[playerY + 2][playerX] == 4)){
                    if(board[playerY + 2][playerX] == 4){
                        board[playerY + 2][playerX] = 5;
                        board[playerY + 1][playerX] = 0;
                        playerY++;
                        printBoard();
                        updateBoard();
                    }
                    else{
                        board[playerY + 2][playerX] = 2;
                        board[playerY+ 1][playerX] = 0;
                        playerY++;
                        printBoard();
                        updateBoard();
                    }
                }
            }
            else if(board[playerY + 1][playerX] == 5){
                if(playerY + 2 < board.length && board[playerY + 2][playerX] == 0 ){
                    board[playerY + 2][playerX] = 2;
                    board[playerY + 1][playerX] = 4;
                    playerY++;
                    printBoard();
                    updateBoard();
                }
                if(playerY + 2 < board.length && board[playerY - 2][playerX] == 4 ){
                    board[playerY + 2][playerX] = 5;
                    board[playerY + 1][playerX] = 4;
                    playerY++;
                    printBoard();
                    updateBoard();
                }
            }
            else{
                System.out.println("down");
                playerY++;
                printBoard();
                updateBoard();
            }
        }
        if(e.getKeyCode() == 39 && playerX < board[playerY].length - 1 && board[playerY][playerX + 1] != 3){
            if(board[playerY][playerX + 1] == 2){
                if(playerX + 2 < board.length && (board[playerY][playerX + 2] == 0 || board[playerY][playerX + 2] == 4)){
                    if(board[playerY][playerX + 2] == 4){
                        board[playerY][playerX + 2] = 5;
                        board[playerY][playerX + 1] = 0;
                        playerX++;
                        printBoard();
                        updateBoard();
                    }
                    else{
                        board[playerY][playerX + 2] = 2;
                        board[playerY][playerX + 1] = 0;
                        playerX++;
                        printBoard();
                        updateBoard();
                    }
                }
            }
            else if(board[playerY][playerX + 1] == 5){
                if(playerX + 2 < board.length && board[playerY][playerX + 2] == 0 ){
                    board[playerY][playerX + 2] = 2;
                    board[playerY][playerX + 1] = 4;
                    playerX++;
                    printBoard();
                    updateBoard();
                }
                if(playerY + 2 < board.length && board[playerY - 2][playerX] == 4 ){
                    board[playerY][playerX + 2] = 5;
                    board[playerY][playerX + 1] = 4;
                    playerX++;
                    printBoard();
                    updateBoard();
                }
            }
            else{
                System.out.println("right");
                playerX++;
                printBoard();
                updateBoard();
            }
        }
        if(e.getKeyCode() == 82){
            board = level;
            playerX = originalX;
            playerY = originalY;
            printBoard();
            updateBoard();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
