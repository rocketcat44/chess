

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
    private static final String RESOURCES_BGEORGE_PNG = "bgeorge.png";
    private static final String RESOURCES_WGEORGE_PNG = "wgeorge.png";
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    

    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
     
      //for (.....)  
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.

        boolean lalalalaAlternate = true;
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                board[row][col] = new Square(this, lalalalaAlternate, row,col);
                this.add(board[row][col]);
                lalalalaAlternate = !lalalalaAlternate;
            }
            lalalalaAlternate = !lalalalaAlternate;
        }
            

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.

    //black on top, white on bottom
    private void initializePieces() {
    	// ////// PIECES !!!!!
    	for (int row = 0; row < 2; row++){
            for (int col = 0; col < 8; col++){
                if (col ==3 && row == 0){
                    Piece king = new King(false,"bking.png");
                    board[row][col].put(king);
                }else{
                    Piece george = new George(false, "bgeorge.png");///edit parameters for both
                    board[row][col].put(george);
                }
            }
        }

        for (int row = 6; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if (col==4 && row == 7){
                    Piece king = new King(true, "wking.png");
                    board[row][col].put(king);
                }else{
                    Piece george = new George(true, "wgeorge.png");///edit parameters for both
                    board[row][col].put(george);
                }
            }
        }


    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
     
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if(sq == fromMoveSquare)
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
        
    }


    //precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
    //postcondition - returns true of the king is in check and false otherwise.
    public boolean isInCheck(boolean kingColor){
        Square king= null;
        ArrayList<Square> check = new ArrayList<Square>();
		for(Square [] row: board){
            for(Square s: row){
                if ((s.getOccupyingPiece() instanceof King) && s.getOccupyingPiece().getColor() == kingColor){
                    king = s;
                }else{
                    for (Square r: s.getOccupyingPiece().getLegalMoves(this, fromMoveSquare)){
                        check.add(r);
                    }
                }
            }
        }

        if (king!= null){return check.contains(king);}
        else{return false;}
    }
          



    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location. 
    @Override
    public void mouseReleased(MouseEvent e) {
        
        if(currPiece!=null && ((currPiece.getColor() && whiteTurn)
        || (!currPiece.getColor() && !whiteTurn))){
            
            Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
            
            //using currPiece
            for(Square [] row: board){
                for(Square s: row){
                    s.setBorder(null);
                }
            }

        
            for(Square s: currPiece.getLegalMoves(this, fromMoveSquare)){
                if (s.equals(endSquare)){
                    
/* 
                    if (isInCheck(whiteTurn)){
                        ///////////////////////////////////////////////////////////////////////////////////FINISH HERE
                    }
*/


                    // if the piece thats in the square that george is going into is black, then george stays in place and the black piece gets eaten (removed), otherwise george goes forward
                    if (s.getOccupyingPiece() != null && (s.getOccupyingPiece().getColor() != (currPiece.getColor()))){
                        endSquare.removePiece();
                    }else{
                        fromMoveSquare.removePiece();
                        s.put(currPiece);
                    }
                    
                    whiteTurn = !whiteTurn;
                }
            }
        }

        

        if (fromMoveSquare!=null){fromMoveSquare.setDisplay(true);}
        currPiece = null;
        repaint();
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        if(currPiece!=null && ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor() && !whiteTurn))){
            for(Square s: currPiece.getLegalMoves(this, fromMoveSquare)){
                s.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
            }
        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}