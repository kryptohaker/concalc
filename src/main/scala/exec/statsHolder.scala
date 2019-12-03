package exec

import scala.gui.statsPanel

/**
 * Created by ltrogr on 10/26/14 (edited by Ramil Mustafayev on 11/28/2019).
 */
object statsHolder {

  var maxPrimeFound = 0
  var minPrimeFound = Int.MaxValue
  var lastFileProcessed = ""
  var filesProcessed = 0
  var lastFoundedPrime = 0

  def setStatistics(minPrime:Int, maxPrime:Int, prime:Int, fileName:String) = {
    if(minPrime < minPrimeFound) {
      minPrimeFound = minPrime
      lastFileProcessed = fileName
    }
    if(maxPrime > maxPrimeFound) {
      maxPrimeFound = maxPrime
      lastFileProcessed = fileName
    }
    lastFoundedPrime = prime
    filesProcessed += 1

    statsPanel.updateStatistics(toString())
  }

  override def toString =
    "Min found primary number: " + minPrimeFound + "\n" +
    "Max found primary number: " + maxPrimeFound + "\n" +
    "Last updating file: " + lastFileProcessed + "\n" +
    "Last found primary number: " + lastFoundedPrime + "\n" +
    "Number of files done: " + filesProcessed

}
