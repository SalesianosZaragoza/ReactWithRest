import React from "react";

export default class UserItem extends React.Component {
  render() {

    const { user } = this.props;

    return (
      <div class="well col-md-4 col-md-offset-4">
        {user.nombre}
        {user.apellido}
        {user.dni}
      </div>
      
    );
  }
}
