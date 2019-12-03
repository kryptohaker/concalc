package scala.gui

import scala.swing._

/**
 * Created by ltrogr on 10/26/14 (edited by Ramil Mustafayev on 11/28/2019).
 */
class ThreadInfoItem {

  val infoArea = new TextArea
  val progressBar = new ProgressBar {
    min = 0
  }

  val contents = new BoxPanel(Orientation.Vertical) {
    border = Swing.EmptyBorder(2,2,2,2)
    contents += infoArea
    contents += progressBar
  }

  def getContents = contents

  def setInfo(strInfo:String) = {
    infoArea.text_=(strInfo)
  }

  def setProgress(hows:Int) = {
    progressBar.value = hows
  }

  def setProgressMax(maxProgress:Int) = {
    progressBar.max = maxProgress
  }

}
