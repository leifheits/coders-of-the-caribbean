import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  private Player player;
  private PrintStream inputForGame;
  private Scanner outputOfGame;
  
  @BeforeEach
  public void createGame() throws IOException {
    PipedOutputStream is = new PipedOutputStream();
    PipedOutputStream os = new PipedOutputStream();
    this.player = new Player(new PipedInputStream(is, 256), new PrintStream(os));
    this.inputForGame = new PrintStream(is);
    this.outputOfGame = new Scanner(new PipedInputStream(os, 256));
  }
  
  @AfterEach
  public void destroyGame() {
    this.outputOfGame = null;
    this.inputForGame = null;
    this.player = null;
  }
  
  @Test
  public void testDefaultTurn() {
    this.inputForGame.println(1); // one player ship
    this.inputForGame.println(3); // one object overall
    this.inputForGame.println("42 SHIP 8 8 0 0 100 1"); // Player ship: id / type / x / y / orientation / speed / rum / player-controlled
    this.inputForGame.println("15 SHIP 16 16 0 0 100 0"); // Hostile ship: id / type / x / y / orientation / speed / rum / player-controlled
    this.inputForGame.println("37 BARREL 12 12 63 0 0 0"); // Barrel: id / type / x / y / rum / reserved / reserved / reserved

    this.player.playOneTurn();
    
    assertThat(this.outputOfGame.nextLine(), is("MOVE 11 10"));
  }
}
