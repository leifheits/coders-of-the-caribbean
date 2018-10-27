import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Player {

  private final Scanner input;
  private final PrintStream output;
  
  public Player(final InputStream input, final PrintStream output) {
    this.input = new Scanner(input);
    this.output = output;
  }
  
  public void playOneTurn() {
    int myShipCount = this.input.nextInt(); // the number of remaining ships
    int entityCount = this.input.nextInt(); // the number of entities (e.g. ships, mines or cannonballs)
    for (int i = 0; i < entityCount; i++) {
      int entityId = this.input.nextInt();
      String entityType = this.input.next();
      int x = this.input.nextInt();
      int y = this.input.nextInt();
      int arg1 = this.input.nextInt();
      int arg2 = this.input.nextInt();
      int arg3 = this.input.nextInt();
      int arg4 = this.input.nextInt();
    }
    for (int i = 0; i < myShipCount; i++) {

      // Write an action using System.out.println()
      // To debug: System.err.println("Debug messages...");

      this.output.println("MOVE 11 10"); // Any valid action, such as "WAIT" or "MOVE x y"
    }
  }

  public static void main(String args[]) {
    Player player = new Player(System.in, System.out);
    while (true) {
      player.playOneTurn();
    }
  }
}
