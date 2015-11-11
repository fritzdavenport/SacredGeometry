/* Sacred Geometry Probability - Brute Force is best Force */
/*  Roll a number of d6 and achieve one of three prime numbers */
// min 2 dice, max of 23 + int mod dice
//  (6 × 6) + (4 – 3) × 1 = 37.
// also: ([6 + 6] × 3) + 4 + 1 = 41
// If the result of his dice pool were 1, 1, 2, and 5,
// he would have been unable to produce any of the relevant prime constants.
// His attempt would have been unsuccessful, and he would have expended a full-round action
// and his prepared 1st-level magic missile spell with no effect.

package SacredGeometry

// https://code.google.com/p/mathparser-java/wiki/MathParser
import com.google.code._;

object SacredGeometry extends App {
    
    val dice = 2
    val maxLevel = 1

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

    val anyPrime = primes.flatten
    val sidesOfDie = (1 to 6)

    def isInPrimeList(num : Double) : Boolean = {return anyPrime exists(_ equals num) }
    def isInPrimeListAtLevel(num : Double, level : Int) : Boolean = {return primes(level) exists(_ equals num) }
    val mathParser = mathparser.MathParserFactory.create();

    val operators = List("+", "-", "/", "*")
    val eachPermutation = for(d <- sidesOfDie; o <- operators) yield d+o
    val max = eachPermutation.length
    val totalPermutation = (1 to 6) :: List.fill(dice-1)(eachPermutation)

    def recursivelyBuildExpression(index : Double, expr : StringBuilder) : StringBuilder = {
        val offset = index % max 
        println("SETUP: "+index+" "+expr+" "+max+" "+offset)
        println( "EXPR: "+expr.toString() )
    
        println( "ADDING: "+eachPermutation( offset.toInt ) )
        expr.append( eachPermutation( offset.toInt ) )

        val newIndex = (index-offset)/max
        if (newIndex < max) {
            expr.append( sidesOfDie( offset.toInt ) )
            println("ALL DONE "+newIndex+" "+max+" "+expr.toString())
            return expr
        } else {
            println("RECURSING "+newIndex+" "+max + " "+expr.toString())
            return recursivelyBuildExpression( newIndex, expr )
        }
    }

    def probabilityForLevel(level : Int) : Double = {
        // make a huge list of numbers corresponding to the total number of indexes
        val totalEventSpaceSize = ( sidesOfDie.length * (( sidesOfDie.length * operators.length)^(dice-1)) )

        val validSolutions = ( 0 to totalEventSpaceSize ) map( i=> mathParser.calculate( recursivelyBuildExpression(i, new StringBuilder ).toString() ).doubleValue() ) 
        println(validSolutions)
            // reduce( (total,i) => if ( isInPrimeListAtLevel( getAndEvalExpressionForIndex(i), level ) ) total+1 else total )

        .5
    }

    for(level <- 1 to maxLevel) yield println("Probability of success for "+dice+" dice at level "+level+": "+probabilityForLevel(level) )
}






    // for( i <- 1 to dice){
    //     i match {
    //         case 1 => val s= "head" + i
    //         case _ => val s= "else" + i
    //         // case _ => println(index+" "+isInPrimeListAtLevel(i,1 ))
    //     }
    //     println(s)
    // }

    // def powerset[A](s: Set[A]) = (0 to s.size).map(s.toSeq.combinations(_)).reduce(_ ++ _).map(_.toSet) // http://rosettacode.org/wiki/Power_set#Scala



    //     val eventSpace = generateEventSpace(dice)
    //     val totalEvents = eventSpace.length
    //     val results = eventSpace par filter( isInPrimeListAtLevel(_, level) )
    // }


    // def rollDice() : Int {
    //   // random gives (0-1], *5 gives (0- ~5], round gives (0-5), +1 gives (0-6)
    //   return Math.round( Math.random() * 5 ) + 1; 
    // }
      


    // def sum(ints: List[Int]): Int = ints match { 
    //     case Nil => 0
    //     case x :: tail => x + sum(tail)
    // }