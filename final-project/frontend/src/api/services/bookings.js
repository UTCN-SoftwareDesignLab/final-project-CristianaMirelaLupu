import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allBookings() {
        console.log("stoped");
        return HTTP.get(BASE_URL + "/booking", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(booking) {
        console.log(booking);
        return HTTP.post(BASE_URL + "/booking", booking, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(booking) {
        return HTTP.put(BASE_URL + "/booking", booking, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    deleteById(id) {
        console.log("id: " + id)
        return HTTP.delete(BASE_URL + "/booking/" + id , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },
};
