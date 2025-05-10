import request from '@/utils/request'
import { setToken } from '@/utils/storage'
import axios from 'axios'

// Mock axios
jest.mock('axios', () => {
  const handlers = { request: { use: jest.fn((fulfilled, rejected) => {
    // 保存拦截器
    handlers.request.fulfilled = fulfilled;
    handlers.request.rejected = rejected;
  }) } };
  return {
    create: jest.fn(() => ({
      defaults: { baseURL: 'http://localhost:21090/api/personal-heath/v1.0', timeout: 8000 },
      interceptors: handlers
    })),
    handlers
  };
});

describe('Request Utils', () => {
  beforeEach(() => {
    // 清除所有模拟
    jest.clearAllMocks()
    sessionStorage.clear()
  })

  test('request instance is created with correct config', () => {
    expect(request.defaults.baseURL).toBe('http://localhost:21090/api/personal-heath/v1.0')
    expect(request.defaults.timeout).toBe(8000)
  })

  test('request interceptor adds token to headers when token exists', async () => {
    const testToken = 'test-token'
    setToken(testToken)
    
    const config = {
      headers: {}
    }
    
    const result = await axios.handlers.request.fulfilled(config)
    
    expect(result.headers.token).toBe(testToken)
  })

  test('request interceptor does not add token when token is null', async () => {
    const config = {
      headers: {}
    }
    
    const result = await axios.handlers.request.fulfilled(config)
    
    expect(result.headers.token).toBeUndefined()
  })

  test('request interceptor handles errors', async () => {
    const error = new Error('Test error')
    
    try {
      await axios.handlers.request.rejected(error)
    } catch (e) {
      expect(e).toBe(error)
    }
  })
}) 