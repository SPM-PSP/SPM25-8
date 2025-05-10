import { getToken, setToken, getHealthInfo, setHealthInfo, clearToken, getActivePath, setActivePath } from '@/utils/storage'

describe('Storage Utils', () => {
  beforeEach(() => {
    // 在每个测试前清除 sessionStorage
    sessionStorage.clear()
  })

  test('token operations', () => {
    const testToken = 'test-token'
    setToken(testToken)
    expect(getToken()).toBe(testToken)
    
    clearToken()
    expect(getToken()).toBeNull()
  })

  test('health info operations', () => {
    const testInfo = { weight: 70, height: 180 }
    setHealthInfo(JSON.stringify(testInfo))
    expect(getHealthInfo()).toBe(JSON.stringify(testInfo))
  })

  test('active path operations', () => {
    const testPath = '/dashboard'
    setActivePath(testPath)
    expect(getActivePath()).toBe(testPath)
  })

  test('clearToken clears all storage', () => {
    setToken('test-token')
    setHealthInfo('test-info')
    setActivePath('/test')
    
    clearToken()
    
    expect(getToken()).toBeNull()
    expect(getHealthInfo()).toBeNull()
    expect(getActivePath()).toBeNull()
  })
}) 