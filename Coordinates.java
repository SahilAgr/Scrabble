/**
 * The Coordinates for the board each axis is an enum so there is no user error when picking where to place a piece
 * @author  Matthew Huitema
 */
public class Coordinates {

    //enums for the x and y co-ords. Enums doesnt like it when I use integers. ~MH
    public enum yCoordinate{ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,ELEVEN,TWELVE,THIRTEEN,FOURTEEN,FIFTEEN;
        public static yCoordinate toYCoordinate(String next){
            Integer value = null;
            if(Character.isDigit(next.charAt(0))){
                value = Integer.parseInt(next);
            }

            if(value != null){
                return yCoordinate.values()[value - 1];
            }

            next = next.toUpperCase();
            switch(next){
                case "ONE": return yCoordinate.ONE;
                case "TWO": return yCoordinate.TWO;
                case "THREE": return yCoordinate.THREE;
                case "FOUR": return yCoordinate.FOUR;
                case "FIVE": return yCoordinate.FIVE;
                case "SIX": return yCoordinate.SIX;
                case "SEVEN": return yCoordinate.SEVEN;
                case "EIGHT": return yCoordinate.EIGHT;
                case "NINE": return yCoordinate.NINE;
                case "TEN": return yCoordinate.TEN;
                case "ELEVEN": return yCoordinate.ELEVEN;
                case "TWELVE": return yCoordinate.TWELVE;
                case "THIRTEEN": return yCoordinate.THIRTEEN;
                case "FOURTEEN": return yCoordinate.FOURTEEN;
                case "FIFTEEN": return yCoordinate.FIFTEEN;
                default: return null; //there's an argument to throw an error here.
            }
        }
        public static yCoordinate ordinalToYCoordinate(int i){
            return yCoordinate.values()[i];
        }
    }
    public enum xCoordinate{A,B,C,D,E,F,G,H,I,J,K,L,M,N,O;
        public static xCoordinate toXCoordinate(char next) {
            if(Character.isLetter(next)){
                next = Character.toUpperCase(next);
            }
            switch(next){
                case 'A': return xCoordinate.A;
                case 'B': return xCoordinate.B;
                case 'C': return xCoordinate.C;
                case 'D': return xCoordinate.D;
                case 'E': return xCoordinate.E;
                case 'F': return xCoordinate.F;
                case 'G': return xCoordinate.G;
                case 'H': return xCoordinate.H;
                case 'I': return xCoordinate.I;
                case 'J': return xCoordinate.J;
                case 'K': return xCoordinate.K;
                case 'L': return xCoordinate.L;
                case 'M': return xCoordinate.M;
                case 'N': return xCoordinate.N;
                case 'O': return xCoordinate.O;
                default: return null; //there's an argument to throw an error here.
            }
        }
        public static xCoordinate ordinalToXCoordinate(int i){
            return xCoordinate.values()[i];
        }

    }
    
    private yCoordinate yCoord;
    private xCoordinate xCoord;

    public Coordinates(xCoordinate xCo, yCoordinate yCo){
        xCoord = xCo;
        yCoord = yCo;
    }

    public Coordinates(Integer xCo, yCoordinate yCo){
        xCoord = xCoordinate.ordinalToXCoordinate(xCo);
        yCoord = yCo;
    }

    public Coordinates(xCoordinate xCo, Integer yCo){
        xCoord = xCo;
        yCoord = yCoordinate.ordinalToYCoordinate(yCo);
    }

    public Coordinates(Integer xCo, Integer yCo){
        xCoord = xCoordinate.ordinalToXCoordinate(xCo);
        yCoord = yCoordinate.ordinalToYCoordinate(yCo);
    }
    

    public final xCoordinate getXCoordinate(){
        return xCoord;
    }

    public final yCoordinate getYCoordinate(){
        return yCoord;
    }

    public void setXCoordinate(Integer xCo){
        xCoord = xCoordinate.ordinalToXCoordinate(xCo);
    }

    public void setYCoordinate(Integer yCo){
        yCoord = yCoordinate.ordinalToYCoordinate(yCo);
    }
}
