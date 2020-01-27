import axios from "axios";

const API_ROOT = 'http://localhost:8080';

const requests = {
    get: url =>
      axios.get(`${API_ROOT}${url}`),
    post: (url, data) =>
      axios.post(`${API_ROOT}${url}`, data),
    put: (url, data) =>
      axios.put(`${API_ROOT}${url}`, data)
};

const Challenges = {
    all: () =>
      requests.get(`/challenges`),
    details: (id) =>
      requests.get(`/challenges/${id}`),
    submit: (id, code) => {
      const submission = {
        language: 'PYTHON',
        code: code
      }
      return requests.post(`/challenges/${id}/attempt`, submission)
    },
    new: (newChallenge) =>
      requests.put(`/challenges`, newChallenge)
      
};


export default {
    Challenges
};