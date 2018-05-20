package com.ferris.json

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

import scala.util.Try

trait FerrisJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object UUIDFormat extends RootJsonFormat[UUID] {
    override def write(obj: UUID): JsValue = {
      require(obj ne null)
      JsString(obj.toString)
    }
    override def read(value: JsValue): UUID = value match {
      case JsString(str) => UUID.fromString(str)
      case other => throw DeserializationException(s"Expected a UUID as JsString, but got $other")
    }
  }

  implicit object LocalDateFormat extends RootJsonFormat[LocalDate] {
    override def write(obj: LocalDate): JsValue = JsString(obj.toString)
    override def read(value: JsValue): LocalDate = {
      def exception(actual: String) = DeserializationException(s"Expected an ISO formatted date as JsString, but got $actual")
      value match {
        case JsString(str) => Try(LocalDate.parse(str)).getOrElse(throw exception(str))
        case other => throw exception(other.toString)
      }
    }
  }

  implicit object LocalDateTimeFormat extends RootJsonFormat[LocalDateTime] {
    override def write(obj: LocalDateTime): JsValue = JsString(obj.toString)
    override def read(value: JsValue): LocalDateTime = {
      def exception(actual: String) = DeserializationException(s"Expected an ISO formatted date-time as JsString, but got $actual")
      value match {
        case JsString(str) => Try(LocalDateTime.parse(str)).getOrElse(throw exception(str))
        case other => throw exception(other.toString)
      }
    }
  }
}
