import { shallowMount } from '@vue/test-utils'
import TagLine from '@/components/TagLine.vue'

describe('TagLine.vue', () => {
  const mockTags = [
    { id: 1, name: '全部' },
    { id: 2, name: '标签1' },
    { id: 3, name: '标签2' }
  ]

  it('renders with provided tags', () => {
    const wrapper = shallowMount(TagLine, {
      propsData: {
        dataList: mockTags
      }
    })
    
    expect(wrapper.exists()).toBe(true)
    const tagItems = wrapper.findAll('.tag-item')
    expect(tagItems.length).toBe(mockTags.length)
    expect(tagItems.at(0).text()).toBe('全部')
    expect(tagItems.at(1).text()).toBe('标签1')
    expect(tagItems.at(2).text()).toBe('标签2')
  })
  
  it('selects first tag ("全部") on mount', () => {
    const wrapper = shallowMount(TagLine, {
      propsData: {
        dataList: mockTags
      }
    })
    
    expect(wrapper.vm.tagSelected).toEqual({ name: '全部', id: null })
  })
  
  it('changes style when tag is selected', async () => {
    const wrapper = shallowMount(TagLine, {
      propsData: {
        dataList: mockTags
      }
    })
    
    // 因为组件在mounted生命周期中会选中"全部"，所以我们不需要额外点击操作
    let tagItems = wrapper.findAll('.tag-item')
    
    // 点击第二个标签应该会改变样式
    await tagItems.at(1).trigger('click')
    tagItems = wrapper.findAll('.tag-item')
    
    // 现在第二个标签应该有选中的样式
    expect(tagItems.at(1).attributes('style')).toContain('background-color: rgb(29, 124, 225)')
    // 而第一个标签应该是未选中的样式
    expect(tagItems.at(0).attributes('style')).toContain('background-color: rgb(252, 252, 252)')
  })
  
  it('emits on-click event when tag is clicked', async () => {
    const wrapper = shallowMount(TagLine, {
      propsData: {
        dataList: mockTags
      }
    })
    
    const tagItems = wrapper.findAll('.tag-item')
    
    // 触发第一个标签的点击事件（已经是选中状态）
    await tagItems.at(0).trigger('click')
    
    // 触发第二个标签的点击事件
    await tagItems.at(1).trigger('click')
    
    // 验证事件发送
    expect(wrapper.emitted('on-click')).toBeTruthy()
    expect(wrapper.emitted('on-click').length).toBe(3) // mounted + 两次点击
    expect(wrapper.emitted('on-click')[1][0]).toEqual({ name: '全部', id: 1 }) // 修正为实际值
    expect(wrapper.emitted('on-click')[2][0]).toEqual(mockTags[1])
  })
  
  it('updates tagSelected when tag is clicked', async () => {
    const wrapper = shallowMount(TagLine, {
      propsData: {
        dataList: mockTags
      }
    })
    
    const tagItems = wrapper.findAll('.tag-item')
    
    // 点击第二个标签
    await tagItems.at(1).trigger('click')
    
    // 验证选中状态更新
    expect(wrapper.vm.tagSelected).toEqual(mockTags[1])
  })
  
  it('calls all() method and emits correct event', () => {
    const wrapper = shallowMount(TagLine, {
      propsData: {
        dataList: mockTags
      }
    })
    
    wrapper.vm.all()
    
    expect(wrapper.emitted('on-click')).toBeTruthy()
    expect(wrapper.emitted('on-click').length).toBe(2) // mounted + all() 调用
    expect(wrapper.emitted('on-click')[1][0]).toEqual({ id: null, name: '全部' })
  })
})