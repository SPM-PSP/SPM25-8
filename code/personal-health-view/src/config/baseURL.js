// config/baseURL.js
// 可根据环境变量自动切换
// const BASE_URL = {
//   development: 'http://127.0.0.1:21090/api/personal-heath/v1.0',
//   production: 'http://backserver.openbase.store:21090/api/personal-heath/v1.0'
// };

const BASE_URL = process.env.VUE_APP_BASE_API || 'http://127.0.0.1:21090/api/personal-heath/v1.0';

export default BASE_URL;
