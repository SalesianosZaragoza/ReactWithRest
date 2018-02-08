

export function fetchUsers() {
   

          fetch('/api/v1/user/list/')
            .then((response) => {
                console.log(response.data);
                return response.data;
            })
            .then((data) => {
                console.log(data)
            })
      
}

export function addUser(state, user) {
   
      

        fetch('/api/v1/user/create/')
        .then((response) => {
            console.log(response.data);
            return response.data;
        })
        .then((data) => {
            console.log(data)
        })
    
}