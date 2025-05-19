import { shallowMount, createLocalVue } from '@vue/test-utils'
import Home from '@/views/user/Home.vue'
import { timeAgo } from '@/utils/data'

const localVue = createLocalVue()
localVue.component('TagLine', { template: '<div />', props: ['dataList'] })
localVue.component('Banner', { template: '<div />', props: ['data'] })
// mock element-ui 组件
localVue.component('el-row', { template: '<div><slot /></div>' })
localVue.component('el-col', { template: '<div><slot /></div>' })
localVue.component('el-empty', { template: '<div>empty</div>' })

const mockAxios = {
  post: jest.fn()
}

mockAxios.post.mockImplementation((url, data) => {
  if (url === '/tags/query') {
    return Promise.resolve({ 
      data: { 
        code: 200, 
        data: [
          { id: 1, name: 'Tag 1' },
          { id: 2, name: 'Tag 2' }
        ] 
      } 
    })
  } else if (url === '/news/query' && data.isTop) {
    return Promise.resolve({ 
      data: { 
        code: 200, 
        data: [
          { id: 1, name: 'Top News 1', cover: 'cover1.jpg', tagName: 'Tag 1', createTime: '2023-01-01T00:00:00Z' },
          { id: 2, name: 'Top News 2', cover: 'cover2.jpg', tagName: 'Tag 2', createTime: '2023-01-02T00:00:00Z' }
        ] 
      } 
    })
  } else if (url === '/news/query') {
    return Promise.resolve({ 
      data: { 
        code: 200, 
        data: [
          { id: 3, name: 'News 3', cover: 'cover3.jpg', tagName: 'Tag 1', createTime: '2023-01-03T00:00:00Z' },
          { id: 4, name: 'News 4', cover: 'cover4.jpg', tagName: 'Tag 2', createTime: '2023-01-04T00:00:00Z' }
        ] 
      } 
    })
  }
  return Promise.resolve({ data: { code: 200, data: [] } })
})

localVue.prototype.$axios = mockAxios
localVue.prototype.$router = { push: jest.fn() }

jest.mock('@/utils/data', () => ({
  timeAgo: jest.fn(() => '1天前')
}))

describe('Home.vue', () => {
  beforeEach(() => {
    jest.clearAllMocks()
    sessionStorage.clear()
  })

  it('renders without error', () => {
    const wrapper = shallowMount(Home, { localVue })
    expect(wrapper.exists()).toBe(true)
  })

  it('calls loadAllTags, loadAllNews, loadAllTopNews on created', () => {
    const spy1 = jest.spyOn(Home.methods, 'loadAllTags')
    const spy2 = jest.spyOn(Home.methods, 'loadAllNews')
    const spy3 = jest.spyOn(Home.methods, 'loadAllTopNews')
    shallowMount(Home, { localVue })
    expect(spy1).toHaveBeenCalled()
    expect(spy2).toHaveBeenCalled()
    expect(spy3).toHaveBeenCalled()
    spy1.mockRestore(); spy2.mockRestore(); spy3.mockRestore();
  })

  it('onBannerClick sets sessionStorage and routes', () => {
    const wrapper = shallowMount(Home, { localVue })
    const banner = { id: 1, name: 'test' }
    wrapper.vm.onBannerClick(banner)
    expect(sessionStorage.getItem('newsInfo')).toBe(JSON.stringify(banner))
    expect(wrapper.vm.$router.push).toHaveBeenCalledWith('/news-detail')
  })

  it('newsItemClick sets sessionStorage and routes', () => {
    const wrapper = shallowMount(Home, { localVue })
    const news = { id: 2, name: 'news' }
    wrapper.vm.newsItemClick(news)
    expect(sessionStorage.getItem('newsInfo')).toBe(JSON.stringify(news))
    expect(wrapper.vm.$router.push).toHaveBeenCalledWith('/news-detail')
  })

  it('parseTime calls timeAgo', () => {
    const wrapper = shallowMount(Home, { localVue })
    const result = wrapper.vm.parseTime('2024-01-01T00:00:00Z')
    expect(timeAgo).toHaveBeenCalledWith('2024-01-01T00:00:00Z')
    expect(result).toBe('1天前')
  })

  it('tagOnClick updates newQueryDto.tagId and loads news', async () => {
    const wrapper = shallowMount(Home, { localVue })
    const spy = jest.spyOn(wrapper.vm, 'loadAllNews')
    
    await wrapper.vm.tagOnClick({ id: 5, name: 'Test Tag' })
    
    expect(wrapper.vm.newQueryDto.tagId).toBe(5)
    expect(spy).toHaveBeenCalled()
  })

  it('loadAllTags fetches tags and adds "全部" option', async () => {
    const wrapper = shallowMount(Home, { localVue })
    await wrapper.vm.loadAllTags()
    
    expect(mockAxios.post).toHaveBeenCalledWith('/tags/query', {})
    expect(wrapper.vm.tagsList.length).toBe(3)
    expect(wrapper.vm.tagsList[0]).toEqual({ name: '全部', id: null })
    expect(wrapper.vm.tagsList[1]).toEqual({ id: 1, name: 'Tag 1' })
  })

  it('loadAllTopNews fetches top news', async () => {
    const wrapper = shallowMount(Home, { localVue })
    await wrapper.vm.loadAllTopNews()
    
    expect(mockAxios.post).toHaveBeenCalledWith('/news/query', { isTop: true })
    expect(wrapper.vm.newsTopList.length).toBe(2)
    expect(wrapper.vm.newsTopList[0]).toEqual({
      id: 1, 
      name: 'Top News 1', 
      cover: 'cover1.jpg', 
      tagName: 'Tag 1', 
      createTime: '2023-01-01T00:00:00Z'
    })
  })

  it('loadAllNews fetches news based on newQueryDto', async () => {
    const wrapper = shallowMount(Home, { localVue })
    wrapper.setData({ newQueryDto: { tagId: 2 } })
    
    await wrapper.vm.loadAllNews()
    
    expect(mockAxios.post).toHaveBeenCalledWith('/news/query', { tagId: 2 })
    expect(wrapper.vm.newsList.length).toBe(2)
    expect(wrapper.vm.newsList[0]).toEqual({
      id: 3, 
      name: 'News 3', 
      cover: 'cover3.jpg', 
      tagName: 'Tag 1', 
      createTime: '2023-01-03T00:00:00Z'
    })
  })

  it('should handle empty newsTopList correctly', () => {
    mockAxios.post.mockImplementationOnce(() => 
      Promise.resolve({ data: { code: 200, data: [] } })
    )
    
    const wrapper = shallowMount(Home, { localVue })
    expect(wrapper.vm.newsTopList).toEqual([])
  })

  it('should handle empty newsList correctly', () => {
    mockAxios.post.mockImplementationOnce(() => 
      Promise.resolve({ data: { code: 200, data: [] } })
    )
    
    const wrapper = shallowMount(Home, { localVue })
    expect(wrapper.vm.newsList).toEqual([])
  })
}) 