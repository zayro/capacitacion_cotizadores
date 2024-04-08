import javax.inject.Inject

import play.api.http.DefaultHttpFilters

import play.filters.csrf.CSRFFilter
import play.filters.headers.SecurityHeadersFilter
import play.filters.hosts.AllowedHostsFilter

/**
 * Add the following filters by default to all projects
 *
 * https://www.playframework.com/documentation/latest/ScalaCsrf
 * https://www.playframework.com/documentation/latest/AllowedHostsFilter
 * https://www.playframework.com/documentation/latest/SecurityHeaders
<<<<<<< HEAD:zayro/ms-scala/app/Filters.scala
 */
class Filters @Inject() (

) extends DefaultHttpFilters(

)
=======
 */
>>>>>>> feature/capacitacion_jorge:jorge/back/zayro/ms-scala/app/Filters.scala
