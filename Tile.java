public class Tile {
    char letter;
    int score;

    public Tile(char letter, int score) {
        this.letter = letter; //FOR M1: 🅰🅱🅲🅳🅴🅵🅶🅷🅸🅹🅺🅻🅼🅽🅾🅿🆀🆁🆂🆃🆄🆅🆆🆇🆈🆉
        this.score = score;
    }

    public Tile() {
        letter = '⬜';
        score = 0;
    }



}
