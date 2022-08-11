package util.hl7.sh.segment

import play.api.libs.json.{Format, Json}

case class EventType(recordedDatetime: String) extends Segment

object EventType {
  implicit val jsonFormat: Format[EventType] = Json.format[EventType]
}