import tester.Tester;
import java.awt.Color;
import java.util.Random;
import javalib.worldimages.*;
import javalib.funworld.*;

//game Class
class MyWorld extends World {

  Target target;
  int score;
  int tick;

  MyWorld(Target target, int score, int tick) {
    this.target = target;
    this.score = score;
    this.tick = tick;

  }

  // create the target drawing with the score on the top left
  public WorldScene makeScene() {
    return this.target.draw()
        .placeImageXY(new TextImage("Score: " + Integer.toString(score), 15, Color.black), 40, 10);
  }

  // create the last scene
  public WorldScene lastScene() {
    WorldScene ws = new WorldScene(600, 400);
    return ws.placeImageXY(new TextImage("Score: " + Integer.toString(score), 30, Color.black), 300,
        200);
  }

  // increment the tick count
  public World onTick() {
    tick++;
    return new MyWorld(this.target, this.score, this.tick);
  }

  // increments the score based on the proximity of the click relative to the
  // target's radius
  public World onMouseClicked(Posn pos) {
    if (this.target.hasBeenHit(pos)) {
      if (this.target.precision(pos) <= (int) (0.15 * this.target.radius)) {
        score += 20;
        return new MyWorld(this.target.move(), this.score, this.tick);
      }
      else if (this.target.precision(pos) <= (int) (0.50 * this.target.radius)) {
        score += 10;
        return new MyWorld(this.target.move(), this.score, this.tick);
      }
      else if (this.target.precision(pos) <= (int) (0.75 * this.target.radius)) {
        score += 5;
        return new MyWorld(this.target.move(), this.score, this.tick);
      }
      else {
        score += 1;
        return new MyWorld(this.target.move(), this.score, this.tick);
      }
    }
    else {
      score -= 1; // given that we miss, decrement a point
    }
    return new MyWorld(this.target, this.score, this.tick);

  }

