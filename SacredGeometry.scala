/* Sacred Geometry Probability - Brute Force is best Force */
/*  Roll a number of d6 and achieve one of three prime numbers */

object SacredGeometry {
  val primes = List(
    List(3, 5, 7),
    List(11, 13, 17),
    List(19, 23, 29),
    List(31, 37, 41),
    List(43, 47, 53),
    List(59, 61, 67),
    List(71, 73, 79),
    List(83, 89, 97),
    List(101, 103, 107)
  )

  def rollDice() : Int {
  	// random gives (0-1], *5 gives (0- ~5], round gives (0-5), +1 gives (0-6)
    return Math.round( Math.random() * 5 ) + 1; 
  }

  def probabilityForLevel(level : Int) : Double {
  }

  // returns [ "1", "2", ... , "1+1", "1-1", ... ]
  def generateEventSpace(dice : Int) : List<String> {
    // TODO: NEED GROUPING




  }

  def main(args: Array[String]){ // can also just extend Application and not have a main method
    // min 2 dice, max of ... many dice... 23 + int mod
    //  (6 × 6) + (4 – 3) × 1 = 37.
    // also: ([6 + 6] × 3) + 4 + 1 = 41
    // If the result of his dice pool were 1, 1, 2, and 5,
    // he would have been unable to produce any of the relevant prime constants.
    // His attempt would have been unsuccessful, and he would have expended a full-round action
    // and his prepared 1st-level magic missile spell with no effect.


    val die = args(1)

    println("Calculating Probability for all spell levels with : "+die)

    // first, get total event space
    // then for each level, parse number of events in event space of which there is a success.
        // for 1-9 ( or less if we can't get it)
          // assemble all permutations of [1-6] and [+-/*], do the math, check if its one of three.
            // HOWEVER grouping matters, and order doesn't matter -
            // multiplication and addition are communitive (4+6) == (6+4) and (6*4) == (4*6)
            // subtraction and division are not
            // PEMDAS
          // if it is, add it to a list.
          // if it isn't, add it to a counter
  }
}