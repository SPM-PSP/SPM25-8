import { timeAgo } from '@/utils/data'

describe('Data Utils', () => {
  beforeEach(() => {
    // 模拟当前时间
    jest.useFakeTimers()
  })

  afterEach(() => {
    jest.useRealTimers()
  })

  test('returns seconds ago for recent time', () => {
    const now = new Date('2024-03-20T10:00:00Z')
    jest.setSystemTime(now)
    
    const date = new Date('2024-03-20T09:59:30Z')
    expect(timeAgo(date.toISOString())).toBe('30 秒前')
  })

  test('returns minutes ago for time within an hour', () => {
    const now = new Date('2024-03-20T10:00:00Z')
    jest.setSystemTime(now)
    
    const date = new Date('2024-03-20T09:30:00Z')
    expect(timeAgo(date.toISOString())).toBe('30 分钟前')
  })

  test('returns hours ago for time within a day', () => {
    const now = new Date('2024-03-20T10:00:00Z')
    jest.setSystemTime(now)
    
    const date = new Date('2024-03-20T07:00:00Z')
    expect(timeAgo(date.toISOString())).toBe('3 小时前')
  })

  test('returns days ago for time more than a day', () => {
    const now = new Date('2024-03-20T10:00:00Z')
    jest.setSystemTime(now)
    
    const date = new Date('2024-03-19T10:00:00Z')
    expect(timeAgo(date.toISOString())).toBe('1 天前')
    
    const date2 = new Date('2024-03-18T10:00:00Z')
    expect(timeAgo(date2.toISOString())).toBe('2 天前')
  })

  test('returns 0 秒前 for now', () => {
    const now = new Date('2024-03-20T10:00:00Z')
    jest.setSystemTime(now)
    expect(timeAgo(now.toISOString())).toBe('0 秒前')
  })

  test('handles invalid date string', () => {
    expect(() => timeAgo('not-a-date')).not.toThrow()
  })
}) 