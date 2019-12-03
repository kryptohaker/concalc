package scala.gui

import scala.swing.{BoxPanel, Orientation}

/**
 * Created by ltrogr on 10/26/14 (edited by Ramil Mustafayev on 11/28/2019).
 */
object threadInfoPanel {

  var panelMap: Map[Int, ThreadInfoItem] = Map()

  val panelContents = new BoxPanel(Orientation.Vertical)

  def addNewItem(panelId: Int) = {
    val threadInfoItem = new ThreadInfoItem
    panelMap += (panelId -> threadInfoItem)
    panelContents.contents += threadInfoItem.getContents
  }

  def deleteItem(panelId: Int) = {
    val panel = getPanel(panelId)
    panelContents.contents -= panel.getContents
  }

  def getPanel(panelId:Int) = panelMap(panelId)
}
