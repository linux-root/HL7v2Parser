package util

package object hl7 {
  implicit class StringOps(val text: String) extends AnyVal{
     def ER7StripMargin: String = {
       text.stripMargin('#').trim.replaceAll("\n", "\r")
     }
  }
}
