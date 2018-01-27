import React from "react"
import { connect } from "react-redux"

import { fetchUsers, addUser } from "../actions/userActions"
import axios from "axios";
import AddUserForm from "./AddUserForm"
import UserList from "./UserList"

export default class Layout extends React.Component {





  render() {

   
    return (
      <div class="text-center">
        {/* <AddUserForm addUser={addUser.bind(this)} /> */}
        <UserList />
      </div>
    )
  }
}
