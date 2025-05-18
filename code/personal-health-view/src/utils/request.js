import axios from "axios"
import { getToken } from "@/utils/storage.js";

import URL_API from "@/config/baseURL.js";

const request = axios.create({
  baseURL: URL_API,
  timeout: 8000
});
//全局拦截器
request.interceptors.request.use(config => {
  const token = getToken();
  if (token !== null) {
    config.headers["token"] = token;
  }
  return config;
}, error => {
  return Promise.reject(error);
});
export default request;
