import axios from "axios";

export function fetchUsers() {
        axios.post("/api/v1/user/list/")
          .then((response) => {
            console.log(response.data);
            return response.data;
          })
          .catch((err) => {
            return err;
          })
      
}

export function addUser(state, user) {
   
        axios.post("/api/v1/user/create/", {
            "name": user.name,
            "surname": user.surname,
            "dni": user.dni
        })
        .then((response) => {
            console.log(response.data);
            return response.data;
        })
        .catch((err) => {
            return err;
        })
    
}