  // handle what scene to show
  public WorldEnd worldEnds() {
    if (this.tick == 30) {
      return new WorldEnd(true, this.lastScene()); // display the score
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

}

//class representing target 
class Target {

  int radius; // radius of target
  Color c; // color that compliments the black to create bullseye
  int x; // x position
  int y; // y position
  Random rand = new Random();

  Target(int radius, Color c, int x, int y) {
    this.radius = radius;
    this.c = c;
    this.x = x;
    this.y = y;

  }

  Target() {
    this(20, Color.RED, 300, 200); // general constructor for default purpose
  }

  // when clicked, moves the target to a random spot
  Target move() {
    return new Target(this.radius, this.c, rand.nextInt(600 - 2 * this.radius) + this.radius,
        rand.nextInt(400 - 2 * this.radius) + this.radius);
  }

  // determines if the clicked position was inside the target
  boolean hasBeenHit(Posn clicked) {

    return Math.hypot(clicked.x - this.x, clicked.y - this.y) <= this.radius;

  }

  // determines the precision of the click within the circle
  double precision(Posn clicked) {

    return Math.hypot(clicked.x - this.x, clicked.y - this.y);

  }

  // draw the bullseye
  WorldScene draw() {
    return new WorldScene(600, 400)
        .placeImageXY(new CircleImage(this.radius, "solid", this.c), this.x, this.y)
        .placeImageXY(new CircleImage((int) (this.radius * 0.75), "solid", Color.black), this.x,
            this.y)
        .placeImageXY(new CircleImage((int) (this.radius * 0.50), "solid", this.c), this.x, this.y)
        .placeImageXY(new CircleImage((int) (this.radius * 0.15), "solid", Color.black), this.x,
            this.y);
  }
}

//examples class 
class ExamplesMyWorld {
  Target sampleTarget;
  Target gameTarget;
  MyWorld gameWorld;
  Target otherTarget;
  MyWorld sampleWorld;
  MyWorld otherWorld;
  Posn scoreTwenty;
  Posn scoreTen;
  Posn scoreFive;
  Posn scoreOne;
  Posn scoreNegativeOne;

  void initData() {
    this.sampleTarget = new Target();
    this.gameTarget = new Target();
    this.gameWorld = new MyWorld(gameTarget, 0, 0);
    this.otherTarget = new Target(40, Color.blue, 300, 300);
    this.sampleWorld = new MyWorld(sampleTarget, 0, 0);
    this.otherWorld = new MyWorld(otherTarget, 20, 29);
    this.scoreTwenty = new Posn(300, 200);
    this.scoreTen = new Posn(305, 205);
    this.scoreFive = new Posn(310, 205);
    this.scoreOne = new Posn(316, 200);
    this.scoreNegativeOne = new Posn(100000, 1000);
  }

  // start the world
  boolean testBigBang(Tester t) {
    this.initData();
    return this.gameWorld.bigBang(600, 400, 1);
  }

  // test move method
  boolean testMove(Tester t) {
    this.initData();
    sampleTarget.rand = new Random(42);
    otherTarget.rand = new Random(65);
    return t.checkExpect(this.sampleTarget.move(), new Target(20, Color.red, 350, 23))
        && t.checkExpect(this.otherTarget.move(), new Target(40, Color.blue, 340, 83));
  }

  // test hasBeenHit method
  boolean testHasBeenHit(Tester t) {
    this.initData();
    return t.checkExpect(this.sampleTarget.hasBeenHit(scoreTwenty), true)
        && t.checkExpect(this.otherTarget.hasBeenHit(scoreNegativeOne), false);
  }

  // test precision method
  boolean testPrecision(Tester t) {
    this.initData();
    return t.checkExpect(this.sampleTarget.precision(scoreTwenty), 0.0)
        && t.checkExpect(this.otherTarget.precision(scoreNegativeOne), 99702.45734183285);
  }

  // test draw target method 
  boolean testDraw(Tester t) {
    this.initData();
    return t.checkExpect(this.sampleTarget.draw(),
        new WorldScene(600, 400)
        .placeImageXY(new CircleImage(this.sampleTarget.radius, "solid", this.sampleTarget.c),
            this.sampleTarget.x, this.sampleTarget.y)
        .placeImageXY(new CircleImage((int) (this.sampleTarget.radius * 0.75), "solid",
            Color.black), this.sampleTarget.x, this.sampleTarget.y)
        .placeImageXY(new CircleImage((int) (this.sampleTarget.radius * 0.50), "solid",
            this.sampleTarget.c), this.sampleTarget.x, this.sampleTarget.y)
        .placeImageXY(new CircleImage((int) (this.sampleTarget.radius * 0.15), "solid",
            Color.black), this.sampleTarget.x, this.sampleTarget.y))
        && t.checkExpect(this.otherTarget.draw(),
            new WorldScene(600, 400)
            .placeImageXY(new CircleImage(this.otherTarget.radius, "solid", this.otherTarget.c),
                this.otherTarget.x, this.otherTarget.y)
            .placeImageXY(new CircleImage((int) (this.otherTarget.radius * 0.75), "solid",
                Color.black), this.otherTarget.x, this.otherTarget.y)
            .placeImageXY(new CircleImage((int) (this.otherTarget.radius * 0.50), "solid",
                this.otherTarget.c), this.otherTarget.x, this.otherTarget.y)
            .placeImageXY(new CircleImage((int) (this.otherTarget.radius * 0.15), "solid",
                Color.black), this.otherTarget.x, this.otherTarget.y));
  }

  // test world makeScene method
  boolean testWorldMakeScene(Tester t) {
    this.initData();
    return t.checkExpect(this.sampleWorld.makeScene(),
        new WorldScene(600, 400)
        .placeImageXY(new CircleImage(this.sampleTarget.radius, "solid", this.sampleTarget.c),
            this.sampleTarget.x, this.sampleTarget.y)
        .placeImageXY(new CircleImage((int) (this.sampleTarget.radius * 0.75), "solid",
            Color.black), this.sampleTarget.x, this.sampleTarget.y)
        .placeImageXY(new CircleImage((int) (this.sampleTarget.radius * 0.50), "solid",
            this.sampleTarget.c), this.sampleTarget.x, this.sampleTarget.y)
        .placeImageXY(new CircleImage((int) (this.sampleTarget.radius * 0.15), "solid",
            Color.black), this.sampleTarget.x, this.sampleTarget.y)
        .placeImageXY(new TextImage("Score: 0", 15, Color.black), 40, 10));
  }

  // test lastScene method
  boolean testWorldMakeLastScene(Tester t) {
    this.initData();
    return t.checkExpect(this.otherWorld.lastScene(), new WorldScene(600, 400)
        .placeImageXY(new TextImage("Score: 20", 30, Color.black), 300, 200));
  }

  // test onTick method
  boolean testWorldOnTick(Tester t) {
    this.initData();
    return t.checkExpect(this.otherWorld.onTick(), new MyWorld(otherTarget, 20, 30));
  }

  // test onMouseClicked method 
  boolean testWorldOnMouseClicked(Tester t) {
    this.initData();
    this.sampleWorld.target.rand = new Random(42);
    t.checkExpect(this.sampleWorld.onMouseClicked(scoreTwenty),
        new MyWorld(new Target(20, Color.red, 350, 23), 20, 0));

    this.sampleWorld.target.rand = new Random(42);
    t.checkExpect(this.sampleWorld.onMouseClicked(scoreTen),
        new MyWorld(new Target(20, Color.red, 350, 23), 30, 0));

    this.sampleWorld.target.rand = new Random(42);
    t.checkExpect(this.sampleWorld.onMouseClicked(scoreFive),
        new MyWorld(new Target(20, Color.red, 350, 23), 35, 0));

    this.sampleWorld.target.rand = new Random(42);
    t.checkExpect(this.sampleWorld.onMouseClicked(scoreOne),
        new MyWorld(new Target(20, Color.red, 350, 23), 36, 0));

    this.sampleWorld.target.rand = new Random(42);
    return t.checkExpect(this.sampleWorld.onMouseClicked(this.scoreNegativeOne),
        new MyWorld(new Target(20, Color.red, 300, 200), 35, 0));
  }

  // test WorldEnd method
  boolean testWorldEnds(Tester t) {
    this.initData();
    return t.checkExpect(this.otherWorld.onTick().worldEnds(), new WorldEnd(true, new WorldScene(600, 400)
        .placeImageXY(new TextImage("Score: 20", 30, Color.black), 300, 200)));
  }
}
