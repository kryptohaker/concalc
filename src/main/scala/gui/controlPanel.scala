package scala.gui

import exec.runner

import scala.swing.event.ButtonClicked
import scala.swing.{Button, FlowPanel, Label}

/**
 * Created by ltrogr on 10/26/14 (edited by Ramil Mustafayev on 11/28/2019).
 */
object controlPanel {

  def setThreadCount:Unit = {
    threadCountLabel.text = "Number of threads: " + runner.threadCount.toString
  }

  val threadCountLabel = new Label

  val buttonAddThread = new Button {
    text = "+"
    borderPainted = true
    enabled = true
    tooltip = "Add thread"
  }

  val buttonDeleteThread = new Button {
    text = "-"
    borderPainted = true
    enabled = true
    tooltip = "Remove thread"
  }

  val contents = new FlowPanel() {
    contents += threadCountLabel
    contents += buttonAddThread
    contents += buttonDeleteThread

    listenTo(buttonAddThread,buttonDeleteThread)

    reactions += {
      case ButtonClicked(component) if component == buttonAddThread => runner.threadCountInc()
      case ButtonClicked(component) if component == buttonDeleteThread => runner.threadCountDec()
    }
  }



}
