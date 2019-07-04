package services.users

import domain.users.UserDemographics
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserDemographicsServiceTest extends FunSuite{
  val entity = UserDemographics("1","genderTest", "raceTest")
  val repository = UserDemographicsService
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.userDemographicsId), 2 minutes)
    assert(result.head.userDemographicsId==entity.userDemographicsId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(genderId = "genderTested")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userDemographicsId), 2 minutes)
    assert(result.head.genderId==updatedEntity.genderId)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userDemographicsId), 2 minutes)
    assert(result.isEmpty)

  }
}