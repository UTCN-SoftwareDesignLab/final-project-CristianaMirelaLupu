import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allTourists() {
    return HTTP.get(BASE_URL + "/tourist", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(tourist) {
    return HTTP.post(BASE_URL + "/tourist", tourist, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(tourist) {
    return HTTP.patch(BASE_URL + "/tourist", tourist, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
    deleteById(id) {
        console.log("id: " + id)
        return HTTP.delete(BASE_URL + "/tourist/" + id , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },
};
