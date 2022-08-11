package util.hl7

import ca.uhn.hl7v2.model.Message
import ca.uhn.hl7v2.model.v28.message.{ADT_A01 => ADT_A01_28, ORU_R01 => ORU_R01_28}
import play.api.libs.json.{JsValue, Json}
import util.hl7.sh.datatype.{CompositeID, MessageType, PatientName}
import util.hl7.sh.message.HL7Message
import util.hl7.sh.segment
import util.hl7.sh.segment.{EventType, ObservationRequest, ObservationResult, PatientIdentification}
import scala.jdk.CollectionConverters.CollectionHasAsScala

/** *
 * convert HAPI message to HL7Message
 */
trait HL7Converter[M <: Message] {
  def convert(message: M): HL7Message
}

object HL7Converter {
  def apply[M <: Message : HL7Converter]: HL7Converter[M] = implicitly[HL7Converter[M]]

  def instance[M <: Message](f: M => HL7Message): HL7Converter[M] = new HL7Converter[M] {
    override def convert(message: M): HL7Message = f(message)
  }

  object Syntax {
    implicit class Ops[M <: Message](val message: M) extends AnyVal {
      def toJson(implicit c: HL7Converter[M]): JsValue = Json.toJson(c.convert(this.message))
    }
  }


  implicit val ADT_A01_28: HL7Converter[ADT_A01_28] = instance { message =>
    val evn = message.getEVN
    val pid = message.getPID
    val msh = message.getMSH

    evn.getRecordedDateTime.getValue
    val identifiers = pid.getPid3_PatientIdentifierList.map { cx =>
      CompositeID(cx.getIDNumber.getValue)
    }.toList

    val names = pid.getPatientName.map { xpn =>
      PatientName(xpn.getFamilyName.getSurname.getValue, xpn.getGivenName.getValue)
    }.toList

    val mshType = msh.getMessageType

    val messageType = MessageType(mshType.getMessageCode.getValue, mshType.getTriggerEvent.getValue)

    val header = segment.MessageHeader(
      msh.getFieldSeparator.getValue,
      msh.getEncodingCharacters.getValue,
      msh.getDateTimeOfMessage.getValue,
      messageType,
      msh.getMessageControlID.getValue,
      msh.getProcessingID.getProcessingID.getValue,
      msh.getVersionID.getVid1_VersionID.getValue
    )

    sh.message.ADT_A01(header, EventType(evn.getRecordedDateTime.getValue), PatientIdentification(identifiers, names))
  }


  implicit val ORU_R01_28: HL7Converter[ORU_R01_28] = instance { message =>
    val msh = message.getMSH
    val patientResult = message.getPATIENT_RESULT
    val pid = patientResult.getPATIENT.getPID


    val identifiers = pid.getPid3_PatientIdentifierList.map { cx =>
      CompositeID(cx.getIDNumber.getValue)
    }.toList

    val names = pid.getPatientName.map { xpn =>
      PatientName(xpn.getFamilyName.getSurname.getValue, xpn.getGivenName.getValue)
    }.toList

    val mshType = msh.getMessageType

    val messageType = MessageType(mshType.getMessageCode.getValue, mshType.getTriggerEvent.getValue)

    val header = segment.MessageHeader(
      msh.getFieldSeparator.getValue,
      msh.getEncodingCharacters.getValue,
      msh.getDateTimeOfMessage.getValue,
      messageType,
      msh.getMessageControlID.getValue,
      msh.getProcessingID.getProcessingID.getValue,
      msh.getVersionID.getVid1_VersionID.getValue
    )

    val patientIdentification = PatientIdentification(identifiers, names)


    val observationRequest = ObservationRequest("N/A") // this should be required field but Reste Lab sample doesn't contain this

    val observationResults = patientResult.getPATIENT.getPATIENT_OBSERVATIONAll.asScala.map { observation =>
      val obx = observation.getOBX
      ObservationResult(
        obx.getSetIDOBX.getValue,
        obx.getValueType.getValue,
        obx.getObservationIdentifier.getIdentifier.getValue,
        obx.getObservationValue.map { value => value.getData.toString }.toList,
        obx.getUnits.getIdentifier.getValue,
        obx.getReferencesRange.getValue,
        obx.getObservationResultStatus.getValue
      )
    }.toList

    sh.message.ORU_R01(header, patientIdentification, observationRequest, observationResults)
  }
}