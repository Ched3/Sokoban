import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class myFrame extends JFrame implements ActionListener, KeyListener {
    private JButton level1;
    private JButton level2;
    private JButton level3;
    private JButton level4;
    private JButton level5;
    private JButton level6;
    private JButton level7;
    private JButton level8;
    private JButton level9;
    private JButton level10;
    private JButton back;
    private JButton levelBack;
    private JLabel winMessage;
    private int [][] board;
    private int [][] level;
    private JPanel levels;
    private JLabel sokoban;
    private JButton start;
    private JPanel game;
    private JButton up;
    private JButton left;
    private JButton down;
    private JButton right;
    private JButton reset;
    private JPanel buttons;
    private int playerX;
    private int playerY;
    private int originalX;
    private int originalY;
    myFrame(){
        makeStartScreen();
    }
    public void makeStartScreen(){
        this.getContentPane().removeAll();
        this.repaint();
        sokoban = new JLabel("Sokoban");
        start = new JButton("Play");

        start.addActionListener(this);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        sokoban.setBounds(60,50,400,100);
        sokoban.setFont(new Font("Cambria",Font.BOLD,85));
        sokoban.setForeground(Color.blue);
        start.setBounds(170,200,100,50);
        start.addActionListener(this);


        this.add(start);
        this.add(sokoban);
        this.setVisible(true);
    }
    public void makeLevelSelectScreen(){
        levels = new JPanel();
        this.getContentPane().removeAll();
        this.repaint();
        sokoban.setText("Level Selector");
        this.setSize(500,500);
        sokoban.setBounds(75,5,400,100);
        sokoban.setFont(new Font("Cambria",Font.BOLD,50));
        levels.setLayout(null);
        levels.setBounds(0,150,500,400);
        {level1 = new JButton("1");
        level2 = new JButton("2");
        level3 = new JButton("3");
        level4 = new JButton("4");
        level5 = new JButton("5");
        level6 = new JButton("6");
        level7 = new JButton("7");
        level8 = new JButton("8");
        level9 = new JButton("9");
        level10 = new JButton("10");
        back = new JButton("back");

        level1.setBounds(50,0,60,60);
        level2.setBounds(130,0,60,60);
        level3.setBounds(210,0,60,60);
        level4.setBounds(290,0,60,60);
        level5.setBounds(370,0,60,60);
        level6.setBounds(50,100,60,60);
        level7.setBounds(130,100,60,60);
        level8.setBounds(210,100,60,60);
        level9.setBounds(290,100,60,60);
        level10.setBounds(370,100,60,60);
        back.setBounds(185,200,100,60);

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(level5);
        levels.add(level6);
        levels.add(level7);
        levels.add(level8);
        levels.add(level9);
        levels.add(level10);
        levels.add(back);

        level1.addActionListener(this);
        level2.addActionListener(this);
        level3.addActionListener(this);
        level4.addActionListener(this);
        level5.addActionListener(this);
        level6.addActionListener(this);
        level7.addActionListener(this);
        level8.addActionListener(this);
        level9.addActionListener(this);
        level10.addActionListener(this);
        back.addActionListener(this);
        }
        this.add(sokoban);
        this.add(levels);
    }
    public void makeLevel(int[][] b, int playerXCoordinate, int playerYCoordinate){
        this.getContentPane().removeAll();
        this.repaint();
        board = b;
        playerX = playerXCoordinate;
        playerY = playerYCoordinate;
        originalX = playerXCoordinate;
        originalY = playerYCoordinate;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        level = new int[b.length][b[0].length];
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){
                level[i][j] = b[i][j];
            }
        }
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

        levelBack = new JButton("back");
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
        levelBack.addActionListener(this);

        buttons.add(up);
        buttons.add(down);
        buttons.add(left);
        buttons.add(right);
        buttons.add(reset);
        this.add(levelBack);
        this.add(game);

        up.setBounds(50,0,50,50);
        left.setBounds(0,50,50,50);
        down.setBounds(50,50,50,50);
        right.setBounds (100,50,50,50);
        reset.setBounds(0,0,50,50);
        levelBack.setBounds(200,game.getHeight() + 50,100,50);

        this.add(buttons);
        buttons.setBounds(0,board.length * 50,150,100);
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
            winMessage = new JLabel("You Win!");
            ImageIcon winIcon = new ImageIcon("star.jpg");
            //winScreen.setSize(game.getWidth(),game.getHeight());
            winMessage.setFont(new Font("Cambria", Font.BOLD,25));
            winMessage.setIcon(winIcon);
            this.add(winMessage);
            winMessage.setBounds(150,game.getHeight(), 200, 50);
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
        else if(e.getSource() == left && playerX >=1 && board[playerY][playerX - 1] != 3){
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
        else if(e.getSource() == down && playerY < board.length - 1 && board[playerY + 1][playerX] != 3){
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
        else if(e.getSource() == right && playerX < board[playerY].length - 1 && board[playerY][playerX + 1] != 3){
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
            int[][] dab = new int[level.length][level[0].length];
            for(int i = 0; i < level.length; i++){
                System.arraycopy(level[i], 0, dab[i], 0, level[0].length);
            }
            board = dab;
            playerX = originalX;
            playerY = originalY;
            printBoard();
            updateBoard();
            this.getContentPane().remove(winMessage);
        }
        else if(e.getSource() == start){
            makeLevelSelectScreen();
        }
        else if(e.getSource() == level1){
            makeLevel(Level.level1,1,1);
        }
        else if(e.getSource() == level2){
            makeLevel(Level.level2,1,1);
        }
        else if(e.getSource() == level3){
            makeLevel(Level.level3,1,1);
        }
        else if(e.getSource() == level4){
            makeLevel(Level.level4,1,1);
        }
        else if(e.getSource() == level5){
            makeLevel(Level.level5,1,1);
        }else if(e.getSource() == level6){
            makeLevel(Level.level6,1,1);
        }
        else if(e.getSource() == level7){
            makeLevel(Level.level7,1,1);
        }
        else if(e.getSource() == level8){
            makeLevel(Level.level8,1,1);
        }
        else if(e.getSource() == level9){
            makeLevel(Level.level9,1,1);
        }
        else if(e.getSource() == level10){
            makeLevel(Level.level10,1,1);
        }
        else if(e.getSource() == back){
            makeStartScreen();
        }
        else if(e.getSource() == levelBack){
            makeLevelSelectScreen();
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
            this.getContentPane().remove(winMessage);
            int[][] dab = new int[level.length][level[0].length];
            for(int i = 0; i < level.length; i++){
                System.arraycopy(level[i], 0, dab[i], 0, level[0].length);
            }
            board = dab;
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
