
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.


    /*
     * New piece: George
     * 
     * He can move like a rook but only one space
     * He can take pieces like a rook but only one space
     * When he takes a piece, he teleports back to his original spot
     *
    */
    public ArrayList<Square> getControlledSquares(Square[][] b, Square start) {
      ArrayList<Square> moves = new ArrayList<Square>();
        if (start.getCol()+1<8 && (b[start.getRow()][start.getCol()+1].isOccupied() || b[start.getRow()][start.getCol()+1].getOccupyingPiece().getColor()!= color) ){
          moves.add(b[start.getRow()][start.getCol()+1]);
        }
        if (start.getCol()-1>=0 && (b[start.getRow()][start.getCol()-1].isOccupied() || b[start.getRow()][start.getCol()-1].getOccupyingPiece().getColor()!= color)){
          moves.add(b[start.getRow()][start.getCol()-1]);
        }
        if (start.getRow()-1>=0 && (b[start.getRow()-1][start.getCol()].isOccupied() || b[start.getRow()-1][start.getCol()].getOccupyingPiece().getColor()!= color)){
          moves.add(b[start.getRow()-1][start.getCol()]);
        }
        if (start.getRow()+1<8 && (b[start.getRow()+1][start.getCol()].isOccupied() || b[start.getRow()+1][start.getCol()].getOccupyingPiece().getColor()!= color)){
          moves.add(b[start.getRow()+1][start.getCol()]);
        }
    
    return moves;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      Square[][] board = b.getSquareArray();
      ArrayList<Square> moves = new ArrayList<Square>();
      if (start.getCol()+1<8 && !( board[start.getRow()][start.getCol()+1].isOccupied() && board[start.getRow()][start.getCol()+1].getOccupyingPiece().getColor()== color ) ){
        moves.add(board[start.getRow()][start.getCol()+1]);
      }
      if (start.getCol()-1>=0 && !(board[start.getRow()][start.getCol()-1].isOccupied() && board[start.getRow()][start.getCol()-1].getOccupyingPiece().getColor()== color)){
        moves.add(board[start.getRow()][start.getCol()-1]);
      }
      if (start.getRow()-1>=0 && !(board[start.getRow()-1][start.getCol()].isOccupied() && board[start.getRow()-1][start.getCol()].getOccupyingPiece().getColor()== color)){
        moves.add(board[start.getRow()-1][start.getCol()]);
      }
      if (start.getRow()+1<8 && !(board[start.getRow()+1][start.getCol()].isOccupied() && board[start.getRow()+1][start.getCol()].getOccupyingPiece().getColor()== color)){
        moves.add(board[start.getRow()+1][start.getCol()]);
      }
    
      return moves;
      
    }
}