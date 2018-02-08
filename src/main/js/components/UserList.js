import React from "react";
import Spinner from 'react-webpack-spinner'
import axios from "axios";
import UserItem from "./UserItem"

export default class UserList extends React.Component {

	constructor(props) {
		super(props)
		this.state = { users: [] }
	}

  render() {
		if (this.state.users.length == 0) {	
			fetch('/api/v1/user/list/')
			.then((response) => {
					return response.json();
			})			
			.then((user) => {
        this.setState({ users: user })
      })
			
		}

	if (this.state.users.length > 0) {
		
		var indents = [];
		this.state.users.forEach(usuario => {
			indents.push(<UserItem user={usuario} />)
		})
		return (
			<div className="container-fluid">
					{indents}
			</div>
			)
			
		
	} else {
		return <p className="text-center">Loading users...</p>
	}


    
  }
}