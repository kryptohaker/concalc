package scala.gui

import scala.swing._

/**
 * Created by ltrogr on 10/26/14 (edited by Ramil Mustafayev on 11/28/2019).
 */

object mainPanel extends SimpleSwingApplication {

  def setThreadCount:Unit = {
    controlPanel.setThreadCount
  }

  def top = new MainFrame {
    title = "Multithreaded primary digit checker"

    bounds = new Rectangle(300,300,300,300)
    minimumSize = new Dimension(500, 500)

    contents = new BoxPanel(Orientation.Vertical) {
      contents += controlPanel.contents
      contents += statsPanel.contents
      contents += threadInfoPanel.panelContents
    }

  }

}
