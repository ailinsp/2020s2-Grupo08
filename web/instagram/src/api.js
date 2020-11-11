import axios from 'axios';

axios.defaults.baseURL = "http://localhost:7000";
axios.defaults.headers.post['Content-Type'] = 'application/json';

const request = (type, path, body) =>
  axios
    .request({ url: path, method: type, data: body })
    .then(response => response.data);


export const login = body => request('post', '/login', body);
export const register = body => request('post','/register', body);
export const getTimeline = body => request('get', '/home', body);
export const getUserProfile = body => request('get', '/profile/${username}', body);
export const followUser = body => request('put', '/follow/${username}', body);
export const likePost = body => request('put', '/like/${post}', body);
export const commentPost = body => request('post', '/comment/${post}', body);
export const search = body => request('get', '/search', body);

