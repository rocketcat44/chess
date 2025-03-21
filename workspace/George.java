//Your name: Bella

//Piece name: George
//brief description of what your piece does:
/*
     * He can move like a rook but only one space
     * He can take pieces like a rook but only one space
     * When he takes a piece, he teleports back to his original spot
 */



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
public class George extends Piece
{
    
    public George(boolean isWhite, String img_file) {
        super(isWhite,img_file);
    }

    public String toString(){
      return "A " + super.toString() + " George";
    }
    
  
    /*
     * New piece: George
     * 
     * He can move like a rook but only one space
     * He can take pieces like a rook but only one space
     * When he takes a piece, he teleports back to his original spot
     *
    */

    // Pre-condition: b is a non-null board that contains some squares. The
	// currentSquare is non-null and present in the board.
	// Post-condition: Returns all legally accessable squares by this piece. In the
	// event that no squares are accessable returns an empty list.
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
    

    /*
     * New piece: George
     * 
     * He can move like a rook but only one space
     * He can take pieces like a rook but only one space
     * When he takes a piece, he teleports back to his original spot
     *
    */
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