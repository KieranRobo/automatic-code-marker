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
    submit: (id, code, language, studentId) => {
      const submission = {
        language: language,
        code: code,
        student_id: studentId
      }
      return requests.post(`/challenges/${id}/attempt`, submission)
    },
    new: (newChallenge) =>
      requests.post(`/challenges`, newChallenge)
      
};

const Lecturers = {
  all: () =>
    requests.get(`/users/lecturers`),
  detailsByEmail: (email) =>
    requests.get(`/users/lecturers?email=${email}`),
  detailsById: (id) =>
    requests.get(`/users/lecturers/${id}`),
  new: (newLecturer) =>
    requests.post(`/users/lecturers`, newLecturer)
};

const Students = {
  all: () =>
    requests.get(`/users/students`),
  detailsByEmail: (email) =>
    requests.get(`/users/students?email=${email}`),
  detailsById: (id) =>
    requests.get(`/users/students/${id}`),
  new: (newStudent) =>
    requests.post(`/users/students`, newStudent)
};

const Classes = {
  all: () =>
    requests.get(`/classes`),
  details: (id) =>
    requests.get(`/classes/${id}`),
  assignChallenge: (classId, challengeId) => 
    requests.put(`/classes/${classId}/assign-challenge/${challengeId}`),
  new: (newClass) =>
    requests.post(`/classes`, newClass)
};


export default {
    Challenges,
    Lecturers,
    Students,
    Classes
};