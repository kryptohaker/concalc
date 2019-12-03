package scala.gui

import java.awt.Color

import javax.swing.BorderFactory

import scala.swing.{FlowPanel, TextArea}

/**
 * Created by ltrogr on 10/26/14 (edited by Ramil Mustafayev on 11/28/2019).
 */
object statsPanel {

  def updateStatistics(newStat:String):Unit = {
    stats.text = newStat
  }

  val stats = new TextArea {
    text = ""
  }

  val contents = new FlowPanel {
    contents += stats
    border = BorderFactory.createLineBorder(Color.black);
  }


}
