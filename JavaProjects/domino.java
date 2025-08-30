import tester.Tester;

//interface for dominos 

//An IDomino is one of MtDomino or Domino
interface IDomino {

  // count how many dominoes come after the current one,
  // not including the current domino itself
  int count();

  // count how many dominoes come after the current one,
  // including the current domino itself
  int countHelper();

  // checks if the chain of dominoes is valid
  boolean validChain();

  // a helper that sees checks if the current left value is equal to the argument
  // aka the previous right value
  boolean validChainHelper(int value);

  // count how many dominoes in the chain are "doubles" (left and right values of
  // this
  // domino are the same)
  int computeDoubles();

  // compute total sum of left and right values of all dominos
  int computeSum();

  // compute number of Strong Links
  int computeStrongLinks();

  // determines if a given domino is a double
  boolean isDouble();

  // determines if the previous domino's right equals current domino's left
  boolean prevRightEqualsCurrentLeft(int value);

}

//empty list of dominos class 
class MtDomino implements IDomino {
  MtDomino() {
  }

  // count how many dominoes come after the current one,
  // not including the current domino itself
  public int count() {
    return 0;
  }

  // count how many dominoes come after the current one,
  // including the current domino itself
  public int countHelper() {
    return 0;
  }

  // determine if the entire chain is valid, meaning every adjacent pair connects
  // properly according to the matching numbers rule
  // (the right number of one domino must match the left number of the next).
  public boolean validChain() {
    return true;
  }

  // a helper that sees checks if the current left value is equal to the argument
  // aka the previous right value
  public boolean validChainHelper(int value) {
    return true;
  }

  // count how many dominoes in the chain are "doubles"
  public int computeDoubles() {
    return 0;
  }

  // compute total sum of dominoes
  public int computeSum() {
    // TODO Auto-generated method stub
    return 0;
  }

  // compute number of Strong Links
  public int computeStrongLinks() {
    return 0;
  }

  // determines if a given domino is a double
  public boolean isDouble() {
    return false;
  }

  // determines if the previous domino's right equals current domino's left
  public boolean prevRightEqualsCurrentLeft(int value) {
    return false;
  }

}

//Domino class 
class Domino implements IDomino {
  int left;
  int right;
  IDomino next;

  Domino(int left, int right, IDomino next) {
    this.left = left;
    this.right = right;
    this.next = next;
  }

  // count how many dominoes come after the current one,
  // not including the current domino itself
  public int count() {
    return this.next.countHelper();
  }

  // count how many dominoes come after the current one,
  // including the current domino itself
  public int countHelper() {
    return 1 + this.next.countHelper();
  }

  // determine if the entire chain is valid, meaning every adjacent pair connects
  // properly according to the matching numbers rule
  // (the right number of current domino must match the left number of the next).
  public boolean validChain() {
    return this.next.validChainHelper(this.right);

  }

  // a helper that sees checks if the current left value is equal to the argument
  // aka the previous right value
  public boolean validChainHelper(int value) {
    return this.left == value && this.next.validChainHelper(this.right);
  }

  // count how many dominoes in the chain are "doubles"
  public int computeDoubles() {
    if (this.left == this.right) {
      return 1 + this.next.computeDoubles();
    }
    else {
      return this.next.computeDoubles();
    }
  }

  // compute total sum of left and right values of all dominos
  public int computeSum() {
    return this.left + this.right + this.next.computeSum();
  }

  // compute number of Strong Links (a strong link is if the previous and current dominos are doubles)
  public int computeStrongLinks() {
    if (this.isDouble() && this.next.isDouble())
        && this.next.prevRightEqualsCurrentLeft(this.right)) {
      return 1 + this.next.computeStrongLinks();
    }
    else {
      return this.next.computeStrongLinks();
    }
  }

  // determines if a given domino is a double
  public boolean isDouble() {
    return this.left == this.right;
  }

  // determines if the previous domino's right equals current domino's left
  public boolean prevRightEqualsCurrentLeft(int value) {
    return this.left == value;
  }

}

class testDomino {
  IDomino base = new MtDomino();
  Domino threeFour = new Domino(3, 4, this.base);
  Domino twoThree = new Domino(2, 3, this.threeFour);
  Domino oneTwo = new Domino(1, 2, this.twoThree);
  Domino oneOne = new Domino(1, 1, this.oneTwo);
  Domino oneOneAgain = new Domino(1, 1, this.oneOne);

  //test count method 
  boolean testCount(Tester t) {
    return t.checkExpect(this.base.count(), 0) && t.checkExpect(this.twoThree.count(), 1)
        && t.checkExpect(this.oneTwo.count(), 2);
  }

  //test countHelper method 
  boolean testCountHelper(Tester t) {
    return t.checkExpect(this.base.countHelper(), 0)
        && t.checkExpect(this.twoThree.countHelper(), 2)
        && t.checkExpect(this.oneTwo.countHelper(), 3);

  }

  //test validChain method 
  boolean testValidChain(Tester t) {
    return t.checkExpect(this.base.validChain(), true)
        && t.checkExpect(this.threeFour.validChain(), true)
        && t.checkExpect(this.twoThree.validChain(), true)
        && t.checkExpect(this.oneTwo.validChain(), true);
  }

  //test validChainHelper method 
  boolean testValidChainHelper(Tester t) {
    return t.checkExpect(this.base.validChainHelper(5), true)
        && t.checkExpect(this.threeFour.validChainHelper(10), false)
        && t.checkExpect(this.threeFour.validChainHelper(3), true);
  }

  //test computeDoubles method 
  boolean testComputeDoubles(Tester t) {
    return t.checkExpect(this.base.computeDoubles(), 0)
        && t.checkExpect(this.threeFour.computeDoubles(), 0)
        && t.checkExpect(this.twoThree.computeDoubles(), 0)
        && t.checkExpect(this.oneTwo.computeDoubles(), 0)
        && t.checkExpect(this.oneOne.computeDoubles(), 1);

  }

  //test computeSum method
  boolean testComputeSum(Tester t) {
    return t.checkExpect(this.base.computeSum(), 0) && t.checkExpect(this.threeFour.computeSum(), 7)
        && t.checkExpect(this.twoThree.computeSum(), 12)
        && t.checkExpect(this.oneTwo.computeSum(), 15)
        && t.checkExpect(this.oneOne.computeSum(), 17);

  }

  //test computeStrongLinks method 
  boolean testComputeStrongLinks(Tester t) {
    return t.checkExpect(this.base.computeStrongLinks(), 0)
        && t.checkExpect(this.threeFour.computeStrongLinks(), 0)
        && t.checkExpect(this.twoThree.computeStrongLinks(), 0)
        && t.checkExpect(this.oneTwo.computeStrongLinks(), 0)
        && t.checkExpect(this.oneOne.computeStrongLinks(), 0)
        && t.checkExpect(this.oneOneAgain.computeStrongLinks(), 1);

  }

  //test isDouble method 
  boolean testIsDouble(Tester t) {
    return t.checkExpect(this.threeFour.isDouble(), false)
        && t.checkExpect(this.oneOne.isDouble(), true);
  }

  //test prevRightEqualsCurrentLeft method
  boolean testPrevRightEqualsCurrentLeft(Tester t) {
    return t.checkExpect(this.threeFour.prevRightEqualsCurrentLeft(4), false)
        && t.checkExpect(this.threeFour.prevRightEqualsCurrentLeft(3), true);
  }

}

