package controllers.address

import domain.address.AddressType
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}


class AddressTypeControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val entity = AddressType("1", "Postal Code")
  val token = "eyJraWQiOiJURVNUX1BIUkFTRSIsImFsZyI6IkVTMjU2In0.eyJpc3MiOiJIQVNIQ09ERS5aTSIsImF1ZCI6IlNJVEVVU0VSUyIsImV4cCI6MTU2NjQ3NDYzNiwianRpIjoiX0dxSG9Dc3dFU1J1R2pGaXBsRzhHZyIsImlhdCI6MTU2NjM4ODIzNiwibmJmIjoxNTY2Mzg4MTE2LCJzdWIiOiJTaXRlIEFjY2VzcyIsImVtYWlsIjoidGVzdEBnbWFpbC5jb20iLCJyb2xlIjoiU1RSMDAxIn0.JH-vlwm0PSRSoBE9D3ZrgMhf_Li3gARBLCf6NUZNdHifvbYo3_iQaaBf8baI2H5DgO87oN6Jrb1RGSxVXdIcDg"


  "EntityController" should {

    "Create Entity" in {

      val request = route(app, FakeRequest(POST, "/address/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }


    "Read Entity " in {

      val request = route(app, FakeRequest(GET, "/address/get/$addressTypeID" + entity.addressTypeID)
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Get Entities" in {
      val request = route(app, FakeRequest(GET, "/address/all")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))

    }

    "Update Entity" in {
      val updatedEntity = entity.copy(addressName = "updated")
      val request = route(app, FakeRequest(POST, "/address/update")
        .withJsonBody(Json.toJson(updatedEntity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }
  }
}

