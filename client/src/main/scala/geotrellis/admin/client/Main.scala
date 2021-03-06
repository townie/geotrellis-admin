package geotrellis.admin.client

import scala.scalajs.js.JSApp

import geotrellis.admin.client.circuit._
import geotrellis.admin.client.routes._
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router._
import org.scalajs.dom


object Main extends JSApp {

  // apply app css
  AppCSS.load()

  // application location
  sealed trait Loc
  case object DashboardLoc extends Loc

  val baseUrl = BaseUrl(dom.window.location.href.takeWhile(_ != '#'))

  val routerConfig: RouterConfig[Loc] = RouterConfigDsl[Loc].buildConfig { dsl =>
    import dsl._

    def layout = ClientCircuit.wrap({r: RootModel => r})(GeotrellisAdminViewer(_))//_.displayM)(GeotrellisAdminViewer(_))

    (staticRoute(root, DashboardLoc) ~> render(layout))
      .notFound(redirectToPage(DashboardLoc)(Redirect.Replace))

  }

  /** The router is itself a React component, which at this point is not mounted (U-suffix) */
  val router: ReactComponentU[Unit, Resolution[Loc], Any, TopNode] =
    Router(baseUrl, routerConfig.logToConsole)()

  def main(): Unit = {
    ReactDOM.render(router, dom.document.getElementsByClassName("app")(0))
  }
}
