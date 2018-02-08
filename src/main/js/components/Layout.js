import React from "react"
import { fetchUsers, addUser } from "../actions/userActions"
import AddUserForm from "./AddUserForm"
import UserList from "./UserList"

export default class Layout extends React.Component {





  render() {

   
    return (
      <div class="text-center">
        <AddUserForm addUser={addUser.bind(this)} />
        <UserList />
      </div>
    )
  }
}
