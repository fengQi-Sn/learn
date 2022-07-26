package example.example.structure.flyweight.chess.two;

import example.example.structure.flyweight.chess.one.ChessPiece;

public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;

    public ChessPieceUnit(int id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }

    public static enum Color { RED, BLACK }
}
