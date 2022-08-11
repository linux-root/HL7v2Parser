package util.hl7.sh.segment

import play.api.libs.json.{Format, Json}
import util.hl7.sh.datatype.MessageType

case class MessageHeader(fieldSeparator: String,
                         encodingCharacters: String,
                         time: String,
                         messageType: MessageType,
                         messageControlId: String,
                         processingId: String,
                         version: String) extends Segment
object MessageHeader{
  import util.hl7.sh.datatype.Datatype.messageType
  implicit val jsonFormat: Format[MessageHeader] = Json.format[MessageHeader]
}
