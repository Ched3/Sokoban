import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class myFrame implements ActionListener, KeyListener {

    private int [][] board;
    private int [][] level;
    private JFrame frame;
    private JPanel game;
    private JButton up;
    private JButton left;
    private JButton down;
    private JButton right;
    private JButton reset;
    private JPanel buttons;
    private int playerX;
    private int playerY;

    public myFrame(int x, int y, int playerXCoord, int playerYCoord) {
        board = new int[y][x];
        frame = new JFrame();
        playerX = playerXCoord;
        playerY = playerYCoord;

        game = new JPanel();
        game.setLayout(null);

        frame.setName("Sokoban");
        game.setSize(board[0].length*50 + 20,board.length*50);
        frame.setSize(board[0].length*50 + 20,board.length*50 + 200);
        frame.setResizable(false);

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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        buttons.add(up);
        buttons.add(down);
        buttons.add(left);
        buttons.add(right);
        buttons.add(reset);
        frame.add(game);

        up.setBounds(50,0,50,50);
        left.setBounds(0,50,50,50);
        down.setBounds(50,50,50,50);
        right.setBounds(100,50,50,50);
        reset.setBounds(0,0,50,50);

        frame.add(buttons);
        buttons.setBounds(0,y * 50,150,100);
        frame.setVisible(true);
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
        ImageIcon box = new ImageIcon("redBox.png");
        JLabel label = new JLabel();
        game.add(label);
        label.setBounds(xCoord,yCoord,50,50);
        label.setIcon(box);
    }
    public void updateBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(i ==playerY && j == playerX){
                    makePlayerTile(j*50,i*50);
                }
                if(board[j][i] == 0) {//air tile
                    makeEmptyTile(j*50, i*50);
                }
                /*else if(board[j][i] == 1){//player tile
                    makePlayerTile(j*50,i*50);
                }*/
                else if(board[j][i] == 2){//box tile
                    makeBoxTile(j*50,i*50);
                }
                else if(board[j][i] == 3){//wall tile
                    makeWallTile(j*50,i*50);
                }
                else if(board[j][i] == 4){//goal tile
                    makeGoalTile(j*50,i*50);
                }
                else if(board[j][i] == 5) {//box on goal tile
                    makeBoxGoalTile(j*50,i*50);
                }

                else if(board[j][i] == 6) {//player on goal tile
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
            frame.add(winMessage);
            winMessage.setBounds(200,game.getHeight(), 200, 100);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == up && playerY >=1 && board[playerY - 1][playerX] != 5 && board[playerY - 1][playerX] != 3){
            if(board[playerY - 1][playerX] == 2){
                if(playerY - 2 >= 0 && board[playerY - 2][playerX] == 0 || board[playerY - 2][playerX] == 4){
                    if(board[playerY - 2][playerX] == 4){
                        board[playerY - 2][playerX] = 5;
                        board[playerY][playerX] = 0;
                        playerY--;
                        printBoard();
                        game.removeAll();
                        updateBoard();
                    }
                    else{
                        board[playerY - 2][playerX] = 2;
                        board[playerY][playerX] = 0;
                        playerY--;
                        printBoard();
                        game.removeAll();
                        updateBoard();
                    }
                }
            }
            else{
                System.out.println("up");
                board[playerY][playerX] = 0;
                playerY--;
                printBoard();
                game.removeAll();
                updateBoard();
            }
        }
        if(e.getSource() == left && playerX >=1 && board[playerY][playerX - 1] != 5 && board[playerY][playerX - 1] != 3){
            if(board[playerY][playerX - 1] == 2){
                if(playerX - 2 >= 0 && board[playerY][playerX - 2] == 0 || board[playerY][playerX - 2] == 4){
                    if(board[playerY][playerX - 2] == 4){
                        board[playerY][playerX - 2] = 5;
                        board[playerY][playerX] = 0;
                        playerX--;
                        printBoard();
                        game.removeAll();
                        updateBoard();
                    }
                    else{
                        board[playerY][playerX - 2] = 2;
                        board[playerY][playerX] = 0;
                        playerX--;
                        printBoard();
                        game.removeAll();
                        updateBoard();
                    }
                }
            }
            else{
                System.out.println("left");
                board[playerY][playerX] = 0;
                playerX--;
                printBoard();
                game.removeAll();
                updateBoard();
            }
        }
        if(e.getSource() == down && playerY < board.length - 1 && board[playerY + 1][playerX] != 5 && board[playerY + 1][playerX] != 3){
            if(board[playerY + 1][playerX] == 2){
                if(playerY + 2 < board.length && board[playerY + 2][playerX] == 0 || board[playerY + 2][playerX] == 4){
                    if(board[playerY + 2][playerX] == 4){
                        board[playerY + 2][playerX] = 5;
                        board[playerY][playerX] = 0;
                        playerY++;
                        printBoard();
                        game.removeAll();
                        updateBoard();
                    }
                    else{
                        board[playerY + 2][playerX] = 2;
                        board[playerY][playerX] = 0;
                        playerY++;
                        printBoard();
                        game.removeAll();
                        updateBoard();
                    }
                }
            }
            else{
                System.out.println("down");
                board[playerY][playerX] = 0;
                playerY++;
                printBoard();
                game.removeAll();
                updateBoard();
            }
        }
        if(e.getSource() == right && playerX < board[playerY].length - 1 && board[playerY][playerX + 1] != 5 && board[playerY][playerX + 1] != 3){
            if(board[playerY][playerX + 1] == 2){
                if(playerX + 2 < board.length && board[playerY][playerX + 2] == 0 || board[playerY][playerX + 2] == 4){
                    if(board[playerY][playerX + 2] == 4){
                        board[playerY][playerX + 2] = 5;
                        board[playerY][playerX] = 0;
                        playerX++;
                        printBoard();
                        game.removeAll();
                        updateBoard();
                    }
                    else{
                        board[playerY][playerX + 2] = 2;
                        board[playerY][playerX] = 0;
                        playerX++;
                        printBoard();
                        game.removeAll();
                        updateBoard();
                    }
                }
            }
            else{
                System.out.println("right");
                board[playerY][playerX] = 0;
                playerX++;
                printBoard();
                game.removeAll();
                updateBoard();
            }
        }
        else if(e.getSource() == reset){
            board = level;
            printBoard();
            game.removeAll();
            updateBoard();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
