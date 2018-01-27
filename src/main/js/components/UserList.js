import React from "react";
import Spinner from 'react-webpack-spinner'
import axios from "axios";
import TodoItem from "./UserItem"

export default class UserList extends React.Component {
  render() {
	  console.log("hola");

	  const { users } = this.props;
	  let data;
	  axios.post("/api/v1/user/list/")
      .then((response) => {
		  console.log(response.data);
			data = response.data;

      })
      .catch((err) => {
        console.log(err);
      })

    console.log("adios");
	
	const UserList = this.data.map((user) => {
		return <UserItem user={user} />;
	});

    return (
    	<div>
    		{UserList}
    	</div>
    );
  }
}