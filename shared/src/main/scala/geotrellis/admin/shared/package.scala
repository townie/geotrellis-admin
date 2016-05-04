package geotrellis.admin


package object shared {
  case class ClassBreaks(breaks: Array[Double] = Array())

  case class LayerDescription(name: String = "", availableZooms: Seq[Int] = Seq())

  case class Metadata(extent: Extent, layoutDefinition: LayoutDefinition, bounds: Bounds, cellType: String, crs: String)
  case class Extent(xmin: Double, ymin: Double, xmax: Double, ymax: Double)
  case class TileLayout(layoutCols: Int, layoutRows: Int, tileCols: Int, tileRows: Int)
  case class LayoutDefinition(extent: Extent, tileLayout: TileLayout)

  case class Key(col: Int, row: Int)
  case class Bounds(minKey: Key, maxKey: Key)
}