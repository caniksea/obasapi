package services.users

import domain.users.UserRole
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserRoleServiceTest extends FunSuite{
  val entity = UserRole("1","aj","desc test")
  val repository = UserRoleService
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.userRoleId), 2 minutes)
    assert(result.head.userRoleId==entity.userRoleId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "desc tested")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userRoleId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userRoleId), 2 minutes)
    assert(result.isEmpty)

  }
}