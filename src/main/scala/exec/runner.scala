package exec

import java.util
import java.util.concurrent.{BlockingQueue, ConcurrentLinkedQueue, LinkedBlockingQueue, TimeUnit}

import scala.gui.{mainPanel, statsPanel, threadInfoPanel}
import scala.gui.{statsPanel, threadInfoPanel}

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.swing.Dialog

/**
 * Created by ltrogr on 10/26/14 (edited by Ramil Mustafayev on 11/28/2019).
 */

object runner {

  var _threadCount: Int = 4
  var realThreadCount: Int = 0
  var threadsDone: Int = 0
  val outputQueue: BlockingQueue[ComputingThread] = new LinkedBlockingQueue[ComputingThread]()
  var inputQueue: ConcurrentLinkedQueue[String] = new ConcurrentLinkedQueue[String]()

  def threadCount = _threadCount
  def threadCount_= (newThreadCount:Int):Unit = _threadCount=newThreadCount

  def threadCountInc() = {
    _threadCount = _threadCount + 1
    mainPanel.setThreadCount
  }

  def threadCountDec() = {
    if(_threadCount > 1) {
      _threadCount = _threadCount - 1
      mainPanel.setThreadCount
    }
  }

  def threadsDoneInc() = { threadsDone = threadsDone + 1}

  def readFileNames(directoryPath:String) = {
    val files = new java.io.File(directoryPath).listFiles.filter(_.getName.endsWith(".txt")).map(_.getAbsolutePath)
    files.foreach(s=>inputQueue.add(s))
  }

  def runNewThread() = {
    val computingThread = new ComputingThread(threadsDone, inputQueue.poll())

    threadInfoPanel.addNewItem(threadsDone)
    threadsDoneInc()

    val futurePrimeResult = Future {
      computingThread.primesComputation
      computingThread
    }

    futurePrimeResult onSuccess {
      case computingThread => {
        System.out.println(computingThread.getFileName)
        outputQueue.add(computingThread)
      }
    }
  }

  def threadManager = {
    while(inputQueue.size() > 0) {
      if( realThreadCount < threadCount ) {
        runNewThread()
        realThreadCount = realThreadCount + 1
      }
      val threadResult = outputQueue.poll(100, TimeUnit.MILLISECONDS)
      if(threadResult != null) {
        System.out.println("here: " + threadResult.prime)
        realThreadCount = realThreadCount - 1
        statsHolder.setStatistics(threadResult.minPrime, threadResult.maxPrime, threadResult.prime,threadResult.getFileName)
        statsPanel.updateStatistics(statsHolder.toString)
        threadInfoPanel.deleteItem(threadResult.getIdNumber)
      }
    }
  }

  def main(args: Array[String]): Unit = {

    readFileNames("data/rand_files") // path of rand files
    mainPanel.top.open()
    mainPanel.setThreadCount
    statsPanel.updateStatistics("read files: " + inputQueue.size().toString)

    threadManager
  }


}